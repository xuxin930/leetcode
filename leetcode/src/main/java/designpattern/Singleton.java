package designpattern;

/**
 * 单例 延时doublecheck
 */
public class Singleton {
    private volatile static Singleton lazyDoubleCheckSingleton = null;
    private Singleton() {
    }

    public static Singleton getInstance() {
         if (lazyDoubleCheckSingleton == null){
            synchronized (Singleton.class){
                if (lazyDoubleCheckSingleton == null){
                    lazyDoubleCheckSingleton = new Singleton();
                }
            }
         }
        return lazyDoubleCheckSingleton;
    }
}
