package pl.put.poznan.transformer.logic;

/**
 * Common interface for all text transformations.
 * Each implementation applies a single transformation to the input text.
 *
 * @author Kajetan Wojnicki
 * @version 1.0
 */
public interface TextTransform {

    /**
     * Applies this transformation to the given text.
     *
     * @param text the input text to transform
     * @return the transformed text
     */
    String transform(String text);
}