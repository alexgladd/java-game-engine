/* FpsManager.java
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

import com.gladdware.game.log.Log;

/**
 * Keep track of FPS for the game engine
 */
public class FpsManager {
    
    private static final String TAG = "FpsManager";
    
    private static long lastFpsTimeNs;
    private static float lastFps;
    private static float averageFps;
    private static int frameCount;
    
    public static void init(int targetFps) {
        lastFps = (float)targetFps;
        averageFps = (float)targetFps;
        frameCount = 0;
        lastFpsTimeNs = Time.getTimeNs();
    }
    
    public static void finishedFrame() {
        frameCount++;
        
        long curTimeNs = Time.getTimeNs();
        
        // update stats every half-second
        if((curTimeNs - lastFpsTimeNs) >= Time.HALF_SECOND_NS) {
            Log.t(TAG, "Recalculating FPS values");
            
            lastFpsTimeNs = curTimeNs;
            
            lastFps = frameCount * 2.0f;
            averageFps = (averageFps + lastFps) / 2.0f;
            
            frameCount = 0;
        }
    }
    
    public static float getFps() {
        return lastFps;
    }
    
    public static float getAverageFps() {
        return averageFps;
    }

    /**
     * Constructor - Never instantiate
     */
    private FpsManager() {}

}
