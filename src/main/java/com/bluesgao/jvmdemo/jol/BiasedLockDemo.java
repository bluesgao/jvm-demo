package com.bluesgao.jvmdemo.jol;

import org.openjdk.jol.info.ClassLayout;

public class BiasedLockDemo {
    static class A {
        boolean flag = true;
    }
    public static void main(String[] args) throws InterruptedException {
        A a = new A();
        System.out.println("=============hashCode:"+a.hashCode()+"======"+Integer.toHexString(a.hashCode()));
        System.out.println(ClassLayout.parseInstance(a).toPrintable());

        synchronized (a){
            System.out.println("=============hashCode:"+a.hashCode()+"======"+Integer.toHexString(a.hashCode()));
            System.out.println(ClassLayout.parseInstance(a).toPrintable());
        }
    }
}


/*output
注意：2次输出的对象头中bits数据有变化

com.bluesgao.jvmdemo.jol.BiasedLockDemo$A object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           01 de 9b 67 (00000001 11011110 10011011 01100111) (1738268161)
      4     4           (object header)                           3c 00 00 00 (00111100 00000000 00000000 00000000) (60)
      8     4           (object header)                           92 c3 00 20 (10010010 11000011 00000000 00100000) (536920978)
     12     1   boolean A.flag                                    true
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total

=============hashCode:1013423070======3c679bde
com.bluesgao.jvmdemo.jol.BiasedLockDemo$A object internals:
 OFFSET  SIZE      TYPE DESCRIPTION                               VALUE
      0     4           (object header)                           98 f3 98 02 (10011000 11110011 10011000 00000010) (43578264)
      4     4           (object header)                           00 00 00 00 (00000000 00000000 00000000 00000000) (0)
      8     4           (object header)                           92 c3 00 20 (10010010 11000011 00000000 00100000) (536920978)
     12     1   boolean A.flag                                    true
     13     3           (loss due to the next object alignment)
Instance size: 16 bytes
Space losses: 0 bytes internal + 3 bytes external = 3 bytes total
 */