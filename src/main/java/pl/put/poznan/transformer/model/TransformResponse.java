package pl.put.poznan.transformer.model;

import java.util.List;

/**
 * DTO representing the result of a text transformation request.
 * Contains the original input, the transformations that were applied, and the final result.
 *
 * @author Kajetan Wojnicki
 * @version 1.0
 */
public class TransformResponse {

    /** The original input text before any transformation. */
    private final String input;

    /** The ordered list of transformation names that were applied. */
    private final List<String> transforms;

    /** The text after all transformations have been applied. */
    private final String result;

    /**
     * Constructs a TransformResponse with the given input, transformations, and result.
     *
     * @param input      the original input text
     * @param transforms the ordered list of transformation names that were applied
     * @param result     the transformed text
     */
    public TransformResponse(String input, List<String> transforms, String result) {
        this.input = input;
        this.transforms = transforms;
        this.result = result;
    }

    /**
     * Returns the original input text.
     *
     * @return the input text before transformation
     */
    public String getInput() {
        return input;
    }

    /**
     * Returns the ordered list of transformation names that were applied.
     *
     * @return the applied transformation names
     */
    public List<String> getTransforms() {
        return transforms;
    }

    /**
     * Returns the transformed text.
     *
     * @return the result after all transformations
     */
    public String getResult() {
        return result;
    }
}
