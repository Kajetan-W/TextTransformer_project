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
    private final String input;
    private final List<String> transforms;
    private final String result;

    public TransformResponse(String input, List<String> transforms, String result) {
        this.input = input;
        this.transforms = transforms;
        this.result = result;
    }

    public String getInput() {
        return input;
    }

    public List<String> getTransforms() {
        return transforms;
    }

    public String getResult() {
        return result;
    }
}