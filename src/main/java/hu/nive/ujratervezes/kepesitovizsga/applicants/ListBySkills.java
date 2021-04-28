package hu.nive.ujratervezes.kepesitovizsga.applicants;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ListBySkills implements ApplicantListGenerator{

    @Override
    public List<Applicant> getListFromDatabase(DataSource dataSource) {
        try(Connection conn = dataSource.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT first_name, last_name, skill FROM applicants WHERE skill LIKE \"___\"")){
            List<Applicant> result = new ArrayList<>();
            while (rs.next()){
                String firstName = rs.getString("first_name");
                String lastname = rs.getString("last_name");
                String skill = rs.getString("skill");
                result.add(new Applicant(firstName, lastname, skill));
            }
            return result;
        } catch (SQLException sqle) {
            throw new IllegalStateException("can not execute query.", sqle);
        }
    }
}
