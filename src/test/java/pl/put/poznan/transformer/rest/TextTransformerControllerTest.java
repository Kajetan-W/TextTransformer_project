package pl.put.poznan.transformer.rest;

import org.junit.jupiter.api.Test;
import pl.put.poznan.transformer.model.TransformRequest;
import pl.put.poznan.transformer.model.TransformResponse;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for {@link TextTransformerController}.
 *
 * @author Kajetan Wojnicki
 */
class TextTransformerControllerTest {

    private final TextTransformerController controller = new TextTransformerController();

    @Test
    void get_upperTransform_returnsUppercasedResult() {
        TransformResponse response = controller.transformGet("hello", new String[]{"upper"});

        assertEquals("hello", response.getInput());
        assertEquals("HELLO", response.getResult());
        assertEquals(Collections.singletonList("upper"), response.getTransforms());
    }

    @Test
    void get_noTransforms_returnsInputUnchanged() {
        TransformResponse response = controller.transformGet("Hello", null);

        assertEquals("Hello", response.getResult());
        assertTrue(response.getTransforms().isEmpty());
    }

    @Test
    void get_emptyText_returnsEmptyResult() {
        TransformResponse response = controller.transformGet("", new String[]{"upper"});
        assertEquals("", response.getResult());
    }

    @Test
    void get_chainedUpperThenInverse_matchesReadmeExample() {
        TransformResponse response = controller.transformGet("Mirek", new String[]{"upper", "inverse"});
        assertEquals("KERIM", response.getResult());
    }

    @Test
    void post_jsonRequest_returnsTransformedResult() {
        TransformRequest request = new TransformRequest();
        request.setText("hello");
        request.setTransforms(Collections.singletonList("upper"));

        TransformResponse response = controller.transformPost(request);

        assertEquals("hello", response.getInput());
        assertEquals("HELLO", response.getResult());
    }

    @Test
    void post_nullText_returnsEmptyResult() {
        TransformRequest request = new TransformRequest();
        request.setText(null);
        request.setTransforms(Collections.singletonList("upper"));

        TransformResponse response = controller.transformPost(request);
        assertEquals("", response.getResult());
    }

    @Test
    void post_nullTransforms_returnsInputUnchanged() {
        TransformRequest request = new TransformRequest();
        request.setText("Hello");
        request.setTransforms(null);

        TransformResponse response = controller.transformPost(request);
        assertEquals("Hello", response.getResult());
    }
}
