package br.edu.ifsp.manhani.massoterapia.controller;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsp.manhani.massoterapia.dto.JwtAuthenticationDTO;
import br.edu.ifsp.manhani.massoterapia.dto.LoginDTO;
import br.edu.ifsp.manhani.massoterapia.exception.BusinessException;
import br.edu.ifsp.manhani.massoterapia.messages.MessageProperties;
import br.edu.ifsp.manhani.massoterapia.util.JwtTokenProvider;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "Autenticação")
@RestController
@RequestMapping(path = "/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @ApiOperation("Operação de login")
    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationDTO> login(@RequestBody @Valid LoginDTO loginRequest) {
        if (StringUtils.isEmpty(loginRequest.getUsername()) || StringUtils.isEmpty(loginRequest.getPassword())) {
            throw new BusinessException(MessageProperties.MSG0001);
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        String token = tokenProvider.generateToken(authentication, Collections.emptyList());
        String refreshToken = tokenProvider.generateRefreshToken(authentication, Collections.emptyList());
        return ResponseEntity.ok(new JwtAuthenticationDTO(token, refreshToken));
    }

    @ApiOperation("Gera novo token com base no refreshToken.")
    @GetMapping("/refreshtoken")
    public ResponseEntity<JwtAuthenticationDTO> refreshToken(HttpServletRequest request) {
        String newToken = tokenProvider.generateToken(request);
        String newRefreshToken = tokenProvider.generateRefreshToken(request);
        return ResponseEntity.ok(new JwtAuthenticationDTO(newToken, newRefreshToken));
    }
}
