package com.bluesgao.jvmdemo.thread;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.LongAdder;

public class NamedThreadFactory implements ThreadFactory {

    private final String prefix;
    private final LongAdder threadNumber = new LongAdder();

    public NamedThreadFactory(String prefix) {
        if(prefix==null || prefix.length()==0){
            throw new IllegalArgumentException("NamedThreadFactory prefix is null");
        }
        this.prefix = prefix;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        threadNumber.add(1);
        return new Thread(runnable, prefix + "-thread-" + threadNumber.intValue());
    }
}
