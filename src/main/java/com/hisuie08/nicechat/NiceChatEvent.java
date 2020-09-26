package com.hisuie08.nicechat;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.NewChatGui;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NiceChat.MODID)
public class NiceChatEvent {
    @SubscribeEvent
    public void onClientChat(ClientChatEvent event){
        event.setCanceled(true);
        NiceChat.LOGGER.info("Client: "+ event.getMessage());
    }
    @SubscribeEvent
    public void onServerChat(ServerChatEvent event){
        event.setCanceled(true);
        event.setResult(Event.Result.ALLOW);
    }
}
