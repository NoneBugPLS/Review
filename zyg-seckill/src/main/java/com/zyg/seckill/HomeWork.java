package com.zyg.seckill;

import java.util.Scanner;
import java.util.Stack;

/**
 * 类名：
 * 作者：Lun
 * 功能：
 * 时间：18:27 2022/6/27
 */
public class HomeWork {
    public static void main(String[] args) {
        //准备一个栈存放
        Stack stack = new Stack();
        //提示用户输入：
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入：");

        String string = scanner.nextLine();

        String [] sArr = string.split(" ");
        for (String s : sArr) {
            stack.push(s);
        }
        for (int i = 0; i <sArr.length ; i++) {
            System.out.print(stack.pop() + " ");
        }
    }
}
