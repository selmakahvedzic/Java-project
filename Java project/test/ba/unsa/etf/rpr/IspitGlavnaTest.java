package ba.unsa.etf.rpr;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.sql.SQLException;
import java.util.ArrayList;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(ApplicationExtension.class)
public class IspitGlavnaTest {
    Stage theStage;
    GlavnaController ctrl;
    GeografijaDAO dao = GeografijaDAO.getInstance();

    @Start
    public void start (Stage stage) throws Exception {
        dao.vratiBazuNaDefault();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/glavna.fxml"));
        ctrl = new GlavnaController();
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Gradovi svijeta");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();

        stage.toFront();

        theStage = stage;
    }

    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }

    @AfterEach
    public void zatvoriProzor(FxRobot robot) {
        if (robot.lookup("#btnCancel").tryQuery().isPresent())
            robot.clickOn("#btnCancel");
    }


    @Test
    public void testDodajRegiju(FxRobot robot) {
        robot.clickOn("#btnDodajRegiju");
        robot.write("Lombardija");

        // Klik na dugme Ok
        robot.clickOn("#btnOk");

        ArrayList<Regija> regije = dao.regije();
        assertAll(
                () -> assertEquals(1, regije.size()),
                () -> assertEquals(1, regije.get(0).getId()),
                () -> assertEquals("Lombardija", regije.get(0).getNaziv())
        );
    }
}

