package com.ao1_7.animation;

public class AnimationState {
    public float limbSwing;
    public float prevLimbSwing;
    public float headYaw;
    public float headPitch;
    public float sprintProgress;
    public float jumpProgress;
    public float attackProgress;

    public AnimationState() {
        limbSwing = prevLimbSwing = headYaw = headPitch = sprintProgress = jumpProgress = attackProgress = 0f;
    }

    public void copyFrom(AnimationState other) {
        this.limbSwing = other.limbSwing;
        this.prevLimbSwing = other.prevLimbSwing;
        this.headYaw = other.headYaw;
        this.headPitch = other.headPitch;
        this.sprintProgress = other.sprintProgress;
        this.jumpProgress = other.jumpProgress;
        this.attackProgress = other.attackProgress;
    }

    public void lerpTo(AnimationState target, float t) {
        this.limbSwing += (target.limbSwing - this.limbSwing) * t;
        this.headYaw += (target.headYaw - this.headYaw) * t;
        this.headPitch += (target.headPitch - this.headPitch) * t;
        this.sprintProgress += (target.sprintProgress - this.sprintProgress) * t;
        this.jumpProgress += (target.jumpProgress - this.jumpProgress) * t;
        this.attackProgress += (target.attackProgress - this.attackProgress) * t;
    }
}
