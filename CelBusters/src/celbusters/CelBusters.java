/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package celbusters;

import celbusters.BMP.BMP;
import java.io.File;

/**
 *
 * @author samuel
 */
public class CelBusters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        File f = new File("lena.bmp");
        BMP bmp = new BMP(f);
    }
}
