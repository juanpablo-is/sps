package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.session.*;
import java.io.IOException;
import java.util.ArrayList;
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
public class InicioServlet extends HttpServlet {

    @EJB
    private ReservaFacadeLocal reservaSession;
    @EJB
    private ClienteFacadeLocal clienteSession;
    @EJB
    private ReporteFacadeLocal reporteSession;

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
                List<Cliente> clientes = clienteSession.findAll();
                ArrayList<String> parqueaderos = new ArrayList<>();

                clientes.forEach((cliente) -> {
                    parqueaderos.add("{latitud:" + cliente.getLatitud() + ",longitud:" + cliente.getLongitud() + ",direccion:'" + cliente.getDireccion() + "',nombre:'" + cliente.getNombre() + "',precio:" + cliente.getPrecio() + ",id:'" + cliente.getId() + "'}");
                });

                List<Reserva> reservas = reservaSession.findAllByUsuarioInicio((Usuario) perfilObject);

                request.setAttribute("parqueaderos", parqueaderos);
                request.setAttribute("reservas", reservas);
                /**
                 * } else if (perfilObject instanceof Cliente) {
                 * System.err.println("Cliente"); } else if (perfilObject
                 * instanceof Movilidad) { System.err.println("Movilidad");*
                 *
                 */
            } else if (perfilObject instanceof Cliente) {
                Cliente cliente = (Cliente) perfilObject;
                Object[] consulta = (Object[]) clienteSession.getDinero(cliente.getId());
                int cantidadReservas = clienteSession.getReservas(cliente.getId());
                List<Reporte> reportecliente = reporteSession.findByCliente((Cliente) perfilObject, 4);
                List<Reserva> reservas = reservaSession.findAllTime(cliente.getId());
                String grafico = clienteSession.graficaHistorial(cliente.getId());

                request.setAttribute("reportes", reportecliente);
                request.setAttribute("cantidadPersonas", consulta[0]);
                request.setAttribute("precio", consulta[1]);
                request.setAttribute("cantidadReservas", cantidadReservas);
                request.setAttribute("reservas", reservas);
                request.setAttribute("grafico", grafico);
            } else if (perfilObject instanceof Movilidad) {
                request.getRequestDispatcher("./reportes").forward(request, response);
            }

            request.getRequestDispatcher("inicio.jsp").forward(request, response);
            /**
             * request.setAttribute("grafica",
             * reservaSession.graficoReserva(perfil));*
             */
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
