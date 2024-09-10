class Project {
    int score1, score2, score3;

    public Project(int s1, int s2, int s3) {
        score1 = s1; score2 = s2; score3 = s3;
    }

    public double calculateAverage() {
        return (score1 + score2 + score3) / 3.0;
    }

    public String getGrade() {
        double avg = calculateAverage();
        return avg >= 80 ? "Outstanding" : avg >= 70 ? "Exceeds Expectations" : avg >= 40 ? "Meets Expectations" : "Needs Improvement";
    }

    public String toString() {
        return "Scores: [" + score1 + ", " + score2 + ", " + score3 + "], Grade: " + getGrade();
    }
}