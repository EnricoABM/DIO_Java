package com.nohana.model;

import java.time.OffsetDateTime;

import lombok.Data;

@Data
public class BlockModel {
    
    private Long id;
    private OffsetDateTime blockedAt;
    private String blockReason;
    private OffsetDateTime unblockedAt;
    private String unblockedReason;
    private CardModel card;
     
}
