package ba.unsa.etf.rpr;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IspitGeografijaDAOZadatak2Test {
    GeografijaDAO dao = GeografijaDAO.getInstance();

    @BeforeEach
    public void resetujBazu() throws SQLException {
        dao.vratiBazuNaDefault();
    }


    @Test
    void testIzmijeniGrad() {
        dao.dodajRegiju(new Regija(100, "Londonska regija"));
        Regija r = dao.regije().get(0);

        Grad london = dao.nadjiGrad("London");
        assertNull(london.getRegija());
        london.setRegija(r);
        dao.izmijeniGrad(london);

        // Uzimam drugu verziju
        Grad london2 = dao.nadjiGrad("London");
        assertEquals("Londonska regija", london2.getRegija().getNaziv());
    }

    @Test
    void testDodajGrad() {
        dao.dodajRegiju(new Regija(100, "Tuzlanski kanton"));
        Regija r = dao.regije().get(0);

        Drzava d = dao.nadjiDrzavu("Francuska");
        Grad tuzla = new Grad(0, "Tuzla", 120000, d);
        tuzla.setRegija(r);
        dao.dodajGrad(tuzla);

        Grad tuzla2 = dao.nadjiGrad("Tuzla");
        assertEquals("Tuzlanski kanton", tuzla2.getRegija().getNaziv());
    }

}
