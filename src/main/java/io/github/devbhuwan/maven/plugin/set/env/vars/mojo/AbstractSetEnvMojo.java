package io.github.devbhuwan.maven.plugin.set.env.vars.mojo;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 12/26/2016
 */
public abstract class AbstractSetEnvMojo extends AbstractMojo {

    @Parameter(defaultValue = "${project}")
    protected MavenProject mavenProject;

    protected Map<String, String> keyValuePairsMap = new ConcurrentHashMap<>();

    protected abstract void buildKeyValuePairs();

    protected Map<String, String> getKeyValuePairsMap() {
        return this.keyValuePairsMap;
    }

    protected void setEnvironmentVariables() {
        try {
            getLog().info("envVars-> " + getKeyValuePairsMap().toString());
            getKeyValuePairsMap().entrySet().stream().forEach(entry -> mavenProject.getProperties().setProperty(entry.getKey(), entry.getValue()));
            getLog().info("System Properties [" + mavenProject.getProperties().toString() + "]");
        } catch (Exception e) {
            throw new IllegalStateException("Failed to set environment variable", e);
        }
    }

}
