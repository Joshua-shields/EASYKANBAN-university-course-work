package Task;

import javax.swing.*;
import java.util.ArrayList;

public class Task {
    private static ArrayList<Task> tasks = new ArrayList<>(); // List to store the tasks
    private static int totalHours = 0; // Variable to store the total hours across all tasks

    public static void main(String[] args) {
        while (true) {
            int choice = Integer.parseInt(JOptionPane.showInputDialog(
                    "Choose an option:\n1. Create Task\n2. Display Tasks with Status 'Done'\n3. Display Task with Longest Duration\n4. Search Task by Name\n5. Search Tasks by Developer\n6. Delete Task\n7. Display Task Report\n8. Display Total Hours\n9. Exit"));

            switch (choice) {
                case 1:
                    createTask(); // Option 1: Create a new task
                    break;
                case 2:
                    displayTasksWithStatus("DONE"); // Option 2: Display tasks with status 'DONE'
                    break;
                case 3:
                    displayTaskWithLongestDuration(); // Option 3: Display the task with the longest duration
                    break;
                case 4:
                    searchTaskByName(); // Option 4: Search for a task by name
                    break;
                case 5:
                    searchTasksByDeveloper(); // Option 5: Search for tasks by developer
                    break;
                case 6:
                    deleteTask(); // Option 6: Delete a task
                    break;
                case 7:
                    displayTaskReport(); // Option 7: Display a report of all tasks
                    break;
                case 8:
                    displayTotalHours(); // Option 8: Display total hours
                    break;
                case 9:
                    JOptionPane.showMessageDialog(null, "Goodbye!");
                    System.exit(0);
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Invalid choice!");
            }
        }
    }

    /**
     * Creates a new task and adds it to the tasks list.
     */
    public static void createTask() {
        String developerName = JOptionPane.showInputDialog(null, "Enter developer name:");
        String taskName = JOptionPane.showInputDialog(null, "Enter task name:");
        String taskDescription;
        boolean validDescription = false;

        do {
            taskDescription = JOptionPane.showInputDialog(null, "Enter task description (max 50 characters):");
            if (taskDescription.length() <= 50) {
                validDescription = true;
            } else {
                JOptionPane.showMessageDialog(null, "Task description must be less than 50 characters.");
            }
        } while (!validDescription);

        int taskDuration = Integer.parseInt(JOptionPane.showInputDialog(null, "Enter task duration (in hours):"));

        int taskStatus = 0;
        boolean validStatus = false;

        do {
            try {
                taskStatus = Integer.parseInt(JOptionPane.showInputDialog(null,
                        "Enter task status:\n1. TO DO\n2. DOING\n3. DONE"));
                if (taskStatus >= 1 && taskStatus <= 3) {
                    validStatus = true;
                } else {
                    throw new Exception();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, "Invalid option! Please enter a number from 1 to 3.");
            }
        } while (!validStatus);

        String taskID = generateTaskID(taskName, developerName);

        Task task = new Task(taskID, developerName, taskName, taskDescription, taskDuration, taskStatus);
        tasks.add(task);

        // Display task details
        String taskDetails = "Task Status: " + task.getTaskStatusText() + "\n"
                + "Developer Details: " + task.getDeveloperName() + "\n"
                + "Task Name: " + task.getTaskName() + "\n"
                + "Task Description: " + task.getTaskDescription() + "\n"
                + "Task Duration: " + task.getTaskDuration() + " hours\n"
                + "Task ID: " + task.getTaskID();

        JOptionPane.showMessageDialog(null, taskDetails);

        // Update total hours
        totalHours += taskDuration;
    }

    /**
     * Displays the total hours.
     */
    public static void displayTotalHours() {
        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
    }

    /**
     * Displays tasks with the specified status.
     *
     * @param status the status to filter the tasks
     */
    public static void displayTasksWithStatus(String status) {
        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            if (task.getTaskStatusText().equalsIgnoreCase(status)) {
                result.append(task.toString()).append("\n");
            }
        }

