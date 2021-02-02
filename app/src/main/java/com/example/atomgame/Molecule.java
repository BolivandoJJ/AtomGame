package com.example.atomgame;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.LinkedList;

public class Molecule extends AtomicStructure {
    private final LinkedList<HashSet<Radical>> radicals;
    private final String name;

    public Molecule(@NonNull HashSet<Atom> structure, @NonNull LinkedList<HashSet<Radical>> radicals,
                    @NonNull LinkedList<Atom> skeleton, boolean isCycled, @NonNull String name) {
        super(structure, skeleton, isCycled);
        this.radicals = radicals;
        this.name = name;
    }
}
