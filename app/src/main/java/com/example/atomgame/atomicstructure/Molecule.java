package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;

public class Molecule extends AtomicStructure {
    private final LinkedList<HashSet<Radical>> radicals;
    private final String name;

    public Molecule(@NonNull HashSet<Atom> structure, @NonNull LinkedList<HashSet<Radical>> radicals,
                    @NonNull LinkedList<Atom> skeleton, boolean isCycled, @NonNull String name) {
        super(structure, skeleton, isCycled);
        this.radicals = radicals;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public LinkedList<Iterator<Radical>> getRadicalIteratorList() {
        LinkedList<Iterator<Radical>> radicalIteratorList = new LinkedList<>();
        for (HashSet<Radical> radicalSet : radicals) {
            radicalIteratorList.add(radicalSet.iterator());
        }
        return radicalIteratorList;
    }
}
