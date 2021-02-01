package com.example.atomgame;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class PickedAtomicStructure implements AtomicStructure{
    private final LinkedList<Atom> skeleton;
    private final boolean skeletonIsCycled;
    private final HashSet<Atom> structure;

    public PickedAtomicStructure(@NonNull HashSet<Atom> structure,
                                 @NonNull LinkedList<Atom> skeleton, boolean isCycled) {
        this.skeleton = skeleton;
        this.structure = structure;
        this.skeletonIsCycled = isCycled;
    }

    @Override
    public boolean skeletonIsCycled() {
        return skeletonIsCycled;
    }

    @Override
    public Iterator<Atom> getSkeletonIterator() {
        return skeleton.iterator();
    }

    @Override
    public Iterator<Atom> getStructureIterator() {
        return structure.iterator();
    }
}
