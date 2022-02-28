# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import os
from app import Docentes, Estudiantes, Matriculas, Reportes


def limpiarPantalla():
    if os.name == "nt":
        os.system("cls")
    else:
        os.system("clear")


def menuDocentes():
    print("___MENÚ DOCENTES___\n")
    print("1. Crear docente")
    print("2. Buscar docente")
    print("3. Editar docente")
    print("4. Eliminar docente")
    print("5. Limpiar pantalla")
    print("6. Regresar")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 6:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))
        if opcion == 1:
            limpiarPantalla()
            Docentes.crearDocente()
            limpiarPantalla()
            menuDocentes()
        elif opcion == 2:
            limpiarPantalla()
            Docentes.buscarDocente()
            menuDocentes()
        elif opcion == 3:
            Docentes.editarDocente()
            menuDocentes()
        elif opcion == 4:
            limpiarPantalla()
            Docentes.eliminarDocente()
            menuDocentes()
        elif opcion == 5:
            limpiarPantalla()
            menuDocentes()
        elif opcion == 6:
            limpiarPantalla()
            menuPrincipal()
    except ValueError:
        limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        menuDocentes()


def menuEstudiantes():
    print("___MENÚ ESTUDIANTES___\n")
    print("1. Crear estudiante")
    print("2. Buscar estudiante")
    print("3. Editar estudiante")
    print("4. Eliminar estudiante")
    print("5. Limpiar pantalla")
    print("6. Regresar")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 6:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))
        if opcion == 1:
            limpiarPantalla()
            Estudiantes.crearEstudiante()
            print("El estudiante se ha creado")
            limpiarPantalla()
            menuEstudiantes()
        elif opcion == 2:
            limpiarPantalla()
            Estudiantes.buscarEstudiante()
            menuEstudiantes()
        elif opcion == 3:
            Estudiantes.editarEstudiante()
            menuEstudiantes()
        elif opcion == 4:
            limpiarPantalla()
            Estudiantes.eliminarEstudiante()
            menuEstudiantes()
        elif opcion == 5:
            limpiarPantalla()
            menuEstudiantes()
        elif opcion == 6:
            limpiarPantalla()
            menuPrincipal()
    except ValueError:
        limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        menuEstudiantes()


def menuMatriculas():
    print("___MENÚ MATRICULAS___\n")
    print("1. Matricular")
    print("2. Buscar matricula")
    print("3. Editar matricula")
    print("4. Eliminar matricula")
    print("5. Limpiar pantalla")
    print("6. Regresar")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 6:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))
        if opcion == 1:
            limpiarPantalla()
            Matriculas.crearMatricula()
            menuMatriculas()
        elif opcion == 2:
            limpiarPantalla()
            Matriculas.buscarMatricula()
            menuMatriculas()
        elif opcion == 3:
            limpiarPantalla()
            Matriculas.editarMatricula()
            menuMatriculas()
        elif opcion == 4:
            limpiarPantalla()
            Matriculas.eliminarMatricula()
            menuMatriculas()
        elif opcion == 5:
            limpiarPantalla()
            menuMatriculas()
        elif opcion == 6:
            limpiarPantalla()
            menuPrincipal()
    except ValueError:
        limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        menuMatriculas()


def menuReportes():
    print("___MENÚ REPORTES___\n")
    print("1. Cantidad de docentes en el sistema")
    print("2. Cantidad de estudiantes en el sistema")
    print("3. Listado de matriculados por curso")
    print("4. Listado de matriculados por docente")
    print("5. Limpiar pantalla")
    print("6. Regresar")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 6:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))

        if opcion == 1:
            Reportes.cantidadDocentes()
            menuReportes()
        elif opcion == 2:
            Reportes.cantidadEstudiantes()
            menuReportes()
        elif opcion == 3:
            Reportes.matriculadosPorCurso()
            print("Se ha reportado matriculados por curso")
            menuReportes()
        elif opcion == 4:
            Reportes.matriculadosPorDocente()
            print("Se ha reportado matriculados por docente")
            menuReportes()
        elif opcion == 5:
            limpiarPantalla()
            menuPrincipal()
        elif opcion == 6:
            limpiarPantalla()
            menuPrincipal()
    except ValueError:
        limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        menuReportes()


def guardarDatos():
    Docentes.cargarDocentes()
    Estudiantes.cargarEstudiantes()
    Matriculas.cargarMatriculas()
    Docentes.guardarDocentes()
    Estudiantes.guardarEstudiantes()
    Matriculas.guardarMatriculas()
    input("Se han guardado los datos, presione intro para continuar...")


def salir():
    exit()


def menuPrincipal():
    print("___MENÚ PRINCIPAL___\n")
    print("1. Docentes")
    print("2. Estudiantes")
    print("3. Matrículas")
    print("4. Reportes")
    print("5. Guardar")
    print("6. Limpiar pantalla")
    print("7. Salir")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 7:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))
        if opcion == 1:
            limpiarPantalla()
            menuDocentes()
        elif opcion == 2:
            limpiarPantalla()
            menuEstudiantes()
        elif opcion == 3:
            limpiarPantalla()
            menuMatriculas()
        elif opcion == 4:
            limpiarPantalla()
            menuReportes()
        elif opcion == 5:
            guardarDatos()
            limpiarPantalla()
            menuPrincipal()
        elif opcion == 6:
            limpiarPantalla()
            menuPrincipal()
        elif opcion == 7:
            salir()
    except ValueError:
        limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        menuPrincipal()


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    menuPrincipal()
