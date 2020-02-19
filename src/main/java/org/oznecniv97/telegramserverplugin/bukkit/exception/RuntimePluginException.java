package org.oznecniv97.telegramserverplugin.bukkit.exception;

import org.oznecniv97.telegramserverplugin.bukkit.enums.Error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class RuntimePluginException extends RuntimeException {

	private static final long serialVersionUID = -3631235588687892553L;

	private final Error error;

	@Override
	public String getMessage() {
		String ret = super.getMessage();
		if(error!=null) {
			ret = error.getMessage();
		}
		return ret;
	}

}
