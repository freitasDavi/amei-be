package com.dggl.amei.services;

import com.dggl.amei.configuration.security.jwt.JwtUtils;
import com.dggl.amei.configuration.security.services.UserDetailsImpl;
import com.dggl.amei.dtos.requests.CNPJValidadoDTO;
import com.dggl.amei.dtos.requests.SignupRequest;
import com.dggl.amei.dtos.requests.UserEnderecoRequestDTO;
import com.dggl.amei.dtos.requests.UserGeralRequestDTO;
import com.dggl.amei.dtos.responses.JwtResponse;
import com.dggl.amei.exceptions.RecursoNaoEncontrado;
import com.dggl.amei.models.RefreshToken;
import com.dggl.amei.models.Role;
import com.dggl.amei.models.User;
import com.dggl.amei.models.enums.EnumRole;
import com.dggl.amei.repositories.RoleRepository;
import com.dggl.amei.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
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

    public User getUserData (Long id) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) throw new RecursoNaoEncontrado("Usuário", id);

        return user.get();
    }

    public User updateInfosGerais (Long id, UserGeralRequestDTO dto) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) throw new RecursoNaoEncontrado("Usuário", id);

        var userFound = user.get();

        dto.toEntity(userFound);

        userRepository.save(userFound);

        return userFound;
    }

    public User updateEndereco (Long id, UserEnderecoRequestDTO dto) {
        var user = userRepository.findById(id);

        if (user.isEmpty()) throw new RecursoNaoEncontrado("Usuário", id);

        var userFound = user.get();

        dto.toEntity(userFound);

        userRepository.save(userFound);

        return userFound;
    }

    public boolean validaCnpj(String cnpj) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            String uri = "https://publica.cnpj.ws/cnpj/"+cnpj;

            HttpHeaders headers = new HttpHeaders();
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");

            HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
            ResponseEntity<?> result =
                    restTemplate.exchange(uri, HttpMethod.GET, entity, CNPJValidadoDTO.class);

            CNPJValidadoDTO body = (CNPJValidadoDTO) result.getBody();

            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}
