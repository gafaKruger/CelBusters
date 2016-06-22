/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @Autores: Samuel Blum Vorpagel - Rafael Fiori Kruger
 */
public class BMP {

    //Cabeçalho de Arquivo
    private byte[] BfType; //2
    private byte[] BfSize; //4
    private byte[] BfReser1; //2
    private byte[] BfReser2; //2
    private byte[] BfOffSetBits; //4
    //Cabeçalho de Imagem
    private byte[] BiSize; //4
    private byte[] BiWidth; //4
    private byte[] BiHeight; //4
    private byte[] BiPlanes; //2
    private byte[] BiBitCount; //2
    private byte[] BiCompress; //4
    private byte[] BiSizeImag; //4
    private byte[] BiXPPMeter; //4
    private byte[] BiYPPMeter; //4
    private byte[] BiClrUsed; //4
    private byte[] BiClrImpor; //4
    private boolean trueColor;
    private boolean valido;
    private byte[] paletaCores;
    private byte[] arquivo;
    private MatrizPixel mp;
    private byte[] tudo;

    public BMP(byte[] b) {
        this.tudo = b;

        //Cabeçaho do Arquivo
        this.BfType = new byte[2];
        System.arraycopy(b, 0, this.BfType, 0, this.BfType.length);

        if ((this.BfType[0] == 66) && (this.BfType[1] == 77)) {
            this.valido = true;
        } else {
            this.valido = false;
        }

        this.BfSize = new byte[4];
        System.arraycopy(b, 2, this.BfSize, 0, this.BfSize.length);

        this.BfReser1 = new byte[2];
        System.arraycopy(b, 6, this.BfReser1, 0, this.BfReser1.length);

        this.BfReser2 = new byte[2];
        System.arraycopy(b, 8, this.BfReser2, 0, this.BfReser2.length);

        this.BfOffSetBits = new byte[4];
        System.arraycopy(b, 10, this.BfOffSetBits, 0, this.BfOffSetBits.length);

        //Cabeçalho da Imagen
        this.BiSize = new byte[4];
        System.arraycopy(b, 14, this.BiSize, 0, this.BiSize.length);

        this.BiWidth = new byte[4];
        System.arraycopy(b, 18, this.BiWidth, 0, this.BiWidth.length);

        this.BiHeight = new byte[4];
        System.arraycopy(b, 22, this.BiHeight, 0, this.BiHeight.length);

        this.BiPlanes = new byte[2];
        System.arraycopy(b, 26, this.BiPlanes, 0, this.BiPlanes.length);

        this.BiBitCount = new byte[2];
        System.arraycopy(b, 28, this.BiBitCount, 0, this.BiBitCount.length);
        System.out.println("Bits Pixel: " + this.getBiBitCount());

        this.BiCompress = new byte[4];
        System.arraycopy(b, 30, this.BiCompress, 0, this.BiCompress.length);

        this.BiSizeImag = new byte[4];
        System.arraycopy(b, 34, this.BiSizeImag, 0, this.BiSizeImag.length);

        this.BiXPPMeter = new byte[4];
        System.arraycopy(b, 38, this.BiXPPMeter, 0, this.BiXPPMeter.length);

        this.BiYPPMeter = new byte[4];
        System.arraycopy(b, 42, this.BiYPPMeter, 0, this.BiYPPMeter.length);

        this.BiClrUsed = new byte[4];
        System.arraycopy(b, 46, this.BiClrUsed, 0, this.BiClrUsed.length);

        this.BiClrImpor = new byte[4];
        System.arraycopy(b, 50, this.BiClrImpor, 0, this.BiClrImpor.length);

        if (getTipo() == 54) {
            this.trueColor = true;
            this.arquivo = new byte[b.length - getTipo()];
            System.arraycopy(b, 54, this.arquivo, 0, this.arquivo.length);
            this.mp = new MatrizPixel(arquivo, getHeigth(), getWidht(), getBiBitCount());
        } else {
            this.trueColor = false;
            this.paletaCores = new byte[getBiClrUsed() * 4];
            System.arraycopy(b, 54, this.paletaCores, 0, this.paletaCores.length);
            this.arquivo = new byte[b.length - getTipo()];
            System.out.println(b.length);
            System.out.println((54 + paletaCores.length) + arquivo.length);
            System.out.println(arquivo.length);
            System.arraycopy(b, (54 + paletaCores.length), this.arquivo, 0, this.arquivo.length);

            System.out.println(getBiBitCount());
            this.mp = new MatrizPixel(paletaCores, arquivo, getHeigth(), getWidht(), getBiBitCount());
        }
    }

    public BMP(BMP b) {
        //Cabeçalho de Arquivo
        this.BfType = b.BfType; //2
        this.BfSize = b.BfSize; //4
        this.BfReser1 = b.BfReser1; //2
        this.BfReser2 = b.BfReser2; //2
        this.BfOffSetBits = b.BfOffSetBits; //4
        //Cabeçalho de Imagem
        this.BiSize = b.BiSize; //4
        this.BiWidth = b.BiWidth; //4
        this.BiHeight = b.BiHeight; //4
        this.BiPlanes = b.BiPlanes; //2
        this.BiBitCount = b.BiBitCount; //2
        this.BiCompress = b.BiCompress; //4
        this.BiSizeImag = b.BiSizeImag; //4
        this.BiXPPMeter = b.BiXPPMeter; //4
        this.BiYPPMeter = b.BiYPPMeter; //4
        this.BiClrUsed = b.BiClrUsed; //4
        this.BiClrImpor = b.BiClrImpor; //4
        this.trueColor = b.trueColor;
        this.valido = b.valido;
        this.paletaCores = b.paletaCores;
        this.arquivo = b.arquivo;
        this.mp = b.mp;
        this.tudo = b.tudo;
    }

