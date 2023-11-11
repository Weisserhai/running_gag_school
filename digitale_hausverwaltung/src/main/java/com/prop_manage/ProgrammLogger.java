package com.prop_manage;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.LogRecord;

public class ProgrammLogger 
{
    public static final Logger LOGGER = Logger.getLogger(ProgrammLogger.class.getName());

    static 
    {
        try 
        {
            Handler fileHandler = new FileHandler("logfile.log", true);
            fileHandler.setFormatter(new ReverseFormatter());
            LOGGER.addHandler(fileHandler);
        } 
        catch (Exception error) 
        {
            error.printStackTrace();
        }
    }

    private static class ReverseFormatter extends SimpleFormatter 
    {
        @Override
        public String format(LogRecord record) 
        {
            return super.format(record) + System.lineSeparator();
        }
    }
}
