package com.example.atomgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atom.Atom;
import com.example.atomgame.atomicstructure.Molecule;
import com.example.atomgame.atomicstructure.FunctionalGroup;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class AtomicStructureFactory {
    private final ArrayList<Atom> skeleton;
    private final boolean skeletonIsCycled;
    private HashSet<Atom> atomSet;
    private ArrayList<HashSet<FunctionalGroup>> functionalGroups;
    private byte status;
    public static final byte DEFAULT_STATUS = 0;
    public static final byte MOLECULE_NOT_FOUND_STATUS = -1;
    public static final byte NEEDS_A_FUNCTIONAL_GROUP_SELECTION_STATUS = 1;
    public static final byte MOLECULE_CREATED_STATUS = 2;

    public AtomicStructureFactory(@NonNull ArrayList<Atom> skeleton, boolean skeletonIsCycled) {
        this.skeleton = skeleton;
        this.skeletonIsCycled = skeletonIsCycled;
        status = DEFAULT_STATUS;
    }

    private void parseSkeleton() {
        if (skeletonIsCycled) {
            parseCycledSkeleton();
        } else {
            parseLinearSkeleton();
        }
    }

    private boolean parseCycledSkeleton() {
        boolean parsingWasSuccessful;
        if (parsingWasSuccessful = parseHeterocyclicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseHomoCyclicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseFunctionalGroup()) {
            return parsingWasSuccessful;
        } else {
            return parsingWasSuccessful = false;
        }
    }

    private boolean parseLinearSkeleton() {
        boolean parsingWasSuccessful;
        if (parsingWasSuccessful = parseSimpleMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseHeterogenicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseHomogenicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseFunctionalGroups()) {
            return parsingWasSuccessful;
        } else {
            return parsingWasSuccessful = false;
        }
    }

    private boolean parseSimpleMolecule() {
        
    }

    public byte getStatus() {
        return status;
    }

    @Nullable
    public ArrayList<Iterator<FunctionalGroup>> getFunctionalGroups() {
        if (functionalGroups != null) {
            ArrayList<Iterator<FunctionalGroup>> functionalGroupIteratorList = new ArrayList<>();
            for (HashSet<FunctionalGroup> functionalGroupSet : functionalGroups) {
                functionalGroupIteratorList.add(functionalGroupSet.iterator());
            }
            return functionalGroupIteratorList;
        }
    }

    public Molecule getMolecule(FunctionalGroup functionalGroups) {

    }
}
