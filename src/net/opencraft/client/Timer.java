package net.opencraft.client;

import net.opencraft.config.GameConfig;

public class Timer {

    public static final short NANO_SECOND = 1000;

    public static int fpsCalc = 0;

    public static int frames = 60;
    public static int pframes = 60;

    public static long time;
    public static long ticks = 0;

    public static void tick() {

        frames++;
        if (System.currentTimeMillis() > time + NANO_SECOND) {

            if (frames != pframes) {
                if (GameConfig.SYNC > 0) {
                    frames = GameConfig.SYNC;
                    fpsCalc = GameConfig.SYNC;
                    printFPS();
                } else {
                    printFPS();
                    calculateFPS();
                }
            }

            pframes = frames;
            ticks++;
            time = System.currentTimeMillis();
            frames = 0;
        }

    }

    public static void printFPS() {

        String fpsCalc_str = "";
        if (GameConfig.GRAPHICS_CONFIG != GameConfig.GraphicsConfig.FAST) {
            fpsCalc_str = " | Media de FPS: " + fpsCalc;
        }

        System.out.println(frames + " FPS" + fpsCalc_str);
    }

    private static void calculateFPS() {
        if (GameConfig.GRAPHICS_CONFIG != GameConfig.GraphicsConfig.FAST) {
            fpsCalc += frames;
            fpsCalc = Math.round((float) fpsCalc / 2);
        }
    }

}
