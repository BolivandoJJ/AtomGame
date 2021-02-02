package com.example.atomgame;

import java.util.HashSet;
import java.util.Iterator;

public abstract class Atom implements Placeable {
    private final HashSet<Connection> connections;
    private byte numOfFreeElectrons;
    public static final byte FREE_ELECTRON_DECREASING_ERROR_CODE = -1;

    public Atom(byte valence) {
        if (valence > 0) {
            numOfFreeElectrons = valence;
        } else {
            throw new IllegalArgumentException("Valence must be > 0");
        }
        connections = new HashSet<>();
    }

    public abstract byte getAtomicNumber();

    public abstract byte getValence();

    public abstract String getName();

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
