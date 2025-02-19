
import java.util.List;




interface operations<T extends Number>{
    T add(T x ,T y);
    T sub(T x, T y);
    T multiply(T x, T y);
    T divide(T x , T y);
}



class MathOperations<T extends  Number> implements operations<T>{

    @SuppressWarnings("unchecked")
    @Override
    public T add(T x, T y) {
        
        if (x instanceof Integer) {
            return (T) Integer.valueOf(x.intValue() + y.intValue());
        } else if (x instanceof Double) {
            return (T) Double.valueOf(x.doubleValue() + y.doubleValue());
        } else if (x instanceof Float) {
            return (T) Float.valueOf(x.floatValue() + y.floatValue());
        } else if (x instanceof Long) {
            return (T) Long.valueOf(x.longValue() + y.longValue());
        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
        
    }

    @Override
    public T sub(T x, T y) {
        if (x instanceof Integer) {
            return (T) Integer.valueOf(x.intValue() - y.intValue());
        } else if (x instanceof Double) {
            return (T) Double.valueOf(x.doubleValue() - y.doubleValue());
        } else if (x instanceof Float) {
            return (T) Float.valueOf(x.floatValue() - y.floatValue());
        } else if (x instanceof Long) {
            return (T) Long.valueOf(x.longValue() - y.longValue());
        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
        

    }

    @Override
    public T multiply(T x, T y) {
        if (x instanceof Integer) {
            return (T) Integer.valueOf(x.intValue() * y.intValue());
        } else if (x instanceof Double) {
            return (T) Double.valueOf(x.doubleValue() * y.doubleValue());
        } else if (x instanceof Float) {
            return (T) Float.valueOf(x.floatValue() * y.floatValue());
        } else if (x instanceof Long) {
            return (T) Long.valueOf(x.longValue() * y.longValue());
        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
    }

    @Override
    public T divide(T x, T y) {
        if (x instanceof Integer) {
            return (T) Integer.valueOf(x.intValue() / y.intValue());
        } else if (x instanceof Double) {
            return (T) Double.valueOf(x.doubleValue() / y.doubleValue());
        } else if (x instanceof Float) {
            return (T) Float.valueOf(x.floatValue() / y.floatValue());
        } else if (x instanceof Long) {
            return (T) Long.valueOf(x.longValue() / y.longValue());
        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
    }
    
} 


class Main{
    // wildcard example but wildcard cant be used to define like T
    // if is only used to reference function params which type is unknown
    public static double sumOfList(List<? extends Number> list) {
        double sum = 0.0;
        for (Number num : list) {
            sum += num.doubleValue();
        }
        return sum;
    }
    public static <T extends Number > T adder(T x , T y){
        if (x instanceof Integer) {
            return (T) Integer.valueOf(x.intValue() + y.intValue());
        } else if (x instanceof Double) {
            return (T) Double.valueOf(x.doubleValue() + y.doubleValue());
        } else if (x instanceof Float) {
            return (T) Float.valueOf(x.floatValue() + y.floatValue());
        } else if (x instanceof Long) {
            return (T) Long.valueOf(x.longValue() + y.longValue());
        } else {
            throw new UnsupportedOperationException("Type not supported");
        }
    }
    public static void main(String[] args) {
        MathOperations<Integer> mo = new MathOperations<>();
        System.out.println( adder(1,2));
        System.out.println(mo.divide(1233, 123333)); 
        List<Integer> intList = List.of(1, 2, 3);
        List<Double> doubleList = List.of(1.1, 2.2, 3.3);

        // Using wildcard method to print lists
        System.out.println(sumOfList(intList)); 
        

    }
}