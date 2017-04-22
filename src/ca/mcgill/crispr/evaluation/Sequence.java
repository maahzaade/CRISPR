package ca.mcgill.crispr.evaluation;

/**
 * Created by Mahdiye on 4/20/2017.
 */
public class Sequence {

    private String name;
    private String sequence;
    private double cCounter;
    private double gCounter;
    private double aCounter;
    private double tCounter;
    private double mismatchNumber = 0;
    private double sumOfMismatchPositions = 0;
    private double firstMismatchPosition = 0;

    public Sequence() {
    }

    public Sequence(String sequence) {
        this.sequence = sequence;
        propagateData(sequence);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
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

    private void propagateData(String sequence) {
        int aCounter = 0;
        int cCounter = 0;
        int gCounter = 0;
        int tCounter = 0;
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

        this.setaCounter(aCounter);
        this.setcCounter(cCounter);
        this.setgCounter(gCounter);
        this.settCounter(tCounter);
    }

}
