package allObjects;

import java.util.Comparator;

public class ClientComparator implements Comparator<Client> {
    @Override
    public int compare(Client client, Client client1) {
        if(client.getPretDePlatit() < client1.getPretDePlatit())
            return 1;
        else if(client.getPretDePlatit() > client1.getPretDePlatit())
            return -1;
        else
            return 0;
    }
}
