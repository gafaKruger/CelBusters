package Controller;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Model.BMP;
import View.JFImagem;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @Autores: Samuel Blum Vorpagel - Rafael Fiori Kruger
 */
public class CellBusters {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try{
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
            //String cname = UIManager.getSystemLookAndFeelClassName();
            //UIManager.setLookAndFeel(cname);
        }catch(Exception ex){
            JOptionPane.showMessageDialog
                    (null, ex.getMessage(), "Erro ao carregar Look and Feel (javax.swing.UIManager).", 
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }        
        JFImagem img = new JFImagem();
        img.setVisible(true); 
    }
}
