package pl.put.poznan.transformer.logic;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts numeric values found in text into their English word equivalents.
 * Supports whole numbers up to 999 and decimals up to two fractional digits.
 *
 * <p>Example: {@code "The total is 105.50 dollars."} → {@code "The total is one hundred and five point fifty dollars."}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class NumberToTextConverter extends TextTransformDecorator {

    /** English words for numbers 0–19. Index corresponds to the number value. */
    private static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
    };

    /** English words for multiples of ten from 20 to 90. Index corresponds to the tens digit. */
    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    /**
     * Constructs a NumberToTextConverter that delegates to the given transformation.
     *
     * @param wrapped the next transformation in the chain
     */
    public NumberToTextConverter(TextTransform wrapped) {
        super(wrapped);
    }

    /**
     * Converts numbers in the result of the wrapped transformer to English words.
     *
     * @param text the input text
     * @return the text with all numeric literals replaced by their word equivalents
     */
    @Override
    public String transform(String text) {
        return convertNumberToText(wrapped.transform(text));
    }

    /**
     * Scans {@code text} for integer and decimal number literals and replaces each
     * with its English word equivalent. Decimals use "point" as the separator.
     *
     * @param text the text to process; returned unchanged if {@code null} or empty
     * @return the text with numbers converted to words
     */
    public String convertNumberToText(String text) {
        if (text == null || text.isEmpty()) return text;

        Pattern pattern = Pattern.compile("\\d+\\.\\d{1,2}|\\d+");
        Matcher matcher = pattern.matcher(text);
        StringBuilder sb = new StringBuilder();

        while (matcher.find()) {
            String match = matcher.group();
            String replacement;

            if (match.contains(".")) {
                String[] parts = match.split("\\.");
                int integerPart = Integer.parseInt(parts[0]);
                String fractionString = parts[1];

                if (fractionString.length() == 1) {
                    fractionString += "0";
                }
                int fractionPart = Integer.parseInt(fractionString);
                replacement = convertInteger(integerPart) + " point " + convertInteger(fractionPart);
            } else {
                replacement = convertInteger(Integer.parseInt(match));
            }

            matcher.appendReplacement(sb, replacement);
        }

        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * Converts a single integer in the range 0–999 to its English word representation.
     *
     * @param number the integer to convert; must be in [0, 999]
     * @return the English word representation of {@code number}
     */
    private String convertInteger(int number) {
        if (number == 0) return "zero";

        StringBuilder sb = new StringBuilder();

        if (number >= 100) {
            sb.append(units[number / 100]).append(" hundred");
            number %= 100;
            if (number > 0) sb.append(" and ");
        }

        if (number >= 20) {
            sb.append(tens[number / 10]);
            if (number % 10 > 0) sb.append("-").append(units[number % 10]);
        } else if (number > 0) {
            sb.append(units[number]);
        }

        return sb.toString().trim();
    }
}
