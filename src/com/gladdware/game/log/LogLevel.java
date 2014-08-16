/* LogLevel.java
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

package com.gladdware.game.log;

/**
 * Logging levels enumeration
 */
public enum LogLevel {
    TRACE(0, "TRACE"),
    DEBUG(1, "DEBUG"),
    INFO(2, "INFO"),
    WARN(3, "WARNING"),
    ERROR(4, "ERROR"),
    FATAL(5, "FATAL");
    
    private final int level;
    private final String name;
    
    /**
     * Constructor
     *
     * @param level numeric log level
     * @param name human-readable log level
     */
    LogLevel(int level, String name) {
        this.level = level;
        this.name = name;
    }
    
    /**
     * Get the level's numeric value (for fast comparisons)
     *
     * @return numeric level
     */
    public int level() {
        return this.level;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Enum#toString()
     */
    public String toString() {
        return this.name;
    }
}
