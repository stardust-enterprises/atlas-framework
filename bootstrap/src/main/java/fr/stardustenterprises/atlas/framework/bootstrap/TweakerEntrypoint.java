package fr.stardustenterprises.atlas.framework.bootstrap;

import net.minecraft.launchwrapper.ITweaker;
import net.minecraft.launchwrapper.LaunchClassLoader;

import java.io.File;
import java.util.List;

/**
 * LaunchWrapper Tweaker to initialize Atlas Framework on a classpath context.
 * (i.e. where we can control the game's launch arguments)
 *
 * @author xtrm
 * @since 0.0.1
 *
 * TODO(@xtrm): this
 */
public class TweakerEntrypoint implements ITweaker {
    @Override
    public String getLaunchTarget() {
        return null;
    }

    @Override
    public String[] getLaunchArguments() {
        return new String[0];
    }

    @Override
    public void acceptOptions(
        List<String> args,
        File gameDir,
        File assetsDir,
        String profile
    ) {
        // TODO
    }

    @Override
    public void injectIntoClassLoader(LaunchClassLoader classLoader) {
        // TODO Ideas:
        /*
        - call AFW's game-analysis on the classpath to find launch target (main)
        - redirect Deface's hooks into LaunchClassLoader? maybe useless
         */
    }
}
