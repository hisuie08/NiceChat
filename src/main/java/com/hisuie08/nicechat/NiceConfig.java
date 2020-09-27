package com.hisuie08.nicechat;

import net.minecraftforge.fml.loading.FMLPaths;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NiceConfig {
    public static File NiceConfigDir = new File(FMLPaths.CONFIGDIR.get().toFile(),"NiceChat");
    public static File ignoreWordsFile = new File(NiceConfigDir,"/ignores.txt");
    public static List<String> ignoreList = new ArrayList<>();

    public static void Init() throws IOException {
        if(!NiceConfigDir.exists()){
            NiceConfigDir.mkdirs();
            NiceChat.LOGGER.info(NiceConfigDir);
        }
        if(!ignoreWordsFile.exists()){
            ignoreWordsFile.createNewFile();
        }
    }
    public static List<String> load() throws FileNotFoundException {
        try (BufferedReader br = new BufferedReader(new FileReader(ignoreWordsFile))) {
            String text;
            while ((text = br.readLine()) != null) {
                ignoreList.add(text);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ignoreList;
    }
}
