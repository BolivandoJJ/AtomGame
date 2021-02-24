package com.example.atomgame;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atom.Atom;
import com.example.atomgame.atom.H;
import com.example.atomgame.atomicstructure.Molecule;
import com.example.atomgame.atomicstructure.FunctionalGroup;
import com.example.atomgame.atomicstructure.type.MoleculeType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class AtomicStructureFactory {
    private final ArrayList<Atom> skeleton;
    private final boolean skeletonIsCycled;
    private ArrayList<HashSet<FunctionalGroup>> listOfFunctionalGroupSet;
    private byte status;
    private Molecule molecule;
    public static final byte DEFAULT_STATUS = 0;
    public static final byte MOLECULE_NOT_FOUND_STATUS = -1;
    public static final byte NEEDS_A_FUNCTIONAL_GROUP_SELECTION_STATUS = 1;
    public static final byte MOLECULE_CREATED_STATUS = 2;

    public AtomicStructureFactory(@NonNull ArrayList<Atom> skeleton, boolean skeletonIsCycled) {
        this.skeleton = skeleton;
        this.skeletonIsCycled = skeletonIsCycled;
        status = DEFAULT_STATUS;
    }

    // TODO: change logic
    private void parseSkeleton() {
        byte[][] skeletonMatrix = createSkeletonMatrix();
        boolean parsingWasSuccessful;
        if (skeletonIsCycled) {
            parsingWasSuccessful = parseCycledSkeleton();
        } else {
            parsingWasSuccessful = parseLinearSkeleton();
        }
        if (!parsingWasSuccessful) {
            status = MOLECULE_NOT_FOUND_STATUS;
        }
    }

    private byte[][] createSkeletonMatrix() {
        byte[][] matrix = new byte[skeleton.size()][];
        matrix[0] = new byte[1];
        matrix[0][0] = skeleton.get(0).getAtomicNumber();
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
            matrix[i] = new byte[i+1];
            matrix[i][i] = skeleton.get(i).getAtomicNumber();
            matrix[i][i-1] = connection.getType();
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
            matrix[skeleton.size() - 1][0] = connection.getType();
        }
        return matrix;
    }

    // TODO: change logic
    private boolean parseCycledSkeleton() {
        boolean parsingWasSuccessful;
        if (parsingWasSuccessful = parseSimpleMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseHeterocyclicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseHomocyclicMolecule()) {
            return parsingWasSuccessful;
        } else if (parsingWasSuccessful = parseFunctionalGroup()) {
            return parsingWasSuccessful;
        } else {
            return parsingWasSuccessful = false;
        }
    }

    // TODO: change logic
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

    private boolean parseSimpleMolecule(@NonNull byte[][] skeletonMatrix) {
        Iterator<AtomicStructureTemplate<MoleculeType>> iterator = AtomicStructureTemplate.getSimpleMoleculeSetIterator();
        AtomicStructureTemplate<MoleculeType> template;
        while (iterator.hasNext()) {
            template = iterator.next();
            if (compare2dArrays(template.getConnectionMatrix(), skeletonMatrix)) {
                molecule = createMolecule(null, template);
                status = MOLECULE_CREATED_STATUS;
                return true;
            }
        }
        return false;
    }

    private Molecule createMolecule(@Nullable FunctionalGroup functionalGroup,
                                    @NonNull AtomicStructureTemplate<MoleculeType> sourceTemplate,
                                    @NonNull byte... radicalLengths) {
        HashSet<Atom> atomSet = createAtomSet(functionalGroup);
        AtomicStructureTemplate<MoleculeType> moleculeFormula = createMoleculeFormula(sourceTemplate, radicalLengths);
        String name = MoleculeNameCreator.createMoleculeName(moleculeFormula, skeletonIsCycled, functionalGroup);
        return new Molecule(atomSet, functionalGroup, skeleton, skeletonIsCycled, name, moleculeFormula);
    }

    private AtomicStructureTemplate<MoleculeType> createMoleculeFormula(@NonNull AtomicStructureTemplate<MoleculeType> sourceTemplate,
                                                                        @NonNull byte... radicalLengths) {
        byte[][] matrix = sourceTemplate.getConnectionMatrix();
        Arrays.sort(radicalLengths);
        // checking input for negative values
        for (byte radicalLength : radicalLengths) {
            if (radicalLength < 0) {
                throw new IllegalArgumentException("The radical length must be non-negative");
            }
        }
        // checking number of input values and adding lengths of radicals to matrix
        byte numOfRadicals = 0;
        for (byte i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == AtomicStructureTemplate.RADICAL) {
                numOfRadicals++;
                try {
                    matrix[i][i] = (byte) -radicalLengths[i];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("The number of length parameters less than the number of radicals", e);
                }
            }
        }
        if (radicalLengths.length > numOfRadicals) {
            throw new IllegalArgumentException("The number of length parameters exceeds the number of radicals");
        }
        return new AtomicStructureTemplate<>(matrix, sourceTemplate.getType());
    }

    private HashSet<Atom> createAtomSet(@Nullable FunctionalGroup functionalGroup) {
        HashSet<Atom> atomSet = new HashSet<>();
        // adding functional group atoms to set
        if (functionalGroup != null) {
            Iterator<Atom> iterator = functionalGroup.getAtomSetIterator();
            while(iterator.hasNext()) {
                atomSet.add(iterator.next());
            }
        }
        // adding skeleton atoms and connected hydrogen to atomSet
        for (Atom atom : skeleton) {
            atomSet.add(atom);
            Iterator<Connection> iterator = atom.getConnectionIterator();
            while (iterator.hasNext()) {
                Atom[] connectedAtoms = iterator.next().getConnectedAtoms();
                for (Atom connectedAtom : connectedAtoms) {
                    if (connectedAtom.getAtomicNumber() == H.ATOMIC_NUMBER) {
                        atomSet.add(connectedAtom);
                    }
                }
            }
        }
        return atomSet;
    }

    public byte getStatus() {
        return status;
    }


    public ArrayList<Iterator<FunctionalGroup>> getFunctionalGroups() {
        if (status == NEEDS_A_FUNCTIONAL_GROUP_SELECTION_STATUS) {
            ArrayList<Iterator<FunctionalGroup>> functionalGroupIteratorList = new ArrayList<>();
            for (HashSet<FunctionalGroup> functionalGroupSet : listOfFunctionalGroupSet) {
                functionalGroupIteratorList.add(functionalGroupSet.iterator());
            }
            return functionalGroupIteratorList;
        } else {
            return null;
        }
    }

    // method returns true if arrays are equals
    private static boolean compare2dArrays(@NonNull byte[][] array1, @NonNull byte[][] array2) {
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
