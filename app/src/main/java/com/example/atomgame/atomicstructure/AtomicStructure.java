package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.ListIterator;

public abstract class AtomicStructure {
    private final ArrayList<Atom> skeleton;
    private final boolean skeletonIsCycled;
    private final HashSet<Atom> atomSet;
    private final String name;

    public AtomicStructure(@NonNull HashSet<Atom> atomSet, @NonNull String name,
                           @NonNull ArrayList<Atom> skeleton, boolean isCycled) {
        this.skeleton = skeleton;
        this.atomSet = atomSet;
        this.skeletonIsCycled = isCycled;
        this.name = name;
    }

    public boolean skeletonIsCycled() {
        return skeletonIsCycled;
    }

    public ListIterator<Atom> getSkeletonListIterator() {
        return skeleton.listIterator();
    }

    public Iterator<Atom> getAtomSetIterator() {
        return atomSet.iterator();
    }

    public String getName() {
        return name;
    }
}
