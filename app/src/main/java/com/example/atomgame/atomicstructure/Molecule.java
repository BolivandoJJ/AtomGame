package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.AtomicStructureTemplate;
import com.example.atomgame.atom.Atom;
import com.example.atomgame.atomicstructure.type.MoleculeType;

import java.util.ArrayList;
import java.util.HashSet;

public class Molecule extends AtomicStructure {
    private final FunctionalGroup functionalGroup;
    private final AtomicStructureTemplate<MoleculeType> moleculeTemplate;

    public Molecule(@NonNull HashSet<Atom> atomSet, @Nullable FunctionalGroup functionalGroup,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name, @NonNull AtomicStructureTemplate<MoleculeType> moleculeTemplate) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroup = functionalGroup;
        this.moleculeTemplate = moleculeTemplate;
    }

    public FunctionalGroup getFunctionalGroup() {
        return functionalGroup;
    }

    public AtomicStructureTemplate<MoleculeType> getMoleculeTemplate() {
        return moleculeTemplate;
    }
}
