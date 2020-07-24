package allObjects;

import Contracts.InchiriereContract;

import java.util.ArrayList;
import java.util.List;

public class Angajat{
    private String nume;
    private String prenume;
    private int salariu;
    private int vechime;
    private List<Client> listaClientiAngajat = new ArrayList<>();


    public Angajat(){};
    public Angajat(String nume, String prenume, int salariu, int vechime, List<Client> listaClientiAngajat){
        this.nume = nume;
        this.prenume = prenume;
        this.salariu = salariu;
        this.vechime = vechime;
        this.listaClientiAngajat = listaClientiAngajat;
    }


    public void setNume(String nume) {

        this.nume = nume;
    }

    public String getNume() {
        return nume;
    }
    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }
    public String getPrenume() {
        return prenume;
    }
    public void setSalariu(int salariu) {
        this.salariu = salariu;
    }
    public int getSalariu() {
        return salariu;
    }
    public void setVechime(int vechime) {
        this.vechime = vechime;
    }
    public int getVechime() {
        return vechime;
    }
    public void setListaClientiAngajat(List<Client> listaClientiAngajat) {
        this.listaClientiAngajat = listaClientiAngajat;
    }
    public List<Client> getListaClientiAngajat() {
        return listaClientiAngajat;
    }

    public void adaugareClientAngajat(Client client){
        this.listaClientiAngajat.add(client);
    }
}
