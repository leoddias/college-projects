package br.leonardodias.bigguardbot;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.opencv.core.Core;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.telegram.telegrambots.logging.BotLogger;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXMLDocument.fxml"));
        System.out.println((getClass().getResource("FXMLDocument.fxml").toURI()));
        loader.load();
        FXMLDocumentController controller = loader.getController();
        Parent root = loader.getRoot();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest((ae) -> {
            Platform.exit();
            System.exit(0);
        });
        
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME); // OPENCV
        ApiContextInitializer.init();
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            //Create a telegram api using BigGuardHandler class, passing controller(javafx window) by argument;
            telegramBotsApi.registerBot(new BigGuardHandler(controller));
            System.out.println("..::BigGuardBot - Started::..");
        } catch (TelegramApiException e) {
            BotLogger.error("LOG", e);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
