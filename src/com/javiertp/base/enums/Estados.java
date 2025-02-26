package com.javiertp.base.enums;

public enum Estados {
    COMPLETADA("Completada"),
    FALLIDA("Fallida"),
    EN_PROGRESO("En progreso");

    private String value;

    Estados(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}