package org.oznecniv97.telegramserverplugin.telegram.commandbot;

import java.util.logging.Level;

import org.oznecniv97.telegramserverplugin.TelegramServerPlugin;
import org.oznecniv97.telegramserverplugin.bukkit.executor.PluginBotCommandExecutor;
import org.oznecniv97.telegramserverplugin.telegram.utils.Emoji;
import org.telegram.telegrambots.extensions.bots.commandbot.TelegramLongPollingCommandBot;
import org.telegram.telegrambots.extensions.bots.commandbot.commands.helpCommand.HelpCommand;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MinecraftServerBot extends TelegramLongPollingCommandBot {

	private static	MinecraftServerBot	instance;

	private final	TelegramServerPlugin	plugin;
	private			String					token;
	private			String					username;

	/**
	 * Metodo statico per reperimento istanza da singleton.
	 * Necessario solo la prima volta per passare plugin con i relativi config valorizzati, successivamente utilizzare getInstance() per ottimizzare.
	 * @param plugin
	 * @return
	 */
	public static MinecraftServerBot getInstance(TelegramServerPlugin plugin) {
		if(instance==null) {
			instance = new MinecraftServerBot(plugin);
		}
		return getInstance();
	}

	/**
	 * Metodo statico per reperimento istanza da singleton.
	 * Richiamabile solo nel caso in cui sia già stato istanziato tramite l'altro metodo che permette la valorizzazione dei campi obbligatori
	 * @return
	 */
	public static MinecraftServerBot getInstance() {
		if(instance.plugin==null) {
			throw new RuntimeTelegramException(TelegramError.MISSING_PLUGIN_ATTRIBUTE);
		}
		if(instance.token==null) {
			throw new RuntimeTelegramException(TelegramError.MISSING_TOKEN_ATTRIBUTE);
		}
		if(instance.username==null) {
			throw new RuntimeTelegramException(TelegramError.MISSING_USERNAME_ATTRIBUTE);
		}
		return instance;
	}

	private MinecraftServerBot(TelegramServerPlugin plugin) {
		this.plugin		= plugin;

		token		= (String) plugin.getConfig().get(PluginBotCommandExecutor.SetKey.TOKEN		.getKey());
		username	= (String) plugin.getConfig().get(PluginBotCommandExecutor.SetKey.USERNAME	.getKey());

		// TODO continuare da qui, tutto copiato da bot di prova fatto su altro progetto
		HelpCommand helpCommand = new HelpCommand();
        register(helpCommand);

		registerDefaultAction((absSender, message) -> {
			SendMessage commandUnknownMessage = new SendMessage();
			commandUnknownMessage.setChatId(message.getChatId());
			commandUnknownMessage.setText("Il comando '" + message.getText() + "' NO FUSSIONA. NO FUSSIONA UN CASSO " + Emoji.AMBULANCE);
			try {
				absSender.execute(commandUnknownMessage);
			} catch (TelegramApiException e) {
				plugin.getLogger().log(Level.SEVERE, username, e);
			}
			helpCommand.execute(absSender, message.getFrom(), message.getChat(), new String[] {});
		});
	}

	@Override
	public void processNonCommandUpdate(Update update) {
        SendMessage echoMessage = new SendMessage();
        echoMessage.setChatId(update.getMessage().getChatId());
        echoMessage.setText("OINKO?");

        try {
            execute(echoMessage);
        } catch (TelegramApiException e) {
        	plugin.getLogger().log(Level.SEVERE, username + " - processNonCommandUpdate", e);
        }
	}

	@Override
	public String getBotToken() {
		return token;
	}

	@Override
	public String getBotUsername() {
		return username;
	}

}
