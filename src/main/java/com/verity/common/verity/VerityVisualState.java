package com.verity.common.verity;

public final class VerityVisualState {
    private VerityMood mood = VerityMood.NORMAL;
    private float aggression = 0.0F;
    private int color = 0xFF66CC;

    public VerityMood getMood() {
        return mood;
    }

    public void setMood(VerityMood mood) {
        this.mood = mood == null ? VerityMood.NORMAL : mood;
        switch (this.mood) {
            case MONSTER -> color = 0xFF3A2A;
            case IRRITATED -> color = 0xFFB347;
            default -> color = 0xFF66CC;
        }
    }

    public float getAggression() {
        return aggression;
    }

    public void setAggression(float aggression) {
        this.aggression = Math.max(0.0F, Math.min(1.0F, aggression));
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
