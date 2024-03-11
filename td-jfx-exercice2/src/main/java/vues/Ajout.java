package vues;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modeleCreaFilm.GenreFilm;

import java.io.IOException;
import java.util.Collection;
import java.util.Objects;

public class Ajout extends Vue implements VueInteractive {
    private Controleur controleur;
    @FXML
    private ComboBox<GenreFilm> genreFilmComboBox;

    public Parent getTop() {
        return mainVbox;
    }
    @FXML
    VBox mainVbox;
    @FXML
    TextField titre;

    @FXML
    TextField realisateur;



    public static Ajout creerVue(Controleur controleur, Stage stage)  {
        FXMLLoader fxmlLoader = new FXMLLoader(Ajout.class.getResource("ajout.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            System.out.println("Probleme de construction de vue Formulaire");
        }

        Ajout vue = fxmlLoader.getController();
        vue.setControleur(controleur);
        vue.setStage(stage);
        vue.setScene(new Scene(vue.getTop()));
        vue.initDonees();
        return vue;

    }

    public void gotomenu(ActionEvent actionEvent) {
        controleur.gotoMenu();
    }


    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;
    }


    public void creerFilm(ActionEvent actionEvent) {
        GenreFilm genrefilm = this.genreFilmComboBox.getSelectionModel().getSelectedItem();
        if (Objects.isNull(genrefilm)){
            Alert alert = new Alert(Alert.AlertType.ERROR,"Vous devez selectionner un genre ", ButtonType.OK);
            alert.showAndWait();
        }else {
        controleur.creerFilm(titre.getText(), genrefilm.name(), realisateur.getText());
        }
    }

    public void viderChamps() {
        titre.setText("");
        realisateur.setText("");
    }

    public void afficherErreur(String titre, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR,message);
        alert.setTitle(titre);
        alert.showAndWait();

    }


    public void initDonees(){
        Collection<GenreFilm> genreFilms = this.controleur.getGenre();
        this.genreFilmComboBox.setItems(FXCollections.observableArrayList(genreFilms));
    }
}
