import java.util.ArrayList;
import java.util.List;


public class Solution {

    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        int len = s.length();
        if (len == 0) {
            return res;
        }
        dfs(s, len, 0, 0, "", res);
        return res;
    }

    /**
     * 判断 ipSegment 这个字符串是否是一个合法的 ip 段
     */
    private boolean judgeIfIpSegment(String ipSegment) {
        int len = ipSegment.length();
        // 大于 1 位的时候，不能以 0 开头
        if (len > 1 && ipSegment.startsWith("0")) {
            return false;
        }
        return Integer.parseInt(ipSegment) <= 255;
    }

    private void dfs(String s, int len, int split, int begin, String pre, List<String> res) {
        if (split == 4) {
            // 分割最多 4 次
            if (begin == len) {
                res.add(pre.substring(0, pre.length() - 1));
            }
            return;
        }

        for (int i = 1; i <= 3; i++) {
            // split < 4 的时候，begin + i <= s.length() 容易被忽略
            if (begin + i > len) {
                break;
            }
            // 可能成为 ip 段的字符串，截取的时候 begin + i 不包括在内
            String ifIpSegment = s.substring(begin, begin + i);
            if (judgeIfIpSegment(ifIpSegment)) {
                dfs(s, len, split + 1, begin + i, pre + ifIpSegment + '.', res);
            }
        }

    }


    public static void main(String[] args) {
        String s = "25525511135";
        Solution solution = new Solution();
        List<String> restoreIpAddresses = solution.restoreIpAddresses(s);
        System.out.println(restoreIpAddresses);
    }
}
