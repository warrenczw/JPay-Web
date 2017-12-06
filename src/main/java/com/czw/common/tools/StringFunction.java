package com.czw.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class StringFunction
{
    /**
     * 
     */
	public static final char[] BlankChar = {' ', '\t'};
	public static final char[] BlankAndReturnChar = {' ', '\t', '\r', '\n'};
	public static final char[] ReturnChar = {'\r', '\n'};
    public static final char[] cannotinputchar = {'|','\'', '\"', '&', '<', '>', '$', '%'};

    /**
     * 将html格式的字符串去掉html相关信息
     * @param htmlText
     * @return
     */
	public final static String htmlToText(String htmlText)
	{
		return htmlToText(htmlText, false);
	}
	
    /**
     * 将html格式的字符串去掉html相关信息
     * @param htmlText
     * @return
     */
	public final static String htmlToText(String htmlText, boolean onlyRemoveJs)
	{
		if(stringIsNotNull(htmlText))
		{
			StringBuffer strbuf = new StringBuffer();
			StringBuffer strbuftemp = new StringBuffer();
			StringBuffer tagName = new StringBuffer();
			char c, lastTagChar = 0;
			boolean startTag = false;
			boolean wrongContent = false;
			boolean haveHeadSlash = false, haveTailSlash = false;
			String tagString;
			String wrongContentTag = null;
			for(int i = 0,noBlankNum = 0,noBlankArray = 0;i < htmlText.length();i ++)
			{
				c = htmlText.charAt(i);
				if(c == '<')
				{
					if(strbuftemp.length() > 0)
					{
						if(startTag)
							strbuf.append(strbuftemp);
						strbuftemp.setLength(0);
					}
					startTag = true;
					haveHeadSlash = false;
					haveTailSlash = false;
					lastTagChar = 0;
					noBlankNum = 0;
					noBlankArray = 0;
					if(tagName.length() > 0)
						tagName.setLength(0);
				}
				else if(c == '>')
				{
					if(startTag)
					{
						startTag = false;
						if(strbuftemp.length() > 0)
						{
							strbuftemp.append(c);
							if(onlyRemoveJs && haveHeadSlash && !wrongContent)
								strbuf.append(strbuftemp);
							
							haveTailSlash = (lastTagChar == '/');
							if(!haveTailSlash)
							{
								tagString = tagName.toString().toLowerCase().trim();
								if(tagString.length() > 0)
								{
									if(haveHeadSlash)
									{
										if(wrongContentTag != null && tagString.equals(wrongContentTag))
											wrongContent = false;
									}
									else if("style".equals(tagString) || "script".equals(tagString) || "xml".equals(tagString))
									{
										wrongContent = true;
										wrongContentTag = tagString;
									}
								}
							}
							if(tagName.length() > 0)
								tagName.setLength(0);
							if(onlyRemoveJs && !haveHeadSlash && !wrongContent)
								strbuf.append(strbuftemp);
							
							strbuftemp.setLength(0);
						}
						continue;
					}
				}
				if(startTag)
				{
					strbuftemp.append(c);
					
					if(c <= ' ')
					{
						noBlankNum = 0;
					}
					else if (c == '/')
					{
						if(noBlankArray == 0)
							haveHeadSlash = true;
						lastTagChar = c;
					}
					else if(c != '<' && c != '>')
					{
						if(noBlankNum == 0)
							noBlankArray ++;
						noBlankNum ++;
						if(noBlankArray == 1)
							tagName.append(c);

						lastTagChar = c;
					}
				}
				else if(!wrongContent)
					strbuf.append(c);
			}
			if(startTag && strbuftemp.length() > 0)
				strbuf.append(strbuftemp);
			return strbuf.toString();
		}
		return htmlText;
	}
	
	public final static String removeJsAndStyleFromText(String htmlText)
	{
		return null;
	}
	
	/**
	 * 将字符串转换成脚本文字
	 * @param string
	 * @return
	 */
	public final static String toScriptChars(String string)
	{
        if(string == null || string.equals(""))
            return string;

        string = replaceAll(string, "\"", "\\\"");
        string = replaceAll(string, "'", "\\'");
        string = replaceAll(string, "\t", "\\t");
        string = replaceAll(string, "\n", "\\n");
        string = replaceAll(string, "/", "\\/");
        string = replaceAll(string, "\\", "\\\\");
        
        return string;
	}
	
	/**
	 * 将脚本文字转换成普通字符串
	 * @param string
	 * @return
	 */
	public final static String unScriptChars(String string)
	{
        if(string == null || string.equals(""))
            return string;

        string = replaceAll(string, "\\\"", "\"");
        string = replaceAll(string, "\\'", "'");
        string = replaceAll(string, "\\t", "\t");
        string = replaceAll(string, "\\n", "\n");
        string = replaceAll(string, "\\/", "/");
        string = replaceAll(string, "\\\\", "\\");
        
        return string;
	}

    /**
     * 将超文本中的替代符号转换成字符
     * @param string
     * @return
     */
    public final static String unHtmlSpecialChars(String string)
    {
        if(string == null || string.equals(""))
            return string;
        string = removeNoReadChar(string);
        string = replaceAll(string, "<br>", "\n");
        string = replaceAll(string, "<br />", "\n");
        string = replaceAll(string, "&rdquo;", "”");
        string = replaceAll(string, "&rarr;", "→");
        string = replaceAll(string, "&ldquo;", "“");
        string = replaceAll(string, "&mdash;", "—");
        string = replaceAll(string, "&times;", "×");
        string = replaceAll(string, "&apos;", "'");
        string = replaceAll(string, "&quot;", "\"");
        string = replaceAll(string, "&lt;", "<");
        string = replaceAll(string, "&gt;", ">");
        string = replaceAll(string, "&nbsp;", " ");
        string = replaceAll(string, "&shy;", "-");
        string = replaceAll(string, "&amp;", "&");
        string = replaceAll(string, "$$", "$");
        return string;
    }

    /**
     * 将超文本中的特殊字符转换成替代符号
     * @param string
     * @return
     */
    public final static String htmlSpecialChars(String string)
    {
        if(string == null || string.equals(""))
            return string;
        string = removeNoReadChar(string);
        string = replaceAll(string, "<br />", "\n");
        string = replaceAll(string, "&#39;", "'");
        string = replaceAll(string, "&apos;", "'");
        string = replaceAll(string, "&quot;", "\"");
        string = replaceAll(string, "&lt;", "<");
        string = replaceAll(string, "&gt;", ">");
        string = replaceAll(string, "&nbsp;", " ");
//        string = replaceAll(string, "&shy;", "-");
        string = replaceAll(string, "&amp;", "&");
        string = replaceAll(string, "$$", "$");

        string = replaceAll(string, "&", "&amp;");
        string = replaceAll(string, "'", "&#39;");
        string = replaceAll(string, "\"", "&quot;");
        string = replaceAll(string, "<", "&lt;");
        string = replaceAll(string, ">", "&gt;");
        string = replaceAll(string, " ", "&nbsp;");
//        string = replaceAll(string, "-", "&shy;");
        string = replaceAll(string, "\r\n", "<br />");
        string = replaceAll(string, "\n", "<br />");
        string = replaceAll(string, "$", "$$");

        return string;
    }
    
    /**
     * 将一段字符串中所给的key全部去掉
     * @param str
     * @param key
     * @return
     */
    public final static String removeAllChars(String str, char[] key)
    {
    	if(stringIsNotNull(str) && key != null && key.length > 0)
    	{
    		char c = 0;
    		StringBuffer strbuf = new StringBuffer();
    		for(int i = 0;i < str.length();i ++)
    		{
    			c = str.charAt(i);
    			if(!isKeyChar(c, key))
    				strbuf.append(c);
    		}
    		return strbuf.toString();
    	}
        return str;
    }
    
    /**
     * 将字符串中所有的空白字符去除
     * @param str
     * @return
     */
    public final static String trimBlankChar(String str)
    {
    	return removeAllChars(str, BlankChar);
    }
    
    /**
     * 将字符串中所有的换行去除
     * @param str
     * @return
     */
    public final static String trimReturnChar(String str)
    {
    	return removeAllChars(str, ReturnChar);
    }
    
    /**
     * 将字符串中所有的空白及换行字符去除
     * @param str
     * @return
     */
    public final static String trimBlankAndReturnChar(String str)
    {
    	return removeAllChars(str, BlankAndReturnChar);
    }
    
    /**
     * 将字符串中的某些字符串去掉
     * @param str
     * @param key
     * @return
     */
    public final static String removeAll(String str, String key)
    {
        return replaceAll(str, key, null);
    }
    
    /**
     * 将字符串中的连续换行去掉
     * @param str
     * @param key
     * @return
     */
    public final static String trimSerialReturn(String str)
    {
    	str = removeAll(str, "\r");
    	return trimSerialChar(str, '\n');
    }
    
    /**
     * 将字符串中的连续某字符去掉
     * @param str
     * @param key
     * @return
     */
    public final static String trimSerialChar(String str, char key)
    {
    	if(key != 0)
    	{
	    	char[] k = new char[1];
	    	k[0] = key;
	    	return trimSerialChars(str, k);
    	}
    	return str;
    }
    
    /**
     * 将字符串中的连续某字符去掉
     * @param str
     * @param key
     * @return
     */
    public final static String trimSerialChars(String str, char[] key)
    {
    	if(stringIsNotNull(str) && key != null && key.length > 0)
    	{
    		char c = 0;
    		StringBuffer strbuf = new StringBuffer();
    		for(int i = 0, bnum = 0;i < str.length();i ++)
    		{
    			c = str.charAt(i);
    			if(!isKeyChar(c, key))
    				bnum = 0;
    			else
    			{
    				bnum ++;
    				if(bnum > 1)
    					continue;
    			}
				strbuf.append(c);
    		}
    		return strbuf.toString();
    	}
        return str;
    }
    
    /**
     * 将字符串中的连续空白去掉
     * @param str
     * @param key
     * @return
     */
    public final static String trimSerialBlankChar(String str)
    {
    	return trimSerialChars(str, BlankChar);
    }
    
    /**
     * 将字符串头尾的换行去掉
     * @param str
     * @return
     */
    public final static String trimHeadTailReturn(String str)
    {
    	return trimHeadTailChar(str, ReturnChar, true, true);
    }
    
    /**
     * 将字符串头尾的换行及空格去掉
     * @param str
     * @return
     */
    public final static String trimHeadTailBlankReturn(String str)
    {
    	return trimHeadTailChar(str, BlankAndReturnChar, true, true);
    }
   
    
    /**
     * 将头尾所指的字符去掉
     * @param str
     * @param key
     * @param head	是否去掉头
     * @param tail	是否去掉尾
     * @return
     */
    public final static String trimHeadTailChar(String str, char[] keys, boolean head, boolean tail)
    {
    	if(stringIsNotNull(str))
    	{
    		int start = 0;
    		int end = str.length();
    		char c = 0;
    		if(head)
    		{
    			boolean haveSame = false;
        		for(int i = 0, j = 0;i < str.length();i ++)
        		{
        			c = str.charAt(i);
        			haveSame = false;
    				for(j = 0;j < keys.length;j ++)
    				{
    					if(c == keys[j])
    					{
    						haveSame = true;
    						break;
    					}
    				}
    				if(!haveSame)
    				{
    					start = i;
    					break;
    				}
        		}
    		}
    		if(tail)
    		{
    			boolean haveSame = false;
        		for(int i = str.length() - 1, j = 0;i >= start;i --)
        		{
        			c = str.charAt(i);
        			haveSame = false;
    				for(j = 0;j < keys.length;j ++)
    				{
    					if(c == keys[j])
    					{
    						haveSame = true;
    						break;
    					}
    				}
    				if(!haveSame)
    				{
    					end = i + 1;
    					break;
    				}
        		}
    		}
    		if(start != end)
    		{
    			if(start != 0 || end != str.length())
    				return str.substring(start, end);
    			return str;
    		}
    		return "";
    	}
    	return null;
    }
    
    /**
     * 将字符串中每行后面的空白字符去除
     * @param str
     * @return
     */
    public final static String trimLineBlankChar(String str, boolean head, boolean tail)
    {
    	if(stringIsNotNull(str))
    	{
    		char c = 0;
    		StringBuffer strbuf = new StringBuffer();
    		StringBuffer lineStrbuf = new StringBuffer();
    		int lastNoBlankIndex = 0;
    		for(int i = 0, j = 0;i < str.length();i ++)
    		{
    			c = str.charAt(i);
    			if(c == '\n' || c == '\r')
    			{
    				if(lineStrbuf.length() > 0)
    				{
    					if(tail)
    					{
	    					if(lastNoBlankIndex > 0)
	        					strbuf.append(lineStrbuf.substring(0, lastNoBlankIndex));
    					}
    					else
    						strbuf.append(lineStrbuf.toString());
    					lineStrbuf.setLength(0);
    				}
    				lastNoBlankIndex = 0;
    				j = 0;
    				strbuf.append(c);
    			}
    			else
				{
    				if(isBlankChar(c))
    				{
    					if(head && lastNoBlankIndex == 0)
    						continue;
    					j ++;
    				}
    				else
    					lastNoBlankIndex = ++ j;
					lineStrbuf.append(c);
				}
    		}
			if(lineStrbuf.length() > 0 && lastNoBlankIndex > 0)
				strbuf.append(lineStrbuf.substring(0, lastNoBlankIndex));
    		return strbuf.toString();
    	}
        return str;
    }
    
    /**
     * 判断所给char是否在key char数组里
     * @param c
     * @param key
     * @return
     */
    public final static boolean isKeyChar(char c, char[] key)
    {
    	if(key != null && key.length > 0)
    	{
    		for(char cc : key)
    		{
    			if(c == cc)
    				return true;
    		}
    	}
    	return false;
    }
    
    /**
     * 判断所给char是否为属于空白类的char
     * @param c
     * @return
     */
    public final static boolean isBlankChar(char c)
    {
    	return c <= ' ';
    }
    
    /**
     * 将字符串中某些字符串替换成其他字符串
     * @param str
     * @param key
     * @param to
     * @return
     */
    public final static String replaceAll(String str, String key, String to)
    {
        if(str != null && key != null
                && !str.equals("")&& !key.equals(""))
        {
            StringBuffer strbuf = new StringBuffer();
            int begin = 0, slen = str.length();
            for(int npos = 0, klen = key.length();
                begin < slen && (npos = str.indexOf(key, begin)) >= begin;
                begin = npos + klen)
            {
                strbuf.append(str.substring(begin, npos));
                if(to != null)
                    strbuf.append(to);
            }
            if(begin == 0)
                return str;
            if(begin < slen)
                strbuf.append(str.substring(begin));
            return strbuf.toString();
        }
        return str;
    }
    
    /**
     * 去掉不正常的字符串
     * @param str
     * @return
     */
    public final static String removeWrongChar(String str)
    {
        if(str != null && str.length() > 0)
        {
            for(int i = 0;i < cannotinputchar.length;i ++)
            {
                str = replaceAll(str, String.valueOf(cannotinputchar[i]), "");
            }
        }
        return str;
    }
    
    /**
     * 将所给字符串中不可显示的字符剔除
     * @param str
     * @return
     */
    public final static String removeNoReadChar(String str)
    {
        if(str != null && str.length() > 0)
        {
            StringBuffer strbuf = new StringBuffer();
            int c = 0;
            for(int i = 0;i < str.length();i ++)
            {
                c = str.charAt(i);
                if(c == 0x09 || c == 0x0A || (c >= 0x20 && c <= 0x80) || (c > 0xFF))
                    strbuf.append((char)c);
            }
            return strbuf.toString();
        }
        return str;
    }
    
    /**
     * 将所给字符串中不可显示的字符剔除(严格处理双字节)
     * @param str
     * @return
     */
    public final static String removeNoReadCharWithWChar(String str)
    {
        if(str != null && str.length() > 0)
        {
            StringBuffer strbuf = new StringBuffer();
            char c = 0;
            for(int i = 0;i < str.length();i ++)
            {
                c = str.charAt(i);
                if(c == 0x09 || c == 0x0A || (c >= 0x20 && c <= 0x80) || (c > 0xFF && isChinese(c)))
                    strbuf.append((char)c);
            }
            return strbuf.toString();
        }
        return str;
    }
    
    /**
     * 判断是否为可识别中文
     * @param c
     * @return
     */
    public final static boolean isChinese(char c)
	{
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
		        || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
		        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
		        || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
		        || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
		        || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
		        || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION)
		{
			return true;
		}
		return false;
	}
    
    /**
	 * 判断字符串是否不为空且长度大于零
	 * 
	 * @param str
	 * @return
	 */
    public final static boolean stringIsNotNull(String str)
    {
        return str != null && !"".equals(str.toString().trim()) && !"null".equalsIgnoreCase(str);
    }

    /**
     * 判断字符串们是否不为空且长度大于零
     * @param string列表
     * @return
     */
    public final static boolean stringIsNotNull(String ... str)
    {
    	boolean ok = false;
    	if(str != null)
    	{
	    	for(String s : str)
	    	{
	    		if(!stringIsNotNull(s))
	    			return false;
	    		else
	    			ok = true;
	    	}
    	}
    	return ok;
    }
    
    /**
     * 判断所给两个字符串是否相同
     * @param str1
     * @param str2
     * @return
     */
    public final static boolean stringIsSame(String str1, String str2)
    {
        return (str1 == null && str2 == null) || (str1 != null && str1.equals(str2));
    }
    
    public final static String isRightPasswordEx(String name, String pwd, int min, int max, String matchs)
    {
        int len = 0;
        if(pwd != null && (len = pwd.length()) >= min && len <= max)
        {
            pwd = pwd.toLowerCase();
            if(name != null)
            {
                name = name.toLowerCase();
                if(name.indexOf(pwd) >= 0 || pwd.indexOf(name) >= 0)
                    return "您的密码与帐号相似度太高";
            }
            if(pwd.matches(matchs))
            {
                int sametimes = 0, uptimes = 0, downtimes = 0;
                char lastc = 0;
                for(int i = 0;i < len;i ++)
                {
                    char c = pwd.charAt(i);
                    if(lastc == c)
                        sametimes ++;
                    if(lastc + 1 == c)
                        uptimes ++;
                    if(lastc - 1 == c)
                        downtimes ++;
                    lastc = c;
                }
                if((sametimes > 0 && sametimes == len - 1)
                        || (uptimes > 0 && uptimes == len - 1)
                        || (downtimes > 0 && downtimes == len - 1))
                    return "您使用的密码连续的字符过多";
                return null;
            }
            return "您使用的字符超出了允许范围";
        }
        return "密码长度应在"+min+"到"+max+"之间";
    }
    
    public final static String isRightWapPassword(String name, String pwd)
    {
        return isRightPasswordEx(name,pwd,6,16,"[0-9a-z]+");
    }
    
    public final static String isRightPassword(String name, String pwd)
    {
        return isRightPasswordEx(name,pwd,6,32,"[0-9a-z]+");
    }
    


    /**
     * 把url中的参数值进行编码
     * @param par
     * @return
     */
    public final static String encodePar(String par)
    {
        return encodePar(par, null);
    }
    
    /**
     * 把url中的参数值进行编码
     * @param par
     * @return
     */
    public final static String encodePar(String par, String encode)
    {
        if(par != null && !par.equals(""))
        {
        	if(!stringIsNotNull(encode))
        		encode = "UTF-8";
            try
            {
                par = java.net.URLEncoder.encode(par, encode);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return par;
    }
    
    /**
     * 解码url中的参数值
     * @param par
     * @return
     */
    public final static String decodePar(String par)
    {
        return decodePar(par, null);
    }
    
    /**
     * 解码url中的参数值
     * @param par
     * @return
     */
    public final static String decodePar(String par, String encode)
    {
        if(par != null && !par.equals(""))
        {
        	if(!stringIsNotNull(encode))
        		encode = "UTF-8";
            try
            {
                par = java.net.URLDecoder.decode(par, encode);
            }
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
        }
        return par;
    }

    /**
     * 将url中的get参数值进行encode
     * @param url
     * @return
     */
	public static String encodeParForUrl(String url)
	{
		if(url != null)
		{
			int npos = url.indexOf('?');
			if(npos > 0)
			{
				String[] pars = url.substring(npos+1).split("\\&");
				StringBuffer sbuf = new StringBuffer();
				int npos2 = 0;
				for(String s : pars)
				{
					npos2 = s.indexOf('=');
					if(npos2 > 0)
						sbuf.append("&").append(s.substring(0, npos2+1)).append(encodePar(decodePar(s.substring(npos2 + 1))));
					else
						sbuf.append("&").append(s);
				}
				url = url.substring(0, npos+1) + sbuf.substring(1);
			}
		}
		return url;
	}
    
    /**
     * 修改用户输入的内容，有些符号是危险的，需要去掉
     * @param str
     * @return
     */
    public final static String fixScanInputString(String str)
    {
        if(str != null)
        {
            str = removeAll(str, "'");
            str = removeAll(str, "\"");
            str = removeAll(str, "\n");
            str = removeAll(str, "\r");
            str = removeAll(str, " ");
            str = removeAll(str, "\t");
        }
        return str;
    }
    
    /**
     * 查看用户的输入内容是否正确，有些特殊字符不能输入，长度是否合理
     * @param str
     * @return
     */
    public final static boolean inputIsRight(String str)
    {
        if(str != null && str.length() > 1 && str.length() < 32)
        {
            return stringIsRight(str);
        }
        return false;
    }
    
    /**
     * 查看用户的输入内容是否正确，有些特殊字符不能输入，不限制长度
     * @param str
     * @return
     */
    public final static boolean stringIsRight(String str)
    {
        if(str != null && !"".equals(str))
        {
            for(int i = 0;i < cannotinputchar.length;i ++)
            {
                if(str.indexOf(cannotinputchar[i]) >= 0)
                    return false;
            }
            return true;
        }
        return false;
    }
    
    /**
     * 将用户输入的换行都去掉
     * @param inputstr
     * @return
     */
    public final static String fixInput(String inputstr)
    {
        if(inputIsRight(inputstr))
        {
            inputstr = inputstr.trim();
            inputstr = replaceAll(inputstr, "\r\n", "");
            inputstr = replaceAll(inputstr, "\n", "");
            return inputstr.trim();
        }
        return null;
    }
    
    /**
     * 将字符串前后空格去掉，如果长度为0，则返回null
     * @param value
     * @return
     */
    public final static String fixValue(String value)
    {
        if(value != null)
        {
            value = value.trim();
            if(!"".equals(value))
                return value;
        }
        return null;
    }
    
    /**
     * 处理请求中带script的情况
     * @param scriptString
     * @return
     */
    public final static String removeRequestScript(String scriptString)
    {
    	if(scriptString != null)
    	{
    		int n = scriptString.toLowerCase().indexOf("javascript");
    		if(n >= 0)
    			scriptString = scriptString.substring(0, n) + scriptString.substring(n + 10);
    		return replaceAll(replaceAll(scriptString, "<", "&lt;"), ">", "&gt;");
    	}
    	return null;
    }
    
    /**
     * 
     * @param str
     * @return
     */
    public final static String fixLogString(String str)
    {
        return (str == null || str.equals("")) ? "-" : str;
    }
    

    
    /**
     * 判断一个字符串里包含相同的字符的百分比
     * @param s
     * @return          0-->1
     */
    public final static double sameInPercent(String s)
    {
        if(s != null)
        {
            int len = s.length();
            if(len > 6)
            {
                int maxlianxu = 0;
                char[] clist = new char[len];
                int[] numlist = new int[len];
                char c = 0, lastc = 0;
                for(int i = 0,j = 0,lianxu = 0;i < len;i ++)
                {
                    c = s.charAt(i);
                    if(lastc == c)
                    {
                        lianxu ++;
                        if(maxlianxu < lianxu)
                            maxlianxu = lianxu;
                    }
                    else
                        lianxu = 0;
                    for(j = 0;j < len;j ++)
                    {
                        if(clist[j] == 0)
                        {
                            clist[j] = c;
                            numlist[j] ++;
                            break;
                        }
                        else if(clist[j] == c)
                        {
                            numlist[j] ++;
                            break;
                        }
                    }
                    lastc = c;
                }
                if(maxlianxu > 10)
                    return 1;
                int max = 0;
                for(int i = 0;i < len;i ++)
                {
                    if(numlist[i] == 0)
                        break;
                    else if(max < numlist[i])
                        max = numlist[i];
                }
                return (double)max / (double)len;
            }
        }
        return 0;
    }

    /**
     * 求两个字符串的相似度，返回相似度百分比
     * @param s
     * @param t
     * @return      0-->1
     */
    public final static double resultInPercent(String s, String t)
    {
        if(s == null || t == null)
            return 0;
        if(s.equals(t))
            return 1;
        int n = s.length(), m = t.length();
        if (n == 0 || m == 0)
            return 0;
        
        int res = 0;
        {
            int[][] d = new int[n + 1][];
            char sc = 0;
            for (int i = 0, j = 0;i <= n; i++)
            {
                d[i] = new int[m + 1];
                d[i][0] = i;
                if(i == 0)
                {
                    for (j = 0;j <= m;j ++)
                        d[0][j] = j;
                }
                else
                {
                    sc = s.charAt(i - 1);
                    for (j = 1; j <= m; j++)
                    {
                        
                        d[i][j] = Math.min(Math.min(d[i - 1][j] + 1, d[i][j - 1] + 1)
                                , d[i-1][j-1] + (sc == t.charAt(j - 1) ? 0 : 1));
                    }
                }
            }
            res = d[n][m];
        }
        
        return (1 - res / (double)Math.max(n, m));
    }
    
    /**
     * 清理用户提交帖子信息中包含的隐私
     * @param msg
     * @return
     */
    public final static String cleanWrongInfomation(String msg)
    {
        if(msg != null && msg.length() > 0)
        {
            msg = msg.replaceAll("1[3-5][0-9]{5}[0-9]{4}", "[隐私:手机号]");
//            msg = msg.replaceAll("[0-9][0-9][0-9][0-9][0-9][0-9][0-9]+", "[隐私:号码]");
            msg = msg.replaceAll("http\\://[/\\@\\:\\.0-9a-zA-Z]+", "[隐私:网址]");
//            msg = msg.replaceAll("[\\.0-9a-zA-Z]+\\@[\\.0-9a-zA-Z]+", "[隐私:email]");
        }
        return msg;
    }
    
    /**
     * 判断昵称是否合理
     * @param name
     * @return
     */
    public final static boolean nickNameIsRight(String name)
    {
        if(inputIsRight(name))
        {
            name = name.toLowerCase();
            return !name.matches(".*社.*区.*")
                && !name.matches(".*优.*百.*")
                && !name.matches(".*客.*服.*")
                && !name.matches(".*版.*主.*")
                && name.indexOf("gm") < 0
                && name.indexOf("manage") < 0
                && name.indexOf("system") < 0
                ;
        }
        return false;
    }
    
    /**
     * 是否与前一个字符串相近
     * @param lasttxt   前一个字符串
     * @param newtxt    新字符串
     * @return          true：相近     false：不相近
     */
    public final static boolean isLikeLastString(String lasttxt, String newtxt)
    {
        if(newtxt == null || newtxt.equals(""))
            return true;
        if(newtxt.equals(lasttxt))
            return true;
        if(sameInPercent(newtxt) > 0.45)
            return true;
        if(lasttxt == null || lasttxt.equals(""))
            return false;
        return resultInPercent(lasttxt, newtxt) > 0.75;
    }
    
    /**
     * 基本功能：判断标记是否存在
     * @param input
     * 
     * @return boolean
     */
    public final static boolean hasSpecialChars(String input) {
        boolean flag = false;

        if ((input != null) && (input.length() > 0)) {
            char c;
            for (int i = 0; i <= input.length() - 1; i++) {
                c = input.charAt(i);
                switch (c) {
                case '>':
                    flag = true;
                    break;
                case '<':
                    flag = true;
                    break;
                case '"':
                    flag = true;
                    break;
                case '&':
                    flag = true;
                    break;
                }
            }
        }
        return flag;

    }

    /**
     * 基本功能：替换标记以正常显示
     * @param input
     * @return String
     */
    public final static String replaceTag(String input) {
        if (!hasSpecialChars(input)) {
            return input;
        }
        StringBuffer filtered = new StringBuffer(input.length());
        char c;
        for (int i = 0; i <= input.length() - 1; i++) {

            c = input.charAt(i);
            switch (c) {
            case '<':
                filtered.append("&lt;");
                break;
            case '>':
                filtered.append("&gt;");
                break;
            case '"':
                filtered.append("&quot;");
                break;
            case '&':
                filtered.append("&amp;");
                break;
            default:
                filtered.append(c);
            }
        }
        return (filtered.toString());
    }
    
    public final static String replaceScriptTag(String str) {
        if (str.indexOf("<script") < 0) {
            return str;
        } else {
            return str.replaceAll("<script.+</script>", "");
        }
    }
    
    public final static boolean isPointNumberString(String str)
    {
    	if(stringIsNotNull(str) && str.length() < 20)
    	{
    		char c = 0;
    		int dashTimes = 0;
    		int pointTimes = 0;
    		for(int i = 0;i < str.length();i ++)
    		{
    			c = str.charAt(i);
    			if((c < 0x30 || c > 0x39) && c != '.' && c != '-')
    				return false;
    			if(c == '.')
    				pointTimes ++;
    			else if(c == '-')
    				dashTimes ++;
    		}
    		return dashTimes < 2 && pointTimes < 2;
    	}
    	return false;
    }
    
    public final static boolean isNumberString(String str)
    {
    	if(stringIsNotNull(str) && str.length() < 20)
    	{
    		char c = 0;
    		int dashTimes = 0;
    		for(int i = 0;i < str.length();i ++)
    		{
    			c = str.charAt(i);
    			if((c < 0x30 || c > 0x39) && c != '-')
    				return false;
    			if(c == '-')
    				dashTimes ++;
    		}
    		return dashTimes < 2;
    	}
    	return false;
    }
    
    public final static String pickStringFromString(String str, int begin, String keyFrom, String keyTo)
    {
    	if(str != null)
    	{
    		if(begin < 0)
    			begin = 0;
    		int npos = begin;
    		if(keyFrom != null)
    			npos = str.indexOf(keyFrom, begin);
    		if(npos >= begin)
    		{
        		if(keyFrom != null)
        			npos += keyFrom.length();
        		if(keyTo != null)
        		{
	    			int npos2 = str.indexOf(keyTo, npos);
	    			if(npos2 >= npos)
	        			return str.substring(npos, npos2);
        		}
        		else
        			return str.substring(npos);
    		}
    	}
    	return null;
    }
    
    /**
     * 替换异类汉字
     * @param source
     * @param reStr 替换字符，默认*
     * @return
     */
    public static String filterSpecialChar(String source,String reStr) {  
    	if(reStr==null)
    		reStr="*";
        if(stringIsNotNull(source))
        {
            Pattern emoji = Pattern.compile ("[\ud840\udc00-\ud869\uded6]",Pattern.UNICODE_CASE | Pattern . CASE_INSENSITIVE ) ;
            Matcher emojiMatcher = emoji.matcher(source);
            if ( emojiMatcher.find()) 
            {
                source = emojiMatcher.replaceAll(reStr);
                return source ; 
            }
        return source;
       }
       return source;  
    }
    
    /**
     * 替换异类汉字
     * @param source
     * @return
     */
    public static String filterSpecialChar(String source){
    	return filterSpecialChar(source, null);
    }
    
    public static void main(String[] args)
    {
    	System.out.println(removeNoReadCharWithWChar("您好"));
    }

}
