function divLogin() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divRegistrarse() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}
function divEliminar() {
    $('#eliminar').toggle();
}

function divModificar() {
    $('#modificar').toggle();

}
function divAgregar() {
    $('#agregar').toggle();
}

// artistas
function ajaxModificarArtista() {
    $(document).ready(function () {
//        var params = {
//            action: 'modificar',
//            nombreArtista: $('input[name=txtNombreArtista]').val(),
//            idArtista: $('input[name=txtID]').val()
//
//        };
//        var nombreArtista = $('input[name=txtNombreArtista]').val();
//        var idArtista = $('input[name=txtID]').val();
        $.ajax({
            type: 'GET',
            url: 'Artista',
            data: {
                action: 'modificar',
                nombreArtista: $('input[name=txtNombreArtista]').val(),
                idArtista: $('input[name=txtID]').val()
            },
            contentType: 'json',
            dataType: 'json'
        });
        divModificar();
        setTimeout(function () {
            location.reload(true);
        }, 300);
//        $.get('Artista', $.param(params), function () {
//            alert('modificado con exito!');
//            divModificar();
//        });
    });
}
function modificarArtista(id) {
    $(document).ready(function () {
        var i = id;
        divModificar();
        var numero = $('#id' + i).html();
        var nombre = $('#nombre' + i).html();
        numero = numero.trim();
        nombre = nombre.trim();

        $('input[name=txtID]').val(numero);
        $('input[name=txtNombreArtista]').val(nombre);

    });
}

function ajaxEliminarArtista() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'Artista',
            data: {
                action: 'eliminar',
                idArtista: $('#aEliminar').html()
            },
            contentType: 'json',
            dataType: 'json'
        });
        divEliminar();
        setTimeout(function () {
            location.reload(true);
        }, 300);
    });
}
function eliminarArtista(id) {
    $(document).ready(function () {
        var i = id;
        divEliminar();
        var numero = $('#id' + i).html();
        var nombre = $('#nombre' + i).html();
        numero = numero.trim();
        nombre = nombre.trim();

        $('#aEliminar').html(numero);
        $('#aEliminarNombre').html(nombre);

    });
}

// album
function eliminarAlbum(id) {
    $(document).ready(function () {
        var i = id;
        divEliminar();
        var numero = $('#id' + i).html();
        var nombre = $('#nombre' + i).html();
        var artista = $('#artista' + i).html();

        numero = numero.trim();
        nombre = nombre.trim();
        artista = artista.trim();

        $('#aEliminar').html(numero);
        $('#aEliminarNombre').html(nombre);
        $('#aEliminarArtista').html(artista);
    });
}

function ajaxEliminarAlbum() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'Album',
            data: {
                action: 'eliminar',
                idAlbum: $('#aEliminar').html()
            },
            contentType: 'json',
            dataType: 'json'
        });
        divEliminar();
        setTimeout(function () {
            location.reload(true);
        }, 300);
    });
}

function modificarAlbum(id) {
    $(document).ready(function () {
        var i = id;
        divModificar();
        var numero = $('#id' + i).html();
        var nombre = $('#nombre' + i).html();
        var artista = $('#artista' + i).html();

        numero = numero.trim();
        nombre = nombre.trim();
        artista = artista.trim();

        $('input[name=txtID]').val(numero);
        $('input[name=txtNombreAlbum]').val(nombre);
        $('input[name=txtArtista]').val(artista);
    });
}

function ajaxModificarAlbum() {
    $(document).ready(function () {
        $.ajax({
            type: 'GET',
            url: 'Album',
            data: {
                action: 'modificar',
                nombreAlbum: $('input[name=txtNombreAlbum]').val(),
                idAlbum: $('input[name=txtID]').val()

            },
            contentType: 'json',
            dataType: 'json'
        });
        divModificar();
        setTimeout(function () {
            location.reload(true);
        }, 300);
    });
}

//canciones
function ajaxModificarCancion() {

}
function modificarCancion(id) {

}

function ajaxEliminarCancion() {

}
function eliminarCancion() {

}

function artistaCambiado() {
    $(document).ready(function () {
        $('#listaAlbum').val(0);
        $('optgroup').attr('disabled', true).css({backgroundColor: '#fcc'});
        $('#el' + $('#listaArtistas :selected').val()).attr('disabled', false);
        $('#el' + $('#listaArtistas :selected').val()).css({backgroundColor: '#66ff66'});
    });
}
// pagina
function doReproducir() {

    $(document).ready(function () {
        
        $('#boton-reproducir').click(function () {
            $(this).find('span').toggleClass('glyphicon-play').toggleClass('glyphicon-pause');
            var r = $('#elReproductor').get(0);
            if (!r.paused) {
                r.pause();
            }else{
                r.play();
            }
        });
    });

}

function reproducir(idCancion) {
    var cosa;
    $.ajax({
        type: 'GET',
        url: 'Cancion',
        data: {
            action: 'descargar',
            idCancion: idCancion

        },
        contentType: 'json',
        dataType: 'json',
        complete: function (data) {
            alert(data.status);
            $('#elReproductor').prop('src',data.responseJSON["dato"]);
            var r = $('#elReproductor').get(0);
            doReproducir();
            $('#boton-reproducir').find('span').toggleClass('glyphicon-play').toggleClass('glyphicon-pause');
        }
    });
    
}

function atras() {
    console.log("DX");
}
function adelante() {
    console.log("xd");
}
function logout() {
    window.location.href = "./AccionLogin";
}
function cambiarTitulo(titulo) {
    document.title = titulo;
}
