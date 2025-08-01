package com.palmergames.bukkit.towny.event;

import com.palmergames.bukkit.towny.TownyUniverse;
import com.palmergames.bukkit.towny.object.Resident;
import com.palmergames.bukkit.towny.object.Town;
import com.palmergames.bukkit.towny.object.TownBlock;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

/*
 * This event runs Async. Be aware of such.
 */
public class TownClaimEvent extends Event  {

    private static final HandlerList handlers = new HandlerList();
    
    private final TownBlock townBlock;
    private Resident resident;
	private boolean isOverClaim;

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }
    
    public static HandlerList getHandlerList() {
		return handlers;
	}

    public TownClaimEvent(TownBlock townBlock, Player player, boolean isOverClaim) {
    	super(!Bukkit.getServer().isPrimaryThread());
        this.townBlock = townBlock;
        this.resident = TownyUniverse.getInstance().getResident(player.getUniqueId());
		this.isOverClaim = isOverClaim;
	}

	/**
	 * Whether the claim has resulted from takeoverclaim.
	 * @return true if the claim resulted from /t takeoverclaim.
	 */
	public boolean isOverClaim() {
		return isOverClaim;
	}

	/**
     *
     * @return the new TownBlock.
     */
    public TownBlock getTownBlock() {
        return townBlock;
    }

	/**
	 * Gets the resident claiming the townblock.
	 * 
	 * @return The resident claiming.
	 */
	public Resident getResident() {
		return resident;
	}

	/**
	 * @return the Town which claimed this TownBlock.
	 */
       public Town getTown() {
               return resident.getPrimaryTown();
       }
}