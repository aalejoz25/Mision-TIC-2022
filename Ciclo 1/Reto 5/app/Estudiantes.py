import json
from builtins import next
import main
from app import Matriculas

estudiantes = {}


def cargarEstudiantes():
    global estudiantes
    with open('json/Estudiantes.json', 'r') as f:
        estudiantes = json.load(f)


def guardarEstudiantes():
    global estudiantes
    with open('json/Estudiantes.json', 'w') as f:
        json.dump(estudiantes, f)


def crearEstudiante():
    global estudiantes
    cargarEstudiantes()
    if len(estudiantes["estudiantes"]) == 0:
        ultimoCodigo = 1001
    else:
        ultimoCodigo = estudiantes["estudiantes"][len(estudiantes["estudiantes"]) - 1]["codigo"]
        ultimoCodigo += 1
    print("El codigo de este estudiante sera", ultimoCodigo)
    nombres = str(input("Ingrese los nombres: "))
    apellidos = str(input("Ingrese los apellidos: "))
    telefono = str(input("Ingrese un telefono: "))
    direccion = str(input("Ingrese la direccion: "))
    estudiante = {
        'codigo': ultimoCodigo,
        'nombres': nombres[0].upper() + nombres[1:len(nombres)].lower(),
        'apellidos': apellidos[0].upper() + apellidos[1:len(apellidos)].lower(),
        'telefono': telefono,
        'direccion': direccion,
    }
    estudiantes["estudiantes"].append(estudiante)
    guardarEstudiantes()
    input("El estudiante se ha creado, presione intro para continuar...")


def buscarPorCodigo(codigo):
    global estudiantes
    cargarEstudiantes()
    for estudiante in estudiantes['estudiantes']:
        if estudiante['codigo'] == codigo:
            return estudiante
            break


def obtenerPosicion(codigo):
    cargarEstudiantes()
    global estudiantes
    indice = next((index for (index, d) in enumerate(estudiantes['estudiantes']) if d["codigo"] == codigo), None)
    return indice


def buscarPorNombre(nombre):
    global estudiantes
    cargarEstudiantes()
    estudiantesPorNombre = {}
    estudiantesPorNombre["estudiantes"] = []
    for estudiante in estudiantes['estudiantes']:
        if estudiante['nombres'].casefold() == nombre.casefold():
            estudiantesPorNombre['estudiantes'].append(estudiante)
    return estudiantesPorNombre


def buscarPorApellido(apellido):
    global estudiantes
    cargarEstudiantes()
    estudiantesPorApellido = {}
    estudiantesPorApellido["estudiantes"] = []
    for estudiante in estudiantes['estudiantes']:
        if estudiante['apellidos'].casefold() == apellido.casefold():
            estudiantesPorApellido['estudiantes'].append(estudiante)
    return estudiantesPorApellido


