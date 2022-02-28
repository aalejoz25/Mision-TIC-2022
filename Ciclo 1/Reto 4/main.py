# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.

#Globales
nombres=["Pedro", "Juan", "Diego", "Jesus", "Camila"]
cromosomas= ["00000101010101010101", "00101010101101110111", "00100010010000001001", "11000010011000100000", "00101000010110011010"]

def pedirCromosoma():
    flag = True
    while True:
        cromosoma = str(input("Ingrese secuencia: "))
        for i in cromosoma:
            if (i != "1") and (i != "0") or (len(cromosoma) != 20):
                print("Error de digitacion, intente de nuevo")
                flag = False
                break
            else:
                flag = True
        if len(cromosoma) == 20 and flag:
            break
    return cromosoma

def compararCromosoma(cromosoma):
    global cromosomas
    global nombres
    paridades = []
    porcentajeParidad = 0

    for i in cromosomas:
        for j in range(0, len(cromosoma)):
            if i[j] == cromosoma[j]:
                porcentajeParidad += 5
        paridades.append(porcentajeParidad)
        porcentajeParidad = 0
    sospechoso = nombres[paridades.index(max(paridades))]
    paridad = max(paridades)
    return sospechoso, paridad


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    cromosoma = pedirCromosoma()
    sospechoso, paridad = compararCromosoma(cromosoma)
    print("El culpable es", sospechoso, "con un parentezco de", str(paridad)+"%")
    
    input()





# See PyCharm help at https://www.jetbrains.com/help/pycharm/
