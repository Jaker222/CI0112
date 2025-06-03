public class Agente {
    private int id;

    public Agente(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("ID inválido, debe ser mayor que 0.");
            this.id = 0;
        }
    }

    public boolean esValido() {
        return id > 0;
    }

    @Override
    public String toString() {
        return "Agente #" + id;
    }

    public int getId() { return id;}
}