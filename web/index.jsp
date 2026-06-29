
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/Alerta.tld" prefix="Alerta" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Odontologia JIRETH</title>
        <link rel="stylesheet" href="Contenido/Assets/css/login.css">
        <link rel="shortcut icon" href="Contenido/Imagen/DienteClauOficial.png" />
        <link rel="stylesheet" href="Contenido/Assets/css/main.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/fontawesome/css/all.min.css">
        <link rel="stylesheet" href="Contenido/Assets/modules/izitoast/css/iziToast.min.css">
          <script src="Contenido/Assets/modules/jquery.min.js"></script>
        <style>
            .hidden {
                display: none;
            }

            .visible {
                display: block;
            }

        </style>
    </head>
    <body>
        <div class="container">
            <input type="checkbox" id="flip">
            <div class="forms">
                <div style="text-align: center; margin-bottom: 5%">
                    <h2>Consultorio Odontologico <br/> JIRETH</h2>
                </div>
                <div class="form-content visible" id="Div1">
                    <div class="login-form">
                        <div style="text-align: center"><img src="Contenido/Imagen/DienteClauOficial.png" style="width: 34%"></div> 
                        <div class="title text-center ">Iniciar Sesion</div>
                        <form action="Sesion?opc=1" method="post">
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user ml-2"></i>
                                    <input type="number" name="Txt_documento" id="InputUno" onkeypress="EnviarDato()" placeholder="Ingrese su usuario" required>
                                </div>
                                <div class="input-box">
                                    <i class="fas fa-key ml-2"></i>
                                    <input type="password" name="Txt_contrasena" placeholder="Ingrese su contraseña" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Ingresar">
                                </div>
                                <div class="text sign-up-text">Olvido Contraseña? <label for="flip"><a onclick="mostrarConvencion(1)">Restablecer</a></label></div>
                                <div class="text sign-up-text"><b>PT 0.0.0</b></div>

                                <div  onclick="CambiarDiv(1)" class="animationImg" style="bottom: 7px; position: absolute; right: 2%;">
                                    <div style="border: 1px solid #4cbeeb; border-radius: 4px; text-align: center; padding: 0; width: 93%; box-shadow: 1px 1px 0px 2px #d1d1d1; cursor: pointer;">
                                        <img src="Contenido/Imagen/doctor.png" style="width: 54px; margin: 5px;">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
                <div class="form-content hidden" id="Div2" >
                    <div class="login-form">
                        <div style="text-align: center"><img src="Contenido/Imagen/DienteClauOficial.png" style="width: 34%"></div> 
                        <div class="title text-center ">Iniciar Sesion</div>
                        <form action="Informe?opc=2" method="post">
                            <div class="input-boxes">
                                <div class="input-box">
                                    <i class="fas fa-user ml-2"></i>
                                    <input type="hidden" name="Validacion" value="1">
                                    <input type="number" name="Documento" id="InputUno" placeholder="Ingrese documento del paciente" required>
                                </div>
                                <div class="button input-box">
                                    <input type="submit" value="Consultar">
                                </div>
                                <div onclick="CambiarDiv(2)" class="animationImg" style="bottom: 7px; position: absolute; right: 2%;">
                                    <div style="border: 1px solid #4cbeeb; border-radius: 4px; text-align: center; padding: 0; width: 93%; box-shadow: 1px 1px 0px 2px #d1d1d1; cursor: pointer;">
                                        <img src="Contenido/Imagen/DienteClauOficial.png" style="width: 54px; margin: 5px;">
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="sweet-local" tabindex="-1" id="Ventana1" style="opacity: 1.03; display:none;">
            <div class="cont_ressP">
                <div style="display: flex; justify-content: space-between">
                    <h2>Restablecer Contraseña</h2>
                    <a href="index.jsp"><button class="btn btn-outline-secondary" style="height: 30px;padding: 3px;width: 30px;"><img style="width: 52%" src="Contenido/Imagen/x.png"></button></a>
                </div>
                <div>
                    <div class="forms">
                        <div class="form-content">
                            <div class="login-form">
                                <form action="Sesion?opc=2" method="post">
                                    <div class="input-boxes">
                                        <div style="text-align: center"><b style="color: orange">Si el usuario existe, se podra cambiar la contraseña</b></div>
                                        <div class="input-box">
                                            <i class="fas fa-user ml-2"></i>
                                            <input type="text" name="Txt_documento" id="InputDos" placeholder="Ingrese su usuario" required>
                                        </div>
                                        <div class="input-box">
                                            <i class="fas fa-key ml-2"></i>
                                            <input type="text" name="Txt_contrasena" placeholder="Ingrese su nueva contraseña" required>
                                        </div>
                                        <div class="button input-box">
                                            <input type="submit" value="Restablecer">
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <Alerta:Alerta/>
        <script type="text/javascript" language="javascript">
            function mostrarConvencion(id) {
                var ventana = document.getElementById("Ventana" + id);
                if (ventana.style.display === "none" || ventana.style.display === "") {
                    ventana.style.display = "block";
                    document.body.style.overflow = "hidden"; // Para evitar el desplazamiento
                } else {
                    ventana.style.display = "none";
                    document.body.style.overflow = ""; // Permitir el desplazamiento
                }
            }

            function EnviarDato() {
                var InputUno = document.getElementById("InputUno");
                var InputDos = document.getElementById("InputDos");

                if (InputUno && InputDos) {
                    InputDos.value = InputUno.value;
                } else {
                    console.error("Uno de los elementos no se encuentra.");
                }
            }
            function CambiarDiv(Id) {
                const div1 = document.getElementById("Div1");
                const div2 = document.getElementById("Div2");

                if (Id === 1) {
                    div1.classList.remove("visible");
                    div1.classList.add("hidden");
                    div2.classList.remove("hidden");
                    div2.classList.add("visible");
                } else {
                    div2.classList.remove("visible");
                    div2.classList.add("hidden");
                    div1.classList.remove("hidden");
                    div1.classList.add("visible");
                }
            }
        </script>
        <script>
            const imageDiv = document.getElementById('myImage');

            imageDiv.addEventListener('click', function () {
                this.classList.toggle('scaled'); // Cambia la clase al hacer clic
            });
        </script>
        <script src="Contenido/Assets/modules/izitoast/js/iziToast.min.js"></script>
        <script src="Contenido/Assets/js/page/modules-toastr.js"></script>
<!--        <div style="width: 100%; bottom: 0;     background: #e6e6e6;">
            Copyri
        </div>-->
    </body>
</html>
