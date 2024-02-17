package com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ProfesorController implements Initializable{

    @FXML
    private Button btGuardar;
    @FXML
    private Button btBorrar;

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
        //si no hay profesor
        Profesor profesor=crearProfesor();

        //si hay alumnos, añadir el otro
        if (profesor!=null){

            //si no existe el profesor que queremos añdir
            if (!listaProfesores.contains(profesor)){
                listaProfesores.add(profesor);
                //llamar a metodo limpiar entrada de datos
                limpiaDatos();
            }else{  //si existe el profesor
                //crear alerta de tipo confirmacion
                Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres modificar el Profesor con DNI \n" + profesor.getDni());

                //configurar botones si o no
                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                //mostrar la alerta y esperar respuesta
                alerta.showAndWait().ifPresent(response ->{
                    if (response== ButtonType.YES){
//                        System.out.println("Usuario seleccionó 'Si'");
                        //llamamos al metodo que sustituira el profesor
                        sustituyeProfesores(profesor);
                    }
                });
            }

        }
    }


    /**metodo para borrar el profesor mediante el dni*/
    @FXML
    void onClickBorrar(MouseEvent event) {
        //guardo el texto del campo dni
        String dni= tfDni.getText();

        //comprobamos que el dni es correcto
        if (Persona.esCorrectoNIF(dni)){
            //creamos profesor provisional
            Profesor profesor=new Profesor(dni, "", 0);

            //comprobamos que el dni profesor exista en lista
            if (listaProfesores.contains(profesor)){
                //creamos alerta de tipo cinfirmacion
                Alert alerta= new Alert(Alert.AlertType.CONFIRMATION);
                alerta.setTitle("Confirmación");
                alerta.setHeaderText("Quieres eliminar el Profesor con DNI \n" + profesor.getDni());

                //configurar botones si o no
                alerta.getButtonTypes().setAll(ButtonType.YES, ButtonType.NO);

                //mostrar la alerta y esperar respuesta
                alerta.showAndWait().ifPresent(response ->{
                    if (response== ButtonType.YES){
                        //llamamos al metodo que eliminara el profesor
                        borrarProfesor(profesor);
                    }
                });
            }

        }else { //si el dni no es correcto
            iniciaAlertaError("El DNI introducido es incorrecto.");
        }
    }

    /*metodo cuando seleccionamos un elemneto de la lista*/
    @FXML
    void onClickProfesores(MouseEvent event) {

    }


    /*metodo donde inicializaremos el controller, i¡donde introduciremos los datos del alumno y el curso que esta
    * y tambien la tabla donde se veran los alumnos agregados*/
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void initialize(ObservableList<Profesor> listaProfesores) {

        //asignamos la lista
        this.listaProfesores=listaProfesores;
        //iniciar la tabla
        iniciaTableView();
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
        //guardar la edad
        String edadString= tfEdad.getText();

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
        } else if (nombre.isEmpty()|| !nombre.matches("[a-zA-Z]+")) {  //campo vacio
            if (nombre.isEmpty()){
                iniciaAlertaError("El campo Nombre no puede estar vacío");
                tfNombre.requestFocus();
            }else if (!nombre.matches("[a-zA-Z]+")){
                iniciaAlertaError("El campo Nombre no puede contener caracteres que no sean letra.");
                tfNombre.requestFocus();
            }

            //comprobamos que el campo no este vacio
        }else if(edadString.isEmpty()|| edadString.matches("[a-zA-Z]+")){   //si esta vacio
            if (edadString.isEmpty()){
                tfEdad.requestFocus();
                iniciaAlertaError("El campo Edad no puede estar vacío");
            }else if (edadString.matches("[a-zA-Z]+")){
                tfEdad.requestFocus();
                iniciaAlertaError("El campo Edad no puede contener letras");
            }

        }else{  //sino comproamos que sea entero(edad>0)
            try{    //bloque que controla excepciones
                //pasar de string a int la edad
                int edad= Integer.parseInt(tfEdad.getText());
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

    /*metodo que sustituye o cambia la informacion del profesor*/
    private void sustituyeProfesores(Profesor profesor){
        //buscamos posicion del profesor
        int indice=listaProfesores.indexOf(profesor);

        //si existe en la lista
        if (listaProfesores.contains(profesor)){
            //cambiamos en el indice, el profesor
            listaProfesores.set(indice, profesor);
        }
    }

    private void borrarProfesor(Profesor profesor){
        //buscamos posicion del profesor
        int indice= listaProfesores.indexOf(profesor);

        //si existe en la lista
        if (listaProfesores.contains(profesor)){
            //lo borramos
            listaProfesores.remove(indice);
        }
    }


}
