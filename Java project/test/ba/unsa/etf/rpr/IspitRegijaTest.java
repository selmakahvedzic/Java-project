package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class IspitRegijaTest {
    @Test
    public void testRegija() {
        Regija r = new Regija(1, "Toskana");
        assertAll(
                () -> assertEquals(1, r.getId()),
                () -> assertEquals("Toskana", r.getNaziv())
        );
    }

    @Test
    public void testSetteri() {
        Regija r = new Regija(1, "Toskana");
        r.setId(1234);
        r.setNaziv("Veneto");
        assertAll(
                () -> assertEquals(1234, r.getId()),
                () -> assertEquals("Veneto", r.getNaziv())
        );
    }
}
