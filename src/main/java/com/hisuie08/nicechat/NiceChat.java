package com.hisuie08.nicechat;


import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import static org.lwjgl.glfw.GLFW.GLFW_KEY_N;

@Mod(NiceChat.MODID)
public class NiceChat
{
    public static final String MODID = "nicechat";
    public static final Logger LOGGER = LogManager.getLogger();
    public static List<String> ignoreContentsList;
    public static List<String> ignoreUUIDList;
    public static String messageInsteadOfHide = I18n.format("message.nicechat.hidecontents");

    public NiceChat() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onSideSetup);
        NiceConfig.Init();
        LOGGER.info(new TranslationTextComponent(messageInsteadOfHide));
    }
    public void onSideSetup(final FMLClientSetupEvent event){
    }

    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) throws FileNotFoundException {
        ignoreContentsList = new NiceConfig().loadContent();
        ignoreUUIDList = new NiceConfig().loadUUID();
        if(event.getType() == ChatType.CHAT){
            if(catchSenderFilter(event.getSenderUUID().toString())) {
                event.setMessage(new TranslationTextComponent(messageInsteadOfHide));
                return;
            }
            else if (catchContentFilter(event.getMessage().getString())){
                event.setMessage(new TranslationTextComponent(messageInsteadOfHide));
                return;
            }

        }
    }
    private boolean catchSenderFilter(String sender){
        boolean flag = false;
        for (String u: ignoreUUIDList){
            if(sender.equals(u)) {
                flag = true;
                break;
            }
        }
        return flag;

    }
    private boolean catchContentFilter(String chat){
        boolean flag = false;
        for (String s: ignoreContentsList){
            if(chat.matches(s)) {
                flag = true;
                break;
            }
        }
        return flag;
    }



}
