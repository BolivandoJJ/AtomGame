package com.example.atomgame.atomicstructure;

import androidx.annotation.NonNull;

import com.example.atomgame.atom.Atom;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class Molecule extends AtomicStructure {
    private final ArrayList<HashSet<Radical>> radicals;

    public Molecule(@NonNull HashSet<Atom> structure, @NonNull ArrayList<HashSet<Radical>> radicals,
                    @NonNull ArrayList<Atom> skeleton, boolean isCycled, @NonNull String name) {
        super(structure, name, skeleton, isCycled);
        this.radicals = radicals;
    }

    public ArrayList<Iterator<Radical>> getRadicalIteratorList() {
        ArrayList<Iterator<Radical>> radicalIteratorList = new ArrayList<>();
        for (HashSet<Radical> radicalSet : radicals) {
            radicalIteratorList.add(radicalSet.iterator());
        }
        return radicalIteratorList;
    }
}
