create view misiontic_unab.`consulta_estudiantes_por_ciudad` AS  

select c.descripcion as "ciudad", 
	count(e.estudiante_id) as "contador estudiantes",
	group_concat(concat(e.nombre, e.apellido, e.usuario)) as "estudiantes",
	AVG(e.activo) as "promedio activos"
from misiontic_unab.reto4_estudiante as e JOIN
	misiontic_unab.reto4_ciudad as c ON  e.ciudad = c.ciudad_id
-- where c.descripcion NOT LIKE 'B%'
group by c.descripcion
having "contador estudiantes"<5 
order by c.descripcion desc
limit 20;



