package com.amitinside.google.codejam.main;

import com.amitinside.google.codejam.util.Functions;
import com.google.common.collect.Multimap;

/**
 * @author AMIT
 */
public class DancingWithGooglers {

    public static void main(String[] args) {
        Functions func = new Functions();
        Multimap<Integer, String> map;
        func.formTriplets(11);
        map = func.getMap();
        System.out.println(map.get(11));
        func.createCombination("012", 3, new StringBuffer());
        System.out.println(func.getNumList());
        func.formCombinations(555);
    }
}
