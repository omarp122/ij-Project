package assessments;
import java.util.Map;

public class softwareTest extends Assessment {
    private final double designMark;
    private final double explanationMark;

    public softwareTest(String cid) {
        super(AssessmentFeed.DESIGN_VIVA);
        designMark = AssessmentFeed.getResult(
                cid, AssessmentFeed.DESIGN_VIVA, AssessmentFeed.DESIGN_MARK);
        explanationMark = AssessmentFeed.getResult(
                cid, AssessmentFeed.DESIGN_VIVA, AssessmentFeed.EXPLANATION_MARK);
    }

    @Override
    public double getFinalPercentage() {
        return 0.5 * designMark + 0.5 * explanationMark;
    }

    @Override
    public Map<String, Double> getSubAssessmentMarks() {
        Map<String, Double> marks = createMarkMap();
        marks.put(AssessmentFeed.DESIGN_MARK, designMark);
        marks.put(AssessmentFeed.EXPLANATION_MARK, explanationMark);
        return marks;
    }
}
