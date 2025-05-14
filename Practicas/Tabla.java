public class Tabla {
    private double largo;
    private double ancho;
    private double precio;

    public Tabla (double largo, double ancho) {
        if (ancho > largo) {
            double aux = largo;
            largo = ancho;
            ancho = aux;
        }
        this.ancho = ancho;
        this.largo = largo;
        precio = (largo*ancho) * 1000;
    }

    public Tabla cortar (double nuevoAncho, double nuevoLargo) {
        if (nuevoAncho > ancho || nuevoLargo > largo) {
            return null;
        }

        ancho = ancho - nuevoAncho;
        largo = largo - nuevoLargo;
        Tabla nuevaTabla = new Tabla (nuevoAncho, nuevoLargo);
        return nuevaTabla;
    }

    public String mostrarInfo() {
        return "Ancho: " + ancho + "m. Largo: " + largo + "m. Precio:" + precio + " colones.";
    }

    public double getAncho() { return ancho; }
    public double getLargo() { return largo; }
    public double getPrecio() { return precio; }

}