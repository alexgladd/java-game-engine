/* KeyboardEvent.java
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

package com.gladdware.game.input;

/**
 * Wrapper for keyboard input events
 */
public class KeyboardEvent extends InputEvent {
    
    /** State of the key */
    private State keyState;
    /** LWJGL key code */
    private int keyCode;
    /** Key character, if applicable */
    private char keyChar;

    /**
     * Constructor
     *
     * @param timeNs the time the keyboard event occurred in nanoseconds
     * @param state the state of the keyboard event
     * @param code the LWJGL code for the keyboard event
     * @param character the character for the keyboard event
     */
    public KeyboardEvent(long timeNs, State state, int code, char character) {
        super(Source.KEYBOARD, timeNs);
        
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
    
    /* (non-Javadoc)
     * @see com.gladdware.game.input.InputEvent#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        
        sb.append("code=" + this.keyCode);
        sb.append(", char=" + this.keyChar);
        sb.append(", state=" + this.keyState.name());
        
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * Key event state
     */
    public enum State {
        UP,
        DOWN;
    }
}
