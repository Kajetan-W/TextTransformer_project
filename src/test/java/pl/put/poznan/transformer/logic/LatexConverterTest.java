package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LatexConverterTest {

    private LatexConverter latexConverter;
    private TextTransform mockTransform;

    @BeforeEach
    public void setUp() {
        mockTransform = mock(TextTransform.class);
        when(mockTransform.transform(anyString())).thenAnswer(i -> i.getArguments()[0]);
        latexConverter = new LatexConverter(mockTransform);
    }

    @Test
    public void testAmpersandReplacement() {
        String input = "Profit & Loss";
        String expected = "Profit \\& Loss";
        assertEquals(expected, latexConverter.transform(input));
        verify(mockTransform).transform(input);
    }

    @Test
    public void testDollarReplacement() {
        String input = "Costs $50";
        String expected = "Costs \\$50";
        assertEquals(expected, latexConverter.transform(input));
        verify(mockTransform).transform(input);
    }

    @Test
    public void testCombinedSpecialChars() {
        String input = "The 100% & ~ costs #1 _test_ ^ {}";
        String expected = "The 100\\% \\& \\textasciitilde{} costs \\#1 \\_test\\_ \\textasciicircum{} \\{\\}";
        assertEquals(expected, latexConverter.transform(input));
    }

    @Test
    public void testNoSpecialChars() {
        String input = "Normal text without special characters";
        String expected = "Normal text without special characters";
        assertEquals(expected, latexConverter.transform(input));
    }
}
