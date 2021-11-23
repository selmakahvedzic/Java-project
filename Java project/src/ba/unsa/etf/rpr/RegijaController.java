package ba.unsa.etf.rpr;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;

public class RegijaController {
    public TextField fieldNaziv;
    private Drzava drzava;
    private Grad gradovi;
    private Regija regija;

    public RegijaController(Regija regija) {
        this.regija=regija;
    }

    @FXML
    public void initialize() {
        if (regija != null) {
            fieldNaziv.setText(drzava.getNaziv());
        }
    }

    public Regija getRegija() {
        return regija;
    }

    public void clickOk(ActionEvent actionEvent) {
        boolean sveOk = true;

        if (fieldNaziv.getText().trim().isEmpty()) {
            fieldNaziv.getStyleClass().removeAll("poljeIspravno");
            fieldNaziv.getStyleClass().add("poljeNijeIspravno");
            sveOk = false;
        } else {
            fieldNaziv.getStyleClass().removeAll("poljeNijeIspravno");
            fieldNaziv.getStyleClass().add("poljeIspravno");
        }

        if (!sveOk) return;

        if (regija == null) regija = new Regija();
        regija.setNaziv(fieldNaziv.getText());
        closeWindow();
    }

    public void clickCancel(ActionEvent actionEvent) {
        regija = null;
        closeWindow();
    }

    private void closeWindow() {
        Stage stage = (Stage) fieldNaziv.getScene().getWindow();
        stage.close();
    }
}
