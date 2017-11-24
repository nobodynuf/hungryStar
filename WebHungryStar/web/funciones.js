/* 
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
/**
 * modales! login!
 * @returns {undefined}
 */
function divLogin() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divRegistrarse() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divModificar(){
    $('#modificar').toggle();
    
}
function divAgregar(){
    $('#agregar').toggle();
}

function modificarArtista(){
    $(document).ready(function(){
        var nombreArtista = $('#txtNombreArtista').val();
        var idArtista = $('#txtID').val();
        $.ajax({
            method: "GET",
            url: "./Artista",
            data: {
                action : modificar,
                nombre: nombreArtista,
                id: idArtista
            },
            success: function(status){
                alert('funciona '+status);
                divModificar();
            }
        });
    });
}
function modificarArtista(id){
    $(document).ready(function(){
        divModificar();
        var numero = $('#id'+i).val();
        var nombre = $('#nombre'+i).val();
        
        $('#txtID').val(numero);
        $('#txtNombreArtista').val(nombre);
        
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
