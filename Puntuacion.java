public class Puntuacion {
    private int puntaje;

    public Puntuacion() {
        this.puntaje = 0;
    }

    public void incrementarPuntaje() {
        puntaje++;
    }

    public int getPuntaje() {
        return puntaje;
    }
}