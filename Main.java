import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] palabras = {"java", "programacion", "computador", "compilar", "python", "complejo", "array", "lenguaje", "inteligencia", "codigo", "consola", "operador", "programar", "iterar", "software", "debug", "registro", "menu", "interfaz", "aleatorio", "input"};
        int intentosmaximos = 7;

        Diccionario diccionario = new Diccionario(palabras);
        Ahorcado juego = new Ahorcado(diccionario.obtenerPalabraAleatoria(), intentosmaximos);
        Puntuacion puntuacion = new Puntuacion();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Bienvenid@ al juego del ahorcado de software");

        while (!juego.juegoTerminado()) {
            System.out.println("Palabra actual: " + obtenerPalabraOculta(juego));
            System.out.println("Intentos restantes: " + juego.getIntentosRestantes());
            System.out.print("Ingresa una letra: ");
            char letra = scanner.next().charAt(0);

            if (juego.adivinarLetra(letra)) {
                puntuacion.incrementarPuntaje();
            } else {
                System.out.println("Incorrecto. La letra no está en la palabra.");
            }
        }

        if (juego.getIntentosRestantes() == 0) {
            System.out.println("Ya no tienes más intentos, la palabra era: " + juego.getPalabraSecreta());
        } else {
            System.out.println("Felicitaciones, has adivinado la palabra: " + juego.getPalabraSecreta());
        }

        System.out.println("Tu puntaje final es: " + puntuacion.getPuntaje());
    }

    private static String obtenerPalabraOculta(Ahorcado juego) {
        StringBuilder palabraOculta = new StringBuilder();
        for (char letra : juego.getPalabraActual().toCharArray()) {
            if (letra == '_') {
                palabraOculta.append("_ ");
            } else {
                palabraOculta.append(letra).append(" ");
            }
        }
        return palabraOculta.toString().trim();
    }
}