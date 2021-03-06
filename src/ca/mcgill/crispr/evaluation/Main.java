package ca.mcgill.crispr.evaluation;

public class Main {

    public static void main(String[] args) {

        String sampleSeq = "CGGGTGGATCACCTGAGGTCAGG";
        Sequence sampleSequence = new Sequence(sampleSeq);

        String guideSeq = "CCCTGTGTCTCTGGATGGATGGG";
        Sequence guideSequence = new Sequence(guideSeq);

        SequenceHandler sequenceHandler = new SequenceHandler(guideSequence, sampleSequence);

        sampleSequence = sequenceHandler.realizeSequncesVariations(guideSequence, sampleSequence);

        double similarity = sequenceHandler.computeSimilarity(guideSequence, sampleSequence);


        System.out.println("Similarity between "+sampleSeq+" and "+ guideSeq+ " is: "+similarity);
    }
}
