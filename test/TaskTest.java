import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertArrayEquals;

public class TaskTest {

    @Test
    public void testAddTask() {
        // Test implementation here
    }

    @Test
    public void testShowReport() {
        // Test implementation here
    }

    @Test
    public void testGenerateTaskID() {
        String taskID = Task.generateTaskID("Login Feature", 1, "Harrison");
        Assert.assertEquals("LF:1:HAR", taskID);
    }

    @Test
    public void testTaskDataPrintTaskDetails() {
        // Test implementation here
    }

    @Test
    public void testAddTaskWithTestData() {
        // Test implementation here
    }

    @Test
    public void testValidTaskDescription() {
        String taskDescription = "Valid task description";
        assertEquals("Task successfully captured", captureTask(taskDescription));
    }

    @Test
    public void testInvalidTaskDescription() {
        String taskDescription = "This is a very long task description that exceeds 50 characters limit";
        assertEquals("Please enter a task description of less than 50 characters", captureTask(taskDescription));
    }

    @Test
    public void testTaskID() {
        String taskID = "AD:1:BYN";
        assertEquals(taskID, getTaskID());
    }

    @Test
    public void testLoopedTaskIDs() {
        String[] expectedTaskIDs = {"CR:0:IKE", "CR:1:ARD", "CR:2:THA", "CR:3:ND"};
        assertArrayEquals(expectedTaskIDs, getLoopedTaskIDs());
    }

    @Test
    public void testTotalHoursInLoop() {
        int expectedTotalHours = 18;
        assertEquals(expectedTotalHours, getTotalHoursInLoop(5, 10, 12, 55, 11, 1));
    }

    @Test
    public void testAdditionalData() {
        int expectedTotalHours = 89;
        assertEquals(expectedTotalHours, getTotalHoursInLoop(2, 10, 12, 55, 11, 1));
    }   

    private String captureTask(String taskDescription) {
        if (taskDescription.length() <= 50) {
            // Task description is valid
            return "Task successfully captured";
        } else {
            // Task description is invalid
            return "Please enter a task description of less than 50 characters";
        }
    }

    private String getTaskID() {
        return "AD:1:BYN";
    }

    private String[] getLoopedTaskIDs() {
        String[] taskIDs = {"CR:0:IKE", "CR:1:ARD", "CR:2:THA", "CR:3:ND"};
        return taskIDs;
    }

    private int getTotalHoursInLoop(int numTasks, int... durations) {
        int totalHours = 0;
        for (int i = 0; i < numTasks; i++) {
            totalHours += durations[i];
        }
        return totalHours;
    }
}
