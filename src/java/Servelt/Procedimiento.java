package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controlador.ProcedimientoJpaController;

public class Procedimiento extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        ProcedimientoJpaController ProcedimientoJpa = new ProcedimientoJpaController();
        int opc = Integer.parseInt(request.getParameter("opc"));
        int IdProcedimiento = 0, Estado = 0;
        String Nombre = "";
        boolean resultado = false;
        try {
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="MODULO PROCEDIMIENTO">
                    try {
                        IdProcedimiento = Integer.parseInt(request.getParameter("IdProcedimiento"));
                    } catch (NumberFormatException e) {
                        IdProcedimiento = 0;
                    }
                    request.setAttribute("IdProcedimiento", IdProcedimiento);
                    request.getRequestDispatcher("Procedimiento.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR Y MODIFICAR PROCEDIMIENTO">
                    try {
                        IdProcedimiento = Integer.parseInt(request.getParameter("IdProcedimiento"));
                    } catch (NumberFormatException e) {
                        IdProcedimiento = 0;
                    }
                    Nombre = request.getParameter("Nombre");
                    if (IdProcedimiento > 0) {
                        resultado = ProcedimientoJpa.ModificarProcedimiento(IdProcedimiento, Nombre);
                        request.setAttribute("ProcedimientoModificar", resultado);
                    } else {
                        resultado = ProcedimientoJpa.RegistrarProcedimiento(Nombre);
                        request.setAttribute("ProcedimientoRegistro", resultado);
                    }
                    request.getRequestDispatcher("Procedimiento?opc=1&IdProcedimiento=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="ACTUALIZAR ESTADO">
                    try {
                        IdProcedimiento = Integer.parseInt(request.getParameter("IdProcedimiento"));
                    } catch (NumberFormatException e) {
                        IdProcedimiento = 0;
                    }
                    Estado = Integer.parseInt(request.getParameter("Estado"));
                    if (Estado == 1) {
                        Estado = 0;
                    } else {
                        Estado = 1;
                    }
                    resultado = ProcedimientoJpa.ModificarProcedimientoEstado(IdProcedimiento, Estado);
                    request.setAttribute("ProcedimientoEstado", resultado);
                    request.getRequestDispatcher("Procedimiento?opc=1&IdProcedimiento=0").forward(request, response);
                    //</editor-fold>
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Procedimiento.jsp").forward(request, response);
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
