package com.nohana.model;

import java.util.stream.Stream;

public enum BoardColumnTypeEnum {
    INITIAL, FINAL, CANCEL, PENDING; 

    public static BoardColumnTypeEnum findByName(final String name) {
        return Stream.of(BoardColumnTypeEnum.values()).filter(e -> e.name().equalsIgnoreCase(name)).findFirst().orElseThrow();
    }
}
