import json
from builtins import next
import main
from app import Matriculas

docentes = {}


def cargarDocentes():
    global docentes
    with open('json/Docentes.json', 'r') as f:
        docentes = json.load(f)


def guardarDocentes():
    global docentes
    with open('json/Docentes.json', 'w') as f:
        json.dump(docentes, f)


def crearDocente():
    global docentes
    cargarDocentes()
    if len(docentes["docentes"]) == 0:
        ultimoCodigo = 1001
    else:
        ultimoCodigo = docentes["docentes"][len(docentes["docentes"]) - 1]["codigo"]
        ultimoCodigo += 1
    print("El codigo de este docente sera", ultimoCodigo)
    nombres = str(input("Ingrese los nombres: "))
    apellidos = str(input("Ingrese los apellidos: "))
    telefono = str(input("Ingrese un telefono: "))
    direccion = str(input("Ingrese la direccion: "))
    tituloProfesional = str(input("Titulo profesional del docente: "))
    nivelEstudios = str(input("Nivel de estudios del docente: "))
    docente = {
        'codigo': ultimoCodigo,
        'nombres': nombres[0].upper() + nombres[1:len(nombres)].lower(),
        'apellidos': apellidos[0].upper() + apellidos[1:len(apellidos)].lower(),
        'telefono': telefono,
        'direccion': direccion,
        'tituloProfesional': tituloProfesional[0].upper() + tituloProfesional[1:len(tituloProfesional)].lower(),
        'nivelEstudios': nivelEstudios[0].upper() + nivelEstudios[1:len(nivelEstudios)].lower()
    }
    docentes["docentes"].append(docente)
    guardarDocentes()
    input("El docente se ha creado, presione intro para continuar...")


def buscarPorCodigo(codigo):
    global docentes
    cargarDocentes()
    for docente in docentes['docentes']:
        if docente['codigo'] == codigo:
            return docente
            break


def obtenerPosicion(codigo):
    cargarDocentes()
    global docentes
    indice = next((index for (index, d) in enumerate(docentes['docentes']) if d["codigo"] == codigo), None)
    return indice


def buscarPorNombre(nombre):
    global docentes
    cargarDocentes()
    docentesPorNombre = {}
    docentesPorNombre["docentes"] = []
    for docente in docentes['docentes']:
        if docente['nombres'].casefold() == nombre.casefold():
            docentesPorNombre['docentes'].append(docente)
    return docentesPorNombre


def buscarPorApellido(apellido):
    global docentes
    cargarDocentes()
    docentesPorApellido = {}
    docentesPorApellido["docentes"] = []
    for docente in docentes['docentes']:
        if docente['apellidos'].casefold() == apellido.casefold():
            docentesPorApellido['docentes'].append(docente)
    return docentesPorApellido


