package pl.put.poznan.transformer.logic;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Replaces known long-form phrases with their acronym equivalents.
 *
 * <p>Example: {@code "for example"} → {@code "e.g."}, {@code "and so on"} → {@code "aso"}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class AcronymReplacer extends TextTransformDecorator {

    /**
     * Maps long-form phrases (keys) to their acronym equivalents (values).
     * Iteration order is preserved via {@link LinkedHashMap}.
     */
    private final Map<String, String> acronymMap;

    /**
     * Constructs an AcronymReplacer with a built-in set of phrase-to-acronym mappings.
     *
     * @param wrapped the next transformation in the chain
     */
    public AcronymReplacer(TextTransform wrapped) {
        super(wrapped);
        acronymMap = new LinkedHashMap<>();
        acronymMap.put("for example", "e.g.");
        acronymMap.put("among others", "i.a.");
        acronymMap.put("and so on", "aso");
    }

    /**
     * Replaces long-form phrases in the result of the wrapped transformer with acronyms.
     *
     * @param text the input text
     * @return the text with known phrases replaced by their acronyms
     */
    @Override
    public String transform(String text) {
        return replaceWithAcronyms(wrapped.transform(text));
    }

    /**
     * Scans {@code text} case-insensitively for known long-form phrases and replaces
     * each occurrence with the corresponding acronym.
     *
     * @param text the text to process; returned unchanged if {@code null} or empty
     * @return the text with phrases replaced by acronyms
     */
    public String replaceWithAcronyms(String text) {
        if (text == null || text.isEmpty()) return text;

        String result = text;
        for (Map.Entry<String, String> entry : acronymMap.entrySet()) {
            String regex = "(?i)" + Pattern.quote(entry.getKey());
            result = result.replaceAll(regex, entry.getValue());
        }
        return result;
    }
}
