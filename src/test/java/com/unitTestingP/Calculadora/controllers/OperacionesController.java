package com.unitTestingP.Calculadora.controllers;

import com.unitTestingP.Calculadora.services.Operaciones;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnableAutoConfiguration(exclude = {
        DataSourceAutoConfiguration.class,
        DataSourceTransactionManagerAutoConfiguration.class,
        HibernateJpaAutoConfiguration.class})
@AutoConfigureWebTestClient
public class OperacionesController {

    @MockitoBean
    private Operaciones operacionesMock;

    @Autowired
    private WebTestClient webTestClient;

    @BeforeEach
    public void setup() {
        Mockito.when(
                operacionesMock
                        .factorial(ArgumentMatchers.anyInt()))
                .thenReturn(0);
    }

    @Test
    public void factorialControllerTest() {
        webTestClient.get()
                .uri("/factorial?numero=10")
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.mensaje")
                .isEqualTo("resultado: 0");
    }
}
