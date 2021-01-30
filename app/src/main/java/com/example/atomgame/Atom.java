package com.example.atomgame;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Atom {
    private final byte valence;
    private final byte atomicNumber;
    private final String name;
    private final HashSet<Connection> connections;

    public Atom(byte valence, byte atomicNumber, @NonNull String name) {
        this.valence = valence;
        this.atomicNumber = atomicNumber;
        this.name = name;
        connections = new HashSet<>();
    }

    public byte getAtomicNumber() {
        return atomicNumber;
    }

    public byte getValence() {
        return valence;
    }

    public String getName() {
        return name;
    }

    public Iterator<Connection> getConnectionIterator() {
        return connections.iterator();
    }

    public boolean addConnection(Connection connection) {
        return connections.add(connection);
    }

    public boolean removeConnection(Connection connection) {
        return connections.remove(connection);
    }
}
