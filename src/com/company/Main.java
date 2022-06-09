package com.company;

import com.company.MapFolder.Map;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
       new BiomeDisplay();
        String arrayS[] = {"isfandyol","isfandyola","gerish","banjemin","d_nial","atharvGosk","ablative","louisE","louisM","epicGamer"};

        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) alphabetical(arrayS,false);
        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) alphabetical(arrayS,true);
        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) shuffle(arrayS);
        System.out.println(Arrays.toString(arrayS));

        int arrayN[] = {86,2967,1,2,6,7,9,3,4,6,7,18,9,92,35,4,56,87,90,34,67,128,127,1};
        Character arrayC[] = new Character[arrayN.length];
        for (int i = 0; i < arrayN.length; i++)
            arrayC[i] = (char) (arrayN[i]);

        System.out.println(Arrays.toString(arrayN));
        arrayC = (Character[]) alphabetical(arrayC,false);
        for (int i = 0; i < arrayN.length; i++)
            arrayN[i] = ((int)(arrayC[i]));
        System.out.println(Arrays.toString(arrayN));
        arrayC = (Character[]) alphabetical(arrayC,true);
        for (int i = 0; i < arrayN.length; i++)
            arrayN[i] = ((int)(arrayC[i]));
        System.out.println(Arrays.toString(arrayN));

    }

    public static Comparable[]  alphabetical(Comparable[] array, boolean reverse){
        int smallest;
        for(int i = 0; i < array.length; i++)
        {
            smallest = i; // set first element as smallest
            for(int j = i + 1; j < array.length; j++) // find smallest
                if(array[j].compareTo(array[smallest]) < 0)
                    smallest = j;
            if(smallest != i)
                swap(array, smallest, i);
        }

        if(reverse){
            for (int i = 0; i < array.length / 2; i++) {
                swap(array,i,array.length - 1 - i);
            }
        }
        return array;
    }

    public static Object[] shuffle(Object[] array){
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            swap(array,rnd.nextInt(i + 1),i);
        }
        for (int i = array.length - 1; i > 0; i--)
        {
            swap(array,rnd.nextInt(i + 1),i);
        }
        return array;
    }

    static void swap(Object[] array, int index1, int index2){
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}




