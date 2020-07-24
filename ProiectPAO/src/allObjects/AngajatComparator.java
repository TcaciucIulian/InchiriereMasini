package allObjects;

import java.util.Comparator;
public class AngajatComparator implements Comparator<Angajat>{
    @Override
    public int compare(Angajat angajat, Angajat angajat1) {
        if(angajat.getVechime() < angajat1.getVechime())
            return 1;
        else if(angajat.getVechime() > angajat1.getVechime())
            return -1;
        else
            return 0;
    }
}
