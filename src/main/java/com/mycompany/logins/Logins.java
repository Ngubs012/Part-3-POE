/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.logins;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javax.swing.JOptionPane;
/**
 *
 * @author RC_Student_lab
 */

public class Logins {
    String enteredUserName;
    String enteredPassword;
    String firstName;
    String surname;
    String password;
    String userName;

    public boolean checkUsername() {
        boolean check = false;
        for (int i = 0; i < userName.length(); i++) {
            if (userName.length() <= 5) {
                if (userName.charAt(i) == '_') {
                    check = true;
                }
            }
        }
        return check;
    }

    public boolean checkPasswordComplexity() {
        boolean capitalLetter = false;
        boolean number = false;
        boolean special = false;
        if (password.length() >= 8) {
            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);
                if (Character.isUpperCase(ch)) {
                    capitalLetter = true;
                } else if (Character.isDigit(ch)) {
                    number = true;
                } else if (!Character.isLetterOrDigit(ch)) {
                    special = true;
                }
            }
        }
        return capitalLetter && number && special;
    }

    public String registerUser() {
        if (checkUsername()) {
            System.out.println("Username successfully captured.");
        } else {
            System.out.println("Username is not correctly formatted, please ensure that your Username contains an underscore and is no more than 5 characters in length.");
        }
        if (checkPasswordComplexity()) {
            System.out.println("Password successfully captured.");
        } else {
            System.out.println("Password is not correctly formatted, please ensure that the password contains at least 8 characters, a capital letter, a number and a special character.");
        }
        if (checkUsername() && checkPasswordComplexity()) {
            System.out.println("The two above conditions have been met and the user has been registered successfully.");
        } else {
            if (!checkPasswordComplexity()) {
                System.out.println("The Password does not meet the complexity requirements.");
            }
            if (!checkUsername()) {
                System.out.println("The username is incorrectly formatted.");
            }
        }
        return "";
    }

    public boolean loginUser() {
        return userName.equals(enteredUserName) && password.equals(enteredPassword);
    }

    public String returnLoginStatus() {
        if (loginUser()) {
            System.out.println("Successful login");
            System.out.println("Welcome " + firstName + " " + surname + " it is great to see you again.");
        } else {
            System.out.println("A failed login");
            System.out.println("Username or Password incorrect please try again");
        }
        return "";
    }

    public static void main(String[] args) {
        Logins login = new Logins();
        Task3 info = new Task3();
        Scanner input = new Scanner(System.in);

        System.out.println("Register..........");
        System.out.println("Enter FirstName :");
        login.firstName = input.next();
        System.out.println("Enter LastName :");
        login.surname = input.next();
        System.out.println("Enter UserName :");
        login.userName = input.next();
        System.out.println("Enter Password :");
        login.password = input.next();

        System.out.println(login.registerUser());
        while (!login.checkUsername() || !login.checkPasswordComplexity()) {
            System.out.println("Try to register again!!!!!");
            System.out.println("Enter UserName :");
            login.userName = input.next();
            System.out.println("Enter Password :");
            login.password = input.next();
            System.out.println(login.registerUser());
        }

        System.out.println("Login..........");
        System.out.println("Enter Username :");
        login.enteredUserName = input.next();
        System.out.println("Enter Password :");
        login.enteredPassword = input.next();
        System.out.println(login.returnLoginStatus());

        while (!login.loginUser()) {
            System.out.println("Try to Login again ..........");
            System.out.println("Enter Username :");
            login.enteredUserName = input.next();
            System.out.println("Enter Password :");
            login.enteredPassword = input.next();
            System.out.println(login.returnLoginStatus());
        }

        if (login.loginUser()) {
            JOptionPane.showMessageDialog(null, "Welcome To EasyKanban");
            int choice;
            do {
                info.input = JOptionPane.showInputDialog("Choose an option:\n1. Add tasks\n2. Show report\n3. Quit");
                choice = Integer.parseInt(info.input);

                switch (choice) {
                    case 1:
                        int numTasks = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of tasks:"));
                        int totalHours = 0;

                        for (int i = 0; i < numTasks; i++) {
                            String taskName = JOptionPane.showInputDialog("Enter task name:");
                            String taskDescription = JOptionPane.showInputDialog("Enter task description:");
                            String developerFirstName = JOptionPane.showInputDialog("Enter developer's first name:");
                            String developerLastName = JOptionPane.showInputDialog("Enter developer's last name:");
                            int taskDuration = Integer.parseInt(JOptionPane.showInputDialog("Enter task duration:"));
                            String taskID = info.createTaskID(taskName, i, developerLastName);
                            String taskStatus = "";

                            int option = Integer.parseInt(JOptionPane.showInputDialog("Please choose the Status of this task from the three options.\n1.To Do\n2.Doing\n3.Done"));
                            switch (option) {
                                case 1:
                                    taskStatus = "To Do";
                                    break;
                                case 2:
                                    taskStatus = "Doing";
                                    break;
                                case 3:
                                    taskStatus = "Done";
                                    break;
                            }

                            info.addTask(taskName, taskDescription, developerFirstName + " " + developerLastName, taskID, taskDuration, taskStatus);
                            String taskDetails = info.printTaskDetails(taskStatus, developerFirstName, developerLastName, i, taskName, taskDescription, taskID, taskDuration);
                            JOptionPane.showMessageDialog(null, taskDetails);
                            JOptionPane.showMessageDialog(null, "Task successfully captured."); // Task captured message
                            totalHours += taskDuration;
                        }

                        JOptionPane.showMessageDialog(null, "Total hours: " + totalHours);
                        break;

                    case 2:
                        int reportChoice;
                        do {
                            reportChoice = Integer.parseInt(JOptionPane.showInputDialog("Show Report Options:\n1. Display all tasks\n2. Display done tasks\n3. Display longest task\n4. Search task by name\n5. Search tasks by developer\n6. Delete task\n7. Go back"));

                            switch (reportChoice) {
                                case 1:
                                    info.showTaskReport();
                                    break;
                                case 2:
                                    info.displayDoneTasks();
                                    break;
                                case 3:
                                    info.displayLongestTask();
                                    break;
                                case 4:
                                    String searchTaskName = JOptionPane.showInputDialog("Enter task name to search:");
                                    info.searchTaskByName(searchTaskName);
                                    break;
                                case 5:
                                    String searchDeveloper = JOptionPane.showInputDialog("Enter developer name to search tasks:");
                                    info.searchTasksByDeveloper(searchDeveloper);
                                    break;
                                case 6:
                                    String deleteTaskName = JOptionPane.showInputDialog("Enter task name to delete:");
                                    info.deleteTask(deleteTaskName);
                                    break;
                                case 7:
                                    JOptionPane.showMessageDialog(null, "Returning to main menu.");
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                                    break;
                            }
                        } while (reportChoice != 7);
                        break;

                    case 3:
                        JOptionPane.showMessageDialog(null, "Exiting the application.");
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice. Please try again.");
                        break;
                }
            } while (choice != 3);
        }
    }
}

