import json

cursos={}

def cargarCursos():
    with open('json/Cursos.json', 'r') as f:
        cursos = json.load(f)
    return cursos

def buscarPorCodigo(codigo):
    global cursos
    cursos = cargarCursos()
    for curso in cursos['cursos']:
        if curso['codigo'] == codigo:
            return curso
            break