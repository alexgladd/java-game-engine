/* Time.java
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

package com.gladdware.game.util;

/**
 * Maintain timing for the game engine
 */
public class Time {
    
    public static final long ONE_SECOND_NS = 1000000000L;
    public static final long HALF_SECOND_NS = 500000000L;
    
    private static long lastFrameStartNs = 0L;
    private static float currentDeltaMs = 0.0f;
    
    /**
     * Call at the start of a game update loop in order automatically calculate
     * a time delta.
     *
     * @return the current time delta in milliseconds
     */
    public static float startFrame() {
        // get current time
        long startTimeNs = getTimeNs();
        
        // calculate time delta in milliseconds
        long deltaNs = startTimeNs - lastFrameStartNs;
        // maintain double-precision for the calculation
        double deltaMs = deltaNs / 1000000.0d;
        
        // save the results
        currentDeltaMs = (float)deltaMs;
        lastFrameStartNs = startTimeNs;
        
        return currentDeltaMs;
    }
    
    /**
     * Get the current time delta in milliseconds
     *
     * @return the current time delta in milliseconds
     */
    public static float getDeltaMs() {
        return currentDeltaMs;
    }
    
    /**
     * Get the start time of the current frame in nanoseconds
     *
     * @return the start time of the current frame in nanoseconds
     */
    public static long getFrameStartTimeNs() {
        return lastFrameStartNs;
    }
    
    /**
     * Get the current game time in nanoseconds. This value starts at zero when
     * the game JVM is created.
     *
     * @return the current game time in nanoseconds
     */
    public static long getTimeNs() {
        /*
         * XXX Chose to use Java's nanoTime() here instead of the LWJGL getTime()
         * in order to avoid having to do math for this call on every frame.
         * 
         * Note that this value *can* roll over, but only after about 292 year.
         * If you're playing a game continuously for that long then you deserve
         * to have it crash on you!
         */
        return System.nanoTime();
    }
    
    /**
     * Get the current game time in milliseconds. This value starts at zero when
     * the game JVM is create.
     * 
     * Note that this method takes more CPU time to execute than getTimeNs() due
     * to the need to divide down to milliseconds.
     *
     * @return the current game time in milliseconds
     */
    public static long getTimeMs() {
        long  tmNs = getTimeNs();
        
        // maintain double-precision for the calculation
        double tmMs = tmNs / 1000000.0d;
        
        // round to closest integer value
        return Math.round(tmMs);
    }

    /**
     * Constructor - Never instantiate
     */
    private Time() {}

}
