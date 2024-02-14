package com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Alumno;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Curso;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Persona;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AlumnoController implements Initializable{

    @FXML
    private Button btGuardar;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private Label dni;

    @FXML
    private HBox hbDatos;
    @FXML
    private TableView<Alumno> tvAlumnos;

    @FXML
    private TableColumn<Alumno, Curso> tcCurso;

    @FXML
    private TableColumn<Alumno, String> tcDni;

    @FXML
    private TableColumn<Alumno, Integer> tcEdad;

    @FXML
    private TableColumn<Alumno, String> tcNombre;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;

    @FXML
    private VBox vbIntroducir;

    private ObservableList<Alumno> listaAlumnos;
    @FXML
    void onClickGuardar(ActionEvent event) {
        //si no hay alumno
        Alumno alumno=creaAlumno();

        //si hay alumnos, crear otro
        if (alumno!=null){
            listaAlumnos.add(alumno);

            //limpiar entrada de datos
            limpiaDatos();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        iniciaCbCurso();
        iniciaTableView();
    }


    private void iniciaCbCurso(){
        //recuperamos valores del Enum Curso
        Curso[] cursos= Curso.values();

        for (Curso curso: cursos){
            //los añadimos al combobox
            cbCurso.getItems().add(curso.toString());
        }


        // Seleccionamos el primero si hay elementos
        if (cursos.length > 0) {
            cbCurso.setValue(cursos[0].toString()); // o cursos[0].name()
        }
    }

    private void iniciaAlertaError(String mensaje){
        //creamos la alerta de tipo Error
        Alert alert= new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        //mostramos el mensaje
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    private Alumno creaAlumno(){

        //obtenemos del text field los datos introducidos
        String nombre=tfNombre.getText();
        //obtenemos el DNI
        String dni= tfDni.getText();
        //creamos el objeto curso, y obtenemos los datos seleccionamos en el combobox
        Curso curso=Curso.valueOf(cbCurso.getValue());
        //guardar el entero introducido como edad
        String edadString= tfEdad.getText();
        int edad=0;

        //creamos el alumno inicialemnte null
        Alumno alumno=null;
        //comprobamos que los datos introducidos esten correctos
        if (dni.isEmpty() || !Persona.esCorrectoNIF(dni)){
            if (dni.isEmpty()){
                iniciaAlertaError("El campo DNI no puede estar vacío");
                //ponemos un foco en el textField DNI
                tfDni.requestFocus();
            } else if (!Persona.esCorrectoNIF(dni)) {
                iniciaAlertaError("El DNI es incorrecto");
                //ponemos un foco en el textField DNI
                tfDni.requestFocus();
            }

            //hacmos lo mismo en el campo nombre, comprobamos los datos
        } else if (nombre.isEmpty()) {
            iniciaAlertaError("El campo Nombre no puede estar vacío");
            tfNombre.requestFocus();

            //verificamos que la edad no este vacia
//        } else if (edadString.isEmpty()) {
//                iniciaAlertaError("El campo Edad no puede ser estar vacío");
//                tfEdad.requestFocus();
//        }else{  //si no esta vacía, comprobamos q no sea menor que 0, convirtiendolo en integer
//            edad = Integer.parseInt(edadString);
//
//            if (edad<0){
//                iniciaAlertaError("El campo Edad no puede ser menor que 0");
//                tfEdad.requestFocus();
//            }
//
//
//        }
//        alumno= new Alumno(dni, nombre, edad, curso);

        }else if(tfEdad.getText().isEmpty()){
            tfEdad.requestFocus();
            iniciaAlertaError("El campo Edad no puede ser estar vacío");
        }else{
            try{    //bloque que controla excepciones
                //pasa el texto introducido a integer entero
                edad= Integer.parseInt(tfEdad.getText());
                //creamos el alumno
                alumno= new Alumno(dni, nombre, edad, curso);
            }catch (NumberFormatException e){   //si no se cumple la condicion de ser entero
                //mensaje de error
                iniciaAlertaError("El campo Edad no puede ser menor que 0");
                //foco en el campo edad
                tfEdad.requestFocus();
            }
        }
        return alumno;
    }

    private void iniciaTableView(){
        //iniciamos lista alumnos en la tabla
        listaAlumnos= FXCollections.observableArrayList();

        //asociamos las columnas con los datos indicando el nombre de la clase
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        tcCurso.setCellValueFactory(new PropertyValueFactory<>("curso"));

        //asociamos esta lista a la tabla
        tvAlumnos.setItems(listaAlumnos);
    }

    private void limpiaDatos(){
        tfEdad.clear();
        tfNombre.clear();
        tfDni.clear();
    }


}
