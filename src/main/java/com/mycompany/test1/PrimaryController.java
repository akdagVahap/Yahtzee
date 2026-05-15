package com.mycompany.test1;

import java.io.File;
import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.*;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import javafx.scene.layout.VBox;
import javafx.scene.control.Label;

public class PrimaryController implements Initializable {

    // Fxml elements
    @FXML
    private Pane zar_havuzu;
    @FXML
    private HBox hold_alan;
    @FXML
    private HBox rakip_alan;
    @FXML
    private Button dice1, dice2, dice3, dice4, dice5;
    @FXML
    private GridPane skorTablosu;
    @FXML
    private Button zar_at;

    @FXML
    private Button ones_me;
    @FXML
    private Button ones;
    @FXML
    private Button twos_me;
    @FXML
    private Button twos;
    @FXML
    private Button threes_me;
    @FXML
    private Button threes;
    @FXML
    private Button fours_me;
    @FXML
    private Button fours;
    @FXML
    private Button fives_me;
    @FXML
    private Button fives;
    @FXML
    private Button sixes_me;
    @FXML
    private Button sixes;
    @FXML
    private Button sum_me;
    @FXML
    private Button sum;
    @FXML
    private Button bonus_me;
    @FXML
    private Button bonus;
    @FXML
    private Button three_of_kind_me;
    @FXML
    private Button three_of_kind;
    @FXML
    private Button four_of_kind_me;
    @FXML
    private Button four_of_kind;
    @FXML
    private Button full_house_me;
    @FXML
    private Button full_house;
    @FXML
    private Button small_straight_me;
    @FXML
    private Button small_straight;
    @FXML
    private Button large_straight_me;
    @FXML
    private Button large_straight;
    @FXML
    private Button chance_me;
    @FXML
    private Button chance;
    @FXML
    private Button yahtzee_me;
    @FXML
    private Button yahtzee;
    @FXML
    private Button total_score_me;
    @FXML
    private Button total_score;

    // Variables
    private int[] benimSkorlar = new int[16];
    private boolean[] benimDolu = new boolean[16];
    private int[] rakipSkorlar = new int[16];
    private boolean[] rakipDolu = new boolean[16];
    private Button[] benimButonlar;
    private Button[] rakipButonlar;

    private Random random = new Random();
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private int kalanHak = 3;
    private boolean siraBende = false;
    // New panels for the menu
    private VBox anaMenuPane;
    private VBox beklemePane;
    // Independent list to keep score history for each player locally
    private java.util.ArrayList<String> yerelSkorGecmisi = new java.util.ArrayList<>();// To keep game scores for each player  

    // Initializes the game user interface and sets the dice to their default starting states
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        zar_at.setDisable(true); // Disable roll button until game starts

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                tasarım(); // make design

                Button[] zarlar = {dice1, dice2, dice3, dice4, dice5};
                for (Button b : zarlar) {
                    if (b != null) {

                        b.setGraphic(drawDiceFace(1));
                        b.setText("");
                        b.setMinSize(60, 60);
                        b.setMaxSize(60, 60);
                        // Set dice to gold color
                        b.setStyle("-fx-background-color: gold; -fx-background-radius: 10; -fx-border-color: #B8860B; -fx-border-radius: 10; -fx-border-width: 2; -fx-padding: 0;");
                    }
                }

                benimButonlar = new Button[16];
                benimButonlar[0] = ones_me;
                benimButonlar[1] = twos_me;
                benimButonlar[2] = threes_me;
                benimButonlar[3] = fours_me;
                benimButonlar[4] = fives_me;
                benimButonlar[5] = sixes_me;
                benimButonlar[6] = sum_me;
                benimButonlar[7] = bonus_me;
                benimButonlar[8] = three_of_kind_me;
                benimButonlar[9] = four_of_kind_me;
                benimButonlar[10] = full_house_me;
                benimButonlar[11] = small_straight_me;
                benimButonlar[12] = large_straight_me;
                benimButonlar[13] = chance_me;
                benimButonlar[14] = yahtzee_me;
                benimButonlar[15] = total_score_me;

                rakipButonlar = new Button[16];
                rakipButonlar[0] = ones;
                rakipButonlar[1] = twos;
                rakipButonlar[2] = threes;
                rakipButonlar[3] = fours;
                rakipButonlar[4] = fives;
                rakipButonlar[5] = sixes;
                rakipButonlar[6] = sum;
                rakipButonlar[7] = bonus;
                rakipButonlar[8] = three_of_kind;
                rakipButonlar[9] = four_of_kind;
                rakipButonlar[10] = full_house;
                rakipButonlar[11] = small_straight;
                rakipButonlar[12] = large_straight;
                rakipButonlar[13] = chance;
                rakipButonlar[14] = yahtzee;
                rakipButonlar[15] = total_score;

                // Initial styling for all score buttons
                String baslangicStil = "-fx-background-color: rgba(255, 215, 0, 0.15); -fx-border-color: #FFD700; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;";
                for (Button b : benimButonlar) {
                    b.setText("0");
                    b.setDisable(true);
                    b.setStyle(baslangicStil);
                }
                for (Button b : rakipButonlar) {
                    b.setText("0");
                    b.setDisable(true);
                    b.setStyle(baslangicStil);
                }

