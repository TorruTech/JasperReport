package com.javiertp.base.enums;

public enum Rangos {
    CAPITAN("Capitán"),
    INGENIERO("Ingeniero"),
    PILOTO("Piloto");

    private String value;

    Rangos(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}