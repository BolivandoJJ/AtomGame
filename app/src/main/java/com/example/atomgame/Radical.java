package com.example.atomgame;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.HashSet;
import java.util.LinkedList;

public class Radical extends AtomicStructure {
    private final String name;

    public Radical(@NonNull HashSet<Atom> structure, @NonNull String name,
                   @NonNull LinkedList<Atom> skeleton, boolean isCycled) {
        super(structure, skeleton, isCycled);
        this.name = name;
    }
}
