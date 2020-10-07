package com.sps.software.servlet;

import com.sps.session.*;
import com.sps.entity.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Juan Pablo
 */
public class ReservaServlet extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaSession;
    @EJB
    private ClienteFacadeLocal clienteSession;
    @EJB
    private UsuarioFacadeLocal usuarioSession;
    @EJB
    private PlazaFacadeLocal plazaSession;
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
                String proceso = request.getParameter("proceso");

                if (proceso == null) {
                    Reserva reservaPersona = reservaSession.findLastCheck(perfil);

                    if (reservaPersona != null) {
                        Calendar timeActual = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));

                        if (reservaPersona.getFecha().after(timeActual.getTime())) {
                            request.setAttribute("cancelar", "si");
                        } else if (reservaPersona.getFecha().before(timeActual.getTime())) {
                            double precioMin = reservaPersona.getIdPlaza().getIdCliente().getPrecio();
                            long diff = timeActual.getTime().getTime() - reservaPersona.getFecha().getTime();
                            int diffmin = (int) (diff / (60 * 1000));
                            double p = (double) (diff / (60 * 1000));
                            request.setAttribute("precio", diffmin * precioMin);
                        }
                    }

                    request.setAttribute("reservaCheck", reservaPersona);
                    request.getRequestDispatcher("reservar.jsp").forward(request, response);
                } else {
                    switch (proceso) {
                        case "cubierto":
                            boolean cubierto = request.getParameter("cubierto").equals("si");
                            List<Object[]> plazas = plazaSession.findNoExistsGroup(perfil.getTipoVehiculo(), cubierto);

                            try (PrintWriter out = response.getWriter()) {
                                for (int i = 0; i < plazas.size(); i++) {
                                    Object cliente = plazas.get(i);
                                    out.println(clienteSession.find(cliente));
                                }
                            }
                            break;
                        case "RESERVAR":
                            boolean cubiertoReserva = request.getParameter("cubierto").equals("si");
                            boolean tipoVehiculo = perfil.getTipoVehiculo();

                            String clienteID = request.getParameter("idCliente");
                            Cliente cliente = clienteSession.find(clienteID);

                            Plaza plaza = plazaSession.findPlazaNoExists(tipoVehiculo, cubiertoReserva, cliente);
                            String fecha = request.getParameter("fecha");
                            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                            formatter.setTimeZone(TimeZone.getTimeZone("America/Bogota"));

                            Date date = new Date();
                            try {
                                date = (Date) formatter.parse(fecha);
                            } catch (ParseException ex) {
                                Logger.getLogger(ReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }

                            Reserva reserva = new Reserva(date, true, plaza, perfil);
                            reservaSession.create(reserva);

//                    Reserva reservaRegistrar = new Reserva(dia, entrada, "", 0, null, perfil);
//
//                    reservaSession.create(reservaRegistrar);
                            /**
                             * if (reservaSession.create(reservaRegistrar)) {
                             * System.err.println("CREADO RESERVA"); } else {
                             * System.err.println("RESERVA NO CREADA"); }
                             *
                             */
                            request.getRequestDispatcher("inicio").forward(request, response);
                            break;
                        case "cancelar":
                            Reserva reservaCancelar = reservaSession.findLastCheck(perfil);

                            try (PrintWriter out = response.getWriter()) {
                                out.print(reservaSession.remove(reservaCancelar));
                            }
                            break;
                        case "liquidar":
                            Reserva reservaLiquidar = reservaSession.findLastCheck(perfil);
                            Calendar timeActualLiquidar = Calendar.getInstance(TimeZone.getTimeZone("GMT-5:00"));

                            double precioMin = reservaLiquidar.getIdPlaza().getIdCliente().getPrecio();
                            long diff = timeActualLiquidar.getTime().getTime() - reservaLiquidar.getFecha().getTime();
                            double diffmin = (double) (diff / (60 * 1000));
                            Historial historial = new Historial(new Date(), (double) diffmin * precioMin, reservaLiquidar);

                            boolean creado = historialSession.create(historial);
                            if (creado) {
                                reservaLiquidar.setEstado(false);
                                reservaSession.edit(reservaLiquidar);
                            }
                            try (PrintWriter out = response.getWriter()) {
                                out.print(creado);
                            }
                            break;
                    }
                }
            } else if (perfilObject instanceof Cliente) {
                String placa = request.getParameter("placa").toUpperCase();
                String idCliente = request.getParameter("cliente");
                boolean cubierto = request.getParameter("cubierto").equals("si");
                Cliente cliente = clienteSession.find(idCliente);
                Usuario perfil = usuarioSession.findByPlaca(placa);

                if (perfil != null) {
                    String fecha = request.getParameter("fecha");
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm");
                    Date date = new Date();
                    try {
                        date = (Date) formatter.parse(fecha);
                    } catch (ParseException ex) {
                        Logger.getLogger(ReservaServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    Plaza plaza = plazaSession.findPlazaNoExists(perfil.getTipoVehiculo(), cubierto, cliente);
                    Reserva reserva = new Reserva(date, true, plaza, perfil);
                    reservaSession.create(reserva);
                }

//                Reserva reservaRegistrar = new Reserva(dia, hora, "", 0, null, perfil);
//
//                reservaSession.create(reservaRegistrar);
                /**
                 * if (reservaSession.create(reservaRegistrar)) {
                 * System.err.println("CREADO RESERVA"); } else {
                 * System.err.println("RESERVA NO CREADA"); }*
                 *
                 */
                request.getRequestDispatcher("reservar.jsp").forward(request, response);
            }
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
