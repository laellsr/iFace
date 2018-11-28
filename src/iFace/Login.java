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
	
	public void menuLogin(ArrayList<Register> accounts, int index, ArrayList<Register> backups)
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
						System.out.printf("%s agora e seu amigo!\n", accounts.get(index).new_profile.invites.get(i).getUsername());
						System.out.printf("[1] Continue\n=> ");
						String okay;
						okay = input.nextLine();
						System.out.printf("\n");
						break;
						
					case 2:
						System.out.printf("Solicitacao de amizade recusada!\n");
						System.out.printf("[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
						break;
						
				}	
			}
		}
		accounts.get(index).new_profile.invites.clear();
		///////
		///////
		/* convites de comunidades */
		for(int i = 0; i < accounts.get(index).new_profile.communities.size(); i++)
		{
			for(int j = 0; j < accounts.get(index).new_profile.communities.get(i).invites.size(); j++)
			{
				System.out.printf("%s esta pedindo permissao para participar "
						+ "da sua comunidade \"%s\"\n[1] Aceitar [2] Recusar\n=> ",
						accounts.get(index).new_profile.communities.get(i).invites.get(j).name,
						accounts.get(i).new_profile.communities.get(i).name);
				decision = input.nextInt(); input.nextLine();
				if(decision==1)
				{
					Friends new_member = new Friends();
					new_member.username = accounts.get(index).new_profile.communities.get(i).invites.get(j).username;
					new_member.name = accounts.get(index).new_profile.communities.get(i).invites.get(j).name;
					accounts.get(index).new_profile.communities.get(i).members.add(new_member);
					System.out.printf("Adicionado(a) aos membros da sua comunidade!\n[1] Continue\n=> ");
					String okay = input.nextLine();
					System.out.printf("\n");
					
				}
			}
			accounts.get(index).new_profile.communities.get(i).invites.clear();
		}
		
		/* Inicio do Menu */
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
					+ "[5] Criar comunidade\n[6] Entrar em comunidade\n[7] Suas comunidades, mensagens e membros.\n[8] Enviar mensagem para comunidade\n"
					+ "[9] Recuperar dados\n[10] Excluir conta\n"
					+ "[11] Salvar dados (backup) e Logoff\n=> ");
			
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
							okay = input.nextLine();
							System.out.printf("\n");	
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
					accounts.get(index).new_profile.communities.add(new_community);
					Friends new_friend2 = new Friends();
					new_friend2.username = accounts.get(index).username;
					new_friend2.name = accounts.get(index).new_profile.getName();
					accounts.get(index).new_profile.communities.get(0).members.add(new_friend2);
					System.out.printf("Comunidade criada!\n");
					System.out.printf("[1] Continue\n=> ");
					okay = input.nextLine();
					System.out.printf("\n");
					break;
				case 6:
					decision = 0;
					System.out.printf("Comunidades do iFace:\n\n");
					for(int i = 0; i < accounts.size(); i++)
					{
						if(accounts.get(i).username.intern() != accounts.get(index).username.intern()
								&& !accounts.get(i).new_profile.communities.isEmpty())
						{
							for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
							{
								System.out.printf("Admin: %s\nNome: %s\nDescricao: %s\n\n",
										accounts.get(i).new_profile.communities.get(j).admin,
										accounts.get(i).new_profile.communities.get(j).name,
										accounts.get(i).new_profile.communities.get(j).theme);
								decision = 1;
							}							
						}
					}
					
					if(decision == 1)
					{
						System.out.printf("Deseja entrar em alguma?\n[1] Sim [2] Nao\n=> ");
						decision = input.nextInt(); input.nextLine();						
					}
					
					if(decision == 1)
					{
						System.out.printf("Digite o nome:\n=> ");
						okay = input.nextLine();
						for(int i = 0; i < accounts.size(); i++)
						{
							if(accounts.get(i).username.intern() != accounts.get(index).username.intern()
									&& !accounts.get(i).new_profile.communities.isEmpty())
							{
								for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
								{
									if(okay.intern() == accounts.get(i).new_profile.communities.get(j).name.intern())
									{
										for(int k = 0; k < accounts.get(i).new_profile.communities.get(j).members.size(); k++)
										{
											if(accounts.get(index).username.intern() == accounts.get(i).new_profile.communities.get(j).members.get(k).username.intern())
											{
												decision = 0; break;
											}
										}
										if(decision == 0)
											break;
										Invite invite = new Invite();
										invite.username = accounts.get(index).username;
										invite.name = accounts.get(index).new_profile.getName();
										accounts.get(i).new_profile.communities.get(j).invites.add(invite);
										System.out.printf("A solicitacao foi enviada para %s\n\n", accounts.get(i).new_profile.communities.get(j).admin);
										System.out.printf("[1] Continue\n=> ");
										okay = input.nextLine();
										System.out.printf("\n"); decision = 0;
										break;
									}
								}
							}
							if(decision==0)
								break;
						}
					}
					else
					{
						System.out.printf("Comunidade nao foi encontrada ou voce ja faz parte.\n");
						System.out.printf("[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
					}
					
					break;
				case 7:
					/*for(int i = 0; i < accounts.get(index).new_profile.communities.size(); i++)
					{
						System.out.printf("Nome: %s\nDescricao: %s\nMembros:\n", accounts.get(index).new_profile.communities.get(i).name,
								accounts.get(index).new_profile.communities.get(i).theme);
						for(int j = 0; j < accounts.get(index).new_profile.communities.get(i).members.size(); j++)
						{
							System.out.printf(" - %s\n", accounts.get(index).new_profile.communities.get(i).members.get(j).getUsername());
						}
						for(int j = 0; j < accounts.get(index).new_profile.communities.get(i).messages.size(); j++)
						{
							System.out.printf("Mensagem de %s:\n%s\n", accounts.get(index).new_profile.communities.get(i).messages.get(j).from_username,
									accounts.get(index).new_profile.communities.get(i).messages.get(j).message);
						}
					}*/
					for(int i = 0; i < accounts.size(); i++)
					{
						for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
						{
							for(int k = 0; k < accounts.get(i).new_profile.communities.get(j).members.size(); k++)
							{
								if(accounts.get(index).username.intern() == accounts.get(i).new_profile.communities.get(j).members.get(k).username.intern() )
								{
									System.out.printf("\n---------------\n");
									System.out.printf("Admin: %s\nNome: %s\nDescricao: %s\nMembros:\n",
											accounts.get(i).new_profile.communities.get(j).admin,
											accounts.get(i).new_profile.communities.get(j).name,
											accounts.get(i).new_profile.communities.get(j).theme);
									for(int l = 0; l < accounts.get(i).new_profile.communities.get(j).members.size(); l++)
									{
										System.out.printf(" -> %s\n", accounts.get(i).new_profile.communities.get(j).members.get(l).getUsername());
									}
									for(int m = 0; m < accounts.get(i).new_profile.communities.get(j).messages.size(); m++)
									{
										System.out.printf("Mensagem de %s:\n%s\n", accounts.get(i).new_profile.communities.get(j).messages.get(m).from_username,
												accounts.get(i).new_profile.communities.get(j).messages.get(m).message);
									}
									System.out.printf("---------------\n\n");
								}
							}
						}
					}
					System.out.printf("[1] Continue\n=> ");
					okay = input.nextLine();
					System.out.printf("\n");
					break;
				case 8:
					System.out.printf("Comunidades do iFace:\n\n");
					for(int i = 0; i < accounts.size(); i++)
					{
						if(accounts.get(i).username.intern() != accounts.get(index).username.intern()
								&& !accounts.get(i).new_profile.communities.isEmpty())
						{
							for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
							{
								System.out.printf("Nome: %s\nDescricao: %s\n\n",
										accounts.get(i).new_profile.communities.get(j).name,
										accounts.get(i).new_profile.communities.get(j).theme);
							}
						}
					}
					System.out.printf("Deseja enviar mensagem para qual?\n=> ");
					okay = input.nextLine(); decision = 1;
					for(int i = 0; i < accounts.size(); i++)
					{
						for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
						{
							if(okay.intern() == accounts.get(i).new_profile.communities.get(j).name.intern())
							{
								InputBox new_message2 = new InputBox();
								new_message2.from_username = accounts.get(i).getUsername();
								new_message2.getMessage();
								accounts.get(i).new_profile.communities.get(j).messages.add(new_message2);
								System.out.printf("Mensagem enviada!\n[1] Continue\n=> ");
								okay = input.nextLine();
								System.out.printf("\n");
								decision = 0;
							}
							if(decision==0)
								break;
						}
						if(decision == 0)
							break;
					}
					if(decision == 1)
					{
						System.out.printf("Comunidade nao encontrada.\n[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
					}
					break;
				case 9:
					int u =0;
					if(u==0)
					{
						System.out.printf("Sem backup.\n[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
						break;
					}
				
					break;
				case 10:
					System.out.printf("Apagar conta?\n[1] Sim [2] Nao\n=> ");
					decision = input.nextInt(); input.nextLine();
					if(decision == 1)
					{
						for(int i = 0; i < accounts.size(); i++)
						{
							if(accounts.get(i).username.intern() == accounts.get(index).username.intern())
							{
								for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
								{
									accounts.get(i).new_profile.communities.get(j).invites.clear();
									accounts.get(i).new_profile.communities.get(j).members.clear();
									accounts.get(i).new_profile.communities.get(j).messages.clear();
								}
								accounts.get(i).new_profile.communities.clear();
								accounts.get(i).new_profile.friends.clear();
								accounts.get(i).new_profile.input_box.clear();
								accounts.get(i).new_profile.invites.clear();
							}
							else
							{
								for(int j = 0; j < accounts.get(i).new_profile.communities.size(); j++)
								{
										for(int k = 0; k < accounts.get(i).new_profile.communities.get(j).invites.size(); k++)
										{
											if(accounts.get(index).username.intern() == accounts.get(i).new_profile.communities.get(j).invites.get(k).username.intern())
												accounts.get(i).new_profile.communities.get(j).invites.remove(k);							
										}
										for(int k = 0; k < accounts.get(i).new_profile.communities.get(j).members.size(); k++)
										{
											if(accounts.get(index).username.intern() == accounts.get(i).new_profile.communities.get(j).members.get(k).username.intern())
												accounts.get(i).new_profile.communities.get(j).members.remove(k);							
										}
										for(int k = 0; k < accounts.get(i).new_profile.communities.get(j).messages.size(); k++)
										{
											if(accounts.get(index).username.intern() == accounts.get(i).new_profile.communities.get(j).messages.get(k).from_username.intern())
												accounts.get(i).new_profile.communities.get(j).messages.remove(k);							
										}
								}
								for(int j = 0; j < accounts.get(i).new_profile.friends.size(); j++)
								{
									if(accounts.get(index).username.intern() == accounts.get(i).new_profile.friends.get(j).username.intern())
										accounts.get(i).new_profile.friends.remove(j);
								}
								for(int j = 0; j < accounts.get(i).new_profile.input_box.size(); j++)
								{
									if(accounts.get(index).username.intern() == accounts.get(i).new_profile.input_box.get(j).from_username.intern())
										accounts.get(i).new_profile.input_box.remove(j);
								}
								for(int j = 0; j < accounts.get(i).new_profile.invites.size(); j++)
								{
									if(accounts.get(index).username.intern() == accounts.get(i).new_profile.invites.get(j).username.intern())
										accounts.get(i).new_profile.invites.remove(j);
								}
							}
						}
						accounts.remove(index);
						active = false;
						System.out.printf("Conta apagada!\n[1] Continue\n=> ");
						okay = input.nextLine();
						System.out.printf("\n");
					}
					break;
				case 11:
					Register save = new Register();
					save.username = accounts.get(index).getUsername();
					save.password = accounts.get(index).getPassword();
					save.new_profile.age = accounts.get(index).new_profile.getAge();
					save.new_profile.city = accounts.get(index).new_profile.getCity();
					save.new_profile.country = accounts.get(index).new_profile.getCountry();
					save.new_profile.gender = accounts.get(index).new_profile.getGender();
					save.new_profile.more1 = accounts.get(index).new_profile.getMore1();
					save.new_profile.more2 = accounts.get(index).new_profile.getMore2();
					save.new_profile.phrase = accounts.get(index).new_profile.getPhrase();
					save.new_profile.state = accounts.get(index).new_profile.getState();
					save.new_profile.name = accounts.get(index).new_profile.getName();
					save.new_profile.communities =  (ArrayList<Community>) accounts.get(index).new_profile.communities.clone();
					for(int i=0; i < accounts.get(index).new_profile.communities.size(); i++)
					{
						save.new_profile.communities.get(i).messages = (ArrayList<InputBox>) accounts.get(index).new_profile.communities.get(i).messages.clone();
						save.new_profile.communities.get(i).members = (ArrayList<Friends>) accounts.get(index).new_profile.communities.get(i).members.clone();
						save.new_profile.communities.get(i).invites = (ArrayList<Invite>) accounts.get(index).new_profile.communities.get(i).invites.clone();
					}
					save.new_profile.friends =  (ArrayList<Friends>) accounts.get(index).new_profile.friends.clone();
					save.new_profile.input_box =  (ArrayList<InputBox>) accounts.get(index).new_profile.input_box.clone();
					save.new_profile.invites =  (ArrayList<Invite>) accounts.get(index).new_profile.invites.clone();
					backups.add(save);
					//System.out.printf("%s\n", save.new_profile.communities.get(0).messages.get(0));
					active = false;
					break;
			}
		}
	}
}
