package com.amitinside.problem.util;

import com.google.common.collect.Sets;
import com.google.common.primitives.Chars;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author AMIT
 */
public class func {
    
    String formatted = null;
    
    public func(String str){
          char[] charArray = str.toCharArray();
        Set charSet;
        String finalStr = "";
        charSet = Sets.newHashSet();
        for (char c : charArray) {
            charSet.add(c);
        }
        Iterator iterator = charSet.iterator();
        while (iterator.hasNext()) {
            finalStr += iterator.next();
        }
        this.formatted = finalStr;
    }

    public String geLexicographicallyBigger(String str) {
        char[] cs = str.toCharArray();
        char lastChar = 0;
        char nextChar;
        int lastCharValue;
        int nextCharValue;
        String finalStr = "";
        for (char c : cs) {
            lastChar = c;
        }

        for (int i = 0; i <= cs.length - 2; i++) {
            finalStr += cs[i];
        }
        if (lastChar == 'z') {
            return finalStr + "aa";
        }

        nextCharValue = Character.valueOf(lastChar) + 1;
        lastChar = (char) nextCharValue;
        String nextCharStr = Character.toString(lastChar);
        return finalStr + nextCharStr;

    }

    public char getNextMissingCharacter() {
        char[] charArray = this.formatted.toCharArray();

        char nextChar = 0;
        int nextCharValue;
        for (char c : charArray) {
            nextCharValue = Character.valueOf(c) + 1;
            nextChar = (char) nextCharValue;
            if (!Chars.contains(charArray, nextChar)) {
                break;
            }
        }
        return nextChar;
    }

    public boolean checkSubstringPresence(String subStr, String str) {
        boolean flag = false;
        if (str.indexOf(subStr) != -1) {
            flag = true;
        }
        return flag;
    }

    public String recursiveSearch(String sequence, String str) {
        if (this.checkSubstringPresence(sequence, str)) {
            return recursiveSearch(this.geLexicographicallyBigger(sequence), str);
        } else {
            return sequence;
        }
    }
}
