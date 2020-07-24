package Contracts;

import allObjects.*;

import java.sql.SQLException;

public abstract class AjutorInchiriereMasiniContract {
    public abstract InchiriereMasini construiesteCompanieInchiriere() throws SQLException;
}
