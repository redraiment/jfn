package me.zzp.fn.core.predicate;

import me.zzp.fn.lambda.Fn;
import me.zzp.fn.lambda.Parameter;

/**
 * 比较运算符
 * @author redraiment
 */
public class Cmp implements Fn<Integer> {
    @Override
    public Integer call(Object... args) {
        Parameter params = new Parameter(2, args);
        Comparable cmp = params.get(0, Comparable.class);
        return cmp.compareTo(params.get(1));
    }
}
