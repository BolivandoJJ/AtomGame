package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public abstract class AtomicStructure {
    private final ArrayList<Atom> skeleton;
    private final boolean skeletonIsCycled;
    private final HashSet<Atom> structure;
    private final String name;

    public AtomicStructure(@NonNull HashSet<Atom> structure, @NonNull String name,
                           @NonNull ArrayList<Atom> skeleton, boolean isCycled) {
        this.skeleton = skeleton;
        this.structure = structure;
        this.skeletonIsCycled = isCycled;
        this.name = name;
    }

    public boolean skeletonIsCycled() {
        return skeletonIsCycled;
    }

    public Iterator<Atom> getSkeletonIterator() {
        return skeleton.iterator();
    }

    public Iterator<Atom> getStructureIterator() {
        return structure.iterator();
    }

    public String getName() {
        return name;
    }
}
