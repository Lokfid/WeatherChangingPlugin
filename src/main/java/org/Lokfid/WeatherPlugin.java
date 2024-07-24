package org.Lokfid;

import org.rusherhack.client.api.RusherHackAPI;
import org.rusherhack.client.api.plugin.Plugin;

/**
 * Weather Changing Plugin
 *
 * @author Lokfid
 */
public class WeatherPlugin extends Plugin {
	
	@Override
	public void onLoad() {
		this.getLogger().info("Weather Plugin Loaded!");
		final WeatherModule weatherModule = new WeatherModule();
		RusherHackAPI.getModuleManager().registerFeature(weatherModule);
	}
	
	@Override
	public void onUnload() {
		this.getLogger().info("Weather Plugin unloaded!");
	}
	
}