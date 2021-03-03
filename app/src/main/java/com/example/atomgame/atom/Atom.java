package com.example.atomgame.atom;

import androidx.annotation.NonNull;

import com.example.atomgame.Connection;
import com.example.atomgame.Placeable;

import java.util.Collections;
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

    public boolean containsConnection(Connection connection) {
        return connections.contains(connection);
    }

    public boolean connectedWith(@NonNull Atom connectedAtom) {
        if (connectedAtom == this) {
            return false;
        }
        for (Connection connection : connections) {
            for (Atom atom : connection.getConnectedAtoms()) {
                if (atom == connectedAtom) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean connectedWith(byte atomicNumber) {
        for (Connection connection : connections) {
            for (Atom atom : connection.getConnectedAtoms()) {
                if ((atom != this) && (atom.getAtomicNumber() == atomicNumber)) {
                    return true;
                }
            }
        }
        return false;
    }

    public HashSet<Atom> getConnectedAtoms() {
        HashSet<Atom> connectedAtoms = new HashSet<>();
        for (Connection connection : connections) {
            Collections.addAll(connectedAtoms, connection.getConnectedAtoms());
        }
        connectedAtoms.remove(this);
        return connectedAtoms;
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