def buscarDocente():
    print("Seleccione la forma en la que desea realizar la busqueda del docente: \n")
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
            codigo = int(input("Digite el codigo del docente: "))
            docente = buscarPorCodigo(codigo)
            if docente is not None:
                print('\nCodigo:  %i' % docente['codigo'])
                print('Nombres:  %s' % docente['nombres'])
                print('Apellidos:  %s' % docente['apellidos'])
                print('Telefono:  %s' % docente['telefono'])
                print('Direccion:  %s' % docente['direccion'])
                print('Titulo Profesional:  %s' % docente['tituloProfesional'])
                print('Nivel de Estudios:  %s\n' % docente['nivelEstudios'])
                input('Presione intro para continuar...')
                main.limpiarPantalla()
            else:
                input("\nEl docente no existe en la base de datos, presione intro para continuar...\n")
                main.limpiarPantalla()
            buscarDocente()
        elif opcion == 2:
            nombre = str(input("Digite el nombre del docente: "))
            docentes = buscarPorNombre(nombre)
            if docentes['docentes'] != []:
                for docente in docentes['docentes']:
                    print('Codigo:  %i' % docente['codigo'])
                    print('Nombres:  %s' % docente['nombres'])
                    print('Apellidos:  %s' % docente['apellidos'])
                    print('Telefono:  %s' % docente['telefono'])
                    print('Direccion:  %s' % docente['direccion'])
                    print('TituloProfesional:  %s' % docente['tituloProfesional'])
                    print('Nivel de Estudios:  %s\n' % docente['nivelEstudios'])
                input('Presione intro para continuar...')
            else:
                input("\nEl docente no existe en la base de datos, presione intro para continuar...\n")
            main.limpiarPantalla()
            buscarDocente()
        elif opcion == 3:
            apellido = str(input("Digite el apellido del docente: "))
            docentes = buscarPorApellido(apellido)
            if docentes['docentes'] != []:
                for docente in docentes['docentes']:
                    print('Codigo:  %i' % docente['codigo'])
                    print('Nombres:  %s' % docente['nombres'])
                    print('Apellidos:  %s' % docente['apellidos'])
                    print('Telefono:  %s' % docente['telefono'])
                    print('Direccion:  %s' % docente['direccion'])
                    print('TituloProfesional:  %s' % docente['tituloProfesional'])
                    print('Nivel de Estudios:  %s\n' % docente['nivelEstudios'])
                input('Presione intro para continuar...')
            else:
                input("\nEl docente no existe en la base de datos, presione intro para continuar...\n")
            main.limpiarPantalla()
            buscarDocente()
        elif opcion == 4:
            main.limpiarPantalla()
            buscarDocente()
        elif opcion == 5:
            main.limpiarPantalla()
            main.menuDocentes()
    except ValueError:
        main.limpiarPantalla()
        print("PORFAVOR SELECCIONE UNA OPCION VALIDA\n")
        buscarDocente()



def editarDocente():
    global docentes
    try:
        codigo = int(input('Ingrese el codigo del docente: '))
        posicionDocente = obtenerPosicion(codigo)
        docente = buscarPorCodigo(codigo)
        if docente != None:
            print("Editará al docente %s %s\n" % (docente['nombres'], docente['apellidos']))
            nombres = str(input("Ingrese nuevamente los nombres: "))
            apellidos = str(input("Ingrese nuevamente los apellidos: "))
            telefono = str(input("Ingrese nuevamente un telefono: "))
            direccion = str(input("Ingrese nuevamente la direccion: "))
            tituloProfesional = str(input("Ingrese nuevamente titulo profesional del docente: "))
            nivelEstudios = str(input("Ingrese nuevamente nivel de estudios del docente: "))
            docentes['docentes'][posicionDocente] = {
                'codigo': docentes['docentes'][posicionDocente]['codigo'],
                'nombres': nombres[0].upper() + nombres[1:len(nombres)].lower(),
                'apellidos': apellidos[0].upper() + apellidos[1:len(apellidos)].lower(),
                'telefono': telefono,
                'direccion': direccion,
                'tituloProfesional': tituloProfesional[0].upper() + tituloProfesional[1:len(tituloProfesional)].lower(),
                'nivelEstudios': nivelEstudios[0].upper() + nivelEstudios[1:len(nivelEstudios)].lower()
            }
            guardarDocentes()
            input("El docente se ha editado, presione intro para continuar...")
            main.limpiarPantalla()
        else:
            input("El docente no existe, presione intro para continuar...")
        main.menuDocentes()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuDocentes()


def eliminarDocente():
    global estudiantes
    try:
        codigo = int(input('Ingrese el codigo del docente: '))
        posicionDocente = obtenerPosicion(codigo)
        docente = buscarPorCodigo(codigo)
        if docente != None:
            Matriculas.cargarMatriculas()
            for matricula in Matriculas.matriculas['matriculas']:
                if matricula['idDocente'] == codigo:
                    input(
                        "No puede eliminar el doente %s %s, esta asociado a alguna matricula, presione intro para continuar..." % (
                            docente['nombres'], docente['apellidos']))
                    main.limpiarPantalla()
                    main.menuDocentes()
                    break
            docentes['docentes'].pop(posicionDocente)
            guardarDocentes()
            input("Ha eliminado el docente %s %s presione intro para continuar..." % (
            docente['nombres'], docente['apellidos']))
        else:
            input("El docente no existe, presione intro para continuar...")
        main.menuDocentes()
    except ValueError:
        input("Error ingrese un valor valido, presione intro para continuar...")
        main.menuDocentes()


