package org.oznecniv97.telegramserverplugin.bukkit.executor.generic;

import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.oznecniv97.telegramserverplugin.bukkit.exception.RuntimePluginException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCommandExecutor<T extends JavaPlugin> implements CommandExecutor {

	protected final T plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean ret;
		try {
			executeCommand(sender, command, label, args);
			ret = true;
		} catch(RuntimePluginException re) {
			StringBuilder sbMessage = new StringBuilder(re.getMessage());
			if(re.getCause()!=null) {
				sbMessage.append(" Check logs for more details.");
				plugin.getLogger().log(Level.SEVERE, re.getMessage(), re);
			}
			sender.sendMessage(sbMessage.toString());
			ret = false;
		}
		return ret;
	}

	protected abstract void executeCommand(CommandSender sender, Command command, String label, String[] args);

}
