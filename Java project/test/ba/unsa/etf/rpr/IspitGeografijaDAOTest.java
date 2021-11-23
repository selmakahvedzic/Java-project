package ba.unsa.etf.rpr;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class IspitGeografijaDAOTest {
    GeografijaDAO dao = GeografijaDAO.getInstance();
    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }
    @Test
    void testRegije() {
        // Metoda regije() postoji i vraća praznu listu nakon resetovanja baze na default
        ArrayList<Regija> regije = dao.regije();
        assertEquals(0, regije.size());
    }
    @Test
    void testDodajRegiju() {
        dao.dodajRegiju(new Regija(1, "Londonska regija"));
        ArrayList<Regija> regije = dao.regije();
        assertAll(
                () -> assertEquals(1, regije.size()),
                () -> assertEquals(1, regije.get(0).getId()),
                () -> assertEquals("Londonska regija", regije.get(0).getNaziv())
        );
    }
}