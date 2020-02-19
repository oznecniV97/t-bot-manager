package org.oznecniv97.telegramserverplugin.bukkit.executor.generic;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;
import org.oznecniv97.telegramserverplugin.bukkit.exception.RuntimePluginException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractCommandExecutor implements CommandExecutor {

	protected final JavaPlugin plugin;

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		boolean ret;
		try {

			ret = true;
		} catch(RuntimePluginException re) {
			sender.sendMessage(re.getMessage());
			ret = false;
		}
		return ret;
	}

	protected abstract void executeCommand(CommandSender sender, Command command, String label, String[] args);

}
