# This is a sample Python script.

# Press Mayús+F10 to execute it or replace it with your code.
# Press Double Shift to search everywhere for classes, files, tool windows, actions, and settings.
from wsgiref.validate import validator

productos = [
    (41419, 'Fideos',        450, 210),
    (70717, 'Cuaderno',      900, 119),
    (78714, 'Jabon',         730, 708),
    (30877, 'Desodorante',  2190,  79),
    (47470, 'Yogur',          99, 832),
    (50809, 'Palta',         500,  55),
    (75466, 'Galletas',      235,   0),
    (33692, 'Bebida',        700,  20),
    (89148, 'Arroz',         900, 121),
    (66194, 'Lapiz',         120, 900),
    (15982, 'Vuvuzela',    12990,  40),
    (41235, 'Chocolate',    3099,  48),
]

clientes = [
    ('11652624-7', 'Perico Los Palotes'),
    ( '8830268-0', 'Leonardo Farkas'),
    ( '7547896-8', 'Fulanita de Tal'),
]

ventas = [
    (1, (2010,  9, 12),  '8830268-0'),
    (2, (2010,  9, 19), '11652624-7'),
    (3, (2010,  9, 30),  '7547896-8'),
    (4, (2010, 10,  1),  '8830268-0'),
    (5, (2010, 10, 13),  '7547896-8'),
    (6, (2010, 11, 11), '11652624-7'),
]

itemes = [
    (1, 89148,  3),
    (2, 50809,  4),
    (2, 33692,  2),
    (2, 47470,  6),
    (3, 30877,  1),
    (4, 89148,  1),
    (4, 75466,  2),
    (5, 89148,  2),
    (5, 47470, 10),
    (6, 41419,  2),
]

def productoMasCaro(productos):
    precio = 0
    producto = ""
    for i in productos:
        if i[2] > precio:
            precio = i[2]
            producto = i[1]
    return producto


def valorTotalBodega(productos):
    precio = 0
    for i in productos:
        precio += i[2] * i[3]
    return precio

def ingresoTotalPorVentas(itemes,productos):
    ingreso = 0
    for i in itemes:
        for j in productos:
            if i[1] == j[0]:
                ingreso += j[2] * i[2]
    return ingreso

def productoConMasIngresos(itemes, productos):
    productoDeMasIngresos=0
    producto = ""
    for i in productos:
        ingreso = 0
        for j in itemes:
            if i[0] == j[1]:
                ingreso += i[2] * j[2]
        if productoDeMasIngresos < ingreso:
            productoDeMasIngresos = ingreso
            producto = i[1]
    return producto



# Press the green button in the gutter to run the script.
if __name__ == '__main__':
    print(productoMasCaro(productos))
    print(valorTotalBodega(productos))
    print(ingresoTotalPorVentas(itemes, productos))
    print(productoConMasIngresos(itemes, productos))

# See PyCharm help at https://www.jetbrains.com/help/pycharm/
