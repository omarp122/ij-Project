package assessments;

import java.util.Map;

public class IndependentProgrammingTest extends Assessment {
    private final double programMark;
    private final boolean explanationSuccessful;

    public IndependentProgrammingTest(String cid) {
        super(AssessmentFeed.PROGRAMMING_TEST);
        programMark = AssessmentFeed.getResult(
                cid, AssessmentFeed.PROGRAMMING_TEST, AssessmentFeed.PROGRAM_MARK);

        double explained = AssessmentFeed.getResult(
                cid, AssessmentFeed.PROGRAMMING_TEST,
                AssessmentFeed.EXPLAINED_SUCCESSFULLY);
        explanationSuccessful = explained == 1.0;
    }

    @Override
    public double getFinalPercentage() {
        if (!explanationSuccessful && programMark > 40.0) {
            return 40.0;
        }
        return programMark;
    }

    @Override
    public Map<String, Double> getSubAssessmentMarks() {
        Map<String, Double> marks = createMarkMap();
        marks.put(AssessmentFeed.PROGRAM_MARK, programMark);
        marks.put(AssessmentFeed.EXPLAINED_SUCCESSFULLY,
                explanationSuccessful ? 1.0 : 0.0);
        return marks;
    }
}
