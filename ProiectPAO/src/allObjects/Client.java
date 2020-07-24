package allObjects;

import java.util.ArrayList;
import java.util.List;


public class Client {
    private String nume;
    private String prenume;
    private int durataInchiriere;
    private int pretInchiriere;
    List<Masina> masiniInchiriate = new ArrayList<>();

    public Client(){}
    public Client(String nume,String prenume, int durataInchiriere, int pretDePlatit, List<Masina> masiniInchiriate){
        this.nume = nume;
        this.prenume = prenume;
        this.durataInchiriere = durataInchiriere;
        this.pretInchiriere = pretDePlatit;
        this.masiniInchiriate = masiniInchiriate;
    }

    public String getNume() {
        return nume;
    }
    public String getPrenume() {
        return prenume;
    }
    public int getDurataInchiriere() {
        return durataInchiriere;
    }
    public int getPretDePlatit() {
        return pretInchiriere;
    }
    public List<Masina> getMasiniInchiriate() {
        return masiniInchiriate;
    }

}
