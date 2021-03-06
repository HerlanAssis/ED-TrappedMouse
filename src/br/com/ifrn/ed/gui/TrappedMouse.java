/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.ifrn.ed.gui;

import java.awt.GridLayout;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author herlan
 */
public class TrappedMouse extends javax.swing.JFrame {

    private JLabel[] lab;
    private final char exitMarker = 'e', entryMarker = 'm', visited = '.';
    private final char passage = '0', wall = '1';
    private String sWall = "/br/com/ifrn/ed/gui/Wall.png";
    private String sPassage = "/br/com/ifrn/ed/gui/Passage.png";
    private String sCheese = "/br/com/ifrn/ed/gui/Cheese.png";
    private String sVisited = "/br/com/ifrn/ed/gui/Visited.png";
    private String sEntry = "/br/com/ifrn/ed/gui/House.png";
    private String sMouse = "/br/com/ifrn/ed/gui/Mouse.png";
    private ImageIcon iconWall, iconPassage, iconCheese, iconVisited, IconEntry, iconMouse;
    int tam = 0;

    /**
     * Creates new form TrappedMouse
     *
     * @param rows
     * @param cols
     */
    public TrappedMouse(int rows, int cols) {
        initComponents();
        GridLayout grid = new GridLayout(rows, cols);
        tam = rows * cols;
        lab = new JLabel[tam];
        PainelLabirinto.setLayout(grid);

        IconEntry = new ImageIcon(getClass().getResource(sEntry));
        iconCheese = new ImageIcon(getClass().getResource(sCheese));
        iconMouse = new ImageIcon(getClass().getResource(sMouse));
        iconPassage = new ImageIcon(getClass().getResource(sPassage));
        iconVisited = new ImageIcon(getClass().getResource(sVisited));
        iconWall = new ImageIcon(getClass().getResource(sWall));
        
        resizeImg(IconEntry);
        resizeImg(iconCheese);
        resizeImg(iconMouse);
        resizeImg(iconPassage);
        resizeImg(iconVisited);
        resizeImg(iconWall);

        for (int i = 0; i < tam; i++) {
            lab[i] = new JLabel();
            PainelLabirinto.add(lab[i]);
        }
    }

    public void mountMaze(String maze, int mouse) {
        char[] mazeCharArray = maze.toCharArray();
        for (int i = 0; i < tam; i++) {
            Character mazePoint = mazeCharArray[i];
            if (i == mouse) {
                lab[i].setIcon(iconMouse);
            } else {
                switch (mazePoint) {
                    case wall:
                        lab[i].setIcon(iconWall);
                        break;
                    case visited:
                        lab[i].setIcon(iconVisited);
                        break;
                    case passage:
                        lab[i].setIcon(iconPassage);
                        break;
                    case entryMarker:
                        lab[i].setIcon(IconEntry);
                        break;
                    case exitMarker:
                        lab[i].setIcon(iconCheese);
                        break;
                }
            }
        }
        
        try {
            Thread.sleep(450);
        } catch (InterruptedException ex) {
            Logger.getLogger(TrappedMouse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void resizeImg(ImageIcon imageIcon){
        final int ALTURA = 20, LARGURA = 20;
        imageIcon.setImage(imageIcon.getImage().getScaledInstance(LARGURA, ALTURA, 100));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        PainelFundo = new javax.swing.JPanel();
        PainelLabirinto = new javax.swing.JPanel();

        javax.swing.GroupLayout jLayeredPane1Layout = new javax.swing.GroupLayout(jLayeredPane1);
        jLayeredPane1.setLayout(jLayeredPane1Layout);
        jLayeredPane1Layout.setHorizontalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jLayeredPane1Layout.setVerticalGroup(
            jLayeredPane1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trapped Mouse");
        setMinimumSize(new java.awt.Dimension(1024, 768));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PainelFundo.setBackground(java.awt.Color.white);
        PainelFundo.setMinimumSize(new java.awt.Dimension(1024, 768));

        PainelLabirinto.setBackground(java.awt.Color.white);
        PainelLabirinto.setMinimumSize(new java.awt.Dimension(1024, 768));
        PainelLabirinto.setLayout(new java.awt.GridLayout(1, 0));

        javax.swing.GroupLayout PainelFundoLayout = new javax.swing.GroupLayout(PainelFundo);
        PainelFundo.setLayout(PainelFundoLayout);
        PainelFundoLayout.setHorizontalGroup(
            PainelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFundoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(PainelLabirinto, javax.swing.GroupLayout.PREFERRED_SIZE, 1024, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(476, 476, 476))
        );
        PainelFundoLayout.setVerticalGroup(
            PainelFundoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PainelFundoLayout.createSequentialGroup()
                .addComponent(PainelLabirinto, javax.swing.GroupLayout.DEFAULT_SIZE, 768, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(PainelFundo, new org.netbeans.lib.awtextra.AbsoluteConstraints(-1, 0, 1024, 768));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel PainelFundo;
    private javax.swing.JPanel PainelLabirinto;
    private javax.swing.JLayeredPane jLayeredPane1;
    // End of variables declaration//GEN-END:variables
}
