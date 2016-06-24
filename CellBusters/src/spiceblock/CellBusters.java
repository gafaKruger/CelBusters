package spiceblock;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import Controller.Controller;
import View.PainelPrincipal;
import java.awt.Frame;
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
                    (null, ex.getMessage(), "Erro ao carregar configurações de aparência (javax.swing.UIManager).", 
                    JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }        
        Controller C = new Controller(); 
        PainelPrincipal img = new PainelPrincipal(C);
        //C.setPainel(img);
        img.setExtendedState(Frame.MAXIMIZED_BOTH);
        //img.setResizable(false);
        img.setVisible(true); 
    }
}
