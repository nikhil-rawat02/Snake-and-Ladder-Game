module com.example.project_snake_ladder {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project_snake_ladder to javafx.fxml;
    exports com.example.project_snake_ladder;
}