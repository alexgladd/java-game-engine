/* Matrix4f.java
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

package com.gladdware.game.math;

import java.nio.FloatBuffer;

/**
 * 4x4 matrix, with floating point elements
 * 
 * Operations defined in this class typically execute in column-major ordering,
 * since that is what OpenGL typically expects to receive.
 */
public class Matrix4f {
    
    private static final int DIM = 4;
    private static final int MATRIX_4X4_SIZE = DIM * DIM;
    
    private static final float[] ZERO_MATRIX = {
        0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 0.0f, 0.0f
    };
    
    private static final String ELEMENT_FMT = "%1$8.6f";
    
    /** Static, immutable 4x4 identity matrix */
    private static Matrix4f IDENTITY = null;
    
    private float[] matrix;
    
    /**
     * Get the identity matrix
     *
     * @return
     */
    public static Matrix4f identity() {
        if(IDENTITY != null) {
            return IDENTITY;
        } else {
            // need to build it
            IDENTITY = new Matrix4f();
            
            IDENTITY.matrix[0] = 1.0f;  // 0 + 0 * 4
            IDENTITY.matrix[5] = 1.0f;  // 1 + 1 * 4
            IDENTITY.matrix[10] = 1.0f; // 2 + 2 * 4
            IDENTITY.matrix[15] = 1.0f; // 3 + 3 * 4
            
            return IDENTITY;
        }
    }
    
    /**
     * Build a translation matrix using the components of the give vector as the
     * translation components for the respective axes.
     *
     * @param xlate the translation vector
     * @return a new translation matrix
     */
    public static Matrix4f translate(Vector3f xlate) {
        Matrix4f result = identity();
        
        // just set the translation components
        result.matrix[12] = xlate.x;    // 0 + 3 * 4
        result.matrix[13] = xlate.y;    // 1 + 3 * 4
        result.matrix[14] = xlate.z;    // 2 + 3 * 4
        
        return result;
    }
    
    /**
     * Calculate a rotation matrix for all axes
     * 
     * See http://en.wikipedia.org/wiki/Rotation_matrix#In_three_dimensions
     *
     * @param radX x axis rotation angle in radians
     * @param radY y axis rotation angle in radians
     * @param radZ z axis rotation angle in radians
     * @return the all-axis rotation matrix
     */
    public static Matrix4f rotate(float radX, float radY, float radZ) {
        Matrix4f rotX = identity();
        Matrix4f rotY = identity();
        Matrix4f rotZ = identity();
        
        if(radX != 0.0f) {
            // calculate the x axis rotation matrix
            float sinX = (float)Math.sin(radX);
            float cosX = (float)Math.cos(radX);
            
            rotX.matrix[5] = cosX;     // 1 + 1 * 4
            rotX.matrix[9] = -sinX;    // 1 + 2 * 4
            rotX.matrix[6] = sinX;     // 2 + 1 * 4
            rotX.matrix[10] = cosX;    // 2 + 2 * 4
        }
        
        if(radY != 0.0f) {
            // calculate the y axis rotation matrix
            float sinY = (float)Math.sin(radY);
            float cosY = (float)Math.cos(radY);
            
            rotY.matrix[0] = cosY;     // 0 + 0 * 4
            rotY.matrix[8] = sinY;     // 0 + 2 * 4
            rotY.matrix[2] = -sinY;    // 2 + 0 * 4
            rotY.matrix[10] = cosY;    // 2 + 2 * 4
        }
        
        if(radZ != 0.0f) {
            // calculate the z axis rotation matrix
            float sinZ = (float)Math.sin(radZ);
            float cosZ = (float)Math.cos(radZ);
            
            rotZ.matrix[0] = cosZ;     // 0 + 0 * 4
            rotZ.matrix[4] = -sinZ;    // 0 + 1 * 4
            rotZ.matrix[1] = sinZ;     // 1 + 0 * 4
            rotZ.matrix[5] = cosZ;     // 1 + 1 * 4
        }
        
        // multiply all three axis rotation matrices to get the full result
        return rotX.mul(rotY).mul(rotZ);
    }
    
    /**
     * Calculate a rotation matrix for all axes
     * 
     * See Matrix4f.rotate(float, float, float)
     *
     * @param degX x axis rotation angle in degrees
     * @param degY y axis rotation angle in degrees
     * @param degZ z axis rotation angle in degrees
     * @return the all-axis rotation matrix
     */
    public static Matrix4f rotateDegrees(float degX, float degY, float degZ) {
        return rotate(
                (float)Math.toRadians(degX),
                (float)Math.toRadians(degY),
                (float)Math.toRadians(degZ));
    }
    
