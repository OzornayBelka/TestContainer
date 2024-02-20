package ru.netology.hw_springboot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class HwSpringBootApplicationTests {

    @Autowired
    TestRestTemplate restTemplate;
    @Container
    private GenericContainer<?> myAppDev = new GenericContainer<>
            ("devapp:latest").withExposedPorts(8080);
    @Container
    private GenericContainer<?> myAppProd = new GenericContainer<>
            ("prodapp:latest").withExposedPorts(8081);

    @Test
    void contextLoadsDev() {

        String devTest = "Current profile is dev";
        ResponseEntity<String> forEntity = restTemplate.getForEntity
                ("http://localhost:" + myAppDev.getMappedPort(8080) + "/profile", String.class);

        Assertions.assertEquals(devTest, forEntity.getBody());


    }

    @Test
    void contextLoadsProd() {

        String prodTest = "Current profile is production";
        ResponseEntity<String> forEntity = restTemplate.getForEntity
                ("http://localhost:" + myAppProd.getMappedPort(8081) + "/profile", String.class);
        Assertions.assertEquals(prodTest, forEntity.getBody());


    }

}
