package ca.mcgill.db;

import ca.mcgill.crispr.modeling.Assay;
import ca.mcgill.crispr.modeling.GuideSeq;
import ca.mcgill.crispr.modeling.OffTarget;

/**
 * Created by Mahdiye on 4/18/2017.
 */
public class Handler {

    Assay assay = new Assay();

    public Handler(Assay assay) {
        this.assay = assay;
    }


    public Assay getAssay() {
        return assay;
    }

    public void setAssay(Assay assay) {
        this.assay = assay;
    }

    public static Assay preprocessAssay(Assay assay) {
        for (OffTarget item : assay.getOffTargets()) {

            //prepare guideRNA's field to compute similarity with each offtarget
            GuideSeq tempGuideSeq = assay.getGuideSeq();
            tempGuideSeq = tempGuideSeq.findFields(tempGuideSeq);
            tempGuideSeq = tempGuideSeq.preprocessGuideSeq(tempGuideSeq, tempGuideSeq.calculateGuideAvg());

            item.setSimilarity(item.computeSimilarity(tempGuideSeq));
        }
        return assay;
    }


}
