
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Alerta.tld" prefix="Alerta" %>
<%@taglib uri="/WEB-INF/tlds/Paciente.tld" prefix="Paciente" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modulo Paciente</title>
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Contenido/Assets/css/main.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/izitoast/css/iziToast.min.css">
        <link rel="shortcut icon" href="Contenido/Imagen/DienteClauOficial.png" />
    </head>
     <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <Paciente:Paciente/>
                </div>
            </div>
        </div>
        <Alerta:Alerta/>
        <script src="Contenido/Assets/modules/datatables/datatables.min.js"></script>
        <script src="Contenido/Assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Contenido/Assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-datatables.js"></script>
        <script src="Contenido/Assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-toastr.js"></script>

    </body>
</html>
