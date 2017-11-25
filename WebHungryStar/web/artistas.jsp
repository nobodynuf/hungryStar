<%-- 
    Document   : artistas
    Created on : 20-nov-2017, 23:18:07
    Author     : nobodynuf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="header.jsp" %>
<script>
    $(document).ready(function () {
        $('#navBar li:nth-child(1)').toggleClass('active');
        $('#navBar li:nth-child(3)').toggleClass('active');
    });
</script>
<h3>Lista Artistas</h3>
<button type="button" class="btn btn-info floating-action-button" onclick="divAgregar()">Agregar Artista</button>
<h1 id="resultado" >
    ${resultado}
</h1>
<table class="table table-striped">
    <thead>
        <tr>
            <td>
                Id
            </td>
            <td>
                Nombre
            </td>
            <td>
                &nbsp;
            </td>
            <td>
                &nbsp;
            </td>
        </tr>
    </thead>
    <c:choose>
        <c:when test="${sessionScope.usuario !=null}">
            <c:choose>
                <c:when test="${empty sessionScope.listaArtistas}">
                    <tr>
                        <td colspan="4">
                            <h3>VACIA LISTA ARTISTA</h3>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <% int i = 0;%>
                    <c:forEach items="${sessionScope.listaArtistas}" var="Artista" >

                        <tr>
                            <td id="id${Artista.id}">
                                <c:out value="${Artista.id}"></c:out>
                                </td>
                                <td id="nombre${Artista.id}">
                                <c:out value="${Artista.nombre}"></c:out>
                                </td>
                                <td>
                                    <button class="btn btn-warning" type="button"
                                            onclick="eliminarArtista(${Artista.id})">Eliminar</button>
                            </td>
                            <td>
                                <button class="btn btn-info" type="button"
                                        onclick="modificarArtista(${Artista.id})">Modificar</button>
                            </td>
                        </tr>

                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </c:when>
        <c:otherwise>
            <%
                response.sendRedirect("./home.jsp");
            %>
        </c:otherwise>
    </c:choose>
</table>

<!-- Modales!! -->

<!-- modal modificar -->
<div class="modal" id="modificar" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Modificar artista</h4>
            </div>
            <div class="modal-body">

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>ID artista</label>
                        <input class="form-control" type="text" id="txtID" name="txtID" value=""
                               readonly="yes"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Nombre artista</label>
                        <input class="form-control" type="text" name="txtNombreArtista" placeholder="Nombre artista"
                               data-validation-required-message="Ingresa un nombre" required="yes"/>
                    </div>
                </div>


            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button class="btn btn-success" type="button" onclick="ajaxModificarArtista()">VAMOS!</button>
            </div>
        </div>
    </div>
</div>
<!-- fin modal modificar -->

<!-- modal agregar -->
<div class="modal" id="agregar" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <form method="POST" action="./Artista" id="formAgregarArtista">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Agregar Artista</h4>
                </div>
                <div class="modal-body">
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Nombre artista</label>
                            <input class="form-control" type="text" name="txtNombre" id="txtNombre" placeholder="Un nombre de artista"
                                   data-validation-required-message="Ingresa el nombre del artista" required="yes"/>
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
<!-- fin modal agregar -->

<!-- modal eliminar -->
<div class="modal" id="eliminar" role="dialog">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close">&times;</button>
                <h4 class="modal-title">Confirmacion</h4>
            </div>
            <div class="modal-body">
                <p>Esta seguro que desea eliminar a
                    <br />
                    <b id="aEliminar"></b>
                    <b id="aEliminarNombre"></b>
                    ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button class="btn btn-warning" type="button" onclick="ajaxEliminarArtista()">Confirmo</button>
            </div>
        </div>
    </div>
</div>
<!-- fin modal eliminar -->

<%@include file="footer.jsp" %>
