module com.table.editabletableview {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires javaluator;
    requires json.simple;
    requires com.google.gson;


    opens com.table.editabletableview to javafx.fxml;
    exports com.table.editabletableview;
    exports com.table.editabletableview.model;
    opens com.table.editabletableview.model to javafx.fxml, com.google.gson;
    exports com.table.editabletableview.controller;
    opens com.table.editabletableview.controller to javafx.fxml;
}