package com.sps.software.servlet;

import com.sps.entity.Cliente;
import com.sps.entity.Persona;
import com.sps.entity.Reserva;
import com.sps.entity.Usuario;
import com.sps.sessionBean.*;
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
public class HistorialServlet extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaSession;
    @EJB
    private ClienteFacadeLocal clienteSession;

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

        Object perfilObject = request.getSession().getAttribute("perfil");

        if (perfilObject != null) {
            if (perfilObject.getClass().getName().equals("com.sps.entity.Usuario")) {
                Usuario perfil = (Usuario) perfilObject;
                Persona persona = (Persona) request.getSession().getAttribute("persona");

                List<Reserva> reservas = reservaSession.findAllByUsuario(perfil);
                if (!reservas.isEmpty()) {
                    request.setAttribute("reservas", reservas);
                }
//        ArrayList<Reserva> reservasHistorial = null;
//
//        for (Reserva reserva : reservas) {
//            if (!reserva.getOcupado()) {
//
//                Cliente cliente = clienteSession.find(reserva.getIdCliente());
//
//                String horaEntrada[] = reserva.getEntrada().split(":");
//                String horaSalida[] = reserva.getSalida().split(":");
//                int horaE = Integer.parseInt(horaEntrada[0]);
//                int horaS = Integer.parseInt(horaSalida[0]);
//                int minutoE = Integer.parseInt(horaEntrada[1]);
//                int minutoS = Integer.parseInt(horaSalida[2]);
//                int total = 0;
//
//                if (minutoS - minutoE < 0) {
//                    horaE = horaE - 1;
//                    minutoE = minutoE + 60;
//                }
//
//                total += minutoS - minutoE;
//                total += (horaS - horaE) * 60;
//
//                reserva.setPrecio(cliente.getPrecio() * total);
//            }
//            reservasHistorial.add(reserva);
//        }
            } else if (perfilObject.getClass().getName().equals("com.sps.entity.Cliente")) {
                Cliente parqueo = (Cliente) request.getSession().getAttribute("perfil");

                List<Reserva> reservas = reservaSession.findAllByCliente(parqueo);
                if (!reservas.isEmpty()) {
                    request.setAttribute("reservas", reservas);
                }
            }
            request.getRequestDispatcher("historial.jsp").forward(request, response);
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
