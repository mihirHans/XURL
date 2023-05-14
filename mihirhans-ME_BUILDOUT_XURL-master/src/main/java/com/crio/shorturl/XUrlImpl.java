
package com.crio.shorturl;
import java.util.HashMap;
import java.util.UUID;

public class XUrlImpl implements XUrl{
   protected HashMap<String, String> map = new HashMap<>();
   protected HashMap<String, String> reverseMap = new HashMap<>();
   protected HashMap<String, Integer> hitCount = new HashMap<>();
    @Override
    public String registerNewUrl(String longUrl){
        String shortUrl="";
        String temp= UUID.randomUUID().toString();

        shortUrl= "http://short.url/" +temp.substring(0,10);
        map.put(shortUrl,longUrl);
        reverseMap.put(longUrl,shortUrl);
        return shortUrl;
    }
    @Override
    public String registerNewUrl(String longUrl, String shortUrl){
        if(map.containsKey(shortUrl)){
            return null;
        }
        map.put(shortUrl, longUrl);
        reverseMap.put(longUrl, shortUrl);
        return shortUrl;
    }
    @Override
    public String getUrl(String shortUrl){
        Integer count=0;
        if(hitCount.containsKey(map.get(shortUrl))){
            count= hitCount.get(map.get(shortUrl));
            count++;
            hitCount.replace(map.get(shortUrl), count);
        }
        else{
            hitCount.put(map.get(shortUrl), 1);
        }
    
        return map.get(shortUrl);

    }
    @Override
    public String delete(String longUrl){
        String shortUrl=reverseMap.get(longUrl);
        map.remove(shortUrl);
        reverseMap.remove(longUrl);

        return "";
    }
    @Override
    public Integer getHitCount(String longUrl){
        if(hitCount.containsKey(longUrl)){
            return hitCount.get(longUrl);
        }
        return 0;
    }

}