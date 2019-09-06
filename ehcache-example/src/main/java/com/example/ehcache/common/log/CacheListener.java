package com.example.ehcache.common.log;

import lombok.extern.slf4j.Slf4j;
import net.sf.ehcache.Ehcache;
import net.sf.ehcache.Element;
import net.sf.ehcache.event.CacheEventListener;

@Slf4j
public final class CacheListener implements CacheEventListener {
    static final CacheEventListener INSTANCE = new CacheListener();

    private void log(Ehcache cache, Element element, String message) {
        log.info("Cache {} of '{}' for item with key '{}' and value '{}'", message, cache.getName(), element.getObjectKey(), element.getObjectValue());
    }

    @Override
    public void notifyElementRemoved(Ehcache cache, Element element) {
        log(cache, element, "removed");
    }

    @Override
    public void notifyElementPut(Ehcache cache, Element element) {
        log(cache, element, "put");
    }

    @Override
    public void notifyElementUpdated(Ehcache cache, Element element) {
        log(cache, element, "update");
    }

    @Override
    public void notifyElementExpired(Ehcache cache, Element element) {
        log(cache, element, "expired");
    }

    @Override
    public void notifyElementEvicted(Ehcache cache, Element element) {
        log(cache, element, "evicted");
    }

    @Override
    public void notifyRemoveAll(Ehcache cache) {
        log.info("Cache remove all of '{}'", cache.getName());
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        throw new CloneNotSupportedException("Singleton instance");
    }

    @Override
    public void dispose() {
        log.info("dispose");
    }
}
