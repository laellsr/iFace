package iFace;

import java.util.ArrayList;

public class Search
{
	public int searchIndex(ArrayList<Register> accounts, String current_name)
	{
		for(int i = 0; i < accounts.size(); i++)
		{
			if(current_name.intern() == (accounts.get(i).getUsername()).intern())
			{
				return i;
			}
		}
		return -1;
	}
}
