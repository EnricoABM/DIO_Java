package com.nohana.model;

import lombok.Data;

@Data
public class CardModel {
    
    private Long id;
    private String title;
    private String description;
    private BoardColumnModel column;
}
