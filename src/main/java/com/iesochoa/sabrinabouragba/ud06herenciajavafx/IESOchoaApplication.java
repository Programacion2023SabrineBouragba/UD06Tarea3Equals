package com.iesochoa.sabrinabouragba.ud06herenciajavafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class IESOchoaApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(IESOchoaApplication.class.getResource("alumno-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 620, 450);
        stage.setTitle("Datos Alumnado");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}