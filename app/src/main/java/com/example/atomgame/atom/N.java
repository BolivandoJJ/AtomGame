package com.example.atomgame.atom;

// Nitrogen
public class N extends Atom {
    public static final String ATOM_NAME = "N";
    public static final byte ATOMIC_NUMBER = 7;
    public static final byte VALENCE = 3;

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
