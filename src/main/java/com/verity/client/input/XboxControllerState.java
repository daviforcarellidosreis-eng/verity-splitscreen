package com.verity.client.input;

public final class XboxControllerState {
    private float leftX;
    private float leftY;
    private float rightX;
    private float rightY;
    private boolean aPressed;
    private boolean bPressed;
    private boolean startPressed;
    private boolean rtPressed;

    public float getLeftX() { return leftX; }
    public void setLeftX(float leftX) { this.leftX = leftX; }

    public float getLeftY() { return leftY; }
    public void setLeftY(float leftY) { this.leftY = leftY; }

    public float getRightX() { return rightX; }
    public void setRightX(float rightX) { this.rightX = rightX; }

    public float getRightY() { return rightY; }
    public void setRightY(float rightY) { this.rightY = rightY; }

    public boolean isAPressed() { return aPressed; }
    public void setAPressed(boolean aPressed) { this.aPressed = aPressed; }

    public boolean isBPressed() { return bPressed; }
    public void setBPressed(boolean bPressed) { this.bPressed = bPressed; }

    public boolean isStartPressed() { return startPressed; }
    public void setStartPressed(boolean startPressed) { this.startPressed = startPressed; }

    public boolean isRtPressed() { return rtPressed; }
    public void setRtPressed(boolean rtPressed) { this.rtPressed = rtPressed; }
}
