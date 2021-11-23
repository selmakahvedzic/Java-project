package ba.unsa.etf.rpr;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class IspitGradTest {
    @Test
    public void testSetRegija() {
        Grad g = new Grad(1, "Sarajevo", 350000, null);
        // Default regija treba biti null
        assertNull(g.getRegija());

        // Setter
        g.setRegija(new Regija(1, "Sarajevski kanton"));
        assertEquals("Sarajevski kanton", g.getRegija().getNaziv());
    }

    @Test
    public void testCtor() {
        // Konstruktor sa pet parametara
        Grad g = new Grad(1, "Visoko", 45000, null, new Regija(2, "Zeničko-dobojski kanton"));
        assertEquals("Zeničko-dobojski kanton", g.getRegija().getNaziv());
    }
}
