package io.github.devbhuwan.maven.plugin.set.env.vars.mojo.goals;

import io.github.devbhuwan.maven.plugin.set.env.vars.mojo.AbstractSetEnvMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.util.Map;
import java.util.Objects;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 12/26/2016
 */
@Mojo(name = "setEnvVarsByKeyValuePairs")
public class SetEnvVarsByKeyValuePairsGoal extends AbstractSetEnvMojo {

    @Parameter(name = "envVarsMap", required = true)
    private Map<String, String> envVarsMap;

    @Override
    protected void buildKeyValuePairs() {
        getKeyValuePairsMap().putAll(envVarsMap);
    }

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        getLog().info("setEnvVarsByKeyValuePairs#execute()-> Start");
        Objects.requireNonNull(envVarsMap, "");
        this.buildKeyValuePairs();
        this.setEnvironmentVariables();
        getLog().info("setEnvVarsByKeyValuePairs#execute()-> Finished");
    }

}
