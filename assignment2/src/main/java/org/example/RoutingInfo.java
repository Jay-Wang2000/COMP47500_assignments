package org.example;

public class RoutingInfo implements Comparable {
    String targetNetwork;
    String nextJump;

    public RoutingInfo(String targetNetwork, String nextJump) {
        this.targetNetwork = targetNetwork;
        this.nextJump = nextJump;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof RoutingInfo)
            return targetNetwork.compareTo(((RoutingInfo) o).targetNetwork);
        else throw new ClassCastException("Can only compare RoutingInfo with RoutingInfo");
    }

    @Override
    public String toString() {
        return "RoutingInfo{" +
                "targetNetwork='" + targetNetwork + '\'' +
                ", nextJump='" + nextJump + '\'' +
                '}';
    }
}
