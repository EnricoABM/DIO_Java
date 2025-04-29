package com.nohana.service;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import com.nohana.dao.BoardColumnDAO;
import com.nohana.dao.BoardDAO;
import com.nohana.dto.BoardDetailsDTO;
import com.nohana.model.BoardColumnModel;
import com.nohana.model.BoardModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardService {

    private Connection conn;

    public BoardModel insert(final BoardModel entity) throws SQLException {
        BoardDAO bdao = new BoardDAO(conn);
        BoardColumnDAO bcdao = new BoardColumnDAO(conn);
        try {
            bdao.insert(entity);
            List<BoardColumnModel> columns = entity.getColumns().stream().map(c -> {
                c.setBoard(entity);
                return c;
            }).toList();

            for (BoardColumnModel column : columns) 
                bcdao.insert(column);

            conn.commit();
        } catch (SQLException ex) {
            conn.rollback();
            throw ex;
        }
        return entity;
    }

    public boolean delete(final long id) throws SQLException {
        BoardDAO bdao = new BoardDAO(conn);
        if (!bdao.exists(id))
            return false;
        bdao.delete(id);
        conn.commit();
        return true;
    }

    public Optional<BoardModel> findById(final long id) throws SQLException {
        BoardDAO bdao = new BoardDAO(conn);
        BoardColumnDAO bcdao = new BoardColumnDAO(conn);
        Optional<BoardModel> opt = bdao.findById(id);
        if (opt.isPresent()) {
            BoardModel entity = opt.get();
            entity.setColumns(bcdao.findByBoardId(id));
            return Optional.of(entity);
        } 
        return Optional.empty();
    }

    public Optional<BoardDetailsDTO> showBoardDetails(Long id) throws SQLException {
        var dao = new BoardDAO(conn);
        var boardColumnDAO = new BoardColumnDAO(conn);
        var optional = dao.findById(id);
        if (optional.isPresent()){
            var entity = optional.get();
            var columns = boardColumnDAO.findByBoardIdWithDetails(entity.getId());
            var dto = new BoardDetailsDTO(entity.getId(), entity.getName(), columns);
            return Optional.of(dto);
        }
        return Optional.empty();
    }
}
