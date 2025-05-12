-- drop schema alquiler; --
create schema alquiler;
use alquiler;

create table vehiculo(
	matricula varchar(7) primary key,
    precioh decimal(10,2) not null,
    fecha_matriculacion date not null,
    tipo int,
    tipo_turismo varchar(20),
    tipo_furgo varchar(20)
);

create table tipo(
	id int,
    nombre varchar(20),
    primary key(id)
);

create table mantenimiento(
    descripcion varchar(100),
    fecha datetime not null,
    matricula varchar(7),
    primary key(fecha, matricula)
);

create table rol(
	id int,
    nombre varchar(25),
    primary key(id)
);

create table usuario(
	dni varchar(9),
    nombre varchar(100),
    telef varchar(15),
    correo varchar(100),
    direccion varchar(100),
    contrasea varchar(255),
    rol int,
    primary key(dni)
);

create table alquiler(
	fechaini datetime,
    fechafin datetime,
    vehiculo varchar(7),
    cliente varchar(9),
    empleado varchar(9),
    total decimal(10,2),
    primary key(fechaini, vehiculo) 
);

create table fechasmant(
	tipo int,
    desde decimal(10,2),
    hasta decimal(10,2),
    frecuencia decimal(10,2),
    primary key(tipo, desde)
);

alter table mantenimiento 
add constraint fk1 foreign key (matricula) references vehiculo(matricula) on delete restrict on update cascade;

alter table usuario
add constraint fk2 foreign key (rol) references rol(id) on delete restrict on update cascade;

alter table alquiler
add constraint fk3 foreign key (vehiculo) references vehiculo(matricula) on delete restrict on update cascade,
add constraint fk4 foreign key (cliente) references usuario(dni) on delete restrict on update cascade,
add constraint fk5 foreign key (empleado) references usuario(dni) on delete restrict on update cascade;

alter table fechasmant
add constraint fk6 foreign key (tipo) references tipo(id) on delete restrict on update cascade;

alter table vehiculo
add constraint fk7 foreign key (tipo) references tipo(id) on delete restrict on update cascade;
