package com.kineteco;

import io.quarkus.runtime.QuarkusApplication;

public class SalesCli implements QuarkusApplication {
    @Override
    public int run(String... args) throws Exception {
        System.out.println("Hello " + args[0]);
        return 0;
    }
}
