/* GameCoreTest.java
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

package com.gladdware.game.tst;

import com.gladdware.game.core.EngineException;
import com.gladdware.game.core.EngineCore;
import com.gladdware.game.core.Game;
import com.gladdware.game.log.Log;
import com.gladdware.game.log.LogLevel;
import com.gladdware.game.util.FpsManager;
import com.gladdware.game.util.Time;

/**
 * Simple concrete test skeleton for GameCore
 */
public class GameTest extends Game {
    
    private static final String TAG = "GameTest";
    
    private long lastFpsDisplayNs;

    /**
     * Constructor
     */
    public GameTest() {
        super();
        
        this.lastFpsDisplayNs = Time.getTimeNs();
    }

    /* (non-Javadoc)
     * @see com.gladdware.game.core.GameCore#onInit()
     */
    @Override
    protected boolean onInit() {
        Log.d(TAG, "onInit");
        return true;
    }

    /* (non-Javadoc)
     * @see com.gladdware.game.core.GameCore#onCleanup()
     */
    @Override
    protected void onCleanup() {
        Log.d(TAG, "onCleanup");
    }

    /* (non-Javadoc)
     * @see com.gladdware.game.core.GameCore#onUpdate(long)
     */
    @Override
    protected void onUpdate(float timeDelta) {
        // TODO Auto-generated method stub

    }

    /* (non-Javadoc)
     * @see com.gladdware.game.core.GameCore#onRender()
     */
    @Override
    protected void onRender() {
        // TODO Auto-generated method stub
        
        // show fps once per second
        long nowNs = Time.getTimeNs();
        if((nowNs - this.lastFpsDisplayNs) >= Time.ONE_SECOND_NS) {
            Log.d(TAG, "FPS: " + FpsManager.getFps() + " (average: " +
                    FpsManager.getAverageFps() + ")");
            
            this.lastFpsDisplayNs = nowNs;
        }
    }
    
    public static void main(String[] args) {
        Log.setLevel(LogLevel.DEBUG);
        String tag = "GameCoreTestDriver";
        
        Log.i(tag, "Starting the Gladdware Game Engine test driver");
        
        Game game = new GameTest();
        EngineCore engine = new EngineCore(game);
        
        try {
            engine.run();
        } catch(EngineException e) {
            Log.f(tag, "Fatal engine error: " + e.getMessage(), e);
        }
        
        Log.i(tag, "Done");
    }

}