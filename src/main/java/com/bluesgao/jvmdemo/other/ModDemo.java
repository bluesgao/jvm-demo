package com.bluesgao.jvmdemo.other;

import java.util.ArrayList;
import java.util.List;

public class ModDemo {
    public static void main(String[] args) {
        System.out.println("0%100=" + 0 % 100);
        System.out.println("19%100=" + 19 % 100);
        System.out.println("199%100=" + 199 % 100);
        System.out.println("200%100=" + 200 % 100);
        System.out.println("1000%100=" + 1000 % 100);


        List<Integer> partiton0 = new ArrayList<>();
        List<Integer> partiton1 = new ArrayList<>();
        List<Integer> partiton2 = new ArrayList<>();

        for(int i=0; i<100_000;i++){
            int mod = i%3;
            if (mod==0){
                partiton0.add(i);
            }else if (mod==1){
                partiton1.add(i);
            }else if(mod==2){
                partiton2.add(i);
            }
        }

        System.out.println("partiton0:"+partiton0);
        System.out.println("partiton1:"+partiton1);
        System.out.println("partiton2:"+partiton2);

    }
}
