/*
Numeric - Graded IRC bot - Mahmut Bulut <mahmutbulut0@gmail.com>

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation.

*/

package numeric.uk.ac.port.dsg.numeric;

import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import java.net.*;
import numeric.uk.ac.port.dsg.numeric.plugins.BrainInterface;

public class PluginManager {

	public static ArrayList loadPlugins(Brain bot) {
		//System.out.println("Discovering plugins");
		ArrayList loadedObjects = new ArrayList();
		Vector modules = discoverPlugins("NumericPlugin");
		for (int i = 0; i < modules.size(); i++) {
			String plugin = (String) modules.get(i);

			Object loadedObject = createDynamicObject(plugin, bot);
			if (loadedObject != null) {
				loadedObjects.add(loadedObject);
			} else {
				System.out.println("Plugin yuklenemedi: " + plugin);
			}

		}
		if (loadedObjects.isEmpty()) {
			// when ever we ask for a plugin if we return nothing teh program will bust
			// there should always be a plugin to saticfy the query
			// so we stop
			System.out.println("Plugin yuklenemedi");
			return null;
		} else {
			// sort the list before we return it

			ArrayList sortedList = new ArrayList();

			while (loadedObjects.size() > 0) {
				int smallestObject = 0;
				int smallestVal = 1000;
				for (int outer = 0; outer < loadedObjects.size(); outer++) {
					int compare = ((BrainInterface) loadedObjects.get(outer)).getImportance();
					if (compare < smallestVal) {
						smallestVal = compare;
						smallestObject = outer;
					}
				}
				sortedList.add(loadedObjects.remove(smallestObject));
			}
			return sortedList;
		}
	}

	private static Object createDynamicObject(String className, Brain bot) {
		try {

			//System.out.println("Trying to load: " + className);

			// get reference to the class

			File file = new File(".");
			ClassLoader loader = new URLClassLoader(new URL[] { file.toURL()});
			Class classRef = loader.loadClass(className);

			//Class classRef = Class.forName(className);

			Class[] classConst = new Class[1];
			classConst[0] = Class.forName("numeric.uk.ac.port.dsg.numeric.Brain");

			Object[] classObject = new Object[1];
			classObject[0] = bot;

			Constructor constr = classRef.getConstructor(classConst);
			Object newObject = constr.newInstance(classObject);

			if (newObject != null) {
				System.out.println("Dinamik olarak yuklendi: " + className);
			}

			return newObject;

		} catch (java.lang.ClassNotFoundException e) {
			System.out.println("Sinif bulunamadi: " + className);
			return null;
		} catch (java.lang.Exception e) {
			System.out.println("Sinif yuklenemedi: " + className);
			System.out.println(e.toString());
			e.printStackTrace();
			return null;
		}

	}

	private static Vector discoverPlugins(String classOfPlugin) {
		String rootPluginPath = "numeric.uk.ac.port.dsg.numeric/plugins/";
		String classPathStub = "numeric.uk.ac.port.dsg.numeric.plugins.";

		Vector foundModules = new Vector();

		File dir = new File(rootPluginPath);
		// This filter only returns directories
		FileFilter fileFilter = new FileFilter() {
			public boolean accept(File file) {
				return file.isDirectory();
			}
		};
		File[] dirs = dir.listFiles(fileFilter);

		for (int i = 0; i < dirs.length; i++) {
			// Get filename of file or directory
			String dirName = dirs[i].getName();
			//System.out.println("Found dir: " + dirName);
			//if (!filterCmd(filter, dirName)) {

			String pathToClass = rootPluginPath + dirName + File.separator + classOfPlugin;
			String pathToClassFileName = pathToClass + ".class";
			//System.out.println("Module to look for is:" + pathToClassFileName);
			File moduleFile = new File(pathToClassFileName);
			if (moduleFile.exists()) {
				//System.out.println("Could load: " + pathToClassFileName);
				pathToClass = pathToClass.replaceAll(rootPluginPath, "");
				foundModules.add(classPathStub + pathToClass.replaceAll(File.separator, "."));
			}
			//}
		}

		return foundModules;

	}

}
