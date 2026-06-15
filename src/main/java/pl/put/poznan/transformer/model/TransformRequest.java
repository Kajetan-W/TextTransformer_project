package pl.put.poznan.transformer.model;
import java.util.List;

/**
 * DTO representing an incoming text transformation request.
 * Contains the input text and the ordered list of transformations to apply.
 *
 * @author Kajetan Wojnicki
 * @version 1.0
 */
public class TransformRequest {

    /** The input text to be transformed. */
    private String text;

    /** Ordered list of transformation names to apply to the input text. */
    private List<String> transforms;

    /**
     * Constructs an empty TransformRequest.
     * Fields are populated via setters (e.g., by a JSON deserializer).
     */
    public TransformRequest() {
    }

    /**
     * Returns the input text.
     *
     * @return the text to be transformed
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the ordered list of transformation names.
     *
     * @return the transformation names
     */
    public List<String> getTransforms() {
        return transforms;
    }

    /**
     * Sets the input text.
     *
     * @param text the text to be transformed
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Sets the ordered list of transformation names.
     *
     * @param transforms the transformation names to apply
     */
    public void setTransforms(List<String> transforms){
        this.transforms = transforms;
    }
}
