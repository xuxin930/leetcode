package algorithm;

/**
 * RMQ(Range Minimum/Maximum Query)
 * 区间最值查询问题
 * <p>
 * 这里采用ST算法 sparse table ; 本质是DP
 * O(nlogn)的预处理,O(1)的查询
 *
 *  简单描述一下问题:对于长度为n的数列A，需要回答若干个查询（A，i，j）返回A中下标在i，j之间的最小/大值
 *
 *  ST算法的思路:
 *  用F[i,j]表示从第i个数起连续2^j个数中的最值--------DP的状态
 *  F[i,0]就等于A[i]--------DP的初始值
 *  确定状态转移方程:把F[i,j]分成两段,因为一定是偶数个,从i到2^(j-1)-1是一段,从i+2^(j-1)到2^(j -1)
 *  两段长度都是2^(j-1), F[i,j]就是这两段中各自最值的最值
 *  F[i,j] = max/min(F[i,j-1],F[i+ 2^(j-1),j-1])----------DP状态转移方程
 *
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
