package com.sps.software.servlet;

import com.sps.session.*;
import com.sps.entity.*;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    @EJB
    private MovilidadFacadeLocal sessionBeanMovilidad;
    @EJB
    private PlazaFacadeLocal sessionBeanPlaza;

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
        Integer cedula = Integer.parseInt(request.getParameter("idPerson"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");

        Persona personaFind = sessioBeanPersona.find(cedula);
        Persona persona = new Persona(cedula, name, password, email);
        persona.setTelefono(phone);

        switch (radio) {
            case "Usuario":
                //Datos 'USUARIO'
                String placa = request.getParameter("placa").toUpperCase();
                String marca = request.getParameter("marca");
                String idPropiedad = request.getParameter("idPropiedad").toUpperCase();
                boolean tipoVehiculo = Boolean.parseBoolean(request.getParameter("tipoVehiculo").toUpperCase());

                Usuario usuarioFind = sessionBeanUsuario.find(placa);
                if (usuarioFind == null) {
                    if (personaFind == null) {
                        sessioBeanPersona.create(persona);
                    }

                    Usuario usuario = new Usuario(persona, idPropiedad, placa, marca, tipoVehiculo);
                    if (sessionBeanUsuario.create(usuario)) {
                        ingreso = true;
                    } else {
                        // EL USUARIO NO SE PUDO REGISTRAR
                        sessioBeanPersona.remove(persona);
                        request.setAttribute("error", "EL USUARIO NO PUDO SER REGISTRADO, INTENTE DE NUEVO");
                    }
                } else {
                    //EL USUARIO YA ESTA REGISTRADO
                    request.setAttribute("error", "EL USUARIO YA ESTA REGISTRADO");
                    request.setAttribute("persona", persona);
                }

                break;
            case "Cliente":
                //Datos 'CLIENTE'
                String idParqueadero = request.getParameter("idParqueadero");
                String horaEntrada = request.getParameter("horaEntrada");
                String horaCierre = request.getParameter("horaCierre");
                String precio = request.getParameter("precio");
                String carros = request.getParameter("cantidadCarros");
                String motos = request.getParameter("cantidadMotos");
                String direccion = request.getParameter("direccion");
                String nombre = request.getParameter("nombreParqueadero");

                Cliente clienteFind = sessionBeanCliente.find(idParqueadero);

                if (clienteFind == null) {
                    if (personaFind == null) {
                        sessioBeanPersona.create(persona);
                    }
                    Cliente cliente = new Cliente();
                    cliente.setId(idParqueadero);
                    cliente.setInicio(horaEntrada);
                    cliente.setFin(horaCierre);
                    cliente.setIdPersona(persona);
                    cliente.setNombre(nombre);
                    cliente.setDireccion(direccion);
                    cliente.setPrecio(Integer.parseInt(precio));
                    cliente.setCarros(Integer.parseInt(carros));
                    cliente.setMotos(Integer.parseInt(motos));
                    cliente.setLatitud(new BigDecimal("4.60" + (int) (Math.random() * 9999 + 1111)));
                    cliente.setLongitud(new BigDecimal("-74.07" + (int) (Math.random() * 9999 + 1111)));

                    if (sessionBeanCliente.create(cliente)) {
                        ingreso = true;
                        crearPlaza(cliente);
                    } else {
                        // EL USUARIO NO SE PUDO REGISTRAR
                        sessioBeanPersona.remove(persona);
                        request.setAttribute("error", "EL CLIENTE NO PUDO SER REGISTRADO, INTENTE DE NUEVO");
                    }
                } else {
                    //EL USUARIO YA ESTA REGISTRADO
                    request.setAttribute("error", "EL CLIENTE YA ESTA REGISTRADÓ");
                    request.setAttribute("persona", persona);
                }
                break;
            case "Movilidad":
                String idMovilidad = request.getParameter("id");
                String sede = request.getParameter("empresa");

                Movilidad movilidad = sessionBeanMovilidad.find(idMovilidad);

                if (movilidad == null) {
                    if (personaFind == null) {
                        sessioBeanPersona.create(persona);
                    }

                    movilidad = new Movilidad(idMovilidad, sede, persona);
                    if (sessionBeanMovilidad.create(movilidad)) {
                        ingreso = true;
                    } else {
                        sessioBeanPersona.remove(persona);
                        request.setAttribute("error", "EL CLIENTE NO PUDO SER REGISTRADO, INTENTE DE NUEVO");
                    }
                } else {
                    request.setAttribute("error", "EL USUARIO DE MOVILIDAD YA ESTA REGISTRADÓ");
                    request.setAttribute("persona", persona);
                }
                break;
        }

        if (ingreso) {
            response.sendRedirect("index.jsp");
        } else {
            request.getRequestDispatcher("registro.jsp").forward(request, response);
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

    private void crearPlaza(Cliente cliente) {
        String id = "";
        boolean cubierto = true;
        Plaza plaza = null;
        boolean tipo = true;
        for (int i = 0; i < cliente.getCarros() + cliente.getMotos(); i++) {
            if (i < cliente.getCarros()) {
                id = "C" + (i + 1) + "-" + cliente.getId();
                tipo = true;
            } else {
                id = "M" + (i + 1 - cliente.getCarros()) + "-" + cliente.getId();
                tipo = false;
            }
            cubierto = (int) Math.round(Math.random()) == 1;
            plaza = new Plaza(id, tipo, cubierto, cliente);
            sessionBeanPlaza.create(plaza);
        }
    }

}
