package com.DevSync.Enums;

public enum Status {
    ENATTENTE("En attente"),
    ENCOURS("En cours"),
    TERMINE("Termine");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static Status fromDBValue(String value) {
        for (Status status : values())
            if (status.getStatus().equals(value))
                return status;
        throw new IllegalArgumentException("Unknown database value: " + value);
    }
}
