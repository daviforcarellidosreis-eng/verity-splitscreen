package com.verity.common.verity;

public final class VerityTransformationManager {
    private float irritation = 0.0F;
    private VerityMood mood = VerityMood.NORMAL;

    public float tickIrritation() {
        irritation = Math.min(100.0F, irritation + 0.25F);
        if (irritation >= 70.0F) {
            mood = VerityMood.IRRITATED;
        }
        if (irritation >= 100.0F) {
            mood = VerityMood.MONSTER;
        }
        return irritation;
    }

    public boolean canTransform() {
        return irritation >= 100.0F;
    }

    public void applyTransformation() {
        if (canTransform()) {
            mood = VerityMood.MONSTER;
        }
    }

    public VerityMood getMood() {
        return mood;
    }

    public float getIrritation() {
        return irritation;
    }

    public void setIrritation(float irritation) {
        this.irritation = irritation;
        if (irritation >= 70.0F && irritation < 100.0F) {
            this.mood = VerityMood.IRRITATED;
        } else if (irritation >= 100.0F) {
            this.mood = VerityMood.MONSTER;
        } else {
            this.mood = VerityMood.NORMAL;
        }
    }
}
