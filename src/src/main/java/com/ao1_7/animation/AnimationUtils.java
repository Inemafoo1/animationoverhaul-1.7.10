package com.ao1_7.animation;

public class AnimationUtils {
    public static float lerp(float a, float b, float t) {
        return a + (b - a) * t;
    }

    public static float smoothstep(float edge0, float edge1, float x) {
        float t = clamp((x - edge0) / (edge1 - edge0), 0.0F, 1.0F);
        return t * t * (3 - 2 * t);
    }

    public static float clamp(float v, float a, float b) {
        return v < a ? a : (v > b ? b : v);
    }

    public static float easeOutCubic(float t) {
        t -= 1f;
        return t * t * t + 1f;
    }
}
