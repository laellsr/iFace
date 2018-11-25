package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Login
{
	public int checkLogin(ArrayList<Register> accounts)
	{
		String username, password; boolean check = false; int index = 0;
		Scanner input = new Scanner(System.in);
		if(accounts.isEmpty())
		{
			System.out.printf("\nNão há cadastros.\n\n");
			return -1;
		}
		System.out.printf("\nNome de usuário:\n=> ");
		username = input.nextLine();
		System.out.printf("Senha:\n=> ");
		password = input.nextLine();
		
		for(int i = 0; i < accounts.size(); i++)
		{
			if(username.intern() == (accounts.get(i).getUsername()).intern()
					&& password.intern() == (accounts.get(i).getPassword()).intern())
				{
					check = true; index = i; break;
				}
		}
		if(!check)
		{
			System.out.printf("Nome de usuário ou senha incorretos\n\n");
			return -1;
		}
		else
			return index;
	}
	
	public void menuLogin(ArrayList<Register> accounts, int index)
	{
		boolean active = true; int user_choise;
		Scanner input = new Scanner(System.in);
	//--------------------------------------------------
		/* Checar se tem convite ou mensagem*/
		/* convites */
		int decision; int index2; 
		if(!accounts.get(index).new_profile.invites.isEmpty())
		{
			for(int i = 0; i < accounts.get(index).new_profile.invites.size(); i++)
			{
				System.out.printf("Você tem um convite de amizade feito por %s\n"
						+ "Deseja aceitar? [1] Sim [2] Não\n=> ", accounts.get(index).new_profile.invites.get(i).getUsername());
				decision = input.nextInt(); input.nextLine();
				switch(decision)
				{
					case 1:
						Friends new_friend = new Friends();
						new_friend.name = accounts.get(index).new_profile.invites.get(i).getName();
						new_friend.username = accounts.get(index).new_profile.invites.get(i).getUsername();
						accounts.get(index).new_profile.friends.add(new_friend);
						
						Friends new_friend2 = new Friends();
						Search search = new Search();
						index2 = search.searchIndex(accounts, accounts.get(index).new_profile.invites.get(i).getUsername());
						new_friend2.name = accounts.get(index).new_profile.getName();
						new_friend2.username = accounts.get(index).getUsername();
						accounts.get(index2).new_profile.friends.add(new_friend2);
						break;
						
					case 2:
						break;
						
				}	
			}
		}
		accounts.get(index).new_profile.invites.clear();
		/* mensagens */
		
		
		while(active)
		{
			System.out.printf("\nOlá, %s, este é o seu Perfil\n"
					+ "%s, %s, \"%s\"\n"
					+ "%s, %s - %s\n"
					+ "%s\n%s\n\n", accounts.get(index).new_profile.getName(), accounts.get(index).new_profile.getGender(),
					accounts.get(index).new_profile.getAge(), accounts.get(index).new_profile.getPhrase(), accounts.get(index).new_profile.getCity(),
					accounts.get(index).new_profile.getState(), accounts.get(index).new_profile.getCountry(),
					accounts.get(index).new_profile.getMore1(), accounts.get(index).new_profile.getMore2());
			System.out.printf("Seus amigos são %s:", accounts.get(index).new_profile.friends.size());
			for (int i = 0; i < accounts.get(index).new_profile.friends.size(); i++)
			{
				System.out.printf(" %s ", accounts.get(index).new_profile.friends.get(i).username);
			}
			System.out.printf("\n");
			System.out.printf("\n[1] Editar perfil\n[2] Adicionar amigo\n[3] Enviar mensagem\n"
					+ "[4] Criar comunidade\n[5] Entrar em comunidade\n[6] Recuperar dados\n[7] Excluir conta\n"
					+ "[8] Logoff\n=> ");
			
			user_choise = input.nextInt();
			input.nextLine();
			
			switch(user_choise)
			{
				case 1:
					accounts.get(index).new_profile.setProfile();
					break;
				case 2:
					Friends friend = new Friends();
					System.out.printf("\nDigite o nome de usuário do amigo:\n=>");
					String username = input.nextLine(); boolean check = false;  int friend_index=0;
					if(!accounts.isEmpty())
					{
						for(int i = 0; i < accounts.size(); i++)
						{
							if(username.intern() == (accounts.get(i).getUsername()).intern())
							{
								/* usuario existe */
								friend_index = i; check = true; break;
							}
						}
						if(!check)
						{
							System.out.printf("\nUsuário não existe.\n");
							break;
						}
					}
					if (!accounts.get(index).new_profile.friends.isEmpty())
					{
						for(int i=0; i < accounts.get(index).new_profile.friends.size(); i++)
						{
							if(username.intern() == (accounts.get(index).new_profile.friends.get(i).username).intern())
							{
								check = false; break;
							}
						}
						if(!check)
						{
							System.out.printf("\nUsuário já foi adicionado.\n");
							break;
						}
					}
					
					Invite new_invite = new Invite();
					new_invite.setName(accounts.get(index).new_profile.getName());
					new_invite.setUsername(accounts.get(index).getUsername());
					accounts.get(friend_index).new_profile.invites.add(new_invite);
					
					System.out.printf("\nO convite de amizade foi enviado!\n\n");
					break;
				case 3:
					break;
				case 4:
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					active = false;
					break;
			}
		}
	}
}
