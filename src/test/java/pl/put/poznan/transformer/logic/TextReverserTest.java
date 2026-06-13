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
 * Unit tests for {@link TextReverser}.
 *
 * @author Kajetan Wojnicki
 */
@ExtendWith(MockitoExtension.class)
class TextReverserTest {

    @Mock
    private TextTransform wrapped;

    @Test
    void reverse_simpleLowercaseWord_isReversed() {
        TextReverser reverser = new TextReverser(wrapped);
        assertEquals("cba", reverser.reversePreservingCase("abc"));
    }

    @Test
    void reverse_preservesCasePositions_mirEk() {
        TextReverser reverser = new TextReverser(wrapped);
        assertEquals("KerIm", reverser.reversePreservingCase("MirEk"));
    }

    @Test
    void reverse_preservesCasePositions_helloWorld() {
        TextReverser reverser = new TextReverser(wrapped);
        assertEquals("Dlrow Olleh", reverser.reversePreservingCase("Hello World"));
    }

    @Test
    void reverse_singleCharacter_isUnchanged() {
        TextReverser reverser = new TextReverser(wrapped);
        assertEquals("A", reverser.reversePreservingCase("A"));
    }

    @Test
    void reverse_emptyString_returnsEmptyString() {
        TextReverser reverser = new TextReverser(wrapped);
        assertEquals("", reverser.reversePreservingCase(""));
    }

    @Test
    void reverse_null_returnsNull() {
        TextReverser reverser = new TextReverser(wrapped);
        assertNull(reverser.reversePreservingCase(null));
    }

    @Test
    void transform_reversesWrappedResult() {
        when(wrapped.transform("in")).thenReturn("abc");
        TextReverser reverser = new TextReverser(wrapped);

        assertEquals("cba", reverser.transform("in"));
        verify(wrapped).transform("in");
    }
}
