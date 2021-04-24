package hu.nive.ujratervezes.kepesitovizsga.ladybird;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

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

    public Set<Ladybug> getLadybirdByPartOfLatinNameAndNumberOfPoints(String partOfName, int numberOfPoints){
        if (partOfName == null || partOfName.isBlank()){
            throw new IllegalArgumentException("The word fragment is empty or null.");
        }
        try(Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM ladybirds WHERE latin_name LIKE ? ESCAPE '!' AND number_of_points = ?;")){
            partOfName = partOfName
                    .replace("!", "!!")
                    .replace("%", "!%")
                    .replace("_", "!_")
                    .replace("[", "![");
            stmt.setString(1, "%" + partOfName + "%");
            stmt.setInt(2, numberOfPoints);
            return getLadybugs(stmt);
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot execute query!", sqle);
        }
    }

    private Set<Ladybug> getLadybugs(PreparedStatement stmt){
        try(ResultSet rs = stmt.executeQuery()){
            Set<Ladybug> result = new HashSet<>();
            while(rs.next()){
                String hungarianName = rs.getString("hungarian_name");
                String latinName = rs.getString("latin_name");
                String genus = rs.getString("genus");
                int points = rs.getInt("number_of_points");
                result.add(new Ladybug(hungarianName, latinName, genus, points));
            }
            return result;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot select ladybugs.");
        }
    }

    public Map<String, Integer> getLadybirdStatistics(){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT genus, COUNT(id) AS species FROM ladybirds GROUP BY genus")) {
            Map<String, Integer> result = new HashMap<>();
            while (rs.next()){
                String genus = rs.getString(1);
                int numberOfSpecies = rs.getInt(2);
                result.put(genus, numberOfSpecies);
            }
            return result;
        }catch (SQLException sqle){
            throw new IllegalStateException("Cannot query data.", sqle);
        }
    }

}
