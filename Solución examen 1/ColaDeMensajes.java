public class ColaDeMensajes {
    private Mensaje[] cola;
    private int inicio;
    private int fin;
    private int max;

    public ColaMensajes(int capacidad) {
        max = capacidad;
        cola = new Mensaje[max];
        inicio = 0;
        fin = 0;
    }

    // Ver si la cola está vacía
    public boolean estaVacia() {
        return inicio == fin;
    }

    // Ver si la cola está llena        
    public boolean estaLlena() {
        return ((fin + 1) % max) == inicio;
    }

    // Añadir un mensaje a la cola
    public boolean enviarMensaje(Mensaje mensaje) {
        if (mensaje == null || !mensaje.esValido()) {
            System.out.println("Mensaje inválido, no se envió.");
            return false;
        }
        if (estaLlena()) {
            System.out.println("La cola está llena.");
            return false;
        }
        cola[fin] = mensaje;
        fin = (fin + 1) % max;
        System.out.println("Mensaje agregado a la cola.");
        return true;
    }

    // Ver el ID destinatario del siguiente mensaje
    public Integer verSiguienteIdDestinatario() {
        if (estaVacia()) {
            return null; // Cola vacía
        }
        return cola[inicio].getIdDestinatario();
    }

    // Extraer el mensaje si el agente es destinatario correcto
    public Mensaje extraerMensaje(int idAgente) {
        if (estaVacia()) {
            System.out.println("No hay mensajes en la cola.");
            return null;
        }
        Mensaje siguiente = cola[inicio];
        if (siguiente.getIdDestinatario() == idAgente) {
            cola[inicio] = null; // limpiar referencia
            inicio = (inicio + 1) % max;
            return siguiente;
        } else {
            System.out.println("No puedes extraer este mensaje. No eres el destinatario.");
            return null;
        }
    }
}
