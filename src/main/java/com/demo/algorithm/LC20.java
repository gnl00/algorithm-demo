package com.demo.algorithm;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']'的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 *
 * 输入：s = "()"
 * 输出：true
 *
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 输入：s = "(]"
 * 输出：false
 *
 * 输入：s = "([)]"
 * 输出：false
 *
 * 输入：s = "{[]}"
 * 输出：true
 *
 * @author lgn
 * @since 2022/1/19 15:04
 */

public class LC20 {
    public static void main(String[] args) {
        LC20 lc20 = new LC20();
        System.out.println(lc20.isValid3("()[]{}"));
        System.out.println(lc20.isValid3("{[()]}"));
    }

    public boolean isValid3(String s) {
        LinkedList<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (!stack.isEmpty() && c != stack.pop()) {
                /**
                 * 遇到右括号的情况下，与栈顶元素比较，相同则将栈顶弹出
                 */
                return false;
            }
        }
        return stack.size() == 0;
    }

    public boolean isValid2(String s) {
        int len = s.length() / 2;
        for (int i = 0; i < len; i++) {
            s = s.replace("()", "").replace("[]", "").replace("{}", "");
        }
        return s.length() == 0;
    }

    public boolean isValid(String s) {

        /**
         * 使用栈
         * 遇到左括号不用管，直接压栈，只需要关注右括号
         * 如果当前是右括号，那就和前一个括号进行匹配，匹配成功，将前一个括号从栈中弹出。
         * 继续下一对括号的匹配。匹配不成功直接 返回false
         * 等到循环完毕，并且栈为空的情况下返回true
         */

        Map<Character, Character> map = new HashMap<>();
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');

        String rightBracket = ")]}";

        LinkedList<Character> stack = new LinkedList<>();

        for (char c : s.toCharArray()) {
            if (rightBracket.indexOf(c) != -1 && stack.peek() == map.get(c)) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }

        return stack.size() == 0;
    }
}
