package com.graduation.Kotaby.service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class WordsServiceImpl implements WordsService {
    private static final String DB_URL = "jdbc:sqlite:E:/Kotaby/src/main/resources/quran.db";

    private static final String SQL =
            "SELECT word_text "
                    + "FROM Words "
                    + "WHERE page_number = ? "
                    + "ORDER BY sora_id, aya_order, word_order";

    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("SQLite JDBC driver not found");
        }
    }

    @Override
    public List<String> getWordsByPage(int page) {
        List<String> words = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = conn.prepareStatement(SQL)) {
            stmt.setInt(1, page);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    words.add(rs.getString("word_text"));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error reading page " + page + " from quran.db", e);
        }

        return words;
    }
}
