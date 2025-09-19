import structures.Ponto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Painel extends JPanel {
    private final BufferedImage imagem;
    private final JPanel painelDaImagem;
    private final JTextField campoCor;
    private String metodoEscolhido = "fila"; // padrao = fila

    public Painel(BufferedImage imagem) {
        this.imagem = imagem;
        setLayout(new BorderLayout());

        this.painelDaImagem = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(imagem, 0, 0, this);
            }
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(imagem.getWidth(), imagem.getHeight());
            }
        };
        add(painelDaImagem, BorderLayout.CENTER);

        JPanel painelDeControles = new JPanel();

        campoCor = new JTextField("", 10);
        JButton botaoFila = new JButton("Pintar com Fila");
        JButton botaoPilha = new JButton("Pintar com Pilha");

        painelDeControles.add(new JLabel("Cor:"));
        painelDeControles.add(campoCor);
        painelDeControles.add(botaoFila);
        botaoFila.addActionListener(e -> {metodoEscolhido = "fila";});
        painelDeControles.add(botaoPilha);
        botaoPilha.addActionListener(e -> {metodoEscolhido = "pilha";});
        add(painelDeControles, BorderLayout.SOUTH);

        painelDaImagem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new Thread(() -> {
                    Ponto pontoInicial = new Ponto(e.getX(), e.getY());
                    String nomeDaCor = campoCor.getText();
                    int novaCor = getCorPeloNome(nomeDaCor);

                    FloodFill preenchimento = new FloodFill(painelDaImagem, novaCor, pontoInicial, imagem);

                    if (metodoEscolhido.equals("pilha")) {
                        preenchimento.pintarPilha();
                    } else {
                        preenchimento.pintarFila();
                    }
                }).start();
            }
        });
    }

    private int getCorPeloNome(String nomeDaCor) {
        switch (nomeDaCor.toLowerCase()) {
            case "vermelho", "red": return Color.RED.getRGB();
            case "azul", "blue": return Color.BLUE.getRGB();
            case "verde", "green": return Color.GREEN.getRGB();
            case "amarelo", "yellow": return Color.YELLOW.getRGB();
            case "ciano", "cyan": return Color.CYAN.getRGB();
            case "laranja", "orange": return Color.ORANGE.getRGB();
            default:
                return Color.GRAY.getRGB();
        }
    }
}