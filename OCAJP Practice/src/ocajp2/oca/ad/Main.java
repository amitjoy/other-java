/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ocajp2.oca.ad;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import ocajp2.oca.*;

/**
 *
 * @author SESA249907
 */
public class Main {

    protected void finalize() {
    }

    public static void main(String[] argv) throws ParseException {
        System.out.println("****************************");
        double d2 = 2;
        Double c = 12.0;
        Double k2 = 3.4;
        if (c == k2) {
        }
        byte bk = 127;
        bk += 1;
        int _52 = 52;
        int x1 = _52;
        System.out.println(bk);
        System.out.println(c.compareTo(2.0));
        System.out.println(Integer.decode("1"));
//        short s = c;
        Animal a = new Animal();
        Dog d = new Dog();
        d.getName();
        Animal b = new Dog();
        Object o = new Dog();
        Object o1 = new Dog();
        Animal am = new Dog();
        Cat cat = new Cat();
        Cat ct = new Cat();
//        d = (Dog)a;
//        System.out.println(a.a);
      //  Dog ak = (Dog) (Fauna) (Cat) (Animal) o1;
        Fauna f = (Fauna) o;
        Fauna f2 = (Cat)(Animal) (Dog) am;
        ct = (Cat)(Animal) (Fauna) f2;
//        d = (Fauna)(Dog)(Animal)o;
        System.out.println(d);
//        System.out.println(cat instanceof Animal);

        /**
         * STRING PRACTICE
         */
        System.out.println("----------------------------------------");
        String str = "AMITA";
        StringBuffer sb = new StringBuffer("AMIT");
        String str1 = new String(str);
        String str2 = new String(sb);
        char[] carr = {'A', 'M', 'I', 'T'};
        String str3 = new String(carr);
        byte[] barr = {97, 98, 99, 100, 97};
        String str4 = new String(barr);
        String str5 = "amit";
        System.out.println("1: " + str1);
        System.out.println("2: " + str2);
        System.out.println("3: " + str3);
        System.out.println("4: " + str4);
        System.out.println(str.charAt(0));
        Byte bh = 67;
        Character ch = 6;
        Short sh = 4;
//        if(bh == ch){
//            
//        }
//        Long l = 34;
        //All integral wrapper classes can have literal integer value so no compiler error
        double dh = 67;
//        System.oulet.println(str.charAt('a'));
        System.out.println(str.compareTo(str4));
        System.out.println(str.compareToIgnoreCase(str4));
        System.out.println(str.contentEquals(sb));
        System.out.println(str.contentEquals("AMIT"));
        System.out.println(str.contentEquals(new String("AMIT")));
        System.out.println(str.endsWith(str));
        System.out.println(str.equalsIgnoreCase(str5));
        System.out.println(str.indexOf("IT"));
        System.out.println(str.indexOf('M'));
        System.out.println(str4.indexOf(99));
        System.out.println("AMITAAMITA" + "AMITAAMITA".indexOf("A", 6));
        System.out.println("AMITAAMITA-------" + "AMITAAMITA".lastIndexOf("A", 6));
        System.out.println(str.lastIndexOf('M', 0));
        System.out.println(str.replace("A", "B"));
        System.out.println(str.replace('A', 'B'));
        System.out.println(str.startsWith("AM"));
        System.out.println(str.startsWith("I", 2));
        System.out.println(String.valueOf(false));
        //substring, toUpperCase, toLowerCase, length
        System.out.println("--------------------------");

        /**
         * STRINGBUILDER PRACTICE
         */
        StringBuilder sb2 = new StringBuilder("AMIT");
        String String = new String("MONDAL");
        boolean bool = false;
        StringBuilder sb3 = new StringBuilder(100);
        StringBuilder sb4 = new StringBuilder();
        StringBuilder sb5 = new StringBuilder(String);
        StringBuilder sb6 = new StringBuilder(sb5);
        System.out.println(sb6.append(bool)); //param: Object/CharSequence
        System.out.println(sb5.capacity());
        System.out.println("*************" + sb5.append(new Main()));
        System.out.println(sb5.charAt(2));
        System.out.println(sb5.delete(1, 3));
        System.out.println(sb5.deleteCharAt(2));
        System.out.println(sb6.indexOf("f")); //only string param
        System.out.println(sb6.indexOf("f", 4));
        System.out.println(sb6.lastIndexOf("f", 4));
        sb6.ensureCapacity(2888);
        System.out.println(sb6.capacity());
        System.out.println(sb6.insert(0, "ABN")); //2nd param any object or char[]
        System.out.println(sb6.replace(0, 2, "ANIT"));
        System.out.println(sb6.reverse());
        sb6.setCharAt(0, 'Z');
        System.out.println(sb6);
        //length,
        System.out.println(sb6.subSequence(0, 3));
        System.out.println(sb6.substring(0, 2));
        System.out.println(sb6.substring(0));
        sb6.setCharAt(0, ' ');
        sb6.trimToSize();
        System.out.println(sb6);

        //Check for void methods in String and StringBuilder

        /**
         * ARRAYS PRACTICE
         */
        System.out.println("*********************************************");
        int[] array = {34, 76, 23, 98, 7};
        int[] array5 = {34, 76, 23, 98, 7};
        System.out.println(array.equals(array5));
        System.out.println(Arrays.equals(array, array5));
        Arrays.sort(array, 1, 3); //any array
        System.out.println(Arrays.toString(array));
        Arrays.sort(array);
        System.out.println(Arrays.toString(array));
        System.out.println(Arrays.binarySearch(array, 7)); //any array except object[]
//        System.out.println(Arrays.binarySearch(array, 89));
        System.out.println(Arrays.binarySearch(array, 9));
        int[] array2 = Arrays.copyOf(array, 10);
        int[] array3 = Arrays.copyOfRange(array, 2, 4);
        System.out.println(Arrays.toString(array2));
        System.out.println(Arrays.toString(array3));
//        System.out.println(Arrays.fill(array, new Main()));
        //deepEquals, deepHashCode, fill, asList, deepToString

        /**
         * Iterators
         */
        List li = new ArrayList<>();
        li.add(23);
        li.add(34);
        li.add(3);
        li.add(89);
        li.add("HYU");
        li.add("UTR");
        Iterator it = li.iterator();
        ListIterator it2 = li.listIterator();
        while (it.hasNext()) {
            System.out.println(it.next());
//            if((it.next()).equals(34))
//                it.remove();
        }
        it.remove();
        System.out.println(li);
        while (it2.hasPrevious()) {
            System.out.println("^ " + it2.next());
        }
        ListIterator litr = li.listIterator();
        while (litr.hasPrevious()) {
            Object element = litr.previous();
            System.out.println("& " + element);
        }

        //Tutorialspoint ListIterator
        ArrayList al = new ArrayList();
        // add elements to the array list
        al.add("C");
        al.add("A");
        al.add("E");
        al.add("B");
        al.add("D");
        al.add("F");

        // Use iterator to display contents of al
        System.out.print("Original contents of al: ");
        Iterator itr = al.iterator();
        while (itr.hasNext()) {
            Object element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // Modify objects being iterated
        while (litr.hasNext()) {
            Object element = litr.next();
            litr.set(element + "+");
        }
        System.out.print("Modified contents of al: ");
        itr = al.iterator();
        while (itr.hasNext()) {
            Object element = itr.next();
            System.out.print(element + " ");
        }
        System.out.println();

        // Now, display the list backwards
        System.out.print("Modified list backwards: ");
        while (litr.hasPrevious()) {
//         Object element = litr.previous();
//         litr.add("HINDU");
            System.out.print(litr.previous() + " ");
        }
        System.out.println();
        while (litr.hasNext()) {
//         Object element = litr.previous();
            litr.add("HINDU");
            System.out.print(litr.next() + " ");
        }

        /**
         * DATE PRACTICE
         */
        System.out.println("DATE~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Date date = new Date();
//       Date date2 = new Date(1234567891010);
        Date date3 = new Date(1234567891010L);
        System.out.println(date.toString());
        System.out.println(date3);
        String string = "h";
        System.out.println(date3.equals(string));
        System.out.println(date3.after(date));
        System.out.println(date3.before(date));
        System.out.println(date3.compareTo(date));
        System.out.println(date3.getTime());
        date3.setTime(45L);
        System.out.println(date3.getTime());
        System.out.println("Date----" + date3);
//        System.out.println(date3.setTime(45L));

        //If constructors are passed simple numbers, it will treat it as integer

        /**
         * DATEFORMAT PRACTICE
         */
        Date dateToday = new Date(0);
        String pattern = "dd-MM-YYYY";
        Calendar calendar = Calendar.getInstance();
        DateFormat dateFormat = new SimpleDateFormat(pattern);
        DateFormat dateFormat2 = new SimpleDateFormat();
        System.out.println(dateFormat.format(date));
        System.out.println("Default Pattern(M/d/YY h:mm a): " + dateFormat2.format(date));
        System.out.format("%tD", calendar);
        DateFormat dateFormat1 = dateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.MEDIUM);
        System.out.println(dateFormat1.format(dateToday));
        SimpleDateFormat dateFormat3 = (SimpleDateFormat) dateFormat.getInstance();
        String dateToParse = "2013-05-13";
        SimpleDateFormat dateFormat4 = new SimpleDateFormat("YYYY-MM-dd");
        Date date1 = dateFormat4.parse(dateToParse); // throws checked exception
        System.out.println("@@" + date1);
        int year;
        Calendar gcalendar = Calendar.getInstance();
        GregorianCalendar gcalendar2 = new GregorianCalendar();
        Calendar cal1 = new GregorianCalendar(2008, 01, 01);
        System.out.print(" " + gcalendar.get(Calendar.DATE) + " ");
//        Calendar.DATE = 23;
        cal1.set(Calendar.MARCH, 23);
        System.out.println("^^^^^^^^^^" + cal1.get(Calendar.MARCH));
        System.out.println(year = gcalendar.get(Calendar.YEAR));
        System.out.print("Time: ");
        System.out.print(gcalendar.get(Calendar.HOUR) + ":");
        System.out.print(gcalendar.get(Calendar.MINUTE) + ":");
        System.out.println(gcalendar.get(Calendar.SECOND));

        // Test if the current year is a leap year
        if (gcalendar2.isLeapYear(year)) { //method not present in Calendar
            System.out.println("The current year is a leap year");
        } else {
            System.out.println("The current year is not a leap year");
        }
        System.out.println(gcalendar.getTime());
        gcalendar.setTime(new Date(425135L));
        //ALL setters are void. always rememeber not to put setters in sout
        gcalendar.set(Calendar.MONTH, 1);
        System.out.println("Distorted " + gcalendar.getTime());

        /**
         * WRAPPERS
         */
        Character charac = 'C';
        Character character = new Character(charac);
        Character character2 = new Character((char) 97); // default no means integer
//        Short sh = new Short(90);
        System.out.println(character2);
        System.out.println(Character.valueOf('c').charValue());
        int charact = Character.valueOf('c').charValue();
        charact = character.charValue();
        System.out.println(character.compareTo(character2));
        //toUpperCase, toLowercase, compareTo, isLetter. isDigit, isWhiteSpace, isUpperCase, isLowerCase, 
        Float fl = new Float(3);
        Float fl2 = new Float(3.0);
        Float fl3 = new Float(3.0f);
        Float fl4 = new Float("3.7");
        System.out.println(fl4);
        Double dl = new Double(5.9f);
        Double dl2 = new Double(5.2);
        Long ln1 = new Long(45L);
        Long ln2 = new Long(8l);
        System.out.println(dl.compareTo(dl2));
        System.out.println(ln1.compareTo(ln2));
        Byte b3 = new Byte((byte) 20);

        Double dk = 4.2;
        Float fk = 4F;
        /**
         * List
         */
        System.out.println("::::::::::::::::::::::::::::::::::::::::::::::");
        List<String> list = new ArrayList<String>();
        List list2 = new ArrayList<String>(100);
        List list3 = new ArrayList<String>(list2);
        list.add("AMIT");
        list.add("ANIT");
        list.add("MONDAL");
        list.add("ARE");
        list.add("BROTHERS");
        list.add(2, "KUMAR");
        System.out.println(list.size());
        System.out.println(list.indexOf(23));//param only object
        System.out.println(list.indexOf("ARE"));//param only object
        System.out.println(list.contains("ARE"));//param only object
        System.out.println(list.remove(new Integer(23)));//param index or Object
        System.out.println(list);
        System.out.println(list.set(2, "ARE"));
        System.out.println(list2.addAll(list));
        list.clear();
        System.out.println(list2);
        System.out.println("TTTTTTTTTTTTTTTTTTTTTTTTT" + list2.subList(1, 3));
        System.out.println(list2.toString());
        //toArray,trimToSize, addAll(int index, collection), get, isEmpty
        Stack stack = new Stack<Integer>();
        stack.push("ADf");
        stack.push(35);
        stack.push(3);
        stack.push(14);
        stack.add(56);
        System.out.println(stack);
        //empty (and isEmpty),pop

        /**
         * SET
         */
        Set set = new HashSet<>();
        HashSet hashSet = new HashSet(list2);
        Set set2 = new HashSet(100);
        //no indexOf, get
        //no object retrieval from HashSet
        //The rest is same as List
        System.out.println(hashSet);

        List listToSet = new ArrayList() {
            {
                add(23);
                add(34);
                add(44);
                add(24);
                add(23);
                add(33);
                add(7);
                add(9);
                add(7);

            }
        };
        Set s = new TreeSet();
        Set s1 = new TreeSet(list);
        Set s2 = new TreeSet();
        TreeSet set1 = new TreeSet(listToSet);
        System.out.println("SET1 ::::::::::::" + set1);
        //no initial capacity
        System.out.println(set1.first());
        System.out.println(set1.last());
//        System.out.println(set1.contains("KUMAR")); //object only
//        System.out.println(set1.remove("MONDAL"));
//        System.out.println(set1.subSet("AMIT", "BROTHERS")); //objects param
//        System.out.println(set1.subSet(0, 4));
        set1.clear();

        /**
         * Collections
         */
        List lis = new ArrayList();
        lis.add(20);
        lis.add(12);
        lis.add(54);
        lis.add(23);
        lis.add(7);
        lis.add("AMIT");
        lis.add(new Byte((byte) 10));
        lis.add(null);
//        Collections.sort(lis);
//        System.out.println(lis);
//        System.out.println(Collections.binarySearch(lis, 20));
//        System.out.println(Collections.binarySearch(lis, 50));
        List list1 = new ArrayList(20);
        List<Integer> list21 = new ArrayList(20);
        list1.add("MONDAL");
        list1.add("AMIT");
        list1.add("KUMAR");

//        Collections.copy(list1, lis);
        System.out.println(Collections.max(list1));
        Collections.reverse(list1);//returns void
        System.out.println(Collections.frequency(list1, "AMIT"));
        //min, max
        Collections.fill(list1, 'K'); //void
        System.out.println(list1);
        System.out.println("AMIT".contains("MI")); //Only CharSequence
        String str90 = "MIN";
        System.out.println(str90 = str90.concat("AMIT"));
        System.out.println(-0.0 == 0.0);
        long[] arr = {};
        int[] arr1 = {};
//        arr = arr1;
        System.out.println("AMITIAMA".lastIndexOf("A", 4));
    }
}