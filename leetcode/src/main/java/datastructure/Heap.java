package datastructure;

import java.util.Arrays;

/**
 * 堆
 * 堆是一个数组实现的完全二叉树:没有父指针子指针。他是根据‘堆属性’排序，‘堆属性’决定了节点的位置
 * 作用：
 * 构建优先队列；
 * 堆排序；
 * 快速查找最值；
 * 装逼；
 * 定义：n个元素的序列｛k1，k2，k3...ki...kn｝满足（ki<k2i && ki<k2i+1）或者(ki>k2i && ki>k2i+1)时可视为堆结构
 * 父节点最大的就是大根堆,最小的就是小跟堆
 *
 *  核心操作分两个:上浮和下沉
 *  上浮就是从向下交换找到满足自己堆属性的位置
 *  下沉反之
 *
 *  push就是在末尾添加,然后不断上浮
 *  pop就是弹出根,然后尾巴顶替根,然后不断下沉
 *
 *  堆排序:
 *  先建大根堆->pop堆顶到末尾->调整递归
 */
public class Heap {
    //结算一个节点的父节点和左右子节点,注意不能越界
    public int parent(int i) {
        if (i == 0) {
            return -1;
        }
        return (i - 1) / 2;
    }

    public int left(int i) {
        return 2 * i + 1;
    }

    public int right(int i) {
        return 2 * i + 2;
    }

    /**
     * 大根堆下沉.小根堆也一样,就比较时候相反
     *
     * @param heap
     * @param i
     * @param heapLength
     */
    public void sink(int[] heap, int i, int heapLength) {
        int l = left(i);
        int r = right(i);
        int max = -1;
        //找到父左右三个中最大的
        if (l < heapLength && heap[i] < heap[l]) {
            max = l;
        } else {
            max = i;
        }
        if (r < heapLength && heap[max] < heap[r]) {
            max = r;
        }
        //父不是最大的就交换
        if (i != max) {
            int temp = heap[i];
            heap[i] = heap[max];
            heap[max] = temp;
            //交换后,子树递归
            sink(heap, max, heapLength);
        }
    }

    /**
     * 大根堆上浮
     * 示例就是尾巴元素上浮了,正常插入要素组扩容什么的
     * @param x
     */
    public void shiftUp(int[] heap, int x) {
        //假设在堆最尾部插入了一个节点
        int index = heap.length - 1;
        for (;index > 1 && x > heap[parent(index)];index =parent(index)){
                heap[index] = heap[parent(index)];
                heap[parent(index)] = x;
        }
    }

    /**
     * 堆排序
     */
    public void heapSort(int[] arr){
        //建堆,****注意 这里一定要从最后一层父节点到根节点倒着下沉,从跟下沉可能会漏掉数据!!!!
        for (int i = arr.length/2 - 1; i >= 0; i--) {
            sink(arr,i,arr.length);
        }
        for (int i = arr.length -1; i > 0; i--) {
            //堆顶和末尾交换
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            //长度是越来越小的 i
            sink(arr,0,i);
        }
    }

    public static void main(String[] args) {
        Heap h = new Heap();
        int[] arr = {7,8,6,2,87,878,111,99,9,87,5,2,1,4,7,5,6,544,77};
        h.heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}