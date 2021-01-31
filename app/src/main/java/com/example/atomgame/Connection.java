package com.example.atomgame;

import androidx.annotation.NonNull;

public class Connection {
    private byte type;
    private Atom [] connectedAtoms;
    public static final byte MAX_CONNECTION_TYPE_VALUE = 3;
    public static final byte MIN_CONNECTION_TYPE_VALUE = 1;
    public static final byte INCREMENTING_ERROR_CODE = -1;

    public Connection(@NonNull Atom atom1, @NonNull Atom atom2) {
        this(MIN_CONNECTION_TYPE_VALUE, atom1, atom2);
    }

    public Connection(byte type, @NonNull Atom atom1, @NonNull Atom atom2) {
        setType(type);
        connectAtoms(atom1, atom2);
    }

    private void connectAtoms(Atom atom1, Atom atom2) {
        if (atom1 != atom2) {
            connectedAtoms = new Atom[] {atom1, atom2};
        } else {
            throw new IllegalArgumentException("Impossible to connect an atom with itself");
        }
        for (Atom a : connectedAtoms) {
            a.addConnection(this);
        }
    }

    public void disconnectAtoms() {
        if (connectedAtoms != null) {
            for (Atom a : connectedAtoms) {
                a.removeConnection(this);
            }
            connectedAtoms = null;
        } else {
            throw new NullPointerException("Connection is no longer active (atoms are disconnected)");
        }
    }

    private void setType(byte type) {
        if ((type >= MIN_CONNECTION_TYPE_VALUE ) && (type <= MAX_CONNECTION_TYPE_VALUE)) {
            this.type = type;
        } else {
            throw new IllegalArgumentException("Connection type must be in 1-3 range");
        }
    }

    public byte getType() {
        return type;
    }

    public boolean connectionIsActive() {
        return connectedAtoms != null;
    }

    // method returns type value after incrementing
    // or -1 if variable cannot be increased
    public byte incrementType() {
        for (Atom atom : connectedAtoms) {
            if (atom.getNumOfFreeElectrons() < 1) {
                return INCREMENTING_ERROR_CODE;
            }
        }
        if (type > MAX_CONNECTION_TYPE_VALUE) {
            return INCREMENTING_ERROR_CODE;
        }
        for (Atom atom : connectedAtoms) {
            atom.decreaseNumOfFreeElectrons();
        }
        return ++type;
    }
}
