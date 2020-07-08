package algorithm;

/**
 * RMQ(Range Minimum/Maximum Query)
 * 区间最值查询问题
 * <p>
 * 这里采用ST算法 sparse table ; 本质是DP
 * O(nlogn)的预处理,O(1)的查询
 */
public class RMQ {
    int[] nums; //数序列
    int[][] dp; //st,以max值为例

    public static void main(String[] args) {
        int[] arr = {8, 8, 4, 5, 3, 6, 5, 4, 7, 1, 5, 8, 2, 3, 4, 7, 5, 4, 5, 8, 4, 1, 3, 6, 9, 8};
        RMQ rmq = new RMQ(arr);
        System.out.println(rmq.query(6, 10));
    }

    /**
     * 构造
     *
     * @param nums
     */
    RMQ(int[] nums) {
        this.nums = nums;
        int len = nums.length;
        this.dp = new int[len][(int) (Math.log(len + 1) / Math.log(2)) + 1];
        initRMQ();
    }

    /**
     * 预运算
     */
    void initRMQ() {
        int len = nums.length;
        //动态规划初始值
        for (int i = 0; i < len; i++) {
            dp[i][0] = nums[i];
        }
        //内外循环,一定不能调换,否则必错,一定从内层开始,内层是dp的初始状态,max(dp[0,0] dp[1,0]) = dp[1,1]
        for (int j = 1; 1 << j <= len; j++) {
            for (int i = 0; (1 << j) + i < len; i++) {  //注意位运算和+-的优先级
                dp[i][j] = Math.max(dp[i][j - 1], dp[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    /**
     * 查询
     *
     * @param f from
     * @param t to
     * @return
     */
    int query(int f, int t) {
        //math.log是以e为底的
        int k = (int) (Math.log(t - f + 1) / Math.log(2));
        return Math.max(dp[f][k], dp[t - (1 << k) + 1][k]);
    }
}
