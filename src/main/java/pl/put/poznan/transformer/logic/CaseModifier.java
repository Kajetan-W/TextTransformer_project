package pl.put.poznan.transformer.logic;

/**
 * Modifies the casing of text: uppercase, lowercase, or title-case capitalization.
 * Extends {@link TextTransformDecorator}.
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class CaseModifier extends TextTransformDecorator {

    /** Casing mode: {@code "upper"}, {@code "lower"}, or {@code "capitalize"}. */
    private final String mode;

    /**
     * Constructs a CaseModifier with the given casing mode.
     *
     * @param wrapped the next transformation in the chain
     * @param mode    one of {@code "upper"}, {@code "lower"}, or {@code "capitalize"}
     */
    public CaseModifier(TextTransform wrapped, String mode) {
        super(wrapped);
        this.mode = mode;
    }

    /**
     * Applies the configured casing transformation to the result of the wrapped transformer.
     *
     * @param text the input text
     * @return the text with casing applied
     */
    @Override
    public String transform(String text) {
        String result = wrapped.transform(text);

        switch (mode) {
            case "lower":
                return toLower(result);
            case "capitalize":
                return capitalize(result);
            default:
                return toUpper(result);
        }
    }

    /**
     * Converts all characters in the text to uppercase.
     *
     * @param text the input text; may be {@code null}
     * @return the uppercased text, or {@code null} if input is {@code null}
     */
    public String toUpper(String text) {
        if (text == null) {
            return null;
        }
        return text.toUpperCase();
    }

    /**
     * Converts all characters in the text to lowercase.
     *
     * @param text the input text; may be {@code null}
     * @return the lowercased text, or {@code null} if input is {@code null}
     */
    public String toLower(String text) {
        if (text == null) {
            return null;
        }
        return text.toLowerCase();
    }

    /**
     * Title-cases the text: first letter of each word uppercased, rest lowercased.
     *
     * @param text the input text; may be {@code null} or empty
     * @return the title-cased text, or the original value if {@code null} or empty
     */
    public String capitalize(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder result = new StringBuilder();
        boolean capitalizeNext = true;

        for (char c : text.toCharArray()) {
            if (Character.isWhitespace(c)) {
                capitalizeNext = true;
                result.append(c);
            } else if (capitalizeNext) {
                result.append(Character.toUpperCase(c));
                capitalizeNext = false;
            } else {
                result.append(Character.toLowerCase(c));
            }
        }

        return result.toString();
    }
}
