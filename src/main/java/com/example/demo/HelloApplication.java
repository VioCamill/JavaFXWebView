package com.example.demo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.web.WebView;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();

        // WebView, um eine Webseite anzuzeigen
        WebView webView = new WebView();
        // html von Quizlet
       // String html = <iframe src= "https://quizlet.com/840888381/learn/embed?i=5gqs03&x=1jj1" height="500" width="100%" style="border:0"></iframe>;

        webView.getEngine().load("https://www.memrise.com/de/");  // Webseite hier laden

        // Benutzerdefinierter Inhalt neben der Webseite
        StackPane contentPane = new StackPane();
        contentPane.setPrefWidth(300);

        Button button = new Button("Record");
        contentPane.getChildren().addAll(button);
        StackPane.setAlignment(button, javafx.geometry.Pos.CENTER);

        root.setCenter(webView);  // Webseite in der Mitte platzieren
        root.setRight(contentPane); // Benutzerdefinierten Inhalt auf der linken Seite platzieren
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recording Interface");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
//Try
//Spring Webseite
//Google Chrome Pluggins