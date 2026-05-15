package com.mycompany.test1;

import java.io.*;
import java.net.Socket;

public class Match implements Runnable {

    private Socket s1, s2;
    private ObjectOutputStream out1, out2;
    private ObjectInputStream in1, in2;
    private int p1ToplamSkor = 0;
    private int p2ToplamSkor = 0;

    public Match(Socket s1, Socket s2) {
        this.s1 = s1;
        this.s2 = s2;
    }

    @Override
    public void run() {
        try {
            out1 = new ObjectOutputStream(s1.getOutputStream());
            out1.flush();
            out2 = new ObjectOutputStream(s2.getOutputStream());
            out2.flush();
            in1 = new ObjectInputStream(s1.getInputStream());
            in2 = new ObjectInputStream(s2.getInputStream());

            int toplamTur = 13;

            for (int tur = 0; tur < toplamTur; tur++) {

                // ─── OYUNCU 1'İN TURU ───
                out1.writeObject(new GameMessage("SIRA_SENDE", ""));
                out1.flush();
                out2.writeObject(new GameMessage("BEKLE", ""));
                out2.flush();

                // ikisine de reset gönder
                GameMessage reset1 = new GameMessage();
                reset1.tip = "RESET_ZARLAR";
                out1.writeObject(reset1);
                out1.flush();
                out2.writeObject(reset1);
                out2.flush();

                // oyuncu1 oynasın
                oynaTur(in1, out2);
                out1.writeObject(new GameMessage("BEKLE", ""));
                out1.flush();
                // ─── OYUNCU 2'NİN TURU ───
                out2.writeObject(new GameMessage("SIRA_SENDE", ""));
                out2.flush();
                out1.writeObject(new GameMessage("BEKLE", ""));
                out1.flush();

                // ikisine de reset gönder
                GameMessage reset2 = new GameMessage();
                reset2.tip = "RESET_ZARLAR";
                out1.writeObject(reset2);
                out1.flush();
                out2.writeObject(reset2);
                out2.flush();

                // oyuncu2 oynasın
                oynaTur(in2, out1);
            }

        } catch (IOException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    private void oynaTur(ObjectInputStream aktifIn,
            ObjectOutputStream rakipOut)
            throws IOException, ClassNotFoundException {

        while (true) {
            GameMessage msg = (GameMessage) aktifIn.readObject();

            if (msg.tip.equals("ZAR_ATILDI")) {
                rakipOut.reset();
                rakipOut.writeObject(msg);
                rakipOut.flush();
            }

            if (msg.tip.equals("TUR_BITTI")) {
                break;
            }

            if (msg.tip.equals("SKOR_GUNCELLE")) {
                GameMessage ilet = new GameMessage();
                ilet.tip = "SKOR_GUNCELLE";
                ilet.rakipSkorlar = msg.rakipSkorlar;
                //ilet.rakipDolu = msg.rakipDolu;
                rakipOut.reset();
                rakipOut.writeObject(ilet);
                rakipOut.flush();
            }
        }
    }
}
