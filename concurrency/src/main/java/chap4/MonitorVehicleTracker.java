package chap4;

import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by spark on 11/9/16.
 */
@ThreadSafe
public class MonitorVehicleTracker {
    @GuardedBy("this")
    private final Map<String,MutablePoint> locations;

    public MonitorVehicleTracker(Map<String, MutablePoint> locations) {
        this.locations = deepCopy(locations);
    }

    public synchronized Map<String,MutablePoint> getLocations(){
        return deepCopy(locations);
    }

    public synchronized MutablePoint getLocation(String id){
        MutablePoint loc = locations.get(id);
        return loc == null ? null : new MutablePoint(loc);
    }

    public synchronized void setLocation(String id,int x,int y){
        MutablePoint loc = locations.get(id);
        if(loc == null){
            throw new IllegalArgumentException("No such ID:" + id);
        }
        loc.x = x;
        loc.y = y;
    }


    private static Map<String,MutablePoint> deepCopy(Map<String,MutablePoint> m){
        Map<String,MutablePoint> result = new HashMap<>();
        for(String id:m.keySet()){
            result.put(id,new MutablePoint(m.get(id)));
        }
        return Collections.unmodifiableMap(result);
    }
}
