package com.hisuie08.nicechat;


import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mod(NiceChat.MOD_ID)
public class NiceChat
{
    public static final String MOD_ID = "nicechat";
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MESSAGE_INSTEAD_OF_HIDE = I18n.format("message.nicechat.hidecontents");
    public static final String CATEGORY_CONFIG_NICECHAT = I18n.format("key.categories.nicechat");
    public static final String LOAD_CONFIG = I18n.format("key.nicechat.config.reload");
    public static final String MESSAGE_LOAD_SUCCESSFUL = I18n.format("message.nicechat.reload");
    public static KeyBinding CONFIG_KEY = new KeyBinding(LOAD_CONFIG,78,CATEGORY_CONFIG_NICECHAT);

    public NiceChat() throws IOException {
        MinecraftForge.EVENT_BUS.register(this);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onSideSetup);
    }
    public void onSideSetup(final FMLClientSetupEvent event){
        try {
            NiceConfig.init();
        } catch (IOException ex) {
            LOGGER.error("Errors occurred in creating new config files", ex);
        }
        config = new NiceConfig();
        loadConfig();
        Minecraft.getInstance().gameSettings.keyBindings = ArrayUtils.add(Minecraft.getInstance().gameSettings.keyBindings
                , CONFIG_KEY);
    }

    @SubscribeEvent
    public void onConfigKey(InputEvent.KeyInputEvent event){
        if (event.getKey() == CONFIG_KEY.getKey().getKeyCode() && event.getAction() == 1){
            loadConfig();
            Minecraft.getInstance().ingameGUI.getChatGUI().printChatMessage(new TranslationTextComponent(MESSAGE_LOAD_SUCCESSFUL));
        }
    }
    @SubscribeEvent
    public void onChatReceived(ClientChatReceivedEvent event) {
        if(event.getType() == ChatType.CHAT){
            if(catchSenderFilter(event.getSenderUUID())) {
                event.setMessage(new TranslationTextComponent(MESSAGE_INSTEAD_OF_HIDE));
            }
            else if (catchContentFilter(event.getMessage().getString())){
                event.setMessage(new TranslationTextComponent(MESSAGE_INSTEAD_OF_HIDE));
            }
        }
    }

    private NiceConfig config;

    private final List<String> ignoreContents = new ArrayList<>();
    private final List<UUID> ignoreUUIDs = new ArrayList<>();

    private void loadConfig() {
        try {
            ignoreContents.clear();
            ignoreContents.addAll(config.loadContent());
        }catch (IOException ex) {
            LOGGER.error("Could not load ignore contents");
        }
        try {
            ignoreUUIDs.clear();
            ignoreUUIDs.addAll(config.loadUUIDs());
        }catch (IOException ex) {
            LOGGER.error("Could not load ignore UUIDs.");
        }
    }

    private boolean catchSenderFilter(@Nullable UUID sender) {
        if(sender == null) return false;
        return ignoreUUIDs.stream()
                .anyMatch(sender::equals);
    }
    private boolean catchContentFilter(String chat) {
        return ignoreContents.stream()
                .anyMatch(chat::matches);
    }



}
