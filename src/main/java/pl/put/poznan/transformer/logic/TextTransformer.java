package pl.put.poznan.transformer.logic;

public class TextTransformer {

    private final String[] transforms;

    public TextTransformer(String[] transforms) {
        this.transforms = transforms;
    }

    public String transform(String text) {
        String result = text == null ? "" : text;

        if (transforms == null) {
            return result;
        }

        CaseModifier caseModifier = new CaseModifier();
        TextReverser textReverser = new TextReverser();
        LatexConverter latexConverter = new LatexConverter();
        AcronymReplacer acronymReplacer = new AcronymReplacer();
        AcronymExpander acronymExpander = new AcronymExpander();
        NumberToTextConverter numberToTextConverter = new NumberToTextConverter();
        RepetetiveWordEliminator repetitiveWordEliminator = new RepetetiveWordEliminator();

        for (String transform : transforms) {
            if (transform == null) {
                continue;
            }

            String name = transform.toLowerCase();

            switch (name) {
                case "upper":
                    result = caseModifier.toUpper(result);
                    break;
                case "lower":
                    result = caseModifier.toLower(result);
                    break;
                case "capitalize":
                    result = caseModifier.capitalize(result);
                    break;
                case "inverse":
                case "reverse":
                    result = textReverser.reversePreservingCase(result);
                    break;
                case "latex":
                case "escape":
                    result = latexConverter.convertToLatex(result);
                    break;
                case "acronym":
                case "acronyms":
                case "replace":
                    result = acronymReplacer.replaceWithAcronyms(result);
                    break;
                case "expand":
                case "expander":
                    result = acronymExpander.expandAcronyms(result);
                    break;
                case "number":
                case "numbers":
                    result = numberToTextConverter.convertNumberToText(result);
                    break;
                case "duplicates":
                case "repetitions":
                case "repetitive":
                    result = repetitiveWordEliminator.eliminateRepetitions(result);
                    break;
                default:
                    break;
            }
        }

        return result;
    }
}