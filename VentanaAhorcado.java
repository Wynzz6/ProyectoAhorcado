import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class VentanaAhorcado extends JFrame {

    private Ahorcado juego;
    private Diccionario diccionario;

    private JLabel iblPalabra;
    private JLabel iblIntentos;
    private JTextField txtLetra;
    private JButton btnAdivinar;

    private JLabel iblImagen;

    private JPanel pnlImagen;

    private BufferedImage myPicture;

    public VentanaAhorcado() {
        super("Juego del Ahorcado");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(400, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        diccionario = new Diccionario(new String[]{"java", "programacion", "computador", "compilar", "python", "complejo", "array", "lenguaje", "inteligencia", "codigo", "consola", "operador", "programar", "iterar", "software", "debug", "registro", "menu", "interfaz", "aleatorio", "input"});
        reiniciarJuego();

        initComponents();
        setVisible(true);
    }

    private void reiniciarJuego() {
        String palabraSecreta = diccionario.obtenerPalabraAleatoria();
        juego = new Ahorcado(palabraSecreta, 7);
    }

    private void initComponents() {
        iblPalabra = new JLabel(obtenerPalabraOculta());
        iblPalabra.setFont(new Font("Arial", Font.BOLD, 20));
        iblPalabra.setHorizontalAlignment(SwingConstants.CENTER);

        iblIntentos = new JLabel("Intentos restantes: " + juego.getIntentosRestantes());
        txtLetra = new JTextField();
        btnAdivinar = new JButton("Adivinar");
        btnAdivinar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adivinarLetra();
            }
        });

        pnlImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (myPicture != null) {
                    g.drawImage(myPicture, 0, 0, getWidth(), getHeight(), this);
                }
            }
        };
        pnlImagen.setPreferredSize(new Dimension(261, 254));

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 1));
        panel.add(iblPalabra);
        panel.add(iblIntentos);
        panel.add(txtLetra);
        panel.add(btnAdivinar);

        add(panel, BorderLayout.CENTER);
        add(pnlImagen, BorderLayout.SOUTH);

        dibujarAhorcado();
    }

    private void dibujarAhorcado() {
        try {
            int intentosRestantes = juego.getIntentosRestantes();
            if (intentosRestantes <= 7 && intentosRestantes > 0) {
                String rutaImagen = "C:\\Users\\Usuario\\Pictures\\ahorcado_" + (intentosRestantes - 1) + ".png";
                myPicture = ImageIO.read(new File(rutaImagen));
                pnlImagen.repaint();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void adivinarLetra() {
        if (!juego.juegoTerminado()) {
            String letraIngresada = txtLetra.getText().toLowerCase();
            if (letraIngresada.length() == 1 && Character.isLetter(letraIngresada.charAt(0))) {
                char letra = letraIngresada.charAt(0);
                boolean letraAdivinada = juego.adivinarLetra(letra);

                iblPalabra.setText(obtenerPalabraOculta());
                iblIntentos.setText("Intentos restantes: " + juego.getIntentosRestantes());
                txtLetra.setText("");

                if (juego.palabraCompletada()) {
                    JOptionPane.showMessageDialog(this, "¡Felicidades! Has adivinado la palabra: " + juego.getPalabraSecreta());
                    reiniciarJuego();
                    dispose();
                } else if (!letraAdivinada) {
                    dibujarAhorcado();
                    JOptionPane.showMessageDialog(this, "Incorrecto. La letra no está en la palabra.");
                    if (juego.juegoTerminado()) {
                        JOptionPane.showMessageDialog(this, "¡Se acabaron los intentos! La palabra era: " + juego.getPalabraSecreta());
                        reiniciarJuego();
                        dispose();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Ingresa una letra válida.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Ya no puedes jugar. Reinicia el juego.");
        }
    }

    private String obtenerPalabraOculta() {
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaAhorcado());
    }
}