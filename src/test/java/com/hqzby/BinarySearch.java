package com.hqzby;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class BinarySearch {



    @Test
    public void test01(){
        int[] ints = new int[]{8,6,121,456,789,123,789,5454,64,56,89};
        //System.out.println(binarySearch(ints, 11));
        System.out.println("排序前：" + Arrays.toString(ints));
        Character str = findToo("abcdec");
        System.out.println("重复的字符是: " + str);

        sort(ints);

        System.out.println("排序后：" + Arrays.toString(ints));

        SingLeTon singLeTon = SingLeTon.getInstance();
        System.out.println("懒汉式的单例模式：" + singLeTon);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000000; i++) {
            service.execute(() -> {
                System.out.println(SingLeTon.getInstance());
            });
        }
        service.shutdown();
    }


    public int binarySearch(int[] ints, int val){
        int low = 0;
        int high = ints.length-1;
        while(low <= high){
            int mid = (low + high) >>> 1;
            int midVal = ints[mid];
            if(val < midVal) high = mid - 1;
            else if(val > midVal) low = mid + 1;
            else return mid;
        }
        return -(low + 1);
    }

    public Character findToo(String str){
        HashMap<Character, Integer> map = new HashMap<>();
        Character c = null;
        for (int i = 0; i < str.length(); i++) {
            c = str.charAt(i);
            if(map.containsKey(c)) return c;
            map.put(c, i);
        }
        return null;
    }

    public void sort(int[] ints){
        for (int i = 0; i < ints.length; i++) {
            for (int j = i + 1; j < ints.length; j++) {
                int temp = ints[i];
                if(temp > ints[j]){
                    ints[i] = ints[j];
                    ints[j] = temp;
                }
            }
        }
    }

    @Test
    public void test02(){
        String str = "aaacccdddfffc";
        HashMap<Character, Integer> map = new HashMap<>();
        int size = 0;
        Character ch = null;
        for (int i = 0; i < str.length(); i++) {
            Character c = str.charAt(i);
            if(map.containsKey(c)){
                int num = map.get(c) + 1;
                map.put(c, num);
                if(size < num) {
                    size = num;
                    ch = c;
                }
            }else
                map.put(c, 1);
        }
        System.out.println("字母是：" + ch + "，次数：" + size);
    }


    @Test
    public void fen(){
        System.out.println( 2 << 3);
        short i = 1;
        i = 1;
    }


}
