package com.ao1_7.animation;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;
import com.google.gson.JsonParser;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.util.logging.Logger;

// Simple loader that reads JSON files from assets/animationoverhaul/animations/
// and stores raw JsonObjects keyed by name. This example uses GSON - ensure
// your build includes gson (older Forge MDKs included it).
public class AnimationDataLoader {
    private static final Logger logger = Logger.getLogger("AO17:Loader");
    private final Map<String, JsonObject> animations = new HashMap<String, JsonObject>();

    public void loadAll() {
        String[] files = new String[] {"walk.json","run.json","jump.json","attack.json","idle.json"};
        for (String f : files) {
            try {
                String path = "/assets/animationoverhaul/animations/" + f;
                InputStream is = this.getClass().getResourceAsStream(path);
                if (is == null) {
                    logger.info("Animation file not found: " + path);
                    continue;
                }
                InputStreamReader r = new InputStreamReader(is, "UTF-8");
                JsonElement e = new JsonParser().parse(r);
                if (e != null && e.isJsonObject()) {
                    animations.put(f.replaceAll("\\.json$",""), e.getAsJsonObject());
                }
                r.close();
                is.close();
            } catch (Exception ex) {
                logger.warning("Failed loading animation " + f + " : " + ex.getMessage());
            }
        }
    }

    public JsonObject get(String name) {
        return animations.get(name);
    }
}
