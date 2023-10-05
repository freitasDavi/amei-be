package com.dggl.amei.services;

import com.dggl.amei.configuration.security.jwt.JwtUtils;
import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.SignupRequest;
import com.dggl.amei.dtos.responses.JwtResponse;
import com.dggl.amei.dtos.responses.MessageResponse;
import com.dggl.amei.models.RefreshToken;
import com.dggl.amei.models.Role;
import com.dggl.amei.models.User;
import com.dggl.amei.models.enums.EnumRole;
import com.dggl.amei.repositories.RoleRepository;
import com.dggl.amei.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RefreshTokenService refreshTokenService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;


    public JwtResponse gerarToken (String username, String password) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

        return new JwtResponse(jwt,
                refreshToken.getToken(),
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public User criarNovoUsuario (SignupRequest signupRequest) {
        Set<Role> roles = recuperarRoles(signupRequest.getRole());
        User user = mapearNovoUsuario(signupRequest, roles);

        userRepository.save(user);

        return user;
    }

    public void usuarioJaExiste (String username, String email) throws Exception {
        if (userRepository.existsByUsername(username)) throw new Exception("Erro: Usuário já existe");

        if (userRepository.existsByEmail(email))  throw new Exception("Erro: O email já está em uso");
    }

    private Set<Role> recuperarRoles (Set<String> signUpRequestRoles) {
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

        if (signUpRequestRoles == null) {
            Role userRole = roleRepository.findByName(EnumRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            signUpRequestRoles.forEach(role -> {
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

    private User mapearNovoUsuario (SignupRequest signupRequest, Set<Role> roles) {
        return new User(signupRequest.getUsername(),
                signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()),
                roles,
                signupRequest.getRazaoSocial(),
                signupRequest.getCnpj(),
                signupRequest.getInscricaoMunicipal(),
                signupRequest.getTelefoneUsuario(),
                signupRequest.getCpeUsuario(),
                signupRequest.getLogradouroUsuario(),
                signupRequest.getNumeroUsuario(),
                signupRequest.getComplementoUsuario(),
                signupRequest.getCidadeUsuario(),
                signupRequest.getBairroUsuario()
        );

    }
}
