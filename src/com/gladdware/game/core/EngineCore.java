/* EngineCore.java
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

    /**
     * Constructor
     *
     * @param game Game implementation
     * @param ctx engine context
     */
    public EngineCore(Game game, EngineContext ctx) {
        this.game = game;
        this.engineCtx = ctx;
    }
    
    /**
     * Constructor
     *
     * @param game Game implementation
     */
    public EngineCore(Game game) {
        this(game, new EngineContext(Window.DEFAULT_WIDTH,
                Window.DEFAULT_HEIGHT, DEFAULT_FRAMERATE, Window.DEFAULT_TITLE));
    }
    
    /**
     * Main game loop
     * 
     * This is the primary control loop for the game. The loop runs one
     * iteration per frame rendered. The loop will call the
     * onInput/onUpdate/onRender methods of the game implementation at the
     * correct times within the loop.
     * 
     * The loop will run until the game implementation sets its shutdown flag
     * (as returned by Game.shutdownRequested()).
     *
     * @throws EngineException on an unrecoverable game engine exception
     */
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
            
            // update inputs
            Input.collectInput();
            
            // update the game state
            game.onUpdate(Time.getDeltaMs());
            
            // render the frame
            game.onRender();
            Window.render();
            
            // update the fps manager
            FpsManager.finishedFrame();
            
            // sync to the target framerate
            Display.sync(engineCtx.targetFramerate);
        }
        
        Log.d(TAG, "Engine stopping");
        
        // cleanup
        game.onCleanup();
        cleanup();
    }
    
    /**
     * Perform core engine initialization
     *
     * @return true on success
     */
    private boolean init() {
        // de-conflict
        if(Display.isCreated()) {
            Log.w(TAG, "Trying to re-initialize engine core");
            return false;
        }
        
        Log.d(TAG, "Engine initialize");
        
        try {
            // setup the window
            Window.create(engineCtx.screenWidth, engineCtx.screenHeight,
                    engineCtx.screenTitle);
            
            // setup input
            Input.init();
        } catch(EngineException e) {
            Log.e(TAG, "Failed to initialize engine: " + e.getMessage(), e);
            return false;
        }
        
        return true;
    }
    
    /**
     * Perform core engine cleanup
     */
    private void cleanup() {
        Log.d(TAG, "Engine cleanup");
        
        // teardown input
        Input.dispose();
        
        // teardown window
        Window.dispose();
    }
}
