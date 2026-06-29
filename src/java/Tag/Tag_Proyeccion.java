package Tag;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import Controlador.PacienteJpaController;
import Controlador.ProyeccionJpaController;
import Controlador.ProcedimientoJpaController;
import Controlador.AbonoJpaController;
import Controlador.ConfiguracionJpaController;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.List;

public class Tag_Proyeccion extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        HttpSession sesion = pageContext.getSession();
        JspWriter out = pageContext.getOut();
        PacienteJpaController PacienteJpa = new PacienteJpaController();
        ProyeccionJpaController ProyeccionJpa = new ProyeccionJpaController();
        ProcedimientoJpaController ProcedimientoJpa = new ProcedimientoJpaController();
        AbonoJpaController AbonoJpa = new AbonoJpaController();
        ConfiguracionJpaController ConfiguracionJpa = new ConfiguracionJpaController();
        List Lst_paciente = null, Lst_proyeccion = null, Lst_Procedimiento = null, Lst_proyeccionId = null, Lst_total = null, Lst_abono = null, Lst_abonoId = null, Lst_dientes = null;
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        int IdPaciente = 0, IdProyeccion = 0, IdAbono = 0, Temp = 0;
        formatter.setMinimumFractionDigits(0);
        formatter.setMaximumFractionDigits(0);
        try {
            try {
                IdPaciente = Integer.parseInt(pageContext.getRequest().getParameter("IdPaciente"));
            } catch (Exception e) {
                IdPaciente = 0;
            }
            try {
                IdProyeccion = Integer.parseInt(pageContext.getRequest().getParameter("IdProyeccion"));
            } catch (Exception e) {
                IdProyeccion = 0;
            }
            try {
                IdAbono = Integer.parseInt(pageContext.getRequest().getParameter("IdAbono"));
            } catch (Exception e) {
                IdAbono = 0;
            }
            try {
                Temp = Integer.parseInt(pageContext.getRequest().getParameter("Temp"));
            } catch (Exception e) {
                Temp = 0;
            }
            if (IdProyeccion > 0) {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR PROYECCION DIENTE">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana2' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_proy scrollbar'>");

                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h2>Modificar Tratamiento</h2>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(2)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                Lst_proyeccionId = ProyeccionJpa.ConsultarIdProyeccion(IdProyeccion);
                if (Lst_proyeccionId != null) {
                    Object[] Obj_ProyeccionId = (Object[]) Lst_proyeccionId.get(0);
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Proyeccion?opc=3' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' id='' name='IdPaciente' value='" + IdPaciente + "'>");
                    out.print("<input type='hidden' id='' name='IdProyeccion' value='" + IdProyeccion + "'>");

                    out.print("<div id='container'>"); // Contenedor principal

                    out.print("<div class='d-flex mb-3 justify-content-around align-items-center'>"); // Clase de Bootstrap para espacio en la parte inferior

                    out.print("<div class='div_select' data-toggle='tooltip' data-placement='right' title='Diente'>");
                    out.print("<div class='text-center' style='margin-bottom:12px'><b>Diente</b></div>");
                    out.print("<select class='select2 form-control'  style='width:auto !important;' name='Cbx_Diente' required>");
                    out.print("<option selected value='" + Obj_ProyeccionId[4] + "'>Diente #" + Obj_ProyeccionId[4] + "</option>");
                    for (int i = 0; i <= 32; i++) {
                        if (Integer.parseInt(Obj_ProyeccionId[4].toString()) != i) {
                            out.print("<option value='" + i + "'>Diente #" + i + "</option>");
                        }
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='div_select' data-toggle='tooltip' data-placement='right' title='Procedimiento'>");
                    out.print("<div class='text-center' style='margin-bottom:12px'><b>Procedimiento</b></div>");
                    Lst_Procedimiento = ProcedimientoJpa.ConsultarProcedimientoActivo();
                    out.print("<select class=' form-control select2 ' style='width:auto !important;' name='Cbx_Procedimiento' required>");
                    out.print("<option selected value='" + Obj_ProyeccionId[5] + "'>" + Obj_ProyeccionId[6] + "</option>");
                    if (Lst_Procedimiento != null) {
                        for (int j = 0; j < Lst_Procedimiento.size(); j++) {
                            Object[] Obj_Procedimiento = (Object[]) Lst_Procedimiento.get(j);
                            if (Integer.parseInt(Obj_ProyeccionId[5].toString()) != Integer.parseInt(Obj_Procedimiento[0].toString())) {
                                out.print("<option value='" + Obj_Procedimiento[0] + "'>" + Obj_Procedimiento[1] + "</option>");
                            }
                        }
                    }
                    out.print("</select>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
                    out.print("</div>");

                    formatter.setMinimumFractionDigits(0);
                    formatter.setMaximumFractionDigits(0);
                    double valorModifica = Double.parseDouble(Obj_ProyeccionId[8].toString());
                    String valorFormateadoModificar = formatter.format(valorModifica);
// Imprimir el valor formateado en la interfaz

                    out.print("<div class='div_select'>");
                    out.print("<div class='text-center' style='margin-bottom:12px'><b>Presupuesto</b></div>");
                    out.print("<input type='text' class='form-control' id='currencyInput' style='margin:0px' name='TxtPresupuesto' inputmode='decimal' value='" + valorFormateadoModificar + "' placeholder='$ 0,00' data-toggle='tooltip' data-placement='right' title='Presupuesto' required>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("<div class='div_select'>");
                    out.print("<div class='text-center' style='margin-bottom:12px'><b>Observación</b></div>");
                    out.print("<textarea class='form-control' style='height: 42px !important;' name='TxtObservacion'>" + Obj_ProyeccionId[11] + "</textarea>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
                    out.print("</div>");

                    out.print("</div>"); // Cierre del primer grupo de inputs

                    formatter.setMinimumFractionDigits(0);
                    formatter.setMaximumFractionDigits(0);
                    double valorModificaOld = Double.parseDouble(Obj_ProyeccionId[8].toString());
                    String valorFormateadoModificarOld = formatter.format(valorModificaOld);
                    out.print("<input type='hidden' class='form-control'  name='TxtPresupuestoOld' value='" + valorFormateadoModificarOld + "' required>");

                    out.print("</div>"); // Cierre del contenedor

                    out.print("<div class='' style='width: 100%; text-align:right;'>");
                    out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                }
                out.print("</div>");
                out.print("</div>");
                //</editor-fold>
            }
            //<editor-fold defaultstate="collapsed" desc="REGISTAR PROYECCION DIENTE">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana1' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_proy scrollbar'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h2>Registrar Tratamiento</h2>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(1)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<div class='cont_form_user'>");
            out.print("<form action='Proyeccion?opc=2' method='post' class='needs-validation' novalidate=''>");
            out.print("<input type='hidden' id='ConteoValidacion' name='ConteoSeccionVal' value='1'>");
            out.print("<input type='hidden' id='' name='IdPaciente' value='" + IdPaciente + "'>");
            out.print("<div id='container'>"); // Contenedor principal

            // Primer contenedor de inputs
            out.print("<div class='d-flex mb-3 justify-content-around align-items-center'>"); // Clase de Bootstrap para espacio en la parte inferior

            out.print("<div class='div_select' data-toggle='tooltip' data-placement='right' title='Diente'>");
            out.print("<div class='text-center' style='margin-bottom:12px'><b>Diente</b></div>");
            out.print("<select class='select2 form-control'  style='width:auto !important;' name='Cbx_Diente1' required>");
            out.print("<option selected disabled value=''>Seleccionar Diente</option>");
            Lst_dientes = ConfiguracionJpa.ConsultarConfiguracion("Dientes");
            if (Lst_dientes != null) {
                Object[] ObjDiente = (Object[]) Lst_dientes.get(0);
                String[] Dientes = ObjDiente[3].toString().replace("][", "---").replace("[", "").replace("]", "").split("---");
                for (int i = 0; i < Dientes.length; i++) {
                    out.print("<option value='" + Dientes[i] + "'>Diente #" + Dientes[i] + "</option>");
                }
            } else {
                out.print("<option value='0'>Diente #0</option>");

            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='div_select' data-toggle='tooltip' data-placement='right' title='Procedimiento'>");
            out.print("<div class='text-center' style='margin-bottom:12px'><b>Procedimiento</b></div>");
            Lst_Procedimiento = ProcedimientoJpa.ConsultarProcedimientoActivo();
            out.print("<select class=' form-control select2 ' style='width:auto !important;' name='Cbx_Procedimiento1' required>");
            out.print("<option selected disabled value=''>Seleccionar Procedimiento</option>");
            if (Lst_Procedimiento != null) {
                for (int j = 0; j < Lst_Procedimiento.size(); j++) {
                    Object[] Obj_Procedimiento = (Object[]) Lst_Procedimiento.get(j);
                    out.print("<option value='" + Obj_Procedimiento[0] + "'>" + Obj_Procedimiento[1] + "</option>");
                }
            }
            out.print("</select>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='div_select'>");
            out.print("<div class='text-center' style='margin-bottom:12px'><b>Presupuesto</b></div>");
            out.print("<input type='text' class='form-control' id='currencyInput' name='TxtPresupuesto1' style='margin:0px' inputmode='decimal' placeholder='$ 0,00' required>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("<div class='div_select'>");
            out.print("<div class='text-center' style='margin-bottom:12px'><b>Observación</b></div>");
            out.print("<textarea class='form-control' style='height: 42px !important;' name='TxtObservacion1'></textarea>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div>");
            out.print("</div>");

            out.print("</div>"); // Cierre del primer grupo de inputs

            out.print("</div>"); // Cierre del contenedor

            //<editor-fold defaultstate="collapsed" desc="SCRIPT NUEVA SECCION">
            out.print("<script>\n"
                    + "    let contadorSecciones = 1; // Inicializa el contador desde 2\n"
                    + "    let contadorNameInpt = 2; // Inicializa el contador desde 2\n"
                    + "    \n"
                    + "    function agregarDiv() {\n"
                    + "        const container = document.getElementById('container');\n"
                    + "        const newDivContainer = document.createElement('div');\n"
                    + "        newDivContainer.classList.add('d-flex', 'mb-3', 'justify-content-around', 'align-items-center');\n"
                    + "        newDivContainer.innerHTML = `\n"
                    + "            <div class='div_select' data-toggle='tooltip' data-placement='right' title='Diente'>\n"
                    + "                <div class='text-center'><b>Diente</b></div>\n"
                    + "                <select class='select2 form-control' style='width:100% !important;' name='Cbx_Diente${contadorNameInpt}' required>\n"
                    + "                    <option selected disabled value=''>Seleccionar Diente</option>\n");

            for (int i = 0; i <= 32; i++) {
                out.print("<option value='" + i + "'>Diente #" + i + "</option>");
            }

            out.print("                </select>\n"
                    + "                <div class='invalid-feedback invalid_data'>\n"
                    + "                    <i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class='div_select' data-toggle='tooltip' data-placement='right' title='Procedimiento'>\n"
                    + "                <div class='text-center'><b>Procedimiento</b></div>\n"
                    + "                <select class='form-control select2' style='width:auto !important;' name='Cbx_Procedimiento${contadorNameInpt}' required>\n"
                    + "                    <option selected disabled value=''>Seleccionar Procedimiento</option>\n");

            if (Lst_Procedimiento != null) {
                for (int j = 0; j < Lst_Procedimiento.size(); j++) {
                    Object[] Obj_Procedimiento = (Object[]) Lst_Procedimiento.get(j);
                    out.print("<option value='" + Obj_Procedimiento[0] + "'>" + Obj_Procedimiento[1] + "</option>");
                }
            }

            out.print("                </select>\n"
                    + "                <div class='invalid-feedback invalid_data'>\n"
                    + "                    <i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class='div_select'>\n"
                    + "                <div class='text-center' style='margin-bottom:12px'><b>Presupuesto</b></div>\n"
                    + "                <input type='text' class='form-control currencyInput' style='margin:0px' name='TxtPresupuesto${contadorNameInpt}' inputmode='decimal' placeholder='$ 0,00' required>\n"
                    + "                <div class='invalid-feedback invalid_data'>\n"
                    + "                    <i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "            <div class='div_select'>\n"
                    + "                <div class='text-center' style='margin-bottom:12px'><b>Observación</b></div>\n"
                    + "                <div class='d-flex align-items-center'><textarea class='form-control' style='height: 42px !important;' name='TxtObservacion${contadorNameInpt}' ></textarea>\n"
                    + "                <button type='button' style='height:2%' class='btn btn-danger ml-2 btn-sm eliminarDiv'><i class=\"fas fa-minus\"></i></button></div>\n" // Botón para eliminar la sección
                    + "                <div class='invalid-feedback invalid_data'>\n"
                    + "                    <i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!\n"
                    + "                </div>\n"
                    + "            </div>\n"
                    + "        `;\n"
                    + "        container.appendChild(newDivContainer);\n"
                    + "        $(newDivContainer).find('.select2').select2();\n"
                    + "        contadorSecciones++;\n"
                    + "        contadorNameInpt++;\n"
                    + "        document.getElementById('ConteoValidacion').value = contadorSecciones;\n"
                    + "        aplicarFormatoMoneda();\n"
                    + "        // Añadir el evento de eliminación al botón recién creado\n"
                    + "        newDivContainer.querySelector('.eliminarDiv').addEventListener('click', function() {\n"
                    + "            eliminarDiv(newDivContainer);\n"
                    + "        });\n"
                    + "    }\n"
                    + "    function eliminarDiv(div) {\n"
                    + "        div.remove(); // Elimina el div\n"
                    + "        contadorSecciones--;\n" // Resta el contador\n"
                    + "        contadorNameInpt--;\n" // Resta el contador\n"
                    + "        document.getElementById('ConteoValidacion').value = contadorSecciones;\n" // Actualiza el contador\n"
                    + "    }\n"
                    + "    function aplicarFormatoMoneda() {\n"
                    + "        const inputs = document.querySelectorAll('.currencyInput');\n"
                    + "        inputs.forEach(function(input) {\n"
                    + "            input.addEventListener('input', function (e) {\n"
                    + "                let value = e.target.value.replace(/\\D/g, '');\n"
                    + "                e.target.value = value;\n"
                    + "            });\n"
                    + "            input.addEventListener('blur', function (e) {\n"
                    + "                let value = e.target.value.replace(/\\D/g, '');\n"
                    + "                if (value) {\n"
                    + "                    value = new Intl.NumberFormat('es-CO', {\n"
                    + "                        style: 'currency',\n"
                    + "                        currency: 'COP',\n"
                    + "                        minimumFractionDigits: 0,\n"
                    + "                        maximumFractionDigits: 0\n"
                    + "                    }).format(value);\n"
                    + "                } else {\n"
                    + "                    value = '';\n"
                    + "                }\n"
                    + "                e.target.value = value;\n"
                    + "            });\n"
                    + "            input.addEventListener('focus', function (e) {\n"
                    + "                let value = e.target.value.replace(/[^0-9]/g, '');\n"
                    + "                e.target.value = value;\n"
                    + "            });\n"
                    + "        });\n"
                    + "    }\n"
                    + "    document.addEventListener('DOMContentLoaded', aplicarFormatoMoneda);\n"
                    + "</script>");
            //</editor-fold>

            out.print("<div class='text-center'><button id=\"addButton\" type=\"button\" class=\"btn btn-primary\" onclick=\"agregarDiv()\"><i class=\"fas fa-plus\"></i></button></div>");
            out.print("<div class='' style='width: 100%; text-align:right;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");

            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            //<editor-fold defaultstate="collapsed" desc="ABONO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana3' style='opacity: 1.03; display:" + ((Temp > 0) ? "block" : "none") + ";'>");
            out.print("<div class='cont_Pagos scrollbar'>");

            out.print("<div style='display: flex; justify-content: space-between'>");

            out.print("<div class='d-flex align-items-flex-strart'>"
                    + "<div><h2>Abonos</h2></div>");
            out.print("<div class='ml-2'><button class='btn btn-green btn-sm' style='border-radius: 4px;' onclick='mostrarConvencion(4)'><i class='fas fa-plus'></i></button></div>"
                    + "</div>");

            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(3)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            if (IdAbono > 0) {
                //<editor-fold defaultstate="collapsed" desc="MODIFICAR ABONO">
                out.print("<div class='sweet-local' tabindex='-1' id='Ventana5' style='opacity: 1.03; display:block;'>");
                out.print("<div class='cont_PagosReg scrollbar'>");

                out.print("<div style='display: flex; justify-content: space-between'>");
                out.print("<h3>Modificar Abono</h3>");
                out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(5)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
                out.print("</div>");
                Lst_abonoId = AbonoJpa.ConsultarAbtonoIdAbono(IdAbono);
                if (Lst_abonoId != null) {
                    Object[] ObjAbono = (Object[]) Lst_abonoId.get(0);
                    out.print("<div class='cont_form_user'>");
                    out.print("<form action='Proyeccion?opc=5' method='post' class='needs-validation' novalidate=''>");
                    out.print("<input type='hidden' id='' name='IdPaciente' value='" + IdPaciente + "'>");
                    out.print("<input type='hidden' id='' name='IdAbono' value='" + IdAbono + "'>");

                    out.print("<div class='d-flex mb-3 justify-content-around align-items-center'>");

                    out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Fecha</b></div>");
                    out.print("<input type='date' class='form-control' name='Fecha' style='margin:0px' value='" + ObjAbono[2] + "'  placeholder='$ 0,00' required>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

                    double AbonoMod = Double.parseDouble(ObjAbono[3].toString());
                    String AbonoModFom = formatter.format(AbonoMod);
                    out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Abono</b></div>");
                    out.print("<input type='text' class='form-control' id='currencyInputAbono' name='Abono' value='" + AbonoModFom + "' style='margin:0px' inputmode='decimal' placeholder='$ 0,00' required>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

                    double SaldoMod = Double.parseDouble(ObjAbono[4].toString());
                    String SaldoModFom = formatter.format(SaldoMod);
                    out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Saldo</b></div>");
                    out.print("<input type='text' class='form-control' id='currencyInputSaldo' name='Saldo' style='margin:0px' value='" + SaldoModFom + "' inputmode='decimal' placeholder='$ 0,00' required>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

                    out.print("</div>");

                    out.print("<div><div class='text-center' style='margin-bottom:12px'><b>Medio</b></div>");

                    out.print("<div class=\"form-group\">\n"
                            + "  <div class=\"selectgroup w-100\">\n"
                            + "    <label class=\"selectgroup-item\">\n"
                            + "      <input type=\"radio\" name=\"Medio\" value=\"Efectivo\" class=\"selectgroup-input\" " + (ObjAbono[5].equals("Efectivo") ? "checked" : "") + " >\n"
                            + "      <span class=\"selectgroup-button selectgroup-button-icon efectivo\">\n"
                            + "        <i class=\"fas fa-money-bill-alt\">&nbsp;Efectivo</i>\n"
                            + "      </span>\n"
                            + "    </label>\n"
                            + "    <label class=\"selectgroup-item\">\n"
                            + "      <input type=\"radio\" name=\"Medio\" value=\"Nequi\" class=\"selectgroup-input\" " + (ObjAbono[5].equals("Nequi") ? "checked" : "") + ">\n"
                            + "      <span class=\"selectgroup-button selectgroup-button-icon nequi\">\n"
                            + "        <i class=\"fas fa-mobile-alt mr-2\">&nbsp;Nequi</i>\n"
                            + "      </span>\n"
                            + "    </label>\n"
                            + "    <label class=\"selectgroup-item\">\n"
                            + "      <input type=\"radio\" name=\"Medio\" value=\"DaviPlata\" class=\"selectgroup-input\" " + (ObjAbono[5].equals("DaviPlata") ? "checked" : "") + ">\n"
                            + "      <span class=\"selectgroup-button selectgroup-button-icon daviPlata\">\n"
                            + "        <i class=\"fas fa-mobile-alt mr-2\">&nbsp;DaviPlata</i>\n"
                            + "      </span>\n"
                            + "    </label>\n"
                            + "    <label class=\"selectgroup-item\">\n"
                            + "      <input type=\"radio\" name=\"Medio\" value=\"Tarjeta\" class=\"selectgroup-input\" " + (ObjAbono[5].equals("Tarjeta") ? "checked" : "") + ">\n"
                            + "      <span class=\"selectgroup-button selectgroup-button-icon tarjeta\">\n"
                            + "        <i class=\"fas fa-credit-card\">&nbsp;Tarjeta</i>\n"
                            + "      </span>\n"
                            + "    </label>\n"
                            + "  </div>\n"
                            + "</div>");

                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

                    out.print("<div class='mb-2'><div class='text-center' style='margin-bottom:12px'><b>Observación</b></div>");
                    out.print("<textarea class='form-control' name='Observacion' required placeholder='Observación'>" + ObjAbono[6] + "</textarea>");
                    out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

                    out.print("<div class='' style='width: 100%; text-align:right;'>");
                    out.print("<button class='btn btn-green btn-lg'>Modificar</button>");
                    out.print("</div>");

                    out.print("</form>");
                    out.print("</div>");
                }
                out.print("</div>");
                out.print("</div>");
                //</editor-fold>
            }
            //<editor-fold defaultstate="collapsed" desc="REGISTRAR ABONO">
            out.print("<div class='sweet-local' tabindex='-1' id='Ventana4' style='opacity: 1.03; display:none;'>");
            out.print("<div class='cont_PagosReg scrollbar'>");

            out.print("<div style='display: flex; justify-content: space-between'>");
            out.print("<h3>Registrar Abono</h3>");
            out.print("<button class='btn btn-outline-secondary' onclick='mostrarConvencion(4)' style='height: 30px;padding: 3px;width: 30px;'><i class='fas fa-times'></i></button>");
            out.print("</div>");

            out.print("<div class='cont_form_user'>");
            out.print("<form action='Proyeccion?opc=5' method='post' class='needs-validation' novalidate=''>");
            out.print("<input type='hidden' id='' name='IdPaciente' value='" + IdPaciente + "'>");

            out.print("<div class='d-flex mb-3 justify-content-around align-items-center'>");

            out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Fecha</b></div>");
            out.print("<input type='date' class='form-control' name='Fecha' style='margin:0px'  placeholder='$ 0,00' required>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

            out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Abono</b></div>");
            out.print("<input type='text' class='form-control' id='currencyInputAbono' name='Abono' style='margin:0px' inputmode='decimal' placeholder='$ 0,00' required>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

            out.print("<div class='DivWidth'><div class='text-center' style='margin-bottom:12px'><b>Saldo</b></div>");
            out.print("<input type='text' class='form-control' id='currencyInputSaldo' name='Saldo' style='margin:0px' inputmode='decimal' placeholder='$ 0,00' required>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

            out.print("</div>");

            out.print("<div><div class='text-center' style='margin-bottom:12px'><b>Medio</b></div>");

            out.print("<div class=\"form-group\">\n"
                    + "  <div class=\"selectgroup w-100\">\n"
                    + "    <label class=\"selectgroup-item\">\n"
                    + "      <input type=\"radio\" name=\"Medio\" value=\"Efectivo\" class=\"selectgroup-input\" checked>\n"
                    + "      <span class=\"selectgroup-button selectgroup-button-icon efectivo\">\n"
                    + "        <i class=\"fas fa-money-bill-alt\">&nbsp;Efectivo</i>\n"
                    + "      </span>\n"
                    + "    </label>\n"
                    + "    <label class=\"selectgroup-item\">\n"
                    + "      <input type=\"radio\" name=\"Medio\" value=\"Nequi\" class=\"selectgroup-input\">\n"
                    + "      <span class=\"selectgroup-button selectgroup-button-icon nequi\">\n"
                    + "        <i class=\"fas fa-mobile-alt mr-2\">&nbsp;Nequi</i>\n"
                    + "      </span>\n"
                    + "    </label>\n"
                    + "    <label class=\"selectgroup-item\">\n"
                    + "      <input type=\"radio\" name=\"Medio\" value=\"DaviPlata\" class=\"selectgroup-input\">\n"
                    + "      <span class=\"selectgroup-button selectgroup-button-icon daviPlata\">\n"
                    + "        <i class=\"fas fa-mobile-alt mr-2\">&nbsp;DaviPlata</i>\n"
                    + "      </span>\n"
                    + "    </label>\n"
                    + "    <label class=\"selectgroup-item\">\n"
                    + "      <input type=\"radio\" name=\"Medio\" value=\"Tarjeta\" class=\"selectgroup-input\">\n"
                    + "      <span class=\"selectgroup-button selectgroup-button-icon tarjeta\">\n"
                    + "        <i class=\"fas fa-credit-card\">&nbsp;Tarjeta</i>\n"
                    + "      </span>\n"
                    + "    </label>\n"
                    + "  </div>\n"
                    + "</div>");

            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

            out.print("<div class='mb-2'><div class='text-center' style='margin-bottom:12px'><b>Observación</b></div>");
            out.print("<textarea class='form-control' name='Observacion' required placeholder='Observación'></textarea>");
            out.print("<div class='invalid-feedback invalid_data'><i class='fas fa-exclamation-circle'></i>&nbsp;&nbsp; Debe ingresar un valor!</div></div>");

            out.print("<div class='' style='width: 100%; text-align:right;'>");
            out.print("<button class='btn btn-green btn-lg'>Registrar</button>");
            out.print("</div>");

            out.print("</form>");
            out.print("</div>");
            out.print("</div>");
            out.print("</div>");
            //</editor-fold>

            out.print("<table class='table table-bordered table-hover' id='table-2'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th>Fecha</th>");
            out.print("<th>Abono</th>");
            out.print("<th>Saldo</th>");
            out.print("<th>Medio</th>");
            out.print("<th>Observación</th>");
            out.print("<th>Opc</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            Lst_abono = AbonoJpa.ConsultarAbtonoIdPaciente(IdPaciente);
            if (Lst_abono != null) {
                for (int i = 0; i < Lst_abono.size(); i++) {
                    Object[] Obj_Abono = (Object[]) Lst_abono.get(i);
                    out.print("<tr>");
                    out.print("<td>" + Obj_Abono[2] + "</td>");
                    if (Obj_Abono[3] != null) {
                        double Abono = Double.parseDouble(Obj_Abono[3].toString());
                        String AbonoFormat = formatter.format(Abono);
                        out.print("<td>" + AbonoFormat + "</td>");
                    } else {
                        out.print("<td></td>");
                    }
                    if (Obj_Abono[4] != null) {
                        double Saldo = Double.parseDouble(Obj_Abono[4].toString());
                        String SaldoFormat = formatter.format(Saldo);
                        out.print("<td>" + SaldoFormat + "</td>");
                    } else {
                        out.print("<td></td>");
                    }
                    out.print("<td>" + Obj_Abono[5] + "</td>");
                    out.print("<td>" + Obj_Abono[6] + "</td>");
                    out.print("<td class='text-center'>");
                    out.print("<button  onclick=\"javascript:location.href='Proyeccion?opc=1&IdPaciente=" + Obj_Abono[1] + "&IdAbono=" + Obj_Abono[0] + "&Temp=1'\" class='btn btn-warning btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-edit'></i></button> &nbsp;&nbsp;");
                    out.print("<button  onclick=\"javascript:location.href='Proyeccion?opc=6&IdPaciente=" + Obj_Abono[1] + "&IdAbono=" + Obj_Abono[0] + "&Estado=0'\" class='btn btn-danger btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Eliminar item'><i class='fas fa-trash'></i></button> &nbsp;&nbsp;");
                    out.print("</td>");
                    out.print("</tr>");
                }
            } else {
                out.print("<td class='text-center' colspan='4'>Sin Abonos registrados</td>");
            }
            out.print("</tbody>");
            out.print("</table>");

            out.print("</div>");
            out.print("</div>");
            //</editor-fold>
            out.print("<section class='section'>");

            out.print("<div class='section-header' style='margin-bottom:0'>");
            out.print("<div class='d-flex justify-content-between align-items-baseline' style='width:12%'>");
            out.print("<div><button class='btn btn-outline-primary btn-sm' style='border-radius: 4px;' onclick=\"javascript:location.href='Paciente?opc=1'\" ><i class=\"far fa-hand-point-left\"></i></button></div>");
            out.print("<div><h1>Tratamiento</h1></div>");
            out.print("</div>");
            out.print("</div>");

            out.print("<div class='card-header d-flex' style='justify-content: space-between;'>");
            Lst_paciente = PacienteJpa.ConsultarPacienteId(IdPaciente);
            if (Lst_paciente != null) {
                Object[] Obj_Paciente = (Object[]) Lst_paciente.get(0);
                out.print("<div><h5>Paciente <br/></h5><b style='color:black; font-size:20px;'> " + Obj_Paciente[3] + "</b></div>");
                out.print("<div><h5>Celular <br/></h5><b style='color:black; font-size:20px;'> " + Obj_Paciente[4] + "</b></div>");
            }
            Lst_total = ProyeccionJpa.ConsultarProyeccionTotalPresupuesto(IdPaciente);
            if (Lst_total != null) {
                Object[] Obj_TotalPresupuesto = (Object[]) Lst_total.get(0);
                double TotalPres = Double.parseDouble(Obj_TotalPresupuesto[1].toString());
                String TotalPresupuesto = formatter.format(TotalPres);
                out.print("<div><h5>Total <br/></h5><b style='color:black; font-size:20px;'> " + TotalPresupuesto + "</b></div>");
            }
//            out.print("<button class='btn btn-outline-light btn-sm' style='border-radius: 4px;height: 5%' onclick='mostrarConvencion(1)'><img class='img_logoPay' src='Contenido/Imagen/transferencia-movil.png'></button>");
            out.print("<a onclick='mostrarConvencion(3)'  ><img  data-toggle='tooltip' data-placement='top' title='Abono' class='img_logoPay' src='Contenido/Imagen/Pago.png'></a>");
            out.print("<button class='btn btn-green' style='border-radius: 4px;height: 5%' onclick='mostrarConvencion(1)'><i class='fas fa-plus'></i>");
            out.print("</div>");
            //<editor-fold defaultstate="collapsed" desc="CONSULTA">
            out.print("<div class='col-12 col-md-12 col-lg-12 card card-info'>");

            out.print("<div class='card-body'>\n");

            out.print("<div class='table-responsive'>");

            out.print("<table class='table table-bordered table-hover' id='table-1'>");
            out.print("<thead>");
            out.print("<tr>");
            out.print("<th style='width:5%'>Diente</th>");
            out.print("<th>Procedimiento</th>");
            out.print("<th>Presupuesto Inicial</th>");
            out.print("<th>Presupuesto Actual </th>");
            out.print("<th>Observación </th>");
            out.print("<th style='text-align: center;'>Revisado</th>");
            out.print("<th style='text-align: center;'>OPC</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            Lst_proyeccion = ProyeccionJpa.ConsultarProcedimiento(IdPaciente);
            if (Lst_proyeccion != null) {
                for (int i = 0; i < Lst_proyeccion.size(); i++) {
                    Object[] Obj_proyeccion = (Object[]) Lst_proyeccion.get(i);
                    out.print("<tr>");
                    if (Integer.parseInt(Obj_proyeccion[4].toString()) == 0) {
                        out.print("<td>&nbsp;</td>");
                    } else {
                        out.print("<td><div class='text-center'>"
                                + "<div><img src='Contenido/Imagen/diente.png' style='width:28px'></div>"
                                + "<div class='text-center'><b style='font-size: 10px;'>#" + Obj_proyeccion[4] + "</b></div>"
                                + "</div></td>");
                    }
//                    out.print("<td><div><img</div>" + Obj_proyeccion[4] + "</td>");
                    out.print("<td>" + Obj_proyeccion[6] + "</td>");
                    // Crear un formateador para la moneda en Colombia (COP)
                    if (Obj_proyeccion[7] != null) {
                        double valorOld = Double.parseDouble(Obj_proyeccion[7].toString());
                        String valorFormateadoOld = formatter.format(valorOld);
                        // Imprimir el valor formateado en la interfaz
                        out.print("<td>" + valorFormateadoOld + "</td>");
                    } else {
                        out.print("<td>&nbsp;</td>");
                    }

                    double valor = Double.parseDouble(Obj_proyeccion[8].toString());
                    String valorFormateado = formatter.format(valor);
// Imprimir el valor formateado en la interfaz
                    out.print("<td><b>" + valorFormateado + "</b></td>");

                    out.print("<td>" + ((Obj_proyeccion[11] == null) ? "" : Obj_proyeccion[11]) + "</td>");
                    out.print("<td class='text-center'>");
                    if (Integer.parseInt(Obj_proyeccion[9].toString()) == 2) {
                        out.print("<div class='checkbox-wrapper-39'>\n");
                        out.print("<label>");
                        out.print("<input type='checkbox' class='estado-checkbox' checked data-id-proyeccion='" + Obj_proyeccion[0] + "'/>\n");
                        out.print("<span class='checkbox'></span>\n");
                        out.print("</label>\n");
                    } else {
                        out.print("<div class='checkbox-wrapper-39'>\n");
                        out.print("<label>");
                        out.print("<input type='checkbox' class='estado-checkbox' data-id-proyeccion='" + Obj_proyeccion[0] + "'/>\n");
                        out.print("<span class='checkbox'></span>\n");
                        out.print("</label>\n");
                    }
                    out.print("</td>");

                    out.print("<td class='text-center'>");
                    out.print("<button  onclick=\"javascript:location.href='Proyeccion?opc=1&IdPaciente=" + Obj_proyeccion[1] + "&IdProyeccion=" + Obj_proyeccion[0] + "'\" class='btn btn-warning btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Editar'><i class='fas fa-edit'></i></button> &nbsp;&nbsp;");
                    out.print("<button  onclick=\"javascript:location.href='Proyeccion?opc=4&IdPaciente=" + Obj_proyeccion[1] + "&IdProyeccion=" + Obj_proyeccion[0] + "&Estado=0'\" class='btn btn-danger btn-icon btn-sm' data-toggle='tooltip' data-placement='top' title='Eliminar item'><i class='fas fa-trash'></i></button> &nbsp;&nbsp;");
                    out.print("</td>");
                    out.print("</tr>");
                }

            }
            out.print("</tbody>");
            out.print("</table>");
            out.print("</div>");
            out.print("</div>\n");
            //</editor-fold>
            out.print("</section>");
        } catch (IOException ex) {
            Logger.getLogger(Tag_Proyeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return super.doStartTag();
    }
}
