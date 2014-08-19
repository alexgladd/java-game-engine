/* KeyboardEvent.java
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

package com.gladdware.game.input;

/**
 * Wrapper for keyboard input events
 */
public class KeyboardEvent extends InputEvent {
    
    private State keyState;
    private int keyCode;
    private char keyChar;

    /**
     * Constructor
     *
     * @param state the state of the keyboard event
     * @param code the LWJGL code for the keyboard event
     * @param character the character for the keyboard event
     */
    public KeyboardEvent(State state, int code, char character) {
        super(Source.KEYBOARD);
        
        this.keyState = state;
        this.keyCode = code;
        this.keyChar = character;
    }
    
    /**
     * Get the key state for the event
     *
     * @return the key state
     */
    public State getKeyState() {
        return this.keyState;
    }
    
    /**
     * Get the LWJGL key code for the event
     *
     * @return the LWJGL key code
     */
    public int getKeyCode() {
        return this.keyCode;
    }
    
    /**
     * Get the character for the event
     *
     * @return the key character
     */
    public char getKeyChar() {
        return this.keyChar;
    }
    
    /**
     * Key event state
     */
    public enum State {
        UP,
        DOWN;
    }
}
