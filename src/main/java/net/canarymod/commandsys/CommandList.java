package net.canarymod.commandsys;

import net.canarymod.Canary;
import net.canarymod.chat.MessageReceiver;
import net.canarymod.commandsys.commands.groupmod.*;
import net.canarymod.commandsys.commands.player.Compass;
import net.canarymod.commandsys.commands.player.GetPosition;
import net.canarymod.commandsys.commands.player.GodCommand;
import net.canarymod.commandsys.commands.player.Kill;
import net.canarymod.commandsys.commands.playermod.*;
import net.canarymod.commandsys.commands.system.*;
import net.canarymod.commandsys.commands.vanilla.*;
import net.canarymod.commandsys.commands.warp.*;
import net.canarymod.commandsys.commands.world.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.canarymod.commandsys.CanaryCommandPermissions.*;
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

        /* groupmod */
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

        /* player */
        temp.put("compass", new Compass());
        temp.put("pos", new GetPosition());
        temp.put("god", new GodCommand());
        temp.put("kill", new Kill());

        /* playermod */
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

        /* system */
        temp.put("ban", new BanCommand());
        temp.put("canarymod", new CanaryModCommand());
        temp.put("createvanilla", new CreateVanilla());
        temp.put("deop", new DeOp());
        temp.put("help", new HelpCommand());
        temp.put("ipban", new IpBanCommand());
        temp.put("kick", new Kick());
        temp.put("kit", new KitCommand());
        temp.put("listplugins", new ListPlugins());
        temp.put("motd", new Motd());
        temp.put("mute", new Mute());
        temp.put("op", new Op());
        temp.put("oplist", new OpList());
        temp.put("playerinfo", new PlayerInformation());
        temp.put("playerlist", new PlayerList());
        temp.put("enableplugin", new PluginCommand(false, false));
        temp.put("disableplugin", new PluginCommand(true, false));
        temp.put("reloadplugin", new PluginCommand(false, true));
        temp.put("reload", new ReloadCommand());
        temp.put("reservelist", new ReservelistCommand());
        temp.put("stop", new StopServer());
        temp.put("sysinfo", new SystemInformation());
        temp.put("unban", new UnbanCommand());
        temp.put("uptime", new Uptime());
        temp.put("whitelist", new WhitelistCommand());

        /* vanilla */
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
        temp.put("message", new Message());
        temp.put("messageraw", new MessageRaw());
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

        /* warp */
        temp.put("home", new Home());
        temp.put("sethome", new SetHome());
        temp.put("spawn", new SpawnCommand());
        temp.put("listwarps", new WarpList());
        temp.put("delwarp", new WarpRemove());
        temp.put("setwarp", new WarpSet());
        temp.put("warp", new WarpUse());

        /* world */
        temp.put("createworld", new CreateWorldCommand());
        temp.put("deleteworld", new DeleteWorldCommand());
        temp.put("loadworld", new LoadWorldCommand());
        temp.put("mobclear", new MobClear());
        temp.put("mobcount", new MobCount());

        natives = Collections.unmodifiableMap(temp);
    }

    private static String perm(CanaryCommandPermissions perm) {
        return perm.toString();
    }

    /* groupmod start */
    @Command(
            aliases = {"groupmod", "group"},
            description = "groupmod info",
            permissions = {GROUPMOD},
            toolTip = "/groupmod <add|delete|rename|permission|list> [parameters...] [--help]",
            version = 2
    )
    public void groupBase(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_base").execute(caller, parameters);
    }

    @TabComplete(commands = {"groupmod"})
    public List<String> groupBaseTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "delete", "rename", "permission", "list"}) : null;
    }

    @Command(
            aliases = {"add", "create"},
            parent = "groupmod",
            helpLookup = "groupmod add",
            description = "group add info",
            permissions = {GROUPMOD$ADD},
            toolTip = "/groupmod add <name> [[parent] [world[:dimension]]]",
            max = 3,
            version = 2
    )
    public void groupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_add").execute(caller, parameters);
    }

    @TabComplete(commands = {"groupmod add", "groupmod create"})
    public List<String> groupmodAddTabComplete(MessageReceiver caller, String[] parameters) {
        switch (parameters.length) {
            // case 1 would be user submitted and unknown
            case 2:
                return matchToGroup(parameters);
            case 3:
                return matchToKnownWorld(parameters);
            default:
                return null;
        }
    }

    @Command(
            aliases = {"permission", "perms"},
            parent = "groupmod",
            helpLookup = "groupmod permission",
            description = "group permission info",
            permissions = {GROUPMOD$PERMISSIONS},
            toolTip = "/groupmod permission <add|remove|check|list> [arguments...] [--help]"
    )
    public void groupPerms(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "groupmod permission");
    }

    @TabComplete(commands = {"groupmod permission", "groupmod perms"})
    public List<String> groupmodPermissionTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove", "check", "list"}) : null;
    }

    @Command(
            aliases = {"add"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission add",
            description = "groupmod permission add info",
            permissions = {GROUPMOD$PERMISSIONS$ADD},
            toolTip = "/groupmod permission add <group> <path>:[value]",
            min = 2
    )
    public void groupPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_add").execute(caller, parameters);
    }

    @Command(
            aliases = {"remove"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission remove",
            description = "groupmod permission remove info",
            permissions = {GROUPMOD$PERMISSIONS$REMOVE},
            toolTip = "/groupmod permission remove <group> <path>:[value]",
            min = 2,
            version = 2
    )
    public void groupPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_remove").execute(caller, parameters);
    }

    @Command(
            aliases = {"check"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission check",
            description = "groupmod permission check info",
            permissions = {GROUPMOD$PERMISSIONS$CHECK},
            toolTip = "/groupmod permission check <group> <path>:[value]",
            min = 2,
            version = 2
    )
    public void groupPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_check").execute(caller, parameters);
    }

    @TabComplete(commands = {"groupmod permission add", "groupmod permission remove", "groupmod permission check"})
    public List<String> groupmodPermissionAddRemoveCheckTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToGroup(parameters)
                : parameters.length == 2 && parameters[2].contains(":") ? matchTo(parameters, new String[]{parameters[2].split(":")[0].concat(":true"), parameters[2].split(":")[0].concat(":false")})
                : null;
    }

    @Command(
            aliases = {"list"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission list",
            description = "groupmod permission list info",
            permissions = {GROUPMOD$PERMISSIONS$LIST},
            toolTip = "/groupmod permission list <group>",
            version = 2
    )
    public void groupPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_list").execute(caller, parameters);
    }

    @Command(
            aliases = {"flush"},
            parent = "groupmod.permission",
            helpLookup = "groupmod permission flush",
            description = "group permissionflush info",
            permissions = {GROUPMOD$PERMISSIONS$FLUSH},
            toolTip = "/groupmod permission flush <group>",
            version = 2
    )
    public void groupFlush(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_perms_flush").execute(caller, parameters);
    }

    @Command(
            aliases = {"list", "show"},
            parent = "groupmod",
            helpLookup = "groupmod list",
            description = "group list info",
            permissions = {GROUPMOD$LIST},
            toolTip = "/groupmod list"
    )
    public void groupList(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_list").execute(caller, parameters);
    }

    @Command(
            aliases = {"remove", "delete"},
            parent = "groupmod",
            helpLookup = "groupmod remove",
            description = "group remove info",
            permissions = {GROUPMOD$REMOVE},
            toolTip = "/groupmod remove <name>",
            version = 2
    )
    public void groupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_remove").execute(caller, parameters);
    }

    @Command(
            aliases = {"check", "show"},
            parent = "groupmod",
            helpLookup = "groupmod check",
            description = "group check info",
            permissions = {GROUPMOD$CHECK},
            toolTip = "/groupmod check <name>",
            version = 2
    )
    public void groupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_check").execute(caller, parameters);
    }

    @Command(
            aliases = {"rename"},
            parent = "groupmod",
            helpLookup = "groupmod rename",
            description = "group rename info",
            permissions = {GROUPMOD$RENAME},
            toolTip = "/groupmod rename <group> <newname>",
            min = 2,
            version = 2
    )
    public void groupRename(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_rename").execute(caller, parameters);
    }

    @Command(
            aliases = {"prefix"},
            parent = "groupmod",
            helpLookup = "groupmod prefix",
            description = "group prefix info",
            permissions = {GROUPMOD$PREFIX},
            toolTip = "/groupmod prefix <group> <prefix>",
            min = 2,
            version = 2
    )
    public void groupPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_prefix").execute(caller, parameters);
    }

    @Command(
            aliases = {"parent"},
            parent = "groupmod",
            helpLookup = "groupmod parent",
            description = "group parent info",
            permissions = {GROUPMOD$PARENT},
            toolTip = "/groupmod parent <group> <parent group>",
            min = 2,
            version = 2
    )
    public void groupParent(MessageReceiver caller, String[] parameters) {
        natives.get("groupmod_parent").execute(caller, parameters);
    }

    @TabComplete(commands = {"groupmod parent"})
    public List<String> groupmodParentTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 || parameters.length == 2 ? matchToGroup(parameters) : null;
    }

    @TabComplete(commands = {"groupmod permissions list", "groupmod permission flush", "groupmod remove", "groupmod check", "groupmod rename", "groupmod prefix"})
    public List<String> matchToGroupTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToGroup(parameters) : null;
    }
    /* groupmod end */

    /* player */
    @Command(
            aliases = {"compass"},
            description = "compass info",
            permissions = {COMPASS},
            toolTip = "/compass"
    )
    public void compassCommand(MessageReceiver caller, String[] parameters) {
        natives.get("compass").execute(caller, parameters);
    }

    @Command(
            aliases = {"pos", "getpos"},
            description = "getpos info",
            permissions = {GETPOS},
            toolTip = "/getpos"
    )
    public void getPosCommand(MessageReceiver caller, String[] parameters) {
        natives.get("pos").execute(caller, parameters);
    }

    @Command(
            aliases = {"god", "godmode"},
            description = "enable god mode",
            permissions = {GOD, GOD$OTHER},
            toolTip = "/god [playername]",
            min = 0,
            max = 1,
            version = 2
    )
    public void godCommand(MessageReceiver caller, String[] parameters) {
        natives.get("god").execute(caller, parameters);
    }

    @Command(
            aliases = {"kill", "murder"},
            description = "kill info",
            permissions = {KILL, KILL$OTHER},
            toolTip = "/kill [playername]",
            version = 2
    )
    public void killCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kill").execute(caller, parameters);
    }

    /* playermod start */
    @Command(
            aliases = {"playermod", "player"},
            description = "playermod info",
            permissions = {PLAYERMOD},
            toolTip = "/playermod <add|remove|prefix|permission|group> [parameters...] [--help]"
    )
    public void playerBase(MessageReceiver caller, String[] parameters) {
        natives.get("playermod").execute(caller, parameters);
    }

    @TabComplete(commands = {"playermod", "player"})
    public List<String> playerBaseTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove", "prefix", "permission", "group"}) : null;
    }

    @Command(
            aliases = {"add", "create"},
            parent = "playermod",
            helpLookup = "playermod add",
            description = "playermod add info",
            permissions = {PLAYERMOD$ADD},
            toolTip = "/playermod add <name> <group>",
            min = 2,
            version = 2
    )
    public void playerAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_add").execute(caller, parameters);
    }

    @TabComplete(commands = {"playermod add"})
    public List<String> playerAddTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToOnlinePlayer(parameters)
                : parameters.length == 2 ? matchToGroup(parameters)
                : null;
    }

    @Command(
            aliases = {"permission", "perms"},
            parent = "playermod",
            helpLookup = "playermod permission",
            description = "playermod permission info",
            permissions = {PLAYERMOD$PERMISSIONS},
            toolTip = "/playermod permission <add|remove|check|list> [arguments...] [--help]"
    )
    public void playerPermissions(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod permission");
    }

    @Command(
            aliases = {"add"},
            parent = "playermod.permission",
            helpLookup = "playermod permission add",
            description = "playermod permission add info",
            permissions = {PLAYERMOD$PERMISSIONS$ADD},
            toolTip = "/playermod permission add <player> <path>:[value]",
            min = 2,
            version = 2
    )
    public void playerPermissionsAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_add").execute(caller, parameters);
    }

    @Command(
            aliases = {"remove"},
            parent = "playermod.permission",
            helpLookup = "playermod permission remove",
            description = "playermod permission remove info",
            permissions = {PLAYERMOD$PERMISSIONS$REMOVE},
            toolTip = "/playermod permission remove <player> <path>",
            min = 2,
            version = 2
    )
    public void playerPermissionsRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_remove").execute(caller, parameters);
    }

    @TabComplete(commands = {"playermod permission remove"})
    public List<String> playermodPermissionRemove(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToKnownPlayer(parameters)
                : parameters.length == 2 ? matchToPlayerPermission(Canary.getServer().matchKnownPlayer(parameters[1]), parameters)
                : null;
    }

    @Command(
            aliases = {"check"},
            parent = "playermod.permission",
            helpLookup = "playermod permission check",
            description = "playermod permission check info",
            permissions = {PLAYERMOD$PERMISSIONS$CHECK},
            toolTip = "/playermod permission check <player> <path>",
            min = 2,
            version = 2
    )
    public void playerPermissionsCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_check").execute(caller, parameters);
    }

    @Command(
            aliases = {"list"},
            parent = "playermod.permission",
            helpLookup = "playermod permission list",
            description = "playermod permission list info",
            permissions = {PLAYERMOD$PERMISSIONS$LIST},
            toolTip = "/playermod permission list <player>",
            version = 2
    )
    public void playerPermissionsList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_perms_list").execute(caller, parameters);
    }

    @Command(
            aliases = {"prefix", "color"},
            parent = "playermod",
            helpLookup = "playermod prefix",
            description = "playermod prefix info",
            permissions = {PLAYERMOD$PREFIX},
            toolTip = "/playermod prefix <name> <prefix>",
            version = 2
    )
    public void playerPrefix(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_prefix").execute(caller, parameters);
    }

    @Command(
            aliases = {"remove", "delete"},
            parent = "playermod",
            helpLookup = "playermod remove",
            description = "playermod remove info",
            permissions = {PLAYERMOD$REMOVE},
            toolTip = "/playermod remove <name>",
            version = 2
    )
    public void playerRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_remove").execute(caller, parameters);
    }

    @Command(
            aliases = {"group"},
            parent = "playermod",
            helpLookup = "playermod group",
            description = "playermod group info",
            permissions = {PLAYERMOD$GROUP},
            toolTip = "/playermod group <list|check|set|add> [arguments...] [--help]"
    )
    public void playerGroup(MessageReceiver caller, String[] parameters) {
        Canary.help().getHelp(caller, "playermod group");
    }

    @TabComplete(commands = {"playermod permission remove"})
    public List<String> playerGroupTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"list", "check", "set", "add"}) : null;
    }

    @Command(
            aliases = {"set"},
            parent = "playermod.group",
            helpLookup = "playermod group set",
            description = "playermod group set info",
            permissions = {PLAYERMOD$GROUP$SET},
            toolTip = "/playermod group set <player> <group> [--help]",
            version = 2
    )
    public void playerGroupSet(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_set").execute(caller, parameters);
    }

    @Command(
            aliases = {"add"},
            parent = "playermod.group",
            helpLookup = "playermod group add",
            description = "playermod group add info",
            permissions = {PLAYERMOD$GROUP$ADD},
            toolTip = "/playermod group add <player> <group> [--help]",
            version = 2
    )
    public void playerGroupAdd(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_add").execute(caller, parameters);
    }

    @Command(
            aliases = {"list"},
            parent = "playermod.group",
            helpLookup = "playermod group list",
            description = "playermod group list info",
            permissions = {PLAYERMOD$GROUP$LIST},
            toolTip = "/playermod group list <player> [--help]",
            version = 2
    )
    public void playerGroupList(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_list").execute(caller, parameters);
    }

    @Command(
            aliases = {"check"},
            parent = "playermod.group",
            helpLookup = "playermod group check",
            description = "playermod group check info",
            permissions = {PLAYERMOD$GROUP$CHECK},
            toolTip = "/playermod group check <player> <group> [--help]",
            min = 2,
            version = 2
    )
    public void playerGroupCheck(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_check").execute(caller, parameters);
    }

    @Command(
            aliases = {"remove"},
            parent = "playermod.group",
            helpLookup = "playermod group remove",
            description = "playermod group remove info",
            permissions = {PLAYERMOD$GROUP$REMOVE},
            toolTip = "/playermod group remove <player> <group> [--help]",
            min = 2,
            version = 2
    )
    public void playerGroupRemove(MessageReceiver caller, String[] parameters) {
        natives.get("playermod_group_remove").execute(caller, parameters);
    }

    @TabComplete(commands = {"playermod group add", "playermod group remove", "playermod group set"})
    public List<String> playermodGroupAddRemoveSet(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToKnownPlayer(parameters)
                : parameters.length == 2 ? matchToGroup(parameters)
                : null;
    }
    /* playermod end */

    /* system */
    @Command(
            aliases = {"ban"},
            description = "ban info",
            permissions = {BAN},
            toolTip = "/ban <player> [reason] [#number hour|day|week|month]",
            min = 1,
            version = 2
    )
    public void banCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ban").execute(caller, parameters);
    }

    @TabComplete(commands = {"ban"})
    public List<String> banTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToKnownPlayer(parameters)
                : parameters.length >= 2 && parameters[parameters.length - 2].matches("\\d+") ? matchTo(parameters, new String[]{"hour", "day", "week", "month"})
                : null;
    }

    @Command(
            aliases = {"canarymod", "version"},
            description = "CanaryMod Information",
            permissions = {CANARYMOD},
            toolTip = "/canarymod"
    )
    public void canarymodInfoCommand(MessageReceiver caller, String[] parameters) {
        natives.get("canarymod").execute(caller, parameters);
    }

    @Command(
            aliases = {"createvanilla", "makevanilla"},
            description = "makevanilla info",
            permissions = {CREATEVANILLA},
            toolTip = "/createvanilla <defaultworld>",
            version = 2
    )
    public void createVanillaCommand(MessageReceiver caller, String[] parameters) {
        natives.get("createvanilla").execute(caller, parameters);
    }

    @Command(
            aliases = {"deop"},
            description = "Takes Op from a Player",
            permissions = {"canary.command.deop"}, // Really no point, Requires Op
            toolTip = "/deop <player>",
            version = 2
    )
    public void deop(MessageReceiver caller, String[] args) {
        natives.get("deop").execute(caller, args);
    }

    @TabComplete(commands = {"deop"})
    public List<String> deopTabComplete(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchTo(args, Canary.ops().getOps()) : null;
    }

    @Command(
            aliases = {"help"},
            description = "help info",
            permissions = {HELP},
            toolTip = "/help [search terms] [page]"
    )
    public void helpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("help").execute(caller, parameters);
    }

    @Command(
            aliases = {"ipban"},
            description = "ipban info",
            permissions = {IPBAN},
            toolTip = "/ipban <player> [reason] [#number hour|day|week|month]",
            min = 2
    )
    public void ipBanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("ipban").execute(caller, parameters);
    }

    @Command(
            aliases = {"kick"},
            description = "kick info",
            permissions = {KICK},
            toolTip = "/kick <playername> [reason]",
            min = 1,
            version = 2
    )
    public void kickCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kick").execute(caller, parameters);
    }

    @Command(
            aliases = {"kit"},
            description = "kit info",
            permissions = {KIT},
            toolTip = "/kit <give|create|list> <name> <use delay> [G|P Groups|Players]",
            min = 2
    )
    public void kitCommand(MessageReceiver caller, String[] parameters) {
        natives.get("kit").execute(caller, parameters);
    }

    @TabComplete(commands = {"kit"})
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

    @Command(
            aliases = {"listplugins", "plugins"},
            description = "lplugin info",
            permissions = {PLUGIN$LIST},
            toolTip = "/listplugins"
    )
    public void listPluginsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listplugins").execute(caller, parameters);
    }

    @Command(
            aliases = {"motd"},
            description = "motd info",
            permissions = {MOTD},
            toolTip = "/motd"
    )
    public void motdCommand(MessageReceiver caller, String[] parameters) {
        natives.get("motd").execute(caller, parameters);
    }

    @Command(
            aliases = {"mute", "stfu"},
            description = "mute info",
            permissions = {MUTE},
            toolTip = "/mute <playername>",
            version = 2
    )
    public void muteCommand(MessageReceiver caller, String[] parameters) {
        natives.get("mute").execute(caller, parameters);
    }

    @Command(
            aliases = {"op"},
            description = "Give Op to a Player",
            permissions = {"canary.command.op"}, // Really no point, Requires Op
            toolTip = "/op <player>",
            version = 2
    )
    public void op(MessageReceiver caller, String[] args) {
        natives.get("op").execute(caller, args);
    }

    @Command(
            aliases = {"oplist"},
            description = "Displays a list of Operators",
            permissions = {"canary.command.oplist"}, // Really no point, Requires Op
            toolTip = "/oplist",
            min = 0,
            version = 2
    )
    public void oplist(MessageReceiver caller, String[] args) {
        natives.get("oplist").execute(caller, args);
    }

    @Command(aliases = {"playerinfo", "pinfo"},
            description = "Player Information",
            permissions = {PLAYER$INFO},
            toolTip = "/playerinfo [player]"
    )
    public void playerinfo(MessageReceiver caller, String[] parameters) {
        natives.get("playerinfo").execute(caller, parameters);
    }

    @Command(
            aliases = {"playerlist", "players", "who"},
            description = "who info",
            permissions = {PLAYER$LIST},
            toolTip = "/who"
    )
    public void playerListCommand(MessageReceiver caller, String[] parameters) {
        natives.get("playerlist").execute(caller, parameters);
    }

    @Command(
            aliases = {"enableplugin"},
            description = "plugin enable info",
            permissions = {PLUGIN$ENABLE},
            toolTip = "/enableplugin <plugin>",
            version = 2
    )
    public void enablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("enableplugin").execute(caller, parameters);
    }

    @Command(
            aliases = {"disableplugin"},
            description = "plugin disable info",
            permissions = {PLUGIN$DISABLE},
            toolTip = "/disableplugin <plugin>",
            version = 2
    )
    public void disablePluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("disableplugin").execute(caller, parameters);
    }

    @Command(
            aliases = {"reloadplugin"},
            description = "plugin reload info",
            permissions = {PLUGIN$RELOAD},
            toolTip = "/reloadplugin <plugin>",
            version = 2
    )
    public void reloadPluginCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reloadplugin").execute(caller, parameters);
    }

    @TabComplete(commands = {"disableplugin", "reloadplugin"})
    public List<String> matchPluginName(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchTo(args, Canary.manager().getPluginNames().toArray(new String[0])) : null;
    }

    @Command(
            aliases = {"reload"},
            description = "reload info",
            permissions = {RELOAD},
            toolTip = "/reload")
    public void reloadCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reload").execute(caller, parameters);
    }

    @Command(
            aliases = {"reservelist", "rlist", "rl"},
            description = "reservelist info",
            permissions = {RESERVELIST},
            toolTip = "/reservelist <add|remove> <playername>",
            min = 2,
            version = 2
    )
    public void reservelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("reservelist").execute(caller, parameters);
    }

    @TabComplete(commands = {"reservelist"})
    public List<String> reservelistTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove"})
                : parameters.length == 2 ?
                parameters[0].equals("remove") ? matchTo(parameters, Canary.reservelist().getReservations())
                        : parameters[0].equals("add") ? matchToKnownPlayer(parameters)
                        : null
                : null;
    }

    @Command(
            aliases = {"stop", "shutdown"},
            description = "stop info",
            permissions = {STOP},
            toolTip = "/stop"
    )
    public void stopCommand(MessageReceiver caller, String[] parameters) {
        natives.get("stop").execute(caller, parameters);
    }

    @Command(
            aliases = {"sysinfo"},
            description = "System Information",
            permissions = {SYSINFO},
            toolTip = "/sysinfo"
    )
    public void sysinfo(MessageReceiver caller, String[] parameters) {
        natives.get("sysinfo").execute(caller, parameters);
    }

    @Command(
            aliases = {"unban"},
            description = "unban info",
            permissions = {UNBAN},
            toolTip = "/unban <player>",
            min = 1,
            version = 2
    )
    public void unbanCommand(MessageReceiver caller, String[] parameters) {
        natives.get("unban").execute(caller, parameters);
    }

    @TabComplete(commands = {"unban"})
    public List<String> unbanTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchToBannedSubject(parameters) : null;
    }

    @Command(
            aliases = {"uptime"},
            description = "server uptime",
            permissions = {UPTIME},
            toolTip = "/uptime"
    )
    public void uptime(MessageReceiver caller, String[] parameters) {
        natives.get("uptime").execute(caller, parameters);
    }

    @Command(aliases = {"whitelist", "wlist", "wl"},
            description = "whitelist info",
            permissions = {WHITELIST},
            toolTip = "/whitelist <add|remove> <playername>",
            min = 3
    )
    public void whitelistCommand(MessageReceiver caller, String[] parameters) {
        natives.get("whitelist").execute(caller, parameters);
    }

    @TabComplete(commands = {"whitelist"})
    public List<String> whitelistTabComplete(MessageReceiver caller, String[] parameters) {
        return parameters.length == 1 ? matchTo(parameters, new String[]{"add", "remove"}) : null;
    }

    /* vanilla start */
    @Command(
            aliases = {"achievement"},
            description = "Gives a player an achievement or increases a statistic.",
            permissions = {ACHIEVEMENT},
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
            permissions = {BROADCAST},
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
            permissions = {CLEAR},
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
            permissions = {DEBUG},
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
            permissions = {DEFAULTGAMEMODE},
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
            permissions = {SETWORLDSPAWN},
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
            permissions = {DIFFICULTY},
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
            permissions = {EFFECT},
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
            permissions = {EMOTE},
            toolTip = "/emote <action...>",
            version = 2
    )
    public void emote(MessageReceiver caller, String[] args) {
        natives.get("emote").execute(caller, args);
    }

    @Command(
            aliases = {"enchant"},
            description = "Enchants target's held item",
            permissions = {ENCHANT},
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
            permissions = {GAMEMODE},
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
            permissions = {GAMERULE},
            toolTip = "/gamerule <rule name> [value]",
            version = 2
    )
    public void gamerule(MessageReceiver caller, String[] args) {
        natives.get("gamerule").execute(caller, args);
    }

    @Command(
            aliases = {"give", "item", "i"},
            description = "Gives an item to a player.",
            permissions = {GIVE},
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
            permissions = {MESSAGE},
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
            permissions = {MESSAGERAW},
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
            permissions = {PLAYSOUND},
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
            permissions = {SAVE$ALL},
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
            permissions = {SAVE$OFF},
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
            permissions = {SAVE$ON},
            toolTip = "/save-on",
            min = 0,
            version = 2
    )
    public void saveon(MessageReceiver caller, String[] args) {
        natives.get("save-on").execute(caller, args);
    }

    @Command(
            aliases = {"scoreboard"},
            description = "SCORE(BOARD)!",
            permissions = {SCOREBOARD},
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
            permissions = {SETBLOCK},
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
            permissions = {SPAWNPOINT},
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
            permissions = {SPREADPLAYERS},
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
            permissions = {SUMMON},
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
            permissions = {TELEPORT, TELEPORT$OTHER},
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
            permissions = {TESTFOR},
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
            permissions = {TESTFORBLOCK},
            toolTip = "/testforblock <x> <y> <z> <TileName> [dataValue] [dataTag]",
            min = 4,
            version = 2
    )
    public void testforblock(MessageReceiver caller, String[] args) {
        natives.get("testforblock").execute(caller, args);
    }

    @Command(
            aliases = {"time"},
            description = "Changes or queries the world's game time.",
            permissions = {TIME},
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
            permissions = {TOGGLEDOWNFALL},
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
            permissions = {WEATHER},
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
            permissions = {XP},
            toolTip = "/xp <amount[L]> [player]",
            min = 1,
            version = 2
    )
    public void xp(MessageReceiver caller, String[] args) {
        natives.get("xp").execute(caller, args);
    }
    /* vanilla end */

    @Command(
            aliases = {"home"},
            description = "home info",
            permissions = {HOME},
            toolTip = "/home [playername]",
            min = 0,
            max = 1,
            version = 2
    )
    public void homeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("home").execute(caller, parameters);
    }

    @Command(
            aliases = {"sethome"},
            description = "sethome info",
            permissions = {HOME$SET},
            toolTip = "/sethome [player]",
            min = 1,
            max = 2
    )
    public void setHomeCommand(MessageReceiver caller, String[] parameters) {
        natives.get("sethome").execute(caller, parameters);
    }

    @Command(aliases = {"spawn"},
            description = "spawn info",
            permissions = {SPAWN},
            toolTip = "/spawn [worldname] [player]",
            min = 1,
            max = 3
    )
    public void spawnCommand(MessageReceiver caller, String[] parameters) {
        natives.get("spawn").execute(caller, parameters);
    }

    @TabComplete(commands = {"spawn"})
    public List<String> matchWorldNamePlayerName(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownWorld(args)
                : args.length == 2 ? matchToOnlinePlayer(args)
                : null;
    }

    @Command(
            aliases = {"listwarps", "warps"},
            description = "lwarps info",
            permissions = {WARP$LIST},
            toolTip = "/listwarps"
    )
    public void listWarpsCommand(MessageReceiver caller, String[] parameters) {
        natives.get("listwarps").execute(caller, parameters);
    }

    @Command(
            aliases = {"delwarp", "removewarp"},
            description = "delwarp info",
            permissions = {WARP$REMOVE},
            toolTip = "/delwarp <name>",
            version = 2
    )
    public void delWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("delwarp").execute(caller, parameters);
    }

    @Command(
            aliases = {"setwarp"},
            description = "setwarp info",
            permissions = {WARP$SET},
            toolTip = "/setwarp <name> [G <group>|P <player>]",
            version = 2
    )
    public void setWarpCommand(MessageReceiver caller, String[] parameters) {
        natives.get("setwarp").execute(caller, parameters);
    }

    @Command(
            aliases = {"warp"},
            description = "warp info",
            permissions = {WARP$USE},
            toolTip = "/warp <name>",
            version = 2
    )
    public void warpUse(MessageReceiver caller, String[] parameters) {
        natives.get("warp").execute(caller, parameters);
    }

    @TabComplete(commands = {"warp", "delwarp"})
    public List<String> matchWarpNames(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToWarpNames(args, caller) : null;
    }

    @Command(
            aliases = {"createworld"},
            description = "creates a world",
            permissions = {WORLD$CREATE},
            toolTip = "/createworld <name> [seed] [dimensionType] [worldType]",
            max = 5,
            version = 2
    )
    public void createWorld(MessageReceiver caller, String[] args) {
        natives.get("createworld").execute(caller, args);
    }

    @TabComplete(commands = {"createworld"})
    public List<String> createWorldTabComplete(MessageReceiver caller, String[] args) {
        switch (args.length) {
            case 3:
                return matchToDimension(args);
            case 4:
                return matchToWorldType(args);
            default:
                return null;
        }
    }

    @Command(
            aliases = {"deleteworld"},
            description = "Deletes a world",
            permissions = {WORLD$DELETE},
            toolTip = "/deleteworld <world_fqName>",
            min = 1,
            version = 2
    )
    public void deleteworld(MessageReceiver caller, String[] args) {
        natives.get("deleteworld").execute(caller, args);
    }

    @TabComplete(commands = {"deleteworld"})
    public List<String> deleteworldTabComplete(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownWorld(args) : null;
    }

    @Command(
            aliases = {"loadworld"},
            description = "loads a world",
            permissions = {WORLD$LOAD},
            toolTip = "/loadworld <worldName> [dimensionType]",
            version = 2
    )
    public void loadWorld(MessageReceiver caller, String[] args) {
        natives.get("loadworld").execute(caller, args);
    }

    @TabComplete(commands = {"loadworld"})
    public List<String> matchWorldNameDimension(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownWorld(args)
                : args.length == 2 ? matchToDimension(args)
                : null;
    }

    @Command(
            aliases = {"mob"},
            description = "Gets a Mob count or destroys mobs within a radius.",
            permissions = {MOB},
            toolTip = "/mob count [world [dimension]] or /mob remove <h|p|t|u|a> [radius] [world [dimension]]",
            min = 1,
            version = 2
    )
    public void mob(MessageReceiver caller, String[] args) {
        Canary.help().getHelp(caller, "mob");
    }

    @Command(
            aliases = {"clear"},
            parent = "mob",
            description = "Destroys mobs within a radius.",
            permissions = {MOB$CLEAR},
            toolTip = "/mob clear <h|p|t|u|a> [radius] [world <dimension>]  NOTE: (h = Hostiles p = Passives t = Tamed u = Utility (Like Items) and a = all)",
            helpLookup = "mob clear",
            min = 1,
            version = 2
    )
    public void mobclear(MessageReceiver caller, String[] args) {
        natives.get("mobclear").execute(caller, args);
    }

    @Command(
            aliases = {"count"},
            parent = "mob",
            description = "Gets a Mob count.",
            permissions = {MOB$COUNT},
            toolTip = "/mob count [world]",
            min = 0,
            version = 2
    )
    public void mobcount(MessageReceiver caller, String[] args) {
        natives.get("mobcount").execute(caller, args);
    }

    @TabComplete(commands = {"mob"})
    public List<String> mobTabComplete(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchTo(args, new String[]{"clear", "count"})
                : args.length == 2 ?
                args[0].equals("clear") ? matchTo(args, new String[]{"h", "ht", "hp", "hu", "htp", "htu", "htpu", "t", "tp", "tu", "tpu", "p", "pu", "u", "a"})
                        : args[0].equals("count") ? matchToLoadedWorld(args)
                        : null
                : null;
    }

    @TabComplete(commands = {"playerinfo", "home", "op", "playermod permission add",
            "playermod permission check", "playermod prefix", "playermod remove",
            "playermod group list"
    })
    public List<String> matchKnownPlayer(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToKnownPlayer(args) : null;
    }

    @TabComplete(commands = {"kick", "kill", "mute", "god"})
    public List<String> matchOnlinePlayer(MessageReceiver caller, String[] args) {
        return args.length == 1 ? matchToOnlinePlayer(args) : null;
    }

}
