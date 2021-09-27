package se.daga.mutant.adapters.output.persistence.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Human entity.
 *
 * @author davidgarcia
 */
@Document(collection = "humans")
public class HumanEntity {

    @Id
    private String id;

    @Version
    private Integer version;

    @Indexed(unique = true)
    private String dna;

    private boolean mutant;

    public HumanEntity() {
    }

    public HumanEntity(String dna, boolean mutant) {
        this.dna = dna;
        this.mutant = mutant;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getDna() {
        return dna;
    }

    public void setDna(String dna) {
        this.dna = dna;
    }

    public boolean isMutant() {
        return mutant;
    }

    public void setMutant(boolean mutant) {
        this.mutant = mutant;
    }
}
