package com.czw.common.tools;

import java.util.HashSet;
import java.util.Set;


public class IdCard
{
    private static final Set<Integer> IdentityCardIdFront = initIdentityCardIdFront();
    
    private static Set<Integer> initIdentityCardIdFront()
    {
    	int[] IdCardIdArray = {11,12,13,14,15,21,22,23,31,32,33,34,35,36,37,41,42,43,44,45,46,50,51,52,53,54,61,62,63,64,65,71,81,82,91};
    	Set<Integer> s = new HashSet<Integer>();
    	for(int i = 0;i < IdCardIdArray.length;i ++)
    		s.add(IdCardIdArray[i]);
    	return s;
    }
    
    public static boolean isRightIdentityCardName(String name)
    {
    	if(name != null && name.length() >= 2 && name.length() <= 8)
    	{
    		char c = 0;
    		for(int i = 0;i < name.length();i ++)
    		{
    			c = name.charAt(i);
    			if(c >= 0 && c < 0x80)
    				return false;
    		}
    		return true;
    	}
    	return false;
    }
    
    public static int getIdentityCardNumSex(String id)
    {
    	if(isRightIdentityCardId(id))
    	{
    		int n = -1;
    		if(id.length() == 15)
    			n = Tools.toInt(id.substring(14));
    		else if(id.length() == 18)
    			n = Tools.toInt(id.substring(16, 17));
    		if(n >= 0)
    			return n % 2;
    	}
    	return -1;
    }
    
    public static boolean isRightIdentityCardId(String id)
    {
    	if(id != null)
    	{
    		if(id.length() == 15)
    		{
    			//	11 0101 77 02 15 401
    			int n = 0;
    			
    			//地区
    			if(!IdentityCardIdFront.contains(Tools.toInt(id.substring(0,2))))
    				return false;
    			//子地区
    			if(!Tools.isNumberString(id.substring(2,6)))
    				return false;
    			//年后2位
    			if(!Tools.isNumberString(id.substring(6,8)))
    				return false;
    			//月
    			n = Tools.toInt(id.substring(8,10));
    			if(n < 1 || n > 12)
    				return false;
    			//日
    			n = Tools.toInt(id.substring(10,12));
    			if(n < 1 || n > 31)
    				return false;
    			//后3位
    			if(!Tools.isNumberString(id.substring(12,15)))
    				return false;
    			return true;
    		}
    		else if(id.length() == 18)
    		{
    			id = id.toUpperCase();
    			//	11 0101 1977 02 15 401 2
    			int n = 0;
    			
    			//地区
    			if(!IdentityCardIdFront.contains(Tools.toInt(id.substring(0,2))))
    				return false;
    			//子地区
    			if(!Tools.isNumberString(id.substring(2,6)))
    				return false;
    			//年前2位
    			n = Tools.toInt(id.substring(6,8));
    			if(n < 19 || n > 20)
    				return false;
    			//年后2位
    			if(!Tools.isNumberString(id.substring(8,10)))
    				return false;
    			//月
    			n = Tools.toInt(id.substring(10,12));
    			if(n < 1 || n > 12)
    				return false;
    			//日
    			n = Tools.toInt(id.substring(12,14));
    			if(n < 1 || n > 31)
    				return false;
    			//后3位
    			if(!Tools.isNumberString(id.substring(14,17)))
    				return false;
    			if(id.charAt(17) != getIdentityCardVerify(id))
    				return false;
    			return true;
    		}
    	}
    	return false;
    }
    
    private static char[] IdentityCardIdVerify = { '1','0','X','9','8','7','6','5','4','3','2' };
    private static int[] IdentityCardIdVerifyKey = {7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2};
    private static char getIdentityCardVerify(String id)
    {    	
		//计算校验位
    	int s = 0;
    	for(int i = 0;i < 17;i ++)
    	{
    		s += (id.charAt(i)&0x0F) * IdentityCardIdVerifyKey[i];
    	}
		return IdentityCardIdVerify[s % 11];
    }
}
