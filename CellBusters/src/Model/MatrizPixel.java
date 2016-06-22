/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JOptionPane;

/**
 * Classe responsavel por gerar a matriz de cores idependentemente se a bmp for
 * 8 ou 24 Bytes
 *
 * @Autores: Samuel Blum Vorpagel - Rafael Fiori Kruger
 */
public class MatrizPixel {

    private Color pixel[][]; //Matriz de cores, serve para poder imprimir na tela
    private int posCor[][]; //Matriz com a posição das cores na paleta de cores.
    private int h, w; //altura e largura da magem
    private boolean grayScale; //é o booleano para saber se foi aplicada a transformação de grayScale
    private boolean trueColor; //é o booleano para dizer se é trueColor ou não, isso vai ajudar na hora de salvar a imagem
    private int tamArquivo;
    private int tamPaleta;
    private byte[] paleta;
    private byte[] arquivo;

    /**
     * Carrega a matriz de cor "this.Color[][]" para imagens bmp 8B
     *
     * @param paleta
     * @param arquivo
     * @param h
     * @param w
     */
    public MatrizPixel(byte[] paleta, byte[] arquivo, int h, int w, int bitPixel) {
        System.out.println("Usa Paleta");
        this.h = h;
        this.w = w;
        this.paleta = paleta;
        this.arquivo = arquivo;
        this.tamArquivo = arquivo.length;
        this.tamPaleta = paleta.length;
        this.trueColor = false;
        int aux;

        //Carregar a paleta de cor  
        int x = 0, y;
        Color[] ColorPaleta = new Color[256];
        for (int i = 0; i < paleta.length; i = i + 4) {
            ColorPaleta[x] = new Color((((byte) paleta[i + 2]) & 0xFF),
                    (((byte) paleta[i + 1]) & 0xFF),
                    (((byte) paleta[i]) & 0xFF));
            x++;
        }

        this.pixel = new Color[h][w];
        this.posCor = new int[h][w];
        int erro = (w * (bitPixel / 8)) / 4;
        //O truncamento realizado acima faz com que o valor atribuido a errot abaixo seja multiplo de 4
        int errot = (erro * 4);
        int pulo = (w * (bitPixel / 8)) - errot;

        /*if ((w*(bitPixel/8)) % 4 == 0) {
         pulo = 0;
         } else {
         int var = (w*(bitPixel/8)) / 4;
         pulo = w - (pulo*(bitPixel/8) - (4*var));
         }*/
        /*if (w % 2 == 0) {
         tam = arquivo.length;
         } else {
         tam = arquivo.length - 1;
         }*/
        int tam;
        tam = arquivo.length - 1;
        x = w - 1;
        y = 0;
        int cont = 0;
        System.out.println("Pulo: " + pulo);
        System.out.println("Tamanho: " + tam);
        for (int i = tam; i >= 0; i--) {
            if (y < h) {
                if (cont >= (w - 1)) {
                    i = i - pulo;
                    cont = 0;
                }
                aux = arquivo[i] & 0xFF;
                this.pixel[y][x] = new Color(ColorPaleta[aux].getRed(),
                        ColorPaleta[aux].getGreen(),
                        ColorPaleta[aux].getBlue());
                this.posCor[y][x] = aux;
                cont++;
                x--;
                if (x == -1) {
                    y++;
                    x = w - 1;
                }
            }
        }
    }

    /**
     * Carrega a matriz de cor para arquivos bmp de 24B
     *
     * @param arquivo
     * @param h
     * @param w
     */
    public MatrizPixel(byte[] arquivo, int h, int w, int bitPixel) {
        System.out.println("True Color");
        this.h = h;
        this.w = w;
        this.tamArquivo = arquivo.length;
        this.trueColor = true;
        int erro = (w * (bitPixel / 8)) / 4;
        System.out.println(w * 3);
        //O truncamento realizado acima faz com que o valor atribuido a errot abaixo seja multiplo de 4
        int errot = (erro * 4);
        System.out.println("errot: " + errot);
        int pulo = (w * (bitPixel / 8)) - errot;

        /*if ((pulo*(bitPixel/8)) % 4 == 0) {
         pulo = 0;
         } else {
         int var = (pulo*(bitPixel/8)) / 4;
         pulo = (pulo*bitPixel) - (4*var);
         }*/
        int tam;
        tam = arquivo.length - 1;
        int cont = 0;
        System.out.println("Pulo: " + pulo);
        System.out.println("Tamanho: " + (tam+1) + " h*w=" + ((h*w*3) + w*pulo));
        System.out.println("h: " + (h - 1) + " w: " + (w - 1));
        int x = 0, y = h - 1;
        System.out.println(x);
        this.pixel = new Color[h][w];
        for (int i = 0; i <= tam; i = i + 3) {
            //System.out.println("Largura: " + x + " Altura: " + y);
            if (y >= 0) {
                if (i - pulo >= 0) {
                    this.pixel[y][x]
                            = new Color((((byte) arquivo[i]) & 0xFF),
                                    (((byte) arquivo[i - 1]) & 0xFF),
                                    (((byte) arquivo[i - 2]) & 0xFF));
                    x++;
                    /*if (x == w) {
                     y--;
                     x = 0;
                     }*/
                    cont++;
                    if (cont >= (w)) {
                        i = i + pulo;
                        cont = 0;
                        x = 0;
                        y--;
                    }
                } else {
                    System.out.println("oi");
                }
            }
        }
    }

