package com.ao1_7.animation;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.TickEvent.ClientTickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import java.util.WeakHashMap;
import java.util.Map;

public class AnimationController {
    private final AnimationState clientState = new AnimationState();
    private final Map<EntityPlayer, AnimationState> states = new WeakHashMap<EntityPlayer, AnimationState>();
    private final AnimationDataLoader loader = new AnimationDataLoader();

    public AnimationController() {
        loader.loadAll();
    }

    @SubscribeEvent
    public void onClientTick(ClientTickEvent evt) {
        Minecraft mc = Minecraft.getMinecraft();
        if (mc == null || mc.thePlayer == null) return;
        EntityPlayer p = mc.thePlayer;

        AnimationState target = computeTargetFromPlayer(p);
        clientState.lerpTo(target, 0.2f);
        states.put(p, clientState);
    }

    private AnimationState computeTargetFromPlayer(EntityPlayer p) {
        AnimationState s = new AnimationState();
        double dx = p.motionX;
        double dz = p.motionZ;
        float speed = (float)Math.sqrt(dx*dx + dz*dz);

        s.sprintProgress = p.isSprinting() ? 1.0f : Math.min(1.0f, speed * 4.0f);
        s.limbSwing = p.limbSwing;
        s.headYaw = p.rotationYawHead;
        s.headPitch = p.rotationPitch;
        s.jumpProgress = p.onGround ? 0f : Math.min(1f, (float)(p.motionY + 0.5));
        s.attackProgress = p.swingProgress;
        return s;
    }

    public AnimationState getStateFor(EntityPlayer p) {
        AnimationState s = states.get(p);
        if (s == null) {
            s = new AnimationState();
            states.put(p, s);
        }
        return s;
    }

    public AnimationState getClientState() {
        return clientState;
    }
}
