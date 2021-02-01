package com.example.atomgame;

import java.util.Iterator;

public interface AtomicStructure {
    boolean skeletonIsCycled();
    Iterator<Atom> getSkeletonIterator();
    Iterator<Atom> getStructureIterator();
}
