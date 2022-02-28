from math import floor
'PITÓN MARKET'

n = int(input("n: "))
cantidad = int(input("Cantidad de productos: "))
totalSinDescuento = 0
totalConDescuento = 0
descuentos = 0
descuentoInicial = 0.2
valorn = n

for i in range(1, cantidad+1):
    precio = int(input("Precio producto "+str(i)+": "))
    totalSinDescuento += precio

    if (cantidad - i >= valorn - 1) and n != 0:
        descuentos += precio * descuentoInicial

    if i == n:
        n += 3
        descuentoInicial /= 2
    '''    if descuentos <= 3:
            if descuentos == 0:
                totalConDescuento += totalSinDescuento - (totalSinDescuento * 0.20)
            elif descuentos == 1:
                totalConDescuento += totalSinDescuento - (totalSinDescuento * 0.10)
            else:
                totalConDescuento += totalSinDescuento - (totalSinDescuento * 0.05)
            descuentos += 1
            if descuentos == 3:
                descuentos = 0
        else:
            totalConDescuento += totalSinDescuento
    else:
        totalConDescuento += totalSinDescuento'''

totalConDescuento = floor(totalSinDescuento - descuentos)

print("Total:", totalSinDescuento)
print("Descuento:", descuentos)
print("Por pagar:", totalConDescuento)

input()
