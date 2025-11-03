package com.ao1_7.render;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.client.event.RenderPlayerEvent;
import com.ao1_7.animation.AnimationController;
import com.ao1_7.animation.AnimationState;
import com.ao1_7.animation.AnimationUtils;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderPlayer;
import java.lang.reflect.Field;

public class HookRenderPlayer {
    private final AnimationController controller;
    private Field fieldModelBipedMain = null;

    public HookRenderPlayer(AnimationController controller) {
        this.controller = controller;
        try {
            fieldModelBipedMain = RenderPlayer.class.getDeclaredField("modelBipedMain");
            fieldModelBipedMain.setAccessible(true);
        } catch (NoSuchFieldException e) {
            try {
                fieldModelBipedMain = RenderPlayer.class.getDeclaredField("field_77071_a");
                fieldModelBipedMain.setAccessible(true);
            } catch (Exception ex) {
                fieldModelBipedMain = null;
            }
        }
    }

    @SubscribeEvent
    public void onRenderPlayerPre(RenderPlayerEvent.Pre event) {
        try {
            RenderPlayer rp = (RenderPlayer) event.renderer;
            ModelBiped model = null;
            if (fieldModelBipedMain != null) {
                model = (ModelBiped) fieldModelBipedMain.get(rp);
            }
            if (model == null) return;

            AnimationState s = controller.getStateFor(event.entityPlayer);

            // blend sprint (inclinar tronco e braços)
            float sprint = s.sprintProgress;
            model.bipedBody.rotateAngleX = AnimationUtils.lerp(model.bipedBody.rotateAngleX, -0.25F * sprint, 0.4f);
            model.bipedRightArm.rotateAngleX = AnimationUtils.lerp(model.bipedRightArm.rotateAngleX, -0.3F * sprint, 0.4f);
            model.bipedLeftArm.rotateAngleX = AnimationUtils.lerp(model.bipedLeftArm.rotateAngleX, -0.3F * sprint, 0.4f);

            // jump
            float jump = s.jumpProgress;
            model.bipedRightLeg.rotateAngleX = AnimationUtils.lerp(model.bipedRightLeg.rotateAngleX, -0.5F * jump, 0.5f);
            model.bipedLeftLeg.rotateAngleX = AnimationUtils.lerp(model.bipedLeftLeg.rotateAngleX, -0.5F * jump, 0.5f);
            model.bipedRightArm.rotateAngleX += -0.2F * jump;
            model.bipedLeftArm.rotateAngleX += -0.2F * jump;

            // attack (additive)
            float attack = s.attackProgress;
            model.bipedRightArm.rotateAngleX += -1.2F * attack;

            // head tracking
            model.bipedHead.rotateAngleY = (float)Math.toRadians(s.headYaw - event.entityPlayer.renderYawOffset);
            model.bipedHead.rotateAngleX = (float)Math.toRadians(s.headPitch);

        } catch (Exception e) {
            // fail-safe: avoid crashing render loop
        }
    }

    @SubscribeEvent
    public void onRenderPlayerPost(RenderPlayerEvent.Post event) {
        // optional: restore or apply post effects
    }
}
