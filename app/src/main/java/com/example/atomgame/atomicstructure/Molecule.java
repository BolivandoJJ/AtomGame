package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Molecule extends AtomicStructure {
    private final ArrayList<HashSet<FunctionalGroup>> functionalGroups;

    public Molecule(@NonNull HashSet<Atom> atomSet, @NonNull ArrayList<HashSet<FunctionalGroup>> functionalGroups,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name) {
        super(atomSet, name, skeleton, isCycled);
        this.functionalGroups = functionalGroups;
    }

    public ArrayList<Iterator<FunctionalGroup>> getFunctionalGrouplIteratorList() {
        ArrayList<Iterator<FunctionalGroup>> functionalGroupIteratorList = new ArrayList<>();
        for (HashSet<FunctionalGroup> functionalGroupSet : functionalGroups) {
            functionalGroupIteratorList.add(functionalGroupSet.iterator());
        }
        return functionalGroupIteratorList;
    }
}
