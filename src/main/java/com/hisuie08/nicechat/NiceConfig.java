package com.hisuie08.nicechat;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class NiceConfig {
    public static Path NICE_CONFIG_DIR = FMLPaths.CONFIGDIR.get().resolve("NiceChat");
    public static Path IGNORE_WORDS_FILE = NICE_CONFIG_DIR.resolve("ignoreContents.txt");
    public static Path IGNORE_UUIDS_FILE = NICE_CONFIG_DIR.resolve("ignoreUUID.txt");

    public static void init() throws IOException {
        if(Files.notExists(NICE_CONFIG_DIR)){
            Files.createDirectories(NICE_CONFIG_DIR);
            NiceChat.LOGGER.debug(NICE_CONFIG_DIR);
        }
        if(Files.notExists(IGNORE_WORDS_FILE)){
            Files.createFile(IGNORE_WORDS_FILE);
        }
        if(Files.notExists(IGNORE_UUIDS_FILE)){
            Files.createFile(IGNORE_UUIDS_FILE);
        }
    }

    public List<String> loadContent() throws IOException {
        return Files.readAllLines(IGNORE_WORDS_FILE);
    }

    public List<UUID> loadUUIDs() throws IOException {
        return Files.readAllLines(IGNORE_UUIDS_FILE)
                .stream()
                .map(UUID::fromString)
                .collect(Collectors.toList());
    }
}
