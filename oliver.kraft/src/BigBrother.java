/**
 * Created by Administrator on 2017-03-21.
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.*;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffectType;

// TASK SCHEDULING
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;


public class BigBrother implements org.bukkit.event.Listener {

    private final AutonomousFireworks plugin;
    public String Name = "[Autonomous Fireworks]";

    public BigBrother(AutonomousFireworks plugin) {
        this.plugin = plugin;
        this.plugin.getServer().getPluginManager().registerEvents(this, plugin);
    }

    // Join message

    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent event) {

        final Player p = event.getPlayer();

        event.setJoinMessage(ChatColor.AQUA + "Welcome " + p.getDisplayName() + " to the " + ChatColor.RED + ChatColor.BOLD + ChatColor.UNDERLINE + "BETA" + ChatColor.RESET + ChatColor.AQUA + " FireWorX server" );

        // Move to Round Start when possible !!!

        // EQUIP Armor

        p.getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        p.getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        p.getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

        System.out.println("Armor Given to @" + event.getPlayer().getDisplayName());

        // Enchant armor with Protection

        p.getEquipment().getHelmet().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        p.getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        p.getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        p.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);

        System.out.println("Armor enchanted for @" + event.getPlayer().getDisplayName());

        // Fall protection

        p.getEquipment().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);

        System.out.println("Fall damage disabled for @" + event.getPlayer().getDisplayName());

        // Armor message

        p.sendMessage(ChatColor.YELLOW + "[FireWorX][BETA] Armor Equipped");

        p.setFoodLevel(20);
        p.setSaturation(20);

        // END of Move to Round Start when possible !!!

        //onlineplayers++;

        p.sendMessage(ChatColor.YELLOW + Integer.toString(Bukkit.getServer().getOnlinePlayers().toArray().length) + ChatColor.AQUA + " Players online");

        // Give Pickaxe

        p.getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
        p.getEquipment().getItemInMainHand().setDurability((short) 1611);

        if (Bukkit.getServer().getOnlinePlayers().toArray().length > 4) {
            BukkitTask gamestart = new GameStart(Bukkit.getServer().getOnlinePlayers().toArray()).runTaskLater(this.plugin, 20);
        }

        else {

        }

    }

    // End of Join message

    //Player Leave Event

    @EventHandler
    public void onPlayerLeaveEvent (PlayerQuitEvent event) {

        String playerleft1;

        // Plural for players on exit message

        if (Bukkit.getServer().getOnlinePlayers().toArray().length < 2) {

            // Possibly check for 0 aswell

            playerleft1 = " player online";

        }

        else {

            playerleft1 = " players online";

        }
        //onlineplayers--;

        event.setQuitMessage(ChatColor.AQUA + event.getPlayer().getDisplayName() + " just left the Server " + Integer.toString(Bukkit.getServer().getOnlinePlayers().toArray().length) + playerleft1);


    }

    // End of Player Leave Event

    //Start of Death Event

    @EventHandler
    public void onPlayerDeath (PlayerDeathEvent event) {

        event.getEntity().getPlayer().sendMessage(ChatColor.AQUA + "You have been eliminated from the game. Feel Free to stick around in spectator mode or type /lobby to go back to the server lobby");

        event.getEntity().getPlayer().setGameMode(GameMode.SPECTATOR);

    }

    //End of Death Event

    // Start of Tool Explosions
    @EventHandler
    public void onPlayerItemBreakEvent (PlayerItemBreakEvent event) {

        final Player p = event.getPlayer();


        // Create Doubles to store player location
        Double X;
        Double Y;
        Double Z;
        // Get X,Y,Z and assign to correct Double
        X = event.getPlayer().getLocation().getX();
        Y = event.getPlayer().getLocation().getY();
        Z = event.getPlayer().getLocation().getZ();
        // Explosion power
        float power = 4F;

        // Delete item in case player dies from Explosion
        event.getBrokenItem().setAmount(0);

        // Create Explosion on players position
        event.getPlayer().getWorld().createExplosion(X, Y, Z, power, false, false );

        // Chat Message
        // DEBUG event.getPlayer().sendMessage(ChatColor.RED + "BOOM! " + ChatColor.YELLOW + "Your tool exploded" );

        // Keep Player Alive

        event.getPlayer().setSaturation(20);
        event.getPlayer().setHealth(20);
        event.getPlayer().setFoodLevel(20);

        // Give back broken diamond pickaxe

        BukkitTask returnitem = new ReturnItems(p).runTaskLater(this.plugin, 100);
        // DEBUG p.sendMessage(ChatColor.RED + "DONE!");

    }
    // End of Tool Explosions
}
