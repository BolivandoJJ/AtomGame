package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atom.Atom;
import com.example.atomgame.atomicstructure.type.MoleculeType;

import java.util.ArrayList;
import java.util.HashSet;

public class Molecule extends AtomicStructure {
    private final FunctionalGroup functionalGroup;
    private final MoleculeType moleculeType;

    public Molecule(@NonNull HashSet<Atom> atomSet, @Nullable FunctionalGroup functionalGroup,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name, MoleculeType moleculeType) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroup = functionalGroup;
        this.moleculeType = moleculeType;
    }

    public FunctionalGroup getFunctionalGroup() {
        return functionalGroup;
    }

    public MoleculeType getMoleculeType() {
        return moleculeType;
    }
}
