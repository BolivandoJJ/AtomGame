package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;

public class FunctionalGroup extends AtomicStructure {
    private final byte id;

    public FunctionalGroup(@NonNull HashSet<Atom> atomSet, @NonNull String name, byte id,
                           @NonNull ArrayList<Atom> skeleton, boolean isCycled) {
        super(atomSet, name, skeleton, isCycled);
        if (id >= 0) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Id of functional group in molecule must be non-negative");
        }
    }
}
