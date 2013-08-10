package me.zzp.fn.lambda;

public final class Parameter {
    private Object[] args;
    
    public Parameter(int min, Object... args) {
        if (args.length < min) {
            throw new IllegalArgumentException(String.format("Wrong number of arguments: %d/%d", args.length, min));
        }
        this.args = args;
    }
    
    public int size() {
        return args.length;
    }
    
    public Object get(int index) {
        if (index < 0 || size() <= index) {
            throw new ArrayIndexOutOfBoundsException(index);
        }
        return args[index];
    }

    public <E> E get(int index, Class<E> type) {
        Object param = get(index);
        
        if (param == null) {
            return null;
        }

        if (!type.isInstance(param)) {
            throw new IllegalArgumentException(String.format("Wrong type of arguments: %s/%s", param.getClass().getName(), type.getName()));
        }

        return type.cast(args[index]);
    }
}
