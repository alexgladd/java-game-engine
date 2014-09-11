/* InputEvent.java
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
 * Abstract game input event
 */
public abstract class InputEvent implements Comparable<InputEvent> {
    /** Source of the input event */
    private Source source;
    
    /** Time the input event occurred, in nanoseconds */
    private long evtTimeNs;
    
    /**
     * Constructor
     *
     * @param src the source of this input
     */
    public InputEvent(Source src, long evtTimeNs) {
        this.source = src;
        this.evtTimeNs = evtTimeNs;
    }
    
    /**
     * Get the source of this input
     *
     * @return the input source
     */
    public Source getSource() {
        return this.source;
    }
    
    /**
     * Get the time the input event occurred, in nanoseconds
     * 
     * Note that the only value of event times is in comparing times of events
     * relative to one another, and not absolute time.
     *
     * @return input event time in nanoseconds
     */
    public long getEventTimeNs() {
        return this.evtTimeNs;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    public int compareTo(InputEvent other) {
        if(other == null) {
            throw new NullPointerException("Other InputEvent must not be null");
        }
        
        if(this.getEventTimeNs() < other.getEventTimeNs()) {
            return -1;
        } else if(this.getEventTimeNs() == other.getEventTimeNs()) {
            return 0;
        } else {
            return 1;
        }
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        switch(this.source) {
        case KEYBOARD:
            sb.append("KEYBOARD EVT");
            break;
            
        case MOUSE:
            sb.append("MOUSE EVT");
            break;
        }
        
        sb.append(" @ " + this.evtTimeNs);
        
        return sb.toString();
    }

    /**
     * Input source enumeration
     */
    public enum Source {
        KEYBOARD,
        MOUSE;
    }
}
