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
    Byte[] BfType;
    Byte[] BfSype;
    Byte[] BfRaser1;
    Byte[] BfRaser2;
    Byte[] BfOffSetBits;

    public CabArquivo(Byte[] BfType, Byte[] BfSype, Byte[] BfRaser1, Byte[] BfRaser2, Byte[] BfOffSetBits) {
        this.BfType = BfType;
        this.BfSype = BfSype;
        this.BfRaser1 = BfRaser1;
        this.BfRaser2 = BfRaser2;
        this.BfOffSetBits = BfOffSetBits;
    }

    public Byte[] getBfType() {
        return BfType;
    }

    public void setBfType(Byte[] BfType) {
        this.BfType = BfType;
    }

    public Byte[] getBfSype() {
        return BfSype;
    }

    public void setBfSype(Byte[] BfSype) {
        this.BfSype = BfSype;
    }

    public Byte[] getBfRaser1() {
        return BfRaser1;
    }

    public void setBfRaser1(Byte[] BfRaser1) {
        this.BfRaser1 = BfRaser1;
    }

    public Byte[] getBfRaser2() {
        return BfRaser2;
    }

    public void setBfRaser2(Byte[] BfRaser2) {
        this.BfRaser2 = BfRaser2;
    }

    public Byte[] getBfOffSetBits() {
        return BfOffSetBits;
    }

    public void setBfOffSetBits(Byte[] BfOffSetBits) {
        this.BfOffSetBits = BfOffSetBits;
    }
    
}
