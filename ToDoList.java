package Todo_list;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

class Task {
    private String description;
    private LocalDateTime dateTime;

    public Task(String description, LocalDateTime dateTime) {
        this.description = description;
        this.dateTime = dateTime;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "[" + formatter.format(dateTime) + "] " + description;
    }
}

public class ToDoList {
    private ArrayList<Task> tasks = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ToDoList todoList = new ToDoList();
        todoList.run();
    }

    private void run() {
        while (true) {
            printMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    addTask();
                    break;
                case 2:
                    editTask();
                    break;
                case 3:
                    deleteTask();
                    break;
                case 4:
                    printTasks();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void printMenu() {
        System.out.println("\n===== To-Do List Menu =====");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. View Tasks");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private void addTask(){
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        LocalDateTime dateTime;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime currentDateTime = LocalDateTime.now();
        int comparisonResult;
        do {
        System.out.print("Enter date and time in format (YYYY-MM-DD HH:MM): ");
        
        String dateTimeString = scanner.nextLine();
        dateTime = LocalDateTime.parse(dateTimeString, formatter);
        comparisonResult = currentDateTime.compareTo(dateTime);
        if(comparisonResult>=0)
        {
        	System.out.println("Date and should be greater than the current time");
        }
        }while(comparisonResult>=0);
        Task task = new Task(description, dateTime);
        tasks.add(task);
        System.out.println("Task added successfully.");
        
    }

    private void editTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to edit.");
            return;
        }

        printTasks();
        System.out.print("Enter task number to edit: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        Task task = tasks.get(taskIndex - 1);
        System.out.print("Enter new task description: ");
        String newDescription = scanner.nextLine();
        task.setDescription(newDescription);

        System.out.print("Enter new date and time (YYYY-MM-DD HH:MM): ");
        String newDateTimeString = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime newDateTime = LocalDateTime.parse(newDateTimeString, formatter);
        task.setDateTime(newDateTime);

        System.out.println("Task updated successfully.");
    }

    private void deleteTask() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to delete.");
            return;
        }

        printTasks();
        System.out.print("Enter task number to delete: ");
        int taskIndex = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (taskIndex < 1 || taskIndex > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }

        Task deletedTask = tasks.remove(taskIndex - 1);
        System.out.println("Task deleted: " + deletedTask);
    }

    private void printTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks to display.");
            return;
        }

        System.out.println("\n===== Tasks =====");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }
}
