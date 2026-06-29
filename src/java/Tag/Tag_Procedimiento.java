package Tag;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controlador.ProcedimientoJpaController;
import javax.servlet.http.HttpSession;

public class Tag_Procedimiento extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession sesion = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        ProcedimientoJpaController ProcedimientoJpa = new ProcedimientoJpaController();
        List Lst_Procedimiento = null, Lst_ProcedimientoId = null;
        int IdProcedimiento = 0, Estado = 0;
        try {
            try {
                IdProcedimiento = Integer.parseInt(pageContext.getRequest().getAttribute("IdProcedimiento").toString());
            } catch (Exception e) {
                IdProcedimiento = 0;
            }
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR PROCEDIMIENTO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_reg'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Procedimiento</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<div class='cont_form_user'>");

            out.print("<form action='Procedimiento?opc=2' method='post' class='needs-validation' novalidate=''>");

            out.print("<div class='col-12'>");
            out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top' title='Nombre'>");
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
            if (IdProcedimiento > 0) {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR PROCEDIMIENTO">
                Lst_ProcedimientoId = ProcedimientoJpa.ConsultarProcedimientoId(IdProcedimiento);
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_reg'>");
                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Modificar Procedimiento</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");

                if (Lst_ProcedimientoId != null) {
                    Object[] obj_procedimientoId = (Object[]) Lst_ProcedimientoId.get(0);
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Procedimiento?opc=2' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' name='IdProcedimiento' value='" + IdProcedimiento + "'>");
                    out.print("<div class='col-12'>");
                    out.print("<input type='text' class='form-control' name='Nombre' id='Nombre' placeholder='Nombre' required='' data-toggle='tooltip' data-placemente='top'  value='" + obj_procedimientoId[1] + "' title='Nombre'>");
                    out.print("<div class='invalid-feedback invalid_data_rll'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp;Debe ingresar un valor!</div>");
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
            out.print("<h1>Módulo Procedimiento</h1>");
            out.print("</div>");

            out.print("<div class='section-body'>");
            out.print("<div class='row'>");
            out.print("<div class='col-12'>");
            out.print("<div class='card'>");

            out.print("<div class='card-header' style='justify-content: space-between;'>");
            out.print("<h4>Listado de Procedimiento</h4>");
            out.print("<button class='btn btn-green' style='border-radius: 4px;' onclick='mostrarConvencion(1)'><i class='fas fa-plus'></i></button>");
            out.print("</div>");

            out.print("<div class='card-body'>");
            out.print("<div class='table-responsive'>");
            out.print("<table class='table table-bordered' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>ID</th>");
            out.print("<th>Procedimiento</th>");
            out.print("<th>Fecha Registro</th>");
            out.print("<th style='text-align: center;'>Estado</th>");
            out.print("<th style='text-align: center;'>OPC</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");

            Lst_Procedimiento = ProcedimientoJpa.ConsultarProcedimiento();
            if (Lst_Procedimiento != null) {
                for (int i = 0; i < Lst_Procedimiento.size(); i++) {
                    Object[] obj_procedimiento = (Object[]) Lst_Procedimiento.get(i);
                    out.print("<tr>");
                    out.print("<td>" + obj_procedimiento[0] + "</td>");
                    out.print("<td>" + obj_procedimiento[1] + "</td>");
                    out.print("<td>" + obj_procedimiento[3] + "</td>");
                    Estado = Integer.parseInt(obj_procedimiento[2].toString());
                    out.print("<td align='center'>" + ((Estado == 1) ? "<div class='badge badge-success'>Activo</div>" : "<div class='badge badge-danger'>Inactivo</div>") + "</td>");
                    out.print("<td class='text-center'>");
                    out.print("<a href='Procedimiento?opc=1&IdProcedimiento=" + obj_procedimiento[0] + "' style='background: orange;' class='btn btn-warning btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-edit'></i></a> &nbsp;&nbsp;");
                    out.print("<a href='Procedimiento?opc=3&IdProcedimiento=" + obj_procedimiento[0] + "&Estado=" + Estado + "' class='btn btn-" + ((Estado == 1) ? "success" : "danger") + " btn-sm' data-toggle='tooltip' data-placement='top' title='Cambiar estados'><i class='" + ((Estado == 1) ? "fas fa-check-circle" : "fas fa-times-circle") + "'></i></a> &nbsp;&nbsp;");
                    out.print("</td>");
                    out.print("</tr>");
                }
            } else {
                out.print("<tr><td class='text-center' colspan='5'><i class=\"far fa-sad-cry\"></i>Sin datos Registrados</td></tr>");
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
            Logger.getLogger(Tag_Procedimiento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
