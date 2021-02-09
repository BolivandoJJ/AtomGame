package com.example.atomgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atom.Atom;
import com.example.atomgame.atomicstructure.Molecule;
import com.example.atomgame.atomicstructure.FunctionalGroup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AtomicStructureFactory {
    private final ArrayList<Atom> skeleton;
    private byte[][] skeletonTemplate;
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

    private void createSkeletonTemplate() {
        byte[][] template = new byte[skeleton.size()][];
        template[0] = new byte[1];
        template[0][0] = skeleton.get(0).getAtomicNumber();
        Iterator<Connection> iterator;
        Connection connection;
        for (int i = 1; i < skeleton.size(); i++) {
            iterator = skeleton.get(i).getConnectionIterator();
            try {
                do {
                    connection = iterator.next();
                } while(!skeleton.get(i-1).containsConnection(connection));
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Unassociated skeleton of a molecule", e);
            }
            template[i] = new byte[i+1];
            template[i][i] = skeleton.get(i).getAtomicNumber();
            template[i][i-1] = connection.getType();
        }
        // set connection first and last atoms in template
        if (skeletonIsCycled) {
            iterator = skeleton.get(0).getConnectionIterator();
            try {
                do {
                    connection = iterator.next();
                } while(!skeleton.get(skeleton.size() - 1).containsConnection(connection));
            } catch (NoSuchElementException e) {
                throw new IllegalArgumentException("Skeleton is not cycled", e);
            }
            template[skeleton.size() - 1][0] = connection.getType();
        }
        skeletonTemplate = template;
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

    private static boolean compare2dArrays(byte[][] array1, byte[][] array2) {
        boolean arraysAreEquals = true;
        if (array1.length == array2.length) {
            for (int i = 0; i < array1.length; i++) {
                if (!(Arrays.equals(array1[i], array2[i]))) {
                    arraysAreEquals = false;
                    break;
                }
            }
        } else {
            arraysAreEquals = false;
        }
        return arraysAreEquals;
    }
}
