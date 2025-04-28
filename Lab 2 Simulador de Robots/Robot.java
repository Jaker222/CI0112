public class Robot {

    private String nombre;
    private int puntosVida;
    private int ataque;
    private int defensa; 

    public Robot (String nombre, int puntosVida, int ataque, int defensa){
        this.nombre = nombre;
        this.puntosVida = puntosVida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public void atacar (Robot otroRobot){
        int daño = ataque - otroRobot.defensa; //variable de el daño que será causado
        if (daño < 0){
            daño = 0;
        }
        otroRobot.puntosVida -= daño;
        if (otroRobot.puntosVida < 0){
            otroRobot.puntosVida = 0;
        }
        System.out.println (nombre + " ataca a " + otroRobot.nombre + " causandole " + daño + " de daño. Vida restante: " + otroRobot.puntosVida);
    }

    public boolean estaVivo(){ //Método para verificar si el robot sigue vivo
        return puntosVida > 0;
    }

    public void mostrarEstado () { //método para mostrar el estado actual del robot
        System.out.println(nombre + " Vida: " + puntosVida + ", Ataque: " + ataque + ", Defensa: " + defensa);
    }

    
    //Getters
    public String getNombre() {return nombre;}
    public int getPuntosVida() {return puntosVida;}
    public int getAtaque() {return ataque;}
    public int getDefensa() {return defensa;}
    
    //Setters
    public void setNombre(String nombre) {this.nombre = nombre;}
    public void setPuntosVida(int puntosVida) {this.puntosVida = puntosVida;}
    public void setAtaque(int ataque) {this.ataque = ataque;}
    public void setDefensa(int defensa) {this.defensa = defensa;}
}