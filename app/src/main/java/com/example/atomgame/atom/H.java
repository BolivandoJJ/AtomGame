package com.example.atomgame.atom;

// Hydrogen
public class H extends Atom {
    public static final String ATOM_NAME = "H";
    public static final byte ATOMIC_NUMBER = 1;
    public static final byte VALENCE = 1;

    public H() {
        super(VALENCE);
    }

    @Override
    public byte getAtomicNumber() {
        return ATOMIC_NUMBER;
    }

    @Override
    public byte getValence() {
        return VALENCE;
    }

    @Override
    public String getName() {
        return ATOM_NAME;
    }
}