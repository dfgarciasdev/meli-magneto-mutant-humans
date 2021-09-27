package se.daga.mutant.adapters.output.persistence.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import se.daga.mutant.adapters.output.persistence.entities.HumanEntity;

import java.util.Optional;

/**
 * Human repository {@link se.daga.mutant.adapters.output.persistence.HumanPersistenceAdapter}.
 *
 * @author davidgarcia
 */
public interface HumanRepository extends MongoRepository<HumanEntity, String> {
    Optional<HumanEntity> findByDna(String dna);
}
