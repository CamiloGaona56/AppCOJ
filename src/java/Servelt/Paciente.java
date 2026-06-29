package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Controlador.PacienteJpaController;

public class Paciente extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            PacienteJpaController PacienteJpa = new PacienteJpaController();
            int opc = Integer.parseInt(request.getParameter("opc"));
            int IdPaciente = 0, Estado = 0, Documento = 0, EstadoPaciente = 0;
            String Nombre = "", Tipo_documento = "", Recomendacion = "", Fecha = "", Celular = "";
            boolean Resultado = false;
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="MODULO PACIENTE">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (NumberFormatException e) {
                        IdPaciente = 0;
                    }
                    try {
                        Estado = Integer.parseInt(request.getParameter("Estado"));
                    } catch (NumberFormatException e) {
                        Estado = 0;
                    }
                    request.setAttribute("Estado", Estado);
                    request.setAttribute("IdPaciente", IdPaciente);
                    request.getRequestDispatcher("Paciente.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR Y MODIFICAR PACIENTE">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (NumberFormatException e) {
                        IdPaciente = 0;
                    }
                    Nombre = request.getParameter("Nombre");
                    Tipo_documento = request.getParameter("Tipo_documento");
                    Documento = Integer.parseInt(request.getParameter("Documento"));
                    Celular = request.getParameter("Celular");
                    Recomendacion = request.getParameter("Recomendacion");
                    Fecha = request.getParameter("Fecha");
                    if (IdPaciente > 0) {
                        Resultado = PacienteJpa.ModificarPaciente(IdPaciente, Nombre, Tipo_documento, Documento, Celular, Recomendacion, Fecha);
                        request.setAttribute("PacienteModificar", Resultado);
                    } else {
                        Resultado = PacienteJpa.RegistrarPaciente(Nombre, Tipo_documento, Documento, Celular, Recomendacion, Fecha);
                        request.setAttribute("PacienteRegistro", Resultado);
                    }
                    request.getRequestDispatcher("Paciente?opc=1&IdPaciente=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="CAMBIAR ESTADO PACIENTE">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (NumberFormatException e) {
                        IdPaciente = 0;
                    }
                    EstadoPaciente = Integer.parseInt(request.getParameter("EstadoPaciente"));
                    if (EstadoPaciente == 1) {
                        EstadoPaciente = 0;
                    } else {
                        EstadoPaciente = 1;
                    }
                    Resultado = PacienteJpa.ModificarEstadoPaciente(IdPaciente, EstadoPaciente);
                    request.setAttribute("PacienteModificarEstado", Resultado);
                    request.getRequestDispatcher("Paciente?opc=1&IdPaciente=0").forward(request, response);
                    //</editor-fold>
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Paciente.jsp").forward(request, response);
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
