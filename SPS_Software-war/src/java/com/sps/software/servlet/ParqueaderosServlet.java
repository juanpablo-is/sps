package com.sps.software.servlet;

import com.sps.sessionBean.*;
import com.sps.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Pablo
 */
//@WebServlet(name = "ParqueaderosServlet", urlPatterns = {"/ParqueaderosServlet"})
public class ParqueaderosServlet extends HttpServlet {

    @EJB
    private ClienteFacadeLocal clienteSession;

    @EJB
    private ReservaFacadeLocal reservaSession;

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
            List<Cliente> clientesSelector = clienteSession.findAll();
            ArrayList<Cliente> clientes = new ArrayList<>();

            for (int i = 0; i < clientesSelector.size(); i++) {
                Cliente cliente = clientesSelector.get(i);
                Number count = reservaSession.findSelector(cliente);
                cliente.setCupos(cliente.getCupos() - count.intValue());
                clientes.add(cliente);
            }

            request.setAttribute("parqueaderos", clientes);
            request.getRequestDispatcher("parqueaderos.jsp").forward(request, response);
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
