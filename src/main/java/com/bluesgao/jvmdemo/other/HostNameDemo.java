package com.bluesgao.jvmdemo.other;

import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;

public class HostNameDemo {
    public static void main(String[] args) {
        System.out.println("------start------");
        RuntimeMXBean runtimeMXBean = ManagementFactory.getRuntimeMXBean();
        System.out.println("name:"+runtimeMXBean.getName());
    }
}
