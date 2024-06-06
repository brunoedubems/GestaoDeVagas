package br.com.brunoedubems4.gestao_vagas.modules.company.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name="job")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class JobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String description;
    private String benefits;
    
    @NotBlank(message = "Esse Campo é obrigatório.")
    private String level;

    @ManyToOne()
    @JoinColumn(name = "company_Id", insertable = false, updatable = false)
    private CompanyEntity companyEntity;
    
    @Column(name="company_Id", nullable = false) //coloca o nome da coluna no banco de dados
    private UUID companyId;

     @CreationTimestamp
    private LocalDateTime createdAt;

}
