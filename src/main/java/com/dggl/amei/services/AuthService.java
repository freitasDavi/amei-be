package com.dggl.amei.services;

import com.dggl.amei.dtos.requests.SignupRequest;
import com.dggl.amei.models.Role;
import com.dggl.amei.models.User;
import com.dggl.amei.models.enums.EnumRole;
import com.dggl.amei.repositories.RoleRepository;
import com.dggl.amei.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User registerUser(SignupRequest dto) throws Exception {
        if (userRepository.existsByUsername(dto.getUsername())) {
            throw new Exception("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(dto.getEmail())) {
            throw new Exception("Error: Email is already in use!");
        }

        var roles = this.ValidateRoles(dto);

        User user = new User(
                dto.getUsername(),
                dto.getEmail(),
                passwordEncoder.encode(dto.getPassword()),
                roles,
                dto.getRazaoSocial(),
                dto.getCnpj(),
                dto.getInscricaoMunicipal(),
                dto.getTelefoneUsuario(),
                dto.getCepUsuario(),
                dto.getEnderecoUsuario(),
                dto.getNumeroUsuario(),
                dto.getComplementoUsuario(),
                dto.getUsuarioCidade(),
                dto.getUsuarioBairro()
        );

        userRepository.save(user);

        return user;
    }

    private Set<Role> ValidateRoles (SignupRequest dto) {
        Set<String> strRoles = dto.getRoles();
        Set<Role> roles = new HashSet<>();

        List<Role> all = roleRepository.findAll();

        if (all.isEmpty()) {
            Role role = new Role();
            role.setName(EnumRole.ROLE_ADMIN);
            roleRepository.save(role);
            Role role2 = new Role();
            role2.setName(EnumRole.ROLE_USER);
            roleRepository.save(role2);
        }

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(EnumRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        return roles;
    }
}
