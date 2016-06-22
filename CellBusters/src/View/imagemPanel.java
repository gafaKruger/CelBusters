/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Model.BMP;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

/**
 *
 * @Autores: Samuel Blum Vorpagel - Rafael Fiori Kruger
 */
public class imagemPanel extends JPanel {

    private BMP imagem;
    private BufferedImage grid;

    public imagemPanel() {
        imagem = null;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        if (grid == null && imagem != null) {
            grid = (BufferedImage) (this.createImage(imagem.getMp().getW(), imagem.getMp().getH()));
            imagem.getMp().paintPixel(grid.createGraphics());
        }
        g2.drawImage(grid, null, 0, 0);
    }

    public void update() {
        this.paintComponent(this.getGraphics());
    }

    public BMP getImagem() {
        return imagem;
    }

    public void setImagem(BMP imagem) {
        this.imagem = imagem;
    }

    public BufferedImage getGrid() {
        return grid;
    }

    public void setGrid(BufferedImage grid) {
        this.grid = grid;
    }

    public void reset() {
        grid = null;
    }
}
