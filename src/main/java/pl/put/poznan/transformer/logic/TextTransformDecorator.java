package pl.put.poznan.transformer.logic;

/**
 * Abstract base for all decorator transformations.
 * Subclasses call {@code wrapped.transform(text)} first, then apply their own logic.
 *
 * @author Kajetan Wojnicki
 * @author Otylia Przyłucka
 * @version 1.0
 */
public abstract class TextTransformDecorator implements TextTransform {

    /** The next transformation in the decorator chain. */
    protected final TextTransform wrapped;

    /**
     * Constructs a decorator that delegates to the given transformation.
     *
     * @param wrapped the next transformation in the chain; must not be {@code null}
     */
    public TextTransformDecorator(TextTransform wrapped) {
        this.wrapped = wrapped;
    }
}
