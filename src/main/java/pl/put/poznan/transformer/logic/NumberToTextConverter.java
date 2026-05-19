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

    private static final String[] units = {
            "", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
            "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
            "seventeen", "eighteen", "nineteen"
    };

    private static final String[] tens = {
            "", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"
    };

    public NumberToTextConverter(TextTransform wrapped) {
        super(wrapped);
    }

    @Override
    public String transform(String text) {
        return convertNumberToText(wrapped.transform(text));
    }

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
     * Converts a single integer (0–999) to its English word representation.
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