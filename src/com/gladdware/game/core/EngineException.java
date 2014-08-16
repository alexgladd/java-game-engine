/* EngineException.java
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
 * Generic exception for the Gladdware Game Engine
 */
public class EngineException extends Exception {

    /**
     * Serializable version id
     */
    private static final long serialVersionUID = 1L;

    /**
     * Constructor
     *
     */
    public EngineException() {
        super();
    }

    /**
     * Constructor
     *
     * @param message
     */
    public EngineException(String message) {
        super(message);
    }

    /**
     * Constructor
     *
     * @param cause
     */
    public EngineException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     */
    public EngineException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructor
     *
     * @param message
     * @param cause
     * @param enableSuppression
     * @param writableStackTrace
     */
    public EngineException(String message, Throwable cause,
            boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
