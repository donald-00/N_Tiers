package vues;

import controleur.Controleur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import modeleCreaFilm.Film;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.Objects;

public class TousLesFilms implements VueInteractive {
    private Scene scene;
    private Stage stage;
    private Controleur controleur;


    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    ListView<Film> listeFilms;



    public static TousLesFilms creerVue(Controleur controleur, Stage stage) {

        URL location = TousLesFilms.class.getResource("TousLesFilms.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(location);

        try {
            BorderPane borderPane = fxmlLoader.load();
            TousLesFilms vue = fxmlLoader.getController();
            vue.setStage(stage);

            vue.setControleur(controleur);  // ne pas oublié

            vue.setScene(new Scene(borderPane,600,700));

            vue.customizeview();    // ne pas oublié pour afficher tous les films
            return vue;
        } catch (IOException e) {
            throw new RuntimeException("problème FXML: TousLesFilms.fxml");
        }

    }



    @Override
    public void setControleur(Controleur controleur) {
        this.controleur = controleur;  // ne pas oublié

    }

    public void show() {
        Collection<Film>  films = this.controleur.getAllFilm();
        this.listeFilms.setItems(FXCollections.observableArrayList(films));

        this.stage.setScene(this.scene);
        this.stage.show();

    }

    public void menu(ActionEvent actionEvent) {
        this.controleur.gotoMenu();
    }

    private void customizeview(){
        this.listeFilms.setCellFactory(new Callback<ListView<Film>, ListCell<Film>>() {
            @Override
            public ListCell<Film> call(ListView<Film> filmListView) {
                return new ListCell<>(){
                    @Override
                    protected void updateItem(Film film, boolean b) {
                        if (! Objects.isNull(film)){
                            this.setText(film.getTitre() + " " + film.getRealisateur());
                        }
                    }
                };
            }
        });
    }


}
