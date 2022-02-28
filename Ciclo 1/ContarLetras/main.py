# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
letras = ["a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"]
vocales = ["a","e","i","o","u",]

def contarLetras(cadena):
    global letras
    contador=0
    caracteres={}
    for letra in letras:
        for i in cadena:
            if i.upper() == letra.upper():
                contador += 1
                caracteres[letra] = contador
        contador = 0
    return caracteres

def contarVocales(cadena):
    global vocales
    contador=0
    caracteres={}
    for vocal in vocales:
        for i in cadena:
            if i.upper() == vocal.upper():
                contador += 1
            caracteres[vocal] = contador
        contador = 0
    return caracteres







# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(contarLetras("El elefante avanza hacia Asia"))
    print(contarVocales("El elefante avanza hacia Asia"))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
