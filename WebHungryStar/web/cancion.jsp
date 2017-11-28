<%-- 
    Document   : cancion
    Created on : 25-nov-2017, 22:56:30
    Author     : nobodynuf
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="rsc/header.jsp" %>
<script>
    $(document).ready(function () {
        $('#navBar li:nth-child(1)').toggleClass('active');
        $('#navBar li:nth-child(4)').toggleClass('active');


    });
</script>
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
                Artista
            </td>
            <td>
                Album
            </td>
            <td>
                Reproducir
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
                <c:when test="${empty sessionScope.listaCanciones}">
                    <tr>
                        <td colspan="4">
                            <h3>VACIA LISTA CANCIONES</h3>
                        </td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <% int i = 0;%>
                    <c:forEach items="${sessionScope.listaCanciones}" var="Cancion" >

                        <tr>
                            <td id="id${Cancion.id}">
                                <c:out value="${Album.id}"></c:out>
                                </td>
                                <td id="nombre${Album.id}">
                                <c:out value="${Album.nombre}"></c:out>
                                </td>
                                <td id="artista${Cancion.id}">
                                <c:forEach items="${sessionScope.listaArtistas}" var="Artista">
                                    <c:if test="${Artista.id == Cancion.idArtista}">
                                        <c:out value="${Artista.nombre}"></c:out>
                                    </c:if>
                                </c:forEach>

                            </td>
                            <td id="album${Cancion.id}">
                                <c:forEach items="${sessionScope.listaAlbunes}" var="Album">
                                    <c:if test="${Album.id == Cancion.idAlbum}">
                                        <c:out value="${Album.nombre}"></c:out>
                                    </c:if>
                                </c:forEach>

                            </td>

                            <td>
                                <a href="#" onclick="reproducir(${Cancion.id})">REPRODUCIR</a>
                            </td>
                            <td>
                                <button class="btn btn-warning" type="button"
                                        onclick="eliminarCancion(${Cancion.id})">Eliminar</button>
                            </td>
                            <td>
                                <button class="btn btn-info" type="button"
                                        onclick="modificarCancion(${Cancion.id})">Modificar</button>
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
            <form method="post" action="/Cancion" id="formAgregarCancion" enctype="multipart/form-data">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <h4 class="modal-title">Agregar Cancion</h4>
                </div>
                <div class="modal-body">
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Nombre Album</label>
                            <input class="form-control" type="text" name="txtNombre" id="txtNombre" placeholder="Un nombre de Cancion"
                                   data-validation-required-message="Ingresa el nombre de la cancion" required="yes"/>
                        </div>
                    </div>
                    <div class="control-group">
                        <div class="form-group floating-label-form-group controls">
                            <label>Artista</label>
                            <div class="selectContainer">
                                <select class="form-control"  name="listaArtistas" id="listaArtistas">
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
                        <div class="control-group">
                            <div class="form-group floating-label-form-group controls">
                                <label>Album</label>
                                <div class="selectContainer">
                                    <select class="form-control" name="listaAlbum" id="listaAlbum">
                                        <option value="0">
                                            -- Seleccione --
                                        </option>
                                        <c:forEach items="${sessionScope.listaAlbunes}" var="Album">
                                            <option value="${Album.id}">
                                                <c:out value="${Album.nombre}"></c:out>

                                                </option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                        </div>

                        <div class="control-group">
                            <div class="form-group floating-label-form-group controls">
                                <label>Archivo</label>
                                <input class="form-control" type="file" name="fileArchivo" id="fileArchivo"
                                       data-validation-required-message="Selecciona un archivo" required="yes"/>
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


<%@include file="rsc/footer.jsp" %>

