CREATE TABLE misiontic_unab.`reto4_profesor`(
profesor_id int(11) not null primary key,
nombre varchar(45) not null,
apellido varchar(45) not null,
usuario varchar(45) not null,
foreign key (usuario) references
	misiontic_unab.reto4_usuario (usuario)
    on update restrict
    on delete restrict    
);
