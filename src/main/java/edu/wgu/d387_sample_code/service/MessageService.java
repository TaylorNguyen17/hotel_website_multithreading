package edu.wgu.d387_sample_code.service;

import org.springframework.stereotype.Service;
import org.springframework.core.io.ClassPathResource;
import java.io.InputStream;
import java.util.*;
import java.util.concurrent.*;

@Service
public class MessageService {

    private static ExecutorService messageExecutor = Executors.newFixedThreadPool(4);

    public List<String> getMessages() throws Exception {
        Properties propertiesEn = new Properties();
        Properties propertiesFr = new Properties();
        List<Callable<String>> tasks = new ArrayList<>();

        Callable<String> taskEn1 = () -> {
            try (InputStream stream = new ClassPathResource("translation_en_CA.properties").getInputStream()) {
                propertiesEn.load(stream);
                return "Thread 1: " + propertiesEn.getProperty("welcome");
            }
        };

        Callable<String> taskEn2 = () -> {
            try (InputStream stream = new ClassPathResource("translation_en_CA.properties").getInputStream()) {
                propertiesEn.load(stream);
                return "Thread 3: " + propertiesEn.getProperty("welcome");
            }
        };

        Callable<String> taskFr1 = () -> {
            try (InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream()) {
                propertiesFr.load(stream);
                return "Thread 2: " + propertiesFr.getProperty("welcome");
            }
        };

        Callable<String> taskFr2 = () -> {
            try (InputStream stream = new ClassPathResource("translation_fr_CA.properties").getInputStream()) {
                propertiesFr.load(stream);
                return "Thread 4: " + propertiesFr.getProperty("welcome");
            }
        };

        tasks.add(taskEn1);
        tasks.add(taskEn2);
        tasks.add(taskFr1);
        tasks.add(taskFr2);

        CompletionService<String> completionService = new ExecutorCompletionService<>(messageExecutor);
        for (Callable<String> task : tasks) {
            completionService.submit(task);
        }

        List<String> messages = new ArrayList<>();
        for (int i = 0; i < tasks.size(); i++) {
            Future<String> future = completionService.take();
            messages.add(future.get());
        }

        return messages;
    }
}