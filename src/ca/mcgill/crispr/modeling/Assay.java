package ca.mcgill.crispr.modeling;

import java.util.ArrayList;

/**
 * Created by Mahdiye on 4/18/2017.
 */
public class Assay {

    private GuideSeq guideSeq;
    private ArrayList<OffTarget> offTargets;

    public Assay() {
    }

    public Assay(String guideSeq) {
        GuideSeq localGuideSeq = new GuideSeq();
        localGuideSeq.setGuideSeq(guideSeq);
        this.setGuideSeq(localGuideSeq);
    }

    public GuideSeq getGuideSeq() {
        return guideSeq;
    }

    public void setGuideSeq(GuideSeq guideSeq) {
        this.guideSeq = guideSeq;
    }

    public void setGuideSeq(String guideSeq) {
        GuideSeq localGuideSeq = new GuideSeq();
        localGuideSeq.setGuideSeq(guideSeq);
        this.setGuideSeq(localGuideSeq);
    }

    public ArrayList<OffTarget> getOffTargets() {
        return offTargets;
    }

    public void setOffTargets(ArrayList<OffTarget> offTargets) {
        this.offTargets = offTargets;
    }
}
