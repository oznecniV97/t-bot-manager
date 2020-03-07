package org.oznecniv97.telegramserverplugin;

import org.bukkit.plugin.java.JavaPlugin;
import org.oznecniv97.telegramserverplugin.bukkit.executor.PluginBotCommandExecutor;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;

import lombok.Getter;

 @Getter
public class TelegramServerPlugin extends JavaPlugin {

    private TelegramBotsApi telegramBotsApi;

	/*
	 * codice per reperire i giocatori online
	 	for (Player player : Bukkit.getServer().getOnlinePlayers()) {
    		playerList.put(player.getName(), playerData(player));
		}
	 */

	@Override
	public void onLoad() {
		super.onLoad();
        //init necessario per gestione bot telegram
		ApiContextInitializer.init();
	    telegramBotsApi = new TelegramBotsApi();

		getCommand("bot").setExecutor(new PluginBotCommandExecutor(this));
		//TODO verificare se le configurazioni sono presenti, se sì avviare il bot di telegram (in caso di errore provare nell'onEnable che viene eseguito dopo il caricamento del mondo)
	}

	@Override
	public void onDisable() {
		super.onDisable();
		getLogger().info("onDisable has been invoked");
	}

}
