package com.nohana.dto;

import com.nohana.model.BoardColumnTypeEnum;

public record BoardColumnInfoDTO(Long id, int order, BoardColumnTypeEnum kind)  {

}
