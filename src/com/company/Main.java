package com.company;

import com.company.MapFolder.Map;

import java.util.Arrays;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
       // new SelectSave();
        String arrayS[] = {"isfandyol","isfandyola","gerish","banjemin","d_nial","atharvGosk","ablative","louisE","louisM","epicGamer"};

        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) selectionSort(arrayS,false);
        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) selectionSort(arrayS,true);
        System.out.println(Arrays.toString(arrayS));
        arrayS = (String[]) shuffleArray(arrayS);
        System.out.println(Arrays.toString(arrayS));

    }

    static Comparable[] selectionSort(Comparable[] array,boolean descend)
    {
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

        if(descend){
            for (int i = 0; i < array.length / 2; i++) {
                swap(array,i,array.length - 1 - i);
            }
        }
        return array;
    }
    static Object[] shuffleArray(Object[] array)
    {
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


    static void swap(Object[] array, int index1, int index2)
    {
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}




