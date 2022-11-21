package com.team.vinylos.network

import androidx.collection.LruCache

class CacheManager {
    companion object {
        private var instance: CacheManager? = null
        fun getInstance() =
            instance ?: synchronized(this) {
                instance ?: CacheManager().also {
                    instance = it
                }
            }
    }

    private var cache: LruCache<String, Any> = LruCache(5)

    fun invalidate(key: Any,classs: Class<*>?) {
        cache.remove(generateKey(classs,key))

    }

    fun put(key: Any, value: Any,classs: Class<*>?) {
        if (cache.get(generateKey(classs,key)) == null) {
            cache.put(generateKey(classs,key), value)
        }
    }

    fun get(key: Any,classs: Class<*>?): Any? {
        return if (cache.get(generateKey(classs,key)) != null) cache.get(generateKey(classs,key)) else null;

    }

    private fun generateKey(classs: Class<*>?, key:Any):String{
        return classs.toString()+key.toString();
    }
}
