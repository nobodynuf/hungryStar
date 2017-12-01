<%-- 
    Document   : artistas
    Created on : 20-nov-2017, 23:18:07
    Author     : nobodynuf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="rsc/header.jsp" %>
<script>
    $(document).ready(function () {
        $('#navBar li:nth-child(1)').toggleClass('active');
        $('#navBar li:nth-child(2)').toggleClass('active');
        cambiarTitulo("Album");
    });
</script>
<h3>Lista Albunes</h3>
<button type="button" class="btn btn-info floating-action-button" onclick="location.href = '/doSession?session=todas&forward=Album'">
    Presiona aqui si la lista no se actualiza
</button>
<button type="button" class="btn btn-info" onclick="divAgregar()">Agregar Artista</button>
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
                Artista
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
                <c:when test="${empty sessionScope.listaAlbunes}">
                    <tr>
                        <td colspan="4">
                            <h3>VACIA LISTA ALBUNES</h3>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <% int i = 0;%>
                    <c:forEach items="${sessionScope.listaAlbunes}" var="Album" >

                        <tr>
                            <td id="id${Album.id}">
                                <c:out value="${Album.id}"></c:out>
                                </td>
                                <td id="nombre${Album.id}">
                                <c:out value="${Album.nombre}"></c:out>
                                </td>
                                <td id="artista${Album.id}">
                                <c:forEach items="${sessionScope.listaArtistas}" var="Artista">
                                    <c:if test="${Artista.id == Album.idArtista}">
                                        <c:out value="${Artista.nombre}"></c:out>
                                    </c:if>
                                </c:forEach>

                            </td>


                            </span>
                            <td>
                                <button class="btn btn-warning" type="button"
                                        onclick="eliminarAlbum(${Album.id})">Eliminar</button>
                            </td>
                            <td>
                                <button class="btn btn-info" type="button"
                                        onclick="modificarAlbum(${Album.id})">Modificar</button>
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

<!-- modales!!! -->
<!-- modal agregar -->
<div class="modal" id="agregar" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">
            <form method="POST" action="./Album" id="formAgregarAlbum">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Agregar Album</h4>
                </div>
                <div class="modal-body">
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Nombre Album</label>
                            <input class="form-control" type="text" name="txtNombre" id="txtNombre" placeholder="Un nombre de Album"
                                   data-validation-required-message="Ingresa el nombre del artista" required="yes"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Artista</label>
                            <div class="selectContainer">
                                <select class="form-control" name="listaArtistas" id="listaArtistas">
                                    <option value="0">
                                        -- Seleccione --
                                    </option>
                                    <c:forEach items="${sessionScope.listaArtistas}" var="Artista">
                                        <option value="${Artista.id}">
                                            ${Artista.nombre}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
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

<!-- modal modificar -->

<div class="modal" id="modificar" role="dialog">
    <div class="modal-dialog">

        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title">Modificar Album</h4>
            </div>
            <div class="modal-body">

                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>ID album</label>
                        <input class="form-control" type="text" id="txtID" name="txtID" value=""
                               readonly="yes"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>Nombre album</label>
                        <input class="form-control" type="text" name="txtNombreAlbum" placeholder="Nombre album"
                               data-validation-required-message="Ingresa un nombre" required="yes"/>
                    </div>
                </div>
                <div class="control-group">
                    <div class="form-group floating-label-form-group controls">
                        <label>artista</label>
                        <input class="form-control" type="text" name="txtArtista" placeholder="Artista"
                               readonly="yes" required="yes"/>
                    </div>
                </div>
                <span style="visibility: hidden" id="txtIdArtista"></span>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button class="btn btn-success" type="button" onclick="ajaxModificarAlbum()">VAMOS!</button>
            </div>
        </div>
    </div>
</div>
<!-- fin modal modificar -->

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
                    ID: <b id="aEliminar"></b>
                    Nombre album: <b id="aEliminarNombre"></b>
                    Artista: <b id="aEliminarArtista"></b>
                    ?</p>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Cancelar</button>
                <button class="btn btn-warning" type="button" onclick="ajaxEliminarAlbum()()">Confirmo</button>
            </div>
        </div>
    </div>
</div>
<!-- fin modal eliminar -->
<!-- fin modales -->
<%@include file="rsc/footer.jsp" %>
