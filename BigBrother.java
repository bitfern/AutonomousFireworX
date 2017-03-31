/**
 * Created by Administrator on 2017-03-21.
 */

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.World;

public class BigBrother implements org.bukkit.event.Listener {

    public String Name = "[Autonomous Fireworks]";

    // Join message

    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.AQUA + "Welcome " + event.getPlayer().getDisplayName() + " to the server" );

    }

    // End of Join message

    @EventHandler
    public void onPlayerItemBreakEvent (PlayerItemBreakEvent event) {

        // Create Doubles to store player location
        Double X;
        Double Y;
        Double Z;
        // Get X,Y,Z and assign to correct Double
        X = event.getPlayer().getLocation().getX();
        Y = event.getPlayer().getLocation().getY();
        Z = event.getPlayer().getLocation().getZ();
        // Explosion power
        float power = 28;

        // Create Explosion on players position
        event.getPlayer().getWorld().createExplosion(X, Y, Z, power);

        event.getPlayer().sendMessage(ChatColor.RED + "BOOM!" + ChatColor.YELLOW + "Your tool broke and exploded" );

        //                                                 TEMPORARY
/*
        if () {
            System.out.println(Name + "Success");
        }
        else {
            System.out.println(Name + "FAIL");
        }
*/
        //                                               END OF TEMPORARY
    }
}
