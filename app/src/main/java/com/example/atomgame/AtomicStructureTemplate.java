package com.example.atomgame;

import androidx.annotation.NonNull;

import java.util.HashSet;

public class AtomicStructureTemplate {
    public final String NAME;
    public final byte[] ATOM_NUMBER_VECTOR;
    public final byte[][] CONNECTION_MATRIX;

    public static final HashSet<AtomicStructureTemplate> simpleMoleculeSet;
    public static final HashSet<AtomicStructureTemplate> functionalGroupSet;

    public AtomicStructureTemplate(@NonNull String name, @NonNull byte[] atoms, @NonNull byte[][] connections) {
        NAME = name;
        ATOM_NUMBER_VECTOR = atoms;
        CONNECTION_MATRIX = connections;
    }
}