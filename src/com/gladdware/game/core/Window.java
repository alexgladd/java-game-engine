/* Window.java
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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.gladdware.game.log.Log;

/**
 * Abstraction for a display window
 */
public class Window {
    
    public static final int DEFAULT_WIDTH = 800;
    public static final int DEFAULT_HEIGHT = 600;
    public static final String DEFAULT_TITLE = "Gladdware Game Engine";
    
    private static final String TAG = "Window";

    /**
     * Create a display window with the given parameters
     *
     * @param width display width
     * @param height display height
     * @param title window title
     * @throws EngineException on failure to initialize or create a display
     */
    public static void create(int width, int height, String title)
            throws EngineException {
        // invoke lwjgl to setup the display
        Display.setTitle(title);
        
        try {
            Display.setDisplayMode(new DisplayMode(width, height));
            Log.d(TAG, "Successfully set display mode");
            
            Display.create();
            Log.d(TAG, "Successfully created display");
        } catch (LWJGLException e) {
            throw new EngineException("Failed to setup display", e);
        }
    }
    
    /**
     * Create a display window with a default title
     *
     * @param width display width
     * @param height display height
     * @throws EngineException on failure to initialize or create a display
     */
    public static void create(int width, int height) throws EngineException {
        create(width, height, DEFAULT_TITLE);
    }
    
    /**
     * Destroy the display window
     */
    public static void dispose() {
        Log.d(TAG, "Destroying display");
        Display.destroy();
    }
    
    /**
     * Update the display window (effectively swaps the OpenGL buffers)
     */
    public static void render() {
        Display.update();
    }
    
    /**
     * Check whether the close button on the display window was clicked
     *
     * @return true if window close is requested
     */
    public static boolean isCloseRequested() {
        return Display.isCloseRequested();
    }
    
    /**
     * Get the width of the display window
     *
     * @return the width of the display window
     */
    public static int getWidth() {
        return Display.getDisplayMode().getWidth();
    }
    
    /**
     * Get the height of the display window
     *
     * @return the height of the display window
     */
    public static int getHeight() {
        return Display.getDisplayMode().getHeight();
    }
    
    /**
     * Constructor - Never instantiate
     */
    private Window() {}
}
