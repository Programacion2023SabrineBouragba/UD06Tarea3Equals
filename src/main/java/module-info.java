module com.iesochoa.sabrinabouragba.ud06herenciajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.iesochoa.sabrinabouragba.ud06herenciajavafx to javafx.fxml;
    exports com.iesochoa.sabrinabouragba.ud06herenciajavafx;
}