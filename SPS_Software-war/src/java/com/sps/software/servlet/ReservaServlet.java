package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.sessionBean.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Pablo
 */
public class ReservaServlet extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaSession;
    @EJB
    private ClienteFacadeLocal clienteSession;
    @EJB
    private UsuarioFacadeLocal usuarioSession;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcion = request.getParameter("reservar");
        Object perfilObject = request.getSession().getAttribute("perfil");
        
        if (perfilObject != null) {
            if (perfilObject.getClass().getName().equals("com.sps.entity.Usuario")) {
                Usuario perfil = (Usuario) perfilObject;

                if (opcion == null) {
//            Reserva reservaPersona = reservaSession.findByUsuario(perfil);
//            if (reservaPersona == null) {
                    List<Cliente> clientesSelector = clienteSession.findAll();
                    ArrayList<Cliente> clientes = new ArrayList<>();

                    for (int i = 0; i < clientesSelector.size(); i++) {
                        Cliente cliente = clientesSelector.get(i);
                        Number count = reservaSession.findSelector(cliente);
                        cliente.setCupos(cliente.getCupos() - count.intValue());
                        clientes.add(cliente);
                    }

                    request.setAttribute("parqueaderos", clientes);
//            }
                    request.getRequestDispatcher("reservar.jsp").forward(request, response);
                } else if (opcion.equalsIgnoreCase("reservar")) {
                    String dia = request.getParameter("dia");

                    String entrada = request.getParameter("entrada");
                    String clienteID = request.getParameter("idCliente");

                    Cliente cliente = clienteSession.findByID(Integer.parseInt(clienteID));

                    Reserva reservaRegistrar = new Reserva(dia, entrada, null, perfil, cliente, true);

                    if (reservaSession.create(reservaRegistrar)) {
                        System.out.println("CREADO RESERVA");
                    } else {
                        System.out.println("RESERVA NO CREADA");
                    }
                    request.getRequestDispatcher("InicioServlet").forward(request, response);
                }
            } else if (perfilObject.getClass().getName().equals("com.sps.entity.Cliente")) {
                String dia = request.getParameter("dia");
                String placa = request.getParameter("placa").toUpperCase();
                String hora = request.getParameter("hora");
                String idCliente = request.getParameter("cliente");

                Cliente cliente = clienteSession.findByID(Integer.parseInt(idCliente));
                Usuario perfil = usuarioSession.findByPlaca(placa);

                Reserva reservaRegistrar = new Reserva(dia, hora, null, perfil, cliente, true);

                if (reservaSession.create(reservaRegistrar)) {
                    System.out.println("CREADO RESERVA");
                } else {
                    System.out.println("RESERVA NO CREADA");
                }
                request.getRequestDispatcher("asignar.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("index.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
