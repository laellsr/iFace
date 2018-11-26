package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		Scanner input = new Scanner(System.in);
		ArrayList<Register> accounts = new ArrayList<Register>();
		boolean active = true; int choise;
		while(active)
		{
			System.out.printf("Bem-vindo ao iFace!\n");
			System.out.printf("[1] Login\n[2] Cadastre-se\n[3] Sair\n=> ");
			
			choise = input.nextInt(); input.nextLine();
			
			switch(choise)
			{
				case 1:
					Login new_login = new Login();
					int index = new_login.checkLogin(accounts);
					if(index>-1)
						new_login.menuLogin(accounts, index);
					break;
				
				case 2:
					Register new_register = new Register();
					new_register.setRegister(accounts, new_register);
					break;
				
				default:
					active = false;
					break;
			}
		}
		System.out.printf("\nUsuario(s) cadastrado(s): [%s]\n", accounts.size());
		for(int i = 0; i < accounts.size(); i++)
			System.out.println(accounts.get(i).username);
		input.close();
	}

}
