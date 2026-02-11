package services;

import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class TaskService {


    @ConfigProperty(name="app.name", defaultValue = "TaskManger")
    String name;

    @ConfigProperty(name="app.default-priority", defaultValue = "MEDIUM")
    String defaultPriority;


    private static final Map<Long, Map<String, Object>> tasks = new ConcurrentHashMap<>();
    private static final AtomicLong idGenerator = new AtomicLong(1);

    static {
        addTask("Découvrir Quarkus", "Installer et lancer le premier projet", "TODO", "HIGH");
        addTask("Apprendre JAX-RS", "Créer des endpoints REST", "IN_PROGRESS", "MEDIUM");
        addTask("Configurer le projet", "pom.xml et application.properties", "DONE", "LOW");
    }

    private static void addTask(String title, String description, String status, String priority) {
        long id = idGenerator.getAndIncrement();
        Map<String, Object> task = new LinkedHashMap<>();
        task.put("id", id);
        task.put("title", title);
        task.put("description", description);
        task.put("status", status);
        task.put("priority", priority);
        task.put("createdAt", LocalDateTime.now().toString());
        tasks.put(id, task);
    }


    public List<Map<String, Object>> getTask() {
        Log.infof("[%s] Récupération de %d tâches", name, tasks.size());
        return new ArrayList<>(tasks.values());
    }





}
