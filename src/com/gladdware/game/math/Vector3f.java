/* Vector3f.java
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

package com.gladdware.game.math;

/**
 * Vector in 3D space, with floating point components
 */
public class Vector3f {
    
    /** Vector coordinates */
    protected float x, y, z;
    
    /**
     * Constructor
     *
     * @param x the x coordinate
     * @param y the y coordinate
     * @param z the z coordinate
     */
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Default constructor
     * 
     * Initialize all coordinates to 0.0
     */
    public Vector3f() {
        this(0.0f, 0.0f, 0.0f);
    }
    
    /**
     * Copy constructor
     *
     * @param other the vector to copy
     */
    public Vector3f(Vector3f other) {
        this(other.x, other.y, other.z);
    }

}
