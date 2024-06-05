module com.skpcorp.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires java.sql;

    opens com.skpcorp.crud to javafx.fxml;
    exports com.skpcorp.crud;
    exports com.skpcorp.crud.data;
      exports com.skpcorp.crud.model;
}
