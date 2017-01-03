package Logger;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * singleton class, which manages the instance of the according OhmLogger
 * @author kevin
 */
public class OhmLogger {
    
    private static Logger lg;
    
    private OhmLogger() {
        lg = null;
    }
    
  /**
   * method to return the single instance of the OhmLogger
   * @return
   */
  public static OhmLogger getInstance() {
        return OhmLoggerHolder.INSTANCE;
    }
    
    private static class OhmLoggerHolder {

        private static final OhmLogger INSTANCE = new OhmLogger();
    }
    
  /**
   *
   * @return returns the OhmLogger instance
   */
  public static Logger getLogger()
    {
        if(lg == null)
        {
            lg = Logger.getLogger("OhmLogger");
            initLogger();
        }
        return lg;
    }
    
    private static void initLogger()
    {
        try
        {
            Properties props = new Properties();
            
            InputStream is = OhmLogger.getInstance().getClass().
                    getResourceAsStream("logger.properties");
            props.load(is);
            
            String logFile = props.getProperty("LOG_DATEI");
            FileHandler fh = new FileHandler(logFile);
            
            lg.addHandler(fh);
            
            fh.setFormatter(new OhmFormatter());
            
            String logLevel = props.getProperty("LOG_LEVEL");
            lg.setLevel(Level.parse(logLevel));  
            
            lg.setUseParentHandlers(false);
            
        }
        catch(IOException ex)
        {
            System.err.println(ex);
        }
    }
}
