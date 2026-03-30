package org.herb.service;

import org.herb.pojo.Herb;
import org.herb.pojo.PageBean;

import java.util.List;

public interface HerbCacheService {
    Herb getHerbById(Integer id);
    void cacheHerb(Herb herb);
    void evictHerbCache(Integer id);
    List<Herb> getHotHerbs();
    void cacheHotHerbs(List<Herb> herbs);
    void evictHotHerbsCache();
}
