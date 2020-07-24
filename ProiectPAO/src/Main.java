import allObjects.*;
import Helpers.*;
import Contracts.*;
import database.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


// Avem o firma de inchiriere masini care cuprinde 3 elemente ( Masini, Angajat, Clienti )

// 1. Clasa BMW, AUDI, MERCEDES sunt 3 categorii de masini, si trebuie sa primeasca calitatile unei masini.
// 2. Clasa Angajat cuprinde date despre Angajat, si o lista de clienti de care Angajatul x trebuie sa se ocupe.
// 3. Clasa Clienti cuprinde date despre Clienti, si o lista de masini, deoarece un client poate sa inchirieze mai multe masini.
// 4. Clasa AngajatComparator este necesar pentru collections.sort() ( facem o ordonare descrescatoare dupa vechime a angajatiilor )
// 5. Clasa InchiriereMasini cuprinde vectorul de masini si 2 liste, una de angajati si clienti. Aici declaram toate functiile necesare pentru rezolvarea interogarilor)
// 6. Clasa AjutorInchiriereMasini este clasa unde facem initilizarea companiei.

public class Main {
    public static void main (String[] args) throws Exception {
        InchiriereMasini companieInchiriere = new AjutorInchiriereMasini().construiesteCompanieInchiriere();
        Audit serviciuAudit = Audit.getAudit();
        Scanner scanner = new Scanner(System.in);

        mySQL mySql = new mySQL();
        mySql.creareTabelAngajati();
        mySql.creareTabelClienti();
        mySql.creareTabelMasini();


        System.out.println(companieInchiriere.getContact());
        System.out.println(companieInchiriere.getNumeFirma());
        List<String> listaOptiuni = new ArrayList<>();

        listaOptiuni.add("Adauga masina");
        listaOptiuni.add("Sterge masina");
        listaOptiuni.add("Generare raport cu toate masinile");
        listaOptiuni.add("Adauga angajat");
        listaOptiuni.add("Sterge angajat dupa nume");
        listaOptiuni.add("Generare raport cu toti angajatii ");
        listaOptiuni.add("Generare raport cu toate masinile diposnibile ");
        listaOptiuni.add("Adauga client nou");
        listaOptiuni.add("Sterge client dupa nume");
        listaOptiuni.add("Generare raport cu toti clientii");
        listaOptiuni.add("Generare raport cu toate  masinile inchiriate de clientul cu numele x si prenumele y");
        listaOptiuni.add("Generare raport cu angajati in ordine descrescatoare dupa vechime");
        listaOptiuni.add("Generare raport cu toate masinile in ordine crescatoare dupa anul fabricatiei");
        listaOptiuni.add("Generare raport cu toti clientii in ordine descrescatoare dupa pretul de plata");
        listaOptiuni.add("Exit");
        for (int i = 0; i < listaOptiuni.size(); i++) {
            System.out.print(i+1);
            System.out.print(".");
            System.out.println(listaOptiuni.get(i));
        }

        String serviciuAuditRaport = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\serviciuAudit.csv";
        while(true) {
            System.out.println(" Alege optiune : ");
            int optiune = scanner.nextInt();
            serviciuAudit.scriereAuditInRaport(listaOptiuni.get(optiune-1), serviciuAuditRaport);
            switch(optiune) {
                case 1:
                    String fisierMasini = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Masini.csv";
                    System.out.println(" Adauga marca ");
                    String marca = scanner.next();
                    System.out.println(" Adauga model ");
                    String model = scanner.next();
                    System.out.println(" Adauga an fabricatie");
                    int anFabricatie = scanner.nextInt();
                    System.out.println(" Adauga pret ");
                    int pret = scanner.nextInt();
                    if(marca.compareTo("BMW") == 0){
                        System.out.print("Model sport? ");
                        String modelSport = scanner.next();
                        BMW masina = new BMW(marca,model,anFabricatie,true,pret,modelSport);
                        companieInchiriere.setListaMasini(InchiriereContract.adaugareMasina(masina,companieInchiriere.getListaMasini().length,companieInchiriere.getListaMasini()));
                        masina.scriereMasiniInRaport(fisierMasini);
                        mySql.inserareMasinaSql(masina, masina.getModelSport(), "nu are client", "nu are client");
                    }
                    else if (marca.compareTo("Audi") == 0){
                        System.out.print("Model 4x4?");
                        String model4x4 = scanner.next();
                        Audi masina = new Audi(marca,model,anFabricatie,true,pret,model4x4);
                        companieInchiriere.setListaMasini(InchiriereContract.adaugareMasina(masina,companieInchiriere.getListaMasini().length,companieInchiriere.getListaMasini()));
                        masina.scriereMasiniInRaport(fisierMasini);
                        mySql.inserareMasinaSql(masina, model4x4, "nu are client", "nu are client");
                    }
                    else if(marca.compareTo("Mercedes") == 0){
                        System.out.print("Maybach?");
                        String maybach = scanner.next();
                        Mercedes masina = new Mercedes(marca,model,anFabricatie,true,pret, maybach);
                        companieInchiriere.setListaMasini(InchiriereContract.adaugareMasina(masina,companieInchiriere.getListaMasini().length,companieInchiriere.getListaMasini()));
                        masina.scriereMasiniInRaport(fisierMasini);
                        mySql.inserareMasinaSql(masina, maybach, "nu are client", "nu are client");
                    }
                    break;
                case 2:
                    for (int i = 0; i < companieInchiriere.getListaMasini().length; i++) {
                        System.out.print(i);
                        System.out.println(companieInchiriere.getListaMasini()[i].getModel());
                    }
                    System.out.println("Introduceti indexul ");
                    int index = scanner.nextInt();
                    String modelMasina = companieInchiriere.getListaMasini()[index].getModel();
                    System.out.println(companieInchiriere.getListaMasini()[index].getModel());
                    companieInchiriere.setListaMasini(InchiriereContract.stergereMasina(companieInchiriere.getListaMasini().length,companieInchiriere.getListaMasini(),index));
                    mySql.stergereMasinaSql(modelMasina);
                    break;
                case 3:
                    String reportMasini = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportMasini.csv";
                    companieInchiriere.scriereMasiniHeader(reportMasini);
                    for(Masina masina : companieInchiriere.getListaMasini()) {
                        masina.scriereMasiniInRaport(reportMasini);
                    }
                    break;
                case 4:
                    String fisierAngajati = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Angajati.csv";
                    System.out.println("Nume angajat : ");
                    String numeAngajat = scanner.next();
                    System.out.println("Prenume angajat : ");
                    String prenumeAngajat = scanner.next();
                    System.out.println("Salariu : ");
                    int salariu = scanner.nextInt();
                    System.out.println("Vechime : ");
                    int vechime = scanner.nextInt();
                    List<Client> clientiAngajat = new ArrayList<>();
                    Angajat angajat = new Angajat(numeAngajat,prenumeAngajat, salariu, vechime, clientiAngajat);
                    companieInchiriere.scriereAngajatiInRaport(angajat, fisierAngajati);
                    companieInchiriere.adaugareAngajat(angajat);
                    mySql.inserareAngajatSql(angajat);
                    System.out.print("Angajatul a fost adaugat in companie !");
                    break;
                case 5:
                    System.out.println("Introduceti numele angajatului  care sa fie sters ");
                    String nume = scanner.next();
                    System.out.println("Introduceti prenumele angajatului  care sa fie sters ");
                    String prenume = scanner.next();
                    companieInchiriere.stergeAngajat(nume, prenume);
                    mySql.stergereAngajatSql(nume, prenume);
                    break;
                case 6:
                    String reportAngajati = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportAngajati.csv";
                    companieInchiriere.scriereAngajatiHeader(reportAngajati);
                    System.out.println(companieInchiriere.getListaAngajati().get(1).getNume());
                    companieInchiriere.getListaAngajati().forEach(angajat1 -> companieInchiriere.scriereAngajatiInRaport(angajat1, reportAngajati));
                    break;
                case 7:
                    String reportMasiniDisponibile = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportMasiniDisponibile.csv";
                    companieInchiriere.scriereMasiniHeader(reportMasiniDisponibile);
                    for(Masina x : companieInchiriere.getListaMasini()){
                        if( x.isDisponibil()){
                            x.scriereMasiniInRaport(reportMasiniDisponibile);
                        }
                    }
                    break;
                case 8:
                    System.out.println("Nume client : ");
                    String numeClient = scanner.next();
                    System.out.println("Prenume client : ");
                    String prenumeClient = scanner.next();
                    System.out.println("Durata inchiriere in saptamani ");
                    int durataInchiriere = scanner.nextInt();

                    List<Masina> masinaInchiriate = new ArrayList<>();
                    System.out.println("Cate masini inchiriaza");
                    int numarMasini = scanner.nextInt();
                    int i = 0;
                    int pretDePlatit = 0;
                    List<Masina> masiniDisponibile;
                    masiniDisponibile = companieInchiriere.afisareMasiniDisponibile();
                    while (i < numarMasini) {
                        System.out.println(" Inserati masina pe care o doriti ");
                        int indexMasina = scanner.nextInt();
                        Masina masinaNoua = masiniDisponibile.get(indexMasina-1);
                        masinaInchiriate.add(masinaNoua);
                        Masina masina = new Masina(masinaNoua.getMarca(), masinaNoua.getModel(), masinaNoua.getAnFabricatie(), masinaNoua.isDisponibil(), masinaNoua.getPret());
                        mySql.stergereMasinaSql(masinaNoua.getModel());
                        mySql.inserareMasinaSql(masina, null, numeClient, prenumeClient);
                        pretDePlatit = pretDePlatit + masiniDisponibile.get(indexMasina-1).getPret() * durataInchiriere;
                        i = i + 1;
                    }
                    Client client = new Client(numeClient, prenumeClient, durataInchiriere, pretDePlatit, masinaInchiriate);
                    companieInchiriere.adaugareClient(client);

                    companieInchiriere.afisareAngajati();
                    System.out.println("Selectam angajatul :");
                    int indexAngajat = scanner.nextInt();

                    System.out.println(client.getNume()+ ' ' + client.getPrenume());
                    companieInchiriere.getListaAngajati().get(indexAngajat).adaugareClientAngajat(client);
                    mySql.inserareClientSql(client, companieInchiriere.getListaAngajati().get(indexAngajat).getNume(),companieInchiriere.getListaAngajati().get(indexAngajat).getPrenume());
                    String fisierClienti = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Clienti.csv";
                    companieInchiriere.scriereClientiInRaport(client, fisierClienti);
                    break;
                case 9:
                    System.out.println("Introduceti numele clientului care sa fie sters ");
                    nume = scanner.next();
                    System.out.println("Introduceti prenumele clientului care sa fie sters ");
                    prenume = scanner.next();
                    companieInchiriere.stergeClient(nume, prenume);
                    mySql.stergereClientSql(nume, prenume);
                    break;
                case 10:
                    String reportClienti = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportClienti.csv";
                    companieInchiriere.scriereClientiHeader(reportClienti);
                    companieInchiriere.getListaClienti().forEach(client1 -> companieInchiriere.scriereClientiInRaport(client1, reportClienti));
                    break;
                case 11:
                    String fisierMasiniInchiriate = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\masiniInchiriateDeClient.csv";
                    System.out.println("Adaugati numele clientului ");
                    nume = scanner.next();
                    System.out.println("Adaugati prenumele clientului ");
                    prenume = scanner.next();
                    companieInchiriere.scriereMasiniHeader(fisierMasiniInchiriate);
                    for(Client x : companieInchiriere.getListaClienti())
                        if(x.getNume().compareTo(nume) == 0 && x.getPrenume().compareTo(prenume) == 0)
                            for (i = 0; i < x.getMasiniInchiriate().size(); i++) {
                                x.getMasiniInchiriate().get(i).scriereMasiniInRaport(fisierMasiniInchiriate);
                            }
                    companieInchiriere.afisareMasiniInchiriate(nume,prenume);
                    break;
                case 12:
                    // nu e colectie, dar o utilizez sortarea ca pentru colectie
                    String fisierAngOrdine = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportAngOrdineDescrescatoare.csv";
                    companieInchiriere.scriereAngajatiHeader(fisierAngOrdine);
                    Collections.sort(companieInchiriere.getListaAngajati(), new AngajatComparator());
                    companieInchiriere.getListaAngajati().forEach(angajat1 -> companieInchiriere.scriereAngajatiInRaport(angajat1, fisierAngOrdine));
                    break;
                case 13:
                    Masina[] masiniOrdonate = InchiriereContract.ordonareMasini(companieInchiriere.getListaMasini());
                    String fisierMasOrd = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportMasiniOrdDupaAnFabricatie.csv";
                    companieInchiriere.scriereMasiniHeader(fisierMasOrd);
                    for (Masina masina : masiniOrdonate) {
                        masina.scriereMasiniInRaport(fisierMasOrd);
                    }
                    break;
                case 14:
                    String fisierClientOrdine = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Reports\\reportClientOrdineDescPret.csv";
                    companieInchiriere.scriereClientiHeader(fisierClientOrdine);
                    Collections.sort(companieInchiriere.getListaClienti(), new ClientComparator());
                    companieInchiriere.getListaClienti().forEach(client1 -> companieInchiriere.scriereClientiInRaport(client1, fisierClientOrdine));
                    break;
                case 15:
                    System.exit(0);
                    break;
                }
            }
        }
    }