    public byte[] get32() {
        byte[] b = new byte[this.tamArquivo];
        for (int i = 0; i < b.length; i++) {
            b[i] = 0;
        }
        int erro = (w) / 4;
        int errot = (erro * 4);
        int tam = 0;

        if (w % 2 == 0) {
            tam = b.length - 1;
        } else {
            tam = b.length;
        }

        int cont = w;
        int pulo = cont - errot;
        int x = w - 1, y = 0;

        for (int i = tam; i >= 0; i = i - 3) {
            if (y < h) {
                if (cont >= w) {
                    i = i - pulo;
                    cont = 0;
                }
                if (pixel[y][x] != null) {
                    b[i] = (byte) this.pixel[y][x].getRed();
                    b[i - 1] = (byte) this.pixel[y][x].getGreen();
                    b[i - 2] = (byte) this.pixel[y][x].getBlue();
                } else {
                    System.out.println("NULL");
                    System.out.println(i);
                }
                cont++;
                x--;
                if (x == -1) {
                    y++;
                    x = w - 1;
                }
            }
        }
        return b;
    }

    public byte[] get8() {
        int x = 0, y;
        int aux;
        byte[] b = new byte[this.tamArquivo + this.tamPaleta];
        byte[] b1 = new byte[this.tamArquivo];
        byte[] b2 = new byte[this.tamPaleta];

        for (int i = 0; i < b2.length; i++) {
            b2[i] = paleta[i];
        }

        for (int i = 0; i < b1.length; i++) {
            b1[i] = 0;
        }

        int erro = (w) / 4;
        int errot = (erro * 4);
        int tam;
        if (w % 2 == 0) {
            tam = b1.length - 1;
        } else {
            tam = b1.length;
        }
        x = w - 1;
        y = 0;
        int cont = w;
        int pulo = cont - errot;

        for (int i = tam; i >= 0; i--) {
            if (y < h) {
                if (cont >= w) {
                    i = i - pulo;
                    cont = 0;
                }
                b1[i] = (byte) posCor[y][x];

                cont++;
                x--;
                if (x == -1) {
                    y++;
                    x = w - 1;
                }
            }
        }

        System.arraycopy(b2, 0, b, 0, b2.length);
        System.arraycopy(b1, 0, b, b2.length, b1.length);

        for (int i = 0; i < b1.length; i++) {
            if (b1[i] != this.arquivo[i]) {
                System.out.println("Diferente no " + i + ": " + b1[i] + " != " + this.arquivo[i]);
            }
        }

        for (int i = 0; i < b2.length; i++) {
            if (b2[i] != this.paleta[i]) {
                System.out.println("Diferente no " + i + ": " + b2[i] + " != " + this.paleta[i]);
            }
        }
        return b;
    }

    /**
     * Método que gera o negativo da imagem
     *
     */
    public void imgNegativa() {
        int i = 0, j = 0;
        for (i = 0; i < h; i++) {
            for (j = 0; j < w; j++) {
                Color t = pixel[i][j];
                Color rgb = new Color(0);
                if (t != null) {
                    rgb = new Color((255 - t.getRed()), (255 - t.getGreen()), (255 - t.getBlue()));
                }
                pixel[i][j] = rgb;
            }
        }
    }

    public void grayScale() {
        for (int i = 0; i < (h); i++) {
            for (int j = 0; j < (w); j++) {
                this.pixel[i][j] = new Color((int) ((pixel[i][j].getRed() * 0.299)
                        + (pixel[i][j].getGreen() * 0.587)
                        + (pixel[i][j].getBlue() * 0.114)),
                        (int) ((pixel[i][j].getRed() * 0.299)
                        + (pixel[i][j].getGreen() * 0.587)
                        + (pixel[i][j].getBlue() * 0.114)),
                        (int) ((pixel[i][j].getRed() * 0.299)
                        + (pixel[i][j].getGreen() * 0.587)
                        + (pixel[i][j].getBlue() * 0.114)));
            }
        }
        grayScale = true;
    }

