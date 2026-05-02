/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.gamebasic;

import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JOptionPane;

/**
 *
 * @author akdag
 */
public class Gamee extends javax.swing.JFrame {

    private void kontrolEt() {

        arr = new int[7];

        ikili_liste.clear();
        üclü_liste.clear();
        String first = first_zar.getText();
        int first1 = Integer.parseInt(first);
        arr[first1]++;

        String second = second_zar.getText();
        int second1 = Integer.parseInt(second);
        arr[second1]++;
        String third = third_zar.getText();
        int third1 = Integer.parseInt(third);
        arr[third1]++;
        String fourd = fourd_zar.getText();
        int fourd1 = Integer.parseInt(fourd);
        arr[fourd1]++;
        String fifth = fifth_zar.getText();
        int fifth1 = Integer.parseInt(fifth);
        arr[fifth1]++;

        dörtlü.setEnabled(false);
        üçlü.setEnabled(false);
        full.setEnabled(false);
        small_straight.setEnabled(false);
        big_straight.setEnabled(false);
        birli.setEnabled(false);
        ikili.setEnabled(false);
        üçlü1.setEnabled(false);
        dörtlü1.setEnabled(false);
        beşli.setEnabled(false);
        altılı.setEnabled(false);
        birli_show.setText("0");
        ikili_show.setText("0");
        üçlü_show1.setText("0");
        dörtlü_show1.setText("0");
        beşli_show.setText("0");
        altılı_show.setText("0");

        int ardısık = 0;

        for (int i = 1; i < 7; i++) {

            if (arr[i] == 3) {
                üçlü.setEnabled(true);

                üclü_liste.add(i);

                int three = i;
                for (int j = 1; j <= i; j++) {
                    if (arr[j] == 2 && j != three) {
                        full.setEnabled(true);

                    }
                }
            } else if (arr[i] == 4) {
                dörtlü.setEnabled(true);

            }
            if (arr[i] > 0) {
                ardısık++;

                if (ardısık == 4) {
                    small_straight.setEnabled(true);

                } else if (ardısık == 5) {
                    big_straight.setEnabled(true);

                }
            }
            if (arr[i] == 0) {
                ardısık = 0;
            }
            if (arr[1] > 0) {
                String birliler = String.valueOf(arr[1]);
                birli_show.setText(birliler);
                birli.setEnabled(true);
            }
            if (arr[2] > 0) {
                String ikililer = String.valueOf(arr[2]);
                ikili_show.setText(ikililer);
                ikili.setEnabled(true);

            }
            if (arr[3] > 0) {
                String üçlüler = String.valueOf(arr[3]);
                üçlü_show1.setText(üçlüler);
                üçlü1.setEnabled(true);

            }
            if (arr[4] > 0) {
                String dörtlüler = String.valueOf(arr[4]);
                dörtlü_show1.setText(dörtlüler);
                dörtlü1.setEnabled(true);

            }
            if (arr[5] > 0) {
                String beşliler = String.valueOf(arr[5]);
                beşli_show.setText(beşliler);
                beşli.setEnabled(true);

            }
            if (arr[6] > 0) {
                String altılar = String.valueOf(arr[6]);
                altılı_show.setText(altılar);
                altılı.setEnabled(true);

            }

        }
    }

    int[] arr = new int[7];
    ArrayList<Integer> ikili_liste = new ArrayList<>();
    ArrayList<Integer> üclü_liste = new ArrayList<>();

    int count_zar = 0;
    int point = 0;
    int rnd1 = 0;
    int rnd2 = 0;
    int rnd3 = 0;
    int rnd4 = 0;
    int rnd5 = 0;

