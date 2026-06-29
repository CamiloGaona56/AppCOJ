package Servelt;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import Controlador.ProyeccionJpaController;
import Controlador.AbonoJpaController;

public class Proyeccion extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        try {
            HttpSession sesion = request.getSession();
            int IdUsuario = Integer.parseInt(sesion.getAttribute("idUsuario").toString());
            ProyeccionJpaController ProyeccionJpa = new ProyeccionJpaController();
            AbonoJpaController AbonoJpa = new AbonoJpaController();
            int opc = Integer.parseInt(request.getParameter("opc"));
            int IdPaciente = 0, IdProyeccion = 0, ContadorSeccion = 0, Diente = 0, Procedimiento = 0, Estado = 0, IdAbono = 0;
            int Count = 1;
            int Presupuesto = 0, PresupuestoOld = 0, Abono = 0, Saldo = 0;
            String Observacion = "", Fecha = "", Medio = "";
            boolean Resultado = false;
            switch (opc) {
                case 1:
                    //<editor-fold defaultstate="collapsed" desc="MODULO PROYECCION">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (NumberFormatException e) {
                        IdPaciente = 0;
                    }
                    try {
                        IdProyeccion = Integer.parseInt(request.getParameter("IdProyeccion"));
                    } catch (NumberFormatException e) {
                        IdProyeccion = 0;
                    }
                    request.setAttribute("IdPaciente", IdPaciente);
                    request.setAttribute("IdProyeccion", IdProyeccion);
                    request.getRequestDispatcher("Proyeccion.jsp").forward(request, response);
                    //</editor-fold>
                    break;
                case 2:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR PROYECCION">
                    IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    ContadorSeccion = Integer.parseInt(request.getParameter("ConteoSeccionVal"));
                    for (int i = 0; i < ContadorSeccion; i++) {
                        Diente = Integer.parseInt(request.getParameter("Cbx_Diente" + Count + ""));
                        Procedimiento = Integer.parseInt(request.getParameter("Cbx_Procedimiento" + Count + ""));
                        String presupuestoStr = request.getParameter("TxtPresupuesto" + Count).replaceAll("[^\\d]", ""); // Solo deja números
                        Observacion = request.getParameter("TxtObservacion" + Count + "");

                        // Convertir el valor limpio a un entero
                        Presupuesto = Integer.parseInt(presupuestoStr);
                        Resultado = ProyeccionJpa.RegistrarProyeccion(IdPaciente, IdUsuario, Diente, Procedimiento, Presupuesto, Observacion);
                        if (i != ContadorSeccion) {
                            Count++;
                        }
                    }
                    request.setAttribute("ProyeccionRegistrar", Resultado);
                    request.getRequestDispatcher("Proyeccion?opc=1&IdPaciente=" + IdPaciente + "").forward(request, response);
                    //</editor-fold>
                    break;
                case 3:
                    //<editor-fold defaultstate="collapsed" desc="MODIFICAR PROYECCION ID">
                    IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    IdProyeccion = Integer.parseInt(request.getParameter("IdProyeccion"));
                    Diente = Integer.parseInt(request.getParameter("Cbx_Diente"));
                    Procedimiento = Integer.parseInt(request.getParameter("Cbx_Procedimiento"));
                    String presupuestoStr = request.getParameter("TxtPresupuesto").replaceAll("[^\\d]", ""); // Solo deja números
                    Presupuesto = Integer.parseInt(presupuestoStr);
                    String presupuestoStrOld = request.getParameter("TxtPresupuestoOld").replaceAll("[^\\d]", ""); // Solo deja números
                    PresupuestoOld = Integer.parseInt(presupuestoStrOld);
                    Observacion = request.getParameter("TxtObservacion");
                    if (PresupuestoOld != Presupuesto) {
                        Resultado = ProyeccionJpa.ModificarProyeccionId(IdProyeccion, Diente, Procedimiento, Presupuesto, Observacion);
                        ProyeccionJpa.ModificarProyeccionIdPresupuestoInicial(IdProyeccion, PresupuestoOld);
                    } else {
                        Resultado = ProyeccionJpa.ModificarProyeccionId(IdProyeccion, Diente, Procedimiento, Presupuesto, Observacion);
                    }
                    request.setAttribute("ProyeccionModificar", Resultado);
                    request.getRequestDispatcher("Proyeccion?opc=1&IdPaciente=" + IdPaciente + "&IdProyeccion=0").forward(request, response);
                    //</editor-fold>
                    break;
                case 4:
                    //<editor-fold defaultstate="collapsed" desc="CAMBIO DE ESTADO PROYECCION ID (REVISION O DESHABILITAR)">
                    try {
                        IdProyeccion = Integer.parseInt(request.getParameter("IdProyeccion"));
                    } catch (NumberFormatException e) {
                        IdProyeccion = 0;
                    }
                    Estado = Integer.parseInt(request.getParameter("Estado"));
                    if (Estado == 2) {
                        //<editor-fold defaultstate="collapsed" desc="REVISION SIN RECARGAR">
                        ProyeccionJpa.ModificarEstadoProyeccionRevisadoDesabilidato(IdProyeccion, Estado);
                        //</editor-fold>
                    } else {
                        //<editor-fold defaultstate="collapsed" desc="DESHABILITAR CON RECARGA">

                        try {
                            IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                        } catch (NumberFormatException e) {
                            IdPaciente = 0;
                        }
                        Resultado = ProyeccionJpa.ModificarEstadoProyeccionRevisadoDesabilidato(IdProyeccion, Estado);
                        request.setAttribute("ProyeccionDeshabilitar", Resultado);
                        request.getRequestDispatcher("Proyeccion?opc=1&IdPaciente=" + IdPaciente + "&IdProyeccion=0").forward(request, response);
                        //</editor-fold>
                    }
                    //</editor-fold>
                    break;
                case 5:
                    //<editor-fold defaultstate="collapsed" desc="REGISTRAR ABONO">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (Exception e) {
                        IdPaciente = 0;
                    }
                    try {
                        IdAbono = Integer.parseInt(request.getParameter("IdAbono"));
                    } catch (Exception e) {
                        IdAbono = 0;
                    }
                    Fecha = request.getParameter("Fecha");
                    String AbonoInput = request.getParameter("Abono").replaceAll("[^\\d]", ""); // Solo deja números
                    Abono = Integer.parseInt(AbonoInput);
                    String SaldoInput = request.getParameter("Saldo").replaceAll("[^\\d]", ""); // Solo deja números
                    Saldo = Integer.parseInt(SaldoInput);
                    Medio = request.getParameter("Medio");
                    Observacion = request.getParameter("Observacion");
                    if (IdAbono > 0) {
                        Resultado = AbonoJpa.ModificarAbono(IdAbono, Fecha, Abono, Saldo, Medio, Observacion);
                        request.setAttribute("AbonoModificar", Resultado);
                    } else {
                        Resultado = AbonoJpa.RegistrarAbono(IdPaciente, Fecha, Abono, Saldo, Medio, Observacion);
                        request.setAttribute("AbonoRegistrar", Resultado);
                    }
                    request.getRequestDispatcher("Proyeccion?opc=1&IdPaciente=" + IdPaciente + "&IdProyeccion=0&IdAbono=0&&Temp=1").forward(request, response);
                    //</editor-fold>
                    break;
                case 6:
                    //<editor-fold defaultstate="collapsed" desc="CAMBIAR DE ESTADO ABONO (DESHABILITAR)">
                    try {
                        IdPaciente = Integer.parseInt(request.getParameter("IdPaciente"));
                    } catch (Exception e) {
                        IdPaciente = 0;
                    }
                    try {
                        IdAbono = Integer.parseInt(request.getParameter("IdAbono"));
                    } catch (Exception e) {
                        IdAbono = 0;
                    }
                    Estado = Integer.parseInt(request.getParameter("Estado"));
                    Resultado = AbonoJpa.ModificarEstadoAbono(IdAbono, Estado);
                    request.setAttribute("AbonoEstado", Resultado);
                    request.getRequestDispatcher("Proyeccion?opc=1&IdPaciente=" + IdPaciente + "&IdProyeccion=0&IdAbono=0&Temp=1").forward(request, response);
                    //</editor-fold>
                    break;
            }
        } catch (Exception ex) {
            request.getRequestDispatcher("Proyeccion.jsp").forward(request, response);
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
