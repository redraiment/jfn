package me.zzp.fn.core.io;

import me.zzp.fn.lambda.SideEffect;

public class Print implements SideEffect {
    @Override
    public Void call(Object... args) {
        for (Object o : args) {
            System.out.print(o);
        }

        return null;
    }
}
