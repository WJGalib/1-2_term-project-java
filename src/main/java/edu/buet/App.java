package edu.buet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
// import java.util.*;
import java.io.IOException;
// import org.controlsfx.control.Notifications;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Stage mainStage;
    private static Scene scene;
    private static PlayerList playerList = new PlayerList();
    private static NetworkUtil networkUtil = new NetworkUtil();
    private static ReadThreadClient rtc;
    // private static Club clientClub = new Club();
    private static UserMode userMode = UserMode.NONE;
    private static boolean uiUpdate = false;
    private static int tabIdx1 = 0, tabIdx2 = 0;

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Football Player Database System");
        scene = new Scene(loadFXML("login"), 1280, 720);
        stage.setOnCloseRequest(e -> {
            e.consume();
            try {
                // networkUtil.closeConnection();
                if (ConfirmationModal.display("Exit Confirmation", "Are you sure you want to exit the program?")) {
                    stage.close();
                    System.exit(0);
                }
            } catch (Exception x) {
                System.out.println("sorry");
                x.printStackTrace();
            }
        });
        stage.setScene(scene);
        mainStage = stage;
        stage.show();
        if (uiUpdate == true) {
            WarningModal.display("Error", "Failed to connect to server.");
            System.exit(0);
        }
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static PlayerList getPlayerList() {
        return playerList;
    }

    static void setPlayerList (PlayerList playerList) {
        App.playerList = playerList;
    }

    static NetworkUtil getNetworkUtil() {
        return networkUtil;
    }

    static UserMode getUserMode() {
        return userMode;
    }

    static void setUserMode(UserMode userMode) {
        App.userMode = userMode;
    }

    static void setRtc(ReadThreadClient rtc) {
        App.rtc = rtc;
    }

    static ReadThreadClient getRtc() {
        return rtc;
    }

    static void setUiUpdate (boolean uiUpdate) {
        App.uiUpdate = uiUpdate;
    }

    static boolean getUiUpdate() {
        return uiUpdate;
    }
    
    static Stage getMainStage() {
        return mainStage;
    }

    static int getTabIdx1() {
        return tabIdx1;
    }

    static int getTabIdx2() {
        return tabIdx2;
    }

    static void setTabIdx1(int tabIdx1) {
        App.tabIdx1 = tabIdx1;
    }

    static void setTabIdx2(int tabIdx2) {
        App.tabIdx2 = tabIdx2;
    }

    // public static Club getClientClub() {
    //     return clientClub;
    // }

    // public static void setClientClub (Club clientClub) {
    //     App.clientClub = clientClub;
    // }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        fxmlLoader.setLocation(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws Exception {

        try {
            networkUtil = new NetworkUtil("127.0.0.1", 33333);

        } catch (Exception e) {
            System.out.println(e);
            uiUpdate = true;
        }
        launch(args);
    }

}