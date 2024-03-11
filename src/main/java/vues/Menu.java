package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Menu implements VueInteractive {

    private Scene scene;
    private Stage stage;

    private Controleur controleur;

    private BorderPane borderPane;
    private Button consulter;
    private Button ajouter;

    public static Menu creerVue(Controleur controleur, Stage stage) {

        Menu menu = new Menu();
        menu.setStage(stage);
        menu.setControleur(controleur);
        menu.initialiserComposante();
        return menu;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur=controleur;

    }


    private void initialiserComposante() {
        this.borderPane = new BorderPane();
        this.consulter = new Button("Consulter");
        this.ajouter = new Button("Ajouter");

        this.consulter.setOnAction(e-> controleur.gotoConsulter()); // on ajoute cette ligne et celle suivant pour gerer le redirection vers d'autres pages
        this.ajouter.setOnAction(e-> controleur.gotoAjout());

        this.consulter.setMaxWidth(Double.MAX_VALUE);
        this.ajouter.setMaxWidth(Double.MAX_VALUE);

        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(20);
        vBox.getChildren().addAll(this.consulter,this.ajouter);
        this.borderPane.setCenter(vBox);

        Label label = new Label("Les Films");
        label.setFont(Font.font(32));
        this.borderPane.setTop(label);


        BorderPane.setAlignment(label,Pos.CENTER);
        BorderPane.setAlignment(vBox,Pos.CENTER);


        this.setScene(new Scene(this.borderPane,600,700));

    }

    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }
}
