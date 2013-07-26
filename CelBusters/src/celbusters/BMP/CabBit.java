/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters.BMP;

/**
 *
 * @author Samuel Blum Vorpagel
 */
public class CabBit {
    Byte[] BiSize;
    Byte[] BiWidh;
    Byte[] BiHeigh;
    Byte[] BiPlains;
    Byte[] BiCont;
    Byte[] BiCompress;
    Byte[] BiSizeImag;
    Byte[] BiXPPMeter;
    Byte[] BiYPPMeter;
    Byte[] BiClrUsed;
    Byte[] BiClrImport;

    public CabBit(Byte[] BiSize, Byte[] BiWidh, Byte[] BiHeigh, Byte[] BiPlains, Byte[] BiCont, Byte[] BiCompress, Byte[] BiSizeImag, Byte[] BiXPPMeter, Byte[] BiYPPMeter, Byte[] BiClrUsed, Byte[] BiClrImport) {
        this.BiSize = BiSize;
        this.BiWidh = BiWidh;
        this.BiHeigh = BiHeigh;
        this.BiPlains = BiPlains;
        this.BiCont = BiCont;
        this.BiCompress = BiCompress;
        this.BiSizeImag = BiSizeImag;
        this.BiXPPMeter = BiXPPMeter;
        this.BiYPPMeter = BiYPPMeter;
        this.BiClrUsed = BiClrUsed;
        this.BiClrImport = BiClrImport;
    }

    public Byte[] getBiSize() {
        return BiSize;
    }

    public void setBiSize(Byte[] BiSize) {
        this.BiSize = BiSize;
    }

    public Byte[] getBiWidh() {
        return BiWidh;
    }

    public void setBiWidh(Byte[] BiWidh) {
        this.BiWidh = BiWidh;
    }

    public Byte[] getBiHeigh() {
        return BiHeigh;
    }

    public void setBiHeigh(Byte[] BiHeigh) {
        this.BiHeigh = BiHeigh;
    }

    public Byte[] getBiPlains() {
        return BiPlains;
    }

    public void setBiPlains(Byte[] BiPlains) {
        this.BiPlains = BiPlains;
    }

    public Byte[] getBiCont() {
        return BiCont;
    }

    public void setBiCont(Byte[] BiCont) {
        this.BiCont = BiCont;
    }

    public Byte[] getBiCompress() {
        return BiCompress;
    }

    public void setBiCompress(Byte[] BiCompress) {
        this.BiCompress = BiCompress;
    }

    public Byte[] getBiSizeImag() {
        return BiSizeImag;
    }

    public void setBiSizeImag(Byte[] BiSizeImag) {
        this.BiSizeImag = BiSizeImag;
    }

    public Byte[] getBiXPPMeter() {
        return BiXPPMeter;
    }

    public void setBiXPPMeter(Byte[] BiXPPMeter) {
        this.BiXPPMeter = BiXPPMeter;
    }

    public Byte[] getBiYPPMeter() {
        return BiYPPMeter;
    }

    public void setBiYPPMeter(Byte[] BiYPPMeter) {
        this.BiYPPMeter = BiYPPMeter;
    }

    public Byte[] getBiClrUsed() {
        return BiClrUsed;
    }

    public void setBiClrUsed(Byte[] BiClrUsed) {
        this.BiClrUsed = BiClrUsed;
    }

    public Byte[] getBiClrImport() {
        return BiClrImport;
    }

    public void setBiClrImport(Byte[] BiClrImport) {
        this.BiClrImport = BiClrImport;
    }
    
}
