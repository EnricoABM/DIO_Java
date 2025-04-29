package com.nohana.model;

import java.util.ArrayList;
import java.util.List;

import lombok.Data;

@Data
public class BoardColumnModel {
    
    private Long id;
    private String name;
    private Integer order;
    private BoardColumnTypeEnum kind;
    private BoardModel board = new BoardModel();
    private List<CardModel> cards = new ArrayList<>();
}
