package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controlador.UsuarioJpaController;

public class Usuario extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        UsuarioJpaController JpaUsuario = new UsuarioJpaController();
        try {
            int opc = Integer.parseInt(request.getParameter("opc"));
            int IdUsuario = 0, Documento = 0, Estado = 0, IdRol = 0;
            String Nombre = "", Apellido = "", Correo = "", Contraseña = "";
            boolean resultado = false;
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="MODULO USUARIO">
                    try {
                        IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    } catch (NumberFormatException e) {
                        IdUsuario = 0;
                    }
                    request.setAttribute("IdUsuario", IdUsuario);
                    request.getRequestDispatcher("Usuario.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR/MODIFICAR USUARIO">
                    try {
                        IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    } catch (NumberFormatException e) {
                        IdUsuario = 0;
                    }
                    Nombre = request.getParameter("Nombre");
                    Apellido = request.getParameter("Apellido");
                    Documento = Integer.parseInt(request.getParameter("Documento"));
                    Correo = request.getParameter("Correo");
                    IdRol = Integer.parseInt(request.getParameter("IdRol"));
                    if (IdUsuario > 0) {
                        Estado = Integer.parseInt(request.getParameter("Estado"));
                        resultado = JpaUsuario.ModificarUsuario(IdUsuario, Nombre, Apellido, Documento, Correo, IdRol, Estado);
                        request.setAttribute("UsuarioModifica", resultado);
                    } else {
                        resultado = JpaUsuario.RegistrarUsuario(Nombre, Apellido, Documento, Correo, IdRol);
                        request.setAttribute("UsuarioRegistro", resultado);
                    }
                    request.getRequestDispatcher("Usuario?opc=1&IdUsuario=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="CAMBIAR ESTADO USUARIO">
                    try {
                        IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    } catch (NumberFormatException e) {
                        IdUsuario = 0;
                    }
                    Estado = Integer.parseInt(request.getParameter("Estado"));
                    if (Estado == 1) {
                        Estado = 0;
                    } else {
                        Estado = 1;
                    }
                    resultado = JpaUsuario.CambiarEstadoUsuario(IdUsuario, Estado);
                    request.setAttribute("CambiarEstado", resultado);
                    request.getRequestDispatcher("Usuario?opc=1&IdUsuario=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 4:
                    //<editor-fold defaultstate="collapsed" desc="RESTABLECER CONTRASEÑA">
                    try {
                        IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                    } catch (NumberFormatException e) {
                        IdUsuario = 0;
                    }
                    resultado = JpaUsuario.RestablecerContrasena(IdUsuario);
                    request.setAttribute("RestablecerCont", resultado);
                    request.getRequestDispatcher("Usuario?opc=1&IdUsuario=0").forward(request, response);
                    //</editor-fold>
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Usuario.jsp").forward(request, response);
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
