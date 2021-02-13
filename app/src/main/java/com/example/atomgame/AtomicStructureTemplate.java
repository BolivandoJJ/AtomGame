package com.example.atomgame;

import androidx.annotation.NonNull;

import com.example.atomgame.atomicstructure.type.FunctionalGroupType;
import com.example.atomgame.atomicstructure.type.LinearMoleculeType;
import com.example.atomgame.atomicstructure.type.SimpleMoleculeType;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;

public class AtomicStructureTemplate<T extends Enum<T>> {
    private final byte[][] connectionMatrix;
    private final T type;
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
    private static final byte RADICAL = Byte.MIN_VALUE; //radical identifier

    private static final HashSet<AtomicStructureTemplate<SimpleMoleculeType>> simpleMoleculeSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<FunctionalGroupType>> functionalGroupSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate<LinearMoleculeType>>  linearMoleculeSet = new HashSet<>();

    //creating sets of atomic structure templates
    static {
        runMultipleThreads(
                new Thread(AtomicStructureTemplate::createSimpleMoleculeSet),
                new Thread(AtomicStructureTemplate::createFunctionalGroupSet),
                new Thread(AtomicStructureTemplate::createLinearMoleculeSet));
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
                new byte[][] {{O}}, SimpleMoleculeType.WATER));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,C},{0,2,O}}, SimpleMoleculeType.CARBON_DIOXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N}}, SimpleMoleculeType.AMMONIA));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O}}, SimpleMoleculeType.HYDROGEN_PEROXIDE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,O}}, SimpleMoleculeType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,N}}, SimpleMoleculeType.NITROXYL));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{1,O},{1,1,O}}, SimpleMoleculeType.OZONE));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{3,N}}, SimpleMoleculeType.NITROGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{O},{2,O}}, SimpleMoleculeType.OXYGEN));
        simpleMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{N},{2,N}}, SimpleMoleculeType.HYDRAZINE));
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
                new byte[][] {{RADICAL}}, LinearMoleculeType.ALKANE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2, RADICAL}}, LinearMoleculeType.ALKENE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2, RADICAL},{0,2,RADICAL}}, LinearMoleculeType.DIENE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{3, RADICAL}}, LinearMoleculeType.ALKYNE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O},{0,1,RADICAL}}, LinearMoleculeType.ETHER));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,O},{0,1,O},{0,0,1,RADICAL}}, LinearMoleculeType.PEROXIDE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{2,N},{0,1,RADICAL}}, LinearMoleculeType.IMINE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,N},{0,1,RADICAL}}, LinearMoleculeType.AMINE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N},{0,0,0,1,RADICAL}}, LinearMoleculeType.AMIDE));
        linearMoleculeSet.add(new AtomicStructureTemplate<>(
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,O},{0,0,0,1,RADICAL}}, LinearMoleculeType.ESTER));
    }

    private AtomicStructureTemplate(@NonNull byte[][] connectionMatrix, @NonNull T type) {
        this.connectionMatrix = connectionMatrix;
        this.type = type;
    }

    public AtomicStructureTemplate(@NonNull AtomicStructureTemplate<T> sourceTemplate,
                                   @NonNull byte... radicalLengths) {
        byte[][] matrix = sourceTemplate.getConnectionMatrix();
        // checking input for negative values
        for (byte radicalLength : radicalLengths) {
            if (radicalLength < 1) {
                throw new IllegalArgumentException("The radical length must be above zero");
            }
        }
        // checking number of input values and adding lengths of radicals to matrix
        byte numOfRadicals = 0;
        for (byte i = 0; i < matrix.length; i++) {
            if (matrix[i][i] == RADICAL) {
                numOfRadicals++;
                try {
                    matrix[i][i] = radicalLengths[i];
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new IllegalArgumentException("The number of length parameters less than the number of radicals", e);
                }
            }
        }
        if (radicalLengths.length != numOfRadicals) {
            throw new IllegalArgumentException("The number of length parameters exceeds the number of radicals");
        }
        this.connectionMatrix = matrix;
        this.type = (T) sourceTemplate.getType();
    }

    public T getType() {
        return type;
    }

    public byte[][] getConnectionMatrix() {
        return Arrays.copyOf(connectionMatrix, connectionMatrix.length);
    }

    public static Iterator<AtomicStructureTemplate<SimpleMoleculeType>> getSimpleMoleculeSetIterator() {
        return simpleMoleculeSet.iterator();
    }

    public static Iterator<AtomicStructureTemplate<FunctionalGroupType>> getFunctionalGroupSetIterator() {
        return functionalGroupSet.iterator();
    }
    
    public static Iterator<AtomicStructureTemplate<LinearMoleculeType>> getLinearMoleculeSetIterator() {
        return linearMoleculeSet.iterator();
    }
}