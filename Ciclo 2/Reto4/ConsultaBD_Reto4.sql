SELECT RANK() OVER (ORDER BY reto4_usuario.fecha_registro DESC) AS CONSECUTIVO, 
(CASE 
WHEN reto4_ciudad.ciudad_id IN (SELECT reto4_estudiante.ciudad FROM reto4_ciudad) THEN reto4_ciudad.descripcion
ELSE 'BOGOTÁ' END )  AS CIUDAD,
UPPER(reto4_usuario.usuario) AS LOGIN_DE_USUARIO,

(CASE 
WHEN reto4_usuario.usuario IN (SELECT reto4_profesor.usuario FROM reto4_profesor) THEN 'PROFESOR'
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) AND reto4_estudiante.activo IN (1) THEN 'ESTUDIANTE'
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) AND reto4_estudiante.activo IN (0) THEN 'EGRESADO'
ELSE 'N/A' END ) 
AS CATEGORIA,

(CASE 
WHEN reto4_usuario.usuario IN (SELECT reto4_profesor.usuario FROM reto4_profesor) THEN UPPER(CONCAT(reto4_profesor.nombre," ",reto4_profesor.apellido))
WHEN reto4_usuario.usuario IN (SELECT reto4_estudiante.usuario FROM reto4_estudiante) THEN UPPER(CONCAT(reto4_estudiante.nombre," ",reto4_estudiante.apellido))
ELSE 'NO ASIGNADO' END ) 
AS NOMBRE_COMPLETO,

reto4_usuario.fecha_registro AS FECHA
FROM reto4_usuario LEFT JOIN reto4_estudiante ON reto4_usuario.usuario=reto4_estudiante.usuario LEFT JOIN reto4_profesor ON reto4_usuario.usuario=reto4_profesor.usuario LEFT JOIN reto4_ciudad ON reto4_estudiante.ciudad = reto4_ciudad.ciudad_id;
