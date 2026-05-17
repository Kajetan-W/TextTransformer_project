import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * The {@code AcronymReplacer} class provides utility functionality to automatically
 * condense full phrases into their corresponding short-form acronyms or abbreviations
 * within a given text string.
 * <p>
 * It utilizes regular expressions for case-insensitive matching and safely escapes
 * literal phrases to prevent regex syntax errors.
 * </p>
 * 
 * <p><b>Example:</b></p>
 * <pre>
 * AcronymReplacer replacer = new AcronymReplacer();
 * String result = replacer.replaceWithAcronyms("We studied logic, among others, and so on.");
 * // Output: "We studied logic, i.a., aso."
 * </pre>
 */
public class AcronymReplacer {
    
    /**
     * A map storing long-form phrases as keys and their corresponding acronyms as values.
     * A {@link LinkedHashMap} is used to guarantee insertion order, ensuring phrases
     * are evaluated sequentially in the exact order they were defined.
     */
    private final Map<String, String> acronymMap;

    /**
     * Constructs a new {@code AcronymReplacer} and initializes the default
     * dictionary of target phrases and their acronym replacements.
     */
    public AcronymReplacer() {
        acronymMap = new LinkedHashMap<>();
        acronymMap.put("for example", "e.g.");
        acronymMap.put("among others", "i.a.");
        acronymMap.put("and so on", "aso");
    }

    /**
     * Scans the input text and replaces all known long-form phrases defined in the internal
     * dictionary with their abbreviated acronym forms.
     * <p>
     * The substitution is case-insensitive, meaning phrases like "For example" or "FOR EXAMPLE"
     * will match successfully and be compressed.
     * </p>
     *
     * @param text The source string containing full phrases to compress.
     * @return The text with all recognized phrases replaced by acronyms, or the original text if it is null or empty.
     */
    public String replaceWithAcronyms(String text) {
        // Fast-fail check for empty or null inputs
        if (text == null || text.isEmpty())
            return text;

        String result = text;
        
        // Iterate through each phrase definition in the map
        for (Map.Entry<String, String> entry : acronymMap.entrySet()) {
            
            // Build a case-insensitive (?i) regex, securely quoting the key phrase
            // to treat all characters (like spaces) as literal text rather than regex commands.
            String regex = "(?i)" + Pattern.quote(entry.getKey());
            
            // Replace all occurrences of the phrase with its acronym counterpart
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
}