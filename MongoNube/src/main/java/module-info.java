module org.softli.utl.mongonube {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;





    opens org.softli.utl.mongonube to javafx.fxml;
    exports org.softli.utl.mongonube;
}