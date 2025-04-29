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
        cantidadRobots = -1;
        while ( cantidadRobots < 2 || cantidadRobots > 10) { //While para evitar errores
            if (scanner.hasNextInt()) {
                cantidadRobots = scanner.nextInt();
                if (cantidadRobots < 2 || cantidadRobots > 10){ //Si después de la entrada de datos sigue recibiendo un dato incorrecto, repite el while
                    System.out.println("Cantidad inválida, por favor ingrese un número entre 2 y 10");
                }
            }
            else {
                    System.out.println("Entrada inválida, debe ingresar un número entre 2 y 10.");
                    scanner.next(); //limpia entrada incorrecta
            }
        }
        scanner.nextLine();

        robots = new Robot[cantidadRobots];
        for (int i = 0; i < cantidadRobots; i++){ // For para la creación de robots
 
            System.out.println("Ingrese el nombre del robot #" + (i+1) + ": ");
            String nombre = scanner.nextLine();
            System.out.println();

            int puntosVida = -1;
            System.out.println("Ingrese los puntos de vida para " + nombre + " (50-100): ");

            while (puntosVida < 50 || puntosVida > 100) {
                if (scanner.hasNextInt()) {
                    puntosVida = scanner.nextInt();
                    scanner.nextLine();
                    if (puntosVida < 50 || puntosVida > 100) {
                        System.out.println("Valor inválido. Debe ingresar un número entre 50 y 100:");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor dígite un número entre 50 y 100:");
                    scanner.next();
                }
            }
    
            int ataque = -1;
            System.out.println("Ingrese el ataque para " + nombre + " (10-20): ");
            while (ataque < 10 || ataque > 20) {
                if (scanner.hasNextInt()) {
                    ataque = scanner.nextInt();
                    scanner.nextLine();
                    if (ataque < 10 || ataque > 20) {
                        System.out.println("Valor inválido. Debe ingresar un número entre 10 y 20:");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor dígite un número entre 10 y 20:");
                    scanner.next();
                }
            }
    
            int defensa = -1;
            System.out.println("Ingrese la defensa para " + nombre + " (0-10): ");
            while (defensa < 0 || defensa > 10) {
                if (scanner.hasNextInt()) {
                    defensa = scanner.nextInt();
                    scanner.nextLine();
                    if (defensa < 0 || defensa > 10) {
                        System.out.println("Valor inválido. Debe ingresar un número entre 0 y 10:");
                    }
                } else {
                    System.out.println("Entrada inválida. Por favor dígite un número entre 0 y 10:");
                    scanner.next();
                }
            }
            robots[i] = new Robot (nombre, puntosVida, ataque, defensa);
            System.out.println();
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
                        if (!objetivo.estaVivo()) { // después de atacar un objetivo, verifica si sigue vivo o si fue destruido
                            System.out.println("\n==================================");
                            System.out.println("¡¡EL ROBOT " + objetivo.getNombre().toUpperCase() + " HA SIDO DESTRUIDO!! ");
                            System.out.println("==================================\n");
                        }
                    }
                }
            }
            System.out.println("\n ¿Desea ver el estado de los robots (Digite 'Si' para ver o cualquier otra cosa para continuar): ");
            System.out.println();
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
            System.out.println();
            String respuesta = scanner.nextLine();
            System.out.println();
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
                System.out.println("\n¡¡EL GANADOR ES " + robots[i].getNombre().toUpperCase() + "!!\n");
                return;
            }
        }
    }
}