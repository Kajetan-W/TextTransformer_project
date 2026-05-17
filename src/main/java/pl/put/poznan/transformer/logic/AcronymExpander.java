import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code AcronymExpander} class provides utility functionality to automatically
 * find and replace specified acronyms or abbreviations within a given text snippet.
 * 
 * It utilizes regular expressions for case-insensitive matching, respects word boundaries,
 * and preserves the capitalization of the first letter of the acronym during expansion.
 * 
 * 
 * <p><bExample:</b></p>
 * <pre>
 * AcronymExpander expander = new AcronymExpander();
 * String result = expander.expandAcronyms("Prof. Smith and Dr. John, e.g., went there.");
 * // Output: "Professor Smith and Doctor John, for example, went there."
 * </pre>
 */
public class AcronymExpander {
    
    /**
     * A map storing the acronym patterns as keys and their corresponding full expansions as values.
     * A {@link LinkedHashMap} is used to guarantee insertion order, ensuring that certain 
     * abbreviations are evaluated before others if overlapping definitions exist.
     */
    private final Map<String, String> expansionMap;

    /**
     * Constructs a new {@code AcronymExpander} and initializes the default 
     * dictionary of acronyms and their expansions.
     */
    public AcronymExpander() {
        expansionMap = new LinkedHashMap<>();
        // Note: Dots in abbreviations must be double-escaped ("\\.") to be treated as literal dots in regex.
        expansionMap.put("prof\\.", "professor");
        expansionMap.put("dr", "doctor");
        expansionMap.put("e\\.g\\.", "for example");
        expansionMap.put("aso", "and so on");
    }

    /**
     * Scans the input text and replaces all known acronyms defined in the internal dictionary 
     * with their expanded forms. 
     * <p>
     * This method respects case-sensitivity for the initial letter (e.g., "Dr" becomes "Doctor", 
     * while "dr" becomes "doctor") and uses word boundaries to prevent accidental partial-word replacements.
     * </p>
     *
     * @param text The source string containing acronyms to expand.
     * @return The text with all recognized acronyms expanded, or the original text if it is null or empty.
     */
    public String expandAcronyms(String text) {
        // Fast-fail check for empty or null inputs
        if (text == null || text.isEmpty()) {
            return text;
        }

        String result = text;
        
        // Iterate through each acronym definition in the map
        for (Map.Entry<String, String> entry : expansionMap.entrySet()) {
            
            // Build a case-insensitive (?i) regex starting at a word boundary (\b)
            String regex = "(?i)\\b" + entry.getKey();
            
            // If the acronym doesn't end with a literal dot, enforce a trailing word boundary (\b).
            // This prevents replacing "dr" inside words like "drive" or "drum".
            if (!entry.getKey().endsWith("\\.")) {
                regex += "\\b";
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(result);
            StringBuilder sb = new StringBuilder();

            // Process matches sequentially
            while (matcher.find()) {
                String matchedText = matcher.group();
                String expansion = entry.getValue();

                // Capitalization handling: If the original acronym started with an uppercase letter,
                // capitalize the first letter of the expanded text.
                if (Character.isUpperCase(matchedText.charAt(0))) {
                    expansion = Character.toUpperCase(expansion.charAt(0)) + expansion.substring(1);
                }

                // Append the text preceding the match, followed by the modified expansion
                matcher.appendReplacement(sb, expansion);
            }

            // Append any remaining text after the last match found for this acronym
            matcher.appendTail(sb);
            
            // Pass the updated text string into the next iteration for subsequent acronym checks
            result = sb.toString();
        }
        
        return result;
    }
}