/* Game.java
 *
 * This file is part of the Gladdware Game Engine (GGE)
 * Copyright (C) 2014 Alex Gladd
 *
 * The GGE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The GGE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with The GGE.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.gladdware.game.core;

/**
 * Base abstract game implementation
 */
public abstract class Game {
    
    private Boolean stopRequested;

    /**
     * Constructor
     */
    public Game() {
        this.stopRequested = false;
    }
    
    public boolean shutdownRequested() {
        boolean result;
        
        // thread-safe access of the flag
        synchronized(this.stopRequested) {
            result = this.stopRequested.booleanValue();
        }
        
        return result;
    }
    
    protected void requestShutdown() {
        // thread-safe modification of the flag
        synchronized(this.stopRequested) {
            this.stopRequested = Boolean.TRUE;
        }
    }
    
    protected abstract boolean onInit();
    
    protected abstract void onCleanup();
    
    protected abstract void onUpdate(float deltaMs);
    
    protected abstract void onRender();

}
