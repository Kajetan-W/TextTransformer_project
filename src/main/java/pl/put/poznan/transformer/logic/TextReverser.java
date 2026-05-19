package pl.put.poznan.transformer.logic;

/**
 * Reverses the characters of text while preserving the original casing pattern at each position.
 *
 * <p>Example: {@code "Hello World"} → {@code "Dlrow Olleh"}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class TextReverser extends TextTransformDecorator {

    public TextReverser(TextTransform wrapped) {
        super(wrapped);
    }

    @Override
    public String transform(String text) {
        return reversePreservingCase(wrapped.transform(text));
    }

    public String reversePreservingCase(String text) {
        if (text == null || text.isEmpty()) return text;

        char[] originalChars = text.toCharArray();
        int length = originalChars.length;

        char[] reversedChars = new char[length];
        for (int i = 0; i < length; i++) {
            reversedChars[i] = originalChars[length - 1 - i];
        }

        char[] finalResult = new char[length];
        for (int i = 0; i < length; i++) {
            char original = originalChars[i];
            char reversed = reversedChars[i];

            if (Character.isUpperCase(original)) {
                finalResult[i] = Character.toUpperCase(reversed);
            } else if (Character.isLowerCase(original)) {
                finalResult[i] = Character.toLowerCase(reversed);
            } else {
                finalResult[i] = reversed;
            }
        }
        return new String(finalResult);
    }
}