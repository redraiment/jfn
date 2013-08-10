package me.zzp.fn.lambda;

/**
 * Lambda function;
 * @author redraiment
 */
public interface Fn<E> {
    public E call(Object... args);
}
