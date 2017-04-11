/**
 * Created by Administrator on 2017-04-11.
 */
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class ReturnItems extends BukkitRunnable {
    public Player p;

    public ReturnItems(Player player) {
        this.p =  player;
    }

    @Override
    public void run() {
        p.getInventory().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
        p.getInventory().getItemInMainHand().setDurability((short)1611);
    }

}