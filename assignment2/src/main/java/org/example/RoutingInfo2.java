package org.example;

public class RoutingInfo2 implements Comparable<RoutingInfo2> {
    String targetNetwork;
    String nextJump;

    public RoutingInfo2(String targetNetwork, String nextJump) {
        this.targetNetwork = targetNetwork;
        this.nextJump = nextJump;
    }

    @Override
    public int compareTo(RoutingInfo2 o) {
        return targetNetwork.compareTo(o.targetNetwork);
    }
}
