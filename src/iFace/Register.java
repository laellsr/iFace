package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Register
{
	Profile new_profile = new Profile();
	String username, password;
	Scanner input = new Scanner(System.in);
	
	public void setRegister(ArrayList<Register> accounts, Register new_register)
	{
		String okay;
		setUsername();
		if(!accounts.isEmpty())
		{
			for(int i = 0; i < accounts.size(); i++)
			{
				if(username.intern() == (accounts.get(i).getUsername()).intern())
				{
					System.out.printf("\nEsse usuario ja existe.\n[1] Continue\n=> ");
					okay = input.nextLine();
					System.out.printf("\n");
					return;
				}
			}
		}
		setPassword();
		new_register.new_profile.setName();
		accounts.add(new_register);
		System.out.printf("\nVoce foi cadastrado no iFace!\n\n");
		
	}
	
	public String getUsername()
	{
		return username;
	}
	
	public void setUsername()
	{
		System.out.printf("\nDigite seu novo nome de usuario:\n=> ");
		username = input.nextLine();
	}
	
	public String getPassword()
	{
		return password;
	}
	
	public void setPassword()
	{
		System.out.printf("Digite sua nova senha:\n=> ");
		password = input.nextLine();
	}
}
