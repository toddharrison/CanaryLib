package net.canarymod.commandsys;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.commands.*;
import net.canarymod.commandsys.commands.groupmod.*;
import net.canarymod.commandsys.commands.playermod.*;
import net.canarymod.commandsys.commands.vanilla.*;
import net.canarymod.commandsys.commands.warp.WarpList;
import net.canarymod.commandsys.commands.warp.WarpRemove;
import net.canarymod.commandsys.commands.warp.WarpSet;
import net.canarymod.commandsys.commands.warp.WarpUse;
import net.canarymod.commandsys.commands.world.CreateWorldCommand;
import net.canarymod.commandsys.commands.world.LoadWorldCommand;

import java.util.*;

import static net.canarymod.commandsys.TabCompleteHelper.*;

/**
 * Canary "native" commands
 *
 * @author Chris (damagefilter)
 * @author Jason (darkdiplomat)
 * @author Aaron (somners)
 */
public class CommandList implements CommandListener {
    private final static Map<String, NativeCommand> natives;

    static {
        HashMap<String, NativeCommand> temp = new HashMap<String, NativeCommand>();
        temp.put("ban", new BanCommand());
        temp.put("unban", new UnbanCommand());
        temp.put("compass", new Compass());
        temp.put("createvanilla", new CreateVanilla());
        temp.put("pos", new GetPosition());
        temp.put("groupmod_base", new GroupBase());
        temp.put("groupmod_add", new GroupCreate());
        temp.put("groupmod_perms_add", new GroupPermissionAdd());
        temp.put("groupmod_perms_remove", new GroupPermissionRemove());
        temp.put("groupmod_perms_check", new GroupPermissionCheck());
        temp.put("groupmod_perms_list", new GroupPermissionList());
        temp.put("groupmod_perms_flush", new GroupPermissionFlush());
        temp.put("groupmod_list", new GroupList());
        temp.put("groupmod_remove", new GroupRemove());
        temp.put("groupmod_check", new GroupCheck());
        temp.put("groupmod_rename", new GroupRename());
        temp.put("groupmod_prefix", new GroupPrefix());
        temp.put("groupmod_parent", new GroupParent());
        temp.put("playermod", new PlayermodBase());
        temp.put("playermod_add", new PlayerCreate());
        temp.put("playermod_perms_add", new PlayerPermissionAdd());
        temp.put("playermod_perms_remove", new PlayerPermissionRemove());
        temp.put("playermod_perms_check", new PlayerPermissionCheck());
        temp.put("playermod_perms_list", new PlayerPermissionList());
        temp.put("playermod_prefix", new PlayerPrefix());
        temp.put("playermod_remove", new PlayerRemove());
        temp.put("playermod_group_set", new PlayerGroupSet());
        temp.put("playermod_group_add", new PlayerGroupAdd());
        temp.put("playermod_group_list", new PlayerGroupList());
        temp.put("playermod_group_check", new PlayerGroupCheck());
        temp.put("playermod_group_remove", new PlayerGroupRemove());
        temp.put("help", new HelpCommand());
        temp.put("home", new Home());
        temp.put("ipban", new IpBanCommand());
        temp.put("kick", new Kick());
        temp.put("kill", new Kill());
        temp.put("kit", new KitCommand());
        temp.put("listplugins", new ListPlugins());
        temp.put("motd", new Motd());
        temp.put("mute", new Mute());
        temp.put("playerlist", new PlayerList());
        temp.put("enableplugin", new PluginCommand(false, false));
        temp.put("disableplugin", new PluginCommand(true, false));
        temp.put("reloadplugin", new PluginCommand(false, true));
        temp.put("reload", new ReloadCommand());
        temp.put("sethome", new SetHome());
        temp.put("warp", new WarpUse());
        temp.put("setwarp", new WarpSet());
        temp.put("listwarps", new WarpList());
        temp.put("delwarp", new WarpRemove());
        temp.put("spawn", new SpawnCommand());
        temp.put("stop", new StopServer());
        temp.put("whitelist", new WhitelistCommand());
        temp.put("god", new GodCommand());
        temp.put("reservelist", new ReservelistCommand());
        temp.put("canarymod", new CanaryModCommand());
        temp.put("playerinfo", new PlayerInformation());
        temp.put("sysinfo", new SystemInformation());
        temp.put("uptime", new Uptime());
        temp.put("loadworld", new LoadWorldCommand());
        temp.put("createworld", new CreateWorldCommand());

        /* The Vanilla Wrappers */
        temp.put("achievement", new Achievement());
        temp.put("broadcast", new Broadcast());
        temp.put("clear", new Clear());
        temp.put("debug", new Debug());
        temp.put("defaultgamemode", new DefaultGameMode());
        temp.put("defaultspawnpoint", new DefaultSpawnpoint());
        temp.put("difficulty", new Difficulty());
        temp.put("effect", new Effect());
        temp.put("emote", new Emote());
        temp.put("enchant", new Enchant());
        temp.put("gamemode", new GameMode());
        temp.put("gamerule", new GameRule());
        temp.put("give", new Give());
        temp.put("tell", new Message());
        temp.put("tellraw", new MessageRaw());
        temp.put("playsound", new PlaySound());
        temp.put("save-all", new SaveAll());
        temp.put("save-off", new SaveOff());
        temp.put("save-on", new SaveOn());
        temp.put("scoreboard", new Scoreboard());
        temp.put("setblock", new SetBlock());
        temp.put("spawnpoint", new SpawnPoint());
        temp.put("spreadplayers", new SpreadPlayers());
        temp.put("summon", new Summon());
        temp.put("teleport", new Teleport());
        temp.put("testfor", new TestFor());
        temp.put("testforblock", new TestForBlock());
        temp.put("time", new Time());
        temp.put("toggledownfall", new ToggleDownfall());
        temp.put("weather", new Weather());
        temp.put("xp", new XP());

        natives = Collections.unmodifiableMap(temp);
    }

