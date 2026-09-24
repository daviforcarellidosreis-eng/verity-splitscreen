package com.verity.client.input;

public final class XboxControllerState {
    private boolean connected;
    private boolean startPressed;
    private boolean backPressed;
    private boolean aPressed;
    private boolean bPressed;
    private boolean xPressed;
    private boolean yPressed;
    private boolean rtPressed;
    private boolean ltPressed;
    private float leftX;
    private float leftY;
    private float rightX;
    private float rightY;
    private float triggerLeft;
    private float triggerRight;

    public boolean isConnected() { return connected; }
    public void setConnected(boolean connected) { this.connected = connected; }

    public boolean isStartPressed() { return startPressed; }
    public void setStartPressed(boolean startPressed) { this.startPressed = startPressed; }

    public boolean isBackPressed() { return backPressed; }
    public void setBackPressed(boolean backPressed) { this.backPressed = backPressed; }

    public boolean isAPressed() { return aPressed; }
    public void setAPressed(boolean aPressed) { this.aPressed = aPressed; }

    public boolean isBPressed() { return bPressed; }
    public void setBPressed(boolean bPressed) { this.bPressed = bPressed; }

    public boolean isXPressed() { return xPressed; }
    public void setXPressed(boolean xPressed) { this.xPressed = xPressed; }

    public boolean isYPressed() { return yPressed; }
    public void setYPressed(boolean yPressed) { this.yPressed = yPressed; }

    public boolean isRtPressed() { return rtPressed; }
    public void setRtPressed(boolean rtPressed) { this.rtPressed = rtPressed; }

    public boolean isLtPressed() { return ltPressed; }
    public void setLtPressed(boolean ltPressed) { this.ltPressed = ltPressed; }

    public float getLeftX() { return leftX; }
    public void setLeftX(float leftX) { this.leftX = leftX; }

    public float getLeftY() { return leftY; }
    public void setLeftY(float leftY) { this.leftY = leftY; }

    public float getRightX() { return rightX; }
    public void setRightX(float rightX) { this.rightX = rightX; }

    public float getRightY() { return rightY; }
    public void setRightY(float rightY) { this.rightY = rightY; }

    public float getTriggerLeft() { return triggerLeft; }
    public void setTriggerLeft(float triggerLeft) { this.triggerLeft = triggerLeft; }

    public float getTriggerRight() { return triggerRight; }
    public void setTriggerRight(float triggerRight) { this.triggerRight = triggerRight; }
}
