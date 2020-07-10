package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * LCA(Least Common Ancestor)
 * 最近公共祖先问题，在一个没有环的树上，两个节点在树上深度最大的公共祖先节点，LCA常用处理两个点仅有唯一一条确定的最短路径时的路径。
 *
 *  暴力解：遍历每个节点往上找父节点,最近相同的就是LCA 时间复杂度O(n*q)
 *
 *  常用LCA算法:Tarjan/DFS+ST/倍增法。后两个都是在线算法，tarjan离线。
 *
 *  Tarjan:离线算法，一次遍历把所有查询问题一次解决，时间复杂度0（n+q）
 *         初始祖先节点都为自己。
 *         从根节点u开始遍历所有的子节点v，并标记被访问的节点。
 *         合并v到u上，v祖先变为u的祖先。（合并的最优方式是并差集）
 *         查看当前遍历节点事有存在查询关系。
 *         若存在查询关系，看查询关系节点q是否被标记访问，如果被访问过了，则可以确认q的祖先就是要查找的LCA。
 *
 *  DFS+ST:把LCA问题转换成RMQ问题
 *         LCA目标节点可以转换成包含两个节点的最小子树的根---既包括这两个节点的数中深度最小的点
 *         搞一个欧拉序遍历一遍就得到从点A离开到进入点B所经历的所有的点了,最小深度就是LCA
 *         把树转成序列->用欧拉序代替DFS序遍历->遍历时记录每个节点的深度->变为RMQ问题->ST表求解
 *
 *  倍增法:TODO:
 *
 *
 *
 *
 */
public class LCA {
    int[] ancestor; //节点祖先集合
    boolean[] visited; //访问记录集合
    List<int[]> querys; //查询请求
    int[][] queryResult; //离线结果

    /**
     * tarjan 离线算法
     *
     * @param node
     */
    void tarjan(Node node) {
        if (node == null) {
            return;
        }
        List<Node> childNodes = node.getChildNodes();
        int value = node.getValue();
        //自己祖先默认是自己---这步在DFS递归时很重要
        ancestor[value] = value;
        if (childNodes != null) {
            for (Node n : childNodes) {
                //最左节点开始
                tarjan(n);
                value = node.getValue();
                if (n != null) {
                    //并
                    unionSet(n.value, value);
                }
            }
        }
        //设置当前被访问节点状态
        visited[node.value] = true;
        int[] query;
        for (int i = 0; i < querys.size(); i++) {
            query = querys.get(i);
            //遍历到查询节点
            if (query[0] == node.value) {
                //如果另一个查询节点已经被访问过
                if (visited[query[1]]) {
                    //因为仍在遍历中,那访问过的那个节点的祖先节点就是要找的LCA
                    queryResult[query[0]][query[1]] = findSet(query[1]);
                }
            } else if (query[1] == node.value) {
                if (visited[0]) {
                    queryResult[query[0]][query[1]] = findSet(query[0]);
                }
            }
        }
    }


    /**
     * 欧拉序遍历:TODO
     * @param node
     */
    void dfsOLA(Node node){
       /* Stack<Node> stack = new Stack<>();
        Node temp = node;
        stack.add(temp);
        while (!stack.isEmpty()){
            temp = stack.pop();
            List<Node> childNodes = temp.getChildNodes();
            if (childNodes!=null && !childNodes.isEmpty()){
                for (Node child:childNodes){
                    stack.push(child);
                }
            }
        }*/
    }

    /**
     * 并查集---查操作
     *
     * @param x
     * @return
     */
    int findSet(int x) {
        if (x != ancestor[x]) {
            ancestor[x] = findSet(ancestor[x]);
        }
        return ancestor[x];
    }

    /**
     * 并查集---并操作
     *
     * @param x
     * @param y
     */
    void unionSet(int x, int y) {
        int fx = findSet(x);
        int fy = findSet(y);

        if (fx != fy) {
            ancestor[fx] = fy;
        }
    }



}

//树节点
class Node {
    int value; //节点值
    List<Node> childNodes; //子节点

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Node> getChildNodes() {
        return childNodes;
    }

    public void setChildNodes(List<Node> childNodes) {
        this.childNodes = childNodes;
    }

    public Node(int value) {
        this.value = value;
    }

    //这里就简单构建二叉树了
    static void createTree(Node root, List<Integer> data) {
        int dataIndex = 0;
        Node temp;
        List<Node> tempList;
        root.setValue(data.get(0));
        dataIndex++;
        Queue<Node> nodeQ = new LinkedBlockingQueue();
        nodeQ.add(root);
        while (!nodeQ.isEmpty()) {
            temp = nodeQ.poll();
            tempList = new ArrayList<>();
            if (dataIndex <= data.size()) {
                Node child1 = new Node(dataIndex);
                dataIndex++;
                nodeQ.add(child1);
            } else {
                temp.setChildNodes(tempList);
                break;
            }
            if (dataIndex <= data.size()) {
                Node child2 = new Node(dataIndex);
                dataIndex++;
                nodeQ.add(child2);
            } else {
                temp.setChildNodes(tempList);
                break;
            }
            temp.setChildNodes(tempList);
        }
    }

}