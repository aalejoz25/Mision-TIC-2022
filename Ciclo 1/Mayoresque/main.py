# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from distutils.command.config import config


def mayoresQue(x, lista):
    contador=0
    for i in lista:
        if i > x:
            contador += 1
    return contador

# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    a = 5
    l = [5,6,9,0,3,5,3,8,7]

    print("La cantidad de numeros mayores de",a,"son",mayoresQue(a,l))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
