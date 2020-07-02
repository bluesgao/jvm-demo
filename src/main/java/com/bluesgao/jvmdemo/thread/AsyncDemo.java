package com.bluesgao.jvmdemo.thread;

import java.util.concurrent.CompletableFuture;

public class AsyncDemo {
    public static void main(String[] args) {
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(()->"hello world")
                .thenApply(s->s+" qq").thenApply(String::toUpperCase);

        System.out.println(cf.join());

    }
}
