package pl.put.poznan.transformer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Removes consecutive duplicate words from text, case-insensitively.
 *
 * <p>Example: {@code "This is is a test test."} → {@code "This is a test."}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class RepetetiveWordEliminator extends TextTransformDecorator {

    /**
     * Constructs a RepetetiveWordEliminator that delegates to the given transformation.
     *
     * @param wrapped the next transformation in the chain
     */
    public RepetetiveWordEliminator(TextTransform wrapped) {
        super(wrapped);
    }

    /**
     * Eliminates consecutive duplicate words from the result of the wrapped transformer.
     *
     * @param text the input text
     * @return the text with consecutive duplicate words removed
     */
    @Override
    public String transform(String text) {
        return eliminateRepetitions(wrapped.transform(text));
    }

    /**
     * Scans {@code text} for runs of the same word separated by whitespace and
     * collapses each run to a single occurrence. Matching is Unicode-aware and
     * case-insensitive.
     *
     * @param text the text to deduplicate; returned unchanged if {@code null} or empty
     * @return the text with consecutive duplicate words collapsed to one
     */
    public String eliminateRepetitions(String text) {
        if (text == null || text.isEmpty()) return text;

        // Matches a word followed by one or more repetitions of itself (Unicode-aware, case-insensitive)
        Pattern pattern = Pattern.compile("(?Ui)\\b(\\p{L}+)(?:\\s+\\1)+\\b");
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            matcher.appendReplacement(sb, "$1");
        }

        matcher.appendTail(sb);
        return sb.toString();
    }
}
