package me.zzp.fn.core.io;

import me.zzp.fn.lambda.SideEffect;

public class Println implements SideEffect {
    @Override
    public Void call(Object... args) {
        for (Object o : args) {
            System.out.println(o);
        }

        return null;
    }
}
