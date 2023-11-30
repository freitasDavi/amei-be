package com.dggl.amei.controllers;

import com.dggl.amei.configuration.security.jwt.JwtUtils;
import com.dggl.amei.dtos.requests.LoginRequest;
import com.dggl.amei.dtos.requests.SignupRequest;
import com.dggl.amei.dtos.requests.TokenRefreshRequest;
import com.dggl.amei.dtos.responses.MessageResponse;
import com.dggl.amei.dtos.responses.RefreshTokenResponse;
import com.dggl.amei.models.RefreshToken;
import com.dggl.amei.services.RefreshTokenService;
import com.dggl.amei.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController extends AbstractController {

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    UserService userService;


    @PostMapping("/signin")
    @Operation(summary = "Login a user")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Login successful"),
            }
    )
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            var tokens = userService.gerarToken(loginRequest.getUsername(), loginRequest.getPassword());

            return ResponseEntity.ok(tokens);
        } catch (Exception ex) {
            return new ResponseEntity<>(new MessageResponse("Error: " + ex.getMessage()), null, 401);
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser (@RequestBody SignupRequest signupRequest) {
        try {
            userService.usuarioJaExiste(signupRequest.getUsername(), signupRequest.getUsername());

            var cnpjExiste = userService.validaCnpj(signupRequest.getCnpj());

            if (!cnpjExiste) return ResponseEntity.badRequest().body(new MessageResponse("CNPJ Inválido"));

            var novoUser = userService.criarNovoUsuario(signupRequest);

            var tokens = userService.gerarToken(novoUser.getUsername(), signupRequest.getPassword());

            //var response = new MessageWithBodyResponse<JwtResponse>("Usuário cadastrado com sucesso", tokens);

            return ResponseEntity.ok(tokens);
        } catch (Exception ex) {
                return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshToken (@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        return refreshTokenService.findByToken(requestRefreshToken)
                .map(refreshTokenService::verifyExpiration)
                .map(RefreshToken::getUser)
                .map(user -> {
                    String token = jwtUtils.generateTokenFromUsername(user.getUsername());
                    return ResponseEntity.ok(new RefreshTokenResponse(token, requestRefreshToken));
                })
                .orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
    }
}
