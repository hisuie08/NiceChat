package com.hisuie08.nicechat;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.config.ModConfig;


public class NiceConfig extends ModConfig {

    public static ForgeConfigSpec CLIENT;
    public NiceConfig(Type type, ForgeConfigSpec spec, ModContainer container, String fileName) {
        super(type, spec, container, fileName);
    }
}
