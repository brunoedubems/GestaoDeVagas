package br.com.brunoedubems4.gestao_vagas.modules.candidate.useCases;

import java.time.Instant;
import java.util.Arrays;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.dto.AuthCandidateRequestDTO;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.dto.AuthCandidateResponseDTO;

@Service
public class AuthCandidateUseCase {

    @Value("${security.token.secret.candidate}")
    private String secretKey;

    @Autowired
    private CandidateRepository candidateRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    

    public AuthCandidateResponseDTO execute(AuthCandidateRequestDTO authCandidateRequestDTO) throws AuthenticationException{
    var candidate = this.candidateRepository.findByUsername(authCandidateRequestDTO.username())
    .orElseThrow(() ->{
    throw new UsernameNotFoundException("usuario/senha incorreto");
    });

    var passwordEncoder = this.passwordEncoder
    .matches(authCandidateRequestDTO.password(), candidate.getPassword());

    if(!passwordEncoder){
        throw new AuthenticationException();
    }

    Algorithm algorithm = Algorithm.HMAC256(secretKey);
    var expriresIn = Instant.now().plus(java.time.Duration.ofMinutes(10));
    var token = JWT.create()
    .withIssuer("javagas")
    .withSubject(candidate.getId().toString())
    .withClaim("roles", Arrays.asList("candidate"))
    .withExpiresAt(expriresIn)
    .sign(algorithm);


    var authCandidateResponseDTO =AuthCandidateResponseDTO.builder()
    .acess_token(token)
    .expires_in(expriresIn.toEpochMilli())
    .build();

    return authCandidateResponseDTO;
    }
}
