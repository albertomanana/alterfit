package com.atlasgym.os.model.enums;

public enum AccessProfile {
    ADMIN("Administrador"),
    STAFF_RECEPCION("Staff Recepción"),
    STAFF_ENTRENADOR("Staff Entrenador"),
    STAFF_GERENTE("Gerente"),
    CLIENTE("Cliente");

    private final String display;

    AccessProfile(String display) {
        this.display = display;
    }

    public String getDisplay() {
        return display;
    }
}
