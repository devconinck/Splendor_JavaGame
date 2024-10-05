module projectMetFXML {
        requires javafx.controls;
        requires javafx.graphics;
        requires javafx.base;
        requires javafx.fxml;
		requires java.sql;


        exports main to javafx.graphics;

        opens gui to javafx.fxml;
}