/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sps.software.servlet;

import com.sps.entity.Cliente;
import com.sps.entity.Reserva;
import com.sps.entity.Usuario;
import com.sps.sessionBean.ClienteFacadeLocal;
import com.sps.sessionBean.ReservaFacadeLocal;
import com.sps.sessionBean.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author andre
 */
@WebServlet(name = "AsignarServlet", urlPatterns = {"/AsignarServlet"})
public class AsignarServlet extends HttpServlet {

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

        String dia = request.getParameter("dia");
        String placa = request.getParameter("placa");
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
