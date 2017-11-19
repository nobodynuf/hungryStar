/* 
 * Here comes the license
 * 
 * Oh snap, waddup
 * 
 * (CC BY-SA 4.0) Jorge Manriquez
 */
function divLogin() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
}

function divRegistrarse() {
    $('#divLogin').toggle();
    $('#divRegistrarse').toggle();
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
