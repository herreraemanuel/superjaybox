package build;

import java.util.ArrayList;

import com.esotericsoftware.scar.Build;
import com.esotericsoftware.scar.Project;


public class Builder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Project project = new Project();
			project.set("name", "sjb");
			project.set('source', 'src');
			project.set("resources", "assets");
			project.set("target", "dist");
			project.set("version", "0.0.1");
			
			ArrayList libs = new  ArrayList();
			libs.add("libs/gdx/*.java");
			libs.add("libs/other/*.java");
			
			project.set("", "")
			
			
			
			Build.lwjglAppletHtml(project);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}
