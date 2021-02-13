package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.AtomicStructureTemplate;
import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;

public class Molecule<T extends Enum<T>> extends AtomicStructure {
    private final FunctionalGroup functionalGroup;
    private final AtomicStructureTemplate<T> moleculeTemplate;

    public Molecule(@NonNull HashSet<Atom> atomSet, @Nullable FunctionalGroup functionalGroup,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name,
                    @NonNull AtomicStructureTemplate<T> moleculeTemplate) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroup = functionalGroup;
        this.moleculeTemplate = moleculeTemplate;
    }

    public FunctionalGroup getFunctionalGroup() {
        return functionalGroup;
    }

    public AtomicStructureTemplate<T> getMoleculeTemplate() {
        return moleculeTemplate;
    }
}
