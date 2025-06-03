public class Mensaje {
    private int idFuente;
    private int idEmisor;
    private int idDestinatario;
    private String contenido;

    public Mensaje ( int idFuente, int idEmisor, int idDestinatario, String contenido) {
        if (idFuente > 0 && idEmisor > 0 && idDestinatario > 0 && contenido != null && contenido.trim().isEmpty()) {
        this.idFuente = idFuente;
        this.idEmisor = idEmisor;
        this.idDestinatario = idDestinatario;
        this.contenido = contenido;
        } else {
            System.out.println("Datos inválidos al crear el mensaje");
            this.idFuente = 0;
            this.idEmisor = 0;
            this.idDestinatario = 0;
            this.contenido = "";
        }
    }

    public boolean esValido() {
        return idFuente > 0 && idEmisor > 0 && idDestinatario > 0 && !contenido.isEmpty();
    }

    @Override
    public String toString() {
        return "De Agente #" + idFuente + " (enviado por Agente #" + idEmisor + ") para Agente #" + idDestinatario + ": " + contenido;
    }

    public int getIdFuente() { return idFuente; }

    public int getIdEmisor() { return idEmisor; }

    public int getIdDestinatario() { return idDestinatario; }

    public String getContenido() { return contenido; }


}