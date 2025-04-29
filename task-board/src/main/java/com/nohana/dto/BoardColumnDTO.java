package com.nohana.dto;

import com.nohana.model.BoardColumnTypeEnum;

public record BoardColumnDTO(Long id, String name, BoardColumnTypeEnum kind, int amount) {
    
}
