package sample;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.stage.Stage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Controller {
    private static String pinCode ="";
    private int[] correctPins = new int[4];
    private static String [] cardNumber = new String[4];
    private int errorCounter = 3;
    private static int userNumber = 0;
    users user1 = new users(2000);
    users user2 = new users(500);
    users user3 = new users(6000);
    users user4 = new users(0);
    MediaPlayer mediaPlayer;
    @FXML
    private Button enter;
    @FXML
    private PasswordField card;
    @FXML
    private Label badCard;
    @FXML
    private TextField input;
    @FXML
    private Label question;
    @FXML
    private PasswordField pin;
    @FXML
    private Button bt7;
    @FXML
    private Button bt8;
    @FXML
    private Button bt9;
    @FXML
    private Button bt4;
    @FXML
    private Button bt5;
    @FXML
    private Button bt6;
    @FXML
    private Button bt1;
    @FXML
    private Button bt2;
    @FXML
    private Button bt3;
    @FXML
    private Button bt0;
    @FXML
    private Label error;
    @FXML
    private Button accept;

    public void checkPin(Event actionEvent) throws Exception {
         correctPins[0] = 1234;
         correctPins[1] = 1111;
         correctPins[2] = 4444;
         correctPins[3] = 5555;
        if(correctPinCheck()) {
            cardAcceptedSound();
            Parent blah = FXMLLoader.load(getClass().getResource("account.fxml"));
            Scene scene = new Scene(blah);
            Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
            errorCounter =3;
        } else {
            errorCounter--;
            if (errorCounter == 0) {
                saveBlocked("blockedCards");
                error.setText("Card blocked!");
                accept.setDisable(true);
                cardBlockedSound();
                return;
            }
            deleteSound();
            error.setText("Incorrect pin, try again! \n Attempts left: " + errorCounter);
        }
    }
    public void checkCard(Event actionEvent) throws Exception{
        cardNumber[0] = "1234123412341234";
        cardNumber[1] = "1111111111111111";
        cardNumber[2] = "4444444444444444";
        cardNumber[3] = "5555555555555555";
        if(!readBlocked("blockedCards")) {
            cardBlockedSound();
            badCard.setText("Card blocked!");
            return;
        }
            if(correctCardCheck()){
                cardAcceptedSound();
                Parent blah = FXMLLoader.load(getClass().getResource("login.fxml"));
                Scene scene = new Scene(blah);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                appStage.setScene(scene);
                appStage.show();
            }else {
                badCard.setText("Invalid input! Try again,");
        }

    }
    private class users{

        private long funds;
        users( int funds){
            this.funds = funds;
        }
        public void setFunds(long funds) {
            this.funds = funds;
        }
        public long getFunds(){
            return funds;
        }
    }
    private boolean correctCardCheck() {
        if(isValid(card.getText())) {
            for (userNumber = 0; userNumber < cardNumber.length; userNumber++) {
                if (cardNumber[userNumber].equals(card.getText())) {
                    return true;
                }
            }
        }
        return false;
    }


    private boolean correctPinCheck() {
            if(Long.parseLong(pinCode) == correctPins[userNumber]) {
                return true;
            }
        return false;
    }
    private void cardBlockedSound() throws URISyntaxException {
        Media musicFile = new Media(getClass().getResource("MP3/nie-jestes-gotow.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();
    }
    private void cardAcceptedSound() throws URISyntaxException {
        Media musicFile = new Media(getClass().getResource("MP3/101_DEathHW.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.setVolume(0.2);
        mediaPlayer.play();
    }
    private void deleteSound() throws URISyntaxException {
        Media musicFile = new Media(getClass().getResource("MP3/za-odpowied.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();
    }
    private void buttonSound() throws URISyntaxException {
        Media musicFile = new Media(getClass().getResource("MP3/Button-SoundBible.com-1420500901.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();
    }
    public void add7(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt7.getText();
        pin.setText(pinCode);
    }
    public void add8(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt8.getText();
        pin.setText(pinCode);
    }
    public void add9(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt9.getText();
        pin.setText(pinCode);
    }
    public void add4(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt4.getText();
        pin.setText(pinCode);
    }
    public void add5(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt5.getText();
        pin.setText(pinCode);
    }
    public void add6(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt6.getText();
        pin.setText(pinCode);
    }
    public void add1(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt1.getText();
        pin.setText(pinCode);
    }
    public void add2(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt2.getText();
        pin.setText(pinCode);
    }
    public void add3(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt3.getText();
        pin.setText(pinCode);
    }
    public void add0(Event actionEvent) throws URISyntaxException {
        buttonSound();
        pinCode += bt0.getText();
        pin.setText(pinCode);
    }
    public void delete(Event actionEvent) throws URISyntaxException {
        pinCode = "";
        deleteSound();
        pin.setText(pinCode);
    }
    public void deleteLast(Event actionEvent) throws URISyntaxException {
        Media musicFile = new Media(getClass().getResource("MP3/clicksoundeffect.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(musicFile);
        mediaPlayer.play();

        if(!pinCode.isEmpty()) {
            String end = "";
            end = pinCode.substring(0, pinCode.length() - 1);
            pinCode = end;
        }
        pin.setText(pinCode);
    }
    private boolean isValid(String s) {
        String regex ="[0-9]+";
        return s.matches(regex);
    }
    public void checkBalance(Event actionEvent) throws URISyntaxException {
        buttonSound();
        if(pinCode.equals("1234")) {
            question.setText("Your balance is: " + user1.getFunds());
        }
        else if(pinCode.equals("1111")) {
            question.setText("Your balance is: " + user2.getFunds());
        }
        else if(pinCode.equals("4444")) {
            question.setText("Your balance is: " + user3.getFunds());
        }
        else if(pinCode.equals("5555")) {
            question.setText("Your balance is: " + user4.getFunds());
        }
    }
    public void accountWithdraw(Event actionEvent) throws URISyntaxException {
        buttonSound();
        if(isValid(input.getText())) {
            if (input.getText().isEmpty() || Long.parseLong(input.getText()) <= 0) {
                question.setText("Please enter the amount of money to withdraw.");
                return;
            }
            if (pinCode.equals("1234")) {
                if ((user1.getFunds() - Long.parseLong(input.getText())) < 0) {
                    question.setText("Error! You can only withdraw: " + user1.getFunds());
                } else {
                    question.setText("Your balance is: " + (user1.getFunds() - Long.parseLong(input.getText())));
                    user1.setFunds(user1.getFunds() - Long.parseLong(input.getText()));
                }
            } else if (pinCode.equals("1111")) {
                if ((user2.getFunds() - Long.parseLong(input.getText())) < 0) {
                    question.setText("Error! You can only withdraw: " + user2.getFunds());
                } else {
                    question.setText("Your balance is: " + (user2.getFunds() - Long.parseLong(input.getText())));
                    user2.setFunds(user2.getFunds() - Long.parseLong(input.getText()));
                }
            } else if (pinCode.equals("4444")) {
                if ((user3.getFunds() - Long.parseLong(input.getText())) < 0) {
                    question.setText("Error! You can only withdraw: " + user3.getFunds());
                } else {
                    question.setText("Your balance is: " + (user3.getFunds() - Long.parseLong(input.getText())));
                    user3.setFunds(user3.getFunds() - Long.parseLong(input.getText()));
                }
            } else if (pinCode.equals("5555")) {
                if ((user4.getFunds() - Long.parseLong(input.getText())) < 0) {
                    question.setText("Error! You can only owithdraw: " + user4.getFunds());
                } else {
                    question.setText("Your balance is: " + (user4.getFunds() - Long.parseLong(input.getText())));
                    user4.setFunds(user4.getFunds() - Long.parseLong(input.getText()));
                }
            }
        } else
            question.setText("Invalid input!");
    }
    public void accountDeposit(Event actionEvent) throws URISyntaxException {
        buttonSound();
        if(isValid(input.getText())) {
            if (input.getText().isEmpty() || Long.parseLong(input.getText()) <= 0) {
                question.setText("Please enter the amount of money to deposit.");
                return;
            }
            if (pinCode.equals("1234")) {
                question.setText("Your balance is: " + (user1.getFunds() + Long.parseLong(input.getText())));
                user1.setFunds(user1.getFunds() + Long.parseLong(input.getText()));
            } else if (pinCode.equals("1111")) {
                question.setText("Your balance is: " + (user2.getFunds() + Long.parseLong(input.getText())));
                user2.setFunds(user2.getFunds() + Long.parseLong(input.getText()));
            } else if (pinCode.equals("4444")) {
                question.setText("Your balance is: " + (user3.getFunds() + Long.parseLong(input.getText())));
                user3.setFunds(user3.getFunds() + Long.parseLong(input.getText()));
            } else if (pinCode.equals("5555")) {
                question.setText("Your balance is: " + (user4.getFunds() + Long.parseLong(input.getText())));
                user4.setFunds(user4.getFunds() + Long.parseLong(input.getText()));
            }
        }else
            question.setText("Invalid input!");
    }
    public void accountExit(Event actionEvent) throws URISyntaxException {
        buttonSound();
        System.exit(0);
    }
    private void saveBlocked(String textfile) {
        try {
            textfile += ".txt";
            File file = new File(textfile);
            FileWriter printer = new FileWriter(file, true);
            printer.write(cardNumber[userNumber] + "\n");
            printer.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }
    private boolean readBlocked(String textfile) {
        try {
            textfile += ".txt";
            File file = new File(textfile);

            Scanner scanner = new Scanner(file);
            String card;
            while(scanner.hasNextLine()) {
                card = scanner.nextLine();
                if(card.equals(this.card.getText())) {
                    scanner.close();
                    return false;
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
        return true;
    }
}

