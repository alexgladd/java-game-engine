/* Log.java
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

package com.gladdware.game.log;

import java.util.Date;

/**
 * Simple, adaptable logging for the GGE
 */
public class Log {
    
    private static final String LOG_FMT = "[%1$tF %1$tT.%1$tL][%2$s][%3$s] > %4$s";
    
    private static LogLevel LOG_LVL = LogLevel.ERROR;
    
    /**
     * Set the global logging level
     * 
     * After this call completes all future log messages will be constrained to
     * the given log leve or higher.
     *
     * @param level the global log level to set
     * @return the previously set log level
     */
    public static LogLevel setLevel(LogLevel level) {
        LogLevel old = LOG_LVL;
        
        LOG_LVL = level;
        
        return old;
    }
    
    /**
     * Generic logging method
     * 
     * The core logic of the logger: formats the parameters into a full log
     * message. The message is only logged if the given level is greater than
     * or equal to the current global log level.
     *
     * @param level the log message level
     * @param tag the log message tag
     * @param msg the log message itself
     */
    private static void log(LogLevel level, String tag, String msg, Throwable t) {
        // only log if we have sufficient level
        if(level.level() < LOG_LVL.level()) {
            return;
        }
        
        // XXX: maybe eventually use throwables to get code locations (option)
        
        Date now = new Date();
        String formattedLog = String.format(LOG_FMT, now, level, tag, msg);
        
        // XXX: temporarily just log to stdout; eventually use swappable adapters
        System.out.println(formattedLog);
        
        // XXX: make this better for throwables
        if(t != null) {
            t.printStackTrace();
        }
    }
    
    /**
     * Log a TRACE message
     *
     * @param tag
     * @param msg
     */
    public static void t(String tag, String msg) {
        log(LogLevel.TRACE, tag, msg, null);
    }
    
    /**
     * Log a DEBUG message
     *
     * @param tag
     * @param msg
     */
    public static void d(String tag, String msg) {
        log(LogLevel.DEBUG, tag, msg, null);
    }
    
    /**
     * Log an INFO message
     *
     * @param tag
     * @param msg
     */
    public static void i(String tag, String msg) {
        log(LogLevel.INFO, tag, msg, null);
    }
    
    /**
     * Log a WARN message with a throwable
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg, Throwable t) {
        log(LogLevel.WARN, tag, msg, t);
    }
    
    /**
     * Log a WARN message
     *
     * @param tag
     * @param msg
     */
    public static void w(String tag, String msg) {
        log(LogLevel.WARN, tag, msg, null);
    }
    
    /**
     * Log an ERROR message with throwable
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg, Throwable t) {
        log(LogLevel.ERROR, tag, msg, t);
    }
    
    /**
     * Log an ERROR message
     *
     * @param tag
     * @param msg
     */
    public static void e(String tag, String msg) {
        log(LogLevel.ERROR, tag, msg, null);
    }
    
    /**
     * Log a FATAL message with throwable
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg, Throwable t) {
        log(LogLevel.FATAL, tag, msg, t);
    }
    
    /**
     * Log a FATAL message
     *
     * @param tag
     * @param msg
     */
    public static void f(String tag, String msg) {
        log(LogLevel.FATAL, tag, msg, null);
    }
    
    /**
     * Constructor - Never instantiate
     */
    private Log() {}
}
