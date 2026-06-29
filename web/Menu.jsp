<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Menu.tld" prefix="Menu" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <!-- General CSS Files -->
        <link rel="stylesheet" href="Contenido/Assets/modules/bootstrap/css/bootstrap.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/fontawesome/css/all.min.css">

        <!-- CSS Libraries -->
        <link rel="stylesheet" href="Contenido/Assets/modules/jqvmap/dist/jqvmap.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/summernote/summernote-bs4.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/owlcarousel2/dist/Assets/owl.carousel.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/owlcarousel2/dist/Assets/owl.theme.default.min.css">

        <!-- Template CSS -->
        <link rel="stylesheet" href="Contenido/Assets/css/style.css">
        <link rel="stylesheet" href="Contenido/Assets/css/components.css">
        <!-- Start GA -->

        <!--<script type="text/javascript" src="Alertas/dist/sweetalert.min.js"></script>-->
        <!--<link href="Alertas/dist/sweetalert.css" rel="stylesheet" type="text/css"/>-->
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());

            gtag('config', 'UA-94034622-3');
        </script>
    </head>
    <body>
        <Menu:Menu/>
        <script type="text/javascript" language="javascript">
            function mostrarConvencion(id) {
                if (document.getElementById("Ventana" + id).style.display === "none") {
                    document.getElementById("Ventana" + id).style.display = "block";
                } else if (document.getElementById("Ventana" + id).style.display === "block") {
                    document.getElementById("Ventana" + id).style.display = "none";
                }
            }
        </script>
        <script type="text/javascript" language="javascript">
            function MostrarWindows(id) {
                if (document.getElementById("Windows" + id).style.display === "none") {
                    document.getElementById("Windows" + id).style.display = "block";
                } else if (document.getElementById("Windows" + id).style.display === "block") {
                    document.getElementById("Windows" + id).style.display = "none";
                }
            }
        </script>
        <script type="text/javascript" language="javascript">
            function mostrarConvencion(id) {
                if (document.getElementById("Ventana" + id).style.display === "none") {
                    document.getElementById("Ventana" + id).style.display = "block";
                } else if (document.getElementById("Ventana" + id).style.display === "block") {
                    document.getElementById("Ventana" + id).style.display = "none";
                }
            }
        </script>
        <!-- Template JS File -->
        <script src="Contenido/Assets/modules/jquery.min.js"></script>
        <script src="Contenido/Assets/modules/popper.js"></script>
        <script src="Contenido/Assets/modules/tooltip.js"></script>
        <!--<link rel="stylesheet" href="Contenido/Assets/modules/bootstrap/css/bootstrap.min.css">-->
        <script src="Contenido/Assets/modules/bootstrap/js/bootstrap.min.js"></script>
        <script src="Contenido/Assets/modules/nicescroll/jquery.nicescroll.min.js"></script>
        <script src="Contenido/Assets/modules/moment.min.js"></script>
        <script src="Contenido/Assets/js/stisla.js"></script>

        <!-- JS Libraies -->
        <script src="Contenido/Assets/modules/jquery.sparkline.min.js"></script>
        <script src="Contenido/Assets/modules/chart.min.js"></script>
        <script src="Contenido/Assets/modules/owlcarousel2/dist/owl.carousel.min.js"></script>
        <script src="Contenido/Assets/modules/summernote/summernote-bs4.js"></script>
        <!-- Page Specific JS File -->
        <script src="Contenido/Assets/js/page/index.js"></script>
        <!-- Template JS File -->
        <script src="Contenido/Assets/js/scripts.js"></script>
        <script src="Contenido/Assets/js/custom.js"></script>
    </body>
</html>
