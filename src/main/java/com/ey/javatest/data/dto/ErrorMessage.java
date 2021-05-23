package com.ey.javatest.data.dto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ErrorMessage {

    private List<String> mensaje;

    public ErrorMessage() {
    }

    public ErrorMessage(List<String> mensaje) {
        this.mensaje = mensaje;
    }

    public ErrorMessage(String mensaje) {
        this(Collections.singletonList(mensaje));
    }

    public ErrorMessage(String ... mensaje) {
        this(Arrays.asList(mensaje));
    }

    public List<String> getMensaje() {
        return mensaje;
    }

    public void setMensaje(List<String> mensaje) {
        this.mensaje = mensaje;
    }
}