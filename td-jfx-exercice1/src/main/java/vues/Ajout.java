package vues;

import controleur.Controleur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.Film;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;

public class Ajout implements VueInteractive {
    private Scene scene;
    private Stage stage;
    private Controleur controleur;

    @FXML
    private TextField titre;
    @FXML
    private TextField genre;
    @FXML
    private TextField realisateur;

    public static Ajout creerVue(Controleur controleur, Stage stage) {
        URL location = Ajout.class.getResource("creerfilm.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();
            Ajout vue = fxmlLoader.getController();
            vue.setStage(stage);
            vue.setControleur(controleur);    // on ajoute ca pour gerer le creer films
            vue.setScene(new Scene(borderPane,600,700));
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML: créerfilm.fxml");
        }
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;   // ne pas oublié egalement

    }



    public void show() {
        this.stage.setScene(this.scene);
        this.stage.show();
    }

    public void afficherErreur(String erreur_de_genre, String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR,erreur_de_genre, ButtonType.OK);
        alert.setTitle(s);
        alert.showAndWait();
    }

    public void viderChamps() {
        this.titre.setText("");
        this.genre.setText("");
        this.realisateur.setText("");
    }

    public void creerfilm(ActionEvent actionEvent) {
        String titreText = this.titre.getText();
        String genreText = this.genre.getText();
        String realisateurText = this.realisateur.getText();

        if (titreText.length()>0 && genreText.length()>0 && realisateurText.length()>0 ){
            this.controleur.creerFilm(titreText,genreText,realisateurText);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"le film a été bien crée",ButtonType.OK);
            alert.showAndWait();
        }
        else {
            this.afficherErreur("toutes les informations sont obligatoires ","Erreur de Saisie ");
        }
    }

    public void menu(ActionEvent actionEvent) {
        this.controleur.gotoMenu();
    }
}
