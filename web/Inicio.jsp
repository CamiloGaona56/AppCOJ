<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Inicio.tld" prefix="Inicio" %>
<%@taglib uri="/WEB-INF/tlds/Alerta.tld" prefix="Alerta" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modulo Inicio</title>
        <link rel="stylesheet" href="Contenido/Assets/css/main.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/izitoast/css/iziToast.min.css">
        <link rel="shortcut icon" href="Contenido/Imagen/DienteClauOficial.png" />
    </head>
    <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <Inicio:Inicio/>
                </div>
            </div>
        </div>
        <Alerta:Alerta/>
        <script src="Contenido/Assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-toastr.js"></script>
    </body>
</html>
