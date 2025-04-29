package com.nohana.model;

import java.util.List;
import java.util.function.Predicate;

import static com.nohana.model.BoardColumnTypeEnum.INITIAL;
import static com.nohana.model.BoardColumnTypeEnum.CANCEL;

import lombok.Data;

@Data
public class BoardModel {

    private Long id;
    private String name;
    private List<BoardColumnModel> columns;
    
    public BoardColumnModel getInitialColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(INITIAL));
    }

    public BoardColumnModel getCancelColumn(){
        return getFilteredColumn(bc -> bc.getKind().equals(CANCEL));
    }

    private BoardColumnModel getFilteredColumn(Predicate<BoardColumnModel> filter){
        return columns.stream()
                .filter(filter)
                .findFirst().orElseThrow();
    }
}
