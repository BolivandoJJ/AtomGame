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
    // Molecule formula is template, where RADICALs
    // are replaced by the length of the radicals taken with a negative sign
    // example:
    /**
     *  Molecule formula is template, where RADICALs
     *  are replaced by the length of the radicals taken with a negative sign
     *  (lengths are sorted in ascending order)
     *  example:
     *      H H   H
     *     | |   |
     *  H-C-C-O-C-H     is a ether with template: R-O-R'
     *    | |   |                   and formula: (-1)-(8)-(-2)
     *   H H   H
     *
     *   formula's matrix:
     *   {{-1},{1,8},{0,1,-1}}
     */
    private final AtomicStructureTemplate<MoleculeType> moleculeFormula;

    public Molecule(@NonNull HashSet<Atom> atomSet, @Nullable FunctionalGroup functionalGroup,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name,
                    @NonNull AtomicStructureTemplate<MoleculeType> moleculeFormula) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroup = functionalGroup;
        this.moleculeFormula = moleculeFormula;
    }

    public FunctionalGroup getFunctionalGroup() {
        return functionalGroup;
    }

    public AtomicStructureTemplate<MoleculeType> getMoleculeTemplate() {
        return moleculeFormula;
    }
}