    public void Limiarizar(int tipoL, int limiar) {
        int tom = 0;
        grayScale();
        if (tipoL == 1) {
            for (int i = 0; i < (h); i++) {
                for (int j = 0; j < (w); j++) {
                    //Como a imagem deve estar em grayscale, o valor dos 3 canais de cor é igual,
                    //justificando a atribuição abaixo
                    tom = this.pixel[i][j].getRed();
                    if (tom < limiar) {
                        this.pixel[i][j] = new Color(0, 0, 0);
                    } else {
                        this.pixel[i][j] = new Color(255, 255, 255);
                    }
                }
            }
        } else {
            if (tipoL == 2) {
                for (int i = 0; i < (h); i++) {
                    for (int j = 0; j < (w); j++) {
                        //Como a imagem deve estar em grayscale, o valor dos 3 canais de cor é igual,
                        //justificando a atribuição abaixo
                        tom = this.pixel[i][j].getRed();
                        if (tom >= limiar) {
                            this.pixel[i][j] = new Color(255, 255, 255);
                        }
                    }
                }
            } else {
                if (tipoL == 3) {
                    for (int i = 0; i < (h); i++) {
                        for (int j = 0; j < (w); j++) {
                            //Como a imagem deve estar em grayscale, o valor dos 3 canais de cor é igual,
                            //justificando a atribuição abaixo
                            tom = this.pixel[i][j].getRed();
                            if (tom >= limiar) {
                                this.pixel[i][j] = new Color(limiar, limiar, limiar);
                            }
                        }
                    }
                }
            }
        }
    }

    public Color[][] getPixel() {
        return pixel;
    }

    public void setPixel(Color[][] pixel) {
        this.pixel = pixel;
    }

    public int getH() {
        return h;
    }

    public void setH(int h) {
        this.h = h;
    }

    public int getW() {
        return w;
    }

    public void setW(int w) {
        this.w = w;
    }

    public boolean isGrayScale() {
        return grayScale;
    }

    public void setGrayScale(boolean grayScale) {
        this.grayScale = grayScale;
    }

    public void paintPixel(Graphics g) {
        for (int i = 0; i < (h); i++) {
            for (int j = 0; j < (w); j++) {
                g.setColor(pixel[i][j]);
                g.drawLine(j, i, j, i);
            }
        }
    }

