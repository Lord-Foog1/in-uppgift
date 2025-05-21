package org.example;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ComboBoxBase;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class PathFinder extends Application {

    File map;
    Image background;

    @Override
    public void start(Stage stage) throws Exception {
        BorderPane root = new BorderPane();
        stage.setTitle("PathFinder");

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");

        FlowPane top = new FlowPane();
        HBox dropDownMenu = new HBox();
        String[] choises = {"New Map", "Open", "Save", "Save Image", "Exit"};
        ComboBox dropDown = new ComboBox(FXCollections.observableArrayList(choises));
        dropDownMenu.getChildren().add(dropDown);
        top.setAlignment(Pos.TOP_LEFT);
        //root.getChildren().add(top);

        dropDown.onActionProperty().set(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if(dropDown.getSelectionModel().getSelectedItem().equals("New Map")) {
                    fileChooser.showSaveDialog(stage);
                    map = fileChooser.getInitialDirectory();
                    if(map != null) {
                        background = new Image(new File(map.getPath()).toURI().toString());
                        ImageView imageView = new ImageView(background);
                        root.setCenter(imageView);
                    }
                }
            }
        });

        HBox options = new HBox();
        Button find = new Button("Find Path");
        find.setDisable(true);
        Button show = new Button("Show Connection");
        show.setDisable(true);
        Button newPlace = new Button("New Place");
        newPlace.setDisable(true);
        Button newConnection = new Button("New Connection");
        newConnection.setDisable(true);
        Button change = new Button("Change Connection");
        change.setDisable(true);
        options.getChildren().addAll(find, show, newPlace, newConnection, change);
        top.getChildren().addAll(options, dropDownMenu, show, newPlace, newConnection, change);

        root.setTop(top);

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }

    private class UpdateHandler implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent actionEvent) {

        }
    }
}
