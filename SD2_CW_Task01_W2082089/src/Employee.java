class Employee {
    String id;
    String name;
    int project1Score;
    int project2Score;
    int project3Score;

    public Employee(String id, String name, int project1Score, int project2Score, int project3Score) {
        this.id = id;
        this.name = name;
        this.project1Score = project1Score;
        this.project2Score = project2Score;
        this.project3Score = project3Score;
    }

    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Project1 Score: " + project1Score +
                ", Project2 Score: " + project2Score + ", Project3 Score: " + project3Score;
    }
}