def buscarEstudiante():
    print("Seleccione la forma en la que desea realizar la busqueda del estudiante: \n")
    print("1. Codigo")
    print("2. Nombre")
    print("3. Apellido")
    print("4. Limpiar pantalla")
    print("5. Regresar")

    try:
        opcion = int(input("\nIngrese el numero de la opcion: "))
        while opcion < 1 or opcion > 5:
            print("\nSeleccione una opcion valida")
            opcion = int(input("\nSeleccione opcion: "))
        if opcion == 1:
            codigo = int(input("Digite el codigo del estudiante: "))
            estudiante = buscarPorCodigo(codigo)
            if estudiante != None:
                print('\nCodigo:  %i' % estudiante['codigo'])
                print('Nombres:  %s' % estudiante['nombres'])
                print('Apellidos:  %s' % estudiante['apellidos'])
                print('Telefono:  %s' % estudiante['telefono'])
                print('Direccion:  %s' % estudiante['direccion'])
                input('Presione intro para continuar...')
            else:
                input("\nEl estudiante no existe en la base de datos, presione intro para continuar...\n")
            main.limpiarPantalla()
            buscarEstudiante()
        elif opcion == 2:
            nombre = str(input("Digite el nombre del estudiante: "))
            estudiantes = buscarPorNombre(nombre)
            if estudiantes['estudiantes'] != []:
                for estudiante in estudiantes['estudiantes']:
                    print('Codigo:  %i' % estudiante['codigo'])
                    print('Nombres:  %s' % estudiante['nombres'])
                    print('Apellidos:  %s' % estudiante['apellidos'])
                    print('Telefono:  %s' % estudiante['telefono'])
                    print('Direccion:  %s \n' % estudiante['direccion'])
                input('Presione intro para continuar...')
            else:
                input("\nEl estudiante no existe en la base de datos, presione intro para continuar...\n")
            main.limpiarPantalla()
            buscarEstudiante()
        elif opcion == 3:
            apellido = str(input("Digite el apellido del estudiante: "))
            estudiantes = buscarPorApellido(apellido)
            if estudiantes['estudiantes'] != []:
                for estudiante in estudiantes['estudiantes']:
                    print('Codigo:  %i' % estudiante['codigo'])
                    print('Nombres:  %s' % estudiante['nombres'])
                    print('Apellidos:  %s' % estudiante['apellidos'])
                    print('Telefono:  %s' % estudiante['telefono'])
                    print('Direccion:  %s' % estudiante['direccion'])
                input('Presione intro para continuar...')
            else:
                input("\nEl estudiante no existe en la base de datos, presione intro para continuar...\n")
                main.limpiarPantalla()
            buscarEstudiante()
        elif opcion == 4:
            main.limpiarPantalla()
            buscarEstudiante()
        elif opcion == 5:
            main.limpiarPantalla()
            main.menuEstudiantes()
    except ValueError:
        main.limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        buscarEstudiante()


def editarEstudiante():
    global estudiantes
    try:
        codigo = int(input('Ingrese el codigo del estudiante: '))
        posicionEstudiante = obtenerPosicion(codigo)
        estudiante = buscarPorCodigo(codigo)
        if estudiante != None:
            print("Editará al estudiante %s %s\n" % (estudiante['nombres'], estudiante['apellidos']))
            nombres = str(input("Ingrese nuevamente los nombres: "))
            apellidos = str(input("Ingrese nuevamente los apellidos: "))
            telefono = str(input("Ingrese nuevamente un telefono: "))
            direccion = str(input("Ingrese nuevamente la direccion: "))
            estudiantes['estudiantes'][posicionEstudiante] = {
                'codigo': estudiantes['estudiantes'][posicionEstudiante]['codigo'],
                'nombres': nombres[0].upper() + nombres[1:len(nombres)].lower(),
                'apellidos': apellidos[0].upper() + apellidos[1:len(apellidos)].lower(),
                'telefono': telefono,
                'direccion': direccion,
            }
            guardarEstudiantes()
            input("El estudiante se ha editado, presione intro para continuar...")
            main.limpiarPantalla()
        else:
            input("El estudiante no existe, presione intro para continuar...")
        main.menuEstudiantes()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuEstudiantes()


def eliminarEstudiante():
    global estudiantes
    try:
        codigo = int(input('Ingrese el codigo del estudiante: '))
        posicionEstudiante = obtenerPosicion(codigo)
        estudiante = buscarPorCodigo(codigo)
        if estudiante != None:
            Matriculas.cargarMatriculas()
            for matricula in Matriculas.matriculas['matriculas']:
                if matricula['idEstudiante'] == codigo:
                    input(
                        "No puede eliminar el estudiante %s %s, esta asociado a alguna matricula, presione intro para continuar..." % (
                            estudiante['nombres'], estudiante['apellidos']))
                    main.limpiarPantalla()
                    main.menuEstudiantes()
                    break
            estudiantes['estudiantes'].pop(posicionEstudiante)
            guardarEstudiantes()
            input("Ha eliminado el estudiante %s %s presione intro para continuar..." % (estudiante['nombres'],estudiante['apellidos']))
        else:
            input("El estudiante no existe, presione intro para continuar...")
        main.menuEstudiantes()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuEstudiantes()
