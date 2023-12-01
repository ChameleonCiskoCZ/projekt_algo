package org.example;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;

import java.util.Scanner;

public class DenikRenderService {
    private DenikService denikService;
    private Scanner scanner;

    @Inject
    public DenikRenderService(DenikService denikService, Scanner scanner) {
        this.denikService = denikService;
        this.scanner = scanner;
    }
    public void spustiProgram() {
        while (true) {
            System.out.println("\nSeznam příkazů:");
            System.out.println("1. predchozi");
            System.out.println("2. dalsi");
            System.out.println("3. novy");
            System.out.println("4. uloz");
            System.out.println("5. smaz");
            System.out.println("6. zavri");

            System.out.print("Zadejte příkaz: ");
            String prikaz = scanner.nextLine();

            switch (prikaz) {
                case "predchozi":
                case "1":
                    denikService.predchoziZaznam();
                    break;
                case "dalsi":
                case "2":
                    denikService.dalsiZaznam();
                    break;
                case "novy":
                case "3":
                    denikService.pridatNovyZaznam();
                    break;
                case "uloz":
                case "4":
                    denikService.ulozitZaznam();
                    break;
                case "smaz":
                case "5":
                    denikService.smazatZaznam();
                    break;
                case "zavri":
                case "6":
                    System.out.println("Program ukončen.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Neplatná volba.");
            }

            denikService.vypisInformaceODeniku();
        }
    }



    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new DenikModule());
        DenikRenderService controller = injector.getInstance(DenikRenderService.class);
        controller.spustiProgram();
    }
}
