package allObjects;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Masina {
    private String marca;
    private String model;
    private int anFabricatie;
    private boolean disponibil;
    private int pret;

    public Masina(){}
    public Masina(String marca, String model, int anFabricatie, boolean disponibil, int pretInchiriere){
        this.marca = marca;
        this.model = model;
        this.anFabricatie = anFabricatie;
        this.disponibil = disponibil;
        this.pret = pretInchiriere;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setAnFabricatie(int anFabricatie) {
        this.anFabricatie = anFabricatie;
    }

    public int getAnFabricatie() {
        return anFabricatie;
    }


    public void setDisponibil(boolean disponibil) {
        this.disponibil = disponibil;
    }

    public boolean isDisponibil() {
        return disponibil;
    }

    public void setPret(int pretInchiriere) {
        this.pret = pretInchiriere;
    }

    public int getPret() {
        return pret;
    }

    public void afisareElement() {
        System.out.print(marca + ' ' + ' ' + model + ' ' + anFabricatie + ' ' + disponibil + ' ' + ' ' + pret + ' ');
    }

    public void scriereMasiniInRaport(String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(this.getMarca());
            bufferedWriter.append(",");
            bufferedWriter.append(this.getModel());
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(this.getAnFabricatie()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(this.isDisponibil()));
            bufferedWriter.append(",");
            bufferedWriter.append(String.valueOf(this.getPret()));
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
