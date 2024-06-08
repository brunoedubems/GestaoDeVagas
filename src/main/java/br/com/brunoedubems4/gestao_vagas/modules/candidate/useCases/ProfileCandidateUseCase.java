package br.com.brunoedubems4.gestao_vagas.modules.candidate.useCases;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.brunoedubems4.gestao_vagas.modules.candidate.CandidateRepository;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO;
import br.com.brunoedubems4.gestao_vagas.modules.candidate.dto.ProfileCandidateResponseDTO.ProfileCandidateResponseDTOBuilder;

@Service
public class ProfileCandidateUseCase {
    
    @Autowired
    private CandidateRepository candidateRepository;

    public ProfileCandidateResponseDTOBuilder execute(UUID idCandidate){
        var candidate = this.candidateRepository.findById(idCandidate)
        .orElseThrow(() -> {
            throw new UsernameNotFoundException("usuario não encontrado");
        });
        var candidateDTO = ProfileCandidateResponseDTO.builder()
        .description(candidate.getDescription())
        .username(candidate.getUsername())
        .email(candidate.getEmail())
        .name(candidate.getName())
        .id(candidate.getId());

        return candidateDTO;
    }
}
