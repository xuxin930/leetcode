package collection;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 模拟实现一个ArrayBlockingQ
 */
public class MyArrayBlockingQueue<T> {
    private T[] items; //  数组
    private Lock lock = new ReentrantLock(); //锁
    private Condition full = lock.newCondition(); //满条件
    private Condition empty = lock.newCondition(); //空条件
    private int count; //队列数据数量
    private int putIndex; //存指针
    private int takeIndex; //取指针

    //构造,传入初始数组
    public MyArrayBlockingQueue(int size) {
        items = (T[]) new Object[size];
    }

    // 存
    public void put(T t) {
        lock.lock();
        try {
            while (count == items.length) {
                //队列已满,存阻塞等待
                full.await();
            }
            //队列未满,插入
            items[putIndex] = t;
            //循环利用数组,插入指针满了从0开始计
            if (++putIndex == items.length) {
                putIndex = 0;
            }
            //元素数量增加
            count++;
            //确定添加唤醒空等待的
            empty.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    // 取
    public T take() {
        lock.lock();
        T t = null;
        try {
            while (count == 0) {
                //空队列,阻塞取
                empty.await();
            }
            t = items[takeIndex];
            if (++takeIndex == items.length) {
                takeIndex = 0;
            }
            count--;
            full.signalAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
        return t;
    }
}
