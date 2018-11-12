# Programacion3
Enunciado del problema
Se quiere resolver un rompecabezas que consistente en rellenar completamente una plantilla
cuadriculada a partir de un conjunto de piezas dadas. Las piezas se pueden rotar en cuatro
movimientos posibles (+90, -90, +180,-180) y cada pieza sólo se puede usar una vez. La
plantilla se debe completar con la mínima cantidad de rotaciones posibles.


El algoritmo deberá leer los datos de dimensiones de la plantilla y formato de fichas partir de un
archivo de texto con extensión txt, con el siguiente formato (NO se puede cambiar el formato):

4,4,6-(2,3-0,1,1,1,1,0) (1,1-1) (3,2-0,1,1,1,0,1) (2,2-1,1,0,1) (1,1-1) (2,2-1,0,1,1)

Donde el primer valor es la cantidad de filas de la plantilla, en el ejemplo 4, el segundo valor la
cantidad de columnas, en el ejemplo 4, y el tercer valor cantidad de ficha, en el ejemplo 6. Las
siguientes son las fichas, cada ficha se representa como un rectángulo en donde el primer valor
es el alto en filas y el segundo el ancho en columnas; el valor cero indica que esa posición en el
rectángulo NO es parte de la ficha, y el valor uno que SI lo es. 


