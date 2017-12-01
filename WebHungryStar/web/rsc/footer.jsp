<%-- 
    Document   : footer
    Created on : 16-nov-2017, 20:17:23
    Author     : nobodynuf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
</div>
</div>
</div>
<nav class="navbar navbar-inversed navbar-fixed-bottom">

    <div id="footer" class="text-xs-center">    
        <nav class="navbar navbar-inverse navbar-fixed-bottom">
            <div class="content-fluid">

                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li><a href=".">HungryStar</a></li>
                    </ul>
                    <ul class="nav navbar-nav navbar-center">
                        <li><a href="#" onclick="atras()"><span class="glyphicon glyphicon-backward"/></a></li>
                        <li ><a href="#" onclick="doReproducir()" id="boton-reproducir"><span class="glyphicon glyphicon-play"/></a></li>
                        <li><a href="#" onclick="adelante()"><span class="glyphicon glyphicon-forward"/></a></li>
                        <li>
                            <audio id="elReproductor" autoplay></audio>
                        </li>
                    </ul>
                    <ul class="nav navbar-nav navbar-right">
                        <c:choose>
                            <c:when test="${sessionScope.usuario == null}">
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#idLogin"><span class="glyphicon glyphicon-log-in" style="vertical-align: middle"/>&nbsp;Ingresar</a>
                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#idRegistrarse"><span class="glyphicon glyphicon-user" style="vertical-align: middle"/>&nbsp;Registrarte&nbsp;</a>
                                </li>
                            </c:when>
                            <c:otherwise>
                                <li>
                                    <a href="#"><span style="vertical-align: middle" />
                                        Bienvenido <c:out value="${sessionScope.usuario.nombre}" /></a>

                                </li>
                                <li>
                                    <a href="#" data-toggle="modal" data-target="#idLogout"><span class="glyphicon glyphicon-log-in" style="vertical-align: middle"/>&nbsp;Salir&nbsp;&nbsp;</a>
                                </li>
                            </c:otherwise>
                        </c:choose>

                    </ul>
                </div>

            </div>
        </nav> 

    </div>
</nav>
<!-- modales!! -->

<!-- modal login -->
<div class="modal" id="idLogin" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <form method="POST" action="/AccionLogin" class="">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Ingresar</h4>
                </div>
                <div class="modal-body">

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Usuario</label>
                            <input class="form-control" type="text" name="txtUsername" placeholder="No tu email"
                                   data-validation-required-message="Ingresa tu usuario"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Contraseña</label>
                            <input class="form-control" type="password" name="txtPassword" placeholder="no te confundas"
                                   data-validation-required-message="Ingresa tu contraseña"/>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-success" type="submit">
                        VAMOS!
                    </button>
                </div>
            </form>
        </div>

    </div>
</div>
<!-- fin modal login -->

<!-- modal registrarse -->
<div class="modal" id="idRegistrarse" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <form method="POST" action="/AccionRegistrarse" id="fRegistrarse">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Registrarse</h4>
                </div>
                <div class="modal-body">

                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Usuario</label>
                            <input class="form-control" type="text" name="txtUsername" placeholder="Un nombre de usuario"
                                   data-validation-required-message="Ingresa tu usuario" required="yes"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Contraseña</label>
                            <input class="form-control" type="text" name="txtPassword" placeholder="Algo seguro?"
                                   data-validation-required-message="Ingresa tu contraseña" required="yes"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Nombre</label>
                            <input class="form-control" type="text" name="txtNombre" placeholder="Ingresa tu nombre"
                                   data-validation-required-message="Ingresa tu nombre" required="yes"/>
                        </div>
                    </div>

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                    <button class="btn btn-success" type="submit">VAMOS!</button>
                </div>
            </form>
        </div>

    </div>
</div>
<!-- fin modal registrarse -->

<!-- modal destruir sesion -->
<div class="modal" id="idLogout" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close">&times;</button>
                <h4 class="modal-title">Confirmacion</h4>
            </div>
            <div class="modal-body">
                <p>¿¿¿¿Esta seguro que desea salir? ??? '?? </p>
                <b>?</b>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button class="btn btn-success" type="button" onclick="logout()" >Confirmo</button>
            </div>
        </div>
    </div>
</div>
<!-- fin modal destruir sesion -->

</html>

