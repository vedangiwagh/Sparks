package com.planning.mealsandrecipes;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConcurrencyTests {
    public ConcurrencyTests() {
        // Constructor logic, if needed
    }
    @LocalServerPort
    private int port;

    RestTemplate restTemplate = new RestTemplate();


    @Test
    public void testConcurrentGetMealPlanRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.getForObject("http://localhost:" + port + "/meals/Main Course/Vegetarian", String.class));
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            // Add assertions or validations on the response
//            System.out.println("tests");
            String response = future.get();
            assertNotNull(response);
        }

        executorService.shutdown();
    }

    @Test
    public void testConcurrentGetIngredientsRequests() throws InterruptedException, ExecutionException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        List<Callable<String>> tasks = new ArrayList<>();

        for (int i = 0; i < numberOfThreads; i++) {
            tasks.add(() -> restTemplate.getForObject("http://localhost:" + port + "/ingredients", String.class));
        }

        List<Future<String>> futures = executorService.invokeAll(tasks);

        for (Future<String> future : futures) {
            // Add assertions or validations on the response
//            System.out.println("tests");
            String response = future.get();
            assertNotNull(response);
        }

        executorService.shutdown();
    }

}
