package com.hisuie08.nicechat;


import net.minecraft.client.Minecraft;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLConfig;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Mod(NiceChat.MODID)
public class NiceChat
{
    public static final String MODID = "nicechat";
    public static final Logger LOGGER = LogManager.getLogger();



    public NiceChat() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        NiceConfig.Init();
    }
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) throws FileNotFoundException {
//        NiceChat.LOGGER.info("Server: " + event.getMessage());
        if(event.getType() == ChatType.CHAT){
            NiceChat.LOGGER.info(event.getMessage());
            List ng = new NiceConfig().load();
            if(ng.contains(event.getMessage().getString())) {
                event.setMessage(ITextComponent.func_244388_a("well!"));
            }
        }
        LOGGER.info(Minecraft.getInstance().gameDir.getName());

    }

}
