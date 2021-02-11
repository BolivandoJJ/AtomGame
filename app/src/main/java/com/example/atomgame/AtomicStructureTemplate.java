package com.example.atomgame;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.HashSet;
import java.util.Iterator;

public class AtomicStructureTemplate {
    public final String name;
    public final byte[][] connectionMatrix;
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

    private static Resources resources;
    private static final HashSet<AtomicStructureTemplate> simpleMoleculeSet = new HashSet<>();
    private static final HashSet<AtomicStructureTemplate> functionalGroupSet = new HashSet<>();

    // AtomicStructureTemplate class must(!) be initialized before using
    // this method creates sets of atomic structure templates
    public static boolean init(@NonNull Resources resources) {
        if (AtomicStructureTemplate.resources == null) {
            AtomicStructureTemplate.resources = resources;
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
            return true;
        } else {
            return false;
        }
    }

    private static void createSimpleMoleculeSet() {
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_water),
                new byte[][] {{O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_carbon_dioxide),
                new byte[][] {{O},{2,C},{0,2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ammonia),
                new byte[][] {{N}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_hydrogen_peroxide),
                new byte[][] {{O},{1,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitroxyl),
                new byte[][] {{N},{2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitroxyl),
                new byte[][] {{O},{2,N}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ozone),
                new byte[][] {{O},{1,O},{1,1,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitrogen),
                new byte[][] {{N},{3,N}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_oxygen),
                new byte[][] {{O},{2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_hydrazine),
                new byte[][] {{N},{2,N}}));
    }

    private static void createFunctionalGroupSet() {
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydroxyl),
                new byte[][] {{RADICAL},{1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydroperoxide),
                new byte[][] {{RADICAL},{1,O},{0,1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_carbonyl),
                new byte[][] {{RADICAL},{2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_imine),
                new byte[][] {{RADICAL},{2,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_amine),
                new byte[][] {{RADICAL},{1,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydrazone),
                new byte[][] {{RADICAL},{2,N},{0,1,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_nitroso_group),
                new byte[][] {{RADICAL},{1,N},{0,2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_isocyanate),
                new byte[][] {{RADICAL},{1,N},{0,2,C},{0,0,2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_oxime),
                new byte[][] {{RADICAL},{2,N},{0,1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_nitrile),
                new byte[][] {{RADICAL},{1,C},{0,3,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_carboxyl),
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_amide),
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_hydrazide),
                new byte[][] {{RADICAL},{1,C},{0,2,O},{0,1,0,N},{0,0,0,1,N}}));
    }

    private AtomicStructureTemplate(@NonNull String name, @NonNull byte[][] connectionMatrix) {
        this.name = name;
        this.connectionMatrix = connectionMatrix;
    }

    // resources != null, when the class is initialized
    public Iterator<AtomicStructureTemplate> getSimpleMoleculeSetIterator() {
        if (resources != null) {
            return simpleMoleculeSet.iterator();
        } else {
            throw new IllegalStateException("AtomicStructureTemplate was not initialize");
        }
    }

    public Iterator<AtomicStructureTemplate> getFunctionalGroupSetIterator() {
        if (resources != null) {
            return functionalGroupSet.iterator();
        } else {
            throw new IllegalStateException("AtomicStructureTemplate was not initialize");
        }
    }
}