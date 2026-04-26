package com.atlasgym.os.model.enums;

public enum StatusMembresia {
    ACTIVA("Activa"),
    EXPIRADA("Expirada"),
    CANCELADA("Cancelada"),
    PENDIENTE_PAGO("Pendiente de Pago");

    private final String display;

    StatusMembresia(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
