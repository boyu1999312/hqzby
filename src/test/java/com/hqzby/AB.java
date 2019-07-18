package com.hqzby;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.util.Arrays;


public class AB {
    @Test
    public void test01() throws UnsupportedEncodingException {
        //System.out.println("�ڰ˸��ˣ�" + age(8));
        //num(1237);
        //
        //int[] arr = new int[]{1, 12, 4564, 64, 32, 1, 654, 564, 896, 7, 5, 3, 34, 56, 7496,};
        //System.out.println(Arrays.toString(arr));
        //sort(arr, 0, arr.length - 1);
        //System.out.println(Arrays.toString(arr));

        countString("asdffsdfasdfewrwasdferewqasdf", "asdf");

        splitString("ABC汉DEF", 6);
    }

    public int age(int num) {
        if (num == 0)
            return 10;
        return age(num - 1) + 2;
    }

    public void num(int size) {
        System.out.println(size);
        if (size <= 5000)
            num(size * 2);
        System.out.println(size);
    }

    public String getString() {
        return "����";
    }


    public void sort(int[] arr, int low, int high) {
        if(low > high) return;
        int i,j,temp,t;
        i = low; j = high;
        temp = arr[low];
        while(i < j){
            while(temp <= arr[j] && i < j){
                j--;
            }
            while(temp >= arr[i] && i < j){
                i++;
            }
            if(i < j){
                t = arr[i];
                arr[i] = arr[j];
                arr[j] = t;
            }
        }
        arr[low] = arr[i];
        arr[i] = temp;
        sort(arr, low, i - 1);
        sort(arr, i + 1, high);
    }


    public void countString(String str, String val){
        int index_key = str.indexOf(val);
        int count = 0;
        while(index_key != -1){
            index_key = str.indexOf(val, index_key + 1);
            count++;
        }
        System.out.println("���ֵĴ�����" + count);
    }


    public void splitString(String str, int size) throws UnsupportedEncodingException {
        byte[] buf = str.getBytes("GBK");
        int times = 0;
        for (int i = 0; i < size; i++) {
            if(buf[i] < 0)
                times++;
        }
        if(times%2!=0) size--;
        String newStr = new String(buf, 0, size);
        System.out.println("��ȡ��" + newStr);
    }

}

