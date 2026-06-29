package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Metodo.Control_Encriptacion;
import Controlador.UsuarioJpaController;
import java.util.List;

public class Sesion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession sesion = request.getSession();
            Control_Encriptacion md5 = new Control_Encriptacion();
            UsuarioJpaController UsuarioJpa = new UsuarioJpaController();
            List Lst_usuario = null;
            int opc = Integer.parseInt(request.getParameter("opc"));
            int IdUsuario = 0, Temp = 0, Documento = 0;
            String Contrasena = "", ContrasenaEncriptada = "";
            boolean accion = false;
            switch (opc) {
                case 1:
                    try {
                        Temp = Integer.parseInt(request.getParameter("Temp"));
                    } catch (Exception e) {
                        Temp = 0;
                    }
                    if (Temp == 1) {
                        IdUsuario = Integer.parseInt(request.getParameter("IdUsuario"));
                        request.setAttribute("IdUsuario", IdUsuario);
                        request.setAttribute("CambioContraseña", true);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        Documento = Integer.parseInt(request.getParameter("Txt_documento"));
                        Contrasena = request.getParameter("Txt_contrasena");
                        if (Contrasena.length() >= 8) {
                            ContrasenaEncriptada = md5.md5(Contrasena);
                            Lst_usuario = UsuarioJpa.ConsultaUsuarioSesion(Documento, ContrasenaEncriptada);
                            if (Lst_usuario == null) {
                                Lst_usuario = UsuarioJpa.ConsultaUsuarioSesion(Documento, Contrasena);
                            }
                        } else {
                            Lst_usuario = UsuarioJpa.ConsultaUsuarioSesion(Documento, Contrasena);
                        }
                    }
                    if (Lst_usuario == null) {
                        request.setAttribute("FalloSession", true);
                        request.getRequestDispatcher("index.jsp").forward(request, response);
                    } else {
                        Object[] obj_sesion = (Object[]) Lst_usuario.get(0);
                        if ((Integer) obj_sesion[6] == 0) {
                            boolean result = true;
                            request.setAttribute("UsuarioDesactivado", true);
                            request.setAttribute("var1", obj_sesion[1]);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else if (obj_sesion[8].toString().equals("Si")) {
                            request.setAttribute("idUsuario", obj_sesion[0]);
                            request.setAttribute("Cambio_contraseña", true);
                            request.getRequestDispatcher("index.jsp").forward(request, response);
                        } else {
                            sesion.setAttribute("idUsuario", obj_sesion[0]);
                            sesion.setAttribute("Nombre", obj_sesion[1]);
                            sesion.setAttribute("Apellido", obj_sesion[2]);
                            sesion.setAttribute("Documento", obj_sesion[3]);
                            sesion.setAttribute("Estado", obj_sesion[5]);
                            sesion.setAttribute("idRol", obj_sesion[6]);
                            sesion.setAttribute("NombreRol", obj_sesion[7]);
                            sesion.setAttribute("Rol/Nombres", obj_sesion[7] + "/" + obj_sesion[10]);
                            sesion.setAttribute("Nombres", obj_sesion[10]);
                            request.setAttribute("Bienvenido", true);
                            request.getRequestDispatcher("Inicio.jsp").forward(request, response);
                        }
                    }
                    break;
                case 2:
                    Documento = Integer.parseInt(request.getParameter("Txt_documento"));
                    Contrasena = request.getParameter("Txt_contrasena");
                    accion = UsuarioJpa.CambiarEstadoUsuario(Documento, Contrasena);
                    request.setAttribute("CambiarContraseña", accion);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    break;
            }

        } catch (Exception e) {
            request.getRequestDispatcher("Salir.jsp").forward(request, response);
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
