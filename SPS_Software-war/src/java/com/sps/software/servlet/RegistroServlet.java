package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.sessionBean.*;
import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Pablo
 */
public class RegistroServlet extends HttpServlet {

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

        String radio = request.getParameter("radio");
        boolean ingreso = false;

        //Datos 'PERSONA'
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String cedula = request.getParameter("idPerson");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Persona personaFind = sessioBeanPersona.find(cedula);
        Persona persona = new Persona(cedula, name, password, email, phone);

        if (personaFind == null) {
            sessioBeanPersona.create(persona);
        }

        switch (radio) {
            case "Usuario":
                //Datos 'USUARIO'
                String placa = request.getParameter("placa");
                String marca = request.getParameter("marca");
                String idPropiedad = request.getParameter("idPropiedad");

                Usuario usuarioFind = sessionBeanUsuario.find(idPropiedad);
                if (usuarioFind == null) {
                    Usuario usuario = new Usuario(idPropiedad, placa, marca);
                    usuario.setIdPersona(persona);
                    sessionBeanUsuario.create(usuario);
                    ingreso = true;
                }

                break;
            case "Cliente":
                //Datos 'CLIENTE'
                String direccion = request.getParameter("direccion");
                String cupos = request.getParameter("cupos");
                String horaEntrada = request.getParameter("horaEntrada");
                String horaCierre = request.getParameter("horaCierre");

//                Cliente clienteFind = sessionBeanCliente.find(idPropiedad);
                Cliente clienteFind = null;
                if (clienteFind == null) {
                    Cliente cliente = new Cliente(direccion, Integer.parseInt(cupos));

                    cliente.setInicio(horaEntrada);
                    cliente.setFin(horaCierre);
                    cliente.setIdPersona(persona);

                    sessionBeanCliente.create(cliente);
                    ingreso = true;
                }
                break;
            case "Administracion":
                break;
        }

        request.getRequestDispatcher(ingreso ? "index.jsp" : "registro.jsp").forward(request, response);
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
