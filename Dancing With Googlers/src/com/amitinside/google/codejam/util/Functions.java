package com.amitinside.google.codejam.util;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import java.util.List;

/**
 * @author AMIT
 */
public class Functions {

    private Multimap<Integer, String> map;
    private List<String> numList;

    public Functions() {
        map = HashMultimap.create();
        numList = Lists.newArrayList();
    }

    public void formTriplets(int no) {

        int[] arr = new int[3];
        int secondnum, thirdnum, firstnum, num;

        num = (checkDivisibility(no)) ? (no / 3) : findNearestInteger(no) / 3;

        for (int i = 0; i <= 4; i++) {
            firstnum = num - i;
            secondnum = num + (i + 1);
            thirdnum = num / 3 + (i + 2);
            this.map.put(no, Integer.toString(firstnum) + Integer.toString(secondnum) + Integer.toString(thirdnum));
        }
    }

    public Multimap<Integer, String> getMap() {
        return map;
    }

    public List<String> getNumList() {
        return numList;
    }

    public boolean checkDivisibility(int no) {
        boolean flag = false;
        if (no % 2 == 0) {
            flag = true;
        }
        return flag;
    }

    public int findNearestInteger(int no) {
        int nearestint = 0;
        if ((no + 1) / 3 == 0) {
            nearestint = no + 1;
        } else {
            nearestint = no - 1;
        }
        return nearestint;
    }

    public void createCombination(String input, int depth, StringBuffer output) {
        if (depth == 0) {
            if (output.toString() != null) {
                this.numList.add(output.toString());
            }
        } else {
            for (int i = 0; i < input.length(); i++) {
                output.append(input.charAt(i));
                createCombination(input, depth - 1, output);
                output.deleteCharAt(output.length() - 1);
            }
        }
    }

    public void formCombinations(int input) {

        for (int i = 0; i <= 2; i++) {
            int digit = getDigitNthPosition(input, i);
            System.out.print(digit);
            int num1 = 0, num2 = 0;
            for (int j = 0; j <= 2; j++) {
                num1 = digit + j;
            }
            System.out.print(num1);
            System.out.print(num2);
            System.out.println("");
        }
    }

    public int getTotal(int num) {
        int total = 0;
        while (num / 10 == 0) {
            num = num % 10;
            num = num / 10;
            total += num;
        }
        return total;
    }

    public int getDigitNthPosition(int num, int position) {
        String number = Integer.toString(num);
        char c = number.charAt(position);
        int digit = Integer.parseInt(Character.toString(c));
        return digit;
    }
}
