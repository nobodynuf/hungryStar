--usar usuario:password prueba:prueba
create database hungryStar;
use hungryStar;

create table usuario(
	id int PRIMARY KEY AUTO_INCREMENT,
    username varchar(25) not null,
    pass varchar(32) not null,
    nombre varchar(50)
);

create table artista(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) not null
);

create table album(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) not null,
    id_artista int not null,
    FOREIGN KEY fk_album_artista(id_artista) references artista(id)
);

create table cancion(
	id int PRIMARY KEY AUTO_INCREMENT,
    nombre varchar(50) not null,
	dato mediumblob not null,
    id_album int,
    id_artista int not null,
    foreign KEY fk_cancion_album(id_album) REFERENCES album(id),
    FOREIGN KEY fk_cancion_artista(id_artista) REFERENCES artista(id)
);
create table log(
	fecha_ingreso datetime not null,
    usuario_ingreso int not null,
    constraint fk_log_usuario FOREIGN key(usuario_ingreso) REFERENCES usuario(id),
    constraint pk_log primary key(fecha_ingreso,usuario_ingreso)
);

alter table cancion add id_usuario int not null, add constraint fk_cancion_usuario foreign key(id_usuario) references usuario(id);