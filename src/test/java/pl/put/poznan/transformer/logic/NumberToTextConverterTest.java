package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Unit tests for {@link NumberToTextConverter}.
 *
 * @author Otylia Przyłucka
 */
@ExtendWith(MockitoExtension.class)
class NumberToTextConverterTest {

    @Mock
    private TextTransform wrapped;

    @Test
    void convertNumberToText_integer_convertsToWords() {
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("five", converter.convertNumberToText("5"));
    }

    @Test
    void convertNumberToText_zero_returnsZero() {
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("zero", converter.convertNumberToText("0"));
    }

    @Test
    void convertNumberToText_decimal_convertsIntegerAndFractionalParts() {
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("three point fourteen", converter.convertNumberToText("3.14"));
    }

    @Test
    void convertNumberToText_hundredths_convertsWithLeadingZeroFraction() {
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("one point fifty", converter.convertNumberToText("1.50"));
    }

    @Test
    void convertNumberToText_numberInSentence_replacesNumberPreservingSurroundingText() {
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("I have three apples", converter.convertNumberToText("I have 3 apples"));
    }

    @Test
    void transform_delegatesToWrappedThenConvertsNumbers() {
        when(wrapped.transform("in")).thenReturn("buy 2 tickets");
        NumberToTextConverter converter = new NumberToTextConverter(wrapped);
        assertEquals("buy two tickets", converter.transform("in"));
        verify(wrapped).transform("in");
    }
}
