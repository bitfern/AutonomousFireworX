
//Imports Plugin Class
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    // Plugin Name String
    public String Name = "[Autonomous Fireworks]";

    public int countdown = 10000;

    @Override
    public void onEnable() {
        // BEGIN STARTUP
        System.out.println(Name + "    Starting");

        while(countdown > 0){
            countdown=countdown-1;
        }

        getServer().getPluginManager().registerEvents(new BigBrother(), this);


        // INITIALIZATION DONE
        System.out.println(Name + "    Started");
    }

    //Logon Message



    @Override
    public void onDisable(){
        //Runs on shutdown
        System.out.println(Name + "    Shutting down " + Name);
    }
}