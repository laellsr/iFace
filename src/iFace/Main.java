package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Main
{

	public static void main(String[] args)
	{
		ArrayList <SingUp> accounts = new ArrayList<SingUp>();
		Scanner input = new Scanner(System.in);
		int choise, index=0; String username = new String(), password = new String();
		boolean active = true;
		
		while(active)
		{
			System.out.printf("Bem-vindo ao iFace!\n");
			System.out.printf("[1] Login\n[2] Cadastre-se\n[3] Sair\n=> ");
			choise = input.nextInt(); input.nextLine();
			switch(choise)
			{
				case 1:
					if(accounts.isEmpty())
					{
						System.out.printf("\nNão há cadastros\n\n");
						break;
					}
					
					boolean check = false;
					
					System.out.printf("\nNome de usuário:\n=> ");
					username = input.nextLine();
					System.out.printf("\nSenha:\n=> ");
					password = input.nextLine();
					
					for(int i = 0; i < accounts.size(); i++)
					{
						if(username.intern() == (accounts.get(i).getUsername()).intern()
								&& password.intern() == (accounts.get(i).getPassword()).intern())
							{
								check = true; index = i; break;
							}
					}
					
					if(check==false)
					{
						System.out.printf("Nome de usuário ou senha incorretos\n\n");
						break;
					}
					
					boolean case1 = true;
					
					while(case1)
					{
						System.out.printf("\nOlá, %s\n, este é o seu Perfil\n", accounts.get(index).getName());
					}
		
				
				case 2:
					boolean case2 = true;
					System.out.printf("\nDigite seu nome de usuário:\n=> ");
		
					username = input.nextLine();
					if(!accounts.isEmpty())
					{
						for(int i = 0; i < accounts.size(); i++)
						{
							if(username.intern() == (accounts.get(i).getUsername()).intern())
								{
									case2=false; System.out.printf("\nEsse usuário já existe.\n\n"); break;
								}
						}
						if(case2==false)
							break;
					}
					SingUp new_user = new SingUp();
					new_user.newUser(username);
					accounts.add(new_user);
					break;
					
				case 3:
					active = false;
					break;
			}
		}

		input.close();
	}

}
