package me.zzp.fn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import me.zzp.fn.core.arithmetic.Add;
import me.zzp.fn.core.filter.Each;
import me.zzp.fn.core.io.Print;
import me.zzp.fn.core.io.Println;
import me.zzp.fn.core.predicate.Cmp;
import me.zzp.fn.core.predicate.Less;
import me.zzp.fn.lambda.Fn;
import me.zzp.fn.lambda.Predicate;
import me.zzp.fn.lambda.SideEffect;

public final class Core {
    private Core() {
    }

    public static class F {
        // IO
        public static SideEffect print = new Print();
        public static SideEffect println = new Println();
        
        // arithmetic
        public static Fn<Integer> add = new Add();
        
        // predicate
        public static Fn<Integer> cmp = new Cmp();
        public static Predicate less = new Less();
        
        // filter
        public static SideEffect each = new Each();
    }
    
    // IO
    public static void print(Object... args) {
        F.print.call(args);
    }

    public static void println(Object... args) {
        F.println.call(args);
    }

    //
    
    public static Integer add(Object... args) {
        return F.add.call(args);
    }
    
    //

    public static Integer cmp(Object... args) {
        return F.cmp.call(args);
    }

    public static Boolean less(Object... args) {
        return F.less.call(args);
    }
    
    //
    
    public static void each(Fn<?> fn, Collection seq) {
        F.each.call(fn, seq);
    }

    public static <E> Collection<E> map(Fn<E> fn, Collection<?> seq) {
        try {
            Collection<E> result = seq.getClass().newInstance();
            for (Object item : seq) {
                result.add(fn.call(item));
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }

    // Utils
    
    public static Collection<Integer> range(int to) {
        return range(0, to);
    }
    
    public static Collection<Integer> range(int from, int to) {
        return range(from, to, 1);
    }

    public static Collection<Integer> range(int from, int to, int step) {
        Collection<Integer> list = new ArrayList<>();
        for (int i = from; i < to; i += step) {
            list.add(i);
        }
        return list;
    }
    
    // High order function
    
    public static <E> E reduce(Fn<E> fn, Collection<?> seq) {
        if (seq.isEmpty()) {
            return null;
        }
        
        Iterator<?> it = seq.iterator();
        E result = seq.size() == 1? fn.call(it.next()): fn.call(it.next(), it.next());
        while (it.hasNext()) {
            result = fn.call(result, it.next());
        }
        return result;
    }

    public static <E> Collection<E> filter(Fn<Boolean> fn, Collection<E> seq) {
        try {
            Collection<E> result = seq.getClass().newInstance();
            for (E item : seq) {
                if (fn.call(item)) {
                    result.add(item);
                }
            }
            return result;
        } catch (InstantiationException | IllegalAccessException e) {
            return null;
        }
    }
    
    public static <E> Fn<E> partial(final Fn<E> fn, final Object... args) {
        return new Fn<E>() {
            @Override
            public E call(Object... more_args) {
                Object[] params = new Object[args.length + more_args.length];
                System.arraycopy(args, 0, params, 0, args.length);
                System.arraycopy(more_args, 0, params, args.length, more_args.length);

                return fn.call(params);
            }
        };
    }

    public static Fn<Boolean> complement(final Fn<Boolean> fn) {
        return new Fn<Boolean>() {
            @Override
            public Boolean call(Object... args) {
                return !fn.call(args);
            }
        };
    }
}
