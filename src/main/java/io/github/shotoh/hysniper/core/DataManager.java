package io.github.shotoh.hysniper.core;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.shotoh.hysniper.HySniper;

import java.io.FileWriter;
import java.io.IOException;

public class DataManager {
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /*
    public static void save(HySniper mod) { // todo replace attribute checker with only shard prices or clear and save
        try {
            FileWriter writer = new FileWriter("./config/hysniper/attributes.json");
            gson.toJson(mod.getAttributeChecker(), writer);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
    public static void load(HySniper mod) {
        try {
            FileReader reader = new FileReader("./config/hysniper/attributes.json");
            mod.setAttributeChecker(gson.fromJson(reader, AttributeChecker.class));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            new File("./config/hysniper/attributes.json");
        }
    }

     */
}
