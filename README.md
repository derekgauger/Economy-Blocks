**Plugin Download:** https://www.spigotmc.org/resources/economyblocks.103809/

EconomyBlocks is a 1.19.1+ Vanilla Minecraft plugin for adding a little bit more spice. This plugin is designed to increase
the lifespan of a Minecraft server by giving people things to work toward. It is also there to encourage people to
play together, build communities, and communicate.

**Info Command:**

The info command is a shortened version of this description.

- /info : Displays the information about the plugin (unfinished)

**Communities**

Communities are included in this plugin to encourage people to work together, live together, and play together. Communities 
also increase the longevity of a server. By making people want to work toward upgrading their community. As you upgrade 
your community, you will start earning better benefits, one-time rewards, and recurring rewards. 

Community Maintenance Commands:
- /c create {name} : Creates a community
- /c delete : Deletes your community
- /c invite {username} : Invites a user to your community
- /c kick {username} : Removes a user from the community
- /c upgrade : Upgrades your community to the next tier
- /c sethub : Sets the hub location
- /c promote {username} : Gives user admin permissions
- /c demote {username} : Removes user's admin permissions

Community Member Commands: 
- /hub : Teleports to community hub
- /hub display : Displays info about community hub location
- /c leave : Leaves the community
- /c info : Displays info about the community
- /c fund {amount} : Adds money to the upgrade pool
- /c progress : Displays the current community progress

Community All Users Commands:
- /c join {community name} : Joins a community
- /c list : lists all communities on the server
- /c list invites : lists all the communities that invited you
- /c perks : lists all perks for all tiers

**Shops:**

- /shop : Opens a menu for opening the following menus
- /buy : Opens a shop for buying items
- /sell : Opens a shop for selling items
- /c buildshop : Opens a shop for build materials
- /c bookshop : Opens a shop for enchanted books
- /fbs : Opens a "Fill Block Shop" blocks that create a cluster of blocks for filling in holes

Only a few items will be available for selling. Only blocks that frequently fill inventories, minerals, mob drops, and crops.
The buy shop features difficult items to obtain, care packages, a few spawn eggs, and redstone.

**Care Packages:**

Care packages are custom wool blocks that, when placed, trigger a random event to happen. The event can be good or bad for the player.
Disclaimer: Try not to open near important areas

**Care Package Tiers:**

- Tier 1 : 50% chance of a good event - Executes 1 good event
- Tier 2 : 67% chance of a good event - Executes 1 good event
- Tier 3 : 80% chance of a good event - Executes 2 good events
- Tier 4 : 95% chance of a good event - Executes 2 good events
- Tier 5 : 100% chance of a good event - Executes 3 good events

**Economy:**

This plugin has a very basic implementation of an economy.

- /send {username} {amount} : Transfers money from your bank account to another player's bank account
- /bal : Checks your bank account balance
- /bal {username} : Checks another player's bank account balance
- /setbal {username} {amount} : Allows console to set a player's bank account balance

**Teleport Menu:**

The teleport menu is a GUI that allows the user to teleport to other people for a price. If you have enough money to teleport, you can click on that person's head in the menu to go to them.

- /go : Opens the teleporting menu
- /go {username} : Teleports to a player
- /godisable : Disable teleporting to you
- /goenable : Enable teleporting to you

**Homes:**

The homes menu is a GUI that allows the user to display coordinates and teleport to the homes for a price. Click the block associated with the home you want to go to, if you have enough money. You can go there.

- /sethome : Adds a new home location
- /sethome {home number}: Replaces an existing home location
- /homes : Open the menu for displaying currently set homes
