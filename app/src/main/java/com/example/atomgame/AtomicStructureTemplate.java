package com.example.atomgame;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class AtomicStructureTemplate {
    public final String NAME;
    public final byte[] ATOM_NUMBER_VECTOR;
    public final byte[][] CONNECTION_MATRIX;

    private static final byte O = com.example.atomgame.atom.O.ATOMIC_NUMBER;
    private static final byte C = com.example.atomgame.atom.C.ATOMIC_NUMBER;
    private static final byte N = com.example.atomgame.atom.N.ATOMIC_NUMBER;

    private static Resources resources;
    public static final HashSet<AtomicStructureTemplate> simpleMoleculeSet;
    public static final HashSet<AtomicStructureTemplate> functionalGroupSet;


    public static boolean init(@NonNull Resources resources) {
        if (resources != null) {
            AtomicStructureTemplate.resources = resources;
            initSimpleMoleculeSet();
            initFunctionalGroupSet();
            return true;
        } else {
            return false;
        }
    }

    private static void initSimpleMoleculeSet() {
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_water),
                new byte[] {O},
                new byte[][] {{0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_carbon_dioxide),
                new byte[] {O,C,O},
                new byte[][] {{0,2,0},{2,0,2},{0,2,0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ammonia),
                new byte[] {N},
                new byte[][] {{0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_hydrogen_peroxide),
                new byte[] {O,O},
                new byte[][] {{0,1},{1,0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitroxyl),
                new byte[] {N,O},
                new byte[][] {{0,2},{2,0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ozone),
                new byte[] {O,O,O},
                new byte[][] {{0,1,1},{1,0,1},{1,1,0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitrogen),
                new byte[] {N,N},
                new byte[][] {{0,3},{3,0}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_oxygen),
                new byte[] {O,O},
                new byte[][] {{0,2},{2,0}}));
    }

    public AtomicStructureTemplate(@NonNull String name, @NonNull byte[] atoms, @NonNull byte[][] connections) {
        NAME = name;
        ATOM_NUMBER_VECTOR = atoms;
        CONNECTION_MATRIX = connections;
    }
}