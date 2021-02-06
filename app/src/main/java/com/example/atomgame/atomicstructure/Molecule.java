package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;

public class Molecule extends AtomicStructure {
    private final FunctionalGroup functionalGroup;

    public Molecule(@NonNull HashSet<Atom> atomSet, @Nullable FunctionalGroup functionalGroup,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroup = functionalGroup;
    }

    public FunctionalGroup getFunctionalGroup() {
        return functionalGroup;
    }
}
