package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.GeografijaDAO;
import ba.unsa.etf.rpr.Grad;
import ba.unsa.etf.rpr.GradController;
import ba.unsa.etf.rpr.Regija;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import static javafx.scene.layout.Region.USE_COMPUTED_SIZE;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(ApplicationExtension.class)
public class IspitGradControllerTest {
    Stage theStage;
    GradController ctrl;

    @Start
    public void start(Stage stage) throws Exception {
        GeografijaDAO dao = GeografijaDAO.getInstance();
        dao.vratiBazuNaDefault();
        // Dodajemo jednu regiju da bi imalo šta da se testira
        dao.dodajRegiju(new Regija(100, "Unsko-sanski kanton"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/grad.fxml"));
        ctrl = new GradController(null, dao.drzave(), dao.regije());
        loader.setController(ctrl);
        Parent root = loader.load();
        stage.setTitle("Grad");
        stage.setScene(new Scene(root, USE_COMPUTED_SIZE, USE_COMPUTED_SIZE));
        stage.setResizable(false);
        stage.show();
        stage.toFront();
        theStage = stage;
    }


    @Test
    public void testDefaultRegija(FxRobot robot) {
        // Po defaultu polje Regija treba biti prazno
        ChoiceBox<Regija> choiceRegija = robot.lookup("#choiceRegija").queryAs(ChoiceBox.class);
        assertNotNull(choiceRegija);
        assertNull(choiceRegija.getValue());

        // Upisujemo grad
        robot.clickOn("#fieldNaziv");
        robot.write("Bihać");
        robot.clickOn("#fieldBrojStanovnika");
        robot.write("80000");
        robot.clickOn("#choiceDrzava");
        robot.clickOn("Francuska");

        // Ostavljamo polje regija prazno

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Grad bihac = ctrl.getGrad();
        assertEquals("Bihać", bihac.getNaziv());
        assertEquals(80000, bihac.getBrojStanovnika());
        assertEquals("Francuska", bihac.getDrzava().getNaziv());
        assertNull(bihac.getRegija());

    }

    @Test
    public void testUnosRegije(FxRobot robot) {
        // Upisujemo grad
        robot.clickOn("#fieldNaziv");
        robot.write("Bihać");
        robot.clickOn("#fieldBrojStanovnika");
        robot.write("80000");
        robot.clickOn("#choiceDrzava");
        robot.clickOn("Francuska");
        robot.clickOn("#choiceRegija");
        robot.clickOn("Unsko-sanski kanton");

        // Klik na dugme ok
        robot.clickOn("#btnOk");

        Grad bihac = ctrl.getGrad();
        assertEquals("Bihać", bihac.getNaziv());
        assertEquals(80000, bihac.getBrojStanovnika());
        assertEquals("Francuska", bihac.getDrzava().getNaziv());
        assertEquals("Unsko-sanski kanton", bihac.getRegija().getNaziv() );
    }
}