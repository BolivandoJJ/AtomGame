package com.example.atomgame;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Atom implements Placeable {
    private final byte valence;
    private final byte atomicNumber;
    private final String name;
    private final HashSet<Connection> connections;
    private byte numOfFreeElectrons;
    public static final byte FREE_ELECTRON_DECREASING_ERROR_CODE = -1;

    public Atom(byte valence, byte atomicNumber, @NonNull String name) {
        if (valence > 0) {
            this.valence = valence;
        } else {
            throw new IllegalArgumentException("Valence must be > 0");
        }
        if (atomicNumber > 0) {
            this.atomicNumber = atomicNumber;
        } else {
            throw new IllegalArgumentException("Atomic number must be > 0");
        }
        this.name = name;
        numOfFreeElectrons = valence;
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

    public byte getNumOfFreeElectrons() {
        return numOfFreeElectrons;
    }

    public Iterator<Connection> getConnectionIterator() {
        return connections.iterator();
    }

    public boolean addConnection(Connection connection) {
        numOfFreeElectrons -= connection.getType();
        return connections.add(connection);
    }

    public boolean removeConnection(Connection connection) {
        numOfFreeElectrons += connection.getType();
        return connections.remove(connection);
    }

    public byte decreaseNumOfFreeElectrons() {
        return decreaseNumOfFreeElectrons((byte) 1);
    }

    public byte decreaseNumOfFreeElectrons(byte decreasingValue) {
        if ((decreasingValue <= numOfFreeElectrons) && decreasingValue > 0) {
            return numOfFreeElectrons -= decreasingValue;
        } else {
            return FREE_ELECTRON_DECREASING_ERROR_CODE;
        }
    }
}
