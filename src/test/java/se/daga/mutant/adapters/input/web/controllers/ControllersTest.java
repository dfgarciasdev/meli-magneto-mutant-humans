package se.daga.mutant.adapters.input.web.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import se.daga.mutant.adapters.input.web.models.MutantHumanModel;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.MediaType.APPLICATION_JSON;

/**
 * Controllers test.
 *
 * @author davidgarcia
 */
@SpringBootTest(webEnvironment = RANDOM_PORT)
class ControllersTest {
    @Autowired
    private WebTestClient client;

    @Test
    void isMutant() {
        var mutantRequest = new MutantHumanModel();
        mutantRequest.setDna(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATGT",
                "AGAAGG",
                "CCCCTA",
                "TCACTG"});
        client.post()
                .uri("/mutant")
                .accept(APPLICATION_JSON)
                .bodyValue(mutantRequest)
                .exchange()
                .expectStatus().isOk();
    }


    @Test
    void noMutant() {
        var mutantRequest = new MutantHumanModel();
        mutantRequest.setDna(new String[]{
                "ATGCGA",
                "CAGTGC",
                "TTATTT",
                "AGACGG",
                "GCGTCA",
                "TCACTG"});
        client.post()
                .uri("/mutant")
                .accept(APPLICATION_JSON)
                .bodyValue(mutantRequest)
                .exchange()
                .expectStatus().isForbidden();
    }

    @Test
    void badRequestMutant() {
        var mutantRequest = new MutantHumanModel();
        mutantRequest.setDna(new String[]{});
        client.post()
                .uri("/mutant")
                .accept(APPLICATION_JSON)
                .bodyValue(mutantRequest)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    void internalErrorMutant() {
        var mutantRequest = new MutantHumanModel();
        mutantRequest.setDna(new String[]{"A", "B"});
        client.post()
                .uri("/mutant")
                .accept(APPLICATION_JSON)
                .bodyValue(mutantRequest)
                .exchange()
                .expectStatus().is5xxServerError();
    }


    @Test
    void stats() {
        client.get()
                .uri("/stats")
                .accept(APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(APPLICATION_JSON)
                .expectBody()
                .jsonPath("$.count_mutant_dna").isEqualTo(1L)
                .jsonPath("$.count_human_dna").isEqualTo(0L)
                .jsonPath("$.ratio").isEqualTo(0.0F);

    }
}