package gui;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.Die;
import models.RaffleCup;
import models.YatzyResultCalculator;

public class YatzyGui extends Application {

    private RaffleCup raffleCup = new RaffleCup();
    private ToggleButton[] diceButtons = new ToggleButton[5];
    private CheckBox[] holdCheckBoxes = new CheckBox[5];
    private Label lblThrowsLeftValue;
    int throwsLeft = 3;
    private Button btnRoll = new Button("Throw dice");
    private TextField[] txfFirstLowerSection = new TextField[6];
    private TextField txfSum = new TextField();
    private TextField txfBonus = new TextField();
    private TextField[] txfSecondLowerSection = new TextField[9];
    private TextField txfTotal = new TextField();


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy");
        GridPane pane = new GridPane();
        this.initYatzyDie(pane);
        //this.initYatzyScore();
        Scene sceneCarList = new Scene(pane);
        primaryStage.setScene(sceneCarList);
        primaryStage.show();


    }

    private void initYatzyDie(GridPane pane) {
        // set padding of the pane
        pane.setPadding(new Insets(50));
        // set horizontal gap between components
        pane.setHgap(10);
        // set vertical gap between components
        pane.setVgap(40);

        GridPane upperSection = new GridPane();
        upperSection.setHgap(10);
        upperSection.setVgap(10);

        GridPane lowerSection = new GridPane();
        lowerSection.setHgap(10);
        lowerSection.setVgap(20);

        //Upper section

        //Add dices
        addDices(upperSection);

        //add checkbox
        addCheckBoxes(upperSection);

        //add throws left
        addhrowsLeft(upperSection);

        //Add throw dice btn
        addThrowDiceBtn(upperSection);

        //Lower section

        //add lowerSectionLabels
        lowerSectionLabels(lowerSection);

        //add lowerFirstSectionTextFields
        firstTxfLowerSection(lowerSection);

        //add lowerSection sum and bonus TexfField
        txfSumAndBonus(lowerSection);

        //add lowerSecondSecionTextfield
        secondTxfLowerSection(lowerSection);

        //add txfTotal
        txfTotal(lowerSection);

        pane.add(upperSection, 0, 0);
        pane.add(lowerSection, 0, 1);

    }

    private void addDices(GridPane upperSection) {
        HBox dices = new HBox();
        dices.setSpacing(10);

        for (int index = 0; index < 5; index++) {
            diceButtons[index] = new ToggleButton();
            diceButtons[index].setPrefSize(50, 50);
            diceButtons[index].setDisable(true);
            dices.getChildren().add(diceButtons[index]);
        }
        upperSection.add(dices, 0, 0, 5, 1);
    }

    private void addCheckBoxes(GridPane upperSection) {
        HBox checkBoxes = new HBox();
        checkBoxes.setSpacing(11);

        for (int index = 0; index < 5; index++) {
            holdCheckBoxes[index] = new CheckBox("Hold");
            checkBoxes.getChildren().add(holdCheckBoxes[index]);
        }
        upperSection.add(checkBoxes, 0, 1, 5, 1);
    }

    private void addhrowsLeft(GridPane upperSection) {
        Label lblThrowsLeft = new Label("Throws left");
        upperSection.add(lblThrowsLeft, 0, 2);

        lblThrowsLeftValue = new Label(String.valueOf(throwsLeft));
        upperSection.add(lblThrowsLeftValue, 1, 2);
    }

    private void addThrowDiceBtn(GridPane upperSection) {
        upperSection.add(btnRoll, 4, 2);
        btnRoll.setOnAction(event -> {

            for (int index = 0; index < 5; index++) {
                if (!holdCheckBoxes[index].isSelected()) {
                    raffleCup.getDice()[index].roll();
                }
            }

            updateDiceFaces();
            throwsLeft--;
            lblThrowsLeftValue.setText(String.valueOf(throwsLeft));
            if (throwsLeft <= 0) {
                btnRoll.setDisable(true);
                displayPosibleScore();
            }

        });
    }

    private void updateDiceFaces() {
        Die[] diceValue = raffleCup.getDice();
        for (int index = 0; index < 5; index++) {
            diceButtons[index].setText(String.valueOf(diceValue[index].getEyes()));
        }
    }

    private static void lowerSectionLabels(GridPane lowerSection) {


        Label lblOnes = new Label("Ones (6)");
        lowerSection.add(lblOnes, 0, 0);

        Label lblTows = new Label("Tows (10)");
        lowerSection.add(lblTows, 0, 1);

        Label lblThrees = new Label("Threes (15)");
        lowerSection.add(lblThrees, 0, 2);

        Label lblFours = new Label("Fours (20)");
        lowerSection.add(lblFours, 0, 3);

        Label lblFives = new Label("Fives (25)");
        lowerSection.add(lblFives, 0, 4);

        Label lblSixes = new Label("Sixes (30)");
        lowerSection.add(lblSixes, 0, 5);

        Label lblSum = new Label("Sum");
        lowerSection.add(lblSum, 2, 6);

        Label lblBonus = new Label("Bonus (63)");
        lowerSection.add(lblBonus, 2, 7);

        Label lblOnePair = new Label("One pair (12)");
        lowerSection.add(lblOnePair, 0, 8);

        Label lblTowPair = new Label("Tow pair (22)");
        lowerSection.add(lblTowPair, 0, 9);

        Label lblThreeOfAKind = new Label("Three of a kind (18)");
        lowerSection.add(lblThreeOfAKind, 0, 10);

        Label lblFourOfAKind = new Label("Four of a kind (24)");
        lowerSection.add(lblFourOfAKind, 0, 11);

        Label lblSmallStraight = new Label("Small straight (15)");
        lowerSection.add(lblSmallStraight, 0, 12);

        Label lblLargeStraight = new Label("Large straight (20)");
        lowerSection.add(lblLargeStraight, 0, 13);

        Label lblFullHouse = new Label("Full house (28)");
        lowerSection.add(lblFullHouse, 0, 14);

        Label lblChance = new Label("Chance (30)");
        lowerSection.add(lblChance, 0, 15);

        Label lblYatzy = new Label("Yatzy (50)");
        lowerSection.add(lblYatzy, 0, 16);

        Label lblTotal = new Label("Total");
        lowerSection.add(lblTotal, 2, 17);
    }

    private void firstTxfLowerSection(GridPane lowerSection) {
        VBox firstTxfLowerSection = new VBox();
        firstTxfLowerSection.setSpacing(10);

        for (int index = 0; index < 6; index++) {
            txfFirstLowerSection[index] = new TextField();
            txfFirstLowerSection[index].setPrefSize(50, 10);
            txfFirstLowerSection[index].setEditable(false);
            firstTxfLowerSection.getChildren().add(txfFirstLowerSection[index]);
            txfFirstLowerSection[index].setOnMouseClicked(event -> this.selectScore(event));
        }
        lowerSection.add(firstTxfLowerSection, 1, 0, 1, 6);
    }

    private void txfSumAndBonus(GridPane lowerSection) {
        txfSum = new TextField();
        txfSum.setPrefSize(50, 10);
        txfSum.setEditable(false);
        lowerSection.add(txfSum, 3, 6);

        txfBonus = new TextField();
        txfBonus.setPrefSize(50, 10);
        txfBonus.setEditable(false);
        lowerSection.add(txfBonus, 3, 7);
    }

    private void secondTxfLowerSection(GridPane lowerSection) {
        VBox secondTxfLowerSection = new VBox();
        secondTxfLowerSection.setSpacing(10);

        for (int index = 0; index < 9; index++) {
            txfSecondLowerSection[index] = new TextField();
            txfSecondLowerSection[index].setPrefSize(50, 10);
            txfSecondLowerSection[index].setEditable(false);
            secondTxfLowerSection.getChildren().add(txfSecondLowerSection[index]);
            txfSecondLowerSection[index].setOnMouseClicked(event -> this.selectScore(event));
        }
        lowerSection.add(secondTxfLowerSection, 1, 8, 1, 9);
    }

    private void txfTotal(GridPane lowerSection) {
        txfTotal = new TextField();
        txfTotal.setPrefSize(50, 10);
        txfTotal.setEditable(false);
        lowerSection.add(txfTotal, 3, 17);
    }

    private void displayPosibleScore() {
        YatzyResultCalculator calculator = new YatzyResultCalculator(raffleCup.getDice());

        for (int index = 0; index < 6; index++) {
            if (!txfFirstLowerSection[index].isDisabled()) {
                txfFirstLowerSection[index].setText(Integer.toString(calculator.upperSectionScore(index + 1)));
            }
        }
        if (!txfSecondLowerSection[0].isDisabled()) {
            txfSecondLowerSection[0].setText(Integer.toString(calculator.onePairScore()));
        }
        if (!txfSecondLowerSection[1].isDisabled()) {
            txfSecondLowerSection[1].setText(Integer.toString(calculator.twoPairScore()));
        }
        if (!txfSecondLowerSection[2].isDisabled()) {
            txfSecondLowerSection[2].setText(Integer.toString(calculator.threeOfAKindScore()));
        }
        if (!txfSecondLowerSection[3].isDisabled()) {
            txfSecondLowerSection[3].setText(Integer.toString(calculator.fourOfAKindScore()));
        }
        if (!txfSecondLowerSection[4].isDisabled()) {
            txfSecondLowerSection[4].setText(Integer.toString(calculator.smallStraightScore()));
        }
        if (!txfSecondLowerSection[5].isDisabled()) {
            txfSecondLowerSection[5].setText(Integer.toString(calculator.largeStraightScore()));
        }
        if (!txfSecondLowerSection[6].isDisabled()) {
            txfSecondLowerSection[6].setText(Integer.toString(calculator.fullHouseScore()));
        }
        if (!txfSecondLowerSection[7].isDisabled()) {
            txfSecondLowerSection[7].setText(Integer.toString(calculator.chanceScore()));
        }
        if (!txfSecondLowerSection[8].isDisabled()) {
            txfSecondLowerSection[8].setText(Integer.toString(calculator.yatzyScore()));
        }


    }

    private void selectScore(Event event) {
        TextField textField = (TextField) event.getSource();
        int score = Integer.parseInt(textField.getText());
        textField.setDisable(true);
        updateTotalScore();
        if (isGameOver()) {
            btnRoll.setDisable(true);
        } else {
            startNewTurn();
        }


    }

    private int updateTotalScore() {
        //Upper section
        int upperSectionSum = 0;
        for (TextField textField : txfFirstLowerSection) {
            if (textField.isDisabled()) {
                upperSectionSum += Integer.parseInt(textField.getText());
            }
        }

        txfSum.setText(String.valueOf(upperSectionSum));

        int totalScore = upperSectionSum;

        if (upperSectionSum >= 63) {
            txfBonus.setText("50");
        } else {
            txfBonus.setText("0");
        }

        totalScore += Integer.parseInt(txfBonus.getText());

        for (TextField textField : txfSecondLowerSection) {
            if (textField.isDisabled()) {
                totalScore += Integer.parseInt(textField.getText());
            }
        }

        txfTotal.setText(String.valueOf(totalScore));

        return totalScore;
    }

    private void startNewTurn() {
        throwsLeft = 3;
        lblThrowsLeftValue.setText(String.valueOf(throwsLeft));
        btnRoll.setDisable(false);

        for (int index = 0; index < 5; index++) {
            holdCheckBoxes[index].setSelected(false);
        }

        for (TextField textField : txfFirstLowerSection) {
            if (!textField.isDisabled()) {
                textField.setText("");
            }
        }

        for (TextField textField : txfSecondLowerSection) {
            if (!textField.isDisabled()) {
                textField.setText("");
            }
        }

        for (ToggleButton diceButton : diceButtons) {
            diceButton.setText("");
        }
    }

    private boolean isGameOver() {
        for (TextField textField : txfFirstLowerSection) {
            if (!textField.isDisabled()) {
                return false;
            }
        }

        for (TextField textField : txfSecondLowerSection) {
            if (!textField.isDisabled()) {
                return false;
            }
        }
        return true;
    }
}


