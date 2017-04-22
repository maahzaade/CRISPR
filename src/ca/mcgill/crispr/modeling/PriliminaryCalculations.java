package ca.mcgill.crispr.modeling;

import ca.mcgill.db.DBModification;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mahdiye on 4/10/2017.
 */
public class PriliminaryCalculations {

    public static void main(String[] args) {
        double maxSimilarity = -1000;
        double minSimilarity = 1000;
        double similarity = 0;

        List<Assay> assayList = new ArrayList<Assay>();
        DBModification dbModification = new DBModification();

        assayList = dbModification.loadAssay();

        System.out.println("name==================================guide sequence========================================OfftargetSequence========================================similarity");

        for (Assay element : assayList) {
            double categorizedMaxSimilarity = -1000;
            double categorizedMinSimilarity = 1000;
            for (OffTarget item : element.getOffTargets()) {
                similarity = item.getSimilarity();

                if (similarity < categorizedMinSimilarity)
                    categorizedMinSimilarity = similarity;

                if (similarity > categorizedMaxSimilarity)
                    categorizedMaxSimilarity = similarity;

                if (similarity < minSimilarity)
                    minSimilarity = similarity;

                if (similarity > maxSimilarity)
                    maxSimilarity = similarity;

                System.out.println(item.getName() + "==============================" + element.getGuideSeq().getGuideSeq() + "====================" + item.getOftSeq() + "======================" + similarity);
            }

            System.out.println("************************************************************************************************************************************************************");
            System.out.println("minimum categorized similarity = "+ categorizedMinSimilarity+"**********************maximum categorized similarity = "+ categorizedMaxSimilarity+"*******subtraction= "+(categorizedMaxSimilarity-categorizedMinSimilarity) );

        }

        System.out.println("************minimum similarity = "+ minSimilarity);
        System.out.println("************maximum similarity = "+ maxSimilarity);



    }

}
