public class Ahorcado {
    private String palabraSecreta;
    private char[] letrasAdivinadas;
    private int intentosRestantes;

    public Ahorcado(String palabraSecreta, int intentosIniciales) {
        this.palabraSecreta = palabraSecreta.toLowerCase();
        this.letrasAdivinadas = new char[palabraSecreta.length()];
        this.intentosRestantes = intentosIniciales;

        // Inicializa letrasAdivinadas con guiones bajos
        for (int i = 0; i < letrasAdivinadas.length; i++) {
            letrasAdivinadas[i] = '_';
        }
    }

    public boolean adivinarLetra(char letra) {
        boolean letraAdivinada = false;

        for (int i = 0; i < palabraSecreta.length(); i++) {
            if (palabraSecreta.charAt(i) == letra) {
                letrasAdivinadas[i] = letra;
                letraAdivinada = true;
            }
        }

        if (!letraAdivinada) {
            intentosRestantes--;
        }

        return letraAdivinada;
    }

    public boolean palabraCompletada() {
        return new String(letrasAdivinadas).equals(palabraSecreta);
    }

    public boolean juegoTerminado() {
        return intentosRestantes == 0 || palabraCompletada();
    }

    public String getPalabraActual() {
        return new String(letrasAdivinadas);
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public int getIntentosRestantes() {
        return intentosRestantes;
    }
}