    public Gamee() {
        initComponents();
        dörtlü.setEnabled(false);
        üçlü.setEnabled(false);
        full.setEnabled(false);
        small_straight.setEnabled(false);
        big_straight.setEnabled(false);
        birli.setEnabled(false);
        ikili.setEnabled(false);
        üçlü1.setEnabled(false);
        dörtlü1.setEnabled(false);
        beşli.setEnabled(false);
        altılı.setEnabled(false);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dörtlü = new javax.swing.JButton();
        üçlü = new javax.swing.JButton();
        zar = new javax.swing.JButton();
        dörtlü_show = new javax.swing.JTextField();
        üçlü_show = new javax.swing.JTextField();
        first_zar = new javax.swing.JTextField();
        second_zar = new javax.swing.JTextField();
        full_zar = new javax.swing.JTextField();
        second_choose = new javax.swing.JCheckBox();
        first_choose = new javax.swing.JCheckBox();
        third_choose = new javax.swing.JCheckBox();
        fourd_choose = new javax.swing.JCheckBox();
        fourd_zar = new javax.swing.JTextField();
        fifth_choose = new javax.swing.JCheckBox();
        fifth_zar = new javax.swing.JTextField();
        third_zar = new javax.swing.JTextField();
        full = new javax.swing.JButton();
        small_straight = new javax.swing.JButton();
        small_show = new javax.swing.JTextField();
        big_show = new javax.swing.JTextField();
        big_straight = new javax.swing.JButton();
        ikili_show = new javax.swing.JTextField();
        üçlü_show1 = new javax.swing.JTextField();
        dörtlü_show1 = new javax.swing.JTextField();
        altılı_show = new javax.swing.JTextField();
        birli_show = new javax.swing.JTextField();
        ikili = new javax.swing.JButton();
        üçlü1 = new javax.swing.JButton();
        dörtlü1 = new javax.swing.JButton();
        beşli = new javax.swing.JButton();
        altılı = new javax.swing.JButton();
        birli = new javax.swing.JButton();
        beşli_show = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dörtlü.setText("dörtlü");
        dörtlü.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dörtlüActionPerformed(evt);
            }
        });

        üçlü.setText("üçlü");

        zar.setText("zar at");
        zar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                zarActionPerformed(evt);
            }
        });

        second_choose.setText("second_choose");
        second_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                second_chooseActionPerformed(evt);
            }
        });

        first_choose.setText("first_choose");
        first_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                first_chooseActionPerformed(evt);
            }
        });

        third_choose.setText("third_choose");
        third_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                third_chooseActionPerformed(evt);
            }
        });

        fourd_choose.setText("fourd_choose");
        fourd_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fourd_chooseActionPerformed(evt);
            }
        });

        fifth_choose.setText("fifth_choose");
        fifth_choose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fifth_chooseActionPerformed(evt);
            }
        });

        full.setText("full");

        small_straight.setText("small straight");

        big_straight.setText("big straight");

        ikili.setText("ikili");

        üçlü1.setText("üçlü1");

        dörtlü1.setText("dörtlü1");

        beşli.setText("beşli");

        altılı.setText("altılı");

        birli.setText("birli");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(big_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(full_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(üçlü_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(small_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dörtlü_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(small_straight)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(dörtlü, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(full, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(big_straight)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(üçlü)
                                .addGap(266, 266, 266)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(fourd_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(fifth_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                                .addComponent(fourd_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(52, 52, 52))
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(ikili_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(altılı_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(üçlü_show1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(dörtlü_show1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(beşli_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(birli_show, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)))
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(fifth_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ikili)
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(dörtlü1)
                                                    .addComponent(üçlü1)
                                                    .addComponent(beşli)
                                                    .addComponent(altılı)))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(birli)))))
                                .addGap(77, 77, 77))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(first_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(first_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(74, 74, 74)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(second_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(second_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(49, 49, 49)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(third_choose, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(third_zar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addComponent(zar)
                .addGap(238, 238, 238))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(üçlü_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(üçlü))
                        .addGap(29, 29, 29))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(birli)
                            .addComponent(birli_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dörtlü)
                    .addComponent(dörtlü_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ikili_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ikili))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(full_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(full, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(üçlü_show1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(üçlü1)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(small_straight)
                    .addComponent(small_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dörtlü_show1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dörtlü1))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(big_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(big_straight)
                    .addComponent(beşli)
                    .addComponent(beşli_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(altılı)
                    .addComponent(altılı_show, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(first_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(second_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fourd_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fifth_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(third_zar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(zar))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(second_choose)
                            .addComponent(third_choose)
                            .addComponent(fourd_choose)
                            .addComponent(fifth_choose))
                        .addGap(27, 27, 27))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(first_choose)
                        .addGap(19, 19, 19))))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void zarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_zarActionPerformed

        count_zar++;
        if (!first_choose.isSelected()) {

            rnd1 = (int) (Math.random() * 6) + 1;
            first_zar.setText(String.valueOf(rnd1));

        }

        if (!second_choose.isSelected()) {
            rnd2 = (int) (Math.random() * 6) + 1;
            second_zar.setText(String.valueOf(rnd2));

        }

        if (!third_choose.isSelected()) {
            rnd3 = (int) (Math.random() * 6) + 1;
            third_zar.setText(String.valueOf(rnd3));

        }

        if (!fourd_choose.isSelected()) {

            rnd4 = (int) (Math.random() * 6) + 1;
            fourd_zar.setText(String.valueOf(rnd4));

        }

        if (!fifth_choose.isSelected()) {

            rnd5 = (int) (Math.random() * 6) + 1;
            fifth_zar.setText(String.valueOf(rnd5));

        }

        kontrolEt();

        if (count_zar == 3) {
            zar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "The game is over");
        }


    }//GEN-LAST:event_zarActionPerformed

    private void first_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_first_chooseActionPerformed

    }//GEN-LAST:event_first_chooseActionPerformed

    private void dörtlüActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dörtlüActionPerformed

        int iki = 0;
        for (int i = 0; i < ikili_liste.size(); i++) {
            iki = ikili_liste.get(i);

        }
        arr[iki] -= 2;
        point += 10;
        System.out.println("point is" + point);

    }//GEN-LAST:event_dörtlüActionPerformed

    private void second_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_second_chooseActionPerformed


    }//GEN-LAST:event_second_chooseActionPerformed

    private void third_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_third_chooseActionPerformed

    }//GEN-LAST:event_third_chooseActionPerformed

    private void fourd_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fourd_chooseActionPerformed


    }//GEN-LAST:event_fourd_chooseActionPerformed

    private void fifth_chooseActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fifth_chooseActionPerformed
        // TODO add your handling code here:
        //kontrolEt();

    }//GEN-LAST:event_fifth_chooseActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gamee().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton altılı;
    private javax.swing.JTextField altılı_show;
    private javax.swing.JButton beşli;
    private javax.swing.JTextField beşli_show;
    private javax.swing.JTextField big_show;
    private javax.swing.JButton big_straight;
    private javax.swing.JButton birli;
    private javax.swing.JTextField birli_show;
    private javax.swing.JButton dörtlü;
    private javax.swing.JButton dörtlü1;
    private javax.swing.JTextField dörtlü_show;
    private javax.swing.JTextField dörtlü_show1;
    private javax.swing.JCheckBox fifth_choose;
    private javax.swing.JTextField fifth_zar;
    private javax.swing.JCheckBox first_choose;
    private javax.swing.JTextField first_zar;
    private javax.swing.JCheckBox fourd_choose;
    private javax.swing.JTextField fourd_zar;
    private javax.swing.JButton full;
    private javax.swing.JTextField full_zar;
    private javax.swing.JButton ikili;
    private javax.swing.JTextField ikili_show;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JCheckBox second_choose;
    private javax.swing.JTextField second_zar;
    private javax.swing.JTextField small_show;
    private javax.swing.JButton small_straight;
    private javax.swing.JCheckBox third_choose;
    private javax.swing.JTextField third_zar;
    private javax.swing.JButton zar;
    private javax.swing.JButton üçlü;
    private javax.swing.JButton üçlü1;
    private javax.swing.JTextField üçlü_show;
    private javax.swing.JTextField üçlü_show1;
    // End of variables declaration//GEN-END:variables
}
