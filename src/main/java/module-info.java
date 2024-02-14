module com.iesochoa.sabrinabouragba.ud06herenciajavafx {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.iesochoa.sabrinabouragba.ud06herenciajavafx to javafx.fxml;
    exports com.iesochoa.sabrinabouragba.ud06herenciajavafx;
    exports com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers;
    exports com.iesochoa.sabrinabouragba.ud06herenciajavafx.model;
    opens com.iesochoa.sabrinabouragba.ud06herenciajavafx.controllers to javafx.fxml;
}