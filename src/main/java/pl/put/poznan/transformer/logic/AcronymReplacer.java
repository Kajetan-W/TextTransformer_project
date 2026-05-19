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

    private final Map<String, String> acronymMap;

    public AcronymReplacer(TextTransform wrapped) {
        super(wrapped);
        acronymMap = new LinkedHashMap<>();
        acronymMap.put("for example", "e.g.");
        acronymMap.put("among others", "i.a.");
        acronymMap.put("and so on", "aso");
    }

    @Override
    public String transform(String text) {
        return replaceWithAcronyms(wrapped.transform(text));
    }

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