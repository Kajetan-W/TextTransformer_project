package pl.put.poznan.transformer.logic;

/**
 * Escapes reserved LaTeX characters in plain text so it can be safely used inside a LaTeX document.
 *
 * <p>Example: {@code "50% profit & loss"} → {@code "50\% profit \& loss"}</p>
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class LatexConverter extends TextTransformDecorator {

    public LatexConverter(TextTransform wrapped) {
        super(wrapped);
    }

    @Override
    public String transform(String text) {
        return convertToLatex(wrapped.transform(text));
    }

    public String convertToLatex(String text) {
        if (text == null) return null;

        return text.replace("&", "\\&")
                .replace("%", "\\%")
                .replace("$", "\\$")
                .replace("#", "\\#")
                .replace("_", "\\_")
                .replace("{", "\\{")
                .replace("}", "\\}")
                .replace("~", "\\textasciitilde{}")
                .replace("^", "\\textasciicircum{}");
    }
}