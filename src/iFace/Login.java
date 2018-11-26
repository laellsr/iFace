package iFace;

import java.util.ArrayList;
import java.util.Scanner;

public class Login
{
	public int checkLogin(ArrayList<Register> accounts)
	{
		String username, password; boolean check = false; int index = 0;
		Scanner input = new Scanner(System.in); String okay;
		if(accounts.isEmpty())
		{
			System.out.printf("\nSem cadastros.\n\n");
			return -1;
		}
		System.out.printf("\nNome de usuario:\n=> ");
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
			System.out.printf("Nome de usuario ou senha incorretos\n");
			System.out.printf("[1] Continue\n=> ");
			okay = input.nextLine();
			System.out.printf("\n");
			return -1;
		}
		else
			return index;
	}
	
	public void menuLogin(ArrayList<Register> accounts, int index)
	{
		System.out.printf("\n");
		boolean active = true; int user_choise;
		Scanner input = new Scanner(System.in);
	//--------------------------------------------------
		/* Checar se tem convites */
		int decision; int index2; 
		if(!accounts.get(index).new_profile.invites.isEmpty())
		{
			for(int i = 0; i < accounts.get(index).new_profile.invites.size(); i++)
			{
				System.out.printf("Voce tem uma solicitacao de amizade feita por %s\n"
						+ "Deseja aceitar? [1] Sim [2] Nao\n=> ", accounts.get(index).new_profile.invites.get(i).getUsername());
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
						System.out.printf("%s agora é seu amigo!\n", accounts.get(index).new_profile.invites.get(i).getUsername());
						System.out.printf("[1] Continue\n=> ");
						String okay;
						okay = input.nextLine();
						System.out.printf("\n");
						break;
						
					case 2:
						System.out.printf("Solicitação de amizade recusada!\n");
						System.out.printf("[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
						break;
						
				}	
			}
		}
		accounts.get(index).new_profile.invites.clear();
		
		
		while(active)
		{
			System.out.printf("\nOla, %s, este e o seu Perfil\n"
					+ "%s, %s, \"%s\"\n"
					+ "%s, %s - %s\n"
					+ "%s\n%s\n\n", accounts.get(index).new_profile.getName(), accounts.get(index).new_profile.getGender(),
					accounts.get(index).new_profile.getAge(), accounts.get(index).new_profile.getPhrase(), accounts.get(index).new_profile.getCity(),
					accounts.get(index).new_profile.getState(), accounts.get(index).new_profile.getCountry(),
					accounts.get(index).new_profile.getMore1(), accounts.get(index).new_profile.getMore2());
			System.out.printf("Amigos [%s]:", accounts.get(index).new_profile.friends.size());
			for (int i = 0; i < accounts.get(index).new_profile.friends.size(); i++)
			{
				System.out.printf(" %s ", accounts.get(index).new_profile.friends.get(i).username);
			}
			System.out.printf("\n");
			System.out.printf("Caixa de entrada [%s].\n", accounts.get(index).new_profile.input_box.size());
			System.out.printf("\n[1] Editar perfil\n[2] Adicionar amigo\n[3] Enviar mensagem\n[4] Ler mensagens da caixa de entrada\n"
					+ "[5] Criar comunidade\n[6] Entrar em comunidade\n[7] Sua(s) comunidade(s)\n[8] Enviar mensagem para comunidade\n"
					+ "[9] Recuperar dados\n[10] Excluir conta\n"
					+ "[11] Logoff\n=> ");
			
			user_choise = input.nextInt();
			input.nextLine();
			
			switch(user_choise)
			{
				case 1:
					accounts.get(index).new_profile.setProfile();
					break;
				case 2:
					Friends friend = new Friends();
					System.out.printf("\nDigite o nome de usuario do amigo:\n=>");
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
							System.out.printf("\nUsuario nao existe.\n");
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
							System.out.printf("\nUsuario ja foi adicionado.\n");
							break;
						}
					}
					
					Invite new_invite = new Invite();
					new_invite.setName(accounts.get(index).new_profile.getName());
					new_invite.setUsername(accounts.get(index).getUsername());
					accounts.get(friend_index).new_profile.invites.add(new_invite);
					
					System.out.printf("\nO convite de amizade foi enviado para %s!\n\n", username);
					break;
				case 3:
					System.out.printf("Enviar mensagem para:\n=> ");
					username = input.nextLine();
					Search new_search = new Search();
					int username_index = new_search.searchIndex(accounts, username);
					if(username_index == -1)
					{
						System.out.printf("Usuario nao existe.\n"); break;
					}
					InputBox new_message = new InputBox();
					new_message.to_username = username;
					new_message.from_username = accounts.get(index).getUsername();
					new_message.getMessage();
					accounts.get(username_index).new_profile.input_box.add(new_message);
					System.out.printf("Mensagem enviada para %s\n", username);
					System.out.printf("[1] Continue\n=> ");
					String okay;
					okay = input.nextLine();
					System.out.printf("\n");
					break;
				case 4:
					if(!accounts.get(index).new_profile.input_box.isEmpty())
					{
						for(int i = 0; i < accounts.get(index).new_profile.input_box.size(); i++)
						{
							System.out.printf("Mensagem de %s:\n\"%s\"\n"
									+ "[1] Continue\n=> ", accounts.get(index).new_profile.input_box.get(i).from_username,
									accounts.get(index).new_profile.input_box.get(i).message);
							decision = input.nextInt(); input.nextLine();	
						}
					}
					else
					{
						System.out.printf("Sem mensagens!\n");
						System.out.printf("[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
					}
					break;
				case 5:
					Community new_community = new Community();
					new_community.setCommunity(accounts.get(index).getUsername());
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				case 11:
					active = false;
					break;
			}
		}
	}
}
