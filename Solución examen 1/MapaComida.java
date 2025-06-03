public class MapaComida {
    private int [][] matriz;
    private int filas;
    private int columnas;

    public MapaComida (int filas, int columnas) {
        this.filas = filas;
        this.columnas = columnas;
        this.matriz = new int [filas][columnas];
    }

    public void asignarComidaAleatoria(int cantidadCeldas) {
        int asignadas = 0;

        while (asignadas < cantidadCeldas) {
            int fila = (int)(Math.random() * filas);
            int col= (int)(Math.random() * columnas);

            if (matriz[fila][col] > 0) {
                continue;
            }

            int sumaVecinas = obtenerSumaVecinas(fila, col);

            if (sumaVecinas == 0 && matriz[fila][col] == 0) {
                matriz[fila][col] = 1;
            } else {
                matriz[fila][col] += sumaVecinas;
            }
            asignadas++;
        }
    }

    private int obtenerSumaVecinas(int fila, int col) {
        int suma = 0;
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < filas && j >= 0 && j < columnas) {
                    if (!(i == fila && j == col)) {
                        suma += matriz[i][j];
                    }
                }
            }
        }
        return suma;
    }
}