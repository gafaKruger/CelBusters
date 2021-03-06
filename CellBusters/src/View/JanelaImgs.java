/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import spiceblock.CellBusters;
import Model.BMP;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @Autores: Samuel Blum Vorpagel - Rafael Fiori Kruger
 */
public class JanelaImgs extends javax.swing.JFrame {

    private BMP b;
    private OPLogicasControlador ctrl;
    private int id;

    /**
     * Creates new form JanelaImgs
     */
    public JanelaImgs() {
        initComponents();
    }

    public JanelaImgs(BMP bmp) {
        initComponents();
        this.b = bmp;
        id = -1;
    }

    public JanelaImgs(BMP bmp, OPLogicasControlador c, int num_id) {
        this.b = bmp;
        initComponents();
        ctrl = c;
        id = num_id;
    }

    public void setId(int num) {
        id = num;
    }

    public int getId() {
        return id;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        imagemPanel = new View.imagemPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuAbrir = new javax.swing.JMenuItem();
        MenuSalvar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        MenuSair = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                JanelaImgs.this.windowClosed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        javax.swing.GroupLayout imagemPanelLayout = new javax.swing.GroupLayout(imagemPanel);
        imagemPanel.setLayout(imagemPanelLayout);
        imagemPanelLayout.setHorizontalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 726, Short.MAX_VALUE)
        );
        imagemPanelLayout.setVerticalGroup(
            imagemPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 464, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(imagemPanel);

        jMenu1.setText("Arquivo");

        MenuAbrir.setText("Abrir");
        MenuAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAbrirActionPerformed(evt);
            }
        });
        jMenu1.add(MenuAbrir);

        MenuSalvar.setText("Salvar");
        MenuSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSalvarActionPerformed(evt);
            }
        });
        jMenu1.add(MenuSalvar);
        jMenu1.add(jSeparator1);

        MenuSair.setText("Sair");
        MenuSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuSairActionPerformed(evt);
            }
        });
        jMenu1.add(MenuSair);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 731, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 453, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 453, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public imagemPanel getImagem() {
        return imagemPanel;
    }

    public void setImagem(imagemPanel img) {
        this.imagemPanel = img;
    }

    private void MenuSairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSairActionPerformed
        this.setVisible(false);
    }//GEN-LAST:event_MenuSairActionPerformed

    private void MenuAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAbrirActionPerformed
        JFileChooser arquivo = new JFileChooser();
        int retorno = arquivo.showOpenDialog(null);
        String nomeImg;
        if (retorno == arquivo.APPROVE_OPTION) {
            try {
                File f = arquivo.getSelectedFile();
                RandomAccessFile rf = new RandomAccessFile(f, "r");
                byte[] b = new byte[(int) rf.length()];
                rf.read(b);
                rf.close();
                this.b = new BMP(b);
            } catch (IOException ex) {
                Logger.getLogger(CellBusters.class.getName()).log(Level.SEVERE, null, ex);
            }
            nomeImg = arquivo.getSelectedFile().getName();
            this.setTitle(nomeImg);
            this.getImagem().setImagem(this.b);
            this.getImagem().reset();
            this.getImagem().update();
        }
    }//GEN-LAST:event_MenuAbrirActionPerformed

    private void MenuSalvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuSalvarActionPerformed
        JFileChooser arquivo = new JFileChooser();
        int retorno = arquivo.showSaveDialog(null);
        String nomeImg;
        if (retorno == arquivo.APPROVE_OPTION) {
            try {
                File f = arquivo.getSelectedFile();
                RandomAccessFile rf = new RandomAccessFile(f, "rw");
                rf.write(this.b.save());
                rf.close();
            } catch (IOException ex) {
                Logger.getLogger(PainelPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
            nomeImg = arquivo.getSelectedFile().getName();
            this.setTitle(nomeImg);
        }
    }//GEN-LAST:event_MenuSalvarActionPerformed

    private void windowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_windowClosed
        if (id != -1) {
            ctrl.excluirElementoLista(id);
        }
    }//GEN-LAST:event_windowClosed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JanelaImgs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JanelaImgs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JanelaImgs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JanelaImgs.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JanelaImgs().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem MenuAbrir;
    private javax.swing.JMenuItem MenuSair;
    private javax.swing.JMenuItem MenuSalvar;
    private View.imagemPanel imagemPanel;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    // End of variables declaration//GEN-END:variables
}
