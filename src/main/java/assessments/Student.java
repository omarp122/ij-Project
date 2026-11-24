package assessments;

import java.util.LinkedHashMap;
import java.util.Map;

public class Student {
    private final String cid;
    private final String name;
    private final String type;
    private final Map<String, Assessment> assessments;

    public Student(String cid, String name, String type) {
        this.cid = cid;
        this.name = name;
        this.type = type;
        assessments = new LinkedHashMap<>();

        assessments.put(AssessmentFeed.PROGRAMMING_TEST,
                new independentTest(cid));
        assessments.put(AssessmentFeed.AI_PROJECT,
                new aiTest(cid));

        if (type.equals("MSc")) {
            assessments.put(AssessmentFeed.DESIGN_VIVA,
                    new softwareTest(cid));
        }
    }

    public String getCid() {
        return cid;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public double getAssessmentMark(String assessmentTitle) {
        Assessment assessment = assessments.get(assessmentTitle);
        if (assessment == null) {
            return 0.0;
        }
        return assessment.getFinalPercentage();
    }

    public void printAssessmentReport() {
        for (Assessment assessment : assessments.values()) {
            assessment.printReport();
        }
    }

    public double getOverallPercentage() {
        double independentMark = getAssessmentMark(
                AssessmentFeed.PROGRAMMING_TEST);
        double projectMark = getAssessmentMark(AssessmentFeed.AI_PROJECT);

        if (type.equals("MSc")) {
            double vivaMark = getAssessmentMark(AssessmentFeed.DESIGN_VIVA);
            return 0.25 * independentMark
                    + 0.45 * projectMark
                    + 0.30 * vivaMark;
        }
        return 0.30 * independentMark + 0.70 * projectMark;
    }

    public double getAiAveragePercentage() {
        return getAssessmentMark(AssessmentFeed.AI_PROJECT);
    }

    public double getNonAiAveragePercentage() {
        double independentMark = getAssessmentMark(
                AssessmentFeed.PROGRAMMING_TEST);

        if (type.equals("MSc")) {
            double vivaMark = getAssessmentMark(AssessmentFeed.DESIGN_VIVA);
            return (independentMark + vivaMark) / 2.0;
        }
        return independentMark;
    }

    public boolean hasAiDiscrepancy() {
        return getAiAveragePercentage() - getNonAiAveragePercentage() >= 20.0;
    }
}
