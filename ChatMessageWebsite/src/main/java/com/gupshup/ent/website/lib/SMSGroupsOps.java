package com.gupshup.ent.website.lib;

import java.util.ArrayList;
import java.util.List;

public class SMSGroupsOps
{
	private static List<String> groupList = new ArrayList<String>();

	public static void setGroupList(String group)
	{
		groupList.add(group);
	}

	public static List<String> getGoupList()
	{
		return groupList;
	}

	public static int getSize()
	{
		return groupList.size();
	}
}
