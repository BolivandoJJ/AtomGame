package com.example.atomgame;

import android.content.res.Resources;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class AtomicStructureTemplate {
    public final String name;
    public final byte[][] connectionMatrix;

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
                new byte[][] {{O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_carbon_dioxide),
                new byte[][] {{O},{2,C},{0,2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ammonia),
                new byte[][] {{N}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_hydrogen_peroxide),
                new byte[][] {{O},{1,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitroxyl),
                new byte[][] {{N},{2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_ozone),
                new byte[][] {{O},{1,O},{1,1,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_nitrogen),
                new byte[][] {{N},{3,N}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_oxygen),
                new byte[][] {{O},{2,O}}));
        simpleMoleculeSet.add(new AtomicStructureTemplate(resources.getString(R.string.molecule_name_hydrazine),
                new byte[][] {{N},{2,N}}));
    }

    private static void initFunctionalGroupSet() {
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydroxyl),
                new byte[][] {{1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydroperoxide),
                new byte[][] {{1,O},{0,1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_carbonyl),
                new byte[][] {{2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_imine),
                new byte[][] {{2,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_amine),
                new byte[][] {{1,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_hydrazone),
                new byte[][] {{2,N},{0,1,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_nitroso_group),
                new byte[][] {{1,N},{0,2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_isocyanate),
                new byte[][] {{1,N},{0,2,C},{0,0,2,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_oxime),
                new byte[][] {{2,N},{0,1,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_nitrile),
                new byte[][] {{1,C},{0,3,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_carboxyl),
                new byte[][] {{1,C},{0,2,O},{0,1,0,O}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_name_amide),
                new byte[][] {{1,C},{0,2,O},{0,1,0,N}}));
        functionalGroupSet.add(new AtomicStructureTemplate(resources.getString(R.string.functional_group_hydrazide),
                new byte[][] {{1,C},{0,2,O},{0,1,0,N},{0,0,0,1,N}}));
    }

    public AtomicStructureTemplate(@NonNull String name, @NonNull byte[][] connectionMatrix) {
        this.name = name;
        this.connectionMatrix = connectionMatrix;
    }
}