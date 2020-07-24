package allObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BMW extends Masina{
    private String modelSport;
    public BMW(){}
    public BMW(String marca, String model, int anFabricatie, boolean disponibil, int pret, String modelSport) {
        super(marca, model, anFabricatie, disponibil, pret);
        this.modelSport = modelSport;
    }
    @Override
    public void setMarca(String marca) {
        super.setMarca(marca);
    }

    @Override
    public String getMarca() {
        return super.getMarca();
    }

    @Override
    public void setModel(String model) {
        super.setModel(model);
    }

    @Override
    public String getModel() {
        return super.getModel();
    }

    @Override
    public void setAnFabricatie(int anFabricatie) {
        super.setAnFabricatie(anFabricatie);
    }

    @Override
    public int getAnFabricatie() {
        return super.getAnFabricatie();
    }

    @Override
    public boolean isDisponibil() {
        return super.isDisponibil();
    }

    @Override
    public void setPret(int pret) {
        super.setPret(pret);
    }

    @Override
    public int getPret() {
        return super.getPret();
    }


    public void setModelSport(String modelSport) {
        this.modelSport = modelSport;
    }

    public String getModelSport() {
        return modelSport;
    }

    @Override
    public void afisareElement() {
        super.afisareElement();
        System.out.println(this.getModelSport());
    }

    @Override
    public void scriereMasiniInRaport(String fisier) {
        super.scriereMasiniInRaport(fisier);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(",");
            bufferedWriter.append(this.getModelSport());
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
