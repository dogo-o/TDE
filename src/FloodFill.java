import structures.*;

import java.awt.image.BufferedImage;
import javax.swing.*;

public class FloodFill {
    private final JPanel painel;

    private final int novaCor;
    private final Ponto pontoInicial;
    private BufferedImage imagem;

    public FloodFill(JPanel painel, int novaCor, Ponto pontoInicial, BufferedImage imagem) {
        this.painel = painel;
        this.novaCor = novaCor;
        this.pontoInicial = pontoInicial;
        this.imagem = imagem;
    }

    public void pintarFila() {
        int contadorDePixels = 0;

        int corOriginal = imagem.getRGB(pontoInicial.x, pontoInicial.y);
        Fila fila = new Fila();
        fila.enqueue(pontoInicial);
        while (!(fila.isEmpty())){
            Ponto p = fila.dequeue();
            int x = p.x;
            int y = p.y;
            if (x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight() && imagem.getRGB(x,y) == corOriginal){
                imagem.setRGB(x,y, novaCor);
                fila.enqueue(new Ponto(x + 1, y));
                fila.enqueue(new Ponto(x - 1, y));
                fila.enqueue(new Ponto(x, y + 1));
                fila.enqueue(new Ponto(x, y - 1));
            }
            contadorDePixels++;
            if (contadorDePixels % 100 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }                painel.repaint();
            }
            painel.repaint();
        }
    }

    public void pintarPilha() {
        int contadorDePixels = 0;

        int corOriginal = imagem.getRGB(pontoInicial.x, pontoInicial.y);
        Pilha pilha = new Pilha();
        pilha.push(pontoInicial);
        while (!(pilha.isEmpty())){
            Ponto p = pilha.pop();
            int x = p.x;
            int y = p.y;
            if (x >= 0 && x < imagem.getWidth() && y >= 0 && y < imagem.getHeight() && imagem.getRGB(x,y) == corOriginal){
                imagem.setRGB(x,y, novaCor);
                pilha.push(new Ponto(x + 1, y));
                pilha.push(new Ponto(x - 1, y));
                pilha.push(new Ponto(x, y + 1));
                pilha.push(new Ponto(x, y - 1));
            }
            contadorDePixels++;
            if (contadorDePixels % 100 == 0) {
                try {
                    Thread.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                painel.repaint();
            }
            painel.repaint();
        }
    }
}