    @Command(aliases = {"ban"},
            description = "ban info",
            permissions = {"canary.super.ban", "canary.command.super.ban"},
            toolTip = "/ban <player> [reason] [#number hour|day|week|month]",
            min = 2,
            tabCompleteMethod = "banTabComplete"
    )
    public void banCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ban").execute(caller, parameters);
    }

    @TabComplete
    public List<String> banTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToKnownPlayer(parameters)
                : parameters.length >= 2 && parameters[parameters.length - 2].matches("\\d+") ? matchTo(parameters, new String[]{"hour", "day", "week", "month"})
                : null;
    }

    @Command(aliases = {"unban"},
            description = "unban info",
            permissions = {"canary.super.unban", "canary.command.super.unban"},
            toolTip = "/unban <player>",
            min = 2,
            tabCompleteMethod = "unbanTabComplete"
    )
    public void unbanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("unban").execute(caller, parameters);
    }

    @TabComplete
    public List<String> unbanTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToBannedSubject(parameters) : null;
    }

    @Command(aliases = {"compass"},
            description = "compass info",
            permissions = {"canary.command.player.compass"},
            toolTip = "/compass",
            min = 1)
    public void compassCommand(MessageReceiver caller, String[] parameters) {
        natives.get("compass").execute(caller, parameters);
    }

    @Command(aliases = {"createvanilla", "makevanilla"},
            description = "makevanilla info",
            permissions = {"canary.super.createvanilla", "canary.command.super.createvanilla"},
            toolTip = "/createvanilla <defaultworld>",
            min = 2)
    public void createVanillaCommand(MessageReceiver caller, String[] parameters) {
        natives.get("createvanilla").execute(caller, parameters);
    }

    @Command(aliases = {"pos", "getpos"},
            description = "getpos info",
            permissions = {"canary.command.player.getpos"},
            toolTip = "/getpos")
    public void getPosCommand(MessageReceiver caller, String[] parameters) {
        natives.get("pos").execute(caller, parameters);
    }

    // XXX groupmod start
    @Command(aliases = {"groupmod", "group"},
            description = "groupmod info",
            permissions = {"canary.command.super.groupmod"},
            toolTip = "/groupmod <add|delete|rename|permission|list> [parameters...] [--help]",
            min = 1,
            tabCompleteMethod = "groupBaseTabComplete"
    )
    public void groupBase(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_base").execute(caller, parameters);
    }

    @TabComplete
    public List<String> groupBaseTabComplete(MessageReceiver caller, String[] parameters) {
        //TODO: finish implemeting arguments
        switch (parameters.length) {
            case 1:
                return matchTo(parameters, new String[]{"add", "delete", "rename", "permission", "list"});
            case 2:
                if (parameters[0].matches("(delete|remove|rename)")) {
                    return matchToGroup(parameters);
                }
            case 3:
                if (parameters[1].equals("permission")) {
                    return matchTo(parameters, new String[]{"add", "delete", "check", "list", "flush"});
                }
            case 4:
                if (parameters[1].matches("(add|create)")) {
                    return matchToGroup(parameters);
                }
            default:
                return null;
        }
    }

    @Command(aliases = {"add", "create"},
            parent = "groupmod",
            helpLookup = "groupmod add",
            description = "group add info",
            permissions = {"canary.command.super.groupmod.add"},
            toolTip = "/groupmod add <name> [[parent] [world[:dimension]]]",
            min = 2,
            max = 4
    )
    public void groupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_add").execute(caller, parameters);
    }

    @Command(aliases = {"permission", "perms"},
            parent = "groupmod",
            helpLookup = "groupmod permission",
            description = "group permission info",
            permissions = {"canary.command.super.groupmod.permissions"},
            toolTip = "/groupmod permission <add|remove|check|list> [arguments...] [--help]",
            min = 2)
    public void groupPerms(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "groupmod permission");
    }

    @Command(aliases = {"add"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission add",
            description = "groupmod permission add info",
            permissions = {"canary.command.super.groupmod.permissions"},
            toolTip = "/groupmod permission add <group> <path>:[value]",
            min = 3)
    public void groupPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_add").execute(caller, parameters);
    }

    @Command(aliases = {"remove"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission remove",
            description = "groupmod permission remove info",
            permissions = {"canary.command.super.groupmod.permissions"},
            toolTip = "/groupmod permission remove <group> <path>:[value]",
            min = 3)
    public void groupPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_remove").execute(caller, parameters);
    }

    @Command(aliases = {"check"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission check",
            description = "groupmod permission check info",
            permissions = {"canary.command.super.groupmod.permissions"},
            toolTip = "/groupmod permission check <group> <path>:[value]",
            min = 3)
    public void groupPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_check").execute(caller, parameters);
    }

    @Command(aliases = {"list"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission list",
            description = "groupmod permission list info",
            permissions = {"canary.command.super.groupmod.permissions"},
            toolTip = "/groupmod permission list <group>",
            min = 2)
    public void groupPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_list").execute(caller, parameters);
    }

    @Command(aliases = {"flush"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission flush",
            description = "group permissionflush info",
            permissions = {"canary.command.super.groupmod.flush"},
            toolTip = "/groupmod permission flush <group>",
            min = 2)
    public void groupFlush(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_flush").execute(caller, parameters);
    }

    @Command(aliases = {"list", "show"},
            parent = "groupmod",
            helpLookup = "groupmod list",
            description = "group list info",
            permissions = {"canary.command.super.groupmod.list"},
            toolTip = "/groupmod list")
    public void groupList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_list").execute(caller, parameters);
    }

    @Command(aliases = {"delete", "remove"},
            parent = "groupmod",
            helpLookup = "groupmod remove",
            description = "group remove info",
            permissions = {"canary.command.super.groupmod.delete"},
            toolTip = "/groupmod remove <name>",
            min = 2)
    public void groupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_remove").execute(caller, parameters);
    }

    @Command(aliases = {"check", "show"},
            parent = "groupmod",
            helpLookup = "groupmod check",
            description = "group check info",
            permissions = {"canary.command.super.groupmod.check"},
            toolTip = "/groupmod check <name>",
            min = 2)
    public void groupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_check").execute(caller, parameters);
    }

    @Command(aliases = {"rename"},
            parent = "groupmod",
            helpLookup = "groupmod rename",
            description = "group rename info",
            permissions = {"canary.command.super.groupmod.rename"},
            toolTip = "/groupmod rename <group> <newname>",
            min = 3)
    public void groupRename(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_rename").execute(caller, parameters);
    }

    @Command(aliases = {"prefix"},
            parent = "groupmod",
            helpLookup = "groupmod prefix",
            description = "group prefix info",
            permissions = {"canary.command.super.groupmod.prefix"},
            toolTip = "/groupmod prefix <group> <prefix>",
            min = 2)
    public void groupPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_prefix").execute(caller, parameters);
    }

    @Command(aliases = {"parent"},
            parent = "groupmod",
            helpLookup = "groupmod parent",
            description = "group parent info",
            permissions = {"canary.command.super.groupmod.parent"},
            toolTip = "/groupmod parent <group> <parent group>",
            min = 3)
    public void groupParent(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_parent").execute(caller, parameters);
    }
    // groupmod end

    // XXX PLAYER Start
    @Command(aliases = {"playermod", "player"},
            description = "playermod info",
            permissions = {"canary.command.super.playermod"},
            toolTip = "/playermod <add|remove|prefix|permission|group> [parameters...] [--help]")
    public void playerBase(MessageReceiver caller, String[] parameters) {
        natives.get("playermod").execute(caller, parameters);
    }

    @Command(aliases = {"add", "create"},
            parent = "playermod",
            helpLookup = "playermod add",
            description = "playermod add info",
            permissions = {"canary.command.super.playermod.add"},
            toolTip = "/playermod add <name> <group>",
            min = 3)
    public void playerAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_add").execute(caller, parameters);
    }

    @Command(aliases = {"permission", "perms"},
            parent = "playermod",
            helpLookup = "playermod permission",
            description = "playermod permission info",
            permissions = {"canary.command.super.playermod.permissions"},
            toolTip = "/playermod permission <add|remove|check|list> [arguments...] [--help]", // <player> <path>:[value] <add|remove|check|list>
            min = 1)
    public void playerPermissions(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod permission");
    }

    @Command(aliases = {"add"},
            parent = "playermod.permission",
            helpLookup = "playermod permission add",
            description = "playermod permission add info",
            permissions = {"canary.command.super.playermod.permissions"},
            toolTip = "/playermod permission add <player> <path>:[value]",
            min = 3)
    public void playerPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_add").execute(caller, parameters);
    }

    @Command(aliases = {"remove"},
            parent = "playermod.permission",
            helpLookup = "playermod permission remove",
            description = "playermod permission remove info",
            permissions = {"canary.command.super.playermod.permissions"},
            toolTip = "/playermod permission remove <player> <path>",
            min = 3)
    public void playerPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_remove").execute(caller, parameters);
    }

    @Command(aliases = {"check"},
            parent = "playermod.permission",
            helpLookup = "playermod permission check",
            description = "playermod permission check info",
            permissions = {"canary.command.super.playermod.permissions"},
            toolTip = "/playermod permission check <player> <path>",
            min = 3)
    public void playerPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_check").execute(caller, parameters);
    }

    @Command(aliases = {"list"},
            parent = "playermod.permission",
            helpLookup = "playermod permission list",
            description = "playermod permission list info",
            permissions = {"canary.command.super.playermod.permissions"},
            toolTip = "/playermod permission list <player>",
            min = 2)
    public void playerPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_list").execute(caller, parameters);
    }

    @Command(aliases = {"prefix", "color"},
            parent = "playermod",
            helpLookup = "playermod prefix",
            description = "playermod prefix info",
            permissions = {"canary.command.super.playermod.prefix"},
            toolTip = "/playermod prefix <name> <prefix>",
            min = 2)
    public void playerPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_prefix").execute(caller, parameters);
    }

    @Command(aliases = {"remove", "delete"},
            parent = "playermod",
            helpLookup = "playermod remove",
            description = "playermod remove info",
            permissions = {"canary.command.super.playermod.remove"},
            toolTip = "/playermod remove <name>",
            min = 2)
    public void playerRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_remove").execute(caller, parameters);
    }

    @Command(aliases = {"group"},
            parent = "playermod",
            helpLookup = "playermod group",
            description = "playermod group info",
            permissions = {"canary.command.super.playermod.group"},
            toolTip = "/playermod group <list|check|set|add> [arguments...] [--help]",
            min = 1)
    public void playerGroup(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod group");
    }

    @Command(aliases = {"set"},
            parent = "playermod.group",
            helpLookup = "playermod group set",
            description = "playermod group set info",
            permissions = {"canary.command.super.playermod.group.set"},
            toolTip = "/playermod group set <player> <group> [--help]",
            min = 2)
    public void playerGroupSet(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_set").execute(caller, parameters);
    }

    @Command(aliases = {"add"},
            parent = "playermod.group",
            helpLookup = "playermod group add",
            description = "playermod group add info",
            permissions = {"canary.command.super.playermod.group.add"},
            toolTip = "/playermod group add <player> <group> [--help]",
            min = 2)
    public void playerGroupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_add").execute(caller, parameters);
    }

    @Command(aliases = {"list"},
            parent = "playermod.group",
            helpLookup = "playermod group list",
            description = "playermod group list info",
            permissions = {"canary.command.super.playermod.group.list"},
            toolTip = "/playermod group list <player> [--help]",
            min = 2)
    public void playerGroupList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_list").execute(caller, parameters);
    }

    @Command(aliases = {"check"},
            parent = "playermod.group",
            helpLookup = "playermod group check",
            description = "playermod group check info",
            permissions = {"canary.command.super.playermod.group.check"},
            toolTip = "/playermod group check <player> <group> [--help]",
            min = 3)
    public void playerGroupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_check").execute(caller, parameters);
    }

    @Command(aliases = {"remove"},
            parent = "playermod.group",
            helpLookup = "playermod group remove",
            description = "playermod group remove info",
            permissions = {"canary.command.super.playermod.group.remove"},
            toolTip = "/playermod group remove <player> <group> [--help]",
            min = 3)
    public void playerGroupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_remove").execute(caller, parameters);
    }

    // playermod end

    @Command(aliases = {"help"},
            description = "help info",
            permissions = {"canary.command.help"},
            toolTip = "/help [search terms] [page]",
            min = 1)
    public void helpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("help").execute(caller, parameters);
    }

    @Command(aliases = {"home"},
            description = "home info",
            permissions = {"canary.command.teleport.home"},
            toolTip = "/home [playername]",
            min = 1,
            max = 2,
            tabCompleteMethod = "matchKnownPlayer"
    )
    public void homeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("home").execute(caller, parameters);
    }

    @Command(aliases = {"ipban"},
            description = "ipban info",
            permissions = {"canary.super.ipban", "canary.command.super.ipban"},
            toolTip = "/ipban <player> [reason] [#number hour|day|week|month]",
            min = 2)
    public void ipBanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ipban").execute(caller, parameters);
    }

    @Command(aliases = {"kick"},
            description = "kick info",
            permissions = {"canary.super.kick", "canary.command.super.kick"},
            toolTip = "/kick <playername> [reason]",
            min = 2,
            tabCompleteMethod = "matchOnlinePlayer"
    )
    public void kickCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kick").execute(caller, parameters);
    }

    @Command(aliases = {"kill", "murder"},
            description = "kill info",
            permissions = {"canary.command.player.kill"},
            toolTip = "/kill [playername]",
            min = 1,
            tabCompleteMethod = "matchOnlinePlayer"
    )
    public void killCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kill").execute(caller, parameters);
    }

    @Command(aliases = {"kit"},
            description = "kit info",
            permissions = {"canary.command.player.kit"},
            toolTip = "/kit <give|create|list> <name> <use delay> [G|P Groups|Players]",
            min = 2,
            tabCompleteMethod = "kitTabComplete"
    )
    public void kitCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kit").execute(caller, parameters);
    }

    @TabComplete
    public List<String> kitTabComplete(MessageReceiver caller, String[] parameters) {
        switch (parameters.length) {
            case 1:
                return matchTo(parameters, new String[]{"give", "create", "list"});
            case 2:
                if (parameters[1].equals("give")) {
                    return matchToKitNames(parameters, caller);
                }
            default:
                return null;
        }
    }

    @Command(aliases = {"listplugins", "plugins"},
            description = "lplugin info",
            permissions = {"canary.command.plugin.list"},
            toolTip = "/listplugins")
    public void listPluginsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listplugins").execute(caller, parameters);
    }

    @Command(aliases = {"motd"},
            description = "motd info",
            permissions = {"canary.command.motd"},
            toolTip = "/motd",
            min = 1)
    public void motdCommand(MessageReceiver caller, String[] parameters) {
        natives.get("motd").execute(caller, parameters);
    }

    @Command(aliases = {"mute", "stfu"},
            description = "mute info",
            permissions = {"canary.super.mute", "canary.command.super.mute"},
            toolTip = "/mute <playername>",
            min = 2,
            tabCompleteMethod = "matchOnlinePlayer"
    )
    public void muteCommand(MessageReceiver caller, String[] parameters) {
        natives.get("mute").execute(caller, parameters);
    }

    @Command(aliases = {"playerlist", "players", "who"},
            description = "who info",
            permissions = {"canary.command.player.list"},
            toolTip = "/who")
    public void playerListCommand(MessageReceiver caller, String[] parameters) {
        natives.get("playerlist").execute(caller, parameters);
    }

    @Command(aliases = {"enableplugin"},
            description = "plugin enable info",
            permissions = {"canary.command.plugin.enable"},
            toolTip = "/enableplugin <plugin>",
            min = 2)
    public void enablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("enableplugin").execute(caller, parameters);
    }

    @Command(aliases = {"disableplugin"},
            description = "plugin disable info",
            permissions = {"canary.command.plugin.disable"},
            toolTip = "/disableplugin <plugin>",
            min = 2,
            tabCompleteMethod = "matchPluginName"
    )
    public void disablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("disableplugin").execute(caller, parameters);
    }

    @Command(aliases = {"reloadplugin"},
            description = "plugin reload info",
            permissions = {"canary.command.plugin.reload"},
            toolTip = "/reloadplugin <plugin>",
            min = 2,
            tabCompleteMethod = "matchPluginName"
    )
    public void reloadPluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reloadplugin").execute(caller, parameters);
    }

    @Command(aliases = {"reload"},
            description = "reload info",
            permissions = {"canary.super.reload", "canary.command.super.reload"},
            toolTip = "/reload")
    public void reloadCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reload").execute(caller, parameters);
    }

    @Command(aliases = {"sethome"},
            description = "sethome info",
            permissions = {"canary.command.teleport.sethome"},
            toolTip = "/sethome [player]",
            min = 1,
            max = 2
    )
    public void setHomeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("sethome").execute(caller, parameters);
    }

    @Command(aliases = {"warp"},
            description = "warp info",
            permissions = {"canary.command.warp.use"},
            toolTip = "/warp <name>",
            min = 2,
            max = 2,
            tabCompleteMethod = "matchWarpNames"
    )
    public void warpUse(MessageReceiver caller, String[] parameters) {
        natives.get("warp").execute(caller, parameters);
    }

    @Command(aliases = {"setwarp"},
            description = "setwarp info",
            permissions = {"canary.command.warp.set"},
            toolTip = "/setwarp <name> [G <group>|P <player>]",
            min = 2)
    public void setWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("setwarp").execute(caller, parameters);
    }

    @Command(aliases = {"listwarps", "warps"},
            description = "lwarps info",
            permissions = {"canary.command.warp.list"},
            toolTip = "/listwarps")
    public void listWarpsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listwarps").execute(caller, parameters);
    }

    @Command(aliases = {"delwarp", "removewarp"},
            description = "delwarp info",
            permissions = {"canary.command.warp.remove"},
            toolTip = "/delwarp <name>",
            min = 2,
            tabCompleteMethod = "matchWarpNames"
    )
    public void delWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("delwarp").execute(caller, parameters);
    }

    @Command(aliases = {"spawn"},
            description = "spawn info",
            permissions = {"canary.command.teleport.spawn"},
            toolTip = "/spawn [worldname] [player]",
            min = 1,
            max = 3,
            tabCompleteMethod = "matchWorldNamePlayerName"
    )
    public void spawnCommand(MessageReceiver caller, String[] parameters) {
        natives.get("spawn").execute(caller, parameters);
    }

    @Command(aliases = {"stop", "shutdown"},
            description = "stop info",
            permissions = {"canary.super.command.stop"},
            toolTip = "/stop")
    public void stopCommand(MessageReceiver caller, String[] parameters) {
        natives.get("stop").execute(caller, parameters);
    }

    @Command(aliases = {"whitelist", "wlist", "wl"},
            description = "whitelist info",
            permissions = {"canary.command.super.whitelist"},
            toolTip = "/whitelist <add|remove> <playername>",
            min = 3,
            tabCompleteMethod = "whitelistTabComplete"
    )
    public void whitelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("whitelist").execute(caller, parameters);
    }

    @TabComplete
    public List<String> whitelistTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove"})
                : parameters.length == 2 && parameters[0].equals("remove") ? matchTo(parameters, Canary.whitelist().getWhitelisted())
                : null;
    }

    @Command(aliases = {"god", "godmode"},
            description = "enable god mode",
            permissions = {"canary.command.god", "canary.command.god.other"},
            toolTip = "/god [playername]",
            min = 1,
            max = 2,
            tabCompleteMethod = "matchOnlinePlayer"
    )
    public void godCommand(MessageReceiver caller, String[] parameters) {
        natives.get("god").execute(caller, parameters);
    }

    @Command(aliases = {"reservelist", "rlist", "rl"},
            description = "reservelist info",
            permissions = {"canary.command.super.reservelist"},
            toolTip = "/reservelist <add|remove> <playername>",
            min = 3,
            tabCompleteMethod = "reservelistTabComplete"
    )
    public void reservelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reservelist").execute(caller, parameters);
    }

    @TabComplete
    public List<String> reservelistTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove"})
                : parameters.length == 2 && parameters[0].equals("remove") ? matchTo(parameters, Canary.reservelist().getReservations())
                : null;
    }

    @Command(aliases = {"canarymod", "version"},
            description = "CanaryMod Information",
            permissions = {"canary.command.canarymod"},
            toolTip = "/canarymod")
    public void canarymodInfoCommand(MessageReceiver caller, String[] parameters) {
        natives.get("canarymod").execute(caller, parameters);
    }

    @Command(aliases = {"playerinfo", "pinfo"},
            description = "Player Information",
            permissions = {"canary.command.player.information"},
            toolTip = "/playerinfo [player]",
            tabCompleteMethod = "matchKnownPlayer"
    )
    public void playerinfo(MessageReceiver caller, String[] parameters) {
        natives.get("playerinfo").execute(caller, parameters);
    }

    @Command(aliases = {"sysinfo"},
            description = "System Information",
            permissions = {"canary.command.sysinfo"},
            toolTip = "/sysinfo")
    public void sysinfo(MessageReceiver caller, String[] parameters) {
        natives.get("sysinfo").execute(caller, parameters);
    }

    @Command(aliases = {"uptime"},
            description = "server uptime",
            permissions = {"canary.command.uptime"},
            toolTip = "/uptime")
    public void uptime(MessageReceiver caller, String[] parameters) {
        natives.get("uptime").execute(caller, parameters);
    }

    @Command(
            aliases = {"loadworld"},
            description = "loads a world",
            permissions = {"canary.commmand.world.load"},
            toolTip = "/loadworld <worldName> [dimensionType]",
            min = 2,
            tabCompleteMethod = "matchWorldNameDimension"
    )
    public void loadWorld(MessageReceiver caller, String[] args) {
        natives.get("loadworld").execute(caller, args);
    }

    @Command(
            aliases = {"createworld"},
            description = "creates a world",
            permissions = {"canary.command.world.create"},
            toolTip = "/createworld <name> [seed] [dimensionType] [worldType]",
            min = 2,
            max = 5,
            tabCompleteMethod = "matchPast2DimensionTypeWorldType"
    )
    public void createWorld(MessageReceiver caller, String[] args) {
        natives.get("createworld").execute(caller, args);
    }

    /* START: Vanilla command wrappers... */
    @Command(
            aliases = {"achievement"},
            description = "Gives a player an achievement or increases a statistic.",
            permissions = {"canary.command.achievement"},
            toolTip = "/achievement <give|take> <stat_name|*> [player]",
            min = 2,
            max = 3,
            version = 2
    )
    public void achievement(MessageReceiver caller, String[] args) {
        natives.get("achievement").execute(caller, args);
    }

    @Command(
            aliases = {"say", "broadcast"},
            description = "Broadcasts a message",
            permissions = {"canary.command.broadcast"},
            toolTip = "/say <message>",
            min = 1,
            version = 2
    )
    public void broadcast(MessageReceiver caller, String[] args) {
        natives.get("broadcast").execute(caller, args);
    }

    @Command(
            aliases = {"clear", "clearinventory", "clearinv"},
            description = "Clears items from player inventory.",
            permissions = {"canary.command.clear"},
            toolTip = "/clear <player> [item] [data] [maxCount] [dataTag]",
            min = 1,
            version = 2
    )
    public void clear(MessageReceiver caller, String[] args) {
        natives.get("clear").execute(caller, args);
    }

    @Command(
            aliases = {"debug"},
            description = "Starts or stops a debugging session.",
            permissions = {"canary.command.debug"},
            toolTip = "/debug <start|stop|chunk> [<x> <y> <z>]",
            min = 1,
            version = 2
    )
    public void debug(MessageReceiver caller, String[] args) {
        natives.get("debug").execute(caller, args);
    }

    @Command(
            aliases = {"defaultgamemode", "defaultmode"},
            description = "Sets the default game mode (creative, survival, etc.) for new players entering a multiplayer server.",
            permissions = {"canary.command.defaultgamemode"},
            toolTip = "/defaultgamemode <gamemode>",
            min = 1,
            version = 2
    )
    public void defaultgamemode(MessageReceiver caller, String[] args) {
        natives.get("defaultgamemode").execute(caller, args);
    }

    @Command(
            aliases = {"setworldspawn", "setspawn"},
            description = "Sets everyones GameMode",
            permissions = {"canary.command.setworldspawn"},
            toolTip = "/setworldspawn [<x> <y> <z>]",
            min = 0,
            version = 2
    )
    public void defaultspawnpoint(MessageReceiver caller, String[] args) {
        natives.get("defaultspawnpoint").execute(caller, args);
    }

    @Command(
            aliases = {"difficulty"},
            description = "Sets the difficulty level (peaceful, easy, etc.).",
            permissions = {"canary.command.difficulty"},
            toolTip = "/difficulty <new difficulty> [world]",
            min = 1,
            version = 2
    )
    public void difficulty(MessageReceiver caller, String[] args) {
        natives.get("difficulty").execute(caller, args);
    }

    @Command(
            aliases = {"effect"},
            description = "The effect command manages status effects on players and other entities.",
            permissions = {"canary.command.enchant"},
            toolTip = "/effect <player> <clear|effect> [seconds] [amplifier] [hideParticles]",
            min = 2,
            version = 2
    )
    public void effect(MessageReceiver caller, String[] args) {
        natives.get("effect").execute(caller, args);
    }

    @Command(
            aliases = {"emote", "me"},
            description = "Express an emotion",
            permissions = {"canary.command.emote"},
            toolTip = "/emote <action...>",
            version = 2
    )
    public void emote(MessageReceiver caller, String[] args) {
        natives.get("emote").execute(caller, args);
    }

    @Command(
            aliases = {"enchant"},
            description = "Enchants target's held item",
            permissions = {"canary.command.enchant"},
            toolTip = "/enchant <player> <enchantment ID> [level]",
            min = 2,
            version = 2
    )
    public void enchant(MessageReceiver caller, String[] args) {
        natives.get("enchant").execute(caller, args);
    }

    @Command(
            aliases = {"gamemode", "mode"},
            description = "Sets a player's game mode.",
            permissions = {"canary.command.gamemode"},
            toolTip = "/gamemode <mode> [player]",
            min = 1,
            version = 2
    )
    public void gamemode(MessageReceiver caller, String[] args) {
        natives.get("gamemode").execute(caller, args);
    }

    @Command(
            aliases = {"gamerule"},
            description = "Sets or queries a game rule value.",
            permissions = {"canary.command.gamerule"},
            toolTip = "/gamerule <rule name> [value]",
            version = 2
    )
    public void gamerule(MessageReceiver caller, String[] args) {
        natives.get("gamerule").execute(caller, args);
    }

    @Command(
            aliases = {"give", "item", "i"},
            description = "Gives an item to a player.",
            permissions = {"canary.command.give"},
            toolTip = "/give <player> <item> [amount] [data] [dataTag]",
            min = 2,
            version = 2
    )
    public void give(MessageReceiver caller, String[] args) {
        natives.get("give").execute(caller, args);
    }

    @Command(
            aliases = {"message", "msg", "tell"},
            description = "Sends a private message to a player",
            permissions = {"canary.command.message"},
            toolTip = "/tell <player> <private message ...>",
            min = 2,
            version = 2
    )
    public void message(MessageReceiver caller, String[] args) {
        natives.get("message").execute(caller, args);
    }

    @Command(
            aliases = {"messageraw", "msgraw", "tellraw"},
            description = "Sends a private message to a player",
            permissions = {"canary.command.message"},
            toolTip = "/tellraw <player> <raw json message>",
            min = 2,
            version = 2
    )
    public void messageraw(MessageReceiver caller, String[] args) {
        natives.get("messageraw").execute(caller, args);
    }

    @Command(
            aliases = {"playsound"},
            description = "Plays a sound",
            permissions = {"canary.command.playsound"},
            toolTip = "/playsound <sound> <player> [x] [y] [z] [volume] [pitch] [minimumVolume]",
            min = 2,
            version = 2
    )
    public void playsound(MessageReceiver caller, String[] args) {
        natives.get("playsound").execute(caller, args);
    }

    @Command(
            aliases = {"save-all", "saveall"},
            description = "Saves world data",
            permissions = {"canary.command.saveall"},
            toolTip = "/save-all",
            min = 0,
            version = 2
    )
    public void saveall(MessageReceiver caller, String[] args) {
        natives.get("save-all").execute(caller, args);
    }

    @Command(
            aliases = {"save-off", "saveoff"},
            description = "Turns off world data saving",
            permissions = {"canary.command.saveoff"},
            toolTip = "/save-off",
            min = 0,
            version = 2
    )
    public void saveoff(MessageReceiver caller, String[] args) {
        natives.get("save-off").execute(caller, args);
    }

    @Command(
            aliases = {"save-on", "saveon"},
            description = "Turns on world data saving",
            permissions = {"canary.command.saveon"},
            toolTip = "/save-off",
            min = 0,
            version = 2
    )
    public void saveon(MessageReceiver caller, String[] args) {
        natives.get("save-on").execute(caller, args);
    }

    @Command(
            aliases = {"scoreboard"},
            description = "SCORE(BOARD)!",
            permissions = {"canary.command.scoreboard"},
            toolTip = "/scoreboard <objectives|players|teams>",
            min = 1,
            version = 2
    )
    public void scoreboard(MessageReceiver caller, String[] args) {
        natives.get("scoreboard").execute(caller, args);
    }

    @Command(
            aliases = {"setblock"},
            description = "Sets a block",
            permissions = {"canary.command.setblock"},
            toolTip = "/setblock <x> <y> <z> <TileName> [dataValue] [oldBlockHandling] [dataTag]",
            min = 4,
            version = 2
    )
    public void setblock(MessageReceiver caller, String[] args) {
        natives.get("setblock").execute(caller, args);
    }

    @Command(
            aliases = {"spawnpoint"},
            description = "Spawnpoint setting (Player based)",
            permissions = {"canary.command.spawnpoint"},
            toolTip = "/spawnpoint [player [<x> <y> <z>]]",
            min = 0,
            version = 2
    )
    public void spawnpoint(MessageReceiver caller, String[] args) {
        natives.get("spawnpoint").execute(caller, args);
    }

    @Command(
            aliases = {"spreadplayers"},
            description = "Spreads out them players",
            permissions = {"canary.command.spreadplayers"},
            toolTip = "/spreadplayers <x> <z> <spreadDistance> <maxRange> <respectTeams true|false> <player ...>",
            min = 4,
            version = 2
    )
    public void spreadplayers(MessageReceiver caller, String[] args) {
        natives.get("spreadplayers").execute(caller, args);
    }

    @Command(
            aliases = {"summon", "mspawn", "mobspawn", "spawnmob"},
            description = "Summons an entity",
            permissions = {"canary.command.summon"},
            toolTip = "/summon <EntityName> [x] [y] [z] [dataTag]",
            min = 1,
            version = 2
    )
    public void summon(MessageReceiver caller, String[] args) {
        natives.get("summon").execute(caller, args);
    }

    @Command(
            aliases = {"teleport", "tp"},
            description = "Teleports a player",
            permissions = {"canary.command.teleport"},
            toolTip = "/tp [target] <player | <<x> <y> <z>>> | /tp <target> <x> <y> <z> <world_fqname> [load]",
            min = 1,
            version = 2
    )
    public void teleport(MessageReceiver caller, String[] args) {
        natives.get("teleport").execute(caller, args);
    }

    @Command(
            aliases = {"testfor"},
            description = "Counts entities (players, mobs, items, etc.) matching specified conditions.",
            permissions = {"canary.command.testfor"},
            toolTip = "/testfor <player|targetselector> [datatag]",
            min = 1,
            version = 2
    )
    public void testfor(MessageReceiver caller, String[] args) {
        natives.get("testfor").execute(caller, args);
    }

    @Command(
            aliases = {"testforblock"},
            description = "Tests whether a certain block is in a specific location.",
            permissions = {"canary.command.testforblock"},
            toolTip = "/testforblock <x> <y> <z> <TileName> [dataValue] [dataTag]",
            min = 1,
            version = 2
    )
    public void testforblock(MessageReceiver caller, String[] args) {
        natives.get("testforblock").execute(caller, args);
    }

    @Command(
            aliases = {"time"},
            description = "Changes or queries the world's game time.",
            permissions = {"canary.command.time"},
            toolTip = "/time <add|query|set> <value>",
            min = 2,
            version = 2
    )
    public void time(MessageReceiver caller, String[] args) {
        natives.get("time").execute(caller, args);
    }

    @Command(
            aliases = {"toggledownfall"},
            description = "Toggles the weather.",
            permissions = {"canary.command.toggledownfall"},
            toolTip = "/Toggles the weather.",
            min = 0,
            version = 2
    )
    public void toggledownfall(MessageReceiver caller, String[] args) {
        natives.get("toggledownfall").execute(caller, args);
    }

    @Command(
            aliases = {"weather"},
            description = "Sets the weather.",
            permissions = {"canary.command.weather"},
            toolTip = "/weather <clear|rain|thunder> [duration in seconds]",
            min = 1,
            version = 2
    )
    public void weather(MessageReceiver caller, String[] args) {
        natives.get("weather").execute(caller, args);
    }

    @Command(
            aliases = {"xp", "experience"},
            description = "Adds experience to a player.",
            permissions = {"canary.command.xp"},
            toolTip = "/xp <amount[L]> [player]",
            min = 1,
            version = 2
    )
    public void xp(MessageReceiver caller, String[] args) {
        natives.get("xp").execute(caller, args);
    }
    /* END: Vanilla command wrappers */

    /* All the reused tab complete stuff */
    @TabComplete
    public List<String> matchKnownPlayer(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownPlayer(args) : null;
    }

    @TabComplete
    public List<String> matchOnlinePlayer(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToOnlinePlayer(args) : null;
    }

    @TabComplete
    public List<String> matchWarpNames(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToWarpNames(args, caller) : null;
    }

    @TabComplete
    public List<String> matchPluginName(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchTo(args, Canary.manager().getPluginNames().toArray(new String[0])) : null;
    }

    @TabComplete
    public List<String> matchWorldNamePlayerName(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownWorld(args)
                : args.length == 2 ? matchToOnlinePlayer(args)
                : null;
    }

    @TabComplete
    public List<String> matchWorldNameDimension(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownWorld(args)
                : args.length == 2 ? matchToDimension(args)
                : null;
    }

    @TabComplete
    public List<String> matchPast2DimensionTypeWorldType(MessageReceiver caller, String[] args) {
        return args.length == 3 ? matchToDimension(args)
                : args.length == 4 ? matchToWorldType(args)
                : null;
    }

    @TabComplete
    public List<String> matchItemTypeDataAmountPlayerNames(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToItemTypeAndData(args)
                : args.length == 2 ? new ArrayList<String>(0)
                : args.length == 3 ? matchToOnlinePlayer(args)
                : null;
    }
}
