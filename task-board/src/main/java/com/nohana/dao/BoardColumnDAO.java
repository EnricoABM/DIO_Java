package com.nohana.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.mysql.cj.jdbc.StatementImpl;
import com.nohana.dto.BoardColumnDTO;
import com.nohana.model.BoardColumnModel;
import com.nohana.model.BoardColumnTypeEnum;
import com.nohana.model.CardModel;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BoardColumnDAO {
    
    private Connection conn;

    public BoardColumnModel insert(final BoardColumnModel entity) throws SQLException {
        String sql = "INSERT INTO BOARD_COLUMNS (name, `order`, kind, board_id) VALUES (?, ?, ?, ?)";
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setString(1, entity.getName());
            st.setInt(2, entity.getOrder());
            st.setString(3, entity.getKind().name());
            st.setLong(4, entity.getBoard().getId());
            st.executeUpdate();
            if (st instanceof StatementImpl impl) 
                entity.setId(impl.getLastInsertID());
            return entity;
        }
    }

    public List<BoardColumnModel> findByBoardId(final long id) throws SQLException {
        String sql = "SELECT * FROM BOARD_COLUMNS WHERE board_id = ? ORDER BY `order`";
        List<BoardColumnModel> entities = new ArrayList<>();
        try (
            var st = conn.prepareStatement(sql)
        ) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BoardColumnModel entity = new BoardColumnModel();
                entity.setId(rs.getLong("id"));
                entity.setName(rs.getString("name"));
                entity.setOrder(rs.getInt("order"));
                entity.setKind(BoardColumnTypeEnum.findByName(rs.getString("kind")));
                entities.add(entity);
            }
        }
        return entities;
    }

    public List<BoardColumnDTO> findByBoardIdWithDetails(final long id) throws SQLException {
        List<BoardColumnDTO> dtos = new ArrayList<>();
        String sql = 
        """
            SELECT 
                bc.id,
                bc.name,
                bc.`order`,
                bc.kind, 
                    (SELECT
                        COUNT(c.id) 
                        FROM CARDS c 
                        WHERE c.board_column_id = bc.id)
                    amount 
                FROM BOARD_COLUMNS bc 
                WHERE board_id = ?
                ORDER BY `order`
        """;
        try (var st = conn.prepareStatement(sql)) {
            st.setLong(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BoardColumnDTO dto = new BoardColumnDTO(
                    rs.getLong("bc.id"),
                    rs.getString("bc.name"),
                    BoardColumnTypeEnum.findByName(rs.getString("bc.kind")),
                    rs.getInt("amount")
                );
                dtos.add(dto);
            }
            return dtos;
        }
    }

    public Optional<BoardColumnModel> findById(Long id) throws SQLException {
        var sql =
        "SELECT bc.name, bc.kind, c.id, c.title, c.description FROM BOARDS_COLUMNS bc LEFT JOIN CARDS c ON c.board_column_id = bc.id WHERE bc.id = ?";
        try(var st = conn.prepareStatement(sql)){
            st.setLong(1, id);
            st.executeQuery();

            var rs = st.getResultSet();
            if (rs.next()){
                var model = new BoardColumnModel();
                model.setName(rs.getString("bc.name"));
                model.setKind(BoardColumnTypeEnum.findByName(rs.getString("bc.kind")));
                do {
                    var card = new CardModel();
                    if (Objects.isNull(rs.getString("c.title"))){
                        break;
                    }
                    card.setId(rs.getLong("c.id"));
                    card.setTitle(rs.getString("c.title"));
                    card.setDescription(rs.getString("c.description"));
                    model.getCards().add(card);
                }while (rs.next());
                return Optional.of(model);
            }
            return Optional.empty();
        }    
    }
}
