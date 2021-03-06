import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution2 {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        // 特判
        if (k <= 0 && k > n) {
            return result;
        }

        Deque<Integer> path = new ArrayDeque<>(k);
        // 从 1 开始
        dfs(n, k, 1, path);
        return result;
    }

    /**
     * 从 [1,n] 中选出 k 个数，
     *
     * @param n     从 [1,n] 中选
     * @param k     选出的数字的个数
     * @param start 当前被选中的起始数字
     * @param path   已经构成的数字列表
     */
    private void dfs(int n, int k, int start, Deque<Integer> path) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.addLast(i);
            dfs(n, k, i + 1, path);
            path.removeLast();
        }
    }

    public static void main(String[] args) {
        Solution2 solution2 = new Solution2();
        List<List<Integer>> combine = solution2.combine(4, 2);
        System.out.println(combine);
    }
}