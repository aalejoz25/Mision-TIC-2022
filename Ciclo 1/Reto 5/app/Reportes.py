import main
from app import Docentes, Estudiantes, Matriculas, Cursos


def cantidadDocentes():
    Docentes.cargarDocentes()
    cantidadDocentes = len(Docentes.docentes['docentes'])
    print("Hay %s docentes en el sistema" % (cantidadDocentes))
    input("Presione intro para continuar...")
    main.limpiarPantalla()


def cantidadEstudiantes():
    Estudiantes.cargarEstudiantes()
    cantidadEstudiantes = len(Estudiantes.estudiantes['estudiantes'])
    print("Hay %s estudiantes en el sistema" % (cantidadEstudiantes))
    input("Presione intro para continuar...")
    main.limpiarPantalla()


def matriculadosPorCurso():
    cursos = Cursos.cargarCursos()
    Matriculas.cargarMatriculas()
    Docentes.cargarDocentes()
    Estudiantes.cargarEstudiantes()
    hayDocentes = False
    hayEstudiantes = False

    for curso in cursos['cursos']:
        docentesRepetidos = []
        estudiantesRepetidos = []
        print("___" + curso['nombre'] + "___")
        print('--->' + 'Docentes en el curso:')
        for docente in Docentes.docentes['docentes']:
            for matricula in Matriculas.matriculas['matriculas']:
                if docente["codigo"] == matricula["idDocente"] and curso['codigo'] == matricula['idCurso']:
                    if docentesRepetidos == []:
                        print(docente['nombres'], docente['apellidos'])
                        docentesRepetidos.append(docente)
                        hayDocentes = True
                    else:
                        esRepetido = False
                        for docenteR in docentesRepetidos:
                            if docente == docenteR:
                                esRepetido = True
                        if esRepetido == False:
                            docentesRepetidos.append(docente)
                            print(docente['nombres'], docente['apellidos'])

        if hayDocentes == False:
            print("No hay docentes en este curso")
        print('--->' + 'Estudiantes en el curso:')
        for estudiante in Estudiantes.estudiantes['estudiantes']:
            for matricula in Matriculas.matriculas['matriculas']:
                if estudiante["codigo"] == matricula["idEstudiante"] and curso['codigo'] == matricula['idCurso']:
                    if estudiantesRepetidos == []:
                        print(estudiante['nombres'], estudiante['apellidos'])
                        estudiantesRepetidos.append(estudiante)
                        hayEstudiantes = True
                    else:
                        esRepetido = False
                        for estudianteR in estudiantesRepetidos:
                            if estudiante == estudianteR:
                                esRepetido = True
                        if esRepetido == False:
                            estudiantesRepetidos.append(estudiante)
                            print(estudiante['nombres'], estudiante['apellidos'])
        if hayEstudiantes == False:
            print("No hay estudiantes en este curso")
        hayDocentes = False
        hayEstudiantes = False
        print("")
    input('Presione intro para continuar...')
    main.limpiarPantalla()


def matriculadosPorDocente():
    cursos = Cursos.cargarCursos()
    Matriculas.cargarMatriculas()
    Docentes.cargarDocentes()
    Estudiantes.cargarEstudiantes()
    hayCursos = False

    for docente in Docentes.docentes['docentes']:
        cursosRepetidos = []

        print("___" + docente['nombres'], docente['apellidos'] + "___")
        print('Cursos que dicta el docente y sus estudiantes: ')
        for curso in cursos['cursos']:
            for matricula in Matriculas.matriculas['matriculas']:
                if curso["codigo"] == matricula["idCurso"] and docente['codigo'] == matricula['idDocente']:
                    if cursosRepetidos == []:
                        print('--->' + curso['nombre'])
                        cursosRepetidos.append(curso)
                        hayCursos = True
                    else:
                        esRepetido = False
                        for cursoR in cursosRepetidos:
                            if curso == cursoR:
                                esRepetido = True
                        if esRepetido == False:
                            cursosRepetidos.append(curso)
                            print('--->' + curso['nombre'])

                    estudiantesRepetidos = []
                    for estudiante in Estudiantes.estudiantes['estudiantes']:
                        # for matriculaE in Matriculas.matriculas['matriculas']:
                        if estudiante["codigo"] == matricula["idEstudiante"] and curso['codigo'] == matricula[
                            'idCurso']:
                            if estudiantesRepetidos == []:
                                print(estudiante['nombres'], estudiante['apellidos'])
                                estudiantesRepetidos.append(estudiante)
                            else:
                                esRepetido = False
                                for estudianteR in estudiantesRepetidos:
                                    if estudiante == estudianteR:
                                        esRepetido = True
                                if esRepetido == False:
                                    estudiantesRepetidos.append(estudiante)
                                    print(estudiante['nombres'], estudiante['apellidos'])

        if hayCursos == False:
            print("--->No hay cursos para este docente<---")
        #
        hayCursos = False

        print("")
    input('Presione intro para continuar...')
    main.limpiarPantalla()


'''   print('Estudiantes en el curso:')
        for estudiante in Estudiantes.estudiantes['estudiantes']:
            for matricula in Matriculas.matriculas['matriculas']:
                if estudiante["codigo"] == matricula["idEstudiante"] and curso['codigo'] == matricula['idCurso']:
                    if estudiantesRepetidos == []:
                        print(estudiante['nombres'], estudiante['apellidos'])
                        estudiantesRepetidos.append(estudiante)
                        hayEstudiantes = True
                    else:
                        for estudianteR in estudiantesRepetidos:
                            if estudiante != estudianteR:
                                estudiantesRepetidos.append(estudiante)
                                print(estudiante['nombres'], estudiante['apellidos'])
                                hayEstudiantes = True

        if hayEstudiantes == False:
            print("No hay docentes en este curso")
        hayDocentes = False
hayEstudiantes = False'''
