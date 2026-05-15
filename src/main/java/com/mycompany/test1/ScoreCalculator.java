/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.test1;

/**
 *
 * @author akdag
 */


public class ScoreCalculator {

    private int[] arr = new int[7];

    public ScoreCalculator(int[] zarDegerleri) {
        for (int deger : zarDegerleri) {
            if (deger >= 1 && deger <= 6) {
                arr[deger]++;
            }
        }
    }

   

   public int ones() {
        if (arr[1] > 0) return arr[1] * 1; // if this is ok for rules return point
        return -1;                         // if not return -1
    }

    public int twos() {
        if (arr[2] > 0) return arr[2] * 2; // if this is ok for rules return point
        return -1;                         // if not return -1
    }

    public int threes() {
        if (arr[3] > 0) return arr[3] * 3; 
        return -1; 
    }

    public int fours() {
       if (arr[4] > 0) return arr[4] * 4; 
        return -1; 
    }

    public int fives() {
        if (arr[5] > 0) return arr[5] * 5; 
        return -1; 
    }

    public int sixes() {
        if (arr[6] > 0) return arr[6] * 6; 
        return -1; 
    }

    public int threeOfKind() {
        boolean aktif = false;
        int puan = 0;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] >= 3) {
                aktif = true;
                break;
            }
        }
        if (aktif) {
            for (int i = 1; i <= 6; i++) puan += arr[i] * i;
            return puan;
        }
        return -1;
    }

    public int fourOfKind() {
        boolean aktif = false;
        int puan = 0;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] >= 4) {
                aktif = true;
                break;
            }
        }
        if (aktif) {
            for (int i = 1; i <= 6; i++) puan += arr[i] * i;
            return puan;
        }
        return -1;
    }

    public int fullHouse() {
        boolean iki = false, uc = false;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] == 2) iki = true;
            if (arr[i] == 3) uc = true;
        }if(iki && uc){
            return 25;
        }
        return -1;
    }

    public int smallStraight() {
        int ardisik = 0;
        boolean aktif = false;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] > 0) {
                ardisik++;
                if (ardisik >= 4) {
                    aktif = true;
                    break;
                }
            } else {
                ardisik = 0;
            }
        }
        if (aktif) return 30;
        return -1;
    }

    public int largeStraight() {
        int ardisik = 0;
        boolean aktif = false;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] > 0) {
                ardisik++;
                if (ardisik >= 5) {
                    aktif = true;
                    break;
                }
            } else {
                ardisik = 0;
            }
        }
        if (aktif) return 40;
        return -1;
    }

    public int chance() {
        int puan = 0;
        for (int i = 1; i <= 6; i++){
            puan += arr[i] * i;
        }
            
        return puan;
    }

    public int yahtzee() {
        boolean aktif = false;
        for (int i = 1; i <= 6; i++) {
            if (arr[i] == 5) {
                aktif = true;
                break;
            }
        }
        if (aktif) return 50;
        return -1;
    }
}