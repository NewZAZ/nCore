package fr.newzproject.core.storage.disk.toml;

import com.moandjiezana.toml.Toml;
import fr.newzproject.core.NCore;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class TomlUtils {

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Toml getConfiguration(File file){
        Plugin plugin = NCore.getInstance();

        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }

        if(!file.exists()){
            try {
                Files.copy(plugin.getResource(file.getName()), file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Toml(new Toml().read(plugin.getResource(file.getName()))).read(file);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Toml getConfiguration(String path){
        Plugin plugin = NCore.getInstance();

        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }
        File file = new File(path);
        if(!file.exists()){
            try {
                Files.copy(plugin.getResource(file.getName()), file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Toml(new Toml().read(plugin.getResource(file.getName()))).read(file);
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Toml getConfiguration(String parent, String child){
        Plugin plugin = NCore.getInstance();

        if(!plugin.getDataFolder().exists()){
            plugin.getDataFolder().mkdirs();
        }
        File file = new File(parent, child);
        if(!file.exists()){
            try {
                Files.copy(plugin.getResource(file.getName()), file.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return new Toml(new Toml().read(plugin.getResource(file.getName()))).read(file);
    }
}
