package com.example.atomgame.atom;

// Oxygen
public class O extends Atom {
    public static final String ATOM_NAME = "O";
    public static final byte ATOMIC_NUMBER = 8;
    public static final byte VALENCE = 2;

    public O() {
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