        if (result.length() > 0) {
            JOptionPane.showMessageDialog(null, result.toString(), "Tasks with Status '" + status + "'", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks with status '" + status + "' found.", "Tasks with Status '" + status + "'", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Displays the task with the longest duration.
     */
    public static void displayTaskWithLongestDuration() {
        Task longestTask = null;

        for (Task task : tasks) {
            if (longestTask == null || task.getTaskDuration() > longestTask.getTaskDuration()) {
                longestTask = task;
            }
        }

        if (longestTask != null) {
            JOptionPane.showMessageDialog(null, longestTask.toString(), "Task with Longest Duration", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.", "Task with Longest Duration", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Searches for a task by name.
     */
    public static void searchTaskByName() {
        String searchName = JOptionPane.showInputDialog(null, "Enter task name to search:");

        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            if (task.getTaskName().equalsIgnoreCase(searchName)) {
                result.append(task.toString()).append("\n");
            }
        }

        if (result.length() > 0) {
            JOptionPane.showMessageDialog(null, result.toString(), "Search Results for Task Name: " + searchName, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks with name '" + searchName + "' found.", "Search Results for Task Name: " + searchName, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Searches for tasks by developer.
     */
    public static void searchTasksByDeveloper() {
        String searchDeveloper = JOptionPane.showInputDialog(null, "Enter developer name to search:");

        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            if (task.getDeveloperName().equalsIgnoreCase(searchDeveloper)) {
                result.append(task.toString()).append("\n");
            }
        }

        if (result.length() > 0) {
            JOptionPane.showMessageDialog(null, result.toString(), "Search Results for Developer: " + searchDeveloper, JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks assigned to developer '" + searchDeveloper + "' found.", "Search Results for Developer: " + searchDeveloper, JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Deletes a task from the tasks list.
     */
    /**
 * Deletes a task from the tasks list.
 */
public static void deleteTask() {
    String taskName = JOptionPane.showInputDialog(null, "Enter task name to delete:");

    boolean found = false;

    for (int i = 0; i < tasks.size(); i++) {
        Task task = tasks.get(i);
        if (task.getTaskName().equalsIgnoreCase(taskName)) {
            tasks.remove(i);
            found = true;
            JOptionPane.showMessageDialog(null, "Task with name '" + taskName + "' deleted successfully.", "Task Deletion", JOptionPane.INFORMATION_MESSAGE);
            break;
        }
    }

    if (!found) {
        JOptionPane.showMessageDialog(null, "No tasks with name '" + taskName + "' found.", "Task Deletion", JOptionPane.INFORMATION_MESSAGE);
    }
}


    /**
     * Displays a report of all tasks.
     */
    public static void displayTaskReport() {
        StringBuilder report = new StringBuilder();

        for (Task task : tasks) {
            report.append(task.toString()).append("\n");
        }

        if (report.length() > 0) {
            JOptionPane.showMessageDialog(null, report.toString(), "Task Report", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No tasks found.", "Task Report", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    /**
     * Generates a unique task ID based on the task name and developer name.
     *
     * @param taskName      the task name
     * @param developerName the developer name
     * @return the generated task ID
     */
    public static String generateTaskID(String taskName, String developerName) {
    String taskNamePrefix = taskName.substring(0, Math.min(taskName.length(), 2)).toUpperCase();
    String developerNameSuffix = developerName.substring(Math.max(0, developerName.length() - 3)).toUpperCase();

    int taskNumber = 0;
    for (Task task : tasks) {
        if (task.getTaskName().equals(taskName)) {
            taskNumber++;
        }
    }

    String taskID = taskNamePrefix + ":" + taskNumber + ":" + developerNameSuffix;
    return taskID;
}

    private String taskID;
    private String developerName;
    private String taskName;
    private String taskDescription;
    private int taskDuration;
    private int taskStatus;

    public Task(String taskID, String developerName, String taskName, String taskDescription, int taskDuration, int taskStatus) {
        this.taskID = taskID;
        this.developerName = developerName;
        this.taskName = taskName;
        this.taskDescription = taskDescription;
        this.taskDuration = taskDuration;
        this.taskStatus = taskStatus;
    }

    public String getTaskID() {
        return taskID;
    }

    public String getDeveloperName() {
        return developerName;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public int getTaskDuration() {
        return taskDuration;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public String getTaskStatusText() {
        switch (taskStatus) {
            case 1:
                return "TO DO";
            case 2:
                return "DOING";
            case 3:
                return "DONE";
            default:
                return "UNKNOWN";
        }
    }

    @Override
    public String toString() {
        return "Task ID: " + taskID + "\n"
                + "Developer Name: " + developerName + "\n"
                + "Task Name: " + taskName + "\n"
                + "Task Description: " + taskDescription + "\n"
                + "Task Duration: " + taskDuration + " hours\n"
                + "Task Status: " + getTaskStatusText();
    }
}

