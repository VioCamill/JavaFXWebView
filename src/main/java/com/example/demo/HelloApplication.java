package com.example.demo;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import java.io.IOException;


public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        BorderPane root = new BorderPane();

        // WebView, um eine Webseite anzuzeigen
        WebView webView = new WebView();
        // html von Quizlet
        // String html = <iframe src= "https://quizlet.com/840888381/learn/embed?i=5gqs03&x=1jj1" height="500" width="100%" style="border:0"></iframe>;
        WebEngine engine = webView.getEngine();
        engine.setJavaScriptEnabled(true);
        engine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.61 Safari/537.36");
        TextArea htmlTextArea = new TextArea();
        htmlTextArea.setWrapText(true);
        addLoadListener(engine, htmlTextArea);
        engine.load("https://www.quizlet.com");  // Webseite hier laden
        // Benutzerdefinierter Inhalt neben der Webseite
        StackPane contentPane = new StackPane();
        contentPane.setPrefWidth(300);

        Button button = new Button("Record");
        contentPane.getChildren().addAll(htmlTextArea);
        StackPane.setAlignment(button, javafx.geometry.Pos.CENTER);

        root.setCenter(webView);  // Webseite in der Mitte platzieren
        root.setRight(contentPane); // Benutzerdefinierten Inhalt auf der linken Seite platzieren
        Scene scene = new Scene(root, 1000, 800);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Recording Interface");
        primaryStage.show();
    }

    void addLoadListener(WebEngine engine, TextArea textArea) {
        engine.getLoadWorker().stateProperty().addListener(
                new ChangeListener<Worker.State>() {
                    public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {
                        if (newState == Worker.State.SUCCEEDED) {
                            Document doc = engine.getDocument();
                            try {
                                String html = (String) engine.executeScript("document.documentElement.outerHTML");
                                //String html = engine.getUserAgent();
                                textArea.setText(html);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                });
    }

    public static void main(String[] args) {
        launch();
    }
}
//Try
//Spring Webseite
//Google Chrome Pluggins