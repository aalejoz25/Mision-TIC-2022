# Alvaro Zarabanda

import math

print("Piton Market")

n=0
cantidadProductos=0
pagoTotal=0
descuentoTotal=0
porcentajeDescuento=0.2
contador = 1
pagoConDescuento = 0

# Ingresa el valor de n
while True:
    try:
        n = int(input("n: "))
        break
    except ValueError:
        print("Error, el dato ingresado no es un numero")

# Ingresa la cantidad de productos

while True:
    try:
        cantidadProductos = int(input("Cantidad de productos: "))
        break
    except ValueError:
        print("Error, el dato ingresado no es un numero")

# Ingresa el valor de cada producto

for i in range(1,cantidadProductos+1):
    while True:
        try:
            exec("producto%s = int(input('Precio producto %s: '))" % (i,i))
            break
        except ValueError:
            print("Error, el dato ingresado no es un numero")


# Calcula el total sin descuento
for i in range(1,cantidadProductos+1):
    pagoTotal += eval("producto%s" % (i))

# Calcula el descuento
divisorPorcentaje = n
while contador <= cantidadProductos:
    if (cantidadProductos >= n) and (n > 0):
        if contador > divisorPorcentaje:
            porcentajeDescuento /= 2
            divisorPorcentaje += n
            if divisorPorcentaje > cantidadProductos:
                break
        descuentoTotal += eval("producto%s" % (contador)) * porcentajeDescuento
        contador += 1

    else:
        break

#Calcula el monto a pagar con el descuento
pagoConDescuento = math.floor(pagoTotal - descuentoTotal)

# Imprime en pantalla
print("Total:",pagoTotal)
print("Descuento:",descuentoTotal)
print("Por pagar:",pagoConDescuento)

input()







