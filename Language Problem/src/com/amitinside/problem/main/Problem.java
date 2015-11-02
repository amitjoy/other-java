/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.amitinside.problem.main;

import com.amitinside.problem.util.func;
import com.google.common.base.CharMatcher;
import com.google.common.collect.Lists;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author AMIT
 */
public class Problem {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<String> list = Lists.newArrayList();
        int no = 0;
        System.out.println("Please enter the no of strings: \n");
        try {
            no = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Please do input a no");
        }
        System.out.println("Please do enter strings in each line");
        for (int i = 0; i <= no; i++) {
            String in = scanner.nextLine();
            list.add(in);
        }

        Iterator<String> iterator = list.iterator();
        String formedOutput = null;
        while (iterator.hasNext()) {
            formedOutput += iterator.next();
        }
        func input = new func(formedOutput);
        if (!CharMatcher.JAVA_LETTER.matches(input.getNextMissingCharacter())) {
            System.out.println("Answer: " + input.recursiveSearch("aa", formedOutput));
        } else {
            System.out.println("Answer: " + input.getNextMissingCharacter());
        }
    }
}
