package io.github.devbhuwan.maven.plugin.set.env.vars.mojo;

import org.apache.maven.plugin.AbstractMojo;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Bhuwan Prasad Upadhyay
 * @date 12/26/2016
 */
public abstract class AbstractSetEnvMojo extends AbstractMojo {


    protected Map<String, String> keyValuePairsMap = new ConcurrentHashMap<>();

    protected abstract void buildKeyValuePairs();

    protected Map<String, String> getKeyValuePairsMap() {
        return this.keyValuePairsMap;
    }

    protected void setEnvironmentVariables() {
        try {
            Map<String, String> env = System.getenv();
            Class<?> cl = env.getClass();
            Field field = cl.getDeclaredField("m");
            field.setAccessible(true);
            Map<String, String> writableEnv = (Map<String, String>) field.get(env);
            writableEnv.putAll(getKeyValuePairsMap());
        } catch (Exception e) {
            throw new IllegalStateException("Failed to set environment variable", e);
        }
    }
}
