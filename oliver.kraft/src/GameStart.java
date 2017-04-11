/**
 * Created by Administrator on 2017-04-11.
 */
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Array;

public class GameStart extends BukkitRunnable {
    private Array onlineplayers;

    public GameStart(Array onlineplayers) {
        this.onlineplayers = onlineplayers;
    }

    @Override
    public void run() {

    }

}