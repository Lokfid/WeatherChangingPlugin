package org.Lokfid;


import org.rusherhack.client.api.events.client.EventUpdate;
import org.rusherhack.client.api.feature.module.ModuleCategory;
import org.rusherhack.client.api.feature.module.ToggleableModule;
import org.rusherhack.core.event.subscribe.Subscribe;
import org.rusherhack.core.setting.EnumSetting;
import org.rusherhack.core.setting.NumberSetting;
import org.rusherhack.core.event.stage.Stage;
/**
 * Weather Changing Plugin
 *
 * @author Lokfid
 */
@SuppressWarnings("unused")
public class WeatherModule extends ToggleableModule {

	private enum Weather {Clear,Rain,Thunder}
	private final EnumSetting<Weather> weather = new EnumSetting<>("Weather","Which weather do you want",Weather.Clear);
	private final NumberSetting<Double> WeatherGradient = new NumberSetting<>("WeatherLevel", 0.0, 0.0, 1.0)
			.incremental(0.1);

	public WeatherModule() {
		super("Weather", "Weather Changing Plugin", ModuleCategory.WORLD);

		
		//register settings
		this.registerSettings(
				this.weather,
				this.WeatherGradient
		);
	}

	@Subscribe(stage = Stage.ALL)
	private void onUpdate(EventUpdate event) {
		if (mc.level != null) {
			if (weather.getValue() == Weather.Rain) {
				mc.level.setRainLevel(WeatherGradient.getValue().floatValue());

			} else if (weather.getValue() == Weather.Clear) {
				mc.level.setRainLevel(0F);
				return;
			}
			else if (weather.getValue() == Weather.Thunder){
				mc.level.setRainLevel(WeatherGradient.getValue().floatValue());
				mc.level.setThunderLevel(1F);
			}
			else{
				return;
			}
		}
	}



	@Override
	public void onDisable() {
		if(mc.level != null) {
			float rain = 0;
			mc.level.getRainLevel(rain);
			mc.level.setRainLevel(rain);
		}

	}
}
