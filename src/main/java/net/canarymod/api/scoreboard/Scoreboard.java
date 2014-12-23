package net.canarymod.api.scoreboard;

import java.util.List;
import net.canarymod.api.entity.living.humanoid.Player;
import net.canarymod.api.world.World;

/**
 * Scoreboard wrapper interface
 *
 * @author Aaron (somners)
 */
public interface Scoreboard {

    /**
     * Gets a list of Scoreoard Objectives associated with this scoreboard.
     *
     * @return List of ScoreObjective instances.
     */
    public List<ScoreObjective> getScoreObjectives();

    /**
     * Creates and Adds a new ScoreObjective to this Scoreboard.
     *
     * @param name
     *         Name of Objective to add.
     * @return The new {@link ScoreObjective} is returned for convenience. (or the existing one if it already existed)
     */
    public ScoreObjective addScoreObjective(String name);

    /**
     * Creates and Adds a scoreboard objective.
     *
     * @param name
     *         The protocol name of the objective to add.
     * @param criteria
     *         The custom ScoreObjective Criteria<br>
     *         This can be null to use a dummy non-read-only criteria.<br>
     *         <b>NOTE: </b> This is only needed if you wish to make it a read-only
     *         score, if this is not the case use {@link #addScoreObjective(String)}
     *
     * @see #addScoreObjective(String)
     * @return The new {@link ScoreObjective} is returned for convenience. (or the existing one if it already existed)
     */
    public ScoreObjective addScoreObjective(String name, ScoreObjectiveCriteria criteria);

    /**
     * Removes a ScoreObjective from this Scoreboard.
     *
     * @param objective
     *         The Objective to remove.
     */
    public void removeScoreObjective(ScoreObjective objective);

    /**
     * Removes a ScoreObjective from this Scoreboard.
     *
     * @param name
     *         The name of the Objective to remove.
     */
    public void removeScoreObjective(String name);

    /**
     * Get a scoreboard objective.
     *
     * @param name
     *         The protocol name of the objective to get.
     *
     * @return The objective or null if it does not exist.
     */
    public ScoreObjective getScoreObjective(String name);

    /**
     * Get a List of Team instances associated with this Scoreboard.
     *
     * @return the List of Teams.
     */
    public List<Team> getTeams();

    /**
     * Adds a new Team to this Scoreboard.
     *
     * @param team
     *         the {@link Team} to add
     */
    public void addTeam(Team team);

    /**
     * Removes a team from this scoreboard.
     *
     * @param team
     *         the team to remove.
     */
    public void removeTeam(Team team);

    /**
     * Removes a team from this scoreboard.
     *
     * @param name
     *         name of the team to remove.
     */
    public void removeTeam(String name);

    /**
     * Gets the score for the given player and given score objective.
     *
     * @param player
     *         name oft he player to get a score for.
     * @param scoreObjective
     *         the objective to get a score for.
     *
     * @return {@link Score}
     */
    public Score getScore(Player player, ScoreObjective scoreObjective);

    /**
     * Gets the score for the given player and given score objective.
     *
     * @param name
     *         name of the score to get
     * @param scoreObjective
     *         the objective to get a score for.
     *
     * @return {@link Score}
     */
    public Score getScore(String name, ScoreObjective scoreObjective);

    /**
     * Gets a list of scores for the given score objective.
     *
     * @param scoreObjective
     *         score objective to get scores for.
     *
     * @return list of {@link Score}
     */
    public List<Score> getScores(ScoreObjective scoreObjective);

    /**
     * Gets a list of all scores for all objectives.
     *
     * @return list of {@link Score}
     */
    public List<Score> getAllScores();

    /**
     * Sets the {@link ScoreObjective}'s position for all players on the server.
     *
     * @param type
     *         the position type.
     * @param objective
     *         the objective to set.
     */
    public void setScoreboardPosition(ScorePosition type, ScoreObjective objective);

    /**
     * Sets the {@link ScoreObjective}'s position for the given player.
     *
     * @param type
     *         the position type.
     * @param objective
     *         the objective to set.
     * @param player
     *         the player to set the scoreboard for.
     */
    public void setScoreboardPosition(ScorePosition type, ScoreObjective objective, Player player);

    /**
     * Sets the {@link ScoreObjective}'s position for all Players in the given 
     * {@link World}.
     *
     * @param type
     *         the position type.
     * @param objective
     *         the objective to set.
     * @param world
     *         the player to set the scoreboard for.
     */
    public void setScoreboardPosition(ScorePosition type, ScoreObjective objective, World world);

    /**
     * Clears the {@link Scoreboard}'s position for all players on the server.
     *
     * @param type
     *         the position type.
     */
    public void clearScoreboardPosition(ScorePosition type);

    /**
     * Clears the {@link Scoreboard}'s position for the given player.
     *
     * @param type
     *         the position type.
     * @param player
     *         the player to set the scoreboard for.
     */
    public void clearScoreboardPosition(ScorePosition type, Player player);
    
    /**
     * Gets the name associated with this scoreboard, used for save file.
     * For "world" bound scoreboards, it will be "scoreboard" always.  For 
     * Canary generated instances it will be user-defined.
     * 
     * @return the name used for saving.
     */
    public String getSaveName();
    
    /**
     * Removes all scores associated with this name
     *
     * @param name
     *         the score's name
     */
    public void removeScore(String name);
    
    /**
     * Removes the score associated with this name from the target objective
     *
     * @param name
     *         the score's name
     * @param objective
     *         the target objective
     */
    public void removeScore(String name, ScoreObjective objective);
    
    /**
     * Get a team by it's name
     *
     * @param name
     *         The name of the team to get.
     *
     * @return The team or null if it does not exist.
     */
    public Team getTeam(String name);
    
    /**
     * Create and add a new team to the scoreboard
     *
     * @param name
     *         The name of the team to create.
     *
     * @return The new {@link Team}
     * 
     * @throws IllegalArgumentException
     *         if the team already exists.
     */
    public Team addTeam(String name) throws IllegalArgumentException;
}
