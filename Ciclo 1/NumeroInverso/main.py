# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def print_hi(name):
    # Use a breakpoint in the code line below to debug your script.
    print(f'Hi, {name}')  # Press Ctrl+F8 to toggle the breakpoint.


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print_hi('PyCharm')

    num = input('digite el numero: ')
    longitudCadena = len(num)
    numeroInvertido = ''

    for i in range(longitudCadena - 1, -1, -1):
        numeroInvertido = numeroInvertido + num[i]

    numeroInvertido = int(numeroInvertido)
    print(numeroInvertido)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
