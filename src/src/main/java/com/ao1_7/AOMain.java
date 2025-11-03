package com.ao1_7;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.SidedProxy;

@Mod(modid = "animationoverhaul17", name = "Animation Overhaul 1.7.10 Edition", version = "1.0.0")
public class AOMain {
    @SidedProxy(clientSide = "com.ao1_7.ClientProxy", serverSide = "com.ao1_7.CommonProxy")
    public static CommonProxy proxy;

    @EventHandler
    public void preInit(FMLPreInitializationEvent evt) {
        proxy.preInit(evt);
    }

    @EventHandler
    public void init(FMLInitializationEvent evt) {
        proxy.init(evt);
    }
}
