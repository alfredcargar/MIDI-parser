package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.project.utility.LogsManager;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        LogsManager log = new LogsManager();

        log.info("hello world!");

    }
}
