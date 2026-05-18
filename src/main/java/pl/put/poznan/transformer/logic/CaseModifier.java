/**
 * The {@code CaseModifier} class provides utility methods for altering the casing
 * of string text. 
 * <p>
 * It offers standard uppercase and lowercase conversions with built-in null-safety, 
 * alongside a custom capitalization method that reformats text into title case (Capitalizing The First Letter Of Each Word).
 * </p>
 */
public class CaseModifier {
    
    /**
     * Converts all characters in the provided string to uppercase.
     * <p>
     * This method is null-safe; if a {@code null} reference is passed, it returns {@code null}
     * instead of throwing a {@link NullPointerException}.
     * </p>
     *
     * @param text The source string to be converted to uppercase.
     * @return The uppercase string, or {@code null} if the input was null.
     */
    public String toUpper(String text) {
        if (text == null) {
            return null;
        }
        return text.toUpperCase();
    }

    /**
     * Converts all characters in the provided string to lowercase.
     * <p>
     * This method is null-safe; if a {@code null} reference is passed, it returns {@code null}
     * instead of throwing a {@link NullPointerException}.
     * </p>
     *
     * @param text The source string to be converted to lowercase.
     * @return The lowercase string, or {@code null} if the input was null.
     */
    public String toLower(String text) {
        if (text == null) {
            return null;
        }
        return text.toLowerCase();
    }

    /**
     * Capitalizes the first letter of every word in the provided text while forcing all 
     * subsequent letters in those words to lowercase. 
     * <p>
     * Words are identified by looking for preceding whitespace characters. This method handles 
     * both {@code null} and empty strings safely.
     * </p>
     * 
     * <p><b>Example:</b></p>
     * <pre>
     * CaseModifier modifier = new CaseModifier();
     * String result = modifier.capitalize("jAvA pRoGrAmMiNg");
     * // Output: "Java Programming"
     * </pre>
     *
     * @param text The source string to process into title-case capitalization.
     * @return The capitalized string, or the original input if it is null or empty.
     */
    public String capitalize(String text) {
        // Guard clause to prevent operations on null or empty strings
        if (text == null || text.isEmpty()) {
            return text;
        }
        
        StringBuilder result = new StringBuilder();
        // State flag tracking whether the next encountered non-whitespace character should be capitalized.
        // Initialized to true so the very first character of the text is capitalized.
        boolean capitalizeNext = true;

        // Iterate through each individual character in the string sequence
        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                // If a whitespace is found, toggle the state flag to true 
                // so the start of the next word gets capitalized.
                capitalizeNext = true;
                result.append(c);
            } else if (capitalizeNext) {
                // Capitalize the first letter of the word, then drop the flag
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                // Lowercase any remaining mid-word letters
                result.append(Character.toLowerCase(c));
            }
        }
        return result.toString();
    }
}