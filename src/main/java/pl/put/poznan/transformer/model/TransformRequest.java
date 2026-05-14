package pl.put.poznan.transformer.model;
import java.util.List;

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
