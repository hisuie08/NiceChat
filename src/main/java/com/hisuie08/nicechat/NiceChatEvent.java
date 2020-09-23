package com.hisuie08.nicechat;

import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.event.ServerChatEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = NiceChat.MODID)
public class NiceChatEvent {
    @SubscribeEvent
    public void onClientChat(ClientChatEvent event){
        NiceChat.LOGGER.info("Client: "+ event.getMessage());
    }
    @SubscribeEvent
    public void onServerChat(ServerChatEvent event){
        event.setResult(Event.Result.DENY);
    }
}
