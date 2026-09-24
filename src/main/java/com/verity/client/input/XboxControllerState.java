package com.verity.client.input;

import org.lwjgl.glfw.GLFW;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/**
 * Reads the first GLFW joystick as a generic Xbox-style gamepad.
 */
public final class XboxControllerState {
    private static final int JOYSTICK = GLFW.GLFW_JOYSTICK_1;

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

    public void poll() {
        connected = GLFW.glfwJoystickPresent(JOYSTICK);
        if (!connected) {
            clear();
            return;
        }

        FloatBuffer axes = GLFW.glfwGetJoystickAxes(JOYSTICK);
        ByteBuffer buttons = GLFW.glfwGetJoystickButtons(JOYSTICK);

        leftX = axis(axes, 0);
        leftY = axis(axes, 1);
        rightX = axis(axes, 2);
        rightY = axis(axes, 3);
        triggerLeft = axis(axes, 4);
        triggerRight = axis(axes, 5);

        aPressed = button(buttons, 0);
        bPressed = button(buttons, 1);
        xPressed = button(buttons, 2);
        yPressed = button(buttons, 3);
        backPressed = button(buttons, 6);
        startPressed = button(buttons, 7);
        rtPressed = triggerRight > 0.35F || button(buttons, 5);
        ltPressed = triggerLeft > 0.35F || button(buttons, 4);
    }

    private static float axis(FloatBuffer data, int index) {
        if (data == null || index < 0 || index >= data.limit()) {
            return 0.0F;
        }
        return data.get(index);
    }

    private static boolean button(ByteBuffer data, int index) {
        if (data == null || index < 0 || index >= data.limit()) {
            return false;
        }
        return data.get(index) == GLFW.GLFW_PRESS;
    }

    private void clear() {
        connected = false;
        startPressed = false;
        backPressed = false;
        aPressed = false;
        bPressed = false;
        xPressed = false;
        yPressed = false;
        rtPressed = false;
        ltPressed = false;
        leftX = 0.0F;
        leftY = 0.0F;
        rightX = 0.0F;
        rightY = 0.0F;
        triggerLeft = 0.0F;
        triggerRight = 0.0F;
    }

    public boolean isConnected() { return connected; }
    public boolean isStartPressed() { return startPressed; }
    public boolean isBackPressed() { return backPressed; }
    public boolean isAPressed() { return aPressed; }
    public boolean isBPressed() { return bPressed; }
    public boolean isXPressed() { return xPressed; }
    public boolean isYPressed() { return yPressed; }
    public boolean isRtPressed() { return rtPressed; }
    public boolean isLtPressed() { return ltPressed; }
    public float getLeftX() { return leftX; }
    public float getLeftY() { return leftY; }
    public float getRightX() { return rightX; }
    public float getRightY() { return rightY; }
    public float getTriggerLeft() { return triggerLeft; }
    public float getTriggerRight() { return triggerRight; }
}
