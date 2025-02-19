import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
// define an interface as fucntional interface and then just define the funciton just by using lambda and start using that object

@FunctionalInterface
interface MyFunctionalInterface{
    void saymessage(String msg);
}

public class Lambda {
    public static void executeMessage(MyFunctionalInterface mfi, String message) {
        mfi.saymessage(message);
    }
    public static void main(String[] args) throws Exception {

        // this can be used with single or multiple params
        MyFunctionalInterface msg = (message) -> System.out.println("|| " + message);
        List<String> names = Arrays.asList("John", "Jane", "Jack", "Jill");

        // Using lambda with Comparator
        Collections.sort(names, (a, b) -> a.compareTo(b));
        System.out.println(names);
        msg.saymessage("my message");

        ExecutorService executor = Executors.newSingleThreadExecutor();

        // Using lambda with Callable
        Callable<String> callable = () -> "Callable result";
        Future<String> future = executor.submit(callable);

        System.out.println(future.get());
        executor.shutdown();
        executeMessage(msg,"mymessage");
    }
}


