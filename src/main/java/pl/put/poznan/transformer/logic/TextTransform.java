package pl.put.poznan.transformer.logic;

/**
 * Common interface for all text transformations.
 * Each implementation applies a single transformation to the input text.
 *
 * @author Kajetan Wojnicki
 * @version 1.0
 */
public interface TextTransform {
    String transform(String text);
}