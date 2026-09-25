package com.verity.common.verity;

public final class VerityTransformationManager {
    private static final float MAX_IRRITATION = 100.0F;
    private float irritation = 0.0F;
    private VerityMood mood = VerityMood.NORMAL;

    public float tickIrritation() {
        return applyStress(0.25F);
    }

    public float applyStress(float delta) {
        irritation = Math.max(0.0F, Math.min(MAX_IRRITATION, irritation + delta));
        updateMood();
        return irritation;
    }

    public boolean canTransform() {
        return irritation >= MAX_IRRITATION;
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
        this.irritation = Math.max(0.0F, Math.min(MAX_IRRITATION, irritation));
        updateMood();
    }

    private void updateMood() {
        if (irritation >= 70.0F && irritation < MAX_IRRITATION) {
            mood = VerityMood.IRRITATED;
        } else if (irritation >= MAX_IRRITATION) {
            mood = VerityMood.MONSTER;
        } else {
            mood = VerityMood.NORMAL;
        }
    }
}
