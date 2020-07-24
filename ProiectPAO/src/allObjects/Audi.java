package allObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Audi extends Masina{
    private String model4x4;

    public Audi(){}
    public Audi(String marca, String model, int anFabricatie, boolean disponibil, int pret, String model4x4) {
        super(marca , model, anFabricatie, disponibil, pret);
        this.model4x4 = model4x4;
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

    @Override
    public void afisareElement() {
        super.afisareElement();
        System.out.println(this.getModel4x4());
    }

    public void setModel4x4(String model4x4) {
        this.model4x4 = model4x4;
    }
    public String getModel4x4() {
        return model4x4;
    }

    @Override
    public void scriereMasiniInRaport(String fisier) {
        super.scriereMasiniInRaport(fisier);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(",");
            bufferedWriter.append(this.getModel4x4());
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
