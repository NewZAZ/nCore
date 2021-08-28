package fr.newzproject.core.storage.disk.yaml;

import org.bukkit.configuration.file.YamlConfiguration;;

import javax.annotation.Nonnull;
import java.io.File;

public class YamlUtils {

    public static YamlConfiguration getConfiguration(@Nonnull File file){
        if(file.exists()){
            return YamlConfiguration.loadConfiguration(file);
        }
        file.mkdirs();
        return YamlConfiguration.loadConfiguration(file);
    }

    public static YamlConfiguration getConfiguration(String path){
        return getConfiguration(new File(path));
    }

    public static YamlConfiguration getConfiguration(String parent, String child){
        return getConfiguration(new File(parent, child));
    }
}
