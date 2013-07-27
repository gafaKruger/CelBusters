/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters.BMP;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Samuel Blum Vorpagel
 */
public class BMP {

    AreaDados areaDados;
    CabArquivo cabArquivo;
    CabBit cabBit;
    PalCor palCor;
    byte[] arq;

    public BMP(File file) {
        try {
            try (RandomAccessFile rf = new RandomAccessFile(file, "r")) {
                this.arq = new byte[(int) rf.length()];
                rf.read(arq);
                rf.close();
                byte[] ca = new byte[14];
                
                for (int i = 0; i < ca.length; i++) {
                    ca[i] = this.arq[i];
                }
                this.cabArquivo = new CabArquivo(ca);
            }
        } catch (IOException ex) {
            Logger.getLogger(BMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    public boolean saveImage (File file) {
        try {
            RandomAccessFile rf = new RandomAccessFile(file, "rwd");
            rf.write(this.arq);
            return true;
        } catch (IOException ex) {
            Logger.getLogger(BMP.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public AreaDados getAreaDados() {
        return areaDados;
    }

    public void setAreaDados(AreaDados areaDados) {
        this.areaDados = areaDados;
    }

    public CabArquivo getCabArquivo() {
        return cabArquivo;
    }

    public void setCabArquivo(CabArquivo cabArquivo) {
        this.cabArquivo = cabArquivo;
    }

    public CabBit getCabBit() {
        return cabBit;
    }

    public void setCabBit(CabBit cabBit) {
        this.cabBit = cabBit;
    }

    public PalCor getPalCor() {
        return palCor;
    }

    public void setPalCor(PalCor palCor) {
        this.palCor = palCor;
    }
}
