# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

    horaActual = int(input('Escriba la hora actual: '))
    while (horaActual >= 24) | (horaActual < 0):
        horaActual = int(input("Digite una hora valida: "))

    cantidadHoras = int(input("Digite la cantidad de horas a sumar: "))
    horaFutura=horaActual+cantidadHoras
    if horaActual+cantidadHoras<=23:
        print("La hora futura es: ",horaFutura)
    else:
        while horaFutura>23:
            horaFutura = horaFutura - 24
    print(horaFutura)




    # See PyCharm help at https://www.jetbrains.com/help/pycharm/
