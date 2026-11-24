import assessments.AssessmentFeed;
import assessments.Student;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Student> students = loadStudents();

        printTechnicalReport(students);

    }

    private static List<Student> loadStudents() {
        List<Student> students = new ArrayList<>();
        for (String cid : AssessmentFeed.getCIDs()) {
            students.add(new Student(
                    cid,
                    AssessmentFeed.getStudentName(cid),
                    AssessmentFeed.getStudentType(cid)));
        }
        return students;
    }

    private static void printTechnicalReport(List<Student> students) {
        for (Student student : students) {
            System.out.printf("Name: %s%n", student.getName());
            System.out.printf("CID: %s%n", student.getCid());
            System.out.printf("Type: %s%n", student.getType());

            student.printAssessmentReport();

            System.out.printf("Overall weighted module percentage: %.2f%%%n",
                    student.getOverallPercentage());
            System.out.printf("AI discrepancy condition met: %s%n",
                    student.hasAiDiscrepancy());
            System.out.println();
        }
    }

    private static void createAndShowWindow(List<Student> students) {
        String[] columns = {
                "Name", "CID", "Type", "Independent Programming Test",
                "AI-Assisted Project", "Software Design Viva",
                "Overall Module Mark"
        };
    }
}
