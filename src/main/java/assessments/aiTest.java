package assessments;
import java.util.Map;

public class aiTest extends Assessment {
    private final double functionalityMark;
    private final double verificationMark;

    public aiTest(String cid) {
        super(AssessmentFeed.AI_PROJECT);
        functionalityMark = AssessmentFeed.getResult(
                cid, AssessmentFeed.AI_PROJECT, AssessmentFeed.FUNCTIONALITY_MARK);
        verificationMark = AssessmentFeed.getResult(
                cid, AssessmentFeed.AI_PROJECT, AssessmentFeed.VERIFICATION_MARK);
    }

    @Override
    public double getFinalPercentage() {
        return 0.4 * functionalityMark + 0.6 * verificationMark;
    }

    @Override
    public Map<String, Double> getSubAssessmentMarks() {
        Map<String, Double> marks = createMarkMap();
        marks.put(AssessmentFeed.FUNCTIONALITY_MARK, functionalityMark);
        marks.put(AssessmentFeed.VERIFICATION_MARK, verificationMark);
        return marks;
    }
}