    public int getWidht() {
        int val = ((256 * ((byte) BiWidth[1] & 0xFF))
                + ((byte) BiWidth[0] & 0xFF)
                + (65536 * ((byte) BiWidth[2] & 0xFF))
                + (16777216 * ((byte) BiWidth[3] & 0xFF)));
        return val;
    }

    public int getHeigth() {
        int val = ((256 * ((byte) BiHeight[1] & 0xFF))
                + ((byte) BiHeight[0] & 0xFF)
                + (65536 * ((byte) BiHeight[2] & 0xFF))
                + (16777216 * ((byte) BiHeight[3] & 0xFF)));
        return val;
    }

    public int getBitSize() {
        int val = ((256 * ((byte) BiSize[1] & 0xFF))
                + ((byte) BiSize[0] & 0xFF)
                + (65536 * ((byte) BiSize[2] & 0xFF))
                + (16777216 * ((byte) BiSize[3] & 0xFF)));
        return val;
    }

    public int getTipo() {
        int val = ((256 * ((byte) BfOffSetBits[1] & 0xFF))
                + ((byte) BfOffSetBits[0] & 0xFF)
                + (65536 * ((byte) BfOffSetBits[2] & 0xFF))
                + (16777216 * ((byte) BfOffSetBits[3] & 0xFF)));
        if (!trueColor) {
            val = val + (4 * getBiClrUsed());
        }
        return val;
    }

    public int getBiClrUsed() {
        int val = ((256 * ((byte) BiClrUsed[1] & 0xFF))
                + ((byte) BiClrUsed[0] & 0xFF)
                + (65536 * ((byte) BiClrUsed[2] & 0xFF))
                + (16777216 * ((byte) BiClrUsed[3] & 0xFF)));
        return val;
    }

    public int getBiBitCount() {
        int val = ((256 * ((byte) BiBitCount[1] & 0xFF))
                + ((byte) BiBitCount[0] & 0xFF));
        return val;
    }

    public byte[] save() {
        byte[] s = new byte[this.tudo.length];
        s = tudo.clone();
        byte[] aux;
        if (isTrueColor()) {
            aux = this.mp.get32();
        } else {
            aux = this.mp.get8();
        }
        System.arraycopy(aux, 0, s, 54, aux.length);
        return s;
    }

    public byte[] convert24to8(BMP bmp) {
        byte[] aux = bmp.mp.convert();
        byte[] aux3 = new byte[aux.length + 54];
        byte[] s = bmp.tudo.clone();
        s[11] = (byte) 4;
        System.arraycopy(s, 0, aux3, 0, 54);
        System.arraycopy(aux, 0, aux3, 54, aux.length);
        return aux3;
    }

    public byte[] getTudo() {
        return tudo;
    }

    public void setTudo(byte[] tudo) {
        this.tudo = tudo;
    }

    public boolean isValido() {
        return valido;
    }

    public void setValido(boolean valido) {
        this.valido = valido;
    }

    public boolean isTrueColor() {
        return trueColor;
    }

    public void setTrueColor(boolean trueColor) {
        this.trueColor = trueColor;
    }

    public byte[] getBfType() {
        return BfType;
    }

    public void setBfType(byte[] BfType) {
        this.BfType = BfType;
    }

    public byte[] getBfSize() {
        return BfSize;
    }

    public void setBfSize(byte[] BfSize) {
        this.BfSize = BfSize;
    }

    public byte[] getBfReser1() {
        return BfReser1;
    }

    public void setBfReser1(byte[] BfReser1) {
        this.BfReser1 = BfReser1;
    }

    public byte[] getBfReser2() {
        return BfReser2;
    }

    public void setBfReser2(byte[] BfReser2) {
        this.BfReser2 = BfReser2;
    }

    public byte[] getBfOffSetBits() {
        return BfOffSetBits;
    }

    public void setBfOffSetBits(byte[] BfOffSetBits) {
        this.BfOffSetBits = BfOffSetBits;
    }

    public byte[] getBiSize() {
        return BiSize;
    }

    public void setBiSize(byte[] BiSize) {
        this.BiSize = BiSize;
    }

    public byte[] getBiWidth() {
        return BiWidth;
    }

    public void setBiWidth(byte[] BiWidth) {
        this.BiWidth = BiWidth;
    }

    public byte[] getBiHeight() {
        return BiHeight;
    }

    public void setBiHeight(byte[] BiHeight) {
        this.BiHeight = BiHeight;
    }

    public byte[] getBiPlanes() {
        return BiPlanes;
    }

    public void setBiPlanes(byte[] BiPlanes) {
        this.BiPlanes = BiPlanes;
    }

    public byte[] getByteBiBitCount() {
        return BiBitCount;
    }

    public void setBiBitCount(byte[] BiBitCount) {
        this.BiBitCount = BiBitCount;
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

    public byte[] getByteBiClrUsed() {
        return BiClrUsed;
    }

    public void setBiClrUsed(byte[] BiClrUsed) {
        this.BiClrUsed = BiClrUsed;
    }

    public byte[] getBiClrImpor() {
        return BiClrImpor;
    }

    public void setBiClrImpor(byte[] BiClrImpor) {
        this.BiClrImpor = BiClrImpor;
    }

    public byte[] getPaletaCores() {
        return paletaCores;
    }

    public void setPaletaCores(byte[] paletaCores) {
        this.paletaCores = paletaCores;
    }

    public byte[] getArquivo() {
        return arquivo;
    }

    public void setArquivo(byte[] arquivo) {
        this.arquivo = arquivo;
    }

    public MatrizPixel getMp() {
        return mp;
    }

    public void setMp(MatrizPixel mp) {
        this.mp = mp;
    }
}
