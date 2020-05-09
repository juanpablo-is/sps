package com.sps.software.servlet;

import com.sps.entity.Usuario;
import com.sps.sessionBean.ClienteFacadeLocal;
import com.sps.sessionBean.MovilidadFacadeLocal;
import com.sps.sessionBean.UsuarioFacadeLocal;
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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Juan Pablo
 */
@WebServlet(name = "AJAXCuentasPersona", urlPatterns = {"/AJAXCuentasPersona"})
public class AJAXCuentasPersona extends HttpServlet {

    @EJB
    private UsuarioFacadeLocal sessionBeanUsuario;
    @EJB
    private ClienteFacadeLocal sessionBeanCliente;
    @EJB
    private MovilidadFacadeLocal sessionBeanMovilidad;

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

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            if (request.getParameter("proceso").equals("1")) {
                ArrayList<Object> cuentas = (ArrayList<Object>) request.getSession().getAttribute("perfiles");
                if (cuentas != null) {
                    for (Object cuenta : cuentas) {
                        out.println(cuenta);
                    }
                }

                HttpSession sesion = request.getSession(true);
                out.print(sesion.getAttribute("perfil"));

            } else if (request.getParameter("proceso").equals("2")) {
                HttpSession sesion = request.getSession();
                String idCuenta = request.getParameter("valor");
                String perfil = request.getParameter("perfil");
                Object perfilObject = null;

                if (perfil.equals("usuario")) {
                    perfilObject = sessionBeanUsuario.findByPlaca(idCuenta);
                } else if (perfil.equals("cliente")) {
                    perfilObject = sessionBeanCliente.findByID(Integer.parseInt(idCuenta));
                } else if (perfil.equals("movilidad")) {
                    perfilObject = sessionBeanMovilidad.findByCedula(idCuenta);
                }
                out.println("GET: " + perfilObject);
                sesion.setAttribute("perfil", perfilObject);
            }

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
