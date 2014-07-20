package com.hyunlabs

import com.google.inject.AbstractModule
import com.google.inject.Scopes

class PixelModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(PixelService).in(Scopes.SINGLETON)
    }
}
