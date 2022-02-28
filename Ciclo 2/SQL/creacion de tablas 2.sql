CREATE TABLE misiontic_unab.`reto4_estudiante`(
estudiante_id int not null primary key,
nombre varchar(255) not null,
apellido varchar(45) not null,
ciudad int not null,
usuario varchar(16) not null,
activo tinyint(1) not null default 0,
foreign key (ciudad) references
	misiontic_unab.reto4_ciudad (ciudad_id)
    on update restrict
    on delete restrict,    
foreign key (usuario) references
	misiontic_unab.reto4_usuario (usuario)
    on update restrict
    on delete restrict    
);

