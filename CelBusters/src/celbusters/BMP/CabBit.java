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
    byte[] BiSize;
    byte[] BiWidh;
    byte[] BiHeigh;
    byte[] BiPlains;
    byte[] BiCont;
    byte[] BiCompress;
    byte[] BiSizeImag;
    byte[] BiXPPMeter;
    byte[] BiYPPMeter;
    byte[] BiClrUsed;
    byte[] BiClrImport;

    public CabBit(byte[] BiSize, byte[] BiWidh, byte[] BiHeigh, byte[] BiPlains, byte[] BiCont, byte[] BiCompress, byte[] BiSizeImag, byte[] BiXPPMeter, byte[] BiYPPMeter, byte[] BiClrUsed, byte[] BiClrImport) {
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

    public byte[] getBiSize() {
        return BiSize;
    }

    public void setBiSize(byte[] BiSize) {
        this.BiSize = BiSize;
    }

    public byte[] getBiWidh() {
        return BiWidh;
    }

    public void setBiWidh(byte[] BiWidh) {
        this.BiWidh = BiWidh;
    }

    public byte[] getBiHeigh() {
        return BiHeigh;
    }

    public void setBiHeigh(byte[] BiHeigh) {
        this.BiHeigh = BiHeigh;
    }

    public byte[] getBiPlains() {
        return BiPlains;
    }

    public void setBiPlains(byte[] BiPlains) {
        this.BiPlains = BiPlains;
    }

    public byte[] getBiCont() {
        return BiCont;
    }

    public void setBiCont(byte[] BiCont) {
        this.BiCont = BiCont;
    }

    public byte[] getBiCompress() {
        return BiCompress;
    }

    public void setBiCompress(byte[] BiCompress) {
        this.BiCompress = BiCompress;
    }

    public byte[] getBiSizeImag() {
        return BiSizeImag;
    }

    public void setBiSizeImag(byte[] BiSizeImag) {
        this.BiSizeImag = BiSizeImag;
    }

    public byte[] getBiXPPMeter() {
        return BiXPPMeter;
    }

    public void setBiXPPMeter(byte[] BiXPPMeter) {
        this.BiXPPMeter = BiXPPMeter;
    }

    public byte[] getBiYPPMeter() {
        return BiYPPMeter;
    }

    public void setBiYPPMeter(byte[] BiYPPMeter) {
        this.BiYPPMeter = BiYPPMeter;
    }

    public byte[] getBiClrUsed() {
        return BiClrUsed;
    }

    public void setBiClrUsed(byte[] BiClrUsed) {
        this.BiClrUsed = BiClrUsed;
    }

    public byte[] getBiClrImport() {
        return BiClrImport;
    }

    public void setBiClrImport(byte[] BiClrImport) {
        this.BiClrImport = BiClrImport;
    }
    
}
