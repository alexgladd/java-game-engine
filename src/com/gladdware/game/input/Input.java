/* Input.java
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

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import com.gladdware.game.core.EngineException;
import com.gladdware.game.log.Log;

/**
 * Abstraction for game input (e.g., mouse and keyboard)
 */
public class Input {
    
    private static final String TAG = "Input";
    
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
    }
    
    /**
     * Tear down the input sources
     */
    public static void dispose() {
        // destroy keyboard and mouse
        Log.d(TAG, "Destroying input sources");
        Keyboard.destroy();
        Mouse.destroy();
    }
    
    // TODO refine input collection
    public static void collectInput() {
        // collect keyboard input
        while(Keyboard.next()) {
            long keyTimeNs = Keyboard.getEventNanoseconds();
            int keyCode = Keyboard.getEventKey();
            char keyChar = Keyboard.getEventCharacter();
            boolean keyState = Keyboard.getEventKeyState();
            
            KeyboardEvent ke = new KeyboardEvent(keyTimeNs,
                    (keyState?KeyboardEvent.State.DOWN:KeyboardEvent.State.UP),
                    keyCode, keyChar);
            
            Log.d(TAG, ke.toString());
            
//            Log.d(TAG, "KEYBOARD EVT [tm=" + keyTimeNs + ", code=" + keyCode +
//                    ", char=" + keyChar + ", state=" + keyState + "]");
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
            
            Log.d(TAG, me.toString());
            
//            Log.d(TAG, "MOUSE EVT [tm=" + mouseTimeNs + ", btn=" + mouseBtn +
//                    ", btnState=" + mouseBtnState + ", x=" + mouseX + ", y=" +
//                    mouseY + ", dx=" + mouseDeltaX + ", dy=" + mouseDeltaY +
//                    ", dwheel=" + mouseDeltaWheel + "]");
        }
    }
    
    /**
     * Constructor - Never instantiate
     */
    private Input() {}
}
