import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The {@code NumberToTextConverter} class provides utility functionality to scan strings, 
 * identify numerical values (both whole numbers and decimals up to two places), and convert 
 * them into their spoken English word equivalents.
 * <p>
 * This class processes whole numbers up to 999 and handles decimal values by padding single digits 
 * (e.g., transforming "1.5" into "one point fifty").
 * </p>
 * 
 * <p><b>Example:</b></p>
 * <pre>
 * NumberToTextConverter converter = new NumberToTextConverter();
 * String result = converter.convertNumberToText("The total is 105.50 dollars.");
 * // Output: "The total is one hundred and five point fifty dollars."
 * </pre>
 */
public class NumberToTextConverter {
    
    /**
     * Look-up table for words representing numbers from 1 to 19. 
     * Index 0 is intentionally left empty to handle clean numerical offsets.
     */
    private static final String[] units = {
        "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
        "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
        "seventeen", "eighteen", "nineteen"
    };

    /**
     * Look-up table for words representing the tens place (20 through 90).
     * Indices 0 and 1 are intentionally left empty since values under 20 are handled by the units array.
     */
    private static final String[] tens = {
        "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    /**
     * Scans the input text for sequence numbers and replaces them with their text equivalents.
     * <p>
     * It matches both standard integers (e.g., 42) and floating-point decimals with one or two 
     * fractional digits (e.g., 3.1 or 3.14).
     * </p>
     *
     * @param text The source text containing digits to convert.
     * @return The updated text string with numbers converted to words, or the original text if null/empty.
     */
    public String convertNumberToText(String text) {
        // Fast-fail check for empty or null strings
        if (text == null || text.isEmpty()) {
            return text;
        }

        // Matches a decimal number with 1 or 2 digits after the dot (\\d+\\.\\d{1,2})
        // OR (|) matches a standard sequence of whole numbers (\\d+)
        Pattern pattern = Pattern.compile("\\d+\\.\\d{1,2}|\\d+");
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();

        // Iterate through all discovered numeric groups in the text
        while (matcher.find()) {
            String match = matcher.group();
            String replacement;

            // Check if the current match is a floating-point number
            if (match.contains(".")) {
                String[] parts = match.split("\\.");
                int integerPart = Integer.parseInt(parts[0]);
                String fractionString = parts[1];

                // Standardize single decimals (e.g., "0.5" -> parses fraction string as "50" / fifty)
                if (fractionString.length() == 1) {
                    fractionString += "0";
                }
                int fractionPart = Integer.parseInt(fractionString);

                // Convert both halves independently and link them with "point"
                replacement = convertInteger(integerPart) + " point " + convertInteger(fractionPart);
            } else {
                // Process simple whole numbers directly
                int integerPart = Integer.parseInt(match);
                replacement = convertInteger(integerPart);
            }

            // Append intermediate text and the freshly generated word replacement
            matcher.appendReplacement(sb, replacement);
        }

        // Gather any remaining trailing text following the final match
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Converts a single integer value (from 0 up to 999) into its written English word representation.
     *
     * @param number The raw integer value to process.
     * @return The corresponding word description of the integer.
     */
    private String convertInteger(int number) {
            // Guard case for absolute zero
            if (number == 0) {
                return "zero";
            }

            StringBuilder sb = new StringBuilder();

            // Handle the hundreds place breakdown
            if (number >= 100) {
                sb.append(units[number / 100]).append(" hundred");
                number %= 100; // Strip the hundreds position away to isolate tens/units
                if (number > 0) {
                    sb.append(" and ");
                }
            }

            // Handle the remaining values under 100
            if (number >= 20) {
                sb.append(tens[number / 10]); // Isolate and append the tens word
                if (number % 10 > 0) {
                    sb.append("-").append(units[number % 10]); // Hyphenate the unit remainder if it exists
                }
            } else if (number > 0) {
                // Directly pull matching teen/single units if number falls under 20
                sb.append(units[number]);
            }
            return sb.toString().trim();
        }
}