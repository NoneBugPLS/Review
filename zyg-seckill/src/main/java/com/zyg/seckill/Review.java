package com.zyg.seckill;

import java.util.Scanner;
import java.util.Stack;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：18:40 2022/6/27
 */
public class Review {
    public static void main(String[] args) {
        //准备个栈用来反转
        Stack stack = new Stack();

        //提示用户输入：
        System.out.println("请输入字符串：");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {

            String str = scanner.nextLine();

            String[] strArr = str.split(" ");
            for (String s : strArr) {
                stack.push(s);
            }
            for (int i = 0; i <strArr.length ; i++) {
                System.out.print(stack.pop() + " ");
            }

        }
    }
}
