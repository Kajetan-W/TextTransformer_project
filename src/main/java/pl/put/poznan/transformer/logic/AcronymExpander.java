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

    private final Map<String, String> expansionMap;

    public AcronymExpander(TextTransform wrapped) {
        super(wrapped);
        expansionMap = new LinkedHashMap<>();
        expansionMap.put("prof\\.", "professor");
        expansionMap.put("dr", "doctor");
        expansionMap.put("e\\.g\\.", "for example");
        expansionMap.put("aso", "and so on");
    }

    @Override
    public String transform(String text) {
        return expandAcronyms(wrapped.transform(text));
    }

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