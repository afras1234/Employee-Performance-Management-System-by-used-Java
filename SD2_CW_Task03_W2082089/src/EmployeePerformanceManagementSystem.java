import java.io.*;
import java.util.Scanner;

public class EmployeePerformanceManagementSystem {
    static Employee[] employees = new Employee[40];
    static int employeeCount = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        do {
            System.out.println("\n--- Employee Performance Management System ---");
            System.out.println("1. Check available number of vacancies");
            System.out.println("2. Register employee (with ID)");
            System.out.println("3. Delete employee");
            System.out.println("4. Find employee (with employee ID)");
            System.out.println("5. Store employee details into a file");
            System.out.println("6. Load employee details from the file to the system");
            System.out.println("7. View the list of employees based on their names");
            System.out.println("8. Open additional controls");
            System.out.println("C. Generate summary report");
            System.out.println("D. Generate complete report with sorted employee details");
            System.out.println("0. Exit");
            System.out.print("Select an option: ");
            input = scanner.next();

            switch (input) {
                case "1": checkVacancyAvailability(); break;
                case "2": registerEmployee(scanner); break;
                case "3": deleteEmployee(scanner); break;
                case "4": findEmployee(scanner); break;
                case "5": storeEmployeeDetails(); break;
                case "6": loadEmployeeDetails(); break;
                case "7": viewEmployeesSortedByName(); break;
                case "8": openAdditionalControls(scanner); break;
                case "C": generateSummaryReport(); break;
                case "D": generateCompleteReport(); break;
                case "0": System.out.println("Exiting..."); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        } while (!input.equals("0"));

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
        int score1 = scanner.nextInt();
        System.out.print("Enter Project 2 Score: ");
        int score2 = scanner.nextInt();
        System.out.print("Enter Project 3 Score: ");
        int score3 = scanner.nextInt();

        employees[employeeCount++] = new Employee(id, name, new Project(score1, score2, score3));
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
                Employee emp = employees[i];
                writer.println(emp.id + "," + emp.name + "," + emp.project.score1 + "," + emp.project.score2 + "," + emp.project.score3);
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
                employees[employeeCount++] = new Employee(details[0], details[1], new Project(Integer.parseInt(details[2]), Integer.parseInt(details[3]), Integer.parseInt(details[4])));
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

    static void openAdditionalControls(Scanner scanner) {
        String subOption;
        do {
            System.out.println("\n--- Additional Controls ---");
            System.out.println("1. Add Employee Name");
            System.out.println("2. Update Project Scores");
            System.out.println("0. Back to Main Menu");
            System.out.print("Select an option: ");
            subOption = scanner.next();

            switch (subOption) {
                case "1": addEmployeeName(scanner); break;
                case "2": updateProjectScores(scanner); break;
                case "0": System.out.println("Returning to main menu..."); break;
                default: System.out.println("Invalid option. Please try again.");
            }
        } while (!subOption.equals("0"));
    }

    static void addEmployeeName(Scanner scanner) {
        System.out.print("Enter Employee ID to update name: ");
        String id = scanner.next();
        Employee employee = findEmployeeById(id);

        if (employee != null) {
            System.out.print("Enter new Employee Name: ");
            employee.name = scanner.next();
            System.out.println("Employee name updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    static void updateProjectScores(Scanner scanner) {
        System.out.print("Enter Employee ID to update project scores: ");
        String id = scanner.next();
        Employee employee = findEmployeeById(id);

        if (employee != null) {
            System.out.print("Enter new Project 1 Score: ");
            int newScore1 = scanner.nextInt();
            System.out.print("Enter new Project 2 Score: ");
            int newScore2 = scanner.nextInt();
            System.out.print("Enter new Project 3 Score: ");
            int newScore3 = scanner.nextInt();

            employee.project = new Project(newScore1, newScore2, newScore3);
            System.out.println("Project scores updated successfully.");
        } else {
            System.out.println("Employee not found.");
        }
    }

    static void generateSummaryReport() {
        int totalEmployees = employeeCount;
        int countMoreThan40InP1 = 0, countMoreThan40InP2 = 0, countMoreThan40InP3 = 0;

        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].project.score1 > 40) countMoreThan40InP1++;
            if (employees[i].project.score2 > 40) countMoreThan40InP2++;
            if (employees[i].project.score3 > 40) countMoreThan40InP3++;
        }

        System.out.println("\n--- Summary Report ---");
        System.out.println("Total Employee Registrations: " + totalEmployees);
        System.out.println("Employees scoring more than 40 in Project 1: " + countMoreThan40InP1);
        System.out.println("Employees scoring more than 40 in Project 2: " + countMoreThan40InP2);
        System.out.println("Employees scoring more than 40 in Project 3: " + countMoreThan40InP3);
    }

    static void generateCompleteReport() {
        for (int i = 0; i < employeeCount - 1; i++) {
            for (int j = 0; j < employeeCount - i - 1; j++) {
                if (employees[j].getAverageScore() < employees[j + 1].getAverageScore()) {
                    Employee temp = employees[j];
                    employees[j] = employees[j + 1];
                    employees[j + 1] = temp;
                }
            }
        }

        System.out.println("\n--- Complete Report ---");
        for (int i = 0; i < employeeCount; i++) {
            Employee emp = employees[i];
            System.out.println(emp + ", Average Score: " + emp.getAverageScore());
        }
    }

    static Employee findEmployeeById(String id) {
        for (int i = 0; i < employeeCount; i++) {
            if (employees[i].id.equals(id)) return employees[i];
        }
        return null;
    }
}