    public byte[] convert() {
        int erro = w - ((w / 4) * 4);
        byte[] b1 = new byte[(w + erro) * h + 54];
        byte[] b2 = new byte[256 * 4];

        for (int i = 0; i < b1.length; i++) {
            b1[i] = (byte) 0;
        }

        for (int i = 0; i < b2.length; i = i + 4) {
            b2[i] = (byte) i;
            b2[i + 1] = (byte) i;
            b2[i + 2] = (byte) i;
            b2[i + 3] = (byte) 0;
        }

        int cont = w * h - 1;
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                int r = pixel[i][j].getRed();
                int g = pixel[i][j].getGreen();
                int b = pixel[i][j].getBlue();
                if (r > 255) {
                    break;
                }
                if ((r != g) || (r != b) || (g != b)) {
                    break;
                } else {
                    b1[cont] = (byte) r;
                    cont--;
                }
            }
        }
        byte[] b = new byte[b1.length + b2.length];
        System.arraycopy(b2, 0, b, 0, b2.length);
        System.arraycopy(b1, 0, b, b2.length, b1.length);
        return b;
    }

    public void setArquivo(byte[] arq) {
        this.arquivo = arq;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void opAND(MatrizPixel mp2) {
        for (int i = 0; i < getH(); i++) {
            for (int j = 0; j < getW(); j++) {
                //System.out.println("i: " + i + " j: " + j);
                pixel[i][j] = new Color(
                        (pixel[i][j].getRed() & mp2.pixel[i][j].getRed()) & 0xff,
                        (pixel[i][j].getGreen() & mp2.pixel[i][j].getGreen()) & 0xff,
                        (pixel[i][j].getBlue() & mp2.pixel[i][j].getBlue()) & 0xff);
            }
        }
    }

    public void opOR(MatrizPixel mp2) {
        for (int i = 0; i < getH(); i++) {
            for (int j = 0; j < getW(); j++) {
                pixel[i][j] = new Color(
                        (pixel[i][j].getRed() | mp2.pixel[i][j].getRed()) & 0xff,
                        (pixel[i][j].getGreen() | mp2.pixel[i][j].getGreen()) & 0xff,
                        (pixel[i][j].getBlue() | mp2.pixel[i][j].getBlue()) & 0xff);
            }
        }
    }

    public void opXOR(MatrizPixel mp2) {
        for (int i = 0; i < getH(); i++) {
            for (int j = 0; j < getW(); j++) {
                pixel[i][j] = new Color(
                        (pixel[i][j].getRed() ^ mp2.pixel[i][j].getRed()) & 0xff,
                        (pixel[i][j].getGreen() ^ mp2.pixel[i][j].getGreen()) & 0xff,
                        (pixel[i][j].getBlue() ^ mp2.pixel[i][j].getBlue()) & 0xff);
            }
        }
    }

    public void convolucao(float[][] mask) {
        int h = getH() / mask.length;
        h = h * mask.length;
        int w = getW() / mask.length;
        w = w * mask.length;
        float red, green, blue;
        for (int pL = 0; pL < h; pL = pL + 3) {
            for (int pC = 0; pC < w; pC = pC + 3) {
                for (int linha = 0; linha < mask.length; linha++) {
                    for (int coluna = 0; coluna < mask.length; coluna++) {
                        red = 0;
                        blue = 0;
                        green = 0;
                        for (int i = 0; i < mask.length; i++) {
                            red = red + ((int) pixel[linha + pL][i + pC].getRed() & 0xff) * mask[i][coluna];
                            green = green + ((int) pixel[linha + pL][i + pC].getGreen() & 0xff) * mask[i][coluna];
                            blue = blue + ((int) pixel[linha + pL][i + pC].getBlue() & 0xff) * mask[i][coluna];
                        }
                        red = (int) red & 0xff;
                        green = (int) green & 0xff;
                        blue = (int) blue & 0xff;
                        pixel[pL + linha][pC + coluna] = new Color((int) red, (int) green, (int) blue);
                    }
                }
            }
        }
    }

    public void sobel() {
        float[][] mask = new float[3][3];
        mask[0][0] = (float) 0.25;
        mask[0][1] = 0;
        mask[0][2] = (float) -0.25;
        mask[1][0] = (float) 0.5;
        mask[1][1] = 0;
        mask[1][2] = (float) -0.5;
        mask[2][0] = (float) 0.25;
        mask[2][1] = 0;
        mask[2][2] = (float) -0.25;

        convolucao(mask);
        mask[0][0] = (float) -0.25;
        mask[0][1] = (float) -0.5;
        mask[0][2] = (float) -0.25;
        mask[1][0] = 0;
        mask[1][1] = 0;
        mask[1][2] = 0;
        mask[2][0] = (float) 0.25;
        mask[2][1] = (float) 0.5;
        mask[2][2] = (float) 0.25;
        convolucao(mask);
    }

    public void robert() {
        float[][] mask = new float[3][3];
        mask[0][0] = 0;
        mask[0][1] = 0;
        mask[0][2] = -1;
        mask[1][0] = 0;
        mask[1][1] = 1;
        mask[1][2] = 0;
        mask[2][0] = 0;
        mask[2][1] = 0;
        mask[2][2] = 0;

        convolucao(mask);
        mask[0][0] = -1;
        mask[0][1] = 0;
        mask[0][2] = 0;
        mask[1][0] = 0;
        mask[1][1] = 1;
        mask[1][2] = 0;
        mask[2][0] = 0;
        mask[2][1] = 0;
        mask[2][2] = 0;
        convolucao(mask);
    }

    public void dilatacao() {
        int tamanho = 2;
        int comp = 240 & 0xff;
        for (int i = tamanho; i < getH() - tamanho; i = i + tamanho) {
            for (int j = tamanho; j < getW() - tamanho; j++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red > comp) && (blue > comp) && (green > comp)) {
                    pixel[i][j] = new Color(255, 255, 255);
                    pixel[i][j + 1] = new Color(255, 255, 255);
                    pixel[i + 1][j] = new Color(255, 255, 255);
                    pixel[i - 1][j] = new Color(255, 255, 255);
                    pixel[i][j - 1] = new Color(255, 255, 255);
                    j = j + tamanho;
                }
            }
        }
        for (int j = tamanho; j < getW() - tamanho; j = j + tamanho) {
            for (int i = tamanho; i < getH() - tamanho; i++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red > comp) && (blue > comp) && (green > comp)) {
                    pixel[i][j] = new Color(255, 255, 255);
                    pixel[i][j + 1] = new Color(255, 255, 255);
                    pixel[i + 1][j] = new Color(255, 255, 255);
                    pixel[i - 1][j] = new Color(255, 255, 255);
                    pixel[i][j - 1] = new Color(255, 255, 255);
                    i = i + tamanho;
                }
            }
        }
        tamanho++;
        for (int i = tamanho; i < getH() - tamanho; i = i + tamanho) {
            for (int j = tamanho; j < getW() - tamanho; j++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red > comp) && (blue > comp) && (green > comp)) {
                    pixel[i][j] = new Color(255, 255, 255);
                    pixel[i][j + 1] = new Color(255, 255, 255);
                    pixel[i + 1][j] = new Color(255, 255, 255);
                    pixel[i - 1][j] = new Color(255, 255, 255);
                    pixel[i][j - 1] = new Color(255, 255, 255);
                    j = j + tamanho;
                }
            }
        }
        for (int j = tamanho; j < getW() - tamanho; j = j + tamanho) {
            for (int i = tamanho; i < getH() - tamanho; i++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red > comp) && (blue > comp) && (green > comp)) {
                    pixel[i][j] = new Color(255, 255, 255);
                    pixel[i][j + 1] = new Color(255, 255, 255);
                    pixel[i + 1][j] = new Color(255, 255, 255);
                    pixel[i - 1][j] = new Color(255, 255, 255);
                    pixel[i][j - 1] = new Color(255, 255, 255);
                    i = i + tamanho;
                }
            }
        }
    }

    public void erosao() {
        int tamanho = 2;
        int comp = 240 & 0xff;
        for (int i = tamanho; i < getH() - tamanho; i = i + tamanho) {
            for (int j = tamanho; j < getW() - tamanho; j++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red < comp) && (blue < comp) && (green < comp)) {
                    pixel[i][j] = new Color(0, 0, 0);
                    pixel[i][j + 1] = new Color(0, 0, 0);
                    pixel[i + 1][j] = new Color(0, 0, 0);
                    pixel[i - 1][j] = new Color(0, 0, 0);
                    pixel[i][j - 1] = new Color(0, 0, 0);
                    j = j + tamanho;
                }
            }
        }
        for (int j = tamanho; j < getW() - tamanho; j = j + tamanho) {
            for (int i = tamanho; i < getH() - tamanho; i++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red < comp) && (blue < comp) && (green < comp)) {
                    pixel[i][j] = new Color(0, 0, 0);
                    pixel[i][j + 1] = new Color(0, 0, 0);
                    pixel[i + 1][j] = new Color(0, 0, 0);
                    pixel[i - 1][j] = new Color(0, 0, 0);
                    pixel[i][j - 1] = new Color(0, 0, 0);
                    i = i + tamanho;
                }
            }
        }
        for (int i = tamanho; i < getH() - tamanho; i = i + tamanho) {
            for (int j = tamanho; j < getW() - tamanho; j++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red < comp) && (blue < comp) && (green < comp)) {
                    pixel[i][j] = new Color(0, 0, 0);
                    pixel[i][j + 1] = new Color(0, 0, 0);
                    pixel[i + 1][j] = new Color(0, 0, 0);
                    pixel[i - 1][j] = new Color(0, 0, 0);
                    pixel[i][j - 1] = new Color(0, 0, 0);
                    j = j + tamanho;
                }
            }
        }
        tamanho++;
        for (int j = tamanho; j < getW() - tamanho; j = j + tamanho) {
            for (int i = tamanho; i < getH() - tamanho; i++) {
                int red = pixel[i][j].getRed() & 0xff;
                int green = pixel[i][j].getGreen() & 0xff;
                int blue = pixel[i][j].getBlue() & 0xff;
                if ((red < comp) && (blue < comp) && (green < comp)) {
                    pixel[i][j] = new Color(0, 0, 0);
                    pixel[i][j + 1] = new Color(0, 0, 0);
                    pixel[i][j + 1] = new Color(0, 0, 0);
                    pixel[i + 1][j] = new Color(0, 0, 0);
                    pixel[i - 1][j] = new Color(0, 0, 0);
                    pixel[i][j - 1] = new Color(0, 0, 0);
                    i = i + tamanho;
                }
            }
        }
    }

    public void abertura(int n) {
        for (int i = 0; i < n; i++) {
            erosao();
        }
        for (int i = 0; i < n; i++) {
            dilatacao();
        }
    }

    public void fechamento(int n) {
        for (int i = 0; i < n; i++) {
            dilatacao();
        }
        for (int i = 0; i < n; i++) {
            erosao();
        }
    }

    public void preProceso() {
        imgNegativa();
        grayScale();
        sobel();
        Limiarizar(1, 200);
        dilatacao();
    }

}
