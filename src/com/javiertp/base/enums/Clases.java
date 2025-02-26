package com.javiertp.base.enums;

public enum Clases {
    EXPLORACION("Exploración"),
    COMBATE("Combate"),
    TRANSPORTE("Transporte");

    private String value;

    Clases(String value) {
        this.value = value;
    }

    public String getValue() {
        return this.value;
    }
}