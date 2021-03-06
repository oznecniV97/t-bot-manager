package org.oznecniv97.telegramserverplugin.bukkit.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BukkitError {

		GENERIC_ERROR			("Generic error")
	,	UNRECOGNIZED_ARGUMENT	("Unrecognized argument!")
	,	NOT_ENOUGH_ARGUMENTS	("Not enough arguments!")
	,	TOO_MANY_ARGUMENTS		("Too many arguments!")
	,	ONLY_CONSOLE_AVAILABLE	("Command available only from console")
	,	TELEGRAM_EXCEPTION		("Error in telegram api.")
	;

	private final String message;

}
