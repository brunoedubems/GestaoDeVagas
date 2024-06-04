package br.com.brunoedubems4.gestao_vagas.modules.candidate.useCases;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.brunoedubems4.gestao_vagas.exceptions.UserFoundException;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateRepository;

@Component
public class CreateCandidateUseCase {

    @Autowired
    private CandidateRepository candidateRespository; 
    
    public CandidateEntity execute(CandidateEntity candidateEntity){
            this.candidateRespository
       .findByUsernameOrEmail(candidateEntity.getUsername(), candidateEntity.getEmail())
       .ifPresent(( user) -> {
        // throw new Exception("Usuário já existe");
         throw new UserFoundException();
       });
       
        return this.candidateRespository.save(candidateEntity);

    }
}
