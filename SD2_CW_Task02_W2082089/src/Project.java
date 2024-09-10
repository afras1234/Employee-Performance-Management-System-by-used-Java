class Project {
    int score1;
    int score2;
    int score3;

    public Project(int score1, int score2, int score3) {
        this.score1 = score1;
        this.score2 = score2;
        this.score3 = score3;
    }

    public double calculateAverage() {
        return (score1 + score2 + score3) / 3.0;
    }

    public String getGrade() {
        double average = calculateAverage();
        if (average >= 80) {
            return "Outstanding";
        } else if (average >= 70) {
            return "Exceeds Expectations";
        } else if (average >= 40) {
            return "Meets Expectations";
        } else {
            return "Needs Improvement";
        }
    }

    public String toString() {
        return "Scores: [" + score1 + ", " + score2 + ", " + score3 + "], Grade: " + getGrade();
    }
}