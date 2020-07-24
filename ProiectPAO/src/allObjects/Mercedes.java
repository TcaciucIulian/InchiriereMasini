package allObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Mercedes extends Masina{
    private String maybach;

    public Mercedes(){}
    public Mercedes(String marca, String model, int anFabricatie, boolean disponibil, int pret,  String maybach){
        super(marca , model, anFabricatie, disponibil, pret);
        this.maybach = maybach;
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
    public void setDisponibil(boolean disponibil) {
        super.setDisponibil(disponibil);
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
        System.out.println(this.getMaybach());
    }

    public String getMaybach() {
        return maybach;
    }

    @Override
    public void scriereMasiniInRaport(String fisier) {
        super.scriereMasiniInRaport(fisier);
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(",");
            bufferedWriter.append(this.getMaybach());
            bufferedWriter.append("\n");
        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
