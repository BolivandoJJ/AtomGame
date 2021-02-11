package com.example.atomgame;

import androidx.annotation.NonNull;

import com.example.atomgame.atomicstructure.FunctionalGroupType;
import com.example.atomgame.atomicstructure.SimpleMoleculeGroupType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class AtomicStructureTemplate<E extends Enum<E>> {
    private final byte[][] connectionMatrix;
    private final E type;
    /**
     * functional group example:
     * (unfilled connections are considered hydrogen connections)
     *    X2
     *    |
     * R-X1=X3
     * adjacency matrix:
     *  R
     * Z10	X1
     * Z20	Z21	X2
     * Z30	Z31	Z32	X3
     * array:
     * {{RADICAL},{Z10,X1},{Z20,Z21,X2},{Z30,Z31,Z32,X3}}
     * Z - connection type
     * Z10 = 1, Z20 = 0, Z30 = 0, Z21 = 1, Z31 = 2, Z32 = 0
     */

    private static final byte O = com.example.atomgame.atom.O.ATOMIC_NUMBER;
    private static final byte C = com.example.atomgame.atom.C.ATOMIC_NUMBER;
    private static final byte N = com.example.atomgame.atom.N.ATOMIC_NUMBER;
    private static final byte RADICAL = -1; //radical identifier

    private static final HashSet<AtomicStructureTemplate<SimpleMoleculeGroupType>> simpleMoleculeSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<FunctionalGroupType>> functionalGroupSet = new HashSet<>();

    //creating sets of atomic structure templates
    static {
        // multithreaded set creation
        Thread simpleMoleculeSetCreatingThread = new Thread(AtomicStructureTemplate::createSimpleMoleculeSet);
        simpleMoleculeSetCreatingThread.start();
        Thread functionalGroupSetCreatingThread = new Thread(AtomicStructureTemplate::createFunctionalGroupSet);
        functionalGroupSetCreatingThread.start();
        try {
            simpleMoleculeSetCreatingThread.join();
            functionalGroupSetCreatingThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void createSimpleMoleculeSet() {
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O}}, SimpleMoleculeGroupType.WATER));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,C},{0,2,O}}, SimpleMoleculeGroupType.CARBON_DIOXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N}}, SimpleMoleculeGroupType.AMMONIA));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O}}, SimpleMoleculeGroupType.HYDROGEN_PEROXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,O}}, SimpleMoleculeGroupType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,N}}, SimpleMoleculeGroupType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O},{1,1,O}}, SimpleMoleculeGroupType.OZONE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{3,N}}, SimpleMoleculeGroupType.NITROGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,O}}, SimpleMoleculeGroupType.OXYGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,N}}, SimpleMoleculeGroupType.HYDRAZINE));
    }

    private static void createFunctionalGroupSet() {
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O}}, FunctionalGroupType.HYDROXYL));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O},{0,1,O}}, FunctionalGroupType.HYDROPEROXIDE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,O}}, FunctionalGroupType.CARBONYL));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,N}}, FunctionalGroupType.IMINE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,N}}, FunctionalGroupType.AMINE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,N},{0,1,N}}, FunctionalGroupType.HYDRAZONE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,N},{0,2,O}}, FunctionalGroupType.NITROSO_GROUP));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,N},{0,2,C},{0,0,2,O}}, FunctionalGroupType.ISOCYANATE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,N},{0,1,O}}, FunctionalGroupType.OXIME));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,3,N}}, FunctionalGroupType.NITRILE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,O}}, FunctionalGroupType.CARBOXYL));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N}}, FunctionalGroupType.AMIDE));
        functionalGroupSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N},{0,0,0,1,N}}, FunctionalGroupType.HYDRAZIDE));
    }

    public AtomicStructureTemplate(@NonNull byte[][] connectionMatrix, @NonNull E type) {
        this.connectionMatrix = connectionMatrix;
        this.type = type;
    }

    public E getType() {
        return type;
    }

    public byte[][] getConnectionMatrix() {
        return Arrays.copyOf(connectionMatrix, connectionMatrix.length);
    }

    public static Iterator<AtomicStructureTemplate<SimpleMoleculeGroupType>> getSimpleMoleculeSetIterator() {
        return simpleMoleculeSet.iterator();
    }

    public static Iterator<AtomicStructureTemplate<FunctionalGroupType>> getFunctionalGroupSetIterator() {
        return functionalGroupSet.iterator();
    }
}