package pl.put.poznan.transformer.logic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Expands known acronyms and abbreviations into their full-word equivalents,
 * preserving the capitalization of the original match.
 *
 * <p>Example: {@code "Dr. Smith"} → {@code "Doctor Smith"}, {@code "e.g."} → {@code "for example"}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class AcronymExpander extends TextTransformDecorator {

    /**
     * Maps regex patterns (keys) to their expanded forms (values).
     * Iteration order is preserved via {@link LinkedHashMap}.
     */
    private final Map<String, String> expansionMap;

    /**
     * Constructs an AcronymExpander with a built-in set of abbreviation mappings.
     *
     * @param wrapped the next transformation in the chain
     */
    public AcronymExpander(TextTransform wrapped) {
        super(wrapped);
        expansionMap = new LinkedHashMap<>();
        expansionMap.put("prof\\.", "professor");
        expansionMap.put("dr", "doctor");
        expansionMap.put("e\\.g\\.", "for example");
        expansionMap.put("aso", "and so on");
    }

    /**
     * Expands acronyms in the result of the wrapped transformer.
     *
     * @param text the input text
     * @return the text with known acronyms expanded
     */
    @Override
    public String transform(String text) {
        return expandAcronyms(wrapped.transform(text));
    }

    /**
     * Scans {@code text} for known acronyms and replaces each with its full form,
     * mirroring the capitalization of the first character of the matched token.
     *
     * @param text the text to process; returned unchanged if {@code null} or empty
     * @return the text with acronyms replaced by their expansions
     */
    public String expandAcronyms(String text) {
        if (text == null || text.isEmpty()) return text;

        String result = text;
        for (Map.Entry<String, String> entry : expansionMap.entrySet()) {
            String regex = "(?i)\\b" + entry.getKey();
            if (!entry.getKey().endsWith("\\.")) {
                regex += "\\b";
            }

            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(result);
            StringBuilder sb = new StringBuilder();

            while (matcher.find()) {
                String matchedText = matcher.group();
                String expansion = entry.getValue();

                // Preserve capitalization of the first letter
                if (Character.isUpperCase(matchedText.charAt(0))) {
                    expansion = Character.toUpperCase(expansion.charAt(0)) + expansion.substring(1);
                }
                matcher.appendReplacement(sb, expansion);
            }
            matcher.appendTail(sb);
            result = sb.toString();
        }
        return result;
    }
}
