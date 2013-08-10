package me.zzp.fn.core.filter;

import java.util.Collection;
import me.zzp.fn.lambda.Fn;
import me.zzp.fn.lambda.Parameter;
import me.zzp.fn.lambda.SideEffect;

public class Each implements SideEffect {
    @Override
    public Void call(Object... args) {
        Parameter params = new Parameter(2, args);
        Fn fn = params.get(0, Fn.class);
        Collection seq = params.get(1, Collection.class);

        for (Object item : seq) {
            fn.call(item);
        }
        
        return null;
    }
}
