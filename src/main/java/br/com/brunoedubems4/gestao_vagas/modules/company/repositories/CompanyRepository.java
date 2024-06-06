package br.com.brunoedubems4.gestao_vagas.modules.company.repositories;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.brunoedubems4.gestao_vagas.modules.company.entities.CompanyEntity;
                                                  
@Repository                                         // ENTIDADE, TIPO DE ID
public interface CompanyRepository extends JpaRepository<CompanyEntity, UUID> {
   Optional<CompanyEntity> findByUsernameOrEmail(String username, String email);
   Optional<CompanyEntity> findByUsername(String username);

}
