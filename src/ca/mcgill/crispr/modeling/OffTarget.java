package ca.mcgill.crispr.modeling;

/**
 * Created by Mahdiye on 4/9/2017.
 */
public class OffTarget {

    private int id;
    private String name;
    private String oftSeq;
    private String diffLogo;
    private double cCounter;
    private double gCounter;
    private double aCounter;
    private double tCounter;
    private double mismatchNumber;
    private double sumOfMismatchPositions;
    private double firstMismatchPosition;
    private double similarity;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOftSeq() {
        return oftSeq;
    }

    public void setOftSeq(String oftSeq) {
        this.oftSeq = oftSeq;
        prepareCleavage(oftSeq);

    }

    public String getDiffLogo() {
        return diffLogo;
    }

    public void setDiffLogo(String diffLogo) {
        this.diffLogo = diffLogo;
        findCleavageAttributes(diffLogo);
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

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    public double computeSimilarity(GuideSeq tempGuideSeq) {
        OffTarget tempOfftargetSeq = new OffTarget();
        double currentOffTargetAvg = calculateAvg(this);
        preprocessCleavage(currentOffTargetAvg);



        double similarity = calculatePearsonCorrelationCoefficient(tempGuideSeq);

        return similarity;
    }


    private double calculateAvg(OffTarget offTarget) {
        double sum = offTarget.getcCounter() + offTarget.getgCounter() + offTarget.getaCounter() + offTarget.gettCounter() + offTarget.getSumOfMismatchPositions() + offTarget.getFirstMismatchPosition() + + offTarget.getMismatchNumber();
        double avg = sum / 7;

        return avg;
    }

    //New OffTarget with new attributes is created
    private void preprocessCleavage(double average) {
        OffTarget newOffTarget = new OffTarget();
        this.setaCounter(this.getaCounter() - average);
        this.setcCounter(this.getcCounter() - average);
        this.setgCounter(this.getgCounter() - average);
        this.settCounter(this.gettCounter() - average);
        this.setMismatchNumber(this.getMismatchNumber() - average);
        this.setSumOfMismatchPositions(this.getSumOfMismatchPositions() - average);
        this.setFirstMismatchPosition(this.getFirstMismatchPosition() - average);
    }

    private double calculatePearsonCorrelationCoefficient(GuideSeq guideSeq) {
        double numerator = this.getcCounter() * guideSeq.getcCounter() + this.getgCounter() * guideSeq.getgCounter() + this.getaCounter() * guideSeq.getaCounter() + this.gettCounter() * guideSeq.gettCounter() +
                this.getSumOfMismatchPositions() * guideSeq.getSumOfMismatchPositions() + this.getFirstMismatchPosition() * guideSeq.getFirstMismatchPosition() +  this.getMismatchNumber() * guideSeq.getMismatchNumber();

        double denominatorFirst = Math.sqrt(Math.pow(this.getcCounter(), 2) + Math.pow(this.getgCounter(), 2) + Math.pow(this.getaCounter(), 2) + Math.pow(this.gettCounter(), 2) + Math.pow(this.getSumOfMismatchPositions(), 2) + Math.pow(this.getFirstMismatchPosition(), 2) + Math.pow(this.getMismatchNumber(), 2));
        double denominatorSecond = Math.sqrt(Math.pow(guideSeq.getcCounter(), 2) + Math.pow(guideSeq.getgCounter(), 2) + Math.pow(guideSeq.getaCounter(), 2) + Math.pow(guideSeq.gettCounter(), 2) + Math.pow(guideSeq.getSumOfMismatchPositions(), 2) + Math.pow(guideSeq.getFirstMismatchPosition(), 2) + + Math.pow(guideSeq.getMismatchNumber(), 2));

        return numerator / (denominatorFirst * denominatorSecond);

    }

    private void prepareCleavage(String sequence) {
        int aCounter = 0;
        int cCounter = 0;
        int gCounter = 0;
        int tCounter = 0;
        //PAM is calculated
        for (int i = 0; i < sequence.length(); i++)

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

        this.setaCounter(aCounter);
        this.setcCounter(cCounter);
        this.setgCounter(gCounter);
        this.settCounter(tCounter);
    }

    private void findCleavageAttributes(String diffLogo) {

        int firstMismatchPosition = diffLogo.length()-1;
        while (diffLogo.charAt(firstMismatchPosition) != '*') {
            firstMismatchPosition--;
        }
        this.setFirstMismatchPosition(20 - firstMismatchPosition);


        int sumOfMismatchPosition = 0;
        for (int i = diffLogo.length(); i > 0; i--)
            if (diffLogo.charAt(i-1) == '*') {
                sumOfMismatchPosition += 20 - i + 1;
            }
        this.setSumOfMismatchPositions(sumOfMismatchPosition);

    }
}
