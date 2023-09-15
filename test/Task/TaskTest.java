package Task;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class TaskTest {
    private static final List<Task> tasks = new ArrayList<>();

    @Test
    public void testCreateTask() {
        // Create tasks and add them to the list
        Task.createTask(tasks, "Mike", "Smith", "Create Login", "Task 1 description", 5, TaskStatus.TODO);
        Task.createTask(tasks, "Edward", "Harrison", "Create Add Features", "Task 2 description", 8, TaskStatus.DOING);
        Task.createTask(tasks, "Samantha", "Paulson", "Create Reports", "Task 3 description", 2, TaskStatus.DONE);
        Task.createTask(tasks, "Glenda", "Oberholzer", "Add Arrays", "Task 4 description", 11, TaskStatus.TODO);

        int expectedTaskCount = 4;
        int actualTaskCount = tasks.size();

        Assert.assertEquals(expectedTaskCount, actualTaskCount);
    }

    @Test
    public void testDisplayTasksWithStatus() {
        // Define the expected output
        String expectedOutput = "Tasks with status DONE:\n" +
                "Samantha Paulson - Create Reports, Duration: 2 hours";
        // Capture the actual output
        String actualOutput = captureUserOutput(() -> Task.displayTasksWithStatus(tasks, TaskStatus.DONE));

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDisplayTaskWithLongestDuration() {
        // Define the expected output
        String expectedOutput = "Task with longest duration:\n" +
                "Developer: Glenda Oberholzer, Duration: 11 hours";
        // Capture the actual output
        String actualOutput = captureUserOutput(() -> Task.displayTaskWithLongestDuration(tasks));

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSearchTaskByName() {
        // Define the expected output
        String expectedOutput = "Task details:\n" +
                "Task Name: Create Add Features\n" +
                "Developer: Edward Harrison\n" +
                "Task Status: DOING";
        // Capture the actual output
        String actualOutput = captureUserOutput(() -> Task.searchTaskByName(tasks, "Create Add Features"));

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testSearchTasksByDeveloper() {
        // Define the expected output
        String expectedOutput = "Tasks assigned to developer Paulson:\n" +
                "Task Name: Create Reports, Task Status: DONE";
        // Capture the actual output
        String actualOutput = captureUserOutput(() -> Task.searchTasksByDeveloper(tasks, "Paulson"));

        Assert.assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testDeleteTask() {
        // Delete a task from the list
        Task.deleteTask(tasks, "Create Login");

        int expectedTaskCount = 3;
        int actualTaskCount = tasks.size();

        Assert.assertEquals(expectedTaskCount, actualTaskCount);
    }

    @Test
public void testDisplayTaskReport() {
    // Define the expected output
    String expectedOutput = "Task Report:\n" +
            "Task ID: 1\n" +
            "Task Name: Create Login\n" +
            "Developer: Mike Smith\n" +
            "Task Description: Task 1 description\n" +
            "Task Duration: 5 hours\n" +
            "Task Status: TODO\n\n" +
            "Task ID: 2\n" +
            "Task Name: Create Add Features\n" +
            "Developer: Edward Harrison\n" +
            "Task Description: Task 2 description\n" +
            "Task Duration: 8 hours\n" +
            "Task Status: DOING\n\n" +
            "Task ID: 3\n" +
            "Task Name: Create Reports\n" +
            "Developer: Samantha Paulson\n" +
            "Task Description: Task 3 description\n" +
            "Task Duration: 2 hours\n" +
            "Task Status: DONE\n\n" +
            "Task ID: 4\n" +
            "Task Name: Add Arrays\n" +
            "Developer: Glenda Oberholzer\n" +
            "Task Description: Task 4 description\n" +
            "Task Duration: 11 hours\n" +
            "Task Status: TODO";
    // Capture the actual output
    String actualOutput = captureUserOutput(Task::displayTaskReport);

    Assert.assertEquals(expectedOutput, actualOutput);
}


    // Helper method to capture the output printed to the console
    private String captureUserOutput(Runnable runnable) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(outputStream);
        PrintStream originalPrintStream = System.out;
        System.setOut(printStream);

        runnable.run();

        System.out.flush();
        System.setOut(originalPrintStream);

        return outputStream.toString().trim();
    }
}
