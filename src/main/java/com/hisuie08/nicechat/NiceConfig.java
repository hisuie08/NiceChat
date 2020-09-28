package com.hisuie08.nicechat;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NiceConfig {
    public static File NICE_CONFIG_DIR = new File(FMLPaths.CONFIGDIR.get().toFile(),"NiceChat");
    public static File IGNORE_WORDS_FILE = new File(NICE_CONFIG_DIR,"/ignoreContents.txt");
    public static File IGNORE_UUIDS_FILE = new File(NICE_CONFIG_DIR, "/ignoreUUID.txt");
    public static List<String> ignoreContentsList = new ArrayList<>();
    public static List<String> ignoreUUIDList = new ArrayList<>();

    public static void init() throws IOException {
        if(!NICE_CONFIG_DIR.exists()){
            NICE_CONFIG_DIR.mkdirs();
            NiceChat.LOGGER.info(NICE_CONFIG_DIR);
        }
        if(!IGNORE_WORDS_FILE.exists()){
            IGNORE_WORDS_FILE.createNewFile();
        }
        if(!IGNORE_UUIDS_FILE.exists()){
            IGNORE_UUIDS_FILE.createNewFile();
        }
    }
    public static List<String> loadContent() throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(IGNORE_WORDS_FILE))) {
            String text;
            while ((text = br.readLine()) != null) {
                ignoreContentsList.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ignoreContentsList;
    }
    public static List<String> loadUUID() throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(IGNORE_UUIDS_FILE))) {
            String text;
            while ((text = br.readLine()) != null) {
                ignoreUUIDList.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ignoreUUIDList;
    }
}
