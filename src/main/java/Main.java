import assessments.AssessmentFeed;
import assessments.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
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

        Object[][] data = new Object[students.size()][columns.length];
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            data[i][0] = student.getName();
            data[i][1] = student.getCid();
            data[i][2] = student.getType();
            data[i][3] = formatPercentage(student.getAssessmentMark(
                    AssessmentFeed.PROGRAMMING_TEST));
            data[i][4] = formatPercentage(student.getAssessmentMark(
                    AssessmentFeed.AI_PROJECT));
            data[i][5] = student.getType().equals("MSc")
                    ? formatPercentage(student.getAssessmentMark(
                    AssessmentFeed.DESIGN_VIVA))
                    : "N/A";
            data[i][6] = formatPercentage(student.getOverallPercentage());
        }

        JTable table = new JTable(data, columns);

        JFrame frame = new JFrame("Assessment Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new JScrollPane(table));
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static String formatPercentage(double mark) {
        return String.format("%.2f%%", mark);
    }
}