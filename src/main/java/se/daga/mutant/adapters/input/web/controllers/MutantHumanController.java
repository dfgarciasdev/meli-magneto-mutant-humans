package se.daga.mutant.adapters.input.web.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import se.daga.mutant.adapters.input.web.models.MutantHumanModel;
import se.daga.mutant.application.ports.in.MutantHumanUseCase;

import javax.validation.Valid;

/**
 * Mutant human controller (web adapter).
 *
 * @author davidgarcia
 */
@Component
@RestController
public class MutantHumanController {
    private final MutantHumanUseCase mutantHumanUseCase;

    public MutantHumanController(MutantHumanUseCase mutantHumanUseCase) {
        this.mutantHumanUseCase = mutantHumanUseCase;
    }

    /**
     * Validate mutant human resource $HOST:$PORT/mutant
     *
     * @param mutantHumanModel request body.
     * @return ResponseEntity<Void></>
     */

    @PostMapping(value = "/mutant", consumes = "application/json")
    public ResponseEntity<Void> validateMutantHuman(@RequestBody @Valid MutantHumanModel mutantHumanModel) {
        var isMutant = mutantHumanUseCase.validateMutantHuman(mutantHumanModel.getDna());
        return ResponseEntity.status(isMutant ? HttpStatus.OK : HttpStatus.FORBIDDEN).build();
    }
}
