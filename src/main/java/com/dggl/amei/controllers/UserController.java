package com.dggl.amei.controllers;

import com.dggl.amei.dtos.requests.UserEnderecoRequestDTO;
import com.dggl.amei.dtos.requests.UserGeralRequestDTO;
import com.dggl.amei.dtos.responses.UserResponseDTO;
import com.dggl.amei.models.User;
import com.dggl.amei.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class    UserController extends AbstractController {

    @Autowired
    UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable Long id) {
        var user = userService.getUserData(id);

        var dto = UserResponseDTO.fromEntity(user);

        return ResponseEntity.ok(dto);
    }

    @PutMapping("geral/{id}")
    public ResponseEntity<UserResponseDTO> updateUserInfosGerais(@PathVariable Long id, @RequestBody UserGeralRequestDTO dto)
    {
        var user = userService.updateInfosGerais(id, dto);

        var responseDTO = UserResponseDTO.fromEntity(user);

        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("endereco/{id}")
    public ResponseEntity<UserResponseDTO> updateUserEndereco (@PathVariable Long id, @RequestBody UserEnderecoRequestDTO dto) {
        var user = userService.updateEndereco(id, dto);

        var responseDTO = UserResponseDTO.fromEntity(user);

        return ResponseEntity.ok(responseDTO);
    }

}
