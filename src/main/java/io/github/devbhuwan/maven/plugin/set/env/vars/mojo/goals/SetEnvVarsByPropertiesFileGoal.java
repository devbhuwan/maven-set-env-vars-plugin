package io.github.devbhuwan.maven.plugin.set.env.vars.mojo.goals;

import io.github.devbhuwan.maven.plugin.set.env.vars.mojo.AbstractSetEnvMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 12/26/2016
 */
@Mojo(name = "setEnvVarsByPropertiesFile")
public class SetEnvVarsByPropertiesFileGoal extends AbstractSetEnvMojo {

    @Parameter(name = "propertiesFile", property = "${setEnvVars.propertiesFile}", required = true)
    private File propertiesFile;

    @Override
    protected void buildKeyValuePairs() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(propertiesFile));
            properties.entrySet().stream().forEach(entry -> getKeyValuePairsMap().put(entry.getKey().toString(), entry.getValue().toString()));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Objects.requireNonNull(propertiesFile, "");
        this.buildKeyValuePairs();
        this.setEnvironmentVariables();
    }

}
