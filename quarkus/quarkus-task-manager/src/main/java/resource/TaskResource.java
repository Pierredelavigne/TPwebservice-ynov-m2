package resource;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Path("/api/tasks")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TaskResource {


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


    @GET
    public List<Map<String, Object>> getTasks() {
        return new ArrayList<>(tasks.values());

    }



}
