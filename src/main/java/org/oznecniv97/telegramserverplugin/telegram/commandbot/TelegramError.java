package org.oznecniv97.telegramserverplugin.telegram.commandbot;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TelegramError {

		GENERIC_ERROR				("Generic error")
	,	MISSING_PLUGIN_ATTRIBUTE	("Cannot retrieve instance without plugin attribute")
	,	MISSING_TOKEN_ATTRIBUTE		("Cannot retrieve instance without token attribute")
	,	MISSING_USERNAME_ATTRIBUTE	("Cannot retrieve instance without username attribute")
	;

	private final String message;

}
