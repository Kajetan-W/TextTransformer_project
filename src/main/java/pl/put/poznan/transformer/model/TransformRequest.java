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
    private String text;
    private List<String> transforms;

    public String getText() {
        return text;
    }

    public List<String> getTransforms() {
        return transforms;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTransforms(List<String> transforms){
        this.transforms = transforms;
    }
}
