package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.SignupRequest;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.List;

class AuthControllerTest {

    @Test
    void authenticateUser() {
        SignupRequest user = new SignupRequest(
                "testeuser",
                "test@test.com",
                new HashSet<String>(List.of("ROLE_ADMIN")),
                "test123"
        );
    }

    @Test
    void registerUser() {
    }
}