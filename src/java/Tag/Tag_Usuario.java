package Tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controlador.UsuarioJpaController;
import Controlador.RolJpaController;
import java.util.List;
import javax.servlet.http.HttpSession;

public class Tag_Usuario extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession sesion = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        UsuarioJpaController JpaUsuario = new UsuarioJpaController();
        RolJpaController JpaRol = new RolJpaController();
        List Lst_Usuario = null, Lst_UsuarioId = null, Lst_rol = null;
        int IdUsuario = 0, IdRol = 0, estado = 0;
        try {

            try {
                IdUsuario = Integer.parseInt(pageContext.getRequest().getParameter("IdUsuario"));
            } catch (NumberFormatException e) {
                IdUsuario = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR USUARIO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Usuario</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<div class='cont_form_user'>");

            out.print("<form action='Usuario?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Apellido' id='Apellido' placeholder='Apellido' required='' data-toggle='tooltip' data-placemente='top' title='Apellido'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("</div>");
//
            out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Documento' id='Documento' placeholder='Documento' required data-toggle='tooltip' data-placemente='top' title='Documento'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='col-lg-12' data-toggle='tooltip' data-placemente='top' title='Rol'>");
            out.print("<select class='form-control' name='IdRol' required style='margin-top: 12px;margin-bottom: 12px;'>");
            out.print("<option value='' disabled selected>Seleccione Rol</option>");
            Lst_rol = JpaRol.ConsultarRol();
            if (Lst_rol != null) {
                for (int i = 0; i < Lst_rol.size(); i++) {
                    Object[] obj_rol = (Object[]) Lst_rol.get(i);
                    out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
                }
            } else {
                out.print("<option value='0'>Se ha producido un error</option>");
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");
            out.print("<div class='col-lg-11 col-md-6' style='max-width:95.666667%!important;'>");
            out.print("<div class='col-12'>");
            out.print("<input type='email' class='form-control' name='Correo' id='Correo' placeholder='Correo' required data-toggle='tooltip' data-placemente='top' title='Correo'>");
            out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='' style='width: 100%; text-align:center;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");

            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            if (IdUsuario > 0) {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR USUARIO">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                Lst_UsuarioId = JpaUsuario.ConsultaUsuarioId(IdUsuario);
                out.print("<div class='cont_reg'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Modificar Usuario</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                if (Lst_UsuarioId != null) {
                    Object[] obj_UsuarioId = (Object[]) Lst_UsuarioId.get(0);
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Usuario?opc=2' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' name='IdUsuario' id='IdUsuario' value='" + IdUsuario + "'>");
                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre' value='" + obj_UsuarioId[1] + "'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Apellido' id='Apellido' placeholder='Apellido' required='' data-toggle='tooltip' data-placemente='top' title='Apellido' value='" + obj_UsuarioId[2] + "'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;'>");

                    out.print("<div class='col-12' >");
                    out.print("<input type='text' class='form-control' name='Documento' id='Documento' placeholder='Documento' required data-toggle='tooltip' data-placemente='top' title='Documento' value='" + obj_UsuarioId[3] + "'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='col-lg-12' data-toggle='tooltip' data-placemente='top' title='Rol'>");
                    out.print("<select class='form-control' name='IdRol' style='margin-top: 12px;margin-bottom: 12px;' required>");
                    out.print("<option value='" + obj_UsuarioId[6] + "' selected>" + obj_UsuarioId[7] + "</option>");
                    Lst_rol = JpaRol.ConsultarRol();
                    if (Lst_rol != null || Lst_rol.size() > 0) {
                        for (int i = 0; i < Lst_rol.size(); i++) {
                            Object[] obj_rol = (Object[]) Lst_rol.get(i);
                            IdRol = Integer.parseInt(obj_UsuarioId[6].toString());
                            if (IdRol != Integer.parseInt(obj_rol[0].toString())) {
                                out.print("<option value='" + obj_rol[0] + "'>" + obj_rol[1] + "</option>");
                            }
                        }
                    } else {
                        out.print("<option value='0'>Se ha producido un error</option>");
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>");

                    out.print("<div class='col-lg-6 col-md-6' style='display: flex;justify-content: space-around;align-items: baseline;'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='email' class='form-control' name='Correo' id='Correo' placeholder='Correo' required data-toggle='tooltip' data-placemente='top' title='Correo' value='" + obj_UsuarioId[4] + "'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor, recordar ingresar (@)</div>");
                    out.print("</div>");
                    out.print("<div class='col-12'>");
                    out.print("<label class='custom-switch mt-2' style='margin: 12px;' onclick='SwitchValue()'>");
                    estado = Integer.parseInt(obj_UsuarioId[5].toString());
                    out.print("<span class='custom-switch-description'>Estado del usuario &nbsp;&nbsp;</span>");
                    out.print("<input style='margin-left: 10px;' type='checkbox' name='Estado' class='custom-switch-input' id='Nmb_est' value='" + estado + "' " + ((estado == 1) ? "checked" : "") + " onclick='SwitchValue()'>");
                    out.print("<span class='custom-switch-indicator'></span>");
                    out.print("</label>");
                    out.print("</div>");
                    out.print("</div>");

                    out.print("<div class='' style='width: 100%; text-align:center;'>");
                    out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");

                }
                out.print("</div>");
                out.print("</div>");
                //</editor-fold>
            }
            out.print("<section class='section'>");

            out.print("<div class='section-header'>");
            out.print("<h1>Módulo Usuarios</h1>");
            out.print("</div>");

            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");

            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Listado de Usuario</h4>");
            out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)'><i class='fas fa-plus'></i></button>");
            out.print("</div>");

            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Nombre</th>");
            out.print("<th>Apellido</th>");
            out.print("<th>Documento</th>");
            out.print("<th>Correo</th>");
            out.print("<th>Rol</th>");
            out.print("<th>Contraseña</th>");
            out.print("<th style='text-align: center;'>Estado</th>");
            out.print("<th style='text-align: center;'>OPC</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            Lst_Usuario = JpaUsuario.ConsultaUsuario();
            if (Lst_Usuario != null) {
                for (int i = 0; i < Lst_Usuario.size(); i++) {
                    Object[] obj_usu = (Object[]) Lst_Usuario.get(i);
                    out.print("<tr>");
                    out.print("<td>" + obj_usu[0] + "</td>");
                    out.print("<td>" + obj_usu[1] + "</td>");
                    out.print("<td>" + obj_usu[2] + "</td>");
                    out.print("<td>" + obj_usu[3] + "</td>");
                    out.print("<td>" + obj_usu[4] + "</td>");
                    out.print("<td>" + obj_usu[7] + "</td>");
                    out.print("<td>" + obj_usu[8] + "</td>");
                    estado = Integer.parseInt(obj_usu[5].toString());
                    out.print("<td align='center'>" + ((estado == 1) ? "<div class='badge badge-success'>Activo</div>" : "<div class='badge badge-danger'>Inactivo</div>") + "</td>");
                    out.print("<td class='text-center'>");
                    out.print("<a href='Usuario?opc=1&IdUsuario=" + obj_usu[0] + "' style='background: orange;' class='btn btn-warning btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-edit'></i></a> &nbsp;&nbsp;");
                    out.print("<a href='Usuario?opc=3&IdUsuario=" + obj_usu[0] + "&Estado=" + estado + "' class='btn btn-" + ((estado == 1) ? "success" : "danger") + " btn-sm' data-toggle='tooltip' data-placement='top' title='Cambiar estados'><i class='" + ((estado == 1) ? "fas fa-check-circle" : "fas fa-times-circle") + "'></i></a> &nbsp;&nbsp;");
                    out.print("<a href='Usuario?opc=4&IdUsuario=" + obj_usu[0] + "' class='btn btn-yellow  btn-icon btn-sm' data-toggle='tooltip ' data-placement='top' title='Reestablecer contraseña'><i class='fas fa-key'></i></a></td>");
                    out.print("</td>");
                    out.print("</tr>");
                }
            } else {
                out.print("<tr><td class='text-center' colspan='6'><i class=\"far fa-sad-cry\"></i>Sin datos Registrados</td></tr>");
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
            Logger.getLogger(Tag_Usuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