    /**
     * Build an orthographic projection matrix using the given clipping pane
     * parameters
     *
     * @param left
     * @param right
     * @param top
     * @param bottom
     * @param near
     * @param far
     * @return a new orthographic projection matrix
     */
    public static Matrix4f orthographic(float left, float right, float top,
            float bottom, float near, float far) {
        Matrix4f result = new Matrix4f();
        
        result.matrix[0] = 2.0f / (right - left);       // 0 + 0 * 4
        result.matrix[5] = 2.0f / (top - bottom);       // 1 + 1 * 4
        result.matrix[10] = -2.0f / (far - near);       // 2 + 2 * 4
        
        result.matrix[12] = -((right + left) / (right - left)); // 0 + 3 * 4
        result.matrix[13] = -((top + bottom) / (top - bottom)); // 1 + 3 * 4
        result.matrix[14] = -((far + near) / (far - near));     // 2 + 3 * 4
        
        result.matrix[15] = 1.0f;                       // 3 + 3 * 4
        
        return result;
    }
    
    /**
     * Build a perspective projection matrix using the given clipping pane
     * parameters
     *
     * @param left
     * @param right
     * @param top
     * @param bottom
     * @param near
     * @param far
     * @return a new perspective projection matrix
     */
    public static Matrix4f perspective(float left, float right, float top,
            float bottom, float near, float far) {
        Matrix4f result = new Matrix4f();
        
        result.matrix[0] = (2.0f * near) / (right - left);      // 0 + 0 * 4
        result.matrix[5] = (2.0f * near) / (top - bottom);      // 1 + 1 * 4
        result.matrix[10] = -((far + near) / (far - near));     // 2 + 2 * 4
        
        result.matrix[8] = (right + left) / (right - left);     // 0 + 2 * 4
        result.matrix[9] = (top + bottom) / (top - bottom);     // 1 + 2 * 4
        
        result.matrix[14] = (-2.0f * far * near) / (far - near);// 2 + 3 * 4
        
        result.matrix[11] = -1.0f;                              // 3 + 2 * 4
        
        return result;
    }
    
    public Matrix4f(float[] src) {
        this.matrix = new float[MATRIX_4X4_SIZE];
        
        System.arraycopy(src, 0, this.matrix, 0, MATRIX_4X4_SIZE);
    }

    /**
     * Default constructor
     * 
     * Initialize all elements to 0.0
     */
    public Matrix4f() {
        this(ZERO_MATRIX);
    }
    
    /**
     * Multiply this matrix by the given other, in column-major order
     *
     * @param other the matrix to multiply by
     * @return a new Matrix4f with the multiplication result
     */
    public Matrix4f mul(Matrix4f other) {
        Matrix4f result = new Matrix4f();
        
        for(int y = 0; y < DIM; y++) {
            for(int x = 0; x < DIM; x++) {
                float sum = 0.0f;
                // XXX could use a loop here, but just unwrapped it myself
                sum += this.matrix[0 + y * DIM] * other.matrix[x + 0 * DIM];
                sum += this.matrix[1 + y * DIM] * other.matrix[x + 1 * DIM];
                sum += this.matrix[2 + y * DIM] * other.matrix[x + 2 * DIM];
                sum += this.matrix[3 + y * DIM] * other.matrix[x + 3 * DIM];
                
                result.matrix[x + y * DIM] = sum;
            }
        }
        
        return result;
    }
    
    /**
     * Create a new float buffer for LWJGL backed by this matrix
     *
     * @return a new float buffer backed by this matrix
     */
    public FloatBuffer toBuffer() {
        return FloatBuffer.wrap(this.matrix);
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    public String toString() {
        // first calculate max width
        int maxWidth = 0;
        for(int i = 0; i < MATRIX_4X4_SIZE; i++) {
            String s = String.format(ELEMENT_FMT, this.matrix[i]);
            
            if(s.length() > maxWidth) {
                maxWidth = s.length();
            }
        }
        
        // now build the string
        StringBuilder sb = new StringBuilder("Matrix4f - " +
                System.identityHashCode(this) + "\n");
        
        for(int x = 0; x < DIM; x++) {
            // print row
            for(int y = 0; y < DIM; y++) {
                if(y == 0) {
                    // first element of new row
                    sb.append("    | ");
                }
                
                sb.append(String.format(
                        "%1$" + maxWidth + ".6f",
                        this.matrix[x + y * 4]));
                
                if(y < DIM - 1) {
                    sb.append(", ");
                }
                
                if(y == DIM - 1) {
                    // last element of row
                    sb.append(" |\n");
                }
            }
        }
        
        return sb.toString();
    }

}
