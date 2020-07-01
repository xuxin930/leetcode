package collection;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;
import com.netflix.hystrix.HystrixCommandProperties;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//断路器演示
public class T {

    public static void main(String[] args) throws Exception {
        Person person = new Man();
        Person proxy = (Person) Proxy.newProxyInstance(Person.class.getClassLoader(), new Class[]{Person.class}, new MyInvocationHandler<>(person));
        System.out.println(proxy.say());
    }
}

interface Person {
    String say();
}

class Man implements Person {
    @Override
    public String say() {
        System.out.println("MAN SAY HELLO");
        return "111";
    }
}

class MyInvocationHandler<T> implements InvocationHandler {
    private T target;

    public MyInvocationHandler(T target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("collection.MyInvocationHandler come in");
        method.invoke(target, args);
        MyCommand myCommand = new MyCommand();
        return myCommand.execute();
    }

}

class MyCommand extends HystrixCommand<Object> {

    public MyCommand() {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("123"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().withExecutionTimeoutInMilliseconds(1000))
                .andCommandKey(HystrixCommandKey.Factory.asKey("helloWorldTimeout")));

    }

    @Override
    protected Object run() {
        try {
            System.out.println("collection.MyCommand run");
            Thread.sleep(2000);
        } catch (Throwable e) {
            System.out.println("HystrixRuntimeException");
        }
        return "success run command";
    }

    @Override
    protected Object getFallback() {
        System.out.println("I AM IN FALLBACK");
        return "123";
    }
}