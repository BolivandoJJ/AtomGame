package com.example.atomgame;

import android.content.res.Resources;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.atomgame.atomicstructure.FunctionalGroup;
import com.example.atomgame.atomicstructure.type.FunctionalGroupType;
import com.example.atomgame.atomicstructure.type.MoleculeType;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MoleculeNameCreator {
    private static Resources resources;
    private static boolean classInitialized;
    private static char[] greeksAlphabet;
    private static String[] aliphaticNamesRoots;
    private static HashMap<MoleculeType, String> aliphaticSuffixes;
    private static String aliphaticEnding;
    private static String substituteSuffix;
    private static HashMap<FunctionalGroupType, String> functionalGroupSuffixes;
    private static HashMap<FunctionalGroupType, String> functionalGroupPrefixes;

    private static HashMap<MoleculeType, String[]> linearMoleculeNamingRules;
    private static HashMap<MoleculeType, String[]> cyclicMoleculeNamingRules;
    private static HashMap<MoleculeType, Byte> cyclicMoleculeRootIncrementationValues;
    private static HashMap<MoleculeType, String> simpleMoleculeNames;

    // TODO: initialize collections
    public static void initResources(@NonNull Resources resources) {
        if (!classInitialized) {
            MoleculeNameCreator.resources = resources;

            classInitialized = true;
        }
    }



    public static String createMoleculeName(@NonNull AtomicStructureTemplate<MoleculeType> moleculeFormula,
                                            boolean moleculeIsCyclic,
                                            @Nullable FunctionalGroup functionalGroup) {
        if (!classInitialized) {
            throw new IllegalStateException("MoleculeNameCreator must be initialized before using. Use initResources(android.content.res.Resources)");
        }
        MoleculeType type = moleculeFormula.getType();
        if (type.isSimple()) {
            return simpleMoleculeNames.get(type);
        } else if (!type.isMayHaveFunctionalGroup()) {
            if (moleculeIsCyclic) {
                byte radicalLength = getSumOfRadicalLengths(moleculeFormula);
                if (radicalLength <= 0) {
                    throw new IllegalArgumentException("Molecule formula must contain radicals with non-zero length");
                }
                Byte increment = cyclicMoleculeRootIncrementationValues.get(type);
                if (increment != null) {
                    radicalLength += increment;
                } else {
                    throw new NullPointerException("cyclicMoleculeRootIncrementationValues was incorrect initialized and has no contains required element.");
                }
                String root;
                if (type == MoleculeType.AMIDE || type == MoleculeType.ESTER) {
                    root = String.valueOf(greeksAlphabet[radicalLength]);
                } else {
                    root = aliphaticNamesRoots[radicalLength];
                }
                String[] namingRule = getCyclicMoleculeRule(type);
                putRootsInRule(namingRule, root);
                return createStringFromRule(namingRule);
            } else {
                byte[] radicalLengths = getRadicalLengths(moleculeFormula);
                String[] roots = new String[radicalLengths.length];
                for (byte i = 0; i < radicalLengths.length; i++) {
                    if (radicalLengths[i] > 0) {
                        roots[i] = aliphaticNamesRoots [i];
                    } else {
                        throw new IllegalArgumentException("Molecule formula must contain radicals with non-zero length");
                    }
                }
                String[] namingRule = getLinearMoleculeRule(type);
                putRootsInRule(namingRule, roots);
                return createStringFromRule(namingRule);
            }
        } else {
            byte radicalLength = getSumOfRadicalLengths(moleculeFormula);
            if (radicalLength <= 0) {
                throw new IllegalArgumentException("Molecule formula must contain radicals with non-zero length");
            }
            String root = aliphaticNamesRoots[radicalLength];
            String[] namingRule;
            if (moleculeIsCyclic) {
                namingRule = getCyclicMoleculeRule(type);
            } else {
                namingRule = getLinearMoleculeRule(type);
            }
            putRootsInRule(namingRule, root);
            return createStringFromRule(namingRule, functionalGroup);
        }
    }

    private static String createStringFromRule(@NonNull String[] rule) {
        StringBuilder builder = new StringBuilder();
        for (String s : rule) {
            builder.append(s);
        }
        return builder.toString();
    }

    private static String createStringFromRule(@NonNull String[] rule,
                                               @Nullable FunctionalGroup functionalGroup) {
        if (functionalGroup == null) {
            return createStringFromRule(rule);
        }
        FunctionalGroupType type = functionalGroup.getType();
        if (functionalGroupSuffixes.get(type) != null) {
            return createStringFromRule(rule) + functionalGroupSuffixes.get(functionalGroup.getType());
        } else if (functionalGroupPrefixes.get(type) != null) {
            return functionalGroupPrefixes.get(functionalGroup.getType()) + createStringFromRule(rule) + aliphaticEnding;
        } else {
            throw new NullPointerException("functionalGroupPrefixes or Suffixes was incorrect initialized and has no contains required element");
        }
    }

    private static void putRootsInRule(@NonNull String[] rule, @NonNull String... roots) {
        byte n = 0;
        for (byte i = 0; i < rule.length; i++) {
            try {
                if (rule[i] == null) {
                    rule[i] = roots[i];
                    n++;
                }
            } catch (ArrayIndexOutOfBoundsException e){
                throw new IllegalArgumentException("The number of roots is less than that required to create the name", e);
            }
        }
        if (n < roots.length) {
            throw new IllegalArgumentException("The number of roots is more than is required to create the name");
        }
    }

    private static byte[] getRadicalLengths(@NonNull AtomicStructureTemplate<MoleculeType> moleculeFormula) {
        byte[][] connectionMatrix = moleculeFormula.getConnectionMatrix();
        byte[] radicalLengths = new byte[connectionMatrix.length];
        byte numOfRadicalLenghts = 0;
        for (byte i = 0; i < connectionMatrix.length; i++) {
            if(connectionMatrix[i][i] < 0) {
                 radicalLengths[numOfRadicalLenghts] = (byte) -connectionMatrix[i][i];
                 numOfRadicalLenghts++;
            }
        }
        radicalLengths = Arrays.copyOf(radicalLengths, numOfRadicalLenghts);
        Arrays.sort(radicalLengths);
        return radicalLengths;
    }

    private static byte getSumOfRadicalLengths(@NonNull AtomicStructureTemplate<MoleculeType> moleculeFormula) {
        byte[][] connectionMatrix = moleculeFormula.getConnectionMatrix();
        byte sumOfRadicalLengths = 0;
        for (byte i = 0; i < connectionMatrix.length; i++) {
            if(connectionMatrix[i][i] < 0) {
                sumOfRadicalLengths += connectionMatrix[i][i];
            }
        }
        return (byte) -sumOfRadicalLengths;
    }

    private static String[] getLinearMoleculeRule(@NonNull MoleculeType type) {
        String[] linearMoleculeRule = linearMoleculeNamingRules.get(type);
        if (linearMoleculeRule != null) {
            return Arrays.copyOf(linearMoleculeRule, linearMoleculeRule.length);
        } else {
            throw new NullPointerException("linearMoleculeRules was incorrect initialized and has no contains required element");
        }
    }

    private static String[] getCyclicMoleculeRule(@NonNull MoleculeType type) {
        String[] cyclicMoleculeRule = cyclicMoleculeNamingRules.get(type);
        if (cyclicMoleculeRule != null) {
            return Arrays.copyOf(cyclicMoleculeRule, cyclicMoleculeRule.length);
        } else {
            throw new NullPointerException("cyclicMoleculeRules was incorrect initialized and has no contains required element");
        }
    }
}
