import java.io.*;
import java.util.Scanner;

public class EmployeePerformanceManagementSystem {
    static Employee[] employees = new Employee[40];
    static int employeeCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int option;
        do {
            System.out.println("\n--- Employee Performance Management System ---");
            System.out.println("1. Check available number of vacancies");
            System.out.println("2. Register employee (with ID)");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee (with employee ID)");
            System.out.println("5. Store employee details into a file");
            System.out.println("6. Load employee details from the file to the system");
            System.out.println("7. View the list of employees based on their names");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1: checkVacancyAvailability(); break;
                case 2: registerEmployee(scanner); break;
                case 3: deleteEmployee(scanner); break;
                case 4: findEmployee(scanner); break;
                case 5: storeEmployeeDetails(); break;
                case 6: loadEmployeeDetails(); break;
                case 7: viewEmployeesSortedByName(); break;
                case 0: System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        } while (option != 0);

        scanner.close();
    }

    static void checkVacancyAvailability() {
        System.out.println("Number of available vacancies: " + (40 - employeeCount));
    }

    static void registerEmployee(Scanner scanner) {
        if (employeeCount >= 40) {
            System.out.println("No vacancies available.");
            return;
        }

        System.out.print("Enter Employee ID: ");
        String id = scanner.next();
        System.out.print("Enter Employee Name: ");
        String name = scanner.next();
        System.out.print("Enter Project 1 Score: ");
        int project1Score = scanner.nextInt();
        System.out.print("Enter Project 2 Score: ");
        int project2Score = scanner.nextInt();
        System.out.print("Enter Project 3 Score: ");
        int project3Score = scanner.nextInt();

        employees[employeeCount++] = new Employee(id, name, project1Score, project2Score, project3Score);
        System.out.println("Employee registered successfully.");
    }

    static void deleteEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to delete: ");
        String id = scanner.next();
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].id.equals(id)) {
                employees[i] = employees[--employeeCount];
                employees[employeeCount] = null;
                System.out.println("Employee deleted successfully.");
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    static void findEmployee(Scanner scanner) {
        System.out.print("Enter Employee ID to find: ");
        String id = scanner.next();
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].id.equals(id)) {
                System.out.println(employees[i]);
                return;
            }
        }
        System.out.println("Employee not found.");
    }

    static void storeEmployeeDetails() {
        try (PrintWriter writer = new PrintWriter(new FileWriter("employees.txt"))) {
            for (int i = 0; i < employeeCount; i++) {
                writer.println(employees[i].id + "," + employees[i].name + "," +
                        employees[i].project1Score + "," + employees[i].project2Score + "," +
                        employees[i].project3Score);
            }
            System.out.println("Employee details stored successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while storing employee details.");
        }
    }

    static void loadEmployeeDetails() {
        try (BufferedReader reader = new BufferedReader(new FileReader("employees.txt"))) {
            String line;
            employeeCount = 0;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(",");
                employees[employeeCount++] = new Employee(details[0], details[1],
                        Integer.parseInt(details[2]), Integer.parseInt(details[3]),
                        Integer.parseInt(details[4]));
            }
            System.out.println("Employee details loaded successfully.");
        } catch (IOException e) {
            System.out.println("An error occurred while loading employee details.");
        }
    }

    static void viewEmployeesSortedByName() {
        for (int i = 0; i < employeeCount - 1; i++) {
            for (int j = 0; j < employeeCount - i - 1; j++) {
                if (employees[j].name.compareTo(employees[j + 1].name) > 0) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }

        System.out.println("Employees sorted by name:");
        for (int i = 0; i < employeeCount; i++) {
            System.out.println(employees[i]);
        }
    }
}

