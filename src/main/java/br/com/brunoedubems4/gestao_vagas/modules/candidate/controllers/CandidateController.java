package br.com.brunoedubems4.gestao_vagas.modules.candidate.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateEntity;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/candidate")

public class CandidateController {
    
    @Autowired
    private CandidateRepository candidaterespository;

    @PostMapping("/")
    public CandidateEntity create(@Valid @RequestBody CandidateEntity candidateEntity){
        return this.candidaterespository.save(candidateEntity);

    }
 
}
