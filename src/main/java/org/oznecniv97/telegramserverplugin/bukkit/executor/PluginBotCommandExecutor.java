package org.oznecniv97.telegramserverplugin.bukkit.executor;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.oznecniv97.telegramserverplugin.TelegramServerPlugin;
import org.oznecniv97.telegramserverplugin.bukkit.enums.BukkitError;
import org.oznecniv97.telegramserverplugin.bukkit.exception.RuntimePluginException;
import org.oznecniv97.telegramserverplugin.bukkit.executor.generic.AbstractCommandExecutor;
import org.oznecniv97.telegramserverplugin.telegram.commandbot.MinecraftServerBot;
import org.telegram.telegrambots.meta.exceptions.TelegramApiRequestException;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

public class PluginBotCommandExecutor extends AbstractCommandExecutor<TelegramServerPlugin> {

	public PluginBotCommandExecutor(TelegramServerPlugin plugin) {
		super(plugin);
	}

	/**
	 * Command to startup, shutdown, set username, token and get status of the telegram bot.
	 * usage: /bot [startup | shutdown | set [username | token] <value> | status]
	 *
	 * PS: Command available only from console.
	 *
	 * @throws RuntimePluginException - in error case
	 */
	@Override
	protected void executeCommand(CommandSender sender, Command command, String label, String[] args) {
		BotCommand botCommand = commandChecks(sender, args);
		switch(botCommand) {
			case SET:
				executeSetCommand(args);
				break;
			case SHUTDOWN:
				executeShutdownCommand();
				break;
			case STARTUP:
				executeStartupCommand();
				break;
			case STATUS:
				executeStatusCommand();
				break;
			case RELOAD:
				executeReloadCommand();
				break;
	    }
	}

	private BotCommand commandChecks(CommandSender sender, String[] args) {
		//check if the command was not executed from server console
		if (!(sender instanceof ConsoleCommandSender)) {
			throw new RuntimePluginException(BukkitError.ONLY_CONSOLE_AVAILABLE);
		}
		//check if there's the first argument
		if (args.length==0) {
			throw new RuntimePluginException(BukkitError.NOT_ENOUGH_ARGUMENTS);
		}
		//check if there's the right number arguments
		BotCommand botCommand = BotCommand.fromCommand(args[0]);
		if(botCommand==null) {
			throw new RuntimePluginException(BukkitError.UNRECOGNIZED_ARGUMENT);
		}
		if (args.length < botCommand.getRequiredArguments()) {
			throw new RuntimePluginException(BukkitError.NOT_ENOUGH_ARGUMENTS);
	    }
		if (args.length > botCommand.getRequiredArguments()) {
			throw new RuntimePluginException(BukkitError.TOO_MANY_ARGUMENTS);
	    }
		return botCommand;
	}

	private void executeSetCommand(String[] args) {
		if(SetKey.fromKey(args[1])==null) {
			throw new RuntimePluginException(BukkitError.UNRECOGNIZED_ARGUMENT);
		}
		//TODO verificare che basta questo per creare un file di configurazione
		//TODO verificare che basta questo per creare settare una variabile nel file
		//TODO verificare che basta questo per creare salvare il file in modo tale che nel prossimo startup funzioni
		plugin.getConfig().set(args[1].toLowerCase(), args[2]);
	}

	/**
	 * Gestione comando di stop del bot di telegram
	 */
	private void executeShutdownCommand() {
		// TODO Auto-generated method stub

	}

	/**
	 * Gestione comando di start del bot di telegram
	 * TODO controllare che il bot non sia startato prima di startarlo
	 * TODO controllare che il config abbia tutti i campi necessari valorizzati
	 */
	private void executeStartupCommand() {
		//TODO spostare get delle configurazioni nel costruttore del bot?
		try {
			plugin.telegramBotsApi.registerBot(MinecraftServerBot.getInstance(plugin));
		} catch(TelegramApiRequestException e) {
			throw new RuntimePluginException(BukkitError.TELEGRAM_EXCEPTION, e);
		}
	}

	/**
	 * Gestione comando per controllo dello stato del bot di telegram
	 */
	private void executeStatusCommand() {
		// TODO Auto-generated method stub

	}

	/**
	 * Gestione comando per ricaricare le configurazioni modificate
	 */
	private void executeReloadCommand() {
		// TODO Auto-generated method stub

	}

	@Getter
	@RequiredArgsConstructor
	private enum BotCommand {
			STARTUP		("startup"	, 1)
		,	SHUTDOWN	("shutdown"	, 1)
		,	SET			("set"		, 3)
		,	STATUS		("status"	, 1)
		,	RELOAD		("reload"	, 1)
		;

		private final String command;
		private final int requiredArguments;

		public static BotCommand fromCommand(String command) {
			for(BotCommand botCommand : BotCommand.values()) {
				if(botCommand.command.equalsIgnoreCase(command)) {
					return botCommand;
				}
			}
			return null;
		}

	}

	@Getter
	@RequiredArgsConstructor
	public enum SetKey {
			USERNAME("username"	)
		,	TOKEN	("token"	)
		;

		private final String key;

		public static SetKey fromKey(String key) {
			for(SetKey setKey : SetKey.values()) {
				if(setKey.key.equalsIgnoreCase(key)) {
					return setKey;
				}
			}
			return null;
		}

	}

}
