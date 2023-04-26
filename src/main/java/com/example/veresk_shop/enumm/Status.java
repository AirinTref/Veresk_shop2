package com.example.veresk_shop.enumm;

public enum Status {
    NEW("Новый"), // новый
    ACCEPTED("В работе"), // принятый
    REJECTED("Отклонен"), // отклоненный
    FINISHED("Завершен") // завершенный
    ;

    private final String displayValue;

    Status(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getDisplayValue() {
        return displayValue;
    }
}

