package simple;

import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;

public class MouseListener {
    private static MouseListener instance;
    private double scrollX, scrollY;
    private double posX, posY, lastX, lastY;
    private boolean mousePressed[] = new boolean[3];
    private boolean dragging;

    private MouseListener() {
        this.scrollX = 0.0;
        this.scrollY = 0.0;
        this.posX = 0.0;
        this.posY = 0.0;
        this.lastX = 0.0;
        this.lastY = 0.0;

    }

    public static MouseListener get () {
        if (instance == null) {
            instance = new MouseListener();
        }
        return instance;
    }
    public static void cursorPosCallback(long window, double posX, double posY) {
        get().lastX = get().posX;
        get().lastY = get().posY;
        get().posX = posX;
        get().posY = posY;
        get().dragging = get().mousePressed[0] || get().mousePressed[1] || get().mousePressed[2];
    }
    public static void mouseButtonCallback(long window, int button, int action, int mods) {
        if (action == GLFW_PRESS) {
            get().mousePressed[button] = true;
        } else if (action == GLFW_RELEASE) {
            get().mousePressed[button] = false;
            get().dragging = false;
        }
    }
    public static void scrollCallback(long window, double xoffset, double yoffset){
        get().scrollX = xoffset;
        get().scrollY = yoffset;
    }
    public static void endFrame () {
        get().scrollX = 0;
        get().scrollY = 0;
        get().lastX = get().posX;
        get().lastY = get().posY;
    }

    public static float getPosX() {
        return (float) get().posX;
    }

    public static float getPosY() {
        return (float) get().posY;
    }
    public static float getDx() {
        return (float)(get().lastX - get().posX);
    }
    public static float getDy() {
        return (float)(get().lastY - get().posY);
    }

    public static float getScrollX() {
        return (float)get().scrollX;
    }

    public static float getScrollY() {
        return (float)get().scrollY;
    }
    public static boolean dragging() {
        return get().dragging;
    }
    public static boolean mouseButtonDown(int button) {
        return get().mousePressed[button];
    }
}

