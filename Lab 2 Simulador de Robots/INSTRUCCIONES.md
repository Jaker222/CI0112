# INSTRUCCIONES - SIMULADOR DE BATALLA.
**Lenguaje:** Java.

## Compile el programa con el siguiente comando:
javac JuegoBatalla.java Robot.java

## Ejecute el programa con:
java JuegoBatalla

## Instrucciones de uso:
- Al iniciar, el programa le pedirá que ingrese una cantidad de robots a crear. Esta debe ser un número entre 2 y 10 (incluidos).
- Luego, para cada robot deberá asignar un nombre, puntos de vida (de 50 a 100), ataque (de 10 a 20) y un valor de defensa (de 0 a 10).

## Después de crear todos los robots:
- La batalla comenzará automáticamente. Después de cada ronda podrá ver el estado de todos los robots vivos y decidir si desea continuar con la simulación o detenerla.

## Reglas de combate: 
- Cada robot atacará un robot aleatorio que esté vivo. Cuándo los puntos de vida de un robot se reducen a 0, es eliminado.
- El juego termina cuándo se destruyen todos los robots y se declara un ganador, o si la simulación es terminada manualmente.

## Notas:!!
- Si se ingresan datos inválidos, deberá volver a ingresar un dato hasta que sea válido.
- Recuerde ingresar los datos dentro del rango permitido especificado.
