package Helpers;

import Contracts.*;
import allObjects.*;

import java.sql.SQLException;
import java.util.List;
import database.mySQL;

public class AjutorInchiriereMasini extends AjutorInchiriereMasiniContract{
    public AjutorInchiriereMasini(){}

    @Override
    public InchiriereMasini construiesteCompanieInchiriere() throws SQLException {
      /*  String fisier = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Masini.csv";
        Masina[] listaMasini = InchiriereMasini.citireMasiniDinCsv(fisier); */

        mySQL mySql = new mySQL();
        mySql.creareTabelAngajati();
        mySql.creareTabelClienti();
        mySql.creareTabelMasini();

        Masina[] listaMasini = mySQL.citireMasiniSql();
        for (Masina masina : listaMasini) {
            System.out.println(masina.getMarca() + ' ' + masina.getModel());
        }

        List<Client> listaClienti = mySql.citireClientiSql();
        for (Client client: listaClienti) {
            System.out.println(client.getNume() + ' ' + client.getPrenume());
        }

        List<Angajat> listaAngajati = mySql.citireAngajatiSql();
        for (Angajat angajat: listaAngajati) {
            System.out.println(angajat.getNume() + ' ' + angajat.getPrenume());
        }
        /*  fisier = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Clienti.csv";
        List<Client> listaClienti = InchiriereMasini.citireClientiDinCsv(fisier); */


      /*  fisier = "C:\\Users\\iulia\\IdeaProjects\\ProiectPAO\\src\\Csvs\\Angajati.csv";
        List<Angajat> listaAngajati = InchiriereMasini.citireAngajatiDinCsv(fisier); */

        return new InchiriereMasini("IuliRENT","Bulevardul Camil Ressu",listaMasini,listaAngajati,listaClienti);
    }
}
