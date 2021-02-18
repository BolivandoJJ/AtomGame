package com.example.atomgame.atomicstructure.type;

public enum MoleculeType {
    WATER(false),
    CARBON_DIOXIDE(false),
    AMMONIA(false),
    HYDROGEN_PEROXIDE(false),
    NITROXYL(false),
    OZONE(false),
    NITROGEN(false),
    OXYGEN(false),
    HYDRAZINE(false),

    ALKANE(true),
    ALKENE(true),
    ALKYNE(true),
    DIENE(true),
    ETHER(false),
    PEROXIDE(false),
    IMINE(false),
    AMINE(false),
    AMIDE(false),
    ESTER(false),
    CARBONATE(false),
    ANHYDRIDE(false);

    private final boolean mayHaveFunctionalGroup;
    MoleculeType(boolean mayHaveFunctionalGroup) {
        this.mayHaveFunctionalGroup = mayHaveFunctionalGroup;
    }

    public boolean isMayHaveFunctionalGroup() {
        return mayHaveFunctionalGroup;
    }
}
