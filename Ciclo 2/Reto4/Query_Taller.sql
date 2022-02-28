SELECT rh_tables.employees.last_name AS 'Employee Last Name', 
rh_tables.employees.department_id AS "Department Number"
FROM rh_tables.employees 
WHERE rh_tables.employees.employee_id = 176;

