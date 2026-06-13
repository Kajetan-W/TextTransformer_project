package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link CaseModifier}.
 *
 * @author Kajetan Wojnicki
 */
@ExtendWith(MockitoExtension.class)
class CaseModifierTest {

    @Mock
    private TextTransform wrapped;

    @Test
    void toUpper_convertsAllLettersToUppercase() {
        CaseModifier modifier = new CaseModifier(wrapped, "upper");
        assertEquals("HELLO", modifier.toUpper("hello"));
    }

    @Test
    void toLower_convertsAllLettersToLowercase() {
        CaseModifier modifier = new CaseModifier(wrapped, "lower");
        assertEquals("hello", modifier.toLower("HeLLo"));
    }

    @Test
    void capitalize_uppercasesFirstLetterOfEachWord() {
        CaseModifier modifier = new CaseModifier(wrapped, "capitalize");
        assertEquals("Capitalize Text", modifier.capitalize("capitalize text"));
    }

    @Test
    void capitalize_lowercasesRemainingLettersOfEachWord() {
        CaseModifier modifier = new CaseModifier(wrapped, "capitalize");
        assertEquals("Hello World", modifier.capitalize("heLLO wORLD"));
    }

    @Test
    void toUpper_null_returnsNull() {
        CaseModifier modifier = new CaseModifier(wrapped, "upper");
        assertNull(modifier.toUpper(null));
    }

    @Test
    void capitalize_emptyString_returnsEmptyString() {
        CaseModifier modifier = new CaseModifier(wrapped, "capitalize");
        assertEquals("", modifier.capitalize(""));
    }

    @Test
    void transform_upperMode_appliesUppercaseToWrappedResult() {
        when(wrapped.transform("in")).thenReturn("abc");
        CaseModifier modifier = new CaseModifier(wrapped, "upper");

        assertEquals("ABC", modifier.transform("in"));
        verify(wrapped).transform("in");
    }

    @Test
    void transform_lowerMode_appliesLowercaseToWrappedResult() {
        when(wrapped.transform("in")).thenReturn("ABC");
        CaseModifier modifier = new CaseModifier(wrapped, "lower");

        assertEquals("abc", modifier.transform("in"));
        verify(wrapped).transform("in");
    }

    @Test
    void transform_capitalizeMode_appliesCapitalizeToWrappedResult() {
        when(wrapped.transform("in")).thenReturn("hello world");
        CaseModifier modifier = new CaseModifier(wrapped, "capitalize");

        assertEquals("Hello World", modifier.transform("in"));
        verify(wrapped).transform("in");
    }
}
