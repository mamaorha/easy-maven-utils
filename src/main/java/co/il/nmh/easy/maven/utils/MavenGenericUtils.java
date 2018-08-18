package co.il.nmh.easy.maven.utils;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.descriptor.PluginDescriptor;
import org.apache.maven.project.MavenProject;

/**
 * @author Maor Hamami
 */

public class MavenGenericUtils
{
	public static void adjustClassLoader(MavenProject project, PluginDescriptor descriptor) throws MojoExecutionException
	{
		try
		{
			List<String> classpathElements = project.getCompileClasspathElements();

			URL urls[] = new URL[classpathElements.size()];

			for (int i = 0; i < classpathElements.size(); ++i)
			{
				urls[i] = new File(classpathElements.get(i)).toURI().toURL();
			}

			for (URL url : urls)
			{
				descriptor.getClassRealm().addURL(url);
			}
		}
		catch (Exception e)
		{
		}
	}
}
