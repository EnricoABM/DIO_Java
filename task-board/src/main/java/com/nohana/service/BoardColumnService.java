package com.nohana.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

import com.nohana.dao.BoardColumnDAO;
import com.nohana.model.BoardColumnModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardColumnService {
    private final Connection conn;

    public Optional<BoardColumnModel> findById(final Long id) throws SQLException {
        var dao = new BoardColumnDAO(conn);
        return dao.findById(id);
    }
}
