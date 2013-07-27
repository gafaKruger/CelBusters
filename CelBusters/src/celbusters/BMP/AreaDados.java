/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters.BMP;

/**
 *
 * @author Samuel Blum Vorpagel
 */
public class AreaDados {
   byte[] pixels;

    public AreaDados(byte[] pixels) {
        this.pixels = pixels;
    }

    public byte[] getPixels() {
        return pixels;
    }

    public void setPixels(byte[] pixels) {
        this.pixels = pixels;
    }
    
    public byte getPixel (int posicao) {
        byte [] g = getPixels();
        return g[posicao];
    }
    
    public void setPixel (int posicao, byte b) {
        byte [] g = getPixels();
        g[posicao] = b;
        setPixels(g);
    }
   
   
}
