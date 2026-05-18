import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code RepetetiveWordEliminator} class provides utility functionality to scan 
 * a string and remove consecutive, duplicate words (e.g., "the the" becomes "the").
 * <p>
 * It utilizes advanced Unicode-aware regular expressions to ensure that duplicate 
 * words are caught case-insensitively across multiple text formats and diverse world languages.
 * </p>
 * 
 * <p><b>Example:</b></p>
 * <pre>
 * RepetetiveWordEliminator eliminator = new RepetetiveWordEliminator();
 * String result = eliminator.eliminateRepetitions("This is is a test test string.");
 * // Output: "This is a test string."
 * </pre>
 */
public class RepetetiveWordEliminator {
    
    /**
     * Scans the input text for any sequence of consecutive repeated words and collapses 
     * them into a single instance of that word.
     * <p>
     * The matching protocol is case-insensitive, meaning "The the" or "word WORD" will be 
     * successfully identified as repetitions and collapsed to the first captured casing variant.
     * </p>
     *
     * @param text The source string containing potential word repetitions.
     * @return The sanitized text with consecutive duplicate words removed, or the original text if null/empty.
     */
    public String eliminateRepetitions(String text) {
        // Fast-fail check to ensure null and empty safety
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Regex Breakdown:
        // (?Ui)       -> U: Enables Unicode-aware character classes and boundaries. i: Case-insensitive matching.
        // \b          -> Asserts a word boundary at the start.
        // (\p{L}+)    -> Group 1: Matches one or more literal letters (\p{L} handles international/Unicode alphabet characters).
        // (?:\s+\1)+  -> Non-capturing group matching one or more spaces (\s+) followed exactly by Group 1 (\1), repeated 1+ times.
        // \b          -> Asserts a trailing word boundary.
        String regex = "(?Ui)\\b(\\p{L}+)(?:\\s+\\1)+\\b";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        StringBuilder sb = new StringBuilder();
        
        // Loop through each instance where a word is repeated back-to-back
        while (matcher.find()) {
            // Replaces the entire duplicate cluster match with just the first word instance captured in Group 1 ($1)
            matcher.appendReplacement(sb, "$1");
        }
        
        // Append all remaining un-duplicated text after the final match group
        matcher.appendTail(sb);
        
        return sb.toString();
    }
}