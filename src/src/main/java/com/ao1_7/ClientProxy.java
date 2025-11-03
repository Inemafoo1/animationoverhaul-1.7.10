package com.ao1_7;

import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;

public class ClientProxy extends CommonProxy {
    private com.ao1_7.animation.AnimationController controller;

    @Override
    public void preInit(FMLPreInitializationEvent evt) {
        super.preInit(evt);
        controller = new com.ao1_7.animation.AnimationController();
    }

    @Override
    public void init(FMLInitializationEvent evt) {
        super.init(evt);
        // registrar hook de render
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(new com.ao1_7.render.HookRenderPlayer(controller));
        net.minecraftforge.common.MinecraftForge.EVENT_BUS.register(controller);
    }
}
