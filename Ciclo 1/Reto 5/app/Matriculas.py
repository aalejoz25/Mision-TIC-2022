import json
import main
from app import Cursos, Docentes, Estudiantes

matriculas = {}


def buscarPorId(id):
    global matriculas
    cargarMatriculas()
    for matricula in matriculas['matriculas']:
        if matricula['id'] == id:
            return matricula
            break


def cargarMatriculas():
    global matriculas
    with open('json/Matriculas.json', 'r') as f:
        matriculas = json.load(f)


def guardarMatriculas():
    global matriculas
    with open('json/Matriculas.json', 'w') as f:
        json.dump(matriculas, f)


def crearMatricula():
    global matriculas
    cargarMatriculas()
    if len(matriculas["matriculas"]) == 0:
        ultimoCodigo = 1001
    else:
        ultimoCodigo = matriculas["matriculas"][len(matriculas["matriculas"]) - 1]["id"]
        ultimoCodigo += 1
    print("El codigo de esta matricula sera", ultimoCodigo)
    try:
        while True:
            idCurso = int(input("Ingrese el codigo del curso: "))
            curso = Cursos.buscarPorCodigo(idCurso)
            if curso is not None:
                input('Ha seleccionado el curso %s, presione intro para continuar...' % (curso['nombre']))
                break
            input("No se ha encontrado el curso, presione intro...")
            main.menuMatriculas()
        while True:
            idDocente = int(input("Ingrese el codigo del docente: "))
            docente = Docentes.buscarPorCodigo(idDocente)
            if docente is not None:
                input('Ha seleccionado el docente %s %s, presione intro para continuar...' % (
                    docente['nombres'], docente['apellidos']))
                break
            input("No se ha encontrado el docente, presione intro...")
            main.menuMatriculas()
        while True:
            idEstudiante = int(input("Ingrese el codigo del estudiante: "))
            estudiante = Estudiantes.buscarPorCodigo(idEstudiante)
            if estudiante is not None:
                input('Ha seleccionado el estudiante %s %s, presione intro para continuar...' % (
                    estudiante['nombres'], estudiante['apellidos']))
                break
            input("No se ha encontrado el estudiante, presione intro...")
            main.menuMatriculas()
    except ValueError:
        main.limpiarPantalla()
        print("Error, porfavor ingresar datos validos")
        crearMatricula()
    matricula = {
        'id': ultimoCodigo,
        'idCurso': idCurso,
        'idDocente': idDocente,
        'idEstudiante': idEstudiante,
    }
    for m in matriculas['matriculas']:
        if m['idCurso'] == matricula['idCurso'] and m['idDocente'] == matricula['idDocente'] and m['idEstudiante'] == \
                matricula['idEstudiante']:
            input("La matricula ya existe, presione intro...")
            main.limpiarPantalla()
            main.menuMatriculas()

    matriculas["matriculas"].append(matricula)
    guardarMatriculas()
    input("La matricula se ha creado, presione intro para continuar...")


def buscarMatricula():
    try:
        id = int(input("Digite el id de la matricula a buscar: "))
        matricula = buscarPorId(id)
        if matricula is not None:
            print('\nId:  %i' % matricula['id'])
            print('Id del curso asociado:  %s' % matricula['idCurso'])
            print('Id del docente asociado:  %s' % matricula['idDocente'])
            print('Id del estudiante asociado:  %s' % matricula['idEstudiante'])
            input('Presione intro para continuar...')
            main.limpiarPantalla()
        else:
            input("\nLa matricula no existe en la base de datos, presione intro para continuar...\n")
            main.limpiarPantalla()
        main.menuMatriculas()
    except ValueError:
        main.limpiarPantalla()
        print("PORFAVOR INGRESE UN DATO VALIDO\n")
        buscarMatricula()


def obtenerPosicion(id):
    cargarMatriculas()
    global matriculas
    indice = next((index for (index, d) in enumerate(matriculas['matriculas']) if d["id"] == id), None)
    return indice


def editarMatricula():
    global matriculas
    try:
        id = int(input('Ingrese el id de la matricula a editar: '))
        posicionMatricula = obtenerPosicion(id)
        matricula = buscarPorId(id)
        if matricula != None:
            print("Podra editar esta matricula")
            try:
                while True:
                    idCurso = int(input("Ingrese nuevamente el codigo del curso: "))
                    curso = Cursos.buscarPorCodigo(idCurso)
                    if curso is not None:
                        input('Ha seleccionado el curso %s, presione intro para continuar...' % (curso['nombre']))
                        break
                    input("No se ha encontrado el curso, presione intro...")
                    main.menuMatriculas()
                while True:
                    idDocente = int(input("Ingrese nuevamente el codigo del docente: "))
                    docente = Docentes.buscarPorCodigo(idDocente)
                    if docente is not None:
                        input('Ha seleccionado el docente %s %s, presione intro para continuar...' % (
                            docente['nombres'], docente['apellidos']))
                        break
                    input("No se ha encontrado el docente, presione intro...")
                    main.menuMatriculas()
                while True:
                    idEstudiante = int(input("Ingrese nuevamente el codigo del estudiante: "))
                    estudiante = Estudiantes.buscarPorCodigo(idEstudiante)
                    if estudiante is not None:
                        input('Ha seleccionado el estudiante %s %s, presione intro para continuar...' % (
                            estudiante['nombres'], estudiante['apellidos']))
                        break
                    input("No se ha encontrado el estudiante, presione intro...")
                    main.menuMatriculas()
            except ValueError:
                main.limpiarPantalla()
                print("Error, porfavor ingresar datos validos")
                editarMatricula()
            matricula = {
                'id': matriculas['matriculas'][posicionMatricula]['id'],
                'idCurso': idCurso,
                'idDocente': idDocente,
                'idEstudiante': idEstudiante,
            }
            for m in matriculas['matriculas']:
                if m['idCurso'] == matricula['idCurso'] and m['idDocente'] == matricula['idDocente'] and m[
                    'idEstudiante'] == \
                        matricula['idEstudiante']:
                    input("La matricula ya existe, presione intro...")
                    main.limpiarPantalla()
                    main.menuMatriculas()
            matriculas['matriculas'][posicionMatricula] = matricula
            guardarMatriculas()
            input("La matricula se ha editado, presione intro para continuar...")
            main.limpiarPantalla()
        else:
            input("La matricula no existe, presione intro para continuar...")
        main.menuMatriculas()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuMatriculas()


def eliminarMatricula():
    global matriculas
    try:
        id = int(input('Ingrese el id de la matricula: '))
        posicionMatricula = obtenerPosicion(id)
        matricula = buscarPorId(id)
        if matricula != None:
            matriculas['matriculas'].pop(posicionMatricula)
            guardarMatriculas()
            input("Ha eliminado la matricula %s presione intro para continuar..."%(id))
        else:
            input("La matricula no existe, presione intro para continuar...")
        main.menuMatriculas()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuMatriculas()

