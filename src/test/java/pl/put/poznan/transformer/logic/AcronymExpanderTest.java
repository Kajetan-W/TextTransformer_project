package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AcronymExpander}.
 *
 * @author Otylia Przyłucka
 */
@ExtendWith(MockitoExtension.class)
class AcronymExpanderTest {

    @Mock
    private TextTransform wrapped;

    @Test
    void expandAcronyms_prof_expandsToProfessor() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("professor Smith", expander.expandAcronyms("prof. Smith"));
    }

    @Test
    void expandAcronyms_dr_expandsToDoctor() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("doctor Jones", expander.expandAcronyms("dr Jones"));
    }

    @Test
    void expandAcronyms_eg_expandsToForExample() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("for example fruits", expander.expandAcronyms("e.g. fruits"));
    }

    @Test
    void expandAcronyms_aso_expandsToAndSoOn() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("cats and so on", expander.expandAcronyms("cats aso"));
    }

    @Test
    void expandAcronyms_uppercaseFirstLetter_preservedInExpansion() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("Doctor Brown", expander.expandAcronyms("Dr Brown"));
    }

    @Test
    void expandAcronyms_noMatch_returnsTextUnchanged() {
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("hello world", expander.expandAcronyms("hello world"));
    }

    @Test
    void transform_delegatesToWrappedThenExpandsAcronyms() {
        when(wrapped.transform("in")).thenReturn("see dr Smith");
        AcronymExpander expander = new AcronymExpander(wrapped);
        assertEquals("see doctor Smith", expander.transform("in"));
        verify(wrapped).transform("in");
    }
}
