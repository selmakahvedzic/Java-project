package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class IspitRegijaControllerTest {
    Stage theStage;
    RegijaController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        dao.vratiBazuNaDefault();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/regija.fxml"));
        ctrl = new RegijaController(null);
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Regija");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }

    @Test
    public void testUnosRegije(FxRobot robot) {
        // Upisujemo grad
        robot.clickOn("#fieldNaziv");
        robot.write("Normandija");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Regija r = ctrl.getRegija();
        assertEquals("Normandija", r.getNaziv());
    }
}