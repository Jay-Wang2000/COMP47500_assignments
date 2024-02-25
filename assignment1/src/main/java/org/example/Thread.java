package org.example;

public class Thread implements Comparable<Object> {
    private final int priority;

    private final String name;

    public Thread(String name, int priority) {
        this.priority = priority;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Thread: " + name + " P: " + priority;
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Thread) {
            if (this.priority == ((Thread) o).priority) {
                return this.name.compareTo(((Thread) o).name) * -1;
            } else
                return Integer.compare(this.priority, ((Thread) o).priority);

        } else
            throw new ClassCastException("Can only compare Thread with Thread");
    }
}
