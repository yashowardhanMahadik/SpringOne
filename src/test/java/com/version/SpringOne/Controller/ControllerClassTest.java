package com.version.SpringOne.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class ControllerClassTest {

    @Test
    void sendHello() {
        ControllerClass cla = new ControllerClass();
        String response = cla.sendHello("hello");
        assertEquals("hello",response);
        assertThat(response).isEqualTo("hello");
    }
}