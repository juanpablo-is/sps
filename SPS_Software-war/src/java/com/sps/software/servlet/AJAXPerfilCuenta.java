package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.session.*;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "AJAXPerfilCuenta", urlPatterns = {"/AJAXPerfilCuenta"})
public class AJAXPerfilCuenta extends HttpServlet {

    @EJB
    private PersonaFacadeLocal personaSession;
    @EJB
    private ClienteFacadeLocal clienteSession;
    @EJB
    private UsuarioFacadeLocal usuarioSession;
    @EJB
    private MovilidadFacadeLocal movilidadSession;

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

        String info = request.getParameter("info");
        Persona persona = (Persona) request.getSession().getAttribute("persona");

        switch (info) {
            case "personal":
                String nombre = request.getParameter("nombre");
                String telefono = request.getParameter("telefono");

                persona.setNombre(nombre);
                persona.setTelefono(telefono);

                try (PrintWriter out = response.getWriter()) {
                    out.println(personaSession.edit(persona));
                }

                break;
            case "acceso":
                String correo = request.getParameter("correo");
                String contrasenia = request.getParameter("contrasenia");

                persona.setCorreo(correo);
                persona.setContrasenia(contrasenia);

                try (PrintWriter out = response.getWriter()) {
                    out.println(personaSession.edit(persona));
                }
                break;
            case "perfil":
                Object perfil = request.getSession().getAttribute("perfil");
                String tipoPerfil = request.getParameter("tipo");

                switch (tipoPerfil) {
                    case "cliente":
                        double precio = Double.parseDouble(request.getParameter("precio"));
                        String nombreCliente = request.getParameter("nombreCliente");
                        String direccion = request.getParameter("direccion");
                        String entrada = request.getParameter("entrada");
                        String salida = request.getParameter("salida");

                        Cliente cliente = (Cliente) perfil;
                        cliente.setPrecio(precio);
                        cliente.setNombre(nombreCliente);
                        cliente.setDireccion(direccion);
                        cliente.setInicio(entrada);
                        cliente.setFin(salida);

                        try (PrintWriter out = response.getWriter()) {
                            out.println(clienteSession.edit(cliente));
                        }
                        break;
                    case "usuario":
                        String marca = request.getParameter("marca");

                        Usuario usuario = (Usuario) perfil;
                        usuario.setMarca(marca);

                        try (PrintWriter out = response.getWriter()) {
                            out.println(usuarioSession.edit(usuario));
                        }
                        break;
                    case "movilidad":
                        String sede = request.getParameter("sede");

                        Movilidad movilidad = (Movilidad) perfil;
                        movilidad.setEmpresa(sede);

                        try (PrintWriter out = response.getWriter()) {
                            out.println(movilidadSession.edit(movilidad));
                        }
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
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
