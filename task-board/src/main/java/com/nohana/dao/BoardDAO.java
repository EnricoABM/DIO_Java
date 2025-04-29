package com.nohana.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import com.mysql.cj.jdbc.StatementImpl;
import com.nohana.model.BoardModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardDAO {
    
    private Connection conn;

    public BoardModel insert(final BoardModel entity) throws SQLException {
        String sql = "INSERT INTO BOARDS (name) VALUES (?)";
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setString(1, entity.getName());
            st.executeUpdate();

            if (st instanceof StatementImpl s) {
                entity.setId(s.getLastInsertID());
            }
            return entity;
        } 
    }

    public void delete(final Long id) throws SQLException {
        String sql = "DELETE FROM BOARDS WHERE id = ?";
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setLong(1, id);
            st.executeUpdate();
        }
    }
 
    public Optional<BoardModel> findById(final Long id) throws SQLException {
        String sql = "SELECT id, name FROM BOARDS WHERE id = ?";
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                BoardModel entity = new BoardModel();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                return Optional.of(entity);
            }
            return Optional.empty();
        }
    }

    public boolean exists(final Long id) throws SQLException {
        String sql = "SELECT 1 FROM BOARDS WHERE id = ?";
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            return rs.next();
        }
    }
}
