package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.HashSet;
import java.util.LinkedList;

public class Radical extends AtomicStructure {

    public Radical(@NonNull HashSet<Atom> structure, @NonNull String name,
                   @NonNull LinkedList<Atom> skeleton, boolean isCycled) {
        super(structure, name, skeleton, isCycled);
    }
}
