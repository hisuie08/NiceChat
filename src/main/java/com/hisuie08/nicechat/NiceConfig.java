package com.hisuie08.nicechat;

import me.shedaniel.clothconfig2.forge.api.ConfigBuilder;
import me.shedaniel.clothconfig2.forge.api.ConfigCategory;
import me.shedaniel.clothconfig2.forge.api.ConfigEntryBuilder;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;

import java.util.concurrent.atomic.AtomicReference;


public class NiceConfig {
    public NiceConfig(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.create()
                .setParentScreen(parent)
                .setTitle(ITextComponent.func_244388_a("title."+NiceChat.MODID+".config"));
        builder.setSavingRunnable(() -> {
            // Serialise the config into the config file. This will be called last after all variables are updated.
        });
        ConfigCategory general = builder.getOrCreateCategory(ITextComponent.func_244388_a("category."+NiceChat.MODID+".general"));
        ConfigEntryBuilder entryBuilder = builder.entryBuilder();
        AtomicReference<String> currentValue = new AtomicReference<>("abc");
        general.addEntry(entryBuilder.startStrField(ITextComponent.func_244388_a("option.examplemod.optionA"), currentValue.get())
                .setDefaultValue("This is the default value") // Recommended: Used when user click "Reset"
                .setTooltip(ITextComponent.func_244388_a("This option is awesome!")) // Optional: Shown when the user hover over this option
                .setSaveConsumer(newValue -> currentValue.set(newValue)) // Recommended: Called when user save the config
                .build()); // Builds the option entry for cloth config

    }
}