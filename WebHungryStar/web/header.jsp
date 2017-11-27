<%-- 
    Document   : header
    Created on : 19-nov-2017, 11:57:25
    Author     : nobodynuf
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://code.jquery.com/jquery-3.2.1.js"
        ></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.3/umd/popper.min.js" integrity="sha384-vFJXuSJphROIrBnz7yo7oB41mKfc8JzQZiCq4NCceLEaO4IHwicKwpJf9c9IpFgh" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

        <script src="funciones.js"></script>
        <link rel="stylesheet" href="estilo.css"><link/>
    </head>

    <body>

        <!-- la cosa de arriba -->
        <div class="container-fluid" style="background-color:#2196F3;color:#fff;height:100px;">

            <h3 style="text-align: center">Recuerda que hungryArtist no es responsable por el contenido
                que subas a la pagina, y la responsabilidad es exclusiva del usuario.
            </h3>
            <h6 style="text-align: right">(c) Team HungryArtist</h6>


        </div>
        <br>

        <div class="container">
            <div class="row">
                <nav class="col-sm-3">
                    <ul class="nav nav-pills nav-stacked" data-spy="affix" data-offset-top="205">
                        <li class="active"><a href="#section1">Home</a></li>
                        <li><a href="#section2">Albunes</a></li>
                        <li><a href="./Artista?action=listar">Artistas</a></li>
                        <li><a href="#section4">Canciones</a></li>
                    </ul>
                </nav>

                <!-- contenedor con columnas de tamaño 9-->
                <div class="col-sm-9">   


