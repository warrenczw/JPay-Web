package com.czw.common.tools;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class LogString
{
    private StringBuffer m_str;
    private boolean m_spiltfirst;
    private String m_splitstring;
    private String m_defaultString;
    
    public LogString()
    {
        this.init();
    }
    
    public LogString(boolean spiltfirst)
    {
        m_spiltfirst = true;
        this.init();
    }
    
    public LogString(String splitstring)
    {
        this.init();
        this.setSplitString(splitstring);
    }
    
    public static String log(Object ... obj)
    {
        return newInstance().append(obj).toString();
    }
    
    public static LogString newInstance()
    {
        return new LogString();
    }
    
    public static LogString newInstance(String splitstring)
    {
        return new LogString(splitstring);
    }
    
    private void init()
    {
        if(m_str == null)
            m_str = new StringBuffer();
        else
            m_str.setLength(0);
        setSplitString(null);
        m_defaultString = "-";
    }
    
    public void reset()
    {
        if(m_str != null)
            m_str.setLength(0);
    }
    
    public void setSplitString(String splitstring)
    {
        m_splitstring = splitstring;
        if(!JavaTools.stringIsNotNull(m_splitstring))
            m_splitstring = "|";
    }
    
    public void setDefaultString(String defaultString)
    {
        if(defaultString != null)
        	m_defaultString = defaultString;
    }
    
    public LogString append(String str)
    {
        if(m_str.length() > 0 || m_spiltfirst)
            m_str.append(m_splitstring);
        m_str.append(str==null||"".equals(str)||"null".equals(str)?m_defaultString:str);
        return this;
    }
    
    @SuppressWarnings("unchecked")
    public LogString append(Object obj)
    {
    	if(obj != null)
    	{
	    	if(obj.getClass().isArray())
	    		return this.append((Object[])obj);
	    	if(obj instanceof List)
	    		return this.append((List)obj);
	    	if(obj instanceof Map)
	    		return this.appendMap((Map)obj);
	    	if(obj instanceof Collection)
	    		return this.append((Collection)obj);
    	}
        return this.append(obj==null?null:obj.toString());
    }
    
    private StringBuffer stringBufferAppend(StringBuffer s, Object o)
    {
		if(s == null)
			s = new StringBuffer();
    	if(o != null)
    	{
	    	if(o.getClass().isArray())
	    	{
	    		int len = ((Object[])o).length;
	    		for(Object oo : (Object[])o)
	    		{
	    			if(len > 1)
	    				s.append('[');
	    			this.stringBufferAppend(s, oo);
	    			if(len > 1)
	    				s.append(']');
	    		}
	    		return s;
	    	}
	    	if(o instanceof List)
	    	{
	    		int len = ((List)o).size();
	    		for(Object oo : (List)o)
	    		{
	    			if(len > 1)
	    				s.append('[');
	    			this.stringBufferAppend(s, oo);
	    			if(len > 1)
	    				s.append(']');
	    		}
	    		return s;
	    	}
	    	if(o instanceof Map)
	    	{
	    		for(Map.Entry<Object, Object> oo : ((Map<Object, Object>)o).entrySet())
	    		{
	    			s.append('[');
	    			this.stringBufferAppend(s, oo.getKey());
    				s.append('=');
	    			this.stringBufferAppend(s, oo.getValue());
	    			s.append(']');
	    		}
	    		return s;
	    	}
	    	if(o instanceof Collection)
	    	{
	    		int len = ((Collection)o).size();
	    		for(Object oo : (Collection)o)
	    		{
	    			if(len > 1)
	    				s.append('[');
	    			this.stringBufferAppend(s, oo);
	    			if(len > 1)
	    				s.append(']');
	    		}
	    		return s;
	    	}
    	}
        return o == null ? s : s.append(o.toString());
    }
    
    public LogString append(Object ... objs)
    {
    	if(objs != null && objs.length > 0)
    	{
    		for(int i = 0;i < objs.length;i ++)
    			this.append(objs[i]);
    	}
    	else
			this.append("");
        return this;
    }
    
    public LogString append(int data)
    {
        return this.append(String.valueOf(data));
    }
    
    public LogString append(long data)
    {
        return this.append(String.valueOf(data));
    }
    
    public LogString append(double data)
    {
        return this.append(String.valueOf(data));
    }
    
    public LogString append(float data)
    {
        return this.append(String.valueOf(data));
    }
    
    public LogString append(boolean data)
    {
        return this.append(data?"T":"F");
    }
    
    @SuppressWarnings("unchecked")
	public LogString appendMap(Map data)
    {
        if(data != null)
        {
            StringBuffer strbuf = this.stringBufferAppend(null, data);
            this.append(strbuf.toString());
        }
        else
            this.append("");
        return this;
    }
    
    @SuppressWarnings("unchecked")
	public LogString append(List data)
    {
        if(data != null && data.size() > 0)
        {
        	for(int i = 0;i < data.size();i ++)
        	{
                this.append(data.get(i));
        	}
        }
        else
            this.append("");
        return this;
    }
    
    @SuppressWarnings("unchecked")
	public LogString append(Map data)
    {
        return this.appendMap(data);
    }

    @SuppressWarnings("unchecked")
    public LogString append(Collection data)
    {
        if(data != null)
        {
            StringBuffer strbuf = this.stringBufferAppend(null, data);
            this.append(strbuf.toString());
        }
        else
            this.append("");
        return this;
    }
    
    public LogString append(byte[] data)
    {
        if(data != null && data.length > 0)
        {
            StringBuffer strbuf = new StringBuffer();
            for(int i = 0;i < data.length;i ++)
            {
                strbuf.append(Integer.toHexString((int)0xFF & data[i]).toUpperCase());
            }
            this.append(strbuf.toString());
        }
        else
            this.append("");
        return this;
    }
    
    public LogString appendStringArray(String[] data)
    {
        if(data != null && data.length > 0)
        {
            if(data.length == 1)
                return this.append(data[0]);
            StringBuffer strbuf = new StringBuffer();
            for(int i = 0;i < data.length;i ++)
            {
            	if(i > 0)
            		strbuf.append(",");
            	strbuf.append(data[i]==null || "".equals(data[i])?m_defaultString:data[i]);
            }
            return this.append(strbuf.toString());
        }
        else
            return this.append("");
    }
    
    public String toString()
    {
        return m_str.toString();
    }
    
    public int length()
    {
        return m_str.length();
    }
    
    public void clean()
    {
        m_str.setLength(0);
    }
    
    public void recycle()
    {
        m_str = null;
        m_spiltfirst = false;
        m_splitstring = null;
    }
    
    public static void main(String[] args)
    {
    	/*
    	Map m = new HashMap();
    	Map m1 = new HashMap();
    	m1.put("a", "b");
    	m1.put("b", 1);
    	List l = new ArrayList();
    	l.add("a");
    	l.add(2);
    	List ll = new ArrayList();
    	ll.add("b");
    	m1.put("bb", l);
    	m1.put("bbb", ll);
    	Map m2 = new HashMap();
    	m2.put("b", "b");
    	m2.put("c", new String[]{"xx", "xxx", "xxxx"});
    	m2.put("cc", new String[]{"xxxxx"});
    	m.put("d", m1);
    	m.put("e", m2);
    	m.put( new String[]{"xx", "xxx", "xxxx"}, m2);
    	LogString ls = new LogString().append("start").append(m).append("end");
    	System.out.println(ls);
    	*/
    }
}
