package com.thankjava.toolkit.resource;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

class ClassLoaderWrapper {
	
	
	final ClassLoader defaultClassLoader = null;
	ClassLoader systemClassLoader;

	ClassLoaderWrapper() {
		try {
			systemClassLoader = ClassLoader.getSystemClassLoader();
		} catch (SecurityException ignored) {
			
		}
	}

	public URL getResourceAsURL(String resource) {
		return getResourceAsURL(resource, getClassLoaders(null));
	}

	public URL getResourceAsURL(String resource, ClassLoader classLoader) {
		return getResourceAsURL(resource, getClassLoaders(classLoader));
	}

	public InputStream getResourceAsStream(String resource) {
		return getResourceAsStream(resource, getClassLoaders(null));
	}

	public InputStream getResourceAsStream(String resource, ClassLoader classLoader) {
		return getResourceAsStream(resource, getClassLoaders(classLoader));
	}

	public Class<?> classForName(String name) throws ClassNotFoundException {
		return classForName(name, getClassLoaders(null));
	}

	public Class<?> classForName(String name, ClassLoader classLoader) throws ClassNotFoundException {
		return classForName(name, getClassLoaders(classLoader));
	}

	InputStream getResourceAsStream(String resource, ClassLoader[] classLoader) {
		for (ClassLoader cl : classLoader) {
			if (null != cl) {

				InputStream returnValue = cl.getResourceAsStream(resource);
				if (null == returnValue) {
					returnValue = cl.getResourceAsStream("/" + resource);
				}
				if (null != returnValue) {
					return returnValue;
				}
			}
		}
		return null;
	}

	URL getResourceAsURL(String resource, ClassLoader[] classLoader) {

		URL url;

		for (ClassLoader cl : classLoader) {

			if (null != cl) {

				url = cl.getResource(resource);
				if (null == url) {
					url = cl.getResource(File.separator + resource);
				}
				if (null != url) {
					return url;
				}

			}

		}

		return null;

	}

	Class<?> classForName(String name, ClassLoader[] classLoader) throws ClassNotFoundException {

		for (ClassLoader cl : classLoader) {

			if (null != cl) {

				try {

					Class<?> c = Class.forName(name, true, cl);

					if (null != c) {
						return c;
					}

				} catch (ClassNotFoundException e) {
				}

			}

		}

		throw new ClassNotFoundException("Cannot find class: " + name);

	}

	ClassLoader[] getClassLoaders(ClassLoader classLoader) {
		return new ClassLoader[] { 
				classLoader, 
				defaultClassLoader,
				Thread.currentThread().getContextClassLoader(),
				getClass().getClassLoader(),
				systemClassLoader 
		};
	}

}
