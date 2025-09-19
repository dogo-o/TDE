import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        File arquivo = new File("img.png");
        BufferedImage imagemParaPintar = ImageIO.read(arquivo);

        JFrame frame = new JFrame("Flood Fill - Clique para pintar");
        Painel painel = new Painel(imagemParaPintar);

        frame.add(painel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
