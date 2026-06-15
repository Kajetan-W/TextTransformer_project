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

    /**
     * Constructs a LatexConverter that delegates to the given transformation.
     *
     * @param wrapped the next transformation in the chain
     */
    public LatexConverter(TextTransform wrapped) {
        super(wrapped);
    }

    /**
     * Escapes LaTeX special characters in the result of the wrapped transformer.
     *
     * @param text the input text
     * @return the text with LaTeX special characters escaped
     */
    @Override
    public String transform(String text) {
        return convertToLatex(wrapped.transform(text));
    }

    /**
     * Replaces each LaTeX reserved character in {@code text} with its escaped equivalent.
     * The characters handled are: {@code & % $ # _ { } ~ ^}.
     *
     * @param text the text to escape; returns {@code null} if input is {@code null}
     * @return the LaTeX-safe version of the text
     */
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
