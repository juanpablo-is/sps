package com.sps.software.servlet;

import com.sps.entity.Cliente;
import com.sps.entity.Persona;
import com.sps.entity.Usuario;
import com.sps.sessionBean.ClienteFacadeLocal;
import com.sps.sessionBean.PersonaFacadeLocal;
import com.sps.sessionBean.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Pablo
 */
public class SeleccionServlet extends HttpServlet {

    @EJB
    private PersonaFacadeLocal sessioBeanPersona;
    @EJB
    private UsuarioFacadeLocal sessionBeanUsuario;
    @EJB
    private ClienteFacadeLocal sessionBeanCliente;

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

        Object perfilObject = request.getSession().getAttribute("persona");
        System.out.println("PERSONA: "+perfilObject);
        
        String id = request.getParameter("id");
        if (id != null) {
            String campos[] = id.split("-");
            id = campos[1];
            Object perfil = null;

            if (campos[0].equals("U")) {
                Usuario usuario = sessionBeanUsuario.find(id);
                perfil = usuario;
            } else if (campos[0].equals("C")) {
                Cliente cliente = sessionBeanCliente.find(Integer.parseInt(id));
                perfil = cliente;
            }

            HttpSession sesion = request.getSession(true);
            sesion.setAttribute("perfil", perfil);

            response.sendRedirect("InicioServlet");
//        request.getRequestDispatcher("InicioServlet").include(request, response);
        } else if (perfilObject != null) {
            response.sendRedirect("InicioServlet");
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