class Task3 {
    String input = "";
    private List<String> developers = new ArrayList<>();
    private List<String> taskNames = new ArrayList<>();
    private List<String> taskIDs = new ArrayList<>();
    private List<Integer> taskDurations = new ArrayList<>();
    private List<String> taskStatuses = new ArrayList<>();

    public boolean checkTaskDescription(String taskDescription) {
        return taskDescription.length() <= 50;
    }

    public String createTaskID(String taskName, int taskNumber, String developerLastName) {
        return taskName.substring(0, 2).toUpperCase() + ":" + taskNumber + ":" + developerLastName.substring(developerLastName.length() - 3).toUpperCase();
    }

    public String printTaskDetails(String taskStatus, String developerFirstName, String developerLastName, int taskNumber, String taskName, String taskDescription, String taskID, int taskDuration) {
        StringBuilder taskDetails = new StringBuilder();
        taskDetails.append("Task Status: ").append(taskStatus).append("\n");
        taskDetails.append("Developer Details: ").append(developerFirstName).append(" ").append(developerLastName).append("\n");
        taskDetails.append("Task Number: ").append(taskNumber).append("\n");
        taskDetails.append("Task Name: ").append(taskName).append("\n");
        taskDetails.append("Task Description: ").append(taskDescription).append("\n");
        taskDetails.append("Task ID: ").append(taskID).append("\n");
        taskDetails.append("Task Duration: ").append(taskDuration).append(" hours\n");
        return taskDetails.toString();
    }

    public void addTask(String taskName, String taskDescription, String developer, String taskID, int taskDuration, String taskStatus) {
        taskNames.add(taskName);
        developers.add(developer);
        taskIDs.add(taskID);
        taskDurations.add(taskDuration);
        taskStatuses.add(taskStatus);
    }

    public void showTaskReport() {
        StringBuilder report = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            report.append("Task Name: ").append(taskNames.get(i)).append("\n");
            report.append("Developer: ").append(developers.get(i)).append("\n");
            report.append("Task ID: ").append(taskIDs.get(i)).append("\n");
            report.append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n");
            report.append("Task Status: ").append(taskStatuses.get(i)).append("\n\n");
        }
        JOptionPane.showMessageDialog(null, report.toString());
    }

    public void displayDoneTasks() {
        StringBuilder doneTasks = new StringBuilder();
        doneTasks.append("Tasks with status 'Done':\n");
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskStatuses.get(i).equals("Done")) {
                doneTasks.append("Developer: ").append(developers.get(i)).append(", ");
                doneTasks.append("Task Name: ").append(taskNames.get(i)).append(", ");
                doneTasks.append("Task Duration: ").append(taskDurations.get(i)).append(" hours\n");
            }
        }
        JOptionPane.showMessageDialog(null, doneTasks.toString());
    }

    public void displayLongestTask() {
        int maxDuration = 0;
        int maxIndex = 0;
        for (int i = 0; i < taskDurations.size(); i++) {
            if (taskDurations.get(i) > maxDuration) {
                maxDuration = taskDurations.get(i);
                maxIndex = i;
            }
        }
        JOptionPane.showMessageDialog(null, "Task with longest duration:\nDeveloper: " + developers.get(maxIndex) + ", Duration: " + maxDuration + " hours");
    }

    public void searchTaskByName(String name) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(name)) {
                result.append("Task Name: ").append(taskNames.get(i)).append("\n");
                result.append("Developer: ").append(developers.get(i)).append("\n");
                result.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
                break;
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void searchTasksByDeveloper(String developer) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < developers.size(); i++) {
            if (developers.get(i).contains(developer)) {
                result.append("Task Name: ").append(taskNames.get(i)).append(", ");
                result.append("Task Status: ").append(taskStatuses.get(i)).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, result.toString());
    }

    public void deleteTask(String taskName) {
        for (int i = 0; i < taskNames.size(); i++) {
            if (taskNames.get(i).equals(taskName)) {
                taskNames.remove(i);
                developers.remove(i);
                taskIDs.remove(i);
                taskDurations.remove(i);
                taskStatuses.remove(i);
                JOptionPane.showMessageDialog(null, "Task '" + taskName + "' deleted successfully.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "Task '" + taskName + "' not found.");
    }
}