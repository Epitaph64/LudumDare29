package com.enzor.LD29.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.enzor.LD29.LudumDare29;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(768, 512);
        }

        @Override
        public ApplicationListener getApplicationListener () {
                return new LudumDare29();
        }
}