public class Unit {
    public int level, hp, mv, hpToNextLevel;

    public int baseStr, baseMag, baseDex, baseSpd, baseLck, baseDef, baseRes, baseCha;
    public int str, mag, dex, spd, lck, def, res, cha;
    public int statTotal;

    public int atk, hit, crit, as, prt, rsl, avo, rng, critAvo;
    public Weapon heldWeapon;

    public int sword, lance, axe, bow, brawl, reason, faith, authority, heavyArmor, riding, flying;
    public CombatArt[] combatArtsKnown = new CombatArt[6];

    public Item[] inventory = new Item[6];

    public void calcBaseSoftStats() {
        if(heldWeapon.weaponType.equals("Magic")) {
            // attack 
            atk = heldWeapon.mt + mag;

            // hit
            hit = heldWeapon.hit + lck;
        } else {
            // attack stat
            atk = heldWeapon.mt + str;

            // hit, how likely are you to land a hit
            hit = heldWeapon.hit + dex;
        }

        // protection (physical)
        prt = def;

        // resilience (magic)
        rsl = res;

        // attack speed
        as = spd - (heldWeapon.wt - (str/5));

        // avoid, how likely are you to dodge
        avo = as;

        // crit chance
        crit = (dex + lck)/2;

        //crit avoid, how likely are you to avoid an enemy crit
        critAvo = lck;
    }

    public void PreviewAttack(Unit target) {
        calcBaseSoftStats();
        
        // damage that will actually be done
        int damage;
        if(heldWeapon.weaponType.equals("Magic")) {
            damage = atk - target.rsl;
        } else {
            damage = atk - target.prt;
        }

        // chance that damage will actually hit
        int displayedHit = hit - target.avo;

        // chance that damage will crit
        int displayedCrit = crit - target.critAvo;

        // number of times unit will attack
        int numberOfHits = 1;
        if(as - 4 > target.as) {
            numberOfHits *= 2;
        }


        target.calcBaseSoftStats();
        // damage that will actually be done by the other unit
        int targetDamage;
        if(heldWeapon.weaponType.equals("Magic")) {
            targetDamage = target.atk - rsl;
        } else {
            targetDamage = target.atk - prt;
        }

        // chance that damage will actually hit from the other unit
        int targetDisplayedHit = target.hit - avo;

        // chance that damage will crit from the other unit
        int targetDisplayedCrit = target.crit - critAvo;

        // number of times the other unit will make an attack
        int targetNumberOfHits = 1;


        // wait for player input
        if(true) {
            // start battle
            Battle(this, damage, displayedHit, displayedCrit, numberOfHits, target, targetDamage, targetDisplayedHit, targetDisplayedCrit, targetNumberOfHits);
        } else {
            // cancel preview
        }
    }

    public void Battle(Unit attacker, int damage, int displayedHit, int displayedCrit, int numberOfHits, Unit target, int targetDamage, int targetDisplayedHit, int targetDisplayedCrit, int targetNumberOfHits) {
        // attacking unit first
        target.hp -= damage;
        if(target.hp < 0) {return;}
        if(attacker.heldWeapon.weaponType.equals("Gauntlet")) {
            // attack again
            target.hp -= damage;
            if(target.hp < 0) {return;}
        }

        // defending unit next
        attacker.hp -= targetDamage;
        if(hp < 0) {return;}
        if(target.heldWeapon.weaponType.equals("Gauntlet")) {
            // attack again
            attacker.hp -= targetDamage;
            if(hp < 0) {return;}
        }

        // if attack speed is four higher than defender, attack again
        if(numberOfHits == 2) {
            target.hp -= damage;
            if(target.hp < 0) {return;}
            if(attacker.heldWeapon.weaponType.equals("Gauntlet")) {
                target.hp -= damage;
                if(target.hp < 0) {return;}
            }
        }
        
    }
}