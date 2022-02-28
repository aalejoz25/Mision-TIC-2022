# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def ingresarLista():
    numeroDatos = int(input('Cunatos datos ingresara? '))
    contador = 0
    datos=[]
    while contador < numeroDatos:
        dato = float(input("Dato %d: " % (contador+1) ))
        datos.append(dato)
        contador += 1
    return datos

def calcularPromedio(lista):
    promedio=sum(lista)/len(lista)
    return promedio

def mayoresaPromedio(lista,promedio):
    contador = 0
    for i in lista:
        if i > promedio:
            contador += 1
    print(contador,"datos son mayores que el promedio")

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    lista = ingresarLista()
    promedio = calcularPromedio(lista)
    mayoresaPromedio(lista, promedio)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
