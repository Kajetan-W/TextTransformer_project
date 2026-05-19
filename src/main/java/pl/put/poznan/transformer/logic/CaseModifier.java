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

    private final String mode;

    /**
     * @param wrapped the next transformation in the chain
     * @param mode one of {@code "upper"}, {@code "lower"}, or {@code "capitalize"}
     */
    public CaseModifier(TextTransform wrapped, String mode) {
        super(wrapped);
        this.mode = mode;
    }

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

    public String toUpper(String text) {
        if (text == null) {
            return null;
        }
        return text.toUpperCase();
    }

    public String toLower(String text) {
        if (text == null) {
            return null;
        }
        return text.toLowerCase();
    }

    /**
     * Title-cases the text: first letter of each word uppercased, rest lowercased.
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