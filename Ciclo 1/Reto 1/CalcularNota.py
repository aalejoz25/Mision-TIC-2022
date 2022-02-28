notaFinal=59.6
notaCertamen1 = int(input('Ingrese la nota del certamen 1: '))
notaCertamen2 = int(input('Ingrese la nota del certamen 2: '))
notaLaboratorio = int(input('Ingrese nota laboratotio: '))
notaCertamen3 = 3*((notaFinal-(notaLaboratorio*0.3))/0.7) - (notaCertamen1 + notaCertamen2)
print('Necesita nota',round(notaCertamen3),'en el certamen 3')

