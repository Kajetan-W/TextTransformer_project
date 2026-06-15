package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RepetetiveWordEliminatorTest {

    private RepetetiveWordEliminator eliminator;
    private TextTransform mockTransform;

    @BeforeEach
    public void setUp() {
        mockTransform = mock(TextTransform.class);
        when(mockTransform.transform(anyString())).thenAnswer(i -> i.getArguments()[0]);
        eliminator = new RepetetiveWordEliminator(mockTransform);
    }

    @Test
    public void testTwoRepeats() {
        String input = "This is is a test";
        String expected = "This is a test";
        assertEquals(expected, eliminator.transform(input));
        verify(mockTransform).transform(input);
    }

    @Test
    public void testManyRepeats() {
        String input = "Word word word word end";
        String expected = "Word end";
        assertEquals(expected, eliminator.transform(input));
    }

    @Test
    public void testRepeatsAcrossText() {
        String input = "The the sun is is hot hot today";
        String expected = "The sun is hot today";
        assertEquals(expected, eliminator.transform(input));
    }

    @Test
    public void testUTF8Characters() {
        String input = "Zażółć gęślą gęślą jaźń jaźń";
        String expected = "Zażółć gęślą jaźń";
        assertEquals(expected, eliminator.transform(input));
    }
}
