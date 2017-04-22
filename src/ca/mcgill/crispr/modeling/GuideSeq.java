package ca.mcgill.crispr.modeling;

/**
 * Created by Mahdiye on 4/19/2017.
 */
public class GuideSeq {

    private String name;
    private String guideSeq;
    private String diffLogo;
    private double cCounter;
    private double gCounter;
    private double aCounter;
    private double tCounter;
    private double mismatchNumber = 0;
    private double sumOfMismatchPositions = 0;
    private double firstMismatchPosition = 0;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGuideSeq() {
        return guideSeq;
    }

    public void setGuideSeq(String guideSeq) {
        this.guideSeq = guideSeq;
    }

    public String getDiffLogo() {
        return diffLogo;
    }

    public void setDiffLogo(String diffLogo) {
        this.diffLogo = diffLogo;
    }

    public double getcCounter() {
        return cCounter;
    }

    public void setcCounter(double cCounter) {
        this.cCounter = cCounter;
    }

    public double getgCounter() {
        return gCounter;
    }

    public void setgCounter(double gCounter) {
        this.gCounter = gCounter;
    }

    public double getaCounter() {
        return aCounter;
    }

    public void setaCounter(double aCounter) {
        this.aCounter = aCounter;
    }

    public double gettCounter() {
        return tCounter;
    }

    public void settCounter(double tCounter) {
        this.tCounter = tCounter;
    }

    public double getMismatchNumber() {
        return mismatchNumber;
    }

    public void setMismatchNumber(double mismatchNumber) {
        this.mismatchNumber = mismatchNumber;
    }

    public double getSumOfMismatchPositions() {
        return sumOfMismatchPositions;
    }

    public void setSumOfMismatchPositions(double sumOfMismatchPositions) {
        this.sumOfMismatchPositions = sumOfMismatchPositions;
    }

    public double getFirstMismatchPosition() {
        return firstMismatchPosition;
    }

    public void setFirstMismatchPosition(double firstMismatchPosition) {
        this.firstMismatchPosition = firstMismatchPosition;
    }

    public GuideSeq findFields(GuideSeq guideSeq) {
        int aCounter = 0;
        int cCounter = 0;
        int gCounter = 0;
        int tCounter = 0;
        String sequence = guideSeq.getGuideSeq();
        //PAM is calculated
        for (int i = 0; i < sequence.length() ; i++)

            switch (sequence.charAt(i)) {
                case ('A'):
                    aCounter++;
                    break;
                case ('C'):
                    cCounter++;
                    break;
                case ('G'):
                    gCounter++;
                    break;
                case ('T'):
                    tCounter++;
                    break;

            }

        guideSeq.setaCounter(aCounter);
        guideSeq.setcCounter(cCounter);
        guideSeq.setgCounter(gCounter);
        guideSeq.settCounter(tCounter);

        return guideSeq;
    }

    public double calculateGuideAvg() {
        double sum = this.getcCounter() + this.getgCounter() + this.getaCounter() + this.gettCounter() + this.getSumOfMismatchPositions() + this.getFirstMismatchPosition();
        double avg = sum / 7;

        return avg;
    }

    //New GuideSeq with new attributes is created
    public GuideSeq preprocessGuideSeq(GuideSeq guideSeq, double average) {
        GuideSeq newGuideSeq = new GuideSeq();
        newGuideSeq.setaCounter(guideSeq.getaCounter() - average);
        newGuideSeq.setcCounter(guideSeq.getcCounter() - average);
        newGuideSeq.setgCounter(guideSeq.getgCounter() - average);
        newGuideSeq.settCounter(guideSeq.gettCounter() - average);
        newGuideSeq.setMismatchNumber(guideSeq.getMismatchNumber() - average);
        newGuideSeq.setSumOfMismatchPositions(guideSeq.getSumOfMismatchPositions() - average);
        newGuideSeq.setFirstMismatchPosition(guideSeq.getFirstMismatchPosition() - average);

        return newGuideSeq;
    }
}
