/* EngineContext.java
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
 * Container for context data for the game engine
 */
public class EngineContext {
    
    /** The width of the display window */
    public int screenWidth;
    /** The height of the display window */
    public int screenHeight;
    /** The desired framerate for the game */
    public int targetFramerate;
    /** The title of the display window */
    public String screenTitle;
    
    /**
     * Constructor
     *
     * @param width display width
     * @param height display height
     * @param framerate target framerate
     * @param title display window title
     */
    public EngineContext(int width, int height, int framerate, String title) {
        this.screenWidth = width;
        this.screenHeight = height;
        this.targetFramerate = framerate;
        this.screenTitle = title;
    }
}
