public class Lista {
    private Nodo primero;

    public Lista() {
        this.primero = null;
    }

    public Lista(Nodo primero) {
        this.primero = primero;
    }

    public boolean buscar (int valor) {
        Nodo actual = this.primero;
        while (actual != null) {
            if (actual.getDato() == valor) {
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(primero);
        primero = nuevo;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (primero == null) {
            primero = nuevo;
        } else {
            Nodo actual = primero;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }
    }

    public void mostrar() {
        Nodo actual = primero;
        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        Lista lista = new Lista();

        lista.insertarAlInicio(10);
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(30);
        lista.insertarAlFinal(1);
        lista.mostrar();
    }

}
