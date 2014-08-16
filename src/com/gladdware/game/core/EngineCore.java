/* EngineCore.java
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

import org.lwjgl.opengl.Display;

import com.gladdware.game.log.Log;
import com.gladdware.game.util.FpsManager;
import com.gladdware.game.util.Time;

/**
 * Core game engine implementation
 * 
 * Responsible for the main game loop
 */
public class EngineCore {
    
    private static final String TAG = "EngineCore";
    
    private static final int DEFAULT_FRAMERATE = 60;
    
    private Game game;
    private EngineContext engineCtx;

    public EngineCore(Game game, EngineContext ctx) {
        this.game = game;
        this.engineCtx = ctx;
    }
    
    public EngineCore(Game game) {
        this(game, new EngineContext(Window.DEFAULT_WIDTH,
                Window.DEFAULT_HEIGHT, DEFAULT_FRAMERATE, Window.DEFAULT_TITLE));
    }
    
    public void run() throws EngineException {
        // initialize
        if(!init()) {
            Log.f(TAG, "Engine initialization failure");
            throw new EngineException("Engine initialization failure");
        } else if(!game.onInit()) {
            Log.f(TAG, "Game initialization failure");
            throw new EngineException("Game initialization failure");
        }
        // else start main loop
        
        Log.d(TAG, "Engine starting");
        
        // init the timer
        Time.startFrame();
        // init fps counter
        FpsManager.init(engineCtx.targetFramerate);
        
        // main loop
        while(!game.shutdownRequested()) {
            // start the frame
            Time.startFrame();
            
            // update the game state
            game.onUpdate(Time.getDeltaMs());
            
            // render the frame
            game.onRender();
            Window.render();
            
            // TODO update fps counter
            
            // XXX temp
            if(Window.isCloseRequested()) {
                game.requestShutdown();
            }
            
            FpsManager.finishedFrame();
            
            // sync to the target framerate
            Display.sync(engineCtx.targetFramerate);
        }
        
        Log.d(TAG, "Engine stopping");
        
        // cleanup
        game.onCleanup();
        cleanup();
    }
    
    private boolean init() {
        // de-conflict
        if(Display.isCreated()) {
            Log.w(TAG, "Trying to re-initialize engine core");
            return false;
        }
        
        Log.d(TAG, "Engine initialize");
        
        // setup the window
        try {
            Window.create(engineCtx.screenWidth, engineCtx.screenHeight,
                    engineCtx.screenTitle);
            
         // TODO init input
        } catch(EngineException e) {
            Log.e(TAG, "Failed to initialize engine: " + e.getMessage(), e);
            return false;
        }
        
        return true;
    }
    
    private void cleanup() {
        Log.d(TAG, "Engine cleanup");
        
        // TODO de-init input
        
        // teardown window
        Window.dispose();
    }
}
