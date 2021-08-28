package fr.newzproject.core.storage.disk.json;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class JSONReader {

    public JSONArray readFile(File file) {
        try (final FileReader reader = new FileReader(file)) {
            final JSONParser parser = new JSONParser();
            final Object object = parser.parse(reader);
            return (JSONArray)object;
        }
        catch (IOException | ParseException ex2) {
            ex2.printStackTrace();
            return null;
        }
    }

    public static Integer intFromObject(Object obj) {
        return Integer.valueOf(String.valueOf(obj));
    }

    public static Double doubleFromObject(Object obj) {
        return Double.valueOf(String.valueOf(obj));
    }

    public static String stringFromObject(Object obj) {
        return String.valueOf(obj);
    }

    public static Long longFromObject(Object obj) {
        return Long.valueOf(String.valueOf(obj));
    }
}
