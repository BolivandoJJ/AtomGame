package com.example.atomgame.atom;

// Nitrogen
public class N extends Atom {
    public static final String ATOM_NAME = "C";
    public static final byte ATOMIC_NUMBER = 6;
    public static final byte VALENCE = 4;

    public N() {
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
