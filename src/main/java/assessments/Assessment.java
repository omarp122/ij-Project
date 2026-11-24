package assessments;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Assessment {
    private final String title;

    protected Assessment(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public abstract double getFinalPercentage();

    public abstract Map<String, Double> getSubAssessmentMarks();

    public void printReport() {
        System.out.printf("Assessment: %s - %.2f%%%n",
                title, getFinalPercentage());

        for (Map.Entry<String, Double> mark : getSubAssessmentMarks().entrySet()) {
            System.out.printf("  %s: %.2f%n", mark.getKey(), mark.getValue());
        }
    }

    protected Map<String, Double> createMarkMap() {
        return new LinkedHashMap<>();
    }
}
