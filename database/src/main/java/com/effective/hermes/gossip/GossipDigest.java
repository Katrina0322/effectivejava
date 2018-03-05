package com.effective.hermes.gossip;


/**
 * filename: GossipDigest
 * Description:
 * Author: ubuntu
 * Date: 1/26/18 11:44 AM
 */
public class GossipDigest implements Comparable<GossipDigest>{
    private InetAddressAndPort endPoint;
    private int generation;
    private int maxVersion;

    public GossipDigest(InetAddressAndPort endPoint, int generation, int maxVersion) {
        this.endPoint = endPoint;
        this.generation = generation;
        this.maxVersion = maxVersion;
    }

    public InetAddressAndPort getEndPoint() {
        return endPoint;
    }

    public int getGeneration() {
        return generation;
    }

    public int getMaxVersion() {
        return maxVersion;
    }

    @Override
    public int compareTo(GossipDigest o) {
        if(generation != o.generation){
            return generation - o.generation;
        }
        return maxVersion - o.maxVersion;
    }

    public String toString()
    {
        return String.valueOf(endPoint) +
                ":" +
                generation +
                ":" +
                maxVersion;
    }

}
