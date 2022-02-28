#Alvaro Zarabanda

#Variables globales
precioProductoA = 270
precioProductoB = 340
precioProductoC = 390

def seleccionarProducto():
    while True:
        try:
            producto = str(input("Elija producto: "))
            if (producto=="A" or producto=="B" or producto=="C"):
                break
            else:
                print("El producto no se encuentra en la maquina")
        except ValueError:
            print("Error")
    if producto == "A":
        precio = precioProductoA
    elif producto == "B":
        precio = precioProductoB
    else:
        precio = precioProductoC
    return precio

def ingresarMonedas(precioProducto):
    print("Ingrese monedas:")
    totalIngresado = []
    while sum(totalIngresado) < precioProducto:
        while True:
            try:
                monedaInsertada = int(input())
                if (monedaInsertada==10 or monedaInsertada==50 or monedaInsertada==100):
                    totalIngresado.append(monedaInsertada)
                    break
                else:
                    print("La maquina no acepta esta moneda")
            except ValueError:
                print("No ha insertado una moneda")
    return totalIngresado

def calcularVuelto(totalInsertado, precioProducto):
    vuelto = 0
    if totalInsertado == precioProducto:
        None
    else:
        print("Su vuelto:")
    while vuelto < totalInsertado-precioProducto:
        while (vuelto + 100) <= (totalInsertado-precioProducto):
            vuelto += 100
            print("100")
        while (vuelto + 50) <= (totalInsertado - precioProducto):
            vuelto += 50
            print("50")
        while (vuelto + 10) <= (totalInsertado - precioProducto):
            vuelto += 10
            print("10")

if __name__ == '__main__':
    precioProducto = seleccionarProducto()
    monedas = ingresarMonedas(precioProducto)
    total = sum(monedas)
    calcularVuelto(total, precioProducto)
    input()

