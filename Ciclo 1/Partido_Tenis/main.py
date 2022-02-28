# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
import random


def ingresarTenistas():
    # Use a breakpoint in the code line below to debug your script.
    tenistas = []
    for i in range(0,8):
        tenista = str(input("Jugador %d: "%(i+1)))
        tenistas.append(tenista)
    print("")
    return tenistas

def enfrentarTenistas(tenistas, ronda):
    ganadores = []
    print("Ronda", ronda)
    for i in range(0,len(tenistas)-1,2):
        enfrentamiento=[tenistas[i],tenistas[i+1]]
        ganador = random.choice(enfrentamiento)
        print(enfrentamiento[0], "-",enfrentamiento[1],":", ganador)
        ganadores.append(ganador)
    print("")
    return ganadores




# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    tenistas=ingresarTenistas()
    ronda1 = enfrentarTenistas(tenistas,1)
    ronda2 = enfrentarTenistas(ronda1,2)
    ronda3 = enfrentarTenistas(ronda2,3)

    print("Campeon:", ronda3[0])
    
    input()


# See PyCharm help at https://www.jetbrains.com/help/pycharm/
