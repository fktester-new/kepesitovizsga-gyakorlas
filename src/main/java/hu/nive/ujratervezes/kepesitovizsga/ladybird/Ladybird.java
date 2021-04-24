package hu.nive.ujratervezes.kepesitovizsga.ladybird;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ladybird {

    private DataSource dataSource;

    public Ladybird(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<String> getLadybirdsWithExactNumberOfPoints(int number){
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select hungarian_name from ladybirds where number_of_points = ?")) {
            stmt.setInt(1, number);
            return getList(stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot execute query");
        }
    }

    private List<String> getList(PreparedStatement stmt){
        try (ResultSet rs = stmt.executeQuery()){
            List<String> result = new ArrayList<>();
            while(rs.next()){
                String str = rs.getString(1);
                result.add(str);
            }
            return result;
        }
        catch (SQLException sqle){
            throw new IllegalStateException("Can not read data.");
        }
    }

    public Map<Integer, Integer> getLadybirdsByNumberOfPoints(){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT number_of_points, COUNT(id) AS number_of_kinds FROM ladybirds GROUP BY number_of_points ORDER BY number_of_points")){
            Map<Integer, Integer> result = new HashMap<>();
            while(rs.next()){
                result.put(rs.getInt(1), rs.getInt(2) );
            }
            return result;
        } catch (SQLException sqle){
            throw new IllegalStateException("Cannot query data.", sqle);
        }
    }
}
