package me.zzp.fn.core.arithmetic;

import me.zzp.fn.lambda.Fn;
import me.zzp.fn.lambda.Parameter;

public class Add implements Fn<Integer> {
    @Override
    public Integer call(Object... args) {
        Parameter params = new Parameter(0, args);

        int sum = 0;
        for (int i = 0; i < params.size(); i++) {
            sum += params.get(i, Integer.class);
        }
        return sum;
    }
}
