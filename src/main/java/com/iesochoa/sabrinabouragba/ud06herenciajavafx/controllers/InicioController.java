package com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers;

import com.iesochoa.sabrinabouragba.ud06herenciajavafx.IESOchoaApplication;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Alumno;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Profesor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class InicioController implements Initializable {

    @FXML
    private Button btAlumnos;

    @FXML
    private Button btProfesores;

    @FXML
    private HBox hbDatos;

    @FXML
    private VBox vbIntroducir;
    @FXML
    void onClickAlumnos(ActionEvent event) {
        abrirAlumnos();
    }

    @FXML
    void onClickProfesores(ActionEvent event) {
        abrirProfesores();
    }

    private ObservableList<Alumno> listaAlumnos;
    private ObservableList<Profesor> listaProfesores;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        listaAlumnos= FXCollections.observableArrayList();

        listaProfesores= FXCollections.observableArrayList();
    }

    /*metodo para iniciar alumno*/
    private void abrirAlumnos(){
        try{
            //cargamos la escena desde el recurso
            FXMLLoader loader=new FXMLLoader(IESOchoaApplication.class.getResource("alumno-view.fxml"));
            Parent root=loader.load();

            //creamos el controller, y cuando este se inicializa, le pasamos la lista de alumnos
            AlumnoController alumnoController=loader.getController();
            alumnoController.initialize(listaAlumnos);
            Scene scene= new Scene(root);
            //iniciamos nuevo stage en forma modal con la scene
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

    /*metodo para iniciar profesor*/
    private void abrirProfesores(){
        try{
            //cargamos la escena desde el recurso
            FXMLLoader loader=new FXMLLoader(IESOchoaApplication.class.getResource("profesor-view.fxml"));
            Parent root=loader.load();

            //creamos el controller, y cuando este se inicializa, le pasamos la lista de profesores
            ProfesorController profesorController=loader.getController();
            profesorController.initialize(listaProfesores);
            Scene scene= new Scene(root);
            //iniciamos nuevo stage en forma modal con la scene
            Stage stage= new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(scene);
            stage.showAndWait();
        }catch (IOException e){
            System.err.println(e.getMessage());
        }
    }

}





