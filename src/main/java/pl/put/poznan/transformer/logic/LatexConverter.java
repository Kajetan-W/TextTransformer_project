/**
 * The {@code LatexConverter} class provides utility functionality to sanitize plain text 
 * strings into safe, LaTeX-compliant formats.
 * <p>
 * In LaTeX, certain characters have special structural meanings (like compounding math blocks, 
 * defining blocks, or creating comments). This class escapes those reserved characters so that 
 * the resulting text can be safely compiled in a LaTeX engine without throwing syntax errors.
 * </p>
 */
public class LatexConverter {

    /**
     * Converts a plain text string into a string safe for LaTeX document insertion by escaping 
     * its reserved special characters.
     * <p>
     * Standard reserved symbols are escaped with a literal backslash, while glyphs like the tilde 
     * and caret are converted to their formal LaTeX macro equivalents to prevent formatting issues.
     * This method is null-safe.
     * </p>
     * 
     * <p><b>Example:</b></p>
     * <pre>
     * LatexConverter converter = new LatexConverter();
     * String result = converter.convertToLatex("Research & Development costs skyrocketed 50% ~ 60% due to profit_margins.");
     * // Output: "Research \& Development costs skyrocketed 50\% \textasciitilde{} 60\% due to profit\_margins."
     * </pre>
     *
     * @param text The raw plain text string to sanitize.
     * @return A sanitized, LaTeX-safe string, or {@code null} if the input text was null.
     */
    public String convertToLatex(String text) {
        // Fast-fail check to ensure null safety
        if (text == null) {
            return null;
        }
        
        // Note: The double backslashes ("\\") are required in Java to compile down to a 
        // single literal backslash character in the resulting string output.
        return text.replace("&", "\\&")
                    .replace("%", "\\%")
                    .replace("$", "\\$")
                    .replace("#", "\\#")
                    .replace("_", "\\_")
                    .replace("{", "\\{")
                    .replace("}", "\\}")
                    // Tilde and caret require specific macro commands to avoid compiling as accents
                    .replace("~", "\\textasciitilde{}")
                    .replace("^", "\\textasciicircum{}");
    }
}