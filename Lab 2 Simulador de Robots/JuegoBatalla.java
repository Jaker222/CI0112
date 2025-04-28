import java.util.Scanner;

public class JuegoBatalla{
    Robot [] robots;
    int cantidadRobots;

    public static void main (String[] args){
        JuegoBatalla juego = new JuegoBatalla();
        juego.iniciarJuego();
    }

    public void iniciarJuego(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("---Bienvenido al simulador de batallas de robots.---");
        System.out.println("Ingrese la cantidad de robots (máximo 10): ");
        cantidadRobots = scanner.nextInt();
        while ( cantidadRobots < 2 || cantidadRobots > 10) { //While para evitar errores
            System.out.println("Cantidad inválida, por favor ingrese un número entre 2 y 10");
            cantidadRobots = scanner.nextInt();
        }
        scanner.nextLine();

        robots = new Robot[cantidadRobots];
        for (int i = 0; i < cantidadRobots; i++){ // For para la creación de robots
 
            System.out.println("Ingrese el nombre del robot #" + (i+1) + ": ");
            String nombre = scanner.nextLine();
            System.out.println();

            int puntosVida = (int) (Math.random() * 51) + 50; //math random entre 50 y 100
            int ataque = (int) (Math.random() * 11) + 10; //math random entre 10 y 20
            int defensa = (int) (Math.random() * 11); // math random entre 0 y 10
            robots[i] = new Robot(nombre, puntosVida, ataque, defensa);
        }
        iniciarBatalla();
    }
    public void iniciarBatalla(){
        boolean detener = false;
        Scanner scanner = new Scanner(System.in);

        while (contarRobotsVivos() > 1 && !detener) {
            for (int i = 0; i < robots.length; i++) {
                if (robots[i] != null && robots[i].estaVivo()) {
                    Robot objetivo = elegirObjetivo(i);

                    if (objetivo != null){
                        robots[i].atacar(objetivo);
                    }
                }
            }
            System.out.println("\n ¿Desea ver el estado de los robots (Digite 'Si' para ver o cualquier otra cosa para continuar): ");
            String respuestaEstado = scanner.nextLine();
            if (respuestaEstado.equalsIgnoreCase("Si")){ //Si la respuesta es "Si", muestra estado
                System.out.println("\n Estado actual de los robots:");
                for(int i=0; i < robots.length; i++){
                    if (robots[i] != null && robots[i].estaVivo()) {
                        robots[i].mostrarEstado();
                    }
                }
            }
            System.out.println("\n Desea continuar la simulación? (Digite 'Si' para continuar o cualquier otra cosa para detener): ");
            String respuesta = scanner.nextLine();
            if (!respuesta.equalsIgnoreCase("Si")) { //si la respuesta es cualquier otra cosa que no sea "Si", detiene
                detener = true;
            }
        }
        if (contarRobotsVivos() == 1){
            mostrarGanador();
    } else {
        System.out.println("\n La simulación fue detenida, no hay un ganador.");
    }
}


    private Robot elegirObjetivo(int atacante) {
        int objetivo = 0;

        while (objetivo == atacante || robots[objetivo] == null || !robots[objetivo].estaVivo()){
            objetivo = (int) (Math.random() * robots.length);
        }
        return robots[objetivo];
    }

    private int contarRobotsVivos(){
        int vivos = 0;
        for (int i = 0; i < robots.length; i++){
            if (robots[i] != null && robots[i].estaVivo()) {
                vivos++;
            }
        }
        return vivos;
    }

    public void mostrarGanador(){
        for (int i = 0; i< robots.length; i++){
            if (robots[i] != null && robots[i].estaVivo()) {
                System.out.println("\n ¡El ganador es: " + robots[i].getNombre() +"!" );
                return;
            }
        }
    }
}