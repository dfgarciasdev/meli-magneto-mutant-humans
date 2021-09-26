package se.daga.mutant.adapters.output.persistence.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import se.daga.mutant.adapters.output.persistence.entities.HumanEntity;
import se.daga.mutant.application.models.SaveHumanModel;

/**
 * Db operations mapper {@link se.daga.mutant.adapters.output.persistence.HumanPersistenceAdapter}.
 *
 * @author davidgarcia
 */
@Mapper(componentModel = "spring")
public interface HumanMapper {

    @Mappings({
            @Mapping(target = "id", ignore = true),
            @Mapping(target = "version", ignore = true)
    })
    HumanEntity modelToEntity(SaveHumanModel model);
}
