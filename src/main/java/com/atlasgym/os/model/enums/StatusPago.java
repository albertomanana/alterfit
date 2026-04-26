package com.atlasgym.os.model.enums;

public enum StatusPago {
    PENDIENTE("Pendiente"),
    PAGADO("Pagado"),
    VENCIDO("Vencido"),
    CANCELADO("Cancelado");

    private final String display;

    StatusPago(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
