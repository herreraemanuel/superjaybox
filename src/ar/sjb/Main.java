package ar.sjb;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.SharedLibraryLoader;


public class Main {

	public static void main(String[] args) {
		new SharedLibraryLoader().load("gdx-controllers-desktop");
		Texture.setEnforcePotImages(false); //FIXME Usar texture atlas
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.width = SJB.GAME_WIDTH;
		cfg.height = SJB.GAME_HEIGHT;
		cfg.resizable = false;
		cfg.addIcon("assets/img/favicon.png", FileType.Internal);
		cfg.title = "Super Jay Box 0.0.1";
		
		new LwjglApplication(new SJB(), cfg);
		
	}

}
