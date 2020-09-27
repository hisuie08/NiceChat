package com.hisuie08.nicechat;


import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Mod(NiceChat.MODID)
public class NiceChat
{
    public static final String MODID = "nicechat";

    public static final Logger LOGGER = LogManager.getLogger();

    public NiceChat() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        MinecraftForge.EVENT_BUS.register(this);
    }
    private void doClientStuff(final FMLClientSetupEvent event) {}
    @SubscribeEvent
    public void onKeyToConfig(InputEvent.KeyInputEvent event){
        LOGGER.info(event.getKey());
    }
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event){
//        NiceChat.LOGGER.info("Server: " + event.getMessage());
        if(event.getType() == ChatType.CHAT){
            NiceChat.LOGGER.info(event.getMessage());
            event.setMessage(ITextComponent.func_244388_a("well!"));

        }

    }

}
