package com.mycompany.test1;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Sunucu ve istemci arasındaki tüm iletişimi paketleyen sınıf. Koordinat ve
 * durum senkronizasyonu için güncellendi.
 *
 * @author akdag
 */
public class GameMessage implements Serializable {
   
    
    public String tip;
    
    public String mesaj;
    public int kalanHak;
    //
    public int[] rakipSkorlar = new int[16];
    public boolean[] rakipDolu = new boolean[16];
    public int[] geciciPuanlar = new int[16];
    //public boolean[] geciciAktif = new boolean[16];

    // Zarların hem değerini hem konumunu hem de durumunu taşıyan liste
    public List<ZarVerisi> zarlar = new ArrayList<>();

    // İç içe sınıf (Inner Class): Her bir zarın tüm bilgisini tutar
    public static class ZarVerisi implements Serializable {
        // private static final long serialVersionUID = 1L;

        public int deger;
        public double x;
        public double y;
        public boolean tutulduMu; // true ise hold_alan, false ise zar_havuzu

        public ZarVerisi(int deger, double x, double y, boolean tutulduMu) {
            this.deger = deger;
            this.x = x;
            this.y = y;
            this.tutulduMu = tutulduMu;
        }
    }

    // Constructor 1: Temel mesajlar için
    public GameMessage(String tip, String mesaj) {
        this.tip = tip;
        this.mesaj = mesaj;
    }

    // Constructor 2: Zar olayları için
    public GameMessage() {
    }
}
