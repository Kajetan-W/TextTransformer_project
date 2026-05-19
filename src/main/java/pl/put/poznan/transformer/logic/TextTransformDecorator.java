package pl.put.poznan.transformer.logic;

/**
 * Abstract base for all decorator transformations.
 * Subclasses call {@code wrapped.transform(text)} first, then apply their own logic.\
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public abstract class TextTransformDecorator implements TextTransform {

    protected final TextTransform wrapped;

    public TextTransformDecorator(TextTransform wrapped) {
        this.wrapped = wrapped;
    }
}