package com.DevSync.Enums;

public enum Request_type {
    DELETE("delete"),
    UPDATE("update");

    private final String type;

    Request_type(String type) {this.type = type;}

    public String getType() {return type;}

    public static Request_type fromDBValue(String value) {
        for (Request_type type : values())
            if (type.getType().equals(value))
                return type;
        throw new IllegalArgumentException("Unknown database value: " + value);
    }
}
