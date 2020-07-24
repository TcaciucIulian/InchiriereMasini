package Contracts;

import allObjects.*;
import java.util.List;

public interface InchiriereContract {
    static Masina[] adaugareMasina(Masina masina, int numarMasini, Masina[] listaMasini) {
        Masina[] nouaListaMasina = new Masina[numarMasini+1];

        for (int i = 0; i < numarMasini; i++) {
            nouaListaMasina[i] = listaMasini[i];
        }
        nouaListaMasina[numarMasini] = masina;
        return nouaListaMasina;
    }

    static Masina[] stergereMasina(int numarMasini, Masina[] listaMasini, int index){
        Masina[] nouaListaMasina = new Masina[numarMasini-1];
        int k = 0;
        for (int i = 0; i < numarMasini; i++) {
            if(i == index)
                continue;

            nouaListaMasina[k] = listaMasini[i];
            k++;
        }
        return nouaListaMasina;
    }
    void adaugareAngajat(Angajat angajat);
    void stergeAngajatIndex(int index);
    void stergeAngajat(String nume, String prenume);
    void afisareElement(Angajat angajat);
    void afisareAngajati();
    List<Masina> afisareMasiniDisponibile();
    void adaugareClient(Client client);
    void stergeClientIndex(int index);
    void stergeClient(String nume, String prenume);
    void afisareMasiniInchiriate(String nume, String prenume);
    static Masina[] ordonareMasini(Masina[] listaMasini){
        Masina aux;
        for (int i = 0; i <listaMasini.length-1 ; i++) {
            for(int j = i; j<listaMasini.length; j++)
                if(listaMasini[j].getAnFabricatie() < listaMasini[i].getAnFabricatie())
                {
                    aux = listaMasini[i];
                    listaMasini[i] = listaMasini[j];
                    listaMasini[j] = aux;
                }
        }
        return listaMasini;
    }

    void scriereClientiInRaport(Client client, String fisier);
    void scriereAngajatiInRaport(Angajat angajati, String fisier);
    void scriereMasiniHeader(String fisier);
    void scriereAngajatiHeader(String fisier);
    void scriereClientiHeader(String fisier);

}

