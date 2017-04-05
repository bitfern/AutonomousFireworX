/**
 * Created by Administrator on 2017-03-21.
 */

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerItemBreakEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;


public class BigBrother implements org.bukkit.event.Listener {

    public String Name = "[Autonomous Fireworks]";


    public int onlineplayers = 0;


    // Join message

    @EventHandler
    public void onPlayerJoinEvent (PlayerJoinEvent event) {

        event.setJoinMessage(ChatColor.AQUA + "Welcome " + event.getPlayer().getDisplayName() + " to the " + ChatColor.RED + ChatColor.BOLD + ChatColor.UNDERLINE + "BETA" + ChatColor.RESET + ChatColor.AQUA + " FireWorX server" );

        // EQUIP Armor

        event.getPlayer().getEquipment().setHelmet(new ItemStack(Material.DIAMOND_HELMET));
        event.getPlayer().getEquipment().setChestplate(new ItemStack(Material.DIAMOND_CHESTPLATE));
        event.getPlayer().getEquipment().setLeggings(new ItemStack(Material.DIAMOND_LEGGINGS));
        event.getPlayer().getEquipment().setBoots(new ItemStack(Material.DIAMOND_BOOTS));

        System.out.println("Armor Given to @" + event.getPlayer().getDisplayName());

        // Enchant armor with Protection

        event.getPlayer().getEquipment().getHelmet().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        event.getPlayer().getEquipment().getChestplate().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        event.getPlayer().getEquipment().getLeggings().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);
        event.getPlayer().getEquipment().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 10);

        System.out.println("Armor enchanted for @" + event.getPlayer().getDisplayName());

        // Fall protection

        event.getPlayer().getEquipment().getBoots().addUnsafeEnchantment(Enchantment.PROTECTION_FALL, 10);

        System.out.println("Fall damage disabled for @" + event.getPlayer().getDisplayName());

        // Armor message

        event.getPlayer().sendMessage(ChatColor.YELLOW + "[FireWorX][BETA] Armor Equipped");

        //Check for number of players

        //String onlineplayercount[];

        //onlineplayercount[] onlineplayercounts = Bukkit.getServer().getOnlinePlayers().toArray(new onlineplayercount[Bukkit.getServer().getOnlinePlayers().size()]);

        //int onlineplayercount =

        onlineplayers++;

        event.getPlayer().sendMessage(ChatColor.YELLOW + Integer.toString(onlineplayers) + ChatColor.AQUA + " Players online");

    }

    // End of Join message

    //Player Leave Event

    @EventHandler
    public void onPlayerLeaveEvent (PlayerQuitEvent event) {

        String playerleft1;

        if (onlineplayers < 2) {

            playerleft1 = " player online";

        }

        else {

            playerleft1 = " players online";

        }

        onlineplayers--;

        event.setQuitMessage(ChatColor.AQUA + event.getPlayer().getDisplayName() + " just left the Server " + Integer.toString(onlineplayers) + playerleft1);


    }

    // End of Player Leave Event

    // Start of Tool Explosions

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
        float power = 1F;

        // Delete item in case player dies from Explosion
        event.getBrokenItem().setAmount(0);

        // Give back broken diamond pickaxe
        event.getPlayer().getEquipment().setItemInMainHand(new ItemStack(Material.DIAMOND_PICKAXE));
        event.getPlayer().getEquipment().getItemInMainHand().setDurability((short) 1611);

        // Create Explosion on players position
        event.getPlayer().getWorld().createExplosion(X, Y, Z, power, false, false );

        // Chat Message
        event.getPlayer().sendMessage(ChatColor.RED + "BOOM! " + ChatColor.YELLOW + "Your tool broke and exploded" );

    }

    // End of Tool Explosions

}
