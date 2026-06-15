package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link AcronymReplacer}.
 *
 * @author Otylia Przyłucka
 */
@ExtendWith(MockitoExtension.class)
class AcronymReplacerTest {

    @Mock
    private TextTransform wrapped;

    @Test
    void replaceWithAcronyms_forExample_replacedWithEg() {
        AcronymReplacer replacer = new AcronymReplacer(wrapped);
        assertEquals("e.g. apples", replacer.replaceWithAcronyms("for example apples"));
    }

    @Test
    void replaceWithAcronyms_amongOthers_replacedWithIa() {
        AcronymReplacer replacer = new AcronymReplacer(wrapped);
        assertEquals("fruits i.a. apples", replacer.replaceWithAcronyms("fruits among others apples"));
    }

    @Test
    void replaceWithAcronyms_andSoOn_replacedWithAso() {
        AcronymReplacer replacer = new AcronymReplacer(wrapped);
        assertEquals("cats aso", replacer.replaceWithAcronyms("cats and so on"));
    }

    @Test
    void replaceWithAcronyms_caseInsensitive_replacesRegardlessOfCase() {
        AcronymReplacer replacer = new AcronymReplacer(wrapped);
        assertEquals("e.g. that", replacer.replaceWithAcronyms("For Example that"));
    }

    @Test
    void transform_delegatesToWrappedAndReplacesAcronyms() {
        when(wrapped.transform("in")).thenReturn("for example");
        AcronymReplacer replacer = new AcronymReplacer(wrapped);
        assertEquals("e.g.", replacer.transform("in"));
        verify(wrapped).transform("in");
    }
}
