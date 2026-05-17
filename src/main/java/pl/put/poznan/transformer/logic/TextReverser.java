/**
 * The {@code TextReverser} class provides utility functionality to reverse the sequence 
 * of characters in a string while strictly preserving the case structure (uppercase vs. lowercase) 
 * of the original indices.
 * <p>
 * If a particular index in the original text holds an uppercase letter, the character shifted into 
 * that index will be dynamically forced to uppercase, keeping formatting patterns stable.
 * </p>
 * 
 * <p><b>Example:</b></p>
 * <pre>
 * TextReverser reverser = new TextReverser();
 * String result = reverser.reversePreservingCase("Hello World");
 * // Casing skeleton: U-L-L-L-L   U-L-L-L-L
 * // Output:          "Dlrow Olleh"
 * </pre>
 */
public class TextReverser {

    /**
     * Reverses the characters of the input text while locking the capitalization properties 
     * to their original positional index coordinates.
     * <p>
     * This method handles {@code null} or empty inputs safely by returning them immediately.
     * </p>
     *
     * @param text The source string to reverse.
     * @return The case-preserved reversed string, or the original text if it was null or empty.
     */
    public String reversePreservingCase(String text) {
        // Fast-fail check for empty or null strings
        if (text == null || text.isEmpty()) return text;

        char[] originalChars = text.toCharArray();
        int length = originalChars.length;

        // Step 1: Populate a fresh character array with a mirror reversal of the original array
        char[] reversedChars = new char[length];
        for (int i = 0; i < length; i++) {
            reversedChars[i] = originalChars[length - 1 - i];
        }

        // Step 2: Correlate original casing layout onto the freshly mirrored text
        char[] finalResult = new char[length];
        for (int i = 0; i < length; i++) {
            char originalChar = originalChars[i];
            char reversedChar = reversedChars[i];

            if (Character.isUpperCase(originalChar)) {
                // If the original character at this index was uppercase, force the incoming character to match
                finalResult[i] = Character.toUpperCase(reversedChar);
            } else if (Character.isLowerCase(originalChar)) {
                // If the original character at this index was lowercase, force the incoming character to match
                finalResult[i] = Character.toLowerCase(reversedChar);
            } else {
                // Maintain raw mappings for symbols, spaces, numbers, or special punctuation
                finalResult[i] = reversedChar;
            }
        }
        return new String(finalResult);
    }
}