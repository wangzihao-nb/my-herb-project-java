package org.herb.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.herb.pojo.Herb;
import org.herb.service.HerbCacheService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class HerbCacheServiceImpl implements HerbCacheService {

    private static final Logger logger = LoggerFactory.getLogger(HerbCacheServiceImpl.class);
    private static final String HERB_CACHE_PREFIX = "herb:";
    private static final String HOT_HERBS_KEY = "herb:hot";
    private static final long CACHE_EXPIRE_HOURS = 24;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public Herb getHerbById(Integer id) {
        String key = HERB_CACHE_PREFIX + id;
        String json = stringRedisTemplate.opsForValue().get(key);
        if (json != null) {
            try {
                logger.debug("Cache hit for herb id: {}", id);
                return objectMapper.readValue(json, Herb.class);
            } catch (JsonProcessingException e) {
                logger.error("Failed to parse cached herb data for id: {}", id, e);
                return null;
            }
        }
        logger.debug("Cache miss for herb id: {}", id);
        return null;
    }

    @Override
    public void cacheHerb(Herb herb) {
        if (herb == null || herb.getId() == null) {
            return;
        }
        String key = HERB_CACHE_PREFIX + herb.getId();
        try {
            String json = objectMapper.writeValueAsString(herb);
            stringRedisTemplate.opsForValue().set(key, json, CACHE_EXPIRE_HOURS, TimeUnit.HOURS);
            logger.debug("Cached herb with id: {}", herb.getId());
        } catch (JsonProcessingException e) {
            logger.error("Failed to cache herb with id: {}", herb.getId(), e);
        }
    }

    @Override
    public void evictHerbCache(Integer id) {
        String key = HERB_CACHE_PREFIX + id;
        stringRedisTemplate.delete(key);
        logger.debug("Evicted cache for herb id: {}", id);
    }

    @Override
    public List<Herb> getHotHerbs() {
        String json = stringRedisTemplate.opsForValue().get(HOT_HERBS_KEY);
        if (json != null) {
            try {
                logger.debug("Cache hit for hot herbs");
                return objectMapper.readValue(json, 
                    objectMapper.getTypeFactory().constructCollectionType(List.class, Herb.class));
            } catch (JsonProcessingException e) {
                logger.error("Failed to parse cached hot herbs data", e);
                return new ArrayList<>();
            }
        }
        logger.debug("Cache miss for hot herbs");
        return null;
    }

    @Override
    public void cacheHotHerbs(List<Herb> herbs) {
        if (herbs == null || herbs.isEmpty()) {
            return;
        }
        try {
            String json = objectMapper.writeValueAsString(herbs);
            stringRedisTemplate.opsForValue().set(HOT_HERBS_KEY, json, CACHE_EXPIRE_HOURS, TimeUnit.HOURS);
            logger.debug("Cached {} hot herbs", herbs.size());
        } catch (JsonProcessingException e) {
            logger.error("Failed to cache hot herbs", e);
        }
    }

    @Override
    public void evictHotHerbsCache() {
        stringRedisTemplate.delete(HOT_HERBS_KEY);
        logger.debug("Evicted cache for hot herbs");
    }
}
