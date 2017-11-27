function divLogin() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divRegistrarse() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divModificar() {
    $('#modificar').toggle();

}
function divAgregar() {
    $('#agregar').toggle();
}

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
            dataType: 'json',
            success: function (status) {
                alert('funciona ' + status);
                divModificar();
            }
        });
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

function reproducir() {

    $('#boton-reproducir').click(function () {
        $(this).find('span').toggleClass('glyphicon-play').toggleClass('glyphicon-pause');
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
