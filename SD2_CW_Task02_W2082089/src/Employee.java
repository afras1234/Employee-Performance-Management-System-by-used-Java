class Employee {
    String id;
    String name;
    Project project;

    public Employee(String id, String name, Project project) {
        this.id = id;
        this.name = name;
        this.project = project;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", " + project;
    }
}