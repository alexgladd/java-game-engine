/* Input.java
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

package com.gladdware.game.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.gladdware.game.input.InputEvent;
import com.gladdware.game.input.KeyboardEvent;
import com.gladdware.game.input.MouseEvent;
import com.gladdware.game.log.Log;

/**
 * Abstraction for game input (e.g., mouse and keyboard)
 */
public class Input {
    
    private static final String TAG = "Input";
    
    /** List of "current" mouse input events */
    private static List<MouseEvent> curMouseInputs;
    /** List of "current" keyboard input events */
    private static List<KeyboardEvent> curKeyboardInputs;
    /** List of all "current" input events */
    private static List<InputEvent> curInputs;
    
    /**
     * Initialize mouse and keyboard input for the game engine
     *
     * @throws EngineException on failure to initialize any input source
     */
    public static void init() throws EngineException {
        // try to initialize keyboard and mouse
        
        try {
            Keyboard.create();
            Log.d(TAG, "Successfully setup keyboard input");
        } catch (LWJGLException e) {
            throw new EngineException("Failed to setup keyboard input", e);
        }
        
        try {
            Mouse.create();
            Log.d(TAG, "Successfully setup mouse input");
        } catch (LWJGLException e) {
            throw new EngineException("Failed to setup mouse input", e);
        }
        
        // allocate input lists
        curInputs = new ArrayList<InputEvent>();
        curMouseInputs = new ArrayList<MouseEvent>();
        curKeyboardInputs = new ArrayList<KeyboardEvent>();
    }
    
    /**
     * Tear down the input sources
     */
    public static void dispose() {
        // destroy keyboard and mouse
        Log.d(TAG, "Destroying input sources");
        Keyboard.destroy();
        Mouse.destroy();
        
        clearInputs();
    }
    
    /**
     * Get all input events collected for the current frame, sorted by time of
     * event occurrence.
     * 
     * Note: Users should not modify the returned list or its contents. Instead,
     * copy data out of the list.
     *
     * @return List of all InputEvents for the current frame
     */
    public static List<InputEvent> getInputs() {
        return curInputs;
    }
    
    /**
     * Get mouse input events collected for the current frame, sorted by time of
     * event occurrence.
     * 
     * Note: Users should not modify the returned list or its contents. Instead,
     * copy data out of the list.
     *
     * @return List of MouseEvents for the current frame
     */
    public static List<MouseEvent> getMouseInputs() {
        return curMouseInputs;
    }
    
    /**
     * Get keyboard input events collected for the current frame, sorted by time
     * of event occurrence.
     * 
     * Note: Users should not modify the returned list or its contents. Instead,
     * copy data out of the list.
     *
     * @return List of KeyboardEvents for the current frame
     */
    public static List<KeyboardEvent> getKeyboardInputs() {
        return curKeyboardInputs;
    }
    
    /**
     * Collect all current mouse and keyboard inputs as reported by LWJGL.
     * 
     * Events wrapped into their respective InputEvent classes and put into
     * separate lists.
     */
    protected static void collectInput() {
        // clear old lists
        clearInputs();
        
        // collect keyboard input
        while(Keyboard.next()) {
            long keyTimeNs = Keyboard.getEventNanoseconds();
            int keyCode = Keyboard.getEventKey();
            char keyChar = Keyboard.getEventCharacter();
            boolean keyState = Keyboard.getEventKeyState();
            
            KeyboardEvent ke = new KeyboardEvent(keyTimeNs,
                    (keyState?KeyboardEvent.State.DOWN:KeyboardEvent.State.UP),
                    keyCode, keyChar);
            
            curInputs.add(ke);
            curKeyboardInputs.add(ke);
            
            Log.t(TAG, ke.toString());
        }
        
        // collect mouse input
        while(Mouse.next()) {
            long mouseTimeNs = Mouse.getEventNanoseconds();
            int mouseBtn = Mouse.getEventButton();
            boolean mouseBtnState = Mouse.getEventButtonState();
            int mouseX = Mouse.getEventX();
            int mouseY = Mouse.getEventY();
            int mouseDeltaX = Mouse.getEventDX();
            int mouseDeltaY = Mouse.getEventDY();
            int mouseDeltaWheel = Mouse.getEventDWheel();
            
            MouseEvent.ButtonState ms;
            if(mouseBtn == -1) {
                // no button
                ms = MouseEvent.ButtonState.NO_BUTTON;
            } else {
                if(mouseBtnState) {
                    ms = MouseEvent.ButtonState.DOWN;
                } else {
                    ms = MouseEvent.ButtonState.UP;
                }
            }
            
            MouseEvent me = new MouseEvent(mouseTimeNs, mouseBtn, ms, mouseX,
                    mouseY, mouseDeltaX, mouseDeltaY, mouseDeltaWheel);
            
            curInputs.add(me);
            curMouseInputs.add(me);
            
            Log.t(TAG, me.toString());
        }
        
        // sort the lists (by event time)
        Collections.sort(curInputs);
        Collections.sort(curMouseInputs);
        Collections.sort(curKeyboardInputs);
    }
    
    /**
     * Clear current input events
     */
    private static void clearInputs() {
        curMouseInputs.clear();
        curKeyboardInputs.clear();
        curInputs.clear();
    }
    
    /**
     * Constructor - Never instantiate
     */
    private Input() {}
}
