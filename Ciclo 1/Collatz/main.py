# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.


def collatz(n):
    if n % 2 == 0:
        n /= 2
        print("*", end="")
        if n != 1:
            collatz(n)
    else:
        if n == 1:
            print("")
        else:
            n = n * 3 + 1
            print("*", end="")
            if n != 1:
                collatz(n)


# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    numero = int(input('Digite un numero: '))
    print(str(numero)+" *", end="")
    collatz(numero)

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
