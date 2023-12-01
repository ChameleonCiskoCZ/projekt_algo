package org.example;

import com.google.inject.AbstractModule;

import java.util.Scanner;

public class DenikModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DenikInterfaceService.class).to(DenikService.class);
        bind(Scanner.class).toInstance(new Scanner(System.in));
    }
}
