/* MouseEvent.java
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
 * Wrapper for mouse input events
 */
public class MouseEvent extends InputEvent {
    
    /** Mouse button that generated the event */
    private int button;
    /** State of the mouse button */
    private ButtonState buttonState;
    
    /** Absolute position of the mouse pointer */
    private int x, y;
    /** Change in position of the mouse pointer since the last poll */
    private int deltaX, deltaY;
    
    /** Change in position of the mouse wheel since the last poll */
    private int deltaWheel;

    /**
     * Constructor
     *
     * @param timeNs the time the mouse event occurred in nanoseconds
     * @param btn the button that caused this event
     * @param btnState the state of the mouse button
     * @param x absolute X position of the mouse pointer
     * @param y absolute Y position of the mouse pointer
     * @param dx change in X position of the mouse pointer since last event
     * @param dy change in Y position of the mouse pointer since last event
     * @param dWheel change in mouse wheel position since last event
     */
    public MouseEvent(long timeNs, int btn, ButtonState btnState, int x, int y,
            int dx, int dy, int dWheel) {
        super(Source.MOUSE, timeNs);
        
        this.button = btn;
        this.buttonState = btnState;
        this.x = x;
        this.y = y;
        this.deltaX = dx;
        this.deltaY = dy;
        this.deltaWheel = dWheel;
    }
    
    /**
     * Get the mouse button for this event.
     * 
     * A button value of -1 indicates that the event doesn't correspond to a
     * button click.
     *
     * @return the mouse button
     */
    public int getButton() {
        return this.button;
    }
    
    /**
     * Get the state of the mouse button event.
     * 
     * The state NO_BUTTON indicates that the event doesn't correspond to a
     * button click.
     *
     * @return the mouse button state
     */
    public ButtonState getButtonState() {
        return this.buttonState;
    }
    
    /**
     * Get the absolute X position of the mouse pointer for this event.
     * 
     * Note that this value will be clamped to the minimum and maximum
     * X position values of the display window.
     *
     * @return absolute X position of this mouse event
     */
    public int getX() {
        return this.x;
    }
    
    /**
     * Get the absolute Y position of the mouse pointer for this event.
     * 
     * Note that this value will be clamped to the minimum and maximum
     * Y position values of the display window.
     *
     * @return absolute Y position of this mouse event
     */
    public int getY() {
        return this.y;
    }
    
    /**
     * Get the change in the X position of the mouse pointer for this event.
     * 
     * This will be the change in the X position since the last mouse event.
     *
     * @return the change in X position for this mouse event
     */
    public int getDeltaX() {
        return this.deltaX;
    }
    
    /**
     * Get the change in the Y position of the mouse pointer for this event.
     * 
     * This will be the change in the Y position since the last mouse event.
     *
     * @return the change in Y position for this mouse event
     */
    public int getDeltaY() {
        return this.deltaY;
    }
    
    /**
     * Get the change in mouse wheel position for this event.
     * 
     * This will be the change in the mouse wheel position since the last mouse
     * event.
     *
     * @return the change in mouse wheel position for this mouse event
     */
    public int getDeltaWheel() {
        return this.deltaWheel;
    }
    
    /* (non-Javadoc)
     * @see com.gladdware.game.input.InputEvent#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append(" [");
        
        sb.append("btn=" + this.button);
        sb.append(", btnState=" + this.buttonState.name());
        sb.append(", x=" + this.x);
        sb.append(", y=" + this.y);
        sb.append(", dx=" + this.deltaX);
        sb.append(", dy=" + this.deltaY);
        sb.append(", dwheel=" + this.deltaWheel);
        
        sb.append("]");
        
        return sb.toString();
    }
    
    /**
     * Mouse button state enumeration
     */
    public enum ButtonState {
        NO_BUTTON,
        UP,
        DOWN;
    }
}
