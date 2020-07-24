package allObjects;


import Contracts.*;
import database.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class InchiriereMasini implements InchiriereContract {
    private  String numeFirma;
    private  String contact;

    private Masina[] listaMasini;
    private List<Angajat> listaAngajati;
    private List<Client> listaClienti;

    public InchiriereMasini(){}
    public InchiriereMasini(String numeFirma, String contact, Masina[] listaMasini, List<Angajat> listaAngajati, List<Client> listaClienti){
        this.numeFirma = numeFirma;
        this.contact = contact;
        this.listaMasini = listaMasini;
        this.listaAngajati = listaAngajati;
        this.listaClienti = listaClienti;
    }

    public void setListaMasini(Masina[] listaMasini) {
        this.listaMasini = listaMasini;
    }
    public Masina[] getListaMasini() {
        return listaMasini;
    }
    public List<Angajat> getListaAngajati() {
        return listaAngajati;
    }
    public List<Client> getListaClienti() {
        return listaClienti;
    }
    public String getContact() { return contact; }
    public String getNumeFirma() {
        return numeFirma;
    }



    @Override
    public void adaugareAngajat(Angajat angajat){
        this.listaAngajati.add(angajat);
    }

    @Override
    public void stergeAngajatIndex(int index){
        this.listaAngajati.remove(index);
    }

    @Override
    public void stergeAngajat(String nume, String prenume){
        int index = 0;
        int indexAngajat = -1;
        for(Angajat x : listaAngajati){
            if( x.getNume().compareTo(nume) == 0   && x.getPrenume().compareTo(prenume) == 0) {
                    indexAngajat = index;
            }
            index++;
        }
        System.out.println(indexAngajat);
        stergeAngajatIndex(indexAngajat);
    }

    @Override
    public void afisareElement(Angajat angajat){
        System.out.println(angajat.getNume() + ' ' + angajat.getPrenume() + ' ' +  angajat.getSalariu()+ ' ' + angajat.getVechime());
    }

    @Override
    public void afisareAngajati(){
        for(Angajat x : listaAngajati){
            afisareElement(x);
        }
    }

    @Override
    public List<Masina> afisareMasiniDisponibile(){
        List<Masina> masiniDisponibile = new ArrayList<>();
        System.out.println(" Urmatoarele masini sunt disponibile ");
        System.out.println(listaMasini.length);
        for(Masina x : listaMasini){
            if( x.isDisponibil()){
                masiniDisponibile.add(x);
                x.afisareElement();
            }
        }
        return masiniDisponibile;
    }


    @Override
    public void adaugareClient(Client client){ this.listaClienti.add(client); }

    @Override
    public void stergeClientIndex(int index){
        this.listaClienti.remove(index);
    }

    @Override
    public void stergeClient(String nume, String prenume) {
        int index = 0;
        int indexClient = -1;
        for(Client x : listaClienti){
            if( x.getNume().compareTo(nume) == 0   && x.getPrenume().compareTo(prenume) == 0) {
                indexClient = index;
            }
            index++;
        }
        stergeClientIndex(indexClient);
    }

    @Override
    public void afisareMasiniInchiriate(String nume, String prenume){
        for(Client x : listaClienti)
            if(x.getNume().compareTo(nume) == 0 && x.getPrenume().compareTo(prenume) == 0)
                for (int i = 0; i < x.masiniInchiriate.size(); i++) {
                    x.masiniInchiriate.get(i).afisareElement();
                }
    }

    @Override
    public void scriereClientiInRaport(Client client, String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(client.getNume());
            bufferedWriter.append(",");
            bufferedWriter.append(client.getPrenume());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(client.getDurataInchiriere()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(client.getPretDePlatit()));
            List<Masina> masiniInchiriate = client.getMasiniInchiriate();
            if (masiniInchiriate != null) {
                for (Masina masina : masiniInchiriate) {
                    bufferedWriter.append(",");
                    String detaliiMasiniInchiriate = masina.getMarca() + ' ' + masina.getModel() + ' ' + masina.getPret() + ' ' + masina.getAnFabricatie();
                    bufferedWriter.append(detaliiMasiniInchiriate);
                }
            }
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void scriereAngajatiInRaport(Angajat angajati, String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(angajati.getNume());
            bufferedWriter.append(",");
            bufferedWriter.append(angajati.getPrenume());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(angajati.getVechime()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(angajati.getSalariu()));
            List<Client> listaClientiAngajat = angajati.getListaClientiAngajat();
            if(listaClientiAngajat != null) {
                for (Client client: listaClientiAngajat) {
                    bufferedWriter.append(",");
                    String numePrenume = client.getNume() + ' ' + client.getPrenume();
                    bufferedWriter.append(numePrenume);
                }
            }
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static Masina[] citireMasiniDinCsv(String fisier) {
        Masina [] masini = new Masina[0];

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fisier))){
            String record;
            bufferedReader.readLine();
            while((record = bufferedReader.readLine()) != null) {
                String [] recordData = record.split(",");
                if(recordData[0].compareTo("BMW") == 0) {
                    BMW bmw = new BMW(recordData[0], recordData[1], Integer.parseInt(recordData[2]),Boolean.parseBoolean(recordData[3]),Integer.parseInt(recordData[4]),recordData[5]);
                    masini = InchiriereContract.adaugareMasina(bmw,masini.length,masini);
                }
                if(recordData[0].compareTo("Mercedes") == 0) {
                    Mercedes mercedes= new Mercedes(recordData[0],recordData[1],Integer.parseInt(recordData[2]),Boolean.parseBoolean(recordData[3]),Integer.parseInt(recordData[4]),recordData[5]);
                    masini = InchiriereContract.adaugareMasina(mercedes,masini.length,masini);
                }
                if(recordData[0].compareTo("Audi") == 0) {
                    Audi audi = new Audi(recordData[0],recordData[1],Integer.parseInt(recordData[2]),Boolean.parseBoolean(recordData[3]),Integer.parseInt(recordData[4]),recordData[5]);
                    masini = InchiriereContract.adaugareMasina(audi,masini.length,masini);
                }
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }
        return masini;
    }


    public static List<Client> citireClientiDinCsv(String fisier) {
        List<Client> clienti = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fisier))){
            String record;
            bufferedReader.readLine();
            while((record = bufferedReader.readLine()) != null) {
                String [] recordData = record.split(",");
                List<Masina> masiniInchiriate = new ArrayList<>();
                for(int i = 4 ; i < recordData.length ; i++) {
                    String[] newRecordData = recordData[i].split(" " );
                    if (newRecordData[0].compareTo("BMW") == 0) {
                        BMW bmw = new BMW(newRecordData[0], newRecordData[1], Integer.parseInt(newRecordData[2]), Boolean.parseBoolean(newRecordData[3]), Integer.parseInt(newRecordData[4]), newRecordData[5]);
                        masiniInchiriate.add(bmw);
                        System.out.println("adaugareBmw");
                    }
                    if (newRecordData[0].compareTo("Mercedes") == 0) {
                        Mercedes mercedes = new Mercedes(newRecordData[0], newRecordData[1], Integer.parseInt(newRecordData[2]), Boolean.parseBoolean(newRecordData[3]), Integer.parseInt(newRecordData[4]), newRecordData[5]);
                        masiniInchiriate.add(mercedes);
                        System.out.println("adaugareMercedes");
                    }
                    if (newRecordData[0].compareTo("Audi") == 0) {
                        Audi audi = new Audi(newRecordData[0], newRecordData[1], Integer.parseInt(newRecordData[2]), Boolean.parseBoolean(newRecordData[3]), Integer.parseInt(newRecordData[4]), newRecordData[5]);
                        masiniInchiriate.add(audi);
                        System.out.println("adaugareAudi");
                    }
                }
                Client client = new Client(recordData[0], recordData[1],Integer.parseInt(recordData[2]),Integer.parseInt(recordData[3]), masiniInchiriate);
                clienti.add(client);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return clienti;
    }

    public static List<Angajat> citireAngajatiDinCsv(String fisier) {
        List<Angajat> angajati = new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(fisier))){
            String record;
            bufferedReader.readLine();
            while((record = bufferedReader.readLine()) != null) {
                String[] recordData = record.split(",");
                List<Client> clientiAngajat = new ArrayList<>();
                for (int i = 4; i < recordData.length; i++) {
                    String[] newRecordData = recordData[i].split(" ");
                    Client client = new Client(newRecordData[0], newRecordData[1], 0, 0, null);
                    clientiAngajat.add(client);
                }
                System.out.println(recordData[1]);
                Angajat angajat = new Angajat(recordData[0], recordData[1], Integer.parseInt(recordData[2]), Integer.parseInt(recordData[3]), clientiAngajat);
                angajati.add(angajat);
            }
        }catch (IOException ex) {
            ex.printStackTrace();
        }

        return angajati;
    }

    @Override
    public void scriereMasiniHeader(String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier))){
            bufferedWriter.append("Marca");
            bufferedWriter.append(",");
            bufferedWriter.append("Model");
            bufferedWriter.append(",");
            bufferedWriter.append("An fabricatie");
            bufferedWriter.append(",");
            bufferedWriter.append("Disponibil");
            bufferedWriter.append(",");
            bufferedWriter.append("Pret");
            bufferedWriter.append(",");
            bufferedWriter.append("Tip");
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    @Override
    public void scriereClientiHeader(String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier))){
            bufferedWriter.append("Nume");
            bufferedWriter.append(",");
            bufferedWriter.append("Prenume");
            bufferedWriter.append(",");
            bufferedWriter.append("Durata inchiriere");
            bufferedWriter.append(",");
            bufferedWriter.append("Pret de platit");
            bufferedWriter.append(",");
            bufferedWriter.append("Lista Masini -> ");
            bufferedWriter.append(",");
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void scriereAngajatiHeader(String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier))){
            bufferedWriter.append("Nume");
            bufferedWriter.append(",");
            bufferedWriter.append("Prenume");
            bufferedWriter.append(",");
            bufferedWriter.append("Vechime");
            bufferedWriter.append(",");
            bufferedWriter.append("Salariu");
            bufferedWriter.append(",");
            bufferedWriter.append("Lista Client -> ");
            bufferedWriter.append(",");
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
