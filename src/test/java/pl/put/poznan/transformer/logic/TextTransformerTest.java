package pl.put.poznan.transformer.logic;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TextTransformerTest {

    @Test
    public void testSingleTransform() {
        String[] transforms = {"upper"};
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform("hello");
        assertEquals("HELLO", result);
    }

    @Test
    public void testChainedTransforms() {
        String[] transforms = {"upper", "reverse"};
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform("hello");
        assertEquals("OLLEH", result);
    }

    @Test
    public void testUnknownNameIgnored() {
        String[] transforms = {"upper", "unknown_transform", "reverse"};
        TextTransformer transformer = new TextTransformer(transforms);
        String result = transformer.transform("hello");
        assertEquals("OLLEH", result); // The unknown name is ignored
    }
    
    @Test
    public void testEmptyAndNullTransforms() {
        TextTransformer transformerEmpty = new TextTransformer(new String[]{});
        assertEquals("hello", transformerEmpty.transform("hello"));

        TextTransformer transformerNull = new TextTransformer(null);
        assertEquals("hello", transformerNull.transform("hello"));
    }

    @Test
    public void testMockDecoratorsAndChainOrder() {
        TextTransform mock1 = mock(TextTransform.class);
        TextTransform mock2 = mock(TextTransform.class);
        
        when(mock1.transform(anyString())).thenReturn("mock1");
        when(mock2.transform(anyString())).thenReturn("mock2");
        
        TextTransform chain = new CaseModifier(new TextReverser(mock1), "upper");
        
        // When we call transform on the chain
        String result = chain.transform("test");
        
        // Mock1 should be called since it's the innermost
        verify(mock1, times(1)).transform("test");
        // We verify chain order by checking that the final output is capitalized "MOCK1" reversed which is "1KCOM" then upper?
        // Wait, TextReverser reverses mock1's output "mock1" -> "1kcom"
        // CaseModifier "upper" makes it "1KCOM"
        assertEquals("1KCOM", result);
    }
}
