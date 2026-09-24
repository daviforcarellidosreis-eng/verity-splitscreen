package com.verity.client.input;

import org.lwjgl.glfw.GLFW;

import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

/** Polls the first GLFW joystick. GLFW exposes Xbox-compatible XInput devices as a gamepad/joystick. */
public final class XboxControllerState {
    private static final int JOYSTICK = GLFW.GLFW_JOYSTICK_1;
    private static final int BUTTON_A = 0;
    private static final int BUTTON_B = 1;
    private static final int BUTTON_X = 2;
    private static final int BUTTON_Y = 3;
    private static final int BUTTON_BACK = 6;
    private static final int BUTTON_START = 7;

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
        boolean wasStart = startPressed;
        boolean wasBack = backPressed;
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

        aPressed = button(buttons, BUTTON_A);
        bPressed = button(buttons, BUTTON_B);
        xPressed = button(buttons, BUTTON_X);
        yPressed = button(buttons, BUTTON_Y);
        startPressed = button(buttons, BUTTON_START) && !wasStart;
        backPressed = button(buttons, BUTTON_BACK) && !wasBack;
        rtPressed = triggerRight > 0.35F || button(buttons, 5);
        ltPressed = triggerLeft > 0.35F || button(buttons, 4);
    }

    private static float axis(FloatBuffer values, int index) {
        return values != null && index < values.limit() ? values.get(index) : 0.0F;
    }

    private static boolean button(ByteBuffer values, int index) {
        return values != null && index < values.limit() && values.get(index) == GLFW.GLFW_PRESS;
    }

    private void clear() {
        startPressed = false; backPressed = false; aPressed = false; bPressed = false;
        xPressed = false; yPressed = false; rtPressed = false; ltPressed = false;
        leftX = leftY = rightX = rightY = triggerLeft = triggerRight = 0.0F;
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
