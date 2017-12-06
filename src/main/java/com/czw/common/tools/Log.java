package com.czw.common.tools;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class Log
{
    private String infoLoggerName = null;
    private String debugLoggerName = null;
    private String errorLoggerName = null;
    
    /**
     * 
     */
    private Log() {
        super();
    }
    
    public static Log newInstance()
    {
        return new Log();
    }
    
    public static void initLog4j(String filename)
    {
        if(filename != null)
        {
            try
            {
                if(filename.endsWith(".xml"))
                    DOMConfigurator.configureAndWatch ( filename );
                else
                    PropertyConfigurator.configureAndWatch (filename);
            }
            catch (Exception e)
            {
                System.out.println("Log4j|init Log4j failed. " + filename);
                e.printStackTrace(System.err);
                return;
            }
            System.out.println("Log4j|init Log4j ok. " + filename);
        }
    }
    
    public Log init(String names)
    {
        if(names != null && names.length() > 0)
        {
            String[] namelist = names.split(",");
            if(namelist != null && namelist.length > 0)
            {
                this.init(namelist[0],namelist.length >= 2 ? namelist[1] : null,namelist.length >= 3 ? namelist[2] : null);
            }
        }
        return this;
    }
    
    public Log init(String info, String debug, String error)
    {
        if(JavaTools.stringIsNotNull(info) && infoLoggerName == null)
        {
            synchronized(this)
            {
                infoLoggerName = info;
            }
        }
        if(JavaTools.stringIsNotNull(debug) && debugLoggerName == null)
        {
            synchronized(this)
            {
                debugLoggerName = debug;
            }
        }
        if(JavaTools.stringIsNotNull(error) && errorLoggerName == null)
        {
            synchronized(this)
            {
                errorLoggerName = error;
            }
        }
        return this;
    }
    
    private Logger getLogger(String name)
    {
    	if(name != null)
    	{
	    	Logger l = Logger.getLogger(name);
	    	if(l.getLevel() != null)
	    		return l;
    	}
    	return null;
    }
    
    public void info(Object ... msg)
    {
    	this.info(JavaTools.logString(msg));
    }
    
    public void info(Object msg)
    {
    	Logger l = this.getLogger(infoLoggerName);
    	if(l != null)
    	{
    		l.info(msg);
    		return;
    	}
    }
    
    public void debug(Object msg)
    {
        debug(msg, null);
    }
    
    public void debug(Object msg, Exception e)
    {
        debug(msg, (Throwable)e);
    }
    
    public void debug(Object msg, Throwable e)
    {
    	Logger l = this.getLogger(debugLoggerName);
    	if(l != null)
    	{
            if(e != null)
                l.debug(msg+"|" + (e.getMessage() == null ? "" : e.getMessage()), e);
            else
                l.debug(msg);
    		return;
    	}
        if(e != null)
        {
            System.out.println(e.getMessage());
            e.printStackTrace(System.out);
        }
    }
    
    public void debug1(Object ... msg)
    {
    	this.debug(JavaTools.logString(msg));
    }
    
    public void debug2(Throwable e, Object ... msg)
    {
    	this.debug(JavaTools.logString(msg), e);
    }
    
    public void error(Object msg)
    {
        error(msg, null);
    }
    
    public void error(Object msg, Exception e)
    {
        error(msg, (Throwable)e);
    }
    
    public void error1(Object ... msg)
    {
    	this.error(JavaTools.logString(msg));
    }
    
    public void error2(Throwable e, Object ... msg)
    {
    	this.error(JavaTools.logString(msg), e);
    }
    
    public void error(Object msg, Throwable e)
    {
    	Logger l = this.getLogger(errorLoggerName);
    	if(l != null)
    	{
            if(e != null)
                l.error(msg
                        +"|" + (e.getMessage() == null ? "" : e.getMessage())
                        , e);
            else
                l.error(msg);
    		return;
    	}
        System.err.println(JavaTools.getTimeString("yyyy-MM-dd HH:mm:ss,SSS")+"|"+msg);
        if(e != null)
        {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
    }
    
    public static String errorString(Object title, Throwable e)
    {
    	return errorString(title, e, true);
    }
    
    public static String errorString(Object title, Throwable e, boolean needTime)
    {
    	return errorString(title, e, true, '|');
    }
    
    public static String errorString(Object title, Throwable e, boolean needTime, char spliter)
    {
        StringBuffer msg = new StringBuffer();
        if(needTime)
        	msg.append(JavaTools.getTimeString("yyyy-MM-dd,HH:mm:ss,SSS")).append(spliter);
        if(title != null)
            msg.append(title).append(spliter);
        if(e != null)
        {
            if(e.getMessage() != null)
                msg.append("Message: ").append(e.getMessage()).append(spliter);
//            if(e.getLocalizedMessage() != null)
//                msg.append("LocalizedMessage: ").append(e.getLocalizedMessage()).append(spliter);

            msg.append("\n");
            msg.append("Exception: ");
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PrintWriter writer = new PrintWriter(bos);
            e.printStackTrace(writer);

            boolean printre = false;
            while(e.getCause() != null)
            {
                e = e.getCause();
                printre = true;
            }
            if(printre)
            {
                writer.print("\nRootException: ");
                e.printStackTrace(writer);
            }
            writer.flush();
            writer.close();
            try {
                bos.close();
            } catch (IOException e1) {
            }
            msg.append(new String(bos.toByteArray()));
        }
        return msg.toString();
    }
    
    
    
}