                // Wait for the scene to load before rendering the menu
                if (zar_havuzu.getScene() != null) {
                    zar_havuzu.getScene().getRoot().setStyle("-fx-background-color: #8B0000;");
                    olusturAnaMenu(); // Create menu if scene is ready
                } else {
                    // Listen for the scene property to be set
                    zar_havuzu.sceneProperty().addListener(new javafx.beans.value.ChangeListener<javafx.scene.Scene>() {
                        @Override
                        public void changed(javafx.beans.value.ObservableValue<? extends javafx.scene.Scene> obs, javafx.scene.Scene oldScene, javafx.scene.Scene newScene) {
                            if (newScene != null) {
                                newScene.getRoot().setStyle("-fx-background-color: #8B0000;");
                                olusturAnaMenu();
                            }
                        }
                    });
                }
            }
        });
    }

    // Connect to the server
    private void baglantiyiKur() {
        // Thread creation without lambda arrows
        Thread dinlemeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Socket socket = new Socket("127.0.0.1", 1414);
                    out = new ObjectOutputStream(socket.getOutputStream());
                    in = new ObjectInputStream(socket.getInputStream());

                    while (true) {
                        GameMessage gelen = (GameMessage) in.readObject();

                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                if (beklemePane != null && beklemePane.isVisible()) {
                                    beklemePane.setVisible(false);
                                }

                                switch (gelen.tip) {
                                    case "RESET_ZARLAR":
                                        rakip_alan.getChildren().clear();
                                        zarlariSifirla();
                                        break;
                                    case "SIRA_SENDE":
                                        siraBende = true;
                                        zar_at.setDisable(false);
                                        kalanHak = 3;
                                        tumZarlariAktifYap(true);
                                        break;
                                    case "BEKLE":
                                        siraBende = false;
                                        zar_at.setDisable(true);
                                        tumZarlariAktifYap(false);
                                        break;
                                    case "ZAR_ATILDI":
                                        rakipGuncellemeIsle(gelen.zarlar);
                                        break;
                                    case "SKOR_GUNCELLE":
                                        rakipSkorlarGuncelle(gelen.rakipSkorlar, gelen.rakipDolu);
                                        break;
                                    case "GECICI_GUNCELLE":
                                        rakipGeciciGuncelle(gelen.geciciPuanlar);
                                        break;
                                }
                            }
                        });
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        dinlemeThread.start();
    }

    // Displays the opponents temporary potential scores on the screen when they roll the dice
    private void rakipGeciciGuncelle(int[] puanlar) {
        for (int i = 0; i < rakipButonlar.length; i++) {
            rakipButonlar[i].setText(String.valueOf(puanlar[i]));
        }
    }

    // Scatters the dice randomly across the board and plays a spinning animation for each roll
    @FXML
    private void handleZarAt() {
        if (siraBende && kalanHak > 0) {
            List<double[]> noktalar = guvenliNoktalar();
            int noktaIndex = 0;

            // Iterate over nodes in the dice pool and animate them
            for (Node node : zar_havuzu.getChildren()) {
                if (node instanceof Button) {
                    Button die = (Button) node;
                    int val = random.nextInt(6) + 1;
                    die.setUserData(val);
                    die.setGraphic(drawDiceFace(val));

                    if (noktaIndex < noktalar.size()) {
                        double[] yeniKonum = noktalar.get(noktaIndex);
                        die.setLayoutX(yeniKonum[0]);
                        die.setLayoutY(yeniKonum[1]);
                        noktaIndex++;
                    }

                    // Dice rolling animation
                    RotateTransition rt = new RotateTransition(Duration.millis(400), die);
                    rt.setByAngle(720);
                    rt.play();
                }
            }

            kalanHak--;
            rakipEkraniniGuncelle(); // Send updated dice positions to the opponent
            tabloGuncelle(); // Calculate and show potential scores

            if (kalanHak == 0) {
                zar_at.setDisable(true);
            }
        }
    }

    // Moves the clicked die between the zarpool and the hold area 
    @FXML
    private void handleZarTiklama(ActionEvent event) {
        if (!siraBende) {
            return; // Return if it is not our turn
        }

        Button secilenZar = (Button) event.getSource();

        if (zar_havuzu.getChildren().contains(secilenZar)) {
            // Move from pool to hold area
            zar_havuzu.getChildren().remove(secilenZar);
            secilenZar.setLayoutX(0);
            secilenZar.setLayoutY(0);
            hold_alan.getChildren().add(secilenZar);
            secilenZar.setStyle("-fx-background-color: gold; -fx-background-radius: 10; -fx-border-color: black; -fx-border-radius: 10; -fx-border-width: 3; -fx-padding: 0;");
            rakipEkraniniGuncelle();

        } else if (hold_alan.getChildren().contains(secilenZar)) {
            // Move from hold area back to pool
            hold_alan.getChildren().remove(secilenZar);

            List<double[]> noktalar = guvenliNoktalar();
            List<String> dolu = new ArrayList<>();
            for (Node n : zar_havuzu.getChildren()) {
                if (n instanceof Button) {
                    dolu.add((int) n.getLayoutX() + "," + (int) n.getLayoutY());
                }
            }

            double yeniX = 50, yeniY = 50;
            for (double[] p : noktalar) {
                String key = (int) p[0] + "," + (int) p[1];
                if (!dolu.contains(key)) {
                    yeniX = p[0];
                    yeniY = p[1];
                    break;
                }
            }

            secilenZar.setLayoutX(yeniX);
            secilenZar.setLayoutY(yeniY);
            zar_havuzu.getChildren().add(secilenZar);
            secilenZar.setStyle("-fx-background-color: gold; -fx-background-radius: 10; -fx-border-color: #B8860B; -fx-border-radius: 10; -fx-border-width: 2; -fx-padding: 0;");

            rakipEkraniniGuncelle();
        }
    }

    // Calculates all possible scores based on the current dice and displays them on the table
    private void tabloGuncelle() {
        ScoreCalculator sc = getSC();

        boolean gecerliPuanVar = false;

        if (benimDolu[0] == false && sc.ones() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[1] == false && sc.twos() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[2] == false && sc.threes() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[3] == false && sc.fours() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[4] == false && sc.fives() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[5] == false && sc.sixes() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[8] == false && sc.threeOfKind() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[9] == false && sc.fourOfKind() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[10] == false && sc.fullHouse() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[11] == false && sc.smallStraight() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[12] == false && sc.largeStraight() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[13] == false && sc.chance() != -1) {
            gecerliPuanVar = true;
        }
        if (benimDolu[14] == false && sc.yahtzee() != -1) {
            gecerliPuanVar = true;
        }

        guncelle(ones_me, 0, sc.ones(), gecerliPuanVar);
        guncelle(twos_me, 1, sc.twos(), gecerliPuanVar);
        guncelle(threes_me, 2, sc.threes(), gecerliPuanVar);
        guncelle(fours_me, 3, sc.fours(), gecerliPuanVar);
        guncelle(fives_me, 4, sc.fives(), gecerliPuanVar);
        guncelle(sixes_me, 5, sc.sixes(), gecerliPuanVar);
        guncelle(three_of_kind_me, 8, sc.threeOfKind(), gecerliPuanVar);
        guncelle(four_of_kind_me, 9, sc.fourOfKind(), gecerliPuanVar);
        guncelle(full_house_me, 10, sc.fullHouse(), gecerliPuanVar);
        guncelle(small_straight_me, 11, sc.smallStraight(), gecerliPuanVar);
        guncelle(large_straight_me, 12, sc.largeStraight(), gecerliPuanVar);
        guncelle(chance_me, 13, sc.chance(), gecerliPuanVar);
        guncelle(yahtzee_me, 14, sc.yahtzee(), gecerliPuanVar);
    }

    // Updates the color and text of a single score button depending on whether it is locked or not
    private void guncelle(Button btn, int index, int puan, boolean gecerliPuanVar) {
        if (benimDolu[index] == true) {
            return;
        }

        if (puan != -1) {
            btn.setText(String.valueOf(puan));
            btn.setDisable(false);
            btn.setStyle("-fx-background-color: rgba(255, 215, 0, 0.5); -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 2 5 2 5;");
        } else if (puan == -1) {
            btn.setText("0");

            if (gecerliPuanVar == false) {
                btn.setDisable(false);
                btn.setStyle("-fx-background-color: rgba(255, 0, 0, 0.4); -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: white; -fx-font-size: 14px; -fx-font-weight: bold; -fx-cursor: hand; -fx-padding: 2 5 2 5;");
            } else if (gecerliPuanVar == true) {
                btn.setDisable(true);
                btn.setStyle("-fx-background-color: rgba(255, 215, 0, 0.15); -fx-border-color: #FFD700; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            }
        }
    }

    // Locks the chosen score into the table permanently and passes the turn to the other player
    private void kategoriSec(int index, int puan) {
        benimDolu[index] = true;
        benimSkorlar[index] = puan;

        benimButonlar[index].setText(String.valueOf(puan));
        benimButonlar[index].setDisable(true);

        if (puan == 0) {
            benimButonlar[index].setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
        } else if (puan > 0) {
            benimButonlar[index].setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
        }

        // Convert the buttons that are open
        for (int i = 0; i < benimButonlar.length; i++) {
            if (benimDolu[i] == false) {
                benimButonlar[i].setText("0");
                benimButonlar[i].setDisable(true);
                benimButonlar[i].setStyle("-fx-background-color: rgba(255, 215, 0, 0.15); -fx-border-color: #FFD700; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            }
        }

        // The part of sums and total
        boolean sumDolu = true;
        for (int i = 0; i <= 5; i++) {
            if (benimDolu[i] == false) {
                sumDolu = false;
                break;
            }
        }

        if (sumDolu == true) {
            int sumPuan = 0;
            for (int i = 0; i <= 5; i++) {
                sumPuan += benimSkorlar[i];
            }

            benimSkorlar[6] = sumPuan;
            benimDolu[6] = true;
            sum_me.setText(String.valueOf(sumPuan));
            sum_me.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");

            if (sumPuan >= 63) {
                benimSkorlar[7] = 35;
                benimDolu[7] = true;
                bonus_me.setText("35");
                bonus_me.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            } else if (sumPuan < 63) {
                benimSkorlar[7] = 0;
                benimDolu[7] = true;
                bonus_me.setText("0");
                bonus_me.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            }
        }

        int[] secilebilenler = {0, 1, 2, 3, 4, 5, 8, 9, 10, 11, 12, 13, 14};
        boolean toplamDolu = true;
        for (int i : secilebilenler) {
            if (benimDolu[i] == false) {
                toplamDolu = false;
                break;
            }
        }

        if (toplamDolu == true) {
            int toplam = 0;
            for (int p : benimSkorlar) {
                toplam += p;
            }

            benimSkorlar[15] = toplam;
            benimDolu[15] = true;
            total_score_me.setText(String.valueOf(toplam));
            total_score_me.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            oyunBittiMi();
        }

        skorGonder();
        siraBende = false;
        zar_at.setDisable(true);
        tumZarlariAktifYap(false);

        try {
            out.reset();
            GameMessage msg = new GameMessage();
            msg.tip = "TUR_BITTI";
            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Registers the chosen score permanently into the ones category on the players score board
    @FXML
    private void onesClicked(ActionEvent e) {
        kategoriSec(0, Integer.parseInt(ones_me.getText()));
    }

    // Registers the chosen score permanently into the twos category on the players score board
    @FXML
    private void twosClicked(ActionEvent e) {
        kategoriSec(1, Integer.parseInt(twos_me.getText()));
    }

    // Registers the chosen score permanently into the threes category on the players score board
    @FXML
    private void threesClicked(ActionEvent e) {
        kategoriSec(2, Integer.parseInt(threes_me.getText()));
    }

    // Registers the chosen score permanently into the fours category on the players score board
    @FXML
    private void foursClicked(ActionEvent e) {
        kategoriSec(3, Integer.parseInt(fours_me.getText()));
    }

    // Registers the chosen score permanently into the fives category on the players score board
    @FXML
    private void fivesClicked(ActionEvent e) {
        kategoriSec(4, Integer.parseInt(fives_me.getText()));
    }

    // Registers the chosen score permanently into the sixes category on the players score board
    @FXML
    private void sixesClicked(ActionEvent e) {
        kategoriSec(5, Integer.parseInt(sixes_me.getText()));
    }

    // Registers the chosen score permanently into the three of a kind category on the table
    @FXML
    private void threeOfKindClicked(ActionEvent e) {
        kategoriSec(8, Integer.parseInt(three_of_kind_me.getText()));
    }

    // Registers the chosen score permanently into the four of a kind category on the table
    @FXML
    private void fourOfKindClicked(ActionEvent e) {
        kategoriSec(9, Integer.parseInt(four_of_kind_me.getText()));
    }

    // Registers the chosen score permanently into the full house category on the score table
    @FXML
    private void fullHouseClicked(ActionEvent e) {
        kategoriSec(10, Integer.parseInt(full_house_me.getText()));
    }

    // Registers the chosen score permanently into the small straight category on the table
    @FXML
    private void smallStraightClicked(ActionEvent e) {
        kategoriSec(11, Integer.parseInt(small_straight_me.getText()));
    }

    // Registers the chosen score permanently into the large straight category on the table
    @FXML
    private void largeStraightClicked(ActionEvent e) {
        kategoriSec(12, Integer.parseInt(large_straight_me.getText()));
    }

    // Registers the chosen score permanently into the chance category on the players score board
    @FXML
    private void chanceClicked(ActionEvent e) {
        kategoriSec(13, Integer.parseInt(chance_me.getText()));
    }

    // Registers the chosen score permanently into the yahtzee category on the players score board
    @FXML
    private void yahtzeeClicked(ActionEvent e) {
        kategoriSec(14, Integer.parseInt(yahtzee_me.getText()));
    }

    // Collects the face values of all dice currently on the screen and sends them to the calculator
    private ScoreCalculator getSC() {
        int[] degerler = new int[5];
        int i = 0;
        for (Node n : zar_havuzu.getChildren()) {
            if (n instanceof Button) {
                degerler[i++] = (Integer) ((Button) n).getUserData();
            }
        }
        for (Node n : hold_alan.getChildren()) {
            if (n instanceof Button) {
                degerler[i++] = (Integer) ((Button) n).getUserData();
            }
        }
        return new ScoreCalculator(degerler);
    }

    // Transmits the players updated permanent score table to the opponent over the network connection
    private void skorGonder() {
        if (out == null) {
            return;
        }
        try {
            out.reset();
            GameMessage msg = new GameMessage();
            msg.tip = "SKOR_GUNCELLE";

            // Sending unplayed categories as negative one so the opponent can recognize zeros as sacrificed
            int[] gonderilecekSkorlar = new int[16];
            for (int i = 0; i < 16; i++) {
                if (!benimDolu[i]) {
                    gonderilecekSkorlar[i] = -1; // Negative one represents unplayed or empty
                } else {
                    gonderilecekSkorlar[i] = benimSkorlar[i]; // Zero or greater represents scored or sacrificed
                }
            }

            msg.rakipSkorlar = gonderilecekSkorlar;

            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Receives the opponents locked scores and visually updates their score table on our screen
    private void rakipSkorlarGuncelle(int[] skorlar, boolean[] doluDurumu) {
        // Loop only through the normal category buttons
        for (int i = 0; i < rakipButonlar.length; i++) {
            if (i == 6 || i == 7 || i == 15) {
                continue; // These will be processed manually below
            }

            int gelenPuan = skorlar[i];

            if (gelenPuan != -1) {
                rakipSkorlar[i] = gelenPuan;
                rakipButonlar[i].setText(String.valueOf(gelenPuan));

                if (gelenPuan > 0) {
                    rakipButonlar[i].setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
                } else if (gelenPuan == 0) {
                    rakipButonlar[i].setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
                }
            }
        }

        // Update total and bonus sections manually using specific variable names
        if (skorlar[6] != -1) {
            rakipSkorlar[6] = skorlar[6]; // Added the previously missing assignment line
            sum.setText(String.valueOf(skorlar[6]));
            sum.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
        }

        if (skorlar[7] != -1) {
            rakipSkorlar[7] = skorlar[7]; // Added the previously missing assignment line
            bonus.setText(String.valueOf(skorlar[7]));
            if (skorlar[7] > 0) {
                bonus.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            } else if (skorlar[7] == 0) {
                bonus.setStyle("-fx-background-color: black; -fx-border-color: red; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: red; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
            }
        }

        if (skorlar[15] != -1) {
            rakipSkorlar[15] = skorlar[15]; // Added the missing line that successfully triggers the end of the game
            total_score.setText(String.valueOf(skorlar[15]));
            total_score.setStyle("-fx-background-color: black; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: gold; -fx-font-size: 16px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;");
        }

        oyunBittiMi();
    }

    // Checks if all score slots are filled and displays a final victory or defeat popup dialog
    private void oyunBittiMi() {
        if (benimDolu[15] && rakipSkorlar[15] > 0) {

            int benimToplam = benimSkorlar[15];
            int rakipToplam = rakipSkorlar[15];

            // Add the final scores to our local list
            yerelSkorGecmisi.add(benimToplam + "," + rakipToplam);

            String sonucBaslik;
            String sonucDetay;

            // Determine match outcome text
            if (benimToplam > rakipToplam) {
                sonucBaslik = "YOU WON!";
                sonucDetay = "Your Score: " + benimToplam + "\nOpponent's Score: " + rakipToplam;
            } else if (rakipToplam > benimToplam) {
                sonucBaslik = " YOU LOST!";
                sonucDetay = "Your Score: " + benimToplam + "\nOpponent's Score: " + rakipToplam;
            } else {
                sonucBaslik = "DRAW!";
                sonucDetay = "Scores are Tied: " + benimToplam;
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    oyunuSifirlaVeMenuyeDon();

                    Alert alert = new Alert(Alert.AlertType.NONE);
                    alert.setTitle("Match Finished!");

                    javafx.scene.control.DialogPane dialogPane = alert.getDialogPane();
                    dialogPane.setStyle("-fx-background-color: #FFD700; -fx-border-color: #B8860B; -fx-border-width: 4; -fx-border-radius: 10; -fx-background-radius: 10;");

                    javafx.scene.layout.AnchorPane icerikAlani = new javafx.scene.layout.AnchorPane();
                    icerikAlani.setPrefSize(450, 180);

                    // Load custom winner or loser image
                    javafx.scene.image.ImageView ozelResim = new javafx.scene.image.ImageView();
                    try {
                        String resim3 = getClass().getResource("/images/zar.png").toExternalForm();
                        javafx.scene.image.Image img = new javafx.scene.image.Image(resim3);
                        ozelResim.setImage(img);
                        ozelResim.setFitWidth(100);
                        ozelResim.setFitHeight(100);
                        ozelResim.setPreserveRatio(true);
                    } catch (Exception ex) {
                        System.out.println("Image not found!");
                    }

                    // Align image to the right
                    javafx.scene.layout.AnchorPane.setTopAnchor(ozelResim, 40.0);
                    javafx.scene.layout.AnchorPane.setRightAnchor(ozelResim, 0.0);

                    VBox yazıKutusu = new VBox(15);
                    yazıKutusu.setAlignment(Pos.CENTER_LEFT);

                    Label lblBaslik = new Label(sonucBaslik);
                    lblBaslik.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: black;");

                    Label lblDetay = new Label(sonucDetay);
                    lblDetay.setStyle("-fx-font-size: 20px; -fx-text-fill: #222222; -fx-font-weight: bold;");

                    yazıKutusu.getChildren().addAll(lblBaslik, lblDetay);

                    // Align text to the left
                    javafx.scene.layout.AnchorPane.setTopAnchor(yazıKutusu, 40.0);
                    javafx.scene.layout.AnchorPane.setLeftAnchor(yazıKutusu, 20.0);

                    icerikAlani.getChildren().addAll(yazıKutusu, ozelResim);
                    dialogPane.setContent(icerikAlani);

                    dialogPane.getButtonTypes().add(javafx.scene.control.ButtonType.OK);
                    Node okButton = dialogPane.lookupButton(javafx.scene.control.ButtonType.OK);
                    if (okButton != null) {
                        okButton.setStyle("-fx-background-color: black; -fx-text-fill: gold; -fx-font-weight: bold; -fx-font-size: 16px; -fx-padding: 8 25 8 25; -fx-cursor: hand; -fx-background-radius: 5;");
                    }

                    alert.showAndWait();
                }
            });
        }
    }

    // Clears all dice and score tables to reset the match and returns to the main start menu
    private void oyunuSifirlaVeMenuyeDon() {
        siraBende = false;
        zar_at.setDisable(true);
        kalanHak = 3;

        for (int i = 0; i < 16; i++) {
            benimSkorlar[i] = 0;
            rakipSkorlar[i] = 0;
            benimDolu[i] = false;
            rakipDolu[i] = false;
        }

        // Reset button styles to default state
        String bosButonStili = "-fx-background-color: rgba(255, 215, 0, 0.15); -fx-border-color: #FFD700; -fx-border-width: 2; -fx-border-radius: 5; -fx-background-radius: 5; -fx-text-fill: #FFFFFF; -fx-font-size: 14px; -fx-font-weight: bold; -fx-padding: 2 5 2 5;";

        for (Button b : benimButonlar) {
            b.setText("0");
            b.setDisable(true);
            b.setStyle(bosButonStili);
        }
        for (Button b : rakipButonlar) {
            b.setText("0");
            b.setDisable(true);
            b.setStyle(bosButonStili);
        }

        zar_havuzu.getChildren().clear();
        hold_alan.getChildren().clear();
        rakip_alan.getChildren().clear();

        zarlariSifirla();

        if (anaMenuPane != null) {
            anaMenuPane.setVisible(true);
        }
    }

    // Synchronizes the opponents dice movements visually on our screen when they make a move
// Renders the dice based on opponent's roll data
    private void rakipGuncellemeIsle(List<GameMessage.ZarVerisi> gelenZarlar) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                zar_havuzu.getChildren().clear();
                rakip_alan.getChildren().clear();

                for (GameMessage.ZarVerisi zv : gelenZarlar) {
                    Button rb = new Button();
                    rb.setGraphic(drawDiceFace(zv.deger));
                    rb.setUserData(zv.deger); // Kritik: Zar değerini butona tekrar tanımlıyoruz
                    rb.setText("");
                    rb.setMinSize(60, 60);
                    rb.setMaxSize(60, 60);

                    // Renk Onarımı: Tam altın sarısı ve doğru kenarlıklar
                    rb.setStyle("-fx-background-color: gold; -fx-background-radius: 10; -fx-border-color: #B8860B; -fx-border-radius: 10; -fx-border-width: 2; -fx-padding: 0;");
                    rb.setDisable(true);

                    if (zv.tutulduMu) {
                        rakip_alan.getChildren().add(rb);
                    } else {
                        rb.setLayoutX(zv.x);
                        rb.setLayoutY(zv.y);
                        zar_havuzu.getChildren().add(rb);
                    }
                }
            }
        });
    }

    // Sends the coordinates and hold status of our current dice to the opponents application
    private void rakipEkraniniGuncelle() {
        if (out == null) {
            return;
        }
        try {
            out.reset();
            GameMessage msg = new GameMessage();
            msg.tip = "ZAR_ATILDI";
            msg.kalanHak = kalanHak;

            // Dice currently in the pool
            for (Node n : zar_havuzu.getChildren()) {
                if (n instanceof Button) {
                    Button b = (Button) n;
                    int deger = (Integer) b.getUserData();
                    double konumX = b.getLayoutX();
                    double konumY = b.getLayoutY();
                    boolean tutulduMu = false;

                    GameMessage.ZarVerisi veri = new GameMessage.ZarVerisi(deger, konumX, konumY, tutulduMu);
                    msg.zarlar.add(veri);
                }
            }

            // Dice currently held by the player
            for (Node n : hold_alan.getChildren()) {
                if (n instanceof Button) {
                    Button b = (Button) n;
                    int deger = (Integer) b.getUserData();
                    double konumX = b.getLayoutX();
                    double konumY = b.getLayoutY();
                    boolean tutulduMu = true;

                    GameMessage.ZarVerisi veri = new GameMessage.ZarVerisi(deger, konumX, konumY, tutulduMu);
                    msg.zarlar.add(veri);
                }
            }

            out.writeObject(msg);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Generates the visual dots on a dice face based on the randomly generated numeric value
    private Pane drawDiceFace(int value) {
        Pane pane = new Pane();
        pane.setPrefSize(60, 60);
        pane.setStyle("-fx-background-color: gold; -fx-background-radius: 10;");

        double L = 11, M = 28, R = 45;

        if (value == 1) {
            Circle nokta1 = new Circle(M, M, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
        } else if (value == 2) {
            Circle nokta1 = new Circle(L, L, 4.8, Color.BLACK);
            Circle nokta2 = new Circle(R, R, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
            pane.getChildren().add(nokta2);
        } else if (value == 3) {
            Circle nokta1 = new Circle(L, L, 4.8, Color.BLACK);
            Circle nokta2 = new Circle(M, M, 4.8, Color.BLACK);
            Circle nokta3 = new Circle(R, R, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
            pane.getChildren().add(nokta2);
            pane.getChildren().add(nokta3);
        } else if (value == 4) {
            Circle nokta1 = new Circle(L, L, 4.8, Color.BLACK);
            Circle nokta2 = new Circle(R, L, 4.8, Color.BLACK);
            Circle nokta3 = new Circle(L, R, 4.8, Color.BLACK);
            Circle nokta4 = new Circle(R, R, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
            pane.getChildren().add(nokta2);
            pane.getChildren().add(nokta3);
            pane.getChildren().add(nokta4);
        } else if (value == 5) {
            Circle nokta1 = new Circle(L, L, 4.8, Color.BLACK);
            Circle nokta2 = new Circle(R, L, 4.8, Color.BLACK);
            Circle nokta3 = new Circle(M, M, 4.8, Color.BLACK);
            Circle nokta4 = new Circle(L, R, 4.8, Color.BLACK);
            Circle nokta5 = new Circle(R, R, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
            pane.getChildren().add(nokta2);
            pane.getChildren().add(nokta3);
            pane.getChildren().add(nokta4);
            pane.getChildren().add(nokta5);
        } else if (value == 6) {
            Circle nokta1 = new Circle(L, L, 4.8, Color.BLACK);
            Circle nokta2 = new Circle(R, L, 4.8, Color.BLACK);
            Circle nokta3 = new Circle(L, M, 4.8, Color.BLACK);
            Circle nokta4 = new Circle(R, M, 4.8, Color.BLACK);
            Circle nokta5 = new Circle(L, R, 4.8, Color.BLACK);
            Circle nokta6 = new Circle(R, R, 4.8, Color.BLACK);
            pane.getChildren().add(nokta1);
            pane.getChildren().add(nokta2);
            pane.getChildren().add(nokta3);
            pane.getChildren().add(nokta4);
            pane.getChildren().add(nokta5);
            pane.getChildren().add(nokta6);
        }

        return pane;
    }

    // Applies the custom background colors borders and hover effects to the user interface elements
    private void tasarım() {
        if (skorTablosu != null) {
            // Apply double border and black background to the score table container
            skorTablosu.setStyle("-fx-background-color: rgba(0,0,0,0.85); -fx-padding: 20; -fx-background-radius: 10; "
                    + "-fx-border-color: #FFD700, #B8860B; -fx-border-width: 4, 1; -fx-border-insets: 0, 6; -fx-border-radius: 10, 5;");
        }

        String alanStili = "-fx-background-color: rgba(255,215,0,0.2); -fx-border-color: #FFD700; -fx-border-width: 3; -fx-border-radius: 15; -fx-background-radius: 15; -fx-padding: 10;";
        if (hold_alan != null) {
            hold_alan.setStyle(alanStili);
            hold_alan.setAlignment(Pos.CENTER);
            hold_alan.setSpacing(10);
        }
        if (rakip_alan != null) {
            rakip_alan.setStyle(alanStili);
            rakip_alan.setAlignment(Pos.CENTER);
            rakip_alan.setSpacing(10);
        }

        // Setup hover effect for the main dice roll button
        if (zar_at != null) {
            String zarAtNormal = "-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 20px; -fx-font-weight: bold; -fx-border-color: gold; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";
            String zarAtHover = "-fx-background-color: #222222; -fx-text-fill: gold; -fx-font-size: 20px; -fx-font-weight: bold; -fx-border-color: white; -fx-border-width: 2; -fx-border-radius: 10; -fx-background-radius: 10; -fx-cursor: hand;";

            zar_at.setStyle(zarAtNormal);
            zar_at.setOnMouseEntered(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent e) {
                    zar_at.setStyle(zarAtHover);
                }
            });

            zar_at.setOnMouseExited(new javafx.event.EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent e) {
                    zar_at.setStyle(zarAtNormal);
                }
            });

        }
    }

    // Generates non overlapping random coordinates on the board so the dice do not collide
    private List<double[]> guvenliNoktalar() {
        List<double[]> noktalar = new ArrayList<>();

        // Generate non colliding coordinate grid
        int[] xNoktalari = {50, 150, 250, 350, 450, 550}; // Six columns
        int[] yNoktalari = {50, 150, 250, 350};           // Four rows

        for (int x : xNoktalari) {
            for (int y : yNoktalari) {
                noktalar.add(new double[]{x, y});
            }
        }

        // Shuffle so dice spread dynamically
        Collections.shuffle(noktalar);

        return noktalar;
    }

    // Enables or disables the clicking feature on all dice depending on whose turn it currently is
    private void tumZarlariAktifYap(boolean aktif) {
        for (Node n : zar_havuzu.getChildren()) {
            if (aktif == true) {
                n.setDisable(false);
            } else if (aktif == false) {
                n.setDisable(true);
            }
        }

        for (Node n : hold_alan.getChildren()) {
            if (aktif == true) {
                n.setDisable(false);
            } else if (aktif == false) {
                n.setDisable(true);
            }
        }
    }

    // Clears the board and generates five brand new dice at their initial default positions
    private void zarlariSifirla() {
        zar_havuzu.getChildren().clear();
        hold_alan.getChildren().clear();
        double x = 20;

        for (int i = 0; i < 5; i++) {
            Button b = new Button();
            //int val = random.nextInt(6) + 1;
            //b.setUserData(val);
            b.setGraphic(drawDiceFace(6));
            b.setMinSize(60, 60);
            b.setMaxSize(60, 60);
            b.setLayoutX(x);
            b.setLayoutY(50);
            b.setStyle("-fx-background-color: gold; -fx-background-radius: 10; -fx-border-color: #B8860B; -fx-border-radius: 10; -fx-border-width: 2; -fx-padding: 0;");

            b.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    handleZarTiklama(e);
                }
            });

            zar_havuzu.getChildren().add(b);
            x = x + 70;
        }
    }

    // Renders the initial startup menu containing the background image and the play button
    private void olusturAnaMenu() {
        //System.out.println("Kaynak dizin: " + getClass().getResource("/"));
        Pane root = (Pane) zar_havuzu.getScene().getRoot();

        anaMenuPane = new VBox(30);
        anaMenuPane.setAlignment(Pos.CENTER);

        String resim1 = getClass().getResource("/images/arka1.png").toExternalForm();

        anaMenuPane.setStyle("-fx-background-image: url('" + resim1 + "'); "
                + "-fx-background-size: cover; "
                + "-fx-background-position: center;");

        anaMenuPane.prefWidthProperty().bind(root.widthProperty());
        anaMenuPane.prefHeightProperty().bind(root.heightProperty());

        // Play button
        Button btnPlay = new Button("PLAY");
        btnPlay.setStyle("-fx-font-size: 28px; -fx-background-color: gold; -fx-text-fill: black; -fx-pref-width: 250px; -fx-background-radius: 15; -fx-font-weight: bold; -fx-cursor: hand;");

        btnPlay.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                anaMenuPane.setVisible(false);
                gosterBeklemeEkrani(root);
                baglantiyiKur();
            }
        });

        // Scores button
        Button btnSkorlar = new Button("SCORES");
        btnSkorlar.setStyle("-fx-font-size: 28px; -fx-background-color: red; -fx-text-fill: white; -fx-pref-width: 250px; -fx-background-radius: 15; -fx-font-weight: bold; -fx-cursor: hand;");

        btnSkorlar.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                gosterSkorlarEkrani(root);
            }
        });

        anaMenuPane.getChildren().addAll(btnPlay, btnSkorlar);
        root.getChildren().add(anaMenuPane);
    }

    // screen that is while waiting the second player
    private void gosterBeklemeEkrani(Pane root) {
        beklemePane = new VBox(0);

        beklemePane.setAlignment(Pos.BOTTOM_CENTER);
        beklemePane.setPadding(new javafx.geometry.Insets(0, 0, 40, 0));// Forty unit space 

        beklemePane.prefWidthProperty().bind(root.widthProperty());
        beklemePane.prefHeightProperty().bind(root.heightProperty());

        String resim2 = getClass().getResource("/images/kalem.png").toExternalForm();
        beklemePane.setStyle("-fx-background-image: url('" + resim2 + "'); -fx-background-size: cover; -fx-background-position: center;");

        String[] ipuclari = {
            " You need 5 matching dice for a Yahtzee (50 Points).",
            " A Small Straight requires 4 consecutive dice (30 Points).",
            " The Chance category is for dice that don't fit anywhere else.",
            " A Large Straight requires 5 consecutive dice (40 Points).",
            " Score 63 or more in the upper section to get a 35-point bonus!"
        };

        Label lblBekle = new Label("WAITING FOR 2ND PLAYER...");
        lblBekle.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white; -fx-background-color: rgba(0,0,0,0.8); -fx-padding: 5 20 5 20;");

        Label lblIpucu = new Label(ipuclari[0]);
        lblIpucu.setStyle("-fx-font-size: 22px; -fx-text-fill: gold; -fx-background-color: rgba(0,0,0,0.8); -fx-padding: 5 20 5 20;");

        beklemePane.getChildren().addAll(lblBekle, lblIpucu);
        root.getChildren().add(beklemePane);

        boolean bekleniyorMu = true;

        Thread beklemeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int index = 0;
                while (bekleniyorMu) {
                    final int guncelIndex = index;

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            lblIpucu.setText(ipuclari[guncelIndex]);
                        }
                    });

                    index++;
                    if (index == 5) {
                        index = 0;
                    }

                    try {
                        Thread.sleep(4000);
                    } catch (InterruptedException e) {
                        break;
                    }
                }
            }
        });
        beklemeThread.start();
    }

    // Opens a scrollable list interface displaying the final results of all previously played matches
    private void gosterSkorlarEkrani(Pane root) {
        VBox skorPane = new VBox(20);
        skorPane.setAlignment(Pos.TOP_CENTER);
        skorPane.setStyle("-fx-background-color: black; -fx-padding: 30;");
        skorPane.prefWidthProperty().bind(root.widthProperty());
        skorPane.prefHeightProperty().bind(root.heightProperty());

        Label baslik = new Label("PAST SCORES");
        baslik.setStyle("-fx-font-size: 36px; -fx-text-fill: gold; -fx-font-weight: bold;");
        skorPane.getChildren().add(baslik);

        VBox liste = new VBox(10);
        liste.setAlignment(Pos.CENTER);

        // Read records directly from the local list
        int macNo = 1;
        for (String satir : yerelSkorGecmisi) {
            String[] parcalar = satir.split(",");
            if (parcalar.length == 2) {
                int skor1 = Integer.parseInt(parcalar[0]);
                int skor2 = Integer.parseInt(parcalar[1]);

                String yazi;
                String renk;

                if (skor1 > skor2) {
                    yazi = macNo + ". Game: YOU WON! (You: " + skor1 + " - Opponent: " + skor2 + ")";
                    renk = "green";
                } else if (skor1 < skor2) {
                    yazi = macNo + ". Game: YOU LOST! (You: " + skor1 + " - Opponent: " + skor2 + ")";
                    renk = "red";
                } else {
                    yazi = macNo + ". Game: DRAW! (Score: " + skor1 + " - " + skor2 + ")";
                    renk = "white";
                }

                Label lbl = new Label(yazi);
                lbl.setStyle("-fx-font-size: 22px; -fx-text-fill: " + renk + "; -fx-font-weight: bold;");
                liste.getChildren().add(lbl);
                macNo++;
            }
        }

        if (liste.getChildren().isEmpty()) {
            Label lbl = new Label("No matches played yet.");
            lbl.setStyle("-fx-font-size: 20px; -fx-text-fill: gray;");
            liste.getChildren().add(lbl);
        }

        javafx.scene.control.ScrollPane scroll = new javafx.scene.control.ScrollPane(liste);
        scroll.setStyle("-fx-background: black; -fx-background-color: transparent;");
        scroll.setFitToWidth(true);
        scroll.setPrefHeight(300);

        skorPane.getChildren().add(scroll);

        HBox butonKutusu = new HBox(30);
        butonKutusu.setAlignment(Pos.CENTER);

        Button btnGeri = new Button("GO BACK");
        btnGeri.setStyle("-fx-font-size: 20px; -fx-background-color: #555555; -fx-text-fill: white; -fx-cursor: hand;");
        btnGeri.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                root.getChildren().remove(skorPane);
                anaMenuPane.setVisible(true);
            }
        });

        Button btnTemizle = new Button("CLEAR SCORES");
        btnTemizle.setStyle("-fx-font-size: 20px; -fx-background-color: red; -fx-text-fill: white; -fx-font-weight: bold; -fx-cursor: hand;");
        btnTemizle.setOnAction(new javafx.event.EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                // Only clears local history and does not affect the opponent
                yerelSkorGecmisi.clear();
                root.getChildren().remove(skorPane);
                gosterSkorlarEkrani(root);
            }
        });

        butonKutusu.getChildren().addAll(btnGeri, btnTemizle);
        skorPane.getChildren().add(butonKutusu);

        root.getChildren().add(skorPane);
    }

}
