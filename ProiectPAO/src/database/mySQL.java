package database;

import Contracts.InchiriereContract;
import allObjects.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class mySQL {
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/proiectpao";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD= "12345";

    private static final String READ_ALL_ANGAJATI = "SELECT * FROM angajati";
    private static final String READ_ALL_MASINI = "SELECT * FROM masini";
    private static final String READ_ALL_CLIENTI = "SELECT * FROM clienti";
    private static final String CREATE_NEW_ANGAJAT = "INSERT INTO angajati(nume,prenume,salariu,vechime)" + "values (?, ?, ?, ?)";
    private static final String CREATE_NEW_MASINA = "INSERT INTO masini(Marca, Model, AnFabricatie, Disponibil, Pret, ModelMasina, NumeClient, PrenumeClient)" + "values (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String CREATE_NEW_ClIENT = "INSERT INTO clienti(Nume, Prenume, Ore, PretDePlatit, NumeAngajat, PrenumeAngajat)" + "values (?, ?, ?, ?, ?, ?)";
    private static final String DELETE_ANGAJAT = "DELETE FROM angajati WHERE nume = ? AND prenume = ?";
    private static final String DELETE_MASINA = "DELETE FROM masini WHERE Model = ?";
    private static final String DELETE_CLIENT = "DELETE FROM clienti WHERE Nume = ? AND Prenume = ?";


    public static Connection getDBConnection() throws SQLException{
        return DriverManager.getConnection(DATABASE_URL,DATABASE_USER,DATABASE_PASSWORD);
    }

    public void creareTabelAngajati() throws SQLException{
        Connection conectiune = getDBConnection();
        PreparedStatement preparedStatement = conectiune.prepareStatement("CREATE TABLE IF NOT EXISTS angajati(nume varchar(20) NOT NULL, prenume varchar(20), salariu int NOT NULL, vechime int NOT NULL, PRIMARY KEY(nume))");
        preparedStatement.executeUpdate();
    }
    public void creareTabelClienti() throws SQLException{
        Connection conectiune = getDBConnection();
        PreparedStatement preparedStatement = conectiune.prepareStatement("CREATE TABLE IF NOT EXISTS clienti(Nume varchar(20) NOT NULL, Prenume varchar(20),  Ore int NOT NULL, PretDePlatit int NOT NULL, NumeAngajat varchar(20), PrenumeAngajat varchar(20),  PRIMARY KEY(Nume))");
        preparedStatement.executeUpdate();
    }
    public void creareTabelMasini() throws SQLException{
            Connection conectiune = getDBConnection();
            PreparedStatement preparedStatement = conectiune.prepareStatement("CREATE TABLE IF NOT EXISTS masini(Marca varchar(20) NOT NULL, Model varchar(20), Anfabricatie int NOT NULL, Disponibil boolean, Pret int NOT NULL, ModelMasina varchar(25), NumeClient varchar(20), PrenumeClient varchar(20), PRIMARY KEY(Model))");
            preparedStatement.executeUpdate();
    }

    public List<Angajat> citireAngajatiSql() throws SQLException{
        List<Angajat> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(READ_ALL_ANGAJATI);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            Angajat ang = new Angajat(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4), new ArrayList<>());
            returnedList.add(ang);
        }
        return returnedList;
    }

    public List<Client> citireClientiSql() throws SQLException{
        List<Client> returnedList = new ArrayList<>();
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(READ_ALL_CLIENTI);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            Client client = new Client(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getInt(4), new ArrayList<>());
            returnedList.add(client);
        }
        return returnedList;
    }
    public static Masina[] citireMasiniSql() throws SQLException{
        Masina [] masini = new Masina[0];
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(READ_ALL_MASINI);
        ResultSet rs = preparedStatement.executeQuery();
        while (rs.next()){
            if (rs.getString(2).equals("BMW")) {
                BMW bmw = new BMW(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4), rs.getInt(5), rs.getString(6));
                masini = InchiriereContract.adaugareMasina(bmw,masini.length,masini);
            }
            else if (rs.getString(2).equals("Mercedes")) {
                Mercedes mercedes = new Mercedes(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4), rs.getInt(5), rs.getString(6));
                masini = InchiriereContract.adaugareMasina(mercedes ,masini.length,masini);
            }
            else if(rs.getString(2).equals("Audi")) {
                Audi audi = new Audi(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4), rs.getInt(5), rs.getString(6));
                masini = InchiriereContract.adaugareMasina(audi,masini.length,masini);
            }
            else {
                Masina masina = new Masina(rs.getString(1),rs.getString(2),rs.getInt(3),rs.getBoolean(4), rs.getInt(5));
                masini = InchiriereContract.adaugareMasina(masina,masini.length,masini);
            }
        }
        return masini;
    }

    public boolean inserareAngajatSql(Angajat ang) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(CREATE_NEW_ANGAJAT);
        preparedStatement.setString(1,ang.getNume());
        preparedStatement.setString(2,ang.getPrenume());
        preparedStatement.setInt(3, ang.getSalariu());
        preparedStatement.setInt(4,ang.getVechime());

        return preparedStatement.executeUpdate() > 0;

    }

    public boolean inserareClientSql(Client client,  String numeAngajat, String prenumeAngajat) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(CREATE_NEW_ClIENT);
        preparedStatement.setString(1,client.getNume());
        preparedStatement.setString(2,client.getPrenume());
        preparedStatement.setInt(3, client.getDurataInchiriere());
        preparedStatement.setInt(4, client.getPretDePlatit());
        preparedStatement.setString(5, numeAngajat);
        preparedStatement.setString(6, prenumeAngajat);


        return preparedStatement.executeUpdate() > 0;
    }

    public boolean inserareMasinaSql(Masina masina, String modelMasina, String numeClient, String prenumeClient) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(CREATE_NEW_MASINA);
        preparedStatement.setString(1,masina.getMarca());
        preparedStatement.setString(2,masina.getModel());
        preparedStatement.setInt(3, masina.getAnFabricatie());
        preparedStatement.setBoolean(4,masina.isDisponibil());
        preparedStatement.setInt(5, masina.getPret());
        preparedStatement.setString(6, modelMasina);
        preparedStatement.setString(7, numeClient);
        preparedStatement.setString(8, prenumeClient);

        return preparedStatement.executeUpdate() > 0;

    }

    public boolean stergereAngajatSql(String numeAngajat, String prenumeAngajat) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_ANGAJAT);
        preparedStatement.setString(1, numeAngajat);
        preparedStatement.setString(2, prenumeAngajat);
        return preparedStatement.executeUpdate() > 0;

    }

    public boolean stergereMasinaSql(String model) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_MASINA);
        preparedStatement.setString(1, model);
        return preparedStatement.executeUpdate() > 0;

    }

    public boolean stergereClientSql(String numeClient, String prenumeClient) throws SQLException{
        PreparedStatement preparedStatement = getDBConnection().prepareStatement(DELETE_CLIENT);
        preparedStatement.setString(1, numeClient);
        preparedStatement.setString(2, prenumeClient);
        return preparedStatement.executeUpdate() > 0;

    }

}
