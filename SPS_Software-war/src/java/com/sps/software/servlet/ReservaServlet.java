package com.sps.software.servlet;

import com.sps.session.*;
import com.sps.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
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
    @EJB
    private PlazaFacadeLocal plazaSession;

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
        System.out.println("OPCION: " + opcion);
        Object perfilObject = request.getSession().getAttribute("perfil");

        if (perfilObject != null) {
            if (perfilObject instanceof Usuario) {
                Usuario perfil = (Usuario) perfilObject;

                if (opcion == null) {
                    /**
                     * Reserva reservaPersona =
                     * reservaSession.findByUsuario(perfil); if (reservaPersona
                     * == null) {*
                     */
                    boolean cubierto = request.getParameter("cubierto").equals("si");
                    List<Object[]> plazas = plazaSession.findNoExistsGroup(perfil.getTipoVehiculo(), cubierto);

                    for (int i = 0; i < plazas.size(); i++) {
                        Object cliente = plazas.get(i);

                        try (PrintWriter out = response.getWriter()) {
                            out.println(clienteSession.find(cliente));
                        }
                    }

//                    request.setAttribute("parqueaderos", clientes);
                    /**
                     * }*
                     */
//                    request.getRequestDispatcher("reservar.jsp").forward(request, response);
                } else if (opcion.equalsIgnoreCase("reservar")) {

                    boolean cubierto = request.getParameter("cubierto").equals("si");
                    boolean tipoVehiculo = perfil.getTipoVehiculo();

                    String clienteID = request.getParameter("idCliente");
                    Cliente cliente = clienteSession.find(clienteID);

                    Plaza plaza = plazaSession.findPlazaNoExists(tipoVehiculo, cubierto, cliente);
                    String dia = request.getParameter("dia");
                    String entrada = request.getParameter("entrada");

                    Reserva reserva = new Reserva(dia, entrada, true, plaza, perfil);
                    reservaSession.create(reserva);

//                    Reserva reservaRegistrar = new Reserva(dia, entrada, "", 0, null, perfil);
//
//                    reservaSession.create(reservaRegistrar);
                    /**
                     * if (reservaSession.create(reservaRegistrar)) {
                     * System.err.println("CREADO RESERVA"); } else {
                     * System.err.println("RESERVA NO CREADA"); }
                     *
                     */
                    request.getRequestDispatcher("inicio").forward(request, response);
                }
            } else if (perfilObject instanceof Cliente) {
                String dia = request.getParameter("dia");
                String placa = request.getParameter("placa").toUpperCase();
                String hora = request.getParameter("hora");
                String idCliente = request.getParameter("cliente");
                boolean cubierto = request.getParameter("cubierto").equals("si");
                Cliente cliente = clienteSession.find(idCliente);
                Usuario perfil = usuarioSession.findByPlaca(placa);

                Plaza plaza = plazaSession.findPlazaNoExists(perfil.getTipoVehiculo(), cubierto, cliente);
                Reserva reserva = new Reserva(dia, hora, true, plaza, perfil);
                reservaSession.create(reserva);

//                Reserva reservaRegistrar = new Reserva(dia, hora, "", 0, null, perfil);
//
//                reservaSession.create(reservaRegistrar);
                /**
                 * if (reservaSession.create(reservaRegistrar)) {
                 * System.err.println("CREADO RESERVA"); } else {
                 * System.err.println("RESERVA NO CREADA"); }*
                 *
                 */
                request.getRequestDispatcher("reservar.jsp").forward(request, response);
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
