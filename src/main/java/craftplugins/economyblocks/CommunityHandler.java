package craftplugins.economyblocks;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.io.BukkitObjectInputStream;
import org.bukkit.util.io.BukkitObjectOutputStream;
import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class CommunityHandler implements CommandExecutor, Listener {

    EconomyBlocks plugin;
    BankHandler bankHandler;

    double[] tierGoals = {100000, 250000, 500000, 1000000, 2000000};
    String[] tierZeroPerks = {"Ability to earn random care packages from harvesting crops", "Ability to earn random care packages from killing mobs", "Ability to kill mobs for $1-10", "1 cooked beef every 30 minutes"};
    String[] tierOnePerks = {"Ability to kill mobs for $1-25", "Reduce random care package wait time by 10 seconds", "Reduce buy shop prices by 5%", "Increase sell shop prices by 5%", "Give all online community members 5 tier 1 care packages", "Add 1 heart to player maximum health", "$2500 every 30 minutes", "And all previous perks"};
    String[] tierTwoPerks = {"Ability to kill mobs for $10-50", "Reduce random care package wait time by 10 seconds", "Reduce buy shop prices by 7%", "Increase sell shop prices by 7%", "Gives all online community members 32 iron, gold, lapis, and copper blocks", "Give all online community members 5 tier 2 care packages", "Add 1 heart to player maximum health", "Another $2500 every 30 minutes", "And all previous perks"};
    String[] tierThreePerks = {"Reduce buy shop prices by 12%", "Increase sell shop prices by 12%", "Give all online community members 4 mending books", "Give all online community members 64 diamonds", "Give all online community members a wither skeleton skull","All online community members receive 5 tier 3 care packages", "Add 1 heart to player maximum health", "A diamond block every 30 minutes", "And all previous perks"};
    String[] tierFourPerks = {"Reduce buy shop prices by 15%", "Increase sell shop prices by 15%", "Give all online community members 45 experience levels", "Give all online community members a shulker box", "Spawn a community cow with 10,000 health", "All online community members receive 5 tier 4 care packages", "Add 1 heart to player maximum health", "A netherite scrap every 30 minutes", "And all previous perks"};
    String[] tierFivePerks = {"Reduce buy shop prices by 20%", "Increase sell shop prices by 20%", "Give all online community members 75 experience levels", "Give all online community members 1 netherite block", "Give community owner a dragon egg", "Give all online community members a totem of undying", "Give all online community members 5 tier 5 care packages", "Add 1 heart to player maximum health", "A random enchanted book every 30 minutes", "And all previous perks"};

    String[][] tierPerks = {tierZeroPerks, tierOnePerks, tierTwoPerks, tierThreePerks, tierFourPerks, tierFivePerks};
    List<Community> communities;

    ItemStack tierOne = Utils.createItem(Material.WHITE_WOOL, Utils.chat("&f&lCare Package Tier 1"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1});

    ItemStack tierTwo = Utils.createItem(Material.LIME_WOOL,Utils.chat("&a&lCare Package Tier 2"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1});

    ItemStack tierThree = Utils.createItem(Material.LIGHT_BLUE_WOOL,Utils.chat("&b&lCare Package Tier 3"),1, new Enchantment[]{Enchantment.MENDING}, new int[]{1});

    ItemStack tierFour = Utils.createItem(Material.PURPLE_WOOL,Utils.chat("&5&lCare Package Tier 4"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1});

    ItemStack tierFive = Utils.createItem(Material.ORANGE_WOOL,Utils.chat("&6&lCare Package Tier 5"), 1, new Enchantment[]{Enchantment.MENDING}, new int[]{1});

    Material[] materials = {Material.GRASS_BLOCK, Material.STONE, Material.COBBLESTONE, Material.GRANITE,
            Material.ICE, Material.DIORITE, Material.CALCITE, Material.BASALT, Material. DEEPSLATE,
            Material.BLACKSTONE, Material.GRAVEL, Material.SAND, Material.SNOW_BLOCK, Material.BRICKS,
            Material.NETHER_BRICKS, Material.NETHERRACK, Material.GLOWSTONE, Material.SEA_LANTERN, Material.RED_DYE,
            Material.ORANGE_DYE, Material.YELLOW_DYE, Material.GREEN_DYE, Material.BLUE_DYE, Material.MAGENTA_DYE,
            Material.PURPLE_DYE, Material.WHITE_DYE, Material.BLACK_DYE};

    double[] buildPrices = {5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,10,10,7.5,7.5,7.5,7.5,7.5,7.5,7.5,7.5,7.5};

    Enchantment[] enchants = {
            Enchantment.MENDING, Enchantment.DURABILITY, Enchantment.WATER_WORKER, Enchantment.BINDING_CURSE, Enchantment.DEPTH_STRIDER,
            Enchantment.PROTECTION_FALL, Enchantment.PROTECTION_ENVIRONMENTAL, Enchantment.OXYGEN, Enchantment.THORNS,
            Enchantment.SOUL_SPEED, Enchantment.FIRE_ASPECT, Enchantment.LOOT_BONUS_MOBS, Enchantment.IMPALING, Enchantment.KNOCKBACK,
            Enchantment.DAMAGE_ALL, Enchantment.SWEEPING_EDGE, Enchantment.CHANNELING, Enchantment.RIPTIDE, Enchantment.LOYALTY,
            Enchantment.ARROW_INFINITE, Enchantment.ARROW_FIRE, Enchantment.ARROW_DAMAGE, Enchantment.ARROW_KNOCKBACK, Enchantment.DIG_SPEED,
            Enchantment.DIG_SPEED, Enchantment.SILK_TOUCH, Enchantment.LOOT_BONUS_BLOCKS
    };

    double[] bookPrices = {100000, 25000, 35000, 75000, 50000, 65000, 85000, 50000, 65000, 25000, 50000, 60000, 45000, 40000, 85000, 75000, 60000, 60000, 50000, 75000, 60000, 65000, 30000, 90000, 420000, 85000, 80000};

    String[] colorCodes = {"&0", "&1", "&2", "&3", "&4", "&5", "&6", "&7", "&8", "&9", "&a", "&b", "&c", "&d", "&e", "&f"};

    int[] levels = {
            1, 3, 1, 1, 3,
            4, 4, 3, 3,
            3, 2, 3, 5, 2,
            5, 3, 1, 3, 3,
            1, 1, 5, 2, 5,
            8, 1, 3
    };

    double setHubPrice = 5000;
    double communityCreationPrice = 25000;
    double communityJoinPrice = 2500;

    Inventory buildShop = Bukkit.createInventory(null, 27, "Community Build Shop");
    Inventory bookShop = Bukkit.createInventory(null, 27, "Community Book Shop");

    public CommunityHandler (EconomyBlocks plugin, BankHandler bankHandler) {
        this.plugin = plugin;
        this.bankHandler = bankHandler;

        communities = loadData("communities.data");

        if (communities == null) {
            communities  = new ArrayList<>();
        }

        for (Player player : Bukkit.getOnlinePlayers()) {
            if (playerInCommunity(player.getUniqueId().toString())) {
                automatedDrops(player);
            }
        }

        Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
        Bukkit.getServer().getPluginCommand("community").setExecutor(this);
        Bukkit.getServer().getPluginCommand("hub").setExecutor(this);
    }

    public Community playerIsOwner(String uuid) {
        for (Community com : communities)  {
            if (com.getOwner().equalsIgnoreCase(uuid)) {
                return com;
            }
        }
        return null;
    }

    public boolean playerInCommunity(String uuid) {
        for (Community com : communities) {
            for (String id : com.uuids) {
                if (uuid.equals(id)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean checkCommunityNameExists(String name) {
        for (Community com : communities) {
            if (com.getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public Community getCommunityByName(String name) {
        for (Community com : communities) {
            if (com.getName().equalsIgnoreCase(name)) {
                return com;
            }
        }
        return null;
    }

    public Community getPlayerCommunity(String uuid) {
        for (Community com : communities) {
            for (String id : com.uuids) {
                if (uuid.equals(id)) {
                    return com;
                }
            }
        }
        return null;
    }

    public boolean playerIsInvited(Community community, String uuid) {
        for (String invUUID : community.invited) {
            if (uuid.equals(invUUID)) {
                return true;
            }
        }
        return false;
    }

    public boolean playerIsAdmin(Community community, String uuid) {
        for (String id : community.admins) {
            if (uuid.equals(id)) {
                return true;
            }
        }
        return false;
    }

    public String getCommunityName(Community community) {
        return community.getSecondaryColor() + "<" + community.getPrimaryColor() + community.getName() + community.getSecondaryColor() + ">";
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if (!(sender instanceof Player)) {
            System.out.println("Only players in game can do that");
            return false;
        }

        Player player = (Player) sender;
        String uuid = player.getUniqueId().toString();

        if (label.equalsIgnoreCase("hub")) {
            handleHub(player, uuid, args);
            return true;
        }

        if (args.length < 1) {
            player.sendMessage(Utils.chat("&cDo /info to get more information on communities"));
            return false;
        }

        String cmd = args[0];

        if (cmd.equalsIgnoreCase("create")) {
            handleCreate(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("invite")) {
            handleInvite(player, uuid, args);

        }else if (cmd.equalsIgnoreCase("join")) {
            handleJoin(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("leave")) {
            handleLeave(player, uuid);

        } else if (cmd.equalsIgnoreCase("delete")) {
            handleDelete(player, uuid);

        } else if (cmd.equalsIgnoreCase("sethub")) {
            handleSetHub(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("upgrade")) {
            handleUpgrade(player, uuid);

        } else if (cmd.equalsIgnoreCase("progress")) {
            handleProgress(player, uuid);

        } else if (cmd.equalsIgnoreCase("list")) {
            handleList(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("perks")) {
            handlePerks(player);

        } else if (cmd.equalsIgnoreCase("info")) {
            handleInfo(player, uuid);

        } else if (cmd.equalsIgnoreCase("fund")) {
            handleContribute(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("kick")) {
            handleKick(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("promote")) {
            handlePromote(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("demote")) {
            handleDemote(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("buildshop")) {

            if (!playerInCommunity(uuid)) {
                player.sendMessage(Utils.chat("&cYou are not currently in a community"));
                return false;
            }

            openBuildInventory(player);

        } else if (cmd.equalsIgnoreCase("bookshop")) {

            if (!playerInCommunity(uuid)) {
                player.sendMessage(Utils.chat("&cYou are not currently in a community"));
                return false;
            }

            openBookInventory(player);

        } else if (cmd.equalsIgnoreCase("primary")) {
            handlePrimary(player, uuid, args);

        } else if (cmd.equalsIgnoreCase("secondary")) {
            handleSecondary(player, uuid, args);

        } else {
            player.sendMessage(Utils.chat("&cDo /info to get more information on communities"));
            return false;
        }

        return true;
    }

    private void handlePrimary(Player player, String uuid, String[] args) {
        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not in a community"));
            return;
        }

        if (!playerIsAdmin(community, uuid)) {
            player.sendMessage(Utils.chat("&cYou need to have admin permissions to do this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community primary {color code}"));
            return;
        }

        String colorCode = args[1];

        if (colorCode.equalsIgnoreCase("&k")) {
            player.sendMessage(Utils.chat("&cCannot use ") + "&k" + Utils.chat(" for this action"));
            return;
        }

        if (!Arrays.asList(colorCodes).contains(colorCode)) {
            player.sendMessage(Utils.chat("&cInvalid color code. Try /colors - Cannot be a formatter either"));
            return;
        }

        community.setPrimaryColor(colorCode);
        for (String id : community.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&dYour new community name style is: " + getCommunityName(community)));
            }
        }
    }

    private void handleSecondary(Player player, String uuid, String[] args) {
        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not in a community"));
            return;
        }

        if (!playerIsAdmin(community, uuid)) {
            player.sendMessage(Utils.chat("&cYou need to have admin permissions to do this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community primary {color code}"));
            return;
        }

        String colorCode = args[1];

        if (colorCode.equalsIgnoreCase("&k")) {
            player.sendMessage(Utils.chat("&cCannot use ") + "&k" + Utils.chat(" for this action"));
            return;
        }

        if (!Arrays.asList(colorCodes).contains(colorCode)) {
            player.sendMessage(Utils.chat("&cInvalid color code. Try /colors - Cannot be a formatter either"));
            return;
        }

        community.setSecondaryColor(colorCode);
        for (String id : community.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&dYour new community name style is: " + getCommunityName(community)));
            }
        }
    }

    private void initializeBuildShop(double multiplier) {
        buildShop.clear();

        for (int i = 0; i < materials.length; i++) {
            buildShop.addItem(Utils.createItem(materials[i], "",1,null,null, Utils.chat("&aPrice Per: $" + Utils.format(buildPrices[i] * multiplier)), Utils.chat("&5Left Click: Buy 1"), Utils.chat("&5Right Click: Buy 64 for $" + buildPrices[i] * multiplier * 64)));
        }
    }

    private void initializeBookShop(double multiplier) {
        bookShop.clear();

        for (int i = 0; i < enchants.length; i++) {
            bookShop.addItem(Utils.createItem(Material.ENCHANTED_BOOK, "",1, new Enchantment[] {enchants[i]}, new int[] {levels[i]}, Utils.chat("&aPrice Per: $" + Utils.format(bookPrices[i] * multiplier))));
        }
    }

    public void openBuildInventory(HumanEntity entity) {

        String uuid = entity.getUniqueId().toString();

        Community community = getPlayerCommunity(uuid);
        double discount = 0;
        if (community == null || community.getLevel() == 0) {
            discount = 0;
        } else if (community.getLevel() == 1) {
            discount = .05;
        } else if (community.getLevel() == 2) {
            discount = .07;
        } else if (community.getLevel() == 3) {
            discount = .12;
        } else if (community.getLevel() == 4) {
            discount = .15;

        } else if (community.getLevel() == 5) {
            discount = .20;
        }

        initializeBuildShop(1 - discount);
        entity.openInventory(buildShop);
    }

    public void openBookInventory(HumanEntity entity) {
        String uuid = entity.getUniqueId().toString();

        Community community = getPlayerCommunity(uuid);

        double discount = 0;
        if (community == null || community.getLevel() == 0) {
            discount = 0;
        } else if (community.getLevel() == 1) {
            discount = .05;
        } else if (community.getLevel() == 2) {
            discount = .07;
        } else if (community.getLevel() == 3) {
            discount = .12;
        } else if (community.getLevel() == 4) {
            discount = .15;

        } else if (community.getLevel() == 5) {
            discount = .20;
        }

        initializeBookShop(1 - discount);

        entity.openInventory(bookShop);
    }

    @EventHandler
    public void onInventoryDragBuild(final InventoryDragEvent e) {
        if (e.getInventory() == buildShop) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryDragBook(final InventoryDragEvent e) {
        if (e.getInventory() == bookShop) {
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onInventoryBuildClick(InventoryClickEvent event) {
        if (event.getInventory() != buildShop) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (!event.getInventory().contains(clickedItem)) {
            return;
        }

        if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(player);
        Community community = getPlayerCommunity(player.getUniqueId().toString());
        ClickType clickType = event.getClick();

        double discount = 0;
        if (community == null || community.getLevel() == 0) {
            discount = 0;
        } else if (community.getLevel() == 1) {
            discount = .05;
        } else if (community.getLevel() == 2) {
            discount = .07;
        } else if (community.getLevel() == 3) {
            discount = .12;
        } else if (community.getLevel() == 4) {
            discount = .15;

        } else if (community.getLevel() == 5) {
            discount = .20;
        }

        double multipler = 1 - discount;

        Material m = clickedItem.getType();
        int index = 0;
        for (Material material : materials) {
            if (material == m) {
                break;
            }
            index += 1;
        }

        double price = buildPrices[index];

        if (clickType.isLeftClick()) {

            if (bankAccount.getBalance() >= price * multipler) {
                Utils.addItemToInventory(new ItemStack(clickedItem.getType(), 1), player);
                bankAccount.withdraw(price * multipler);
            } else {
                player.sendMessage(Utils.chat("&cInsufficient Funds"));
            }
        }

        if (clickType.isRightClick()) {
            if (bankAccount.getBalance() >= price * multipler) {
                Utils.addItemToInventory(new ItemStack(clickedItem.getType(), 64), player);
                bankAccount.withdraw(price * 64 * multipler);
            } else {
                player.sendMessage(Utils.chat("&cInsufficient Funds"));
            }
        }

        player.updateInventory();

    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getInventory() != bookShop) {
            return;
        }

        event.setCancelled(true);

        ItemStack clickedItem = event.getCurrentItem();

        if (!event.getInventory().contains(clickedItem)) {
            return;
        }

        if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.GRAY_STAINED_GLASS_PANE) {
            return;
        }

        Player player = (Player) event.getWhoClicked();
        BankAccount bankAccount = bankHandler.getBankAccount(player);
        Community community = getPlayerCommunity(player.getUniqueId().toString());

        double discount = 0;
        if (community == null || community.getLevel() == 0) {
            discount = 0;
        } else if (community.getLevel() == 1) {
            discount = .05;
        } else if (community.getLevel() == 2) {
            discount = .07;
        } else if (community.getLevel() == 3) {
            discount = .12;
        } else if (community.getLevel() == 4) {
            discount = .15;

        } else if (community.getLevel() == 5) {
            discount = .20;
        }

        double multipler = 1 - discount;

        double bookPrice = 0;
        for (int i = 0; i < event.getInventory().getSize(); i++) {
            ItemStack item = event.getInventory().getItem(i);
            if (item.isSimilar(clickedItem)) {
                bookPrice = bookPrices[i];
                break;
            }
        }

        if (bankAccount.getBalance() < bookPrice * multipler) {
            player.sendMessage(Utils.chat("&cInsufficient Funds"));
            return;
        }
        System.out.println(bookPrice);
        Utils.addItemToInventory(clickedItem, player);
        bankAccount.withdraw(bookPrice * multipler);

        player.updateInventory();

    }

    private void handleDemote(Player player, String uuid, String[] args) {
        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        if (playerIsOwner(uuid) == null) {
            player.sendMessage(Utils.chat("&cYou must own a community to use this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community demote {username}"));
            return;
        }

        Player newAdmin = Bukkit.getPlayer(args[1]);

        if (newAdmin == null) {
            player.sendMessage(Utils.chat("&cPlayer '" + args[1] + "' is currently offline"));
            return;
        }

        if (!playerIsAdmin(community, newAdmin.getUniqueId().toString())) {
            player.sendMessage(Utils.chat("&cThis player is not currently an admin"));
            return;
        }

        if (community != getPlayerCommunity(newAdmin.getUniqueId().toString())) {
            player.sendMessage(Utils.chat("&cThat player is not in your community"));
            return;
        }

        community.removeAdmin(newAdmin.getUniqueId().toString());
        for (String id : community.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&d" + args[1] + " has been demoted from admin in your community"));
            }
        }

    }


    private void handlePromote(Player player, String uuid, String[] args) {
        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        if (playerIsOwner(uuid) == null) {
            player.sendMessage(Utils.chat("&cYou must own a community to use this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community promote {username}"));
            return;
        }

        Player newAdmin = Bukkit.getPlayer(args[1]);

        if (newAdmin == null) {
            player.sendMessage(Utils.chat("&cPlayer '" + args[1] + "' is currently offline"));
            return;
        }

        if (playerIsAdmin(community, newAdmin.getUniqueId().toString())) {
            player.sendMessage(Utils.chat("&cThe player is already an admin"));
            return;
        }

        if (community != getPlayerCommunity(newAdmin.getUniqueId().toString())) {
            player.sendMessage(Utils.chat("&cThat player is not in your community"));
            return;
        }

        community.addAdmin(newAdmin.getUniqueId().toString());
        for (String id : community.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&d" + args[1] + " has been promoted to admin in your community"));
            }
        }
    }

    private void handleKick(Player player, String uuid, String[] args) {

        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not in a community"));
            return;
        }

        if (!playerIsAdmin(community, uuid)) {
            player.sendMessage(Utils.chat("&cYou need to have admin permissions to do this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community kick {username}"));
            return;
        }

        String username = args[1];

        if (args[1].equalsIgnoreCase(community.ownerName)) {
            player.sendMessage(Utils.chat("&cYou cannot kick the owner"));
            return;
        }

        if (!community.playerNames.contains(username)) {
            player.sendMessage(Utils.chat("&cPlayer &4'" + username + "'&c not found in the community"));
            return;
        }

        if (username.equalsIgnoreCase(community.ownerName)) {
            player.sendMessage(Utils.chat("&cYou cannot kick yourself from the community. Try /community delete"));
            return;
        }

        Player p = Bukkit.getPlayer(username);

        if (p == null) {
            player.sendMessage(Utils.chat("&cPlayer &4'" + username + "'&c is currently not online"));
            return;
        }
        p.sendMessage(Utils.chat("&dYou have been kicked from the community"));
        community.removePlayer(p.getUniqueId().toString());
        p.setMaxHealth(20.0);

        for (String id : community.uuids) {
            Player play = Bukkit.getPlayer(UUID.fromString(id));
            if (play != null) {
                play.sendMessage(Utils.chat("&d" + username + " has been removed from the community"));
            }
        }
    }

    private void handleInfo(Player player, String uuid) {
        Community playerCommunity = getPlayerCommunity(uuid);

        if (playerCommunity == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        player.sendMessage(Utils.chat("&d===================================="));
        player.sendMessage(Utils.chat("&5Community Name: &d" + playerCommunity.getName()));
        player.sendMessage(Utils.chat("&5Owner: &d" + playerCommunity.getOwnerName()));
        player.sendMessage(Utils.chat("&dAdmins: "));
        player.sendMessage(Utils.chat("&d" + String.join(", ", playerCommunity.adminNames)));
        player.sendMessage(Utils.chat("&dPlayers: "));
        player.sendMessage(Utils.chat("&d" + String.join(", ", playerCommunity.playerNames)));
        player.sendMessage(Utils.chat("&5Community Size: &d" + playerCommunity.getSize()));
        player.sendMessage(Utils.chat("&5Current Tier: &d" + playerCommunity.getLevel()));
        player.sendMessage(Utils.chat("&dPerks: "));
        for (String perk : tierPerks[playerCommunity.getLevel()]) {
            player.sendMessage(Utils.chat("&d - " + perk));
        }

        int goalLevel = playerCommunity.getLevel() + 1;
        if (goalLevel == tierGoals.length + 1) {
            goalLevel -= 1;
        }
        player.sendMessage(Utils.chat("&5Current progress: &a$" + Utils.format(playerCommunity.getProgress()) + " &d/&a $" + Utils.format(playerCommunity.getGoal()) + "&d for tier " + goalLevel));
        player.sendMessage(Utils.chat("&d===================================="));
    }

    private void handleContribute(Player player, String uuid, String[] args) {
        Community playerCommunity = getPlayerCommunity(uuid);

        if (playerCommunity == null) {
            player.sendMessage(Utils.chat("&cYou are currently not in a community"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community fund {amount}"));
            return;
        }

        if (playerCommunity.getLevel() == 5) {
            player.sendMessage(Utils.chat("&dYou have reached the max tier number!"));
            return;
        }

        try {
            BankAccount ba = bankHandler.getBankAccount(player);
            double amount = Double.parseDouble(args[1]);

            if (amount <= 0) {
                player.sendMessage(Utils.chat("&cYou cannot contribute that amount of money"));
                return;
            }

            if (ba.getBalance() < amount) {
                player.sendMessage(Utils.chat("&cYou don't have enough money for that contribution"));
                player.sendMessage(Utils.chat("&aBalance: $" + Utils.format(ba.getBalance())));
                return;
            }

            if (playerCommunity.getLevel() == 4 && amount + playerCommunity.getProgress() > playerCommunity.getGoal()) {
                amount = playerCommunity.getGoal() - playerCommunity.getProgress();
            }

            if (amount == 0) {
                player.sendMessage(Utils.chat("&cYour community has contributed the max amount"));
                return;
            }

            ba.withdraw(amount);
            playerCommunity.setProgress(playerCommunity.getProgress() + amount);
            player.sendMessage(Utils.chat("&dYou have contributed &a$" + Utils.format(amount) + "&d to the community"));

        } catch (NumberFormatException e) {
            player.sendMessage(Utils.chat("&cInvalid contribution amount, must be a number"));
        }
    }

    private void handlePerks(Player player) {
        int tierNumber = 0;
        player.sendMessage(Utils.chat("&d===================================="));
        for (String[] tier : tierPerks) {
            player.sendMessage(Utils.chat("&d&lTier " + tierNumber));
            for (String perk : tier) {
                player.sendMessage(Utils.chat("&d - " + perk));
            }
            tierNumber += 1;
        }
        player.sendMessage(Utils.chat("&d===================================="));
    }

    private void handleUpgrade(Player player, String uuid) {
        Community ownedCommunity = playerIsOwner(uuid);

        if (ownedCommunity == null) {
            player.sendMessage(Utils.chat("&cYou must own a community to do this command"));
            return;
        }

        if (ownedCommunity.getLevel() == tierGoals.length) {
            player.sendMessage(Utils.chat("&dYou have reached the max tier number!"));
            return;
        }

        if (ownedCommunity.worldUUID == null || ownedCommunity.worldUUID.equals("")) {
            player.sendMessage(Utils.chat("&cYou must set your community hub before upgrading"));
            return;
        }

        if (ownedCommunity.getProgress() >= ownedCommunity.getGoal()) {
            ownedCommunity.setProgress(ownedCommunity.getProgress() - ownedCommunity.getGoal());

        } else {
            player.sendMessage(Utils.chat("&cYour community is not ready to be upgraded. Try /community progress"));
            return;
        }

        ownedCommunity.setLevel(ownedCommunity.getLevel() + 1);
        if (ownedCommunity.getLevel() != tierGoals.length) {
            ownedCommunity.setGoal(tierGoals[ownedCommunity.getLevel()]);
        }

        oneTimeStuff(ownedCommunity);
        hoard(player);
        Bukkit.broadcastMessage(Utils.chat("&d" + ownedCommunity.getName() + " just upgraded to tier " + ownedCommunity.getLevel()));

    }

    private void hoard(Player player) {
        new BukkitRunnable() {
            int timer = 300 + 1;
            @Override
            public void run() {

                Community community = getPlayerCommunity(player.getUniqueId().toString());

                if (timer-- == 0) {
                    spawnHoard(community);
                    this.cancel();
                }

                if (community == null) {
                    this.cancel();
                    return;
                }

                if (300 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 5 minutes at your hub location!"));

                } else if (240 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 4 minutes!"));

                } else if (180 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 3 minutes!"));

                } else if (120 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 2 minutes!"));

                } else if (60 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 1 minutes!"));

                } else if (30 == timer) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in 30 seconds!"));

                } else if (timer <= 10 && timer > 0) {
                    Bukkit.broadcastMessage(Utils.chat("&d" + community.getName() + " Community's raid starts in " + timer + " seconds!"));

                } else if (timer == 0) {
                    Bukkit.broadcastMessage(Utils.chat("&d&l" + community.getName() + " Community's raid starts now!"));
                }
            }
        }.runTaskTimer(plugin, 0,20 * 1);
    }

    private void spawnHoard(Community community) {
        List<Location> locations = new ArrayList<>();

        for (int x = -5; x < 5; x++) {
            for (int z = -5; z < 5; z++) {
                locations.add(new Location(Bukkit.getWorld(UUID.fromString(community.worldUUID)), community.x + x, community.y, community.z + z));
            }
        }

        EntityType[] mobs = {EntityType.RAVAGER, EntityType.WITHER_SKELETON, EntityType.VEX, EntityType.VINDICATOR, EntityType.ILLUSIONER, EntityType.ENDERMAN, EntityType.WITCH};

        boolean ravagerSpawned = false;

        for (Location location : locations) {
            int randomNum = Utils.getRandomNumber(0, 100);
            if (randomNum > 50) {
                continue;
            }


            randomNum = Utils.getRandomNumber(0, mobs.length);
            if (ravagerSpawned) {
                randomNum = Utils.getRandomNumber(1, mobs.length);
            }

            EntityType randomEntity = mobs[randomNum];

            if (randomEntity == EntityType.RAVAGER) {
                ravagerSpawned = true;
            }

            locations.get(0).getWorld().spawnEntity(location, randomEntity);

        }
    }

    private void oneTimeStuff(Community community) {

        for (String id : community.uuids) {
            Player player = Bukkit.getPlayer(UUID.fromString(id));
            if (player != null) {
                if (community.getLevel() == 1) {
                    for (int i = 0; i < 5; i++) {
                        Utils.addItemToInventory(tierOne, player);

                    }

                } else if (community.getLevel() == 2) {
                    for (int i = 0; i < 5; i++) {
                        Utils.addItemToInventory(tierTwo, player);

                    }

                    ItemStack ironBlock = Utils.createItem(Material.IRON_BLOCK, "", 32, null, null);
                    ItemStack goldBlock = Utils.createItem(Material.GOLD_BLOCK, "", 32, null, null);
                    ItemStack lapisBlock = Utils.createItem(Material.LAPIS_BLOCK, "", 32, null, null);
                    ItemStack copperBlock = Utils.createItem(Material.COPPER_BLOCK, "", 32, null, null);

                    Utils.addItemToInventory(ironBlock, player);
                    Utils.addItemToInventory(goldBlock, player);
                    Utils.addItemToInventory(lapisBlock, player);
                    Utils.addItemToInventory(copperBlock, player);

                } else if (community.getLevel() == 3) {
                    for (int i = 0; i < 5; i++) {
                        Utils.addItemToInventory(tierThree, player);

                    }

                    ItemStack witherSkull = Utils.createItem(Material.WITHER_SKELETON_SKULL, "", 1, null, null);
                    ItemStack mending = Utils.createItem(Material.ENCHANTED_BOOK, "", 1, new Enchantment[] {Enchantment.MENDING}, new int[] {1});
                    ItemStack diamonds = Utils.createItem(Material.DIAMOND, "", 64, null, null);

                    for (int i = 0; i < 4; i++) {
                        Utils.addItemToInventory(mending, player);
                    }
                    Utils.addItemToInventory(witherSkull, player);
                    Utils.addItemToInventory(diamonds, player);

                } else if (community.getLevel() == 4) {
                    for (int i = 0; i < 5; i++) {
                        Utils.addItemToInventory(tierFour, player);

                    }

                    player.setLevel(player.getLevel() + 45);
                    ItemStack shulker = Utils.createItem(Material.SHULKER_BOX, "", 1, null, null);

                    Utils.addItemToInventory(shulker, player);

                } else if (community.getLevel() == 5) {
                    for (int i = 0; i < 5; i++) {
                        Utils.addItemToInventory(tierFive, player);

                    }

                    player.setLevel(player.getLevel() + 75);
                    ItemStack dragonEgg = Utils.createItem(Material.DRAGON_EGG, "", 1, null, null);
                    ItemStack netheriteBlock = Utils.createItem(Material.NETHERITE_BLOCK, "", 1, null, null);
                    ItemStack totem = Utils.createItem(Material.TOTEM_OF_UNDYING, "", 1, null, null);

                    if (id.equalsIgnoreCase(community.ownerUUID)) {
                        Utils.addItemToInventory(dragonEgg, player);
                    }
                    Utils.addItemToInventory(netheriteBlock, player);
                    Utils.addItemToInventory(totem, player);
                    player.sendMessage(Utils.chat("&dYou really just spent all that time doing this huh?"));
                }

                player.setMaxHealth(player.getMaxHealth() + 2.0);

            }
        }

        if (community.getLevel() == 4) {
            Location location = new Location(Bukkit.getWorld(UUID.fromString(community.worldUUID)), community.x, community.y, community.z);

            Entity cow = Bukkit.getWorld(community.worldUUID).spawnEntity(location, EntityType.COW);
            ((LivingEntity) cow).setMaxHealth(2047.9);
            ((LivingEntity) cow).setHealth(2047.9);
            cow.setCustomNameVisible(true);
            cow.setCustomName(Utils.chat("&d&l" + community.getName() + " Community Cow"));

        }

    }

    private void handleProgress(Player player, String uuid) {
        Community playerCommunity = getPlayerCommunity(uuid);


        if (playerCommunity == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        if (playerCommunity.getLevel() == tierGoals.length) {
            player.sendMessage(Utils.chat("&dYou have reached the max tier number!"));
            return;
        }
        int goalLevel = playerCommunity.getLevel() + 1;
        if (goalLevel == tierGoals.length + 1) {
            goalLevel -= 1;
        }
        player.sendMessage(Utils.chat("&dCurrent progress: &a$" + Utils.format(playerCommunity.getProgress()) + " &d/&a $" + Utils.format(playerCommunity.getGoal()) + "&d for tier " + goalLevel));

    }

    private void handleDelete(Player player, String uuid) {
        Community ownedCommunity = playerIsOwner(uuid);
        if (ownedCommunity == null) {
            player.sendMessage(Utils.chat("&cYou do not own a community to delete"));
            return;
        }

        for (String id : ownedCommunity.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.setMaxHealth(20.0);
            }
        }

        communities.remove(ownedCommunity);
        Bukkit.broadcastMessage(Utils.chat("&dThe community &5'" + ownedCommunity.getName() + "'&d has been disband"));
    }

    private void handleSetHub(Player player, String uuid, String[] args) {
        Community community = getPlayerCommunity(uuid);

        if (community == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        if (!playerIsAdmin(community, uuid)) {
            player.sendMessage(Utils.chat("&cYou need to have admin permissions to do this command"));
            return;
        }

        BankAccount bankAccount = bankHandler.getBankAccount(player);

        if (bankAccount.getBalance() < setHubPrice) {
            player.sendMessage(Utils.chat("&cInsufficient Funds. /community sethub price is  $" + Utils.format(setHubPrice)));
            return;
        }

        String worldID = player.getWorld().getUID().toString();
        double x = player.getLocation().getX();
        double y = player.getLocation().getY();
        double z = player.getLocation().getZ();

        community.setX(x);
        community.setY(y);
        community.setZ(z);
        community.setWorld(worldID);
        bankAccount.withdraw(setHubPrice);

        for (String id : community.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&dYour community hub has been set to x: " + Utils.format(x) + ", y: " + Utils.format(y) + ", z: " + Utils.format(z) + " in the " + Utils.getWorldName(UUID.fromString(worldID)) + " dimension"));
            }
        }
    }

    private void handleLeave(Player player, String uuid) {
        Community playerCommunity = getPlayerCommunity(uuid);

        if (playerCommunity == null) {
            player.sendMessage(Utils.chat("&cYou are currently not in a community"));
            return;
        }

        if (playerIsOwner(uuid) != null) {
            player.sendMessage(Utils.chat("&cYou are the owner of your community, use /community delete instead"));
            return;
        }

        if (playerIsAdmin(playerCommunity, uuid)) {
            playerCommunity.removeAdmin(uuid);
        }

        playerCommunity.removePlayer(uuid);
        player.setMaxHealth(20);
        player.sendMessage(Utils.chat("&dYou have been removed from the &5'" + playerCommunity.getName() + "'&d community"));

        for (String id : playerCommunity.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&d" + player.getDisplayName() + " has left your community"));
            }
        }
    }

    private void handleList(Player player, String uuid, String[] args) {
        if (args.length >= 1) {


            String cmdParameter = "";

            if (args.length >= 2) {
                cmdParameter = args[1];

            }

            int count = 0;

            if (cmdParameter.equalsIgnoreCase("invites")) {
                for (Community com : communities) {
                    for (String id : com.invited) {
                        if (uuid.equals(id)) {
                            player.sendMessage(Utils.chat("&d - " + com));
                            count += 1;
                        }
                    }
                }
                if (count == 0) {
                    player.sendMessage(Utils.chat("&dYou have no pending invites"));
                }

            } else {
                for (Community com : communities) {
                    player.sendMessage(Utils.chat("&d - " + com));
                    count += 1;
                }
                if (count == 0) {
                    player.sendMessage(Utils.chat("&dCurrently there are no communities on this server"));
                }
            }
        }
    }

    private void handleHub(Player player, String uuid, String[] args) {
        Community playerCommunity = getPlayerCommunity(uuid);
        if (playerCommunity == null) {
            player.sendMessage(Utils.chat("&dYou are currently not in a community. Message someone to try to get in. Try /community list"));
            return;
        }

        if (playerCommunity.worldUUID == null || playerCommunity.worldUUID.equals("")) {
            player.sendMessage(Utils.chat("&cNo community hub set, ask an admin to /sethub somewhere"));
            return;
        }

        if (args.length > 0) {
            String cmdParameter = args[0];

            if (cmdParameter.equalsIgnoreCase("display")) {
                player.sendMessage(Utils.chat("&dYour community hub is set to x: " + Utils.format(playerCommunity.x) + ", y: " + Utils.format(playerCommunity.y) + ", z: " + Utils.format(playerCommunity.z) + " in the " + Utils.getWorldName(UUID.fromString(playerCommunity.worldUUID)) + " dimension"));
            } else {
                player.sendMessage(Utils.chat("&cInvalid command '/hub " + args[0] + "' try /hub display"));
            }
            return;
        }

        Location location = new Location(Bukkit.getWorld(UUID.fromString(playerCommunity.worldUUID)), playerCommunity.x, playerCommunity.y, playerCommunity.z);
        player.teleport(location);
        player.sendMessage(Utils.chat("&dYou have been teleported to your community hub"));
    }

    private void handleJoin(Player player, String uuid, String[] args) {
        if (playerInCommunity(uuid)) {
            player.sendMessage(Utils.chat("&cYou are already in a community you must leave or delete your current community"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community join {community name}"));
            return;
        }

        Community joinCommunity = getCommunityByName(args[1]);

        if (joinCommunity == null) {
            player.sendMessage(Utils.chat("&cNo community by the name '" + args[1] + "'found. Try /community list invites"));
            return;
        }

        if (!playerIsInvited(joinCommunity, uuid)) {
            player.sendMessage(Utils.chat("&cYou are not invited to '" + args[1] + "'"));
            return;
        }

        BankAccount ba = bankHandler.getBankAccount(player);

        if (ba.getBalance() < communityJoinPrice) {
            player.sendMessage(Utils.chat("&cYou need at least $" + Utils.format(communityJoinPrice) + "to join a community"));
            return;
        }

        ba.withdraw(communityJoinPrice);
        player.sendMessage(Utils.chat("&dThe joining fee of $" + communityJoinPrice + " goes into the contribution pool"));
        joinCommunity.setProgress(joinCommunity.getProgress() + communityJoinPrice);
        joinCommunity.addPlayer(uuid);
        joinCommunity.invited.remove(uuid);
        automatedDrops(player);

        for (String id : joinCommunity.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&d" + player.getDisplayName() + " has joined your community"));
            }
        }

        player.sendMessage(Utils.chat("&dYou have joined the community: &5'" + args[1] + "'"));
    }


    private void handleInvite(Player player, String uuid, String[] args) {
        Community invCommunity = getPlayerCommunity(uuid);

        if (invCommunity == null) {
            player.sendMessage(Utils.chat("&cYou are not currently in a community"));
            return;
        }

        if (!playerIsAdmin(invCommunity, uuid)) {
            player.sendMessage(Utils.chat("&cYou need to have admin permissions to do this command"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community invite {username}"));
            return;
        }

        Player invite = Bukkit.getPlayer(args[1]);

        if (invite == null) {
            player.sendMessage(Utils.chat("&cPlayer &5'" + args[1] + "'&d does not exist or is currently offline"));
            return;
        }

        String invitedUUID = invite.getUniqueId().toString();

        if (invitedUUID.equalsIgnoreCase(uuid)) {
            player.sendMessage(Utils.chat("&cYou cannot invite yourself to a community"));
            return;
        }

        if (getPlayerCommunity(invitedUUID) == invCommunity) {
            player.sendMessage(Utils.chat("&c'" + args[1] + "' is already in your community"));
            return;
        }

        invCommunity.invitePlayer(invitedUUID);
        invite.sendMessage(Utils.chat("&dYou have been invited to join the community: " + invCommunity.getName() + ". Try /community join " + invCommunity.getName()));
        for (String id : invCommunity.uuids) {
            Player p = Bukkit.getPlayer(UUID.fromString(id));
            if (p != null) {
                p.sendMessage(Utils.chat("&d" + args[1] + "&d has been invited to your community"));
            }
        }
    }

    private void handleCreate(Player player, String uuid, String[] args) {
        if (playerInCommunity(uuid)) {
            player.sendMessage(Utils.chat("&cYou are already in a community you must leave or delete your current community"));
            return;
        }

        if (args.length < 2) {
            player.sendMessage(Utils.chat("&cUsage: /community create {name}"));
            return;
        }

        BankAccount ba = bankHandler.getBankAccount(player);
        if (ba.getBalance() < communityCreationPrice) {
            player.sendMessage(Utils.chat("&cYou need at least $" + Utils.format(communityCreationPrice) + " to create a community"));
            return;
        }

        String communityName = args[1];

        if (communityName.length() > 32) {
            player.sendMessage(Utils.chat("&cCommunity name cannot be longer than 32 characters"));
            return;
        }

        if (checkCommunityNameExists(communityName)) {
            player.sendMessage(Utils.chat("&c'" + communityName + "' already exists"));
            return;
        }

        Community com = new Community(uuid,communityName,0, tierGoals[0]);

        communities.add(com);
        ba.withdraw(communityCreationPrice);
        automatedDrops(player);
        Bukkit.broadcastMessage(Utils.chat("&dCommunity named &5'" + com.getName() + "'&d has been created"));
    }

    public boolean saveData(String filePath) {
        try {
            BukkitObjectOutputStream out = new BukkitObjectOutputStream(new GZIPOutputStream(new FileOutputStream(filePath)));
            out.writeObject(communities);
            out.close();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Community> loadData(String filePath) {
        try {
            BukkitObjectInputStream in = new BukkitObjectInputStream(new GZIPInputStream(new FileInputStream(filePath)));
            communities = (List<Community>) in.readObject();
            in.close();
            return communities;
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();

        Community community = getPlayerCommunity(player.getUniqueId().toString());

        if (community == null) {
            player.setMaxHealth(20.0);
            return;
        }

        player.setMaxHealth(20 + (community.getLevel() * 2));

        automatedDrops(player);

    }


    public void automatedDrops(Player player) {

        new BukkitRunnable() {
            int count = 0;
            @Override
            public void run() {
                count++;
                Community community = getPlayerCommunity(player.getUniqueId().toString());
                if (community == null) {
                    this.cancel();
                    return;
                }

                if (!player.isOnline()) {
                    this.cancel();
                    return;
                }

                if (count % (60 * 30) == 0) {

                    if (community.getLevel() >= 0) {
                        Utils.addItemToInventory(new ItemStack(Material.COOKED_BEEF, 1), player);
                    }

                    if (community.getLevel() >= 1) {
                        BankAccount ba = bankHandler.getBankAccount(player);
                        if (community.getLevel() == 1) {
                            ba.deposit(2500);
                        } else if (community.getLevel() >= 2) {
                            ba.deposit(5000);
                        }

                    }

                    if (community.getLevel() >= 3) {
                        Utils.addItemToInventory(new ItemStack(Material.DIAMOND_BLOCK, 1), player);

                    }

                    if (community.getLevel() >= 4) {
                        Utils.addItemToInventory(new ItemStack(Material.NETHERITE_SCRAP, 1), player);

                    }

                    if (community.getLevel() >= 5) {
                        int randomIndex = Utils.getRandomNumber(0, enchants.length);

                        ItemStack book = Utils.createItem(Material.ENCHANTED_BOOK, "", 1, new Enchantment[]{enchants[randomIndex]}, new int[]{levels[randomIndex]});
                        Utils.addItemToInventory(book, player);

                    }

                    player.updateInventory();
                    player.sendMessage(Utils.chat("&dYou have been given your reoccurring community rewards"));
                }
            }
        }.runTaskTimer(plugin, 20, 20);

    }
}
