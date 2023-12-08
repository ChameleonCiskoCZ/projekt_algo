package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.google.inject.Inject;

public class DenikService implements DenikInterfaceService {
    @Inject
    Scanner scanner;
    private ZaznamService temporaryZaznam;
    private LinkedList<ZaznamService> zaznamy = new LinkedList<>();

    @Override
    public List<ZaznamService> getZaznamy() {
        return zaznamy;
    }

    @Override
    public void pridejZaznam(Date datum, String text) {
        temporaryZaznam = new ZaznamService(datum, text);
    }

    @Override
    public void odstranZaznam(ZaznamService zaznam) {
        zaznamy.remove(zaznam);
    }

    private int aktualniZaznamIndex = -1;
    @Override
    public void predchoziZaznam() {
        List<ZaznamService> zaznamy = getZaznamy();
        if (!zaznamy.isEmpty()) {
            if (aktualniZaznamIndex > 0) {
                aktualniZaznamIndex--;
            } else {
                System.out.println("Jste na prvním záznamu.");
            }
        } else {
            System.out.println("Deník je prázdný.");
        }
    }

    public void dalsiZaznam() {
        List<ZaznamService> zaznamy = getZaznamy();
        if (!zaznamy.isEmpty()) {
            if (aktualniZaznamIndex < zaznamy.size() - 1) {
                aktualniZaznamIndex++;
            } else {
                System.out.println("Jste na posledním záznamu.");
            }
        } else {
            System.out.println("Deník je prázdný.");
        }
    }

    public void pridatNovyZaznam() {
        System.out.println("Zadejte datum (dd.MM.yyyy): ");
        String datumString = scanner.nextLine();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        try {
            Date datum = dateFormat.parse(datumString);
            System.out.println("Zadejte text záznamu: ");
            String text = scanner.nextLine();

            pridejZaznam(datum, text);
        } catch (ParseException e) {
            System.out.println("Chybný formát datumu. Zadejte datum ve formátu dd.MM.yyyy.");
        }
    }

    public void ulozitZaznam() {
        if (temporaryZaznam != null) {
            zaznamy.addLast(temporaryZaznam);
            System.out.println("Záznam byl úspěšně uložen.");
            temporaryZaznam = null; // Reset temporary variable
        } else {
            System.out.println("Není žádný záznam k uložení.");
        }
    }

    public void smazatZaznam() {
        List<ZaznamService> zaznamy = getZaznamy();
        if (!zaznamy.isEmpty()) {
            ZaznamService odstranovanyZaznam = zaznamy.get(aktualniZaznamIndex);
            System.out.println("Opravdu chcete smazat následující záznam?\n" + odstranovanyZaznam);
            System.out.println("Potvrďte smazání (ano/ne): ");
            String potvrzeni = scanner.nextLine().toLowerCase();

            if (potvrzeni.equals("ano")) {
                odstranZaznam(odstranovanyZaznam);
                if (aktualniZaznamIndex > 0) {
                    aktualniZaznamIndex--;
                } else if (zaznamy.size() > 0) {
                    aktualniZaznamIndex = 0;
                } else {
                    aktualniZaznamIndex = -1;
                }
                System.out.println("Záznam byl úspěšně smazán.");
            } else {
                System.out.println("Smazání záznamu zrušeno.");
            }
        } else {
            System.out.println("Deník je prázdný. Nelze smazat žádný záznam.");
        }
    }

    public void vypisInformaceODeniku() {
        List<ZaznamService> zaznamy = getZaznamy();
        System.out.println("Počet záznamů v deníku: " + zaznamy.size());
        if (!zaznamy.isEmpty()) {
            if (aktualniZaznamIndex >= 0 && aktualniZaznamIndex < zaznamy.size()) {
                ZaznamService aktualniZaznam = zaznamy.get(aktualniZaznamIndex);
                System.out.println("Aktuální záznam:\n" + aktualniZaznam);
            } else {
                System.out.println("Prázdná stránka");
            }
        }
    }
}
