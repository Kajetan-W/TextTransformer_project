package pl.put.poznan.transformer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import pl.put.poznan.transformer.model.TransformRequest;
import pl.put.poznan.transformer.logic.TextTransformer;
import pl.put.poznan.transformer.model.TransformResponse;

import java.util.Collections;
import java.util.Arrays;
import java.util.List;

@RestController
public class TextTransformerController {

    private static final Logger logger = LoggerFactory.getLogger(TextTransformerController.class);

    @GetMapping(value = "/transform", produces = "application/json")
    public TransformResponse transformGet(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "transforms", required = false) String[] transforms) {

        List<String> selectedTransforms = transforms == null
                ? Collections.emptyList()
                : Arrays.asList(transforms);

        return transform(text, selectedTransforms);
    }

    @PostMapping(value = "/transform", consumes = "application/json", produces = "application/json")
    public TransformResponse transformPost(@RequestBody TransformRequest request) {
        String text = request.getText() == null ? "" : request.getText();

        List<String> transforms = request.getTransforms() == null
                ? Collections.emptyList()
                : request.getTransforms();

        return transform(text, transforms);
    }

    private TransformResponse transform(String text, List<String> transforms){
        logger.info("Received text transformation request");
        logger.debug("Input text: {}", text);
        logger.debug("Selected transforms: {}", transforms);
        TextTransformer transformer = new TextTransformer(transforms.toArray(new String[0]));
        String result = transformer.transform(text);
        logger.info("Text transformation completed");
        return new TransformResponse(text, transforms, result);
    }
}


