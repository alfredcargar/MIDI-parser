package org.project.utility;

import org.apache.log4j.*;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class LogsManager {

    private static final Logger logger = LogManager.getLogger(LogsManager.class);
    private ArrayList<String> infoLog = new ArrayList<>();
    private ArrayList<String> errorLog = new ArrayList<>();

    public void info(String log) {
        logger.log(Level.INFO, log);
        infoLog.add(log);
    }

    public void warn(String log) {
        logger.warn(log);
    }

    public void error(String log) {
        logger.log(Level.ERROR, log);
        errorLog.add(log);
    }

    public void fatal(String log) {
        logger.fatal(log);
    }


    public static Logger getLogger() {
        return logger;
    }

    public ArrayList<String> getInfoLog() {
        return infoLog;
    }

    public void setInfoLog(ArrayList<String> infoLog) {
        this.infoLog = infoLog;
    }

    public ArrayList<String> getErrorLog() {
        return errorLog;
    }

    public void setErrorLog(ArrayList<String> errorLog) {
        this.errorLog = errorLog;
    }
}
