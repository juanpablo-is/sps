package com.sps.software.servlet;

import com.sps.session.*;
import com.sps.entity.*;
import java.io.IOException;
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
    @EJB
    private HistorialFacadeLocal historialSession;

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
            if (perfilObject instanceof Usuario) {
                Usuario perfil = (Usuario) perfilObject;

                List<Historial> historiales = historialSession.findByUsuario(perfil);
                
                if (!historiales.isEmpty()) {
                    request.setAttribute("historiales", historiales);
                }
                /**
                 * Persona persona = (Persona)
                 * request.getSession().getAttribute("persona");
                 * ArrayList<Reserva> reservasHistorial = null;
                 *
                 * for (Reserva reserva : reservas) { if (!reserva.getOcupado())
                 * {
                 *
                 * Cliente cliente =
                 * clienteSession.find(reserva.getIdCliente());
                 *
                 * String horaEntrada[] = reserva.getEntrada().split(":");
                 * String horaSalida[] = reserva.getSalida().split(":"); int
                 * horaE = Integer.parseInt(horaEntrada[0]); int horaS =
                 * Integer.parseInt(horaSalida[0]); int minutoE =
                 * Integer.parseInt(horaEntrada[1]); int minutoS =
                 * Integer.parseInt(horaSalida[2]); int total = 0;
                 *
                 * if (minutoS - minutoE < 0) { horaE = horaE - 1; minutoE =
                 * minutoE + 60; }
                 *
                 * total += minutoS - minutoE; total += (horaS - horaE) * 60;
                 *
                 * reserva.setPrecio(cliente.getPrecio() * total); }
                 * reservasHistorial.add(reserva); }
                 *
                 */
            } else if (perfilObject instanceof Cliente) {
                Cliente parqueo = (Cliente) request.getSession().getAttribute("perfil");

//                List<Reserva> reservas = reservaSession.findAllByCliente(parqueo);
                List<Historial> historiales = historialSession.findByCliente(parqueo);
                
                if (!historiales.isEmpty()) {
                    request.setAttribute("historiales", historiales);
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
