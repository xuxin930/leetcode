package algorithm;

/**
 * 并查集 Union-Find
 *
 * 用于解决动态联通一类问题
 * 同一族谱寻找祖先问题
 *
 * 主要两个核心功能查Find,并Union
 */
public class UnionFind {
    int[] id; //通路记录数组

    /**
     * 初始化,每一个元素都属于自己的分组
     */
    UnionFind (int num){
        id = new int[num];
        for (int i = 0; i < num ; i++) {
            id[i] = i;
        }
    }

    /**
     *  查--这里使用了优化的路径压缩算法,在寻找的时候直接就把不是祖先的父节点和字节点都直接指向祖先了
     * @param p
     * @return
     */
    int find(int p){
        if (p != id[p]){
            id[p] = find(id[p]);
        }
        return find(id[p]);
    }

    /**
     * 并--这里没有确定树的平衡情况,优化思路是可以引入另外的变量记录i,j两树的大小,再决定谁像谁合并
     * @param p
     * @param q
     */
    void union(int p, int q){
        int i = find(p);
        int j = find(q);
        if (i == j)return;
        id[i] = j;
    }
}
