package com.example.hii;
import java.util.Random;
import static edu.rice.hj.Module1.*;

/**
 * Created by user on 4/28/2015.
 */
public class quicksortamal {
    public static void quicks(int[] arr,int left,int right)
    {
       int i = left;
        int j= right;
        int temp;
        int pivot = arr[(((left+right)/2)+1)];
        while(i<=j)
        {
            while (arr[i]<pivot) i++;
            while (arr[j]>pivot) j--;
            if(i<=j)
            {
                temp=arr[i];
                arr[i]=arr[j];
                arr[j]=temp;
                i++;
                j--;
            }

        }
        final int pointi=i;
        final int pointj=j;
       // if (j== ) {
         //   return newPoint(N, storeIndex - 1);
        //} else if (storeIndex == M) {
          //  return newPoint(storeIndex + 1, M);
        //}

       async(()->
        { if(left<pointj) quicks(arr,left,pointj);});
        async(()->{
        if(pointi<right)quicks(arr,pointi,right);});
    }
    public static void display(int [] arr){
        for(int i=0;i<arr.length;++i)
            System.out.println(arr[i]);
    }

    public static void main(String[] args){
        initializeHabanero();
        int [] da = new int[]{5,10,1,9,4,8,3,7,2};
        quicks(da,0,da.length-1);
        display(da);
        finalizeHabanero();
    }

}
