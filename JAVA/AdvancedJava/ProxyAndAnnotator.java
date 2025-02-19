import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
// Assume you want to run a function without as a pre or post processing for some function 
// so called decorator in python is the same in java called annotator 
// To make this happen you make a custom annotator and use the custom annotator
// but this wont work directly , You will have to break the execution of the code using the proxy
// To the class for which you want to run a annotator function you will create a object and send the object 
// to the interface proxy and run the fucntion through the proxy 
// proxy will invoke the function you call in its own way using invoke override from invocationhandler


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface LoggerAnnotate{

}
class LoggerProxy implements InvocationHandler{
    final Object target;
    LoggerProxy(Object Target){
        target = Target;

    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        
        
        Method originalMethod = target.getClass().getMethod(method.getName(), method.getParameterTypes());
        for (Annotation annotation : originalMethod.getAnnotations()) {
            System.out.println(annotation);
        }
        if (originalMethod.isAnnotationPresent(LoggerAnnotate.class)){
            System.out.println("method started");
            Object result = method.invoke(target, args);
            System.out.println("Method Completed");
            return result;
        }
        return method.invoke(target, args);
    }

    static <T> T createProxy(T target , Class<T> interfaceType){
        return (T) Proxy.newProxyInstance(interfaceType.getClassLoader(), new Class<?>[]{interfaceType}, new LoggerProxy(target));
    }
}

interface Services{
    
    void putSomething();
    void getSomething();
}


public class ProxyAndAnnotator implements  Services{
    String currKey = "";
    HashMap<String,String> hm = new HashMap<>();
    
    @Override
    @LoggerAnnotate
    public void putSomething() {
         
        hm.put("MyKey","MyKey");
        currKey = "MyKey";
        System.out.println("Inside put");
    }

    @Override
    public void getSomething() {
        if (hm.containsKey("MyKey")){
            hm.get(currKey);
        }
        System.out.println("Inside get");
    }
}

class min{
    public static void main(String[] args) {
        ProxyAndAnnotator serv = new ProxyAndAnnotator();
        Services proxyserv = LoggerProxy.createProxy(serv, Services.class);
        proxyserv.getSomething();
        proxyserv.putSomething();
    }
}

