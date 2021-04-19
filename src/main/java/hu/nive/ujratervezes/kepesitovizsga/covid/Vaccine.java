package hu.nive.ujratervezes.kepesitovizsga.covid;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Vaccine {
    private DataSource dataSource;

    public Vaccine(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Person> getVaccinationList(){
        List<Person> vaccinationList = new ArrayList<>();
        try(Connection conn = dataSource.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs =stmt.executeQuery("select * from registrations")) {
            while(rs.next()){
                String name = rs.getString(1);
                int age = rs.getInt(2);
                ChronicDisease chronic = ChronicDisease.valueOf(rs.getString(3).toUpperCase());
                Pregnancy pregnancy = Pregnancy.valueOf(rs.getString(4).toUpperCase());
                vaccinationList.add(new Person(name, age, chronic, pregnancy));
            }
            return vaccinationList;
        } catch (SQLException sqle) {
            throw new IllegalArgumentException("Can not connect to database", sqle);
        }
    }

    }
