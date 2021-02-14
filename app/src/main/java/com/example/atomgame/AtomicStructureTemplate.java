package com.example.atomgame;

import androidx.annotation.NonNull;

import com.example.atomgame.atomicstructure.type.FunctionalGroupType;
import com.example.atomgame.atomicstructure.type.MoleculeType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class AtomicStructureTemplate<T extends Enum<T>> {
    private final byte[][] connectionMatrix;
    private final T type;
    /**
     * Template example (functional group):
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

    public static final byte O = com.example.atomgame.atom.O.ATOMIC_NUMBER;
    public static final byte C = com.example.atomgame.atom.C.ATOMIC_NUMBER;
    public static final byte N = com.example.atomgame.atom.N.ATOMIC_NUMBER;
    public static final byte RADICAL = Byte.MIN_VALUE; //radical identifier

    private static final HashSet<AtomicStructureTemplate<FunctionalGroupType>> functionalGroupSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<MoleculeType>> simpleMoleculeSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<MoleculeType>>  linearMoleculeSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<MoleculeType>>  cyclicMoleculeSet = new HashSet<>();

    //creating sets of atomic structure templates
    static {
        runMultipleThreads(
                new Thread(AtomicStructureTemplate::createSimpleMoleculeSet),
                new Thread(AtomicStructureTemplate::createFunctionalGroupSet),
                new Thread(AtomicStructureTemplate::createLinearMoleculeSet),
                new Thread(AtomicStructureTemplate::createCyclicMoleculeSet));

    }

    private static void runMultipleThreads(Thread... threads) {
        for (Thread t : threads) {
            t.start();
        }
        for (Thread t : threads) {
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void createSimpleMoleculeSet() {
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O}}, MoleculeType.WATER));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,C},{0,2,O}}, MoleculeType.CARBON_DIOXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N}}, MoleculeType.AMMONIA));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O}}, MoleculeType.HYDROGEN_PEROXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,O}}, MoleculeType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,N}}, MoleculeType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O},{1,1,O}}, MoleculeType.OZONE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{3,N}}, MoleculeType.NITROGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,O}}, MoleculeType.OXYGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,N}}, MoleculeType.HYDRAZINE));
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

    private static void createLinearMoleculeSet() {
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL}}, MoleculeType.ALKANE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2, RADICAL}}, MoleculeType.ALKENE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2, RADICAL},{0,2,RADICAL}}, MoleculeType.DIENE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{3, RADICAL}}, MoleculeType.ALKYNE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O},{0,1,RADICAL}}, MoleculeType.ETHER));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O},{0,1,O},{0,0,1,RADICAL}}, MoleculeType.PEROXIDE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,N},{0,1,RADICAL}}, MoleculeType.IMINE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,N},{0,1,RADICAL}}, MoleculeType.AMINE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N},{0,0,0,1,RADICAL}}, MoleculeType.AMIDE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,O},{0,0,0,1,RADICAL}}, MoleculeType.ESTER));
    }

    private static void createCyclicMoleculeSet() {

    }

    public AtomicStructureTemplate(@NonNull byte[][] connectionMatrix, @NonNull T type) {
        this.connectionMatrix = connectionMatrix;
        this.type = type;
    }

    public T getType() {
        return type;
    }

    public byte[][] getConnectionMatrix() {
        return Arrays.copyOf(connectionMatrix, connectionMatrix.length);
    }

    public static Iterator<AtomicStructureTemplate<MoleculeType>> getSimpleMoleculeSetIterator() {
        return simpleMoleculeSet.iterator();
    }

    public static Iterator<AtomicStructureTemplate<FunctionalGroupType>> getFunctionalGroupSetIterator() {
        return functionalGroupSet.iterator();
    }
    
    public static Iterator<AtomicStructureTemplate<MoleculeType>> getLinearMoleculeSetIterator() {
        return linearMoleculeSet.iterator();
    }

    public static Iterator<AtomicStructureTemplate<MoleculeType>> getCyclicMoleculeSetIterator() {
        return cyclicMoleculeSet.iterator();
    }
}