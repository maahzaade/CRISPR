package ca.mcgill.crispr.evaluation;

/**
 * Created by Mahdiye on 4/20/2017.
 */
public class SequenceHandler {

    public Sequence guideSequence;
    public Sequence sampleSequence;


    public SequenceHandler(Sequence guideSequence, Sequence sampleSequence) {
        this.guideSequence = guideSequence;
        this.sampleSequence = sampleSequence;
    }

    public Sequence getGuideSequence() {
        return guideSequence;
    }

    public void setGuideSequence(Sequence guideSequence) {
        this.guideSequence = guideSequence;
    }

    public Sequence getSampleSequence() {
        return sampleSequence;
    }

    public void setSampleSequence(Sequence sampleSequence) {
        this.sampleSequence = sampleSequence;
    }

    public Sequence realizeSequncesVariations(Sequence guideSequence, Sequence sampleSequence) {
        String guideSeq = guideSequence.getSequence();
        String sampleSeq = sampleSequence.getSequence();

        int firstMismatchPosition = 0;
        int mismatchNumbers = 0;
        int sumOfMismatchPositions = 0;

        for (int i = 20; i > 0; i--) {
            if (sampleSeq.charAt(i-1) != guideSeq.charAt(i-1)) {
                firstMismatchPosition = 20 - i +1;
                break;
            }
        }

        for (int i = 20; i > 0; i--) {
            if (sampleSeq.charAt(i-1) != guideSeq.charAt(i-1)) {
                mismatchNumbers++;
                sumOfMismatchPositions += (20 - i + 1);
            }
        }


        sampleSequence.setMismatchNumber(mismatchNumbers);
        sampleSequence.setFirstMismatchPosition(firstMismatchPosition);
        sampleSequence.setSumOfMismatchPositions(sumOfMismatchPositions);

        return sampleSequence;

    }


    public double computeSimilarity(Sequence guideSequence, Sequence sampleSequence) {
        double guideSeqAvg = calculateAvg(guideSequence);
        double sampleSeqAvg = calculateAvg(sampleSequence);

        guideSequence = preprocessSequence(guideSequence, guideSeqAvg);
        sampleSequence = preprocessSequence(sampleSequence, sampleSeqAvg);



        double similarity = calculatePearsonCorrelationCoefficient(guideSequence, sampleSequence);

        return similarity;
    }


    private double calculateAvg(Sequence sequence) {
        double sum = sequence.getcCounter() + sequence.getgCounter() + sequence.getaCounter() + sequence.gettCounter() + sequence.getSumOfMismatchPositions() + sequence.getFirstMismatchPosition() + sequence.getMismatchNumber();
        double avg = sum / 7;

        return avg;
    }

    private Sequence preprocessSequence(Sequence sequence, double average) {
        Sequence newSequence = new Sequence();
        newSequence.setaCounter(sequence.getaCounter() - average);
        newSequence.setcCounter(sequence.getcCounter() - average);
        newSequence.setgCounter(sequence.getgCounter() - average);
        newSequence.settCounter(sequence.gettCounter() - average);
        newSequence.setMismatchNumber(sequence.getMismatchNumber() - average);
        newSequence.setSumOfMismatchPositions(sequence.getSumOfMismatchPositions() - average);
        newSequence.setFirstMismatchPosition(sequence.getFirstMismatchPosition() - average);

        return newSequence;
    }

    private double calculatePearsonCorrelationCoefficient(Sequence guideSequence, Sequence sampleSequence) {
        double numerator = sampleSequence.getcCounter() * guideSequence.getcCounter() + sampleSequence.getgCounter() * guideSequence.getgCounter() + sampleSequence.getaCounter() * guideSequence.getaCounter() + sampleSequence.gettCounter() * guideSequence.gettCounter() +
                sampleSequence.getSumOfMismatchPositions() * guideSequence.getSumOfMismatchPositions() + sampleSequence.getFirstMismatchPosition() * guideSequence.getFirstMismatchPosition() + sampleSequence.getMismatchNumber() * guideSequence.getMismatchNumber();

        double denominatorFirst = Math.sqrt(Math.pow(sampleSequence.getcCounter(), 2) + Math.pow(sampleSequence.getgCounter(), 2) + Math.pow(sampleSequence.getaCounter(), 2) + Math.pow(sampleSequence.gettCounter(), 2) + Math.pow(sampleSequence.getSumOfMismatchPositions(), 2) + Math.pow(sampleSequence.getFirstMismatchPosition(), 2)+ Math.pow(sampleSequence.getMismatchNumber(), 2));
        double denominatorSecond = Math.sqrt(Math.pow(guideSequence.getcCounter(), 2) + Math.pow(guideSequence.getgCounter(), 2) + Math.pow(guideSequence.getaCounter(), 2) + Math.pow(guideSequence.gettCounter(), 2) + Math.pow(guideSequence.getSumOfMismatchPositions(), 2) + Math.pow(guideSequence.getFirstMismatchPosition(), 2) + Math.pow(guideSequence.getMismatchNumber(), 2));

        return numerator / (denominatorFirst * denominatorSecond);

    }



}
