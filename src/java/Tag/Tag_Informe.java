package Tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controlador.PacienteJpaController;
import Controlador.ProyeccionJpaController;
import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class Tag_Informe extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = pageContext.getOut();
        PacienteJpaController PacienteJpa = new PacienteJpaController();
        ProyeccionJpaController ProyeccionJpa = new ProyeccionJpaController();
        List Lst_Paciente = null, Lst_PacienteT = null, Lst_Proyeccion = null;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        int Documento = 0, Validacion = 0;
        try {
            try {
                Documento = Integer.parseInt(pageContext.getRequest().getParameter("Documento"));
            } catch (NumberFormatException e) {
                Documento = 0;
            }
            try {
                Validacion = Integer.parseInt(pageContext.getRequest().getParameter("Validacion"));
            } catch (NumberFormatException e) {
                Validacion = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="FILTRO">

            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_Filtro'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h6>Filtro Busqueda</h6>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<form action='Informe?opc=1' method='post' class='needs-validation' novalidate=''>");
            out.print("<div data-toggle='tooltip' data-placement='right' title='Paciente'>");
            out.print("<select class='select2 form-control'  style='width:auto !important;' name='Documento' required>");
            out.print("<option selected disabled value=''>Seleccionar Paciente</option>");
            Lst_PacienteT = PacienteJpa.ConsultarPaciente();
            if (Lst_PacienteT != null) {
                for (int i = 0; i < Lst_PacienteT.size(); i++) {
                    Object[] Obj_Pac = (Object[]) Lst_PacienteT.get(i);
                    out.print("<option value='" + Obj_Pac[2] + "'>" + Obj_Pac[2] + " - " + Obj_Pac[3] + "</option>");
                }
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='text-center mt-4' style='width: 100%;'>");
            out.print("<button class='btn btn-green btn-lg'>Consultar</button>");
            out.print("</div>");
            out.print("</form>");

            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            out.print("<section class='section'>");
            Lst_Paciente = PacienteJpa.ConsultarPacienteDocumento(Documento);
            if (Lst_Paciente != null) {
                if (Validacion == 0) {
                    out.print("<div class='BottomFiltro'><button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)'><i class='fas fa-search'></i></button></div>");
                } else {
                    out.print("<div class='BottomFiltro'><a href='index.jsp'><button class='btn btn-green' style='border-radius: 4px;'><i class='fas fa-home'></i>&nbsp;Inicio</button></a></div>");
                }
                Object[] Obj_Paciente = (Object[]) Lst_Paciente.get(0);
                out.print("<div class='d-flex'>");
                out.print("<div class='card mr-1 StyleBoxIzq mb-3'>");
                int EstadoPaciente = Integer.parseInt(Obj_Paciente[7].toString());
                out.print("<div class='text-center mt-3'><h4>" + Obj_Paciente[3] + "</h4></div>");
                out.print("<div class='text-center'><img alt='image' src='Contenido/Imagen/DienteAnimado.png' class='rounded-circle' width='67%'></div>");
                out.print("<div class='text-center'><h6 class='FontSizeLeft'>" + Obj_Paciente[1] + " - " + Obj_Paciente[2] + "</h6><div class='BorderBottonInforme'></div></div>");
                out.print("<div class='text-center'><h6 class='FontSizeLeft'>" + Obj_Paciente[4] + "</h6><div class='BorderBottonInforme'></div></div>");
                out.print("<div class='text-center'><h6 class='FontSizeLeft'>" + Obj_Paciente[5] + "</h6><div class='BorderBottonInforme'></div></div>");
                out.print("<div class='text-center mb-5'><h6 class='FontSizeLeft'>" + Obj_Paciente[6] + "</h6></div>");
                out.print("<div class='text-center FooterInforme'>" + ((EstadoPaciente == 1) ? "<div class='badge badge-success' style='border-radius:2px !important; width:74%'>Activo</div>" : "<div class='badge badge-danger' style='border-radius:2px !important; width:74%'>Inactivo</div>") + "</div>");

                out.print("</div>");
                out.print("<div class='card StyleBoxRight mb-3'>");
                out.print("<div class='m-5'>");
                out.print("<div><h6>Tratamiento</h6></div>");
                Lst_Proyeccion = ProyeccionJpa.ConsultarProcedimiento(Integer.parseInt(Obj_Paciente[0].toString()));
                if (Validacion == 0) {
                    //<editor-fold defaultstate="collapsed" desc="USUARIO">
                    if (Lst_Proyeccion != null) {
                        out.print("<table class='table table-bordered table-hover' id='table-1'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<th style='width:5%'>Diente</th>");
                        out.print("<th>Tratamiento</th>");
                        out.print("<th>Presupuesto Inicial</th>");
                        out.print("<th>Presupuesto Actual </th>");
                        out.print("<th>Observación</th>");
                        out.print("</tr>");
                        out.print("</thead>");
                        out.print("<tbody>");
                        for (int i = 0; i < Lst_Proyeccion.size(); i++) {
                            Object[] Obj_proyeccion = (Object[]) Lst_Proyeccion.get(i);
                            out.print("<tr>");
                            if (Integer.parseInt(Obj_proyeccion[4].toString()) == 0) {
                                out.print("<td>&nbsp;</td>");
                            } else {
                                out.print("<td><div class='text-center '>"
                                        + "<div><img src='Contenido/Imagen/diente.png' style='width:28px'></div>"
                                        + "<div class='text-center'><b style='font-size: 12px;'>#" + Obj_proyeccion[4] + "</b></div>"
                                        + "</div></td>");
                            }
                            out.print("<td>" + Obj_proyeccion[6] + "</td>");
                            if (Obj_proyeccion[7] != null) {
                                double valorOld = Double.parseDouble(Obj_proyeccion[7].toString());
                                String valorFormateadoOld = formatter.format(valorOld);
                                out.print("<td>" + valorFormateadoOld + "</td>");
                            } else {
                                out.print("<td>&nbsp;</td>");
                            }

                            double valor = Double.parseDouble(Obj_proyeccion[8].toString());
                            String valorFormateado = formatter.format(valor);
                            out.print("<td><b>" + valorFormateado + "</b></td>");
                            out.print("<td><b>" + ((Obj_proyeccion[11] == null) ? "" : Obj_proyeccion[11]) + "</b></td>");

                            out.print("</tr>");
                        }
                        out.print("</tbody>");
                        out.print("</table>");
                    } else {
                        out.print("<div class='m-5 text-center'>"
                                + "<h4>Sin tratamientos registrados</h4></div>");
                    }
                    //</editor-fold>
                } else {
                    //<editor-fold defaultstate="collapsed" desc="DOCTOR">
                    if (Lst_Proyeccion != null) {
                        out.print("<table class='table table-bordered table-hover' id='table-2'>");
                        out.print("<thead>");
                        out.print("<tr>");
                        out.print("<th style='width:5%'></th>");
                        out.print("<th style='width:5%'>Diente</th>");
                        out.print("<th>Procedimiento</th>");
                        out.print("<th>Observación</th>");
                        out.print("</thead>");
                        out.print("<tbody>");
                        for (int i = 0; i < Lst_Proyeccion.size(); i++) {
                            Object[] Obj_proyeccion = (Object[]) Lst_Proyeccion.get(i);
                            out.print("<tr>");
                            if (Integer.parseInt(Obj_proyeccion[4].toString()) == 0) {
                                out.print("<td>&nbsp;</td>");
                                out.print("<td>&nbsp;</td>");
                            } else {
                                out.print("<td><div><img src='Contenido/Imagen/diente.png' style='width:28px'></div></td>"
                                        + "<td><div class='text-center'><b style='font-size: 12px;'>" + Obj_proyeccion[4] + "</b></div></td>");
                            }
                            out.print("<td>" + Obj_proyeccion[6] + "</td>");
                             out.print("<td><b>" + ((Obj_proyeccion[11] == null) ? "" : Obj_proyeccion[11]) + "</b></td>");
                            out.print("</tr>");
                        }
                        out.print("</tbody>");
                        out.print("</table>");
                    } else {
                        out.print("<div class='m-5 text-center'>"
                                + "<h4>Sin tratamientos registrados</h4></div>");
                    }
                    //</editor-fold>
                }
                out.print("</div>");
                out.print("</div>");
                out.print("</div>");
            } else {
                if (Validacion == 1) {
                    out.print("<div class='card'>");
                    out.print("<div class='m-5 text-center'>"
                            + "<div><h4>Sin información registrada</h4></div>");
                    out.print("<div class='text'><a href='index.jsp'><button class='btn btn-green' style='border-radius: 4px;'>Volver</button></a></div>");
                    out.print("</div>");
                    out.print("</div>");
                } else {
                    out.print("<div class='card'>");
                    out.print("<div class='m-5 text-center'>"
                            + "<div><h4>Sin información registrada, dar clic en el siguiente boton para filtrar contenido</h4></div>");
                    out.print("<div class='text'><button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)'><i class='fas fa-search'></i></button></div>");
                    out.print("</div>");
                    out.print("</div>");
                }
            }
            out.print("</section>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_Informe.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
