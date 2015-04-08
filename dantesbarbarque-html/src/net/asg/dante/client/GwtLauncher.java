package net.asg.dante.client;

import net.asg.games.dante.DantesBarbarqueGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;

public class GwtLauncher extends GwtApplication {
	@Override
	public GwtApplicationConfiguration getConfig () {
		GwtApplicationConfiguration cfg = new GwtApplicationConfiguration(480, 320);
		return cfg;
	}

	@Override
	public ApplicationListener getApplicationListener () {
		return new DantesBarbarque();
	}

	@Override
	public void log(String tag, String message, Throwable exception) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getLogLevel() {
		// TODO Auto-generated method stub
		return 0;
	}
}