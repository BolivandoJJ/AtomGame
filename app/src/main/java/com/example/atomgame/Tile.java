package com.example.atomgame;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

public class Tile {
    private Atom placedAtom;
    private final short xCoord;
    private final short yCoord;

    public Tile(short x, short y) {
        xCoord = x;
        yCoord = y;
    }

    public Atom getAtom() {
        return placedAtom;
    }

    public short getxCoord() {
        return xCoord;
    }

    public short getyCoord() {
        return yCoord;
    }

    public boolean tileIsEmpty() {
        return placedAtom == null;
    }

    // method adds atom to empty tile and
    // returns false if tile already contains atom
    public boolean addAtom(@NonNull Atom atom) {
        if (tileIsEmpty()) {
            setAtom(atom);
            return true;
        } else {
            return false;
        }
    }

    public void setAtom(@NonNull Atom atom) {
        placedAtom = atom;
    }

    // method removes atom from tile
    public void clear() {
        placedAtom = null;
    }
}
