package com.hisuie08.nicechat;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NiceConfig {
    public static File NiceConfigDir = new File(FMLPaths.CONFIGDIR.get().toFile(),"NiceChat");
    public static File ignoreWordsFile = new File(NiceConfigDir,"/ignoreContents.txt");
    public static File ignoreUUIDFile = new File(NiceConfigDir, "/ignoreUUID.txt");
    public static List<String> ignoreContentsList = new ArrayList<>();
    public static List<String> ignoreUUIDList = new ArrayList<>();

    public static void Init() throws IOException {
        if(!NiceConfigDir.exists()){
            NiceConfigDir.mkdirs();
            NiceChat.LOGGER.info(NiceConfigDir);
        }
        if(!ignoreWordsFile.exists()){
            ignoreWordsFile.createNewFile();
        }
        if(!ignoreUUIDFile.exists()){
            ignoreUUIDFile.createNewFile();
        }
    }
    public static List<String> loadContent() throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(ignoreWordsFile))) {
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
        try (BufferedReader br = new BufferedReader(new FileReader(ignoreUUIDFile))) {
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
