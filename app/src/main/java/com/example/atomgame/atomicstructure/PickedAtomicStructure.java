package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.HashSet;
import java.util.LinkedList;

public class PickedAtomicStructure extends AtomicStructure {

    public PickedAtomicStructure(@NonNull HashSet<Atom> structure,
                                 @NonNull LinkedList<Atom> skeleton, boolean isCycled) {
        super(structure, skeleton, isCycled);
    }
}
