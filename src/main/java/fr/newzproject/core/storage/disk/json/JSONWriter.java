package fr.newzproject.core.storage.disk.json;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class JSONWriter {

    private final JSONArray objects;

    public JSONWriter() {
        this.objects = new JSONArray();
    }

    public void addObject(JSONObject obj) {
        this.objects.add(obj);
    }

    public void removeObject(JSONObject obj) {
        this.objects.remove(obj);
    }

    public void writeFile(File file) {
        if (file.exists()) {
            file.delete();
        }
        try {
            final FileWriter writer = new FileWriter(file);
            writer.write(this.objects.toJSONString());
            writer.flush();
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clear() {
        this.objects.clear();
    }
}
