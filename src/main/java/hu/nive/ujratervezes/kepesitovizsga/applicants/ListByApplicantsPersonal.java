package hu.nive.ujratervezes.kepesitovizsga.applicants;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListByApplicantsPersonal implements ApplicantListGenerator{

    @Override
    public List<Applicant> getListFromDatabase(DataSource dataSource) {

        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select first_name, last_name, phone_number, email from applicants")){
            List<Applicant> result = new ArrayList<>();
            while(rs.next()){
                String firstName = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String phoneNumber = rs.getString("phone_number");
                String email = rs.getString("email");
                result.add(new Applicant(firstName, lastname, phoneNumber, email));
            }
            return result;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Can not execute query",sqle );
        }

    }
}
