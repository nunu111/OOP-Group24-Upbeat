package com.GAME.UPBEAT.GameProgress;

import java.util.*;

public class ShortestPathUnweighted {
    private static ShortestPathUnweighted findShortest;
    private ShortestPathUnweighted(){}
    public static ShortestPathUnweighted findShortest(){
        if(findShortest == null) findShortest= new ShortestPathUnweighted();
        return findShortest;
    }
    public Long shortestPath( Region Start, Region End) {
        if(Start == null && End == null) return (long)-1;
        Set<Region> visited = new HashSet<>();
        Queue<Region> queue = new LinkedList<>();
        Map<Region,Long> distance = new HashMap<>();
        queue.add(Start);
        visited.add(Start);
        distance.put(Start,(long)0);
        while(!queue.isEmpty()){
            Region CurrentRegion = queue.poll();
            if(CurrentRegion.equals(End)) return distance.get(CurrentRegion);
            for(Region partner : CurrentRegion.PartnerRegion){
                if(partner !=null && !visited.contains(partner)){
                    visited.add(partner);
                    queue.add(partner);
                    distance.put(partner, distance.get(CurrentRegion)+1);
                }
            }
        }
        return (long)-1; // no path
    }
}
