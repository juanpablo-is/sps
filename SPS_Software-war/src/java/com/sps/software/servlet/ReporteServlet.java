package com.sps.software.servlet;

import com.sps.entity.*;
import com.sps.session.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
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
public class ReporteServlet extends HttpServlet {

    @EJB
    private ReporteFacadeLocal reporteSession;
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

        Object perfil = request.getSession().getAttribute("perfil");
        String id = request.getParameter("id");
        if (perfil != null) {
            if (perfil instanceof Usuario) {

                System.out.println("ID: " + id);
                if (id == null) {
                    String opcion = request.getParameter("opcion");
                    String idCliente = request.getParameter("idCliente");

                    if (opcion.equalsIgnoreCase("REPORTAR")) {
                        String textoArea = request.getParameter("reporteTexto");
                        String titulo = request.getParameter("titulo");
                        Cliente cliente = clienteSession.find(idCliente);

                        Reporte reporte = new Reporte(textoArea, titulo, cliente, (Usuario) perfil);
                        reporte.setEstado(false);
                        reporte.setFecha(new Date());
                        response.setContentType("text/html;charset=UTF-8");
                        try (PrintWriter out = response.getWriter()) {
                            out.print(reporteSession.create(reporte));
                        }
                    }
                } else {
                    request.setAttribute("individual", "");
                    request.getRequestDispatcher("reportes.jsp").forward(request, response);
                }

            } else if (perfil instanceof Cliente) {

            } else if (perfil instanceof Movilidad) {
                if (id == null) {
                    List<Reporte> reportes = reporteSession.findLast();

                    request.setAttribute("reportes", reportes);
                } else {
                    request.setAttribute("individual", reporteSession.find(Integer.parseInt(id)));
                }
                request.getRequestDispatcher("reportes.jsp").forward(request, response);
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
