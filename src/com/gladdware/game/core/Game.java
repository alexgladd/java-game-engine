/* Game.java
 *
 * This file is part of the Gladdware Game Engine (GGE)
 * Copyright (C) 2014 Alex Gladd
 *
 * The GGE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The GGE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with The GGE.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gladdware.game.core;

/**
 * Base abstract game implementation
 */
public abstract class Game {
    
    /**
     * Shutdown flag
     */
    private Boolean stopRequested;

    /**
     * Constructor
     */
    public Game() {
        this.stopRequested = false;
    }
    
    /**
     * Get the game's shutdown flag
     *
     * @return true if the game should be shutdown
     */
    public boolean shutdownRequested() {
        boolean result;
        
        // thread-safe access of the flag
        synchronized(this.stopRequested) {
            result = this.stopRequested.booleanValue();
        }
        
        return result;
    }
    
    /**
     * Request that the game shutdown as soon as possible
     */
    protected void requestShutdown() {
        // thread-safe modification of the flag
        synchronized(this.stopRequested) {
            this.stopRequested = Boolean.TRUE;
        }
    }
    
    /**
     * Perform game initialization
     *
     * @return true on success
     */
    protected abstract boolean onInit();
    
    /**
     * Perform game cleanup
     */
    protected abstract void onCleanup();
    
    /**
     * Update the game state
     *
     * @param deltaMs elapsed time since the last update, in milliseconds
     */
    protected abstract void onUpdate(float deltaMs);
    
    /**
     * Render the game state
     */
    protected abstract void onRender();

}
