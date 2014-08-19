/* InputEvent.java
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
 * Abstract game input event
 */
public abstract class InputEvent {
    
    private Source source;
    
    /**
     * Constructor
     *
     * @param src the source of this input
     */
    public InputEvent(Source src) {
        this.source = src;
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
     * Input source enumeration
     */
    public enum Source {
        KEYBOARD,
        MOUSE;
    }
}
