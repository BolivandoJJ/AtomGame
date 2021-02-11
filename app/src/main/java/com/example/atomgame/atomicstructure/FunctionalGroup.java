package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;

public class FunctionalGroup extends AtomicStructure {
    private final FunctionalGroupType type;

    public FunctionalGroup(@NonNull HashSet<Atom> atomSet, @NonNull String name, FunctionalGroupType type,
                           @NonNull ArrayList<Atom> skeleton, boolean isCycled) {
        super(atomSet, name, skeleton, isCycled);
        this.type = type;
    }

    public FunctionalGroupType getType() {
        return type;
    }
}
