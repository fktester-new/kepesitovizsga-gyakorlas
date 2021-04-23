package hu.nive.ujratervezes.kepesitovizsga.dictionary;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Dictionary {

    private DataSource dataSource;

    public Dictionary(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public String getEnglishWord(String hungarianWord) {
        if (isEmpty(hungarianWord)) {
            throw new IllegalArgumentException("The word is empty or null.");
        }
        Map<String, String> words = createDictionary(dataSource);
        Set<String> hungarian = words.keySet();
        for (String word : hungarian) {
            if (hungarianWord.equals(word)) {
                return words.get(word);
            }
        }
        throw new IllegalArgumentException("No such word in dictionary.");
    }

    public String getHungarianWord(String englishWord){
        if (isEmpty(englishWord)){
            throw new IllegalArgumentException("The wor is empty or null.");
        }
        Map<String, String> words = createDictionary(dataSource);
        for (Map.Entry<String, String> entry : words.entrySet()) {
            if (englishWord.equals(entry.getValue())){
                return entry.getKey();
            }
        }
        throw new IllegalArgumentException("No such word in dictionary.");
    }

    private Map<String, String> createDictionary(DataSource dataSource){
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select magyar, angol from words")) {
            Map<String, String> words = new HashMap<>();
            while (rs.next()) {
                String hun = rs.getString("magyar");
                String en = rs.getString("angol");
                words.put(hun, en);
            }
            return words;
        } catch (SQLException sqle) {
            throw new IllegalStateException("Cannot execute query");
        }
    }

    private boolean isEmpty(String s) {
        return s == null || s.isBlank();
    }

}
