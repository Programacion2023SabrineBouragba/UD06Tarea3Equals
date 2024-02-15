package com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Alumno;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Profesor;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Curso;
import com.iesochoa.sabrinabouragba.ud06herenciajavafx.model.Persona;
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
import java.util.ResourceBundle;

public class ProfesorController implements Initializable{

    @FXML
    private Button btGuardar;

    @FXML
    private ComboBox<String> cbCurso;

    @FXML
    private Label dni;

    @FXML
    private HBox hbDatos;
    @FXML
    private TableView<Profesor> tvProfesores;

    @FXML
    private TableColumn<Profesor, String> tcDni;

    @FXML
    private TableColumn<Profesor, Integer> tcEdad;

    @FXML
    private TableColumn<Profesor, String> tcNombre;

    @FXML
    private TextField tfDni;

    @FXML
    private TextField tfEdad;

    @FXML
    private TextField tfNombre;

    @FXML
    private VBox vbIntroducir;

    private ObservableList<Profesor> listaProfesores;

    //metodo donde le vamos a dar funcion al boton guardar
    @FXML
    void onClickGuardar(ActionEvent event) {
        //si no hay alumno
        Profesor profesor=crearProfesor();

        //si hay alumnos, añadir el otro
        if (profesor!=null){
            listaProfesores.add(profesor);

            //llamar a metodo limpiar entrada de datos
            limpiaDatos();
        }
    }

    /*metodo donde inicializaremos el controller, i¡donde introduciremos los datos del alumno y el curso que esta
    * y tambien la tabla donde se veran los alumnos agregados*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //iniciar el curso
        iniciaCbCurso();
        //iniciar la tabla
        iniciaTableView();
    }


    /*metodo donde inicializaremos el curso que cursa el alumno, es decir seleccionar el modulo en el que esta
    * y guardarlo*/
    private void iniciaCbCurso(){
        //recuperamos valores del Enum Curso
        Curso[] cursos= Curso.values();

        //recorremos la clase e iremos almacenando los datos
        for (Curso curso: cursos){
            //los añadimos al combobox
            cbCurso.getItems().add(curso.toString());
        }


        // Seleccionamos el primero si hay elementos, valor por defecto
        if (cursos.length > 0) {
            cbCurso.setValue(cursos[0].toString()); // o cursos[0].name()
        }
    }


    //metodo donde inicializaremos los mensajes de alerta que vamos a utilizar si se introducen datos erroneos
    private void iniciaAlertaError(String mensaje){
        //creamos la alerta de tipo Error
        Alert alert= new Alert(Alert.AlertType.ERROR);
        //la creamos sin titulo
        alert.setHeaderText(null);
        //mostramos el mensaje
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /*metodo donde vamos creando el alumno*/
    private Profesor crearProfesor(){

        //obtenemos del text field los datos introducidos
        String nombre=tfNombre.getText();
        //obtenemos el DNI
        String dni= tfDni.getText();
        //creamos el objeto curso, y obtenemos los datos seleccionamos en el combobox
        Curso curso=Curso.valueOf(cbCurso.getValue());
        //guardar la edad
        String edadString= tfEdad.getText();
        int edad=0;

        //creamos el profesor inicialemnte null
        Profesor profesor=null;
        //comprobamos que los datos introducidos esten correctos
        //si el dni esta vacio o incorrecto
        if (dni.isEmpty() || !Persona.esCorrectoNIF(dni)){
            if (dni.isEmpty()){ //campo vacio
                iniciaAlertaError("El campo DNI no puede estar vacío");
                //ponemos un cursor en el textField DNI
                tfDni.requestFocus();
            } else if (!Persona.esCorrectoNIF(dni)) {   //campo incorrecto
                iniciaAlertaError("El DNI es incorrecto");
                //ponemos un foco/cursor en el textField DNI
                tfDni.requestFocus();
            }

            //hacmos lo mismo en el campo nombre, comprobamos los datos
        } else if (nombre.isEmpty()) {  //campo vacio
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

            //comprobamos que el campo no este vacio

        }else if(tfEdad.getText().isEmpty()){   //si esta vacio
            tfEdad.requestFocus();
            iniciaAlertaError("El campo Edad no puede ser estar vacío");
        }else{  //sino comproamos que sea entero(edad>0)
            try{    //bloque que controla excepciones
                //pasa el texto introducido a integer
                edad= Integer.parseInt(tfEdad.getText());
                //creamos el profesor con los datos itroducidos hasta ahora, ya que estan comprobados y bien
                profesor= new Profesor(dni, nombre, edad);
            }catch (NumberFormatException e){   //si no se cumple la condicion de ser entero
                //mensaje de error
                iniciaAlertaError("El campo Edad no puede ser menor que 0");
                //foco en el campo edad
                tfEdad.requestFocus();
            }
        }
        //devuelvo el profesor
        return profesor;
    }

    //metodo donde inicializamos la vista de la tabla de los alumnos que vamos creando
    private void iniciaTableView(){
        //iniciamos lista alumnos en la tabla
        listaProfesores= FXCollections.observableArrayList();

        //asociamos las columnas con los datos indicando el nombre de la clase
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tcEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));

        //asociamos esta lista a la tabla
        tvProfesores.setItems(listaProfesores);
    }

    //metodo donde vaciamos estos campos, para facilitar la entrada de datos nueva
    private void limpiaDatos(){
        tfEdad.clear();
        tfNombre.clear();
        tfDni.clear();
    }


}
