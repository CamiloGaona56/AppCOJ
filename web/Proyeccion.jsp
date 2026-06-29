
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Proyeccion.tld" prefix="Proyeccion" %>
<%@taglib uri="/WEB-INF/tlds/Alerta.tld" prefix="Alerta" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Modulo Tratamiento</title>
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/datatables.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/DataTables-1.10.16/css/dataTables.bootstrap4.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/datatables/Select-1.2.4/css/select.bootstrap4.min.css">
        <link rel="stylesheet" href="Contenido/Assets/css/main.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/izitoast/css/iziToast.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/select2/dist/css/select2.min.css" >
        <link rel="shortcut icon" href="Contenido/Imagen/DienteClauOficial.png" />
    </head>
    <body>
        <div id="app">
            <div class="main-wrapper main-wrapper-1">
                <jsp:include page="Menu.jsp"></jsp:include>
                    <div class="main-content" style="min-height: 694px;">
                    <Proyeccion:Proyeccion/>
                </div>
            </div>
        </div>
        <script type="text/javascript">
            const input = document.getElementById('currencyInput');

            input.addEventListener('input', function (e) {
                // Remover todos los caracteres que no sean números
                let value = e.target.value.replace(/\D/g, '');
                e.target.value = value; // Muestra el número en crudo sin formato
            });

            input.addEventListener('blur', function (e) {
                let value = e.target.value.replace(/\D/g, ''); // Obtener solo los números

                if (value) {
                    // Convertir el valor a formato de moneda (pesos colombianos) sin decimales
                    value = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP',
                        minimumFractionDigits: 0, // Sin decimales
                        maximumFractionDigits: 0  // Sin decimales
                    }).format(value);
                } else {
                    value = '';
                }

                e.target.value = value; // Actualizar el input con el formato
            });

            input.addEventListener('focus', function (e) {
                // Al hacer foco, cambiar el valor del input a un número entero
                let value = e.target.value.replace(/[^0-9]/g, ''); // Mantener solo números
                e.target.value = value; // Permitir la edición del número sin formato
            });
        </script>
        <script type="text/javascript">
            const input2 = document.getElementById('currencyInputAbono');

            input2.addEventListener('input', function (e) {
                // Remover todos los caracteres que no sean números
                let value = e.target.value.replace(/\D/g, '');
                e.target.value = value; // Muestra el número en crudo sin formato
            });

            input2.addEventListener('blur', function (e) {
                let value = e.target.value.replace(/\D/g, ''); // Obtener solo los números

                if (value) {
                    // Convertir el valor a formato de moneda (pesos colombianos) sin decimales
                    value = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP',
                        minimumFractionDigits: 0, // Sin decimales
                        maximumFractionDigits: 0  // Sin decimales
                    }).format(value);
                } else {
                    value = '';
                }

                e.target.value = value; // Actualizar el input con el formato
            });

            input2.addEventListener('focus', function (e) {
                // Al hacer foco, cambiar el valor del input a un número entero
                let value = e.target.value.replace(/[^0-9]/g, ''); // Mantener solo números
                e.target.value = value; // Permitir la edición del número sin formato
            });
        </script>
        <script type="text/javascript">
            const input3 = document.getElementById('currencyInputSaldo');

            input3.addEventListener('input', function (e) {
                // Remover todos los caracteres que no sean números
                let value = e.target.value.replace(/\D/g, '');
                e.target.value = value; // Muestra el número en crudo sin formato
            });

            input3.addEventListener('blur', function (e) {
                let value = e.target.value.replace(/\D/g, ''); // Obtener solo los números

                if (value) {
                    // Convertir el valor a formato de moneda (pesos colombianos) sin decimales
                    value = new Intl.NumberFormat('es-CO', {
                        style: 'currency',
                        currency: 'COP',
                        minimumFractionDigits: 0, // Sin decimales
                        maximumFractionDigits: 0  // Sin decimales
                    }).format(value);
                } else {
                    value = '';
                }

                e.target.value = value; // Actualizar el input con el formato
            });

            input3.addEventListener('focus', function (e) {
                // Al hacer foco, cambiar el valor del input a un número entero
                let value = e.target.value.replace(/[^0-9]/g, ''); // Mantener solo números
                e.target.value = value; // Permitir la edición del número sin formato
            });
        </script>
        <script>
            $(document).ready(function () {
                $('.estado-checkbox').change(function () {
                    var idProyeccion = $(this).data('id-proyeccion'); // Obtiene el IdProyeccion

                    // Determina el nuevo estado basado en si el checkbox está marcado o no
                    var estado = this.checked ? 2 : 1;

                    // Realiza la petición AJAX
                    $.ajax({
                        url: 'Proyeccion', // Reemplaza con la URL de tu servlet
                        type: 'POST',
                        data: {
                            'opc': 4, // Asegúrate de enviar el valor de opc
                            'Estado': estado,
                            'IdProyeccion': idProyeccion
                        },
                        success: function (response) {
                            // Maneja la respuesta si es necesario
                            console.log('Estado actualizado correctamente.');
                        },
                        error: function (xhr, status, error) {
                            // Maneja el error si ocurre
                            console.error('Error al actualizar el estado:', error);
                        }
                    });
                });
            });
        </script>


        <Alerta:Alerta/>
        <script src="Contenido/Assets/modules/datatables/datatables.min.js"></script>
        <script src="Contenido/Assets/modules/datatables/DataTables-1.10.16/js/dataTables.bootstrap4.min.js"></script>
        <script src="Contenido/Assets/modules/datatables/Select-1.2.4/js/dataTables.select.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-datatables.js"></script>
        <script src="Contenido/Assets/modules/select2/dist/js/select2.full.min.js"></script>
        <script src="Contenido/Assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-toastr.js"></script>
    </body>
</html>
