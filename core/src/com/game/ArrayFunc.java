package com.game;

public abstract class ArrayFunc {

    public static boolean contains(int value, int[] array){

        for (int index = 0; index < array.length; index++){
            if(array[index] == value){
                return true;
            }
        }
        return false;
    }

    public static boolean contains(byte value, int[] array){

        for (int index = 0; index < array.length; index++){
            if(array[index] == (int)value){
                return true;
            }
        }
        return false;
    }
}
