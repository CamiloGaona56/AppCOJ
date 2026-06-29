package Tag;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controlador.PacienteJpaController;
import Controlador.ConfiguracionJpaController;
import javax.servlet.http.HttpSession;

public class Tag_Paciente extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession sesion = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        PacienteJpaController PacienteJpa = new PacienteJpaController();
        ConfiguracionJpaController ConfiguracionJpa = new ConfiguracionJpaController();
        int Estado = 0, IdPaciente = 0, EstadoPaciente = 0;
        List Lst_Paciente = null, Lst_configuracion = null, Lst_PacienteId = null;
        try {
            try {
                IdPaciente = Integer.parseInt(pageContext.getRequest().getParameter("IdPaciente"));
            } catch (NumberFormatException e) {
                IdPaciente = 0;
            }
            try {
                Estado = Integer.parseInt(pageContext.getRequest().getParameter("Estado"));
            } catch (NumberFormatException e) {
                Estado = 0;
            }
            if (IdPaciente > 0) {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR PACIENTE">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg'>");

                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Modificar Paciente</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");

                out.print("<div class='cont_form_user'>");
                Lst_PacienteId = PacienteJpa.ConsultarPacienteId(IdPaciente);
                if (Lst_PacienteId != null) {
                    Object[] Obj_pacienteId = (Object[]) Lst_PacienteId.get(0);
                    out.print("<form action='Paciente?opc=2' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' name='IdPaciente' value='" + Obj_pacienteId[0] + "'>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' value='" + Obj_pacienteId[3] + "' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='d-flex text-center'>");

                    out.print("<div class='col-6'>");

                    out.print("<select class='form-control' name='Tipo_documento' style='margin-top: 12px;margin-bottom: 12px;'>");
                    Lst_configuracion = ConfiguracionJpa.ConsultarConfiguracion("Tipo_documento");
                    if (Lst_configuracion != null) {
                        for (int i = 0; i < Lst_configuracion.size(); i++) {
                            Object[] Obj_Configuracion = (Object[]) Lst_configuracion.get(i);
                            if (Obj_pacienteId[1].equals(Obj_Configuracion[2])) {
                                out.print("<option value='" + Obj_Configuracion[2] + "' selected>" + Obj_Configuracion[3] + "</option>");
                            } else {
                                out.print("<option value='" + Obj_Configuracion[2] + "'>" + Obj_Configuracion[3] + "</option>");
                            }
                        }
                    }
                    out.print("</select>");

                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-6'>");
                    out.print("<input type='number' class='form-control' name='Documento' id='Documento' placeholder='Documento' required='' value='" + Obj_pacienteId[2] + "' data-toggle='tooltip' data-placemente='top' title='Documento'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>");

                    out.print("<div class='d-flex text-center'>");

                    out.print("<div class='col-6'>");
                    out.print("<input type='text' class='form-control' name='Celular' id='Celular' placeholder='Celular' required='' value='" + Obj_pacienteId[4] + "' data-toggle='tooltip' data-placemente='top' title='Celular'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-6'>");
                    out.print("<input type='date' class='form-control' name='Fecha' id='Fecha' placeholder='Fecha' required='' value='" + Obj_pacienteId[5] + "' data-toggle='tooltip' data-placemente='top' title='Fecha'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Recomendacion' id='Recomendacion' placeholder='Recomendación' required='' value='" + Obj_pacienteId[6] + "' data-toggle='tooltip' data-placemente='top' title='Recomendación'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                    out.print("</div>");

                    out.print("</form>");
                }
                out.print("</div>");

                out.print("</div>");
                out.print("</div>");
                //</editor-fold>
            }
            //<editor-fold defaultstate="collapsed" desc="REGISTROS PACIENTE">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Paciente</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<div class='cont_form_user'>");

            out.print("<form action='Paciente?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='d-flex text-center'>");

            out.print("<div class='col-6'>");

            out.print("<select class='form-control' name='Tipo_documento' style='margin-top: 12px;margin-bottom: 12px;'>");
            out.print("<option value='0'>Seleccione Tipo Documento</option>");
            Lst_configuracion = ConfiguracionJpa.ConsultarConfiguracion("Tipo_documento");
            if (Lst_configuracion != null) {
                for (int i = 0; i < Lst_configuracion.size(); i++) {
                    Object[] Obj_Configuracion = (Object[]) Lst_configuracion.get(i);
                    out.print("<option value='" + Obj_Configuracion[2] + "'>" + Obj_Configuracion[3] + "</option>");
                }
            }
            out.print("</select>");

            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-6'>");
            out.print("<input type='number' class='form-control' name='Documento' id='Documento' placeholder='Documento' required='' data-toggle='tooltip' data-placemente='top' title='Documento'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("</div>");

            out.print("<div class='d-flex text-center'>");

            out.print("<div class='col-6'>");
            out.print("<input type='text' class='form-control' name='Celular' id='Celular' placeholder='Celular' required='' data-toggle='tooltip' data-placemente='top' title='Celular'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-6'>");
            out.print("<input type='date' class='form-control' name='Fecha' id='Fecha' placeholder='Fecha' required='' data-toggle='tooltip' data-placemente='top' title='Fecha'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Recomendacion' id='Recomendacion' placeholder='Recomendación' required='' data-toggle='tooltip' data-placemente='top' title='Recomendación'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            out.print("<section class='section'>");
            out.print("<div class='section-header'>");
            out.print("<h1>Módulo Paciente</h1>");
            out.print("</div>");
            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");
            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Listado de Paciente</h4>");
            out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)'><i class='fas fa-plus'></i></button>");
            out.print("</div>");
            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Icono</th>");
            out.print("<th>DNI</th>");
            out.print("<th>Documento</th>");
            out.print("<th>Nombre</th>");
            out.print("<th>Celular</th>");
            out.print("<th>Fecha</th>");
            out.print("<th>Recomendación</th>");
            out.print("<th style='text-align: center;'>Estado</th>");
            out.print("<th style='text-align: center;'>Tratamiento</th>");
            out.print("<th style='text-align: center;'>OPC</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            if (Estado == 1) {

            } else {
                Lst_Paciente = PacienteJpa.ConsultarPaciente();
            }
            if (Lst_Paciente != null) {
                for (int i = 0; i < Lst_Paciente.size(); i++) {
                    Object[] obj_Paciente = (Object[]) Lst_Paciente.get(i);
                    double randmAvatar = Math.floor(Math.random() * 8) + 1;
                    int randow = (int) randmAvatar;
                    out.print("<tr>");
                    out.print("<td><img alt='image' src='Contenido/Assets/img/avatar/avatar-" + randow + ".png' class='rounded-circle' width='35'></td>");
                    out.print("<td>" + obj_Paciente[1] + "</td>");
                    out.print("<td>" + obj_Paciente[2] + "</td>");
                    out.print("<td>" + obj_Paciente[3] + "</td>");
                    out.print("<td>" + obj_Paciente[4] + "</td>");
                    out.print("<td>" + obj_Paciente[5] + "</td>");
                    out.print("<td>" + obj_Paciente[6] + "</td>");
                    EstadoPaciente = Integer.parseInt(obj_Paciente[7].toString());
                    out.print("<td align='center'>" + ((EstadoPaciente == 1) ? "<div class='badge badge-success'>Activo</div>" : "<div class='badge badge-danger'>Inactivo</div>") + "</td>");
                    out.print("<td align='center'><button onclick=\"javascript:location.href='Proyeccion?opc=1&IdPaciente=" + obj_Paciente[0] + "'\" class='btn btn-green btn-icon btn'><i class=\"fas fa-tooth\"></i></button></td>");
                    out.print("<td class='text-center'>");
                    out.print("<a href='Paciente?opc=1&IdPaciente=" + obj_Paciente[0] + "' style='background: orange;' class='btn btn-warning btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-edit'></i></a> &nbsp;&nbsp;");
                    out.print("<a href='Paciente?opc=3&IdPaciente=" + obj_Paciente[0] + "&EstadoPaciente=" + EstadoPaciente + "' class='btn btn-" + ((EstadoPaciente == 1) ? "success" : "danger") + " btn-sm' data-toggle='tooltip' data-placement='top' title='Cambiar estados'><i class='" + ((EstadoPaciente == 1) ? "fas fa-check-circle" : "fas fa-times-circle") + "'></i></a> &nbsp;&nbsp;");
                    out.print("</td>");
                    out.print("</tr>");
                }
            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</div>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("</section>");

        } catch (IOException ex) {
            Logger.getLogger(Tag_Paciente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
