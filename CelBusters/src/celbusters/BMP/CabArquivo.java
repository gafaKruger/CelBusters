/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters.BMP;

/**
 *
 * @author Samuel Blum Vorpagel
 */
public class CabArquivo {

    byte[] BfType;
    byte[] BfSyze;
    byte[] BfRaser1;
    byte[] BfRaser2;
    byte[] BfOffSetBits;

    public CabArquivo(byte[] BfType, byte[] BfSype, byte[] BfRaser1, byte[] BfRaser2, byte[] BfOffSetBits) {
        this.BfType = BfType;
        this.BfSyze = BfSype;
        this.BfRaser1 = BfRaser1;
        this.BfRaser2 = BfRaser2;
        this.BfOffSetBits = BfOffSetBits;
    }

    public CabArquivo(byte[] b) {
        this.BfType = new byte[2];
        this.BfSyze = new byte[4];
        this.BfRaser1 = new byte[2];
        this.BfRaser2 = new byte[2];
        this.BfOffSetBits = new byte[4];

        int controlador = 0;

        for (int i = controlador; i < 2 + controlador; i++) {
            this.BfType[i - controlador] = b[i];
        }
        controlador = 2 + controlador;
        for (int i = controlador; i < 4 + controlador; i++) {
            this.BfSyze[i - controlador] = b[i];
        }
        controlador = 4 + controlador;
        for (int i = controlador; i < 2 + controlador; i++) {
            this.BfRaser1[i - controlador] = b[i];
        }
        controlador = 2 + controlador;
        for (int i = controlador; i < 2 + controlador; i++) {
            this.BfRaser2[i - controlador] = b[i];
        }
        for (int i = controlador; i < 4 + controlador; i++) {
            this.BfOffSetBits[i - controlador] = b[i];
        }
        controlador = 4 + controlador;
        System.out.println(controlador);
    }
    
    public String impInf () {
        String aux;
        aux = "Tipo: " + this.BfType[0] + " " + this.BfType[1] + "\n";
        aux = aux + "Tamanho: " + this.BfSyze[0] + " " + this.BfSyze[1] + " "
                + this.BfSyze[2] + " " + this.BfSyze[3] + "\n"; 
        aux = aux + "Reservado 1: " + this.BfRaser1[0] + " " + this.BfRaser1[1] + "\n";
        aux = aux + "Reservado 2: " + this.BfRaser2[0] + " " + this.BfRaser2[1] + "\n";
        aux = aux + "OffSetBit: " + this.BfOffSetBits[0] + " " + this.BfOffSetBits[1] + " "
                + this.BfOffSetBits[2] + " " + this.BfOffSetBits[3] + "\n";
        
        if (this.BfOffSetBits[2] == 54) {
            aux = aux + "Imagen True Coler";
        } else {
            aux = aux + "Paleta de Cores";
        }
        return aux; 
    }
    
    

    public byte[] getBfType() {
        return BfType;
    }

    public void setBfType(byte[] BfType) {
        this.BfType = BfType;
    }

    public byte[] getBfSyze() {
        return BfSyze;
    }

    public void setBfSyze(byte[] BfSyze) {
        this.BfSyze = BfSyze;
    }

    public byte[] getBfRaser1() {
        return BfRaser1;
    }

    public void setBfRaser1(byte[] BfRaser1) {
        this.BfRaser1 = BfRaser1;
    }

    public byte[] getBfRaser2() {
        return BfRaser2;
    }

    public void setBfRaser2(byte[] BfRaser2) {
        this.BfRaser2 = BfRaser2;
    }

    public byte[] getBfOffSetBits() {
        return BfOffSetBits;
    }

    public void setBfOffSetBits(byte[] BfOffSetBits) {
        this.BfOffSetBits = BfOffSetBits;
    }
}
