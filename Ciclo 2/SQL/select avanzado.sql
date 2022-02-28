select c.descripcion as "ciudad", e.estudiante_id, e.nombre, e.apellido, e.usuario,
e.activo
from misiontic_unab.reto4_estudiante as e,
misiontic_unab.reto4_ciudad as c
where e.ciudad = c.ciudad_id
order by c.descripcion desc
limit 8 ;
