package com.wwd.leetcode.demo.chap_01;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;

/**
 * @ClassName LeetCodeOne
 * @Description: TODO
 * @Author wang.wending
 * @Date 2019/10/8 15:37
 * @Version 1.0.0
 **/
public class LeetCodeDemo {
    // 求两个数之和
    @Test
    public void twoNumSum() {
        int[] arr = {2, 7, 1, 8, 3};
        int target = 9;
        twoNumSum(target, arr);
    }

    private void twoNumSum(int target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            map.put(i, arr[i]);
        }
        for (int num : map.values()) {
            if (map.containsValue(target - num)) {
                System.out.println(num + ", " + (target - num));
                break;
            }
        }
    }


    //给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字
    // 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
    // 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    //输出：7 -> 0 -> 8
    //原因：342 + 465 = 807
    @Test
    public void addTwoNodeNum() {
        Long begin = System.currentTimeMillis();
        System.out.println(begin);
        LeetCodeDemo node = new LeetCodeDemo();
        node.put(2);
        node.put(4);
        node.put(3);
        LeetCodeDemo node2 = new LeetCodeDemo();
        node2.put(5);
        node2.put(6);
        node2.put(4);
        addTwoNodeNum(node, node2);
        Long end = System.currentTimeMillis();
        System.out.println(end);
        System.out.println("耗时:" + (end - begin));

    }

    private void addTwoNodeNum(LeetCodeDemo node, LeetCodeDemo node2) {
        LeetCodeDemo leetCodeDemo = new LeetCodeDemo();
        Map<String, Integer> result = new HashMap<>(16);
        int a, b = 0;
        int resultNum = 0;
        if (node != null && node2 != null) {
            result = node.outResult();
            a = transferNum(result);
            result = node2.outResult();
            b = transferNum(result);
            resultNum = a + b;
        }
        if (resultNum > 0) {
            int x, y, z;
            x = resultNum / 100;
            y = (resultNum - x * 100) / 10;
            z = (resultNum - x * 100 - y * 10) / 1;
            leetCodeDemo.put(x);
            leetCodeDemo.put(y);
            leetCodeDemo.put(z);
        }
        System.out.println(leetCodeDemo.outResult());
    }

    private int transferNum(Map<String, Integer> result) {
        int a = 0;
        int i = 0;
        if (result != null && result.size() > 0) {
            for (Map.Entry<String, Integer> entry : result.entrySet()) {
                i++;
                if (i == 1) {
                    a += entry.getValue() * 100;
                }
                if (i == 2) {
                    a += entry.getValue() * 10;
                }
                if (i == 3) {
                    a += entry.getValue();
                }
            }
        }

        return a;
    }

    private transient ListNode head;
    private transient int size;

    void put(int e) {
        if (head == null) {
            head = new ListNode(e);
        } else {
            ListNode node = new ListNode(e);
            node.next = head;
            head = node;
        }
        size++;
    }

    Map<String, Integer> outResult() {
        Map<String, Integer> result = new HashMap<>(16);
        ListNode node = head;
        int i = 0;
        int value = 0;
        while (node != null) {
            i++;
            value = node.val;
            result.put(String.valueOf(i), value);
            node = node.next;
        }
        return result;
    }

    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        int getSize() {
            return size;
        }
    }


    // 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
    // 输入: "abcabcbb"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
    // 输入: "bbbbb"
    //输出: 1
    //解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
    // 输入: "pwwkew"
    //输出: 3
    //解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
    //     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。

    @Test
    public void norepetitionSubStrLength() {
        String a = "dvdf";
        String b = "bbbbb";
        String c = " ";

        int maxLength = 0;
//        maxLength = norepetitionSubStrLength(a);
        maxLength = norepetitionSubStrLengthTwo(a);

    }

    private int norepetitionSubStrLengthTwo(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {
                i = Math.max(map.get(s.charAt(j)), i);
            }
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        System.out.println(ans);
        return ans;

    }

    private int norepetitionSubStrLength(String s) {
        int length = 0;
        int flag = 0;
        int temp = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap<>(16);
        if (s != null) {
            flag = s.length();
            for (;;) {
                for (int i = j; i < flag; i++) {
                    if (map.containsKey(s.charAt(i))) {
                        temp = swapMax(length, temp);
                        length = temp;
                        length = 0;
                    }
                    map.put(s.charAt(i), i);
                    length++;
                    temp = swapMax(length, temp);
                }
                j++;
                map.clear();
                length = 0;
                if (j == flag) {
                    break;
                }
            }

        }
        length = swapMax(length, temp);
        System.out.println(length);

        return length;
    }

    private int swapMax(int length, int temp) {
        int num = 0;
        if (length >= 0 && temp >= 0) {
            if (length > temp) {
                num = length;
            } else {
                num = temp;
            }
        }
        return num;
    }


    // 给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。
    //
    //请你找出这两个有序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
    //
    //你可以假设 nums1 和 nums2 不会同时为空。
    //
    //示例 1:
    //
    //nums1 = [1, 3]
    //nums2 = [2]
    //
    //则中位数是 2.0
    //示例 2:
    //
    //nums1 = [1, 2]
    //nums2 = [3, 4]
    //
    //则中位数是 (2 + 3)/2 = 2.5
    @Test
    public void findMedianSortedArrays() {
        int[] nums1 = {1,2};
        int[] nums2 = {3,4};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double mdedianNum = 0.0;
        int[] newNums = new int[nums1.length + nums2.length];
        int length = newNums.length;
        Map<Integer, Integer> map = new HashMap<>(16);
        int i = 0;
        for (int a : nums1) {
            i++;
            map.put(i, a);
        }
        for (int b : nums2) {
            i++;
            map.put(i, b);
        }
        for (int j = 0; j < newNums.length; j++) {
            newNums[j] = map.get(j+1);
        }
        Arrays.sort(newNums);
        if (length % 2 == 0) {
            mdedianNum = (newNums[length/2 - 1] + newNums[length/2]) / 2.0;
        } else {
            mdedianNum = newNums[length/2];
        }

        return mdedianNum;
    }

    // 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
    //
    //示例 1：
    //
    //输入: "babad"
    //输出: "bab"
    //注意: "aba" 也是一个有效答案。
    //示例 2：
    //
    //输入: "cbbd"
    //输出: "bb"
    //

    @Test
    public void longestPalindrome() {
        String s = "\"gphyvqruxjmwhonjjrgumxjhfyupajxbjgthzdvrdqmdouuukeaxhjumkmmhdglqrrohydrmbvtuwstgkobyzjjtdtjroqpyusfsbjlusekghtfbdctvgmqzeybnwzlhdnhwzptgkzmujfldoiejmvxnorvbiubfflygrkedyirienybosqzrkbpcfidvkkafftgzwrcitqizelhfsruwmtrgaocjcyxdkovtdennrkmxwpdsxpxuarhgusizmwakrmhdwcgvfljhzcskclgrvvbrkesojyhofwqiwhiupujmkcvlywjtmbncurxxmpdskupyvvweuhbsnanzfioirecfxvmgcpwrpmbhmkdtckhvbxnsbcifhqwjjczfokovpqyjmbywtpaqcfjowxnmtirdsfeujyogbzjnjcmqyzciwjqxxgrxblvqbutqittroqadqlsdzihngpfpjovbkpeveidjpfjktavvwurqrgqdomiibfgqxwybcyovysydxyyymmiuwovnevzsjisdwgkcbsookbarezbhnwyqthcvzyodbcwjptvigcphawzxouixhbpezzirbhvomqhxkfdbokblqmrhhioyqubpyqhjrnwhjxsrodtblqxkhezubprqftrqcyrzwywqrgockioqdmzuqjkpmsyohtlcnesbgzqhkalwixfcgyeqdzhnnlzawrdgskurcxfbekbspupbduxqxjeczpmdvssikbivjhinaopbabrmvscthvoqqbkgekcgyrelxkwoawpbrcbszelnxlyikbulgmlwyffurimlfxurjsbzgddxbgqpcdsuutfiivjbyqzhprdqhahpgenjkbiukurvdwapuewrbehczrtswubthodv\"";
        System.out.println(longestPalindrome(s));
    }

    public String longestPalindrome(String s) {
        String result = "";
        String temp = "";
        int length = s.length();
        int flag = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j <= length; j++) {
                temp = s.substring(i, j);
                if (isPalindrome(temp)) {
                    if (temp.length() > flag) {
                        result = s.substring(i, j);
                        flag = Math.max(temp.length(), flag);
                    }
                }
            }
        }
        return result;
    }

    // 判断是否是回文字符串
    private boolean isPalindrome(String result) {
        boolean flag = true;
        int length = result.length();
        for (int i = 0; i < length / 2; i++) {
            if (result.charAt(i) != result.charAt(length - 1 - i)) {
                flag = false;
            }
        }
        return flag;
    }

    // 将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。
    //
    //比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
    //
    //L   C   I   R
    //E T O E S I I G
    //E   D   H   N
    // 示例 1:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 3
    //输出: "LCIRETOESIIGEDHN"
    //示例 2:
    //
    //输入: s = "LEETCODEISHIRING", numRows = 4
    //输出: "LDREOEIIECIHNTSG"

    @Test
    public void convert() {
        String s = "A";
        System.out.println(convert(s,1));
    }

    public String convert(String s, int numRows) {
        int flag = 2 * numRows - 2;
        int length = s.length();
        int temp = 0;
        // 存放每行的字符
        List<StringBuilder> list = new ArrayList<>(numRows);
        if (flag > 0 && numRows > 1) {
            for (int j = 0; j < numRows; j++) {
                StringBuilder stringBuilder = new StringBuilder();
                list.add(stringBuilder);
            }
            for (int i = 0; i < length; i++) {
                temp = i % flag;
                if (temp >= numRows) {
                    temp = flag - temp;
                }
                StringBuilder stringBuilder = list.get(temp);
                stringBuilder.append(s.charAt(i));
            }
        } else {
            return s;
        }

        System.out.println(list);
        // 拼接字符串
        StringBuilder builder = new StringBuilder();
        for (StringBuilder string : list) {
            builder.append(string.toString());
        }

        return builder.toString();
    }

    @Test
    public void ttt() {
        System.out.println(6%6);
        System.out.println(5%6);
        System.out.println(7%6);
    }

    @Test
    public void reverse() {
        System.out.println(reverse(1534236469));
    }

    public int reverse(int x) {
        Long result = 0L;
        String string  = "";
        int length = 0;
        boolean flag = false;
        StringBuilder builder = new StringBuilder();
        if (x > Integer.MIN_VALUE && x < Integer.MAX_VALUE) {
            string  = String.valueOf(x);
            length = string.length();
            for (int i = length - 1; i >= 0; i--) {
                if (i == length  -1 && string.charAt(i) == '0') {
                    continue;
                }
                if ('-' == string.charAt(i)) {
                    flag = true;
                    continue;
                }
                builder.append(string.charAt(i));
            }
            if (builder.length() > 0 ) {
                result = Long.parseLong(builder.toString());
            }
            if (flag) {
                result = - result;
            }
            if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
                return 0;
            }
        }
        return result.intValue();
    }

    // 请你来实现一个 atoi 函数，使其能将字符串转换成整数。
    //
    //首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
    //
    //当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
    //
    //该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
    //
    //注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
    //
    //在任何情况下，若函数不能进行有效的转换时，请返回 0。

    @Test
    public void myAtoi() {
        System.out.println(myAtoi("+1 +42"));
    }

    public int myAtoi(String str) {
        Long result = 0L;
        StringBuilder numStr = new StringBuilder();
        boolean flag = false;
        boolean flag2 = false;
        int length = str.length();
        char c = 0;
        String temp = "";
        if (length > 0) {
            for (int i = 0; i < length; i++) {
                c = str.charAt(i);
                if (' ' == c) {
                    if (flag || flag2 || numStr.length() > 0) {
                        break;
                    }
                    continue;
                }
                if (('0' <= c && c <='9')) {
                    numStr.append(c);
                    continue;
                }
                if ('-' == c) {
                    if (flag || flag2 || numStr.length() > 0) {
                        break;
                    }
                    flag = true;
                    continue;
                }
                if ('+' == c) {
                    if (flag || flag2 || numStr.length() > 0) {
                        break;
                    }
                    flag2 = true;
                    continue;
                }
                else {
                    break;
                }
            }
        }

        if (flag && flag2) {
            return 0;
        }

        if (numStr.length() > 0) {
            int index = 0;
            temp = numStr.toString();
            length = temp.length();
            for (int j = 0; j < length; j++) {
                if ('0' == temp.charAt(j)) {
                    continue;
                } else {
                    index = j;
                    break;
                }
            }
            temp = temp.substring(index, length);
            if (temp.startsWith("0")) {
                return 0;
            }
            if (temp.length() > 11) {
                result = Long.MAX_VALUE;
            } else {
                result = Long.parseLong(numStr.toString());
            }
            if (flag) {
                result = - result;
            }
        }

        if (result > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (result < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }

        return result.intValue();
    }


    @Test
    public void dd() {
        System.out.println(Long.MAX_VALUE);
        System.out.println(Integer.MAX_VALUE);
    }

}
