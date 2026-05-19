package pl.put.poznan.transformer.logic;

/**
 * Entry point for applying a sequence of text transformations.
 * Builds a decorator chain from the provided transform names and executes them in order.
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms) {
        this.transforms = transforms;
    }

    /**
     * Applies all configured transformations to the input text in order.
     *
     * @param text the input text
     * @return the transformed text
     */
    public String transform(String text) {
        String input = text == null ? "" : text;

        if (transforms == null) {
            return input;
        }

        TextTransform pipeline = new TextTransform() {
            @Override
            public String transform(String text) {
                return text;
            }
        };

        for (String transform : transforms) {
            if (transform == null) {
                continue;
            }

            String name = transform.toLowerCase();

            switch (name) {
                case "upper":
                    pipeline = new CaseModifier(pipeline, "upper");
                    break;
                case "lower":
                    pipeline = new CaseModifier(pipeline, "lower");
                    break;
                case "capitalize":
                    pipeline = new CaseModifier(pipeline, "capitalize");
                    break;
                case "inverse":
                case "reverse":
                    pipeline = new TextReverser(pipeline);
                    break;
                case "latex":
                case "escape":
                    pipeline = new LatexConverter(pipeline);
                    break;
                case "acronym":
                case "acronyms":
                case "replace":
                    pipeline = new AcronymReplacer(pipeline);
                    break;
                case "expand":
                case "expander":
                    pipeline = new AcronymExpander(pipeline);
                    break;
                case "number":
                case "numbers":
                    pipeline = new NumberToTextConverter(pipeline);
                    break;
                case "duplicates":
                case "repetitions":
                case "repetitive":
                    pipeline = new RepetetiveWordEliminator(pipeline);
                    break;
                default:
                    break;
            }
        }

        return pipeline.transform(input);
    }
}