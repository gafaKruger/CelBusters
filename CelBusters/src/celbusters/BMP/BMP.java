/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters.BMP;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
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

    public BMP(File file) {
        FileInputStream fi = null;
        try {
            fi = new FileInputStream(file);
            byte b;
            b = (byte) fi.read();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(BMP.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fi.close();
            } catch (IOException ex) {
                Logger.getLogger(BMP.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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
