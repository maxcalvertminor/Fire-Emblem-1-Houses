import java.util.*;

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

    public Vector2 screenPosition;
    public Tile tile;

    public Unit(int st, int ma, int de, int sp, int lc, int deff, int re, int ch, Vector2 sPos) {
        baseStr = st;
        baseMag = ma;
        baseDex = de;
        baseSpd = sp;
        baseLck = lc;
        baseDef = deff;
        baseRes = re;
        baseCha = ch;

        screenPosition = sPos;
    }

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

    public void FindPosition(TileGrid grid) {
        // uses screen coords to find position on grid
        //tile = grid.ScreenCoordToTile(screenPosition);
    }

    public void GetLocalCoordsWithinDistance(int distance) {
        // set that the function will check next
        ArrayList<Vector2> openSet = new ArrayList<Vector2>();

        // tiles we already have checked
        ArrayList<Vector2> closedSet = new ArrayList<Vector2>();

        // tiles we will check next loop
        ArrayList<Vector2> queuedSet = new ArrayList<Vector2>();

        openSet.add(new Vector2(0,0));
        // use range to loop function
        for(int i = 0; i < distance; i++) {
            
            // loop to check every index in openSet
            for(int ii = 0; ii < openSet.size(); ii++) {

                // loop to check a potential index of queuedSet against every index in closedSet
                for(int iii = 0; iii < closedSet.size(); iii++) {
                    if(new Vector2(openSet.get(ii).x, openSet.get(ii).y).Equals(closedSet.get(iii))) {return;}
                }
            }
        }
    }


}