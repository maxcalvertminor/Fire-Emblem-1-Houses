import java.util.*;

public class Unit {

    // jframe the whole game runs off of
    public MainFrame frame;

    // mv is movement, rest is hopefully self explanatory 
    public int level, hp, mv, xpToNextLevel;

    // strength, magic, dexterity, speed, luck, defense, resistance, charm
    public int baseStr, baseMag, baseDex, baseSpd, baseLck, baseDef, baseRes, baseCha;
    public int str, mag, dex, spd, lck, def, res, cha;
    public int statTotal;

    // attack, hit, crit, attack speed, protection, resilience, avoidance, range, crit avoidance
    public int atk, hit, crit, as, prt, rsl, avo, rng, critAvo;

    // current weapon in hand
    public Weapon heldWeapon;

    // skills in each of these stats, 0 is F, 1 is F+, so on so forth to 16 is S
    public int sword, lance, axe, bow, brawl, reason, faith, authority, heavyArmor, riding, flying;
    public CombatArt[] combatArtsKnown = new CombatArt[6];

    // inventory that holds type Item, everything that can be held in inventory at least extends Item
    public Item[] inventory = new Item[6];

    // position in terms of the frame, rather than screen or local
    public Vector2 framePosition;

    public Vector2 uiPosition;

    // tile this unit is currently on
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

        framePosition = sPos;
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

    //-----------------------------------------------------------
    // INCOMPLETE
    // this is the section for the jLabel that represents player


    
    //-----------------------------------------------------------


}