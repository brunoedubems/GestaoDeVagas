package br.com.brunoedubems4.gestao_vagas.modules.company.useCases;

import java.time.Duration;
import java.time.Instant;

import javax.security.sasl.AuthenticationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import br.com.brunoedubems4.gestao_vagas.modules.company.dto.AuthCompanyDTO;
import br.com.brunoedubems4.gestao_vagas.modules.company.repositories.CompanyRepository;

@Service
public class AuthCompanyUseCase {

    @Value("security.token.secret")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;


    public String execute(AuthCompanyDTO authCompanyDTO) throws AuthenticationException{

        var company = this.companyRepository.findByUsername(authCompanyDTO.getUsername()).orElseThrow(
            () ->{
                throw new UsernameNotFoundException("Username(company) não existe");
            }
        );

            //verificar a senha são iguais
            var passwordMatches = this.passwordEncoder.matches(authCompanyDTO.getPassword(), company.getPassword());
             
            //se nao for igual -> erro
            if(!passwordMatches){
                throw new AuthenticationException();
            }
                // se for igual -> gerar o token
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            var token = JWT.create().withIssuer("javagas")
             .withExpiresAt(Instant.now().plus(Duration.ofHours(2)))
            .withSubject(company.getId().toString())
            .sign(algorithm);

            return token;
    }
}
