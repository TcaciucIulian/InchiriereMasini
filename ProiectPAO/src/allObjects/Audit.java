package allObjects;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;

public class Audit {
    private static Audit audit = new Audit();

    public Audit(){}
    public static Audit getAudit() {
        return audit;
    }
    public void scriereAuditInRaport(String actiune, String fisier) {
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(fisier,true))){
            bufferedWriter.append(actiune);
            bufferedWriter.append(",");
            Timestamp timp = new Timestamp(System.currentTimeMillis());
            bufferedWriter.append(timp.toString());
            bufferedWriter.append("\n");
            }
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
