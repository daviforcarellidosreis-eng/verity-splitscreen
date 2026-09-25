package com.verity.common.secondplayer;

import com.verity.common.verity.VerityMood;

public final class SecondPlayerState {
    private boolean active;
    private boolean playerSelected;
    private boolean veritySelected;
    private int color;
    private float irritation;
    private VerityMood mood = VerityMood.NORMAL;

    public boolean isActive() { return active; }
    public void setActive(boolean active) { this.active = active; }

    public boolean isPlayerSelected() { return playerSelected; }
    public void setPlayerSelected(boolean playerSelected) { this.playerSelected = playerSelected; }

    public boolean isVeritySelected() { return veritySelected; }
    public void setVeritySelected(boolean veritySelected) { this.veritySelected = veritySelected; }

    public int getColor() { return color; }
    public void setColor(int color) { this.color = color; }

    public float getIrritation() { return irritation; }
    public void setIrritation(float irritation) { this.irritation = irritation; }

    public VerityMood getMood() { return mood; }
    public void setMood(VerityMood mood) { this.mood = mood; }
}
