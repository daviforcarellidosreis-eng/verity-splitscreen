package com.verity.common.verity;

public final class VerityVisualState {
    private VerityMood mood = VerityMood.NORMAL;
    private float aggression = 0.0F;
    private int color = 0xFF66CC;

    public VerityMood getMood() {
        return mood;
    }

    public void setMood(VerityMood mood) {
        this.mood = mood;
    }

    public float getAggression() {
        return aggression;
    }

    public void setAggression(float aggression) {
        this.aggression = aggression;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
