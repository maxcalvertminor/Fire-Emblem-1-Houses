public class Unit {
    public int level, hp, mv, hpToNextLevel;

    public int baseStr, baseMag, baseDex, baseSpd, baseLck, baseDef, baseRes, baseCha;
    public int str, mag, dex, spd, lck, def, res, cha;
    public int statTotal;

    public int atk, hit, crit, as, prt, rsl, avo, rng;
    public Weapon heldWeapon;

    public int sword, lance, axe, bow, brawl, reason, faith, authority, heavyArmor, riding, flying;
    public CombatArt[] combatArtsKnown;

    

    public void calcBaseSoftStats() {
        if(heldWeapon.weaponType.equals("Magic")) {
            atk = heldWeapon.mt + mag;
            hit = heldWeapon.hit + lck;
        } else {
            atk = heldWeapon.mt + str;
            hit = heldWeapon.hit + dex;
        }

        prt = def;
        rsl = res;
        as = spd - (heldWeapon.wt - (str/5));
        avo = as;

    }
}