public class Almacen{
    private Tabla[] tablas;
    private int cantidad;
    public static final int MAX_TABLAS = 500;

    public Almacen() {
        tablas= new Tabla[MAX_TABLAS];
        cantidad = 0;
    }

    public void agregarTabla(Tabla t) {
        if (cantidad < MAX_TABLAS) {
            tablas[cantidad] = t;
            cantidad++;
            ordenarTablas();
        }
    }

    public void ordenarTablas() { // anotar en forro
        for (int i = 0; i < cantidad - 1; i++) {
            for (int j = i + 1; j < cantidad; j++) {
                if (tablas[i].getAncho() > tablas[j].getAncho()) {
                    Tabla aux = tablas[i];
                    tablas[i] = tablas[j];
                    tablas[j] = aux;
                } else if (tablas[i].getAncho() == tablas[j].getAncho() && tablas[i].getLargo() > tablas[j].getLargo()) {
                    Tabla aux = tablas[i];
                    tablas[i] = tablas[j];
                    tablas[j] = aux;
                }
            }
        }
    }

    public Tabla buscarTabla(double anchoSolicitado, double largoSolicitado) {
        for (int i = 0; i < cantidad; i++) {
            if (tablas[i].getAncho() >= anchoSolicitado && tablas[i].getLargo() >= largoSolicitado) {
                Tabla encontrada = tablas[i];
                for (int j = i; j < cantidad - 1; j++){
                    tablas[j] = tablas [j + 1];
                }
                cantidad--;
                return encontrada;
            }
        }
        return null;
    }
}