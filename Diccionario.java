public class Diccionario {
    private String[] palabras;

    public Diccionario(String[] palabras) {
        this.palabras = palabras;
    }

    public String obtenerPalabraAleatoria() {
        int indice = (int) (Math.random() * palabras.length);
        return palabras[indice];
    }
}