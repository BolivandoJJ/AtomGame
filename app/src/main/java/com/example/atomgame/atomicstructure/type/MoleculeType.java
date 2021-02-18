package com.example.atomgame.atomicstructure.type;

public enum MoleculeType {
    WATER(false, true),
    CARBON_DIOXIDE(false, true),
    AMMONIA(false, true),
    HYDROGEN_PEROXIDE(false, true),
    NITROXYL(false, true),
    OZONE(false, true),
    NITROGEN(false, true),
    OXYGEN(false, true),
    HYDRAZINE(false, true),
    ALKANE(true, false),
    ALKENE(true, false),
    ALKYNE(true, false),
    DIENE(true, false),
    ETHER(false, false),
    PEROXIDE(false, false),
    IMINE(false, false),
    AMINE(false, false),
    AMIDE(false, false),
    ESTER(false, false),
    CARBONATE(false, false),
    ANHYDRIDE(false, false);

    private final boolean isMayHaveFunctionalGroup;
    private final boolean isSimple;
    MoleculeType(boolean isMayHaveFunctionalGroup, boolean isSimple) {
        this.isMayHaveFunctionalGroup = isMayHaveFunctionalGroup;
        this.isSimple = isSimple;
    }

    public boolean isMayHaveFunctionalGroup() {
        return isMayHaveFunctionalGroup;
    }

    public boolean isSimple() {
        return isSimple;
    }
}
