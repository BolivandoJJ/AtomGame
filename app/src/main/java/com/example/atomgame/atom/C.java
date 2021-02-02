package com.example.atomgame.atom;

// Carbon
public class C extends Atom {
    public static final String ATOM_NAME = "C";
    public static final byte ATOMIC_NUMBER = 6;
    public static final byte VALENCE = 4;

    public C() {
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
