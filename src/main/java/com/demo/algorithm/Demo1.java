package com.demo.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 获取1000之内因数之和等于自身的数
 *
 * 例如
 * 6
 * 1 2 3
 *
 * @author lgn
 * @since 2021/12/16 10:18
 */

public class Demo1 {
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int sum = 0;
            List<Integer> list = getFac(i);

            for(Integer n: list) {
                sum += n;
            }
            if (sum == i) {
                System.out.println(list);
                System.out.println(i);
            }

        }
    }

    public static List<Integer> getFac(int n) {

        List<Integer> list = new ArrayList<>();
        if (n == 0) {
            list.add(0);
        } else {
            list.add(1);
        }

        if (n > 1)  {
            for (int i = 2; i < n; i++) {
                if ( ! list.contains(i) && n % i == 0 ) {
                    list.add(i);
                    list.add(n / i);
                }
            }
        }
        return list;
    }

}
