package org.example;
import java.util.Date;
import java.util.List;

public interface DenikInterfaceService {
    List<ZaznamService> getZaznamy();

    void pridejZaznam(Date datum, String text);

    void odstranZaznam(ZaznamService zaznam);

    void predchoziZaznam();

    void dalsiZaznam();

    void pridatNovyZaznam();

    void ulozitZaznam();

    void smazatZaznam();

    void vypisInformaceODeniku();
}
