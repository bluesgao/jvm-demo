package com.bluesgao.jvmdemo.jmm;

public class VolatileDemo {
    int x = 0;
    volatile boolean flag = false;
    public void write(){
        x = 100;
        flag = true;
    }
    public void write_1(){
        flag = true;
        x = 100;
    }
    public void read(){
        System.out.println("flag:"+flag);
        if(flag){
            System.out.println("x:"+x);
        }
    }

    public static void main(String[] args) {
        VolatileDemo volatileDemo = new VolatileDemo();
        //volatileDemo.write();
        //volatileDemo.write_1();

         new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                volatileDemo.write_1();

            }
        }).start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        volatileDemo.read();

    }
}
