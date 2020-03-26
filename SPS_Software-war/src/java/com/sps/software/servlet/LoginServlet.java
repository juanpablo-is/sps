package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.sessionBean.ClienteFacadeLocal;
import com.sps.sessionBean.PersonaFacadeLocal;
import com.sps.sessionBean.UsuarioFacadeLocal;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
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
public class LoginServlet extends HttpServlet {

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

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Persona persona = sessioBeanPersona.findLogin(email, password);

        List<Usuario> usuarios = sessionBeanUsuario.findByCedula(persona);
        List<Cliente> clientes = sessionBeanCliente.findByCedula(persona);

        ArrayList<String[]> mapPerfiles = new ArrayList<>();

        int total = usuarios.size() + clientes.size();
        if (total > 1) {

            for (Usuario usuario : usuarios) {
                String dato[] = {"USUARIO", "PLACA", usuario.getPlaca(), usuario.getIdPropiedad()};
                mapPerfiles.add(dato);
            }

            for (Cliente cliente : clientes) {
                String dato[] = {"CLIENTE", "DIRECCIÓN", cliente.getDireccion(), String.valueOf(cliente.getId())};
                mapPerfiles.add(dato);
            }

            request.setAttribute("perfil", mapPerfiles);
            request.getRequestDispatcher("seleccion.jsp").forward(request, response);
        } else {
            request.setAttribute("persona", persona);
            request.setAttribute("usuario", usuarios.get(0));
            request.getRequestDispatcher("inicio.jsp").forward(request, response);
        }

        if (!usuarios.isEmpty()) {

        } else if (!clientes.isEmpty()) {

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


/*
PERSON{
    ID - CEDULA
    NAME
    PASSWORD
    EMAIL
    PHONE
}

USUARIO{
    PLACA
    MODELO
    MARCA
    ID-PROPIEDAD
}

CLIENT{
    ADDRESS
    CUPOS
    SCHEDULE START
    SCHEDULE END
}


ADMIN{
    ID-PROPIEDAD
    

}

LD_SPS_DB
ldspsdb
 */
