package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controlador.RolJpaController;

public class Rol extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        RolJpaController RolJpa = new RolJpaController();
        int opc = Integer.parseInt(request.getParameter("opc"));
        int IdRol = 0, Estado = 0;
        String Nombre = "";
        boolean resultado = false;
        try {
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="MODULO ROL">
                    try {
                        IdRol = Integer.parseInt(request.getParameter("IdRol"));
                    } catch (NumberFormatException e) {
                        IdRol = 0;
                    }
                    request.setAttribute("IdRol", IdRol);
                    request.getRequestDispatcher("Rol.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR Y MODIFICAR ROL">
                    try {
                        IdRol = Integer.parseInt(request.getParameter("IdRol"));
                    } catch (NumberFormatException e) {
                        IdRol = 0;
                    }
                    Nombre = request.getParameter("Nombre");
                    if (IdRol > 0) {
                        resultado = RolJpa.ModificarRol(IdRol, Nombre);
                        request.setAttribute("RolModifica", resultado);
                    } else {
                        resultado = RolJpa.RegistrarRol(Nombre);
                        request.setAttribute("RolRegistro", resultado);
                    }
                    request.getRequestDispatcher("Rol?opc=1&IdRol=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="CAMBIAR ESTADO ROL">
                    try {
                        IdRol = Integer.parseInt(request.getParameter("IdRol"));
                    } catch (NumberFormatException e) {
                        IdRol = 0;
                    }
                    Estado = Integer.parseInt(request.getParameter("Estado"));
                    if (Estado == 1) {
                        Estado = 0;
                    } else {
                        Estado = 1;
                    }
                    resultado = RolJpa.ActualizarEstado(IdRol, Estado);
                    request.setAttribute("RolEstado", resultado);
                    request.getRequestDispatcher("Rol?opc=1&IdRol=0").forward(request, response);
                    //</editor-fold>
                    break;
            }

        } catch (Exception ex) {
            request.getRequestDispatcher("Rol.jsp").forward(request, response);
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
