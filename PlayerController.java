import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class PlayerController extends MouseAdapter {

    // current grid that the mouse should be using
    public TileGrid grid;

    // tileMode being on means the mouse can select tiles and ONLY tiles
    public boolean tileMode;
    public Unit unit;

    public void SetGrid(TileGrid g) {
        grid = g;
        tileMode = true;
    }

    public void MouseClicked(MouseEvent mouseEvent) {
        if(tileMode == true) {
            Tile tile = grid.FrameCoordToTile(new Vector2(mouseEvent.getX(), mouseEvent.getY()));

            if(unit == null) {
                // set selected unit if there is no current selected unit
                unit = tile.unitOnTile;

                // make cancel button visible to clear selected unit
            } else if(unit != null) {
                // if there is a selected unit, and another unit is clicked, move to and attack that unit
                tileMode = false;
                PreviewAttack(unit, tile.unitOnTile);
            }  
        } else {
            // INCOMPLETE
            // this is for menuing, selecting a move, selecting to wait on a unit, selecting to cancel, etc.
        }
        

    }

    public void MouseMoved(MouseEvent mouseEvent) {
        
    }

    public void DisplayRangeOfUnit(Unit unit) {
        ArrayList<Tile> inRangeTiles = grid.GetTilesWithinDistance(unit.rng, unit.tile);

        for(Tile tile : inRangeTiles) {
            // INCOMPLETE
            // access UI element, set color to red
        }
    }

    public void ResetTileColor() {
        // INCOMPLETE
        // run after ALL mouseEvents
        for(int r = 0; r < grid.grid.length; r++) {
            for(int c = 0; c < grid.grid[r].length; c++) {
                // set grid.grid[r][c] color to standard color
            }
        }
    }

    public void PreviewAttack(Unit attacker, Unit defender) {
        attacker.calcBaseSoftStats();
        defender.calcBaseSoftStats();
        
        // damage that will actually be done
        int damage;
        if(attacker.heldWeapon.weaponType.equals("Magic")) {
            damage = attacker.atk - defender.rsl;
        } else {
            damage = attacker.atk - defender.prt;
        }

        // chance that damage will actually hit
        int displayedHit = attacker.hit - defender.avo;

        // chance that damage will crit
        int displayedCrit = attacker.crit - defender.critAvo;

        // number of times unit will attack
        int numberOfHits = 1;
        if(attacker.as - 4 > defender.as) {
            numberOfHits *= 2;
        }


        // damage that will actually be done by the other unit
        int defenderDamage;
        if(attacker.heldWeapon.weaponType.equals("Magic")) {
            defenderDamage = defender.atk - attacker.rsl;
        } else {
            defenderDamage = defender.atk - attacker.prt;
        }

        // chance that damage will actually hit from the other unit
        int defenderDisplayedHit = defender.hit - attacker.avo;

        // chance that damage will crit from the other unit
        int defenderDisplayedCrit = defender.crit - attacker.critAvo;

        // number of times the other unit will make an attack
        int defenderNumberOfHits = 1;

        
        // INCOMPLETE
        // wait for player input
        if(true) {
            // start battle
            Battle(attacker, damage, displayedHit, displayedCrit, numberOfHits, defender, defenderDamage, defenderDisplayedHit, defenderDisplayedCrit, defenderNumberOfHits);
        } else {
            // cancel preview
        }
    }

    public void Battle(Unit attacker, int damage, int displayedHit, int displayedCrit, int numberOfHits, Unit defender, int defenderDamage, int defenderDisplayedHit, int defenderDisplayedCrit, int defenderNumberOfHits) {
        // attacking unit first
        defender.hp -= damage;
        if(defender.hp < 0) {return;}
        if(attacker.heldWeapon.weaponType.equals("Gauntlet")) {
            // attack again
            defender.hp -= damage;
            if(defender.hp < 0) {return;}
        }

        // defending unit next
        attacker.hp -= defenderDamage;
        if(attacker.hp < 0) {return;}
        if(defender.heldWeapon.weaponType.equals("Gauntlet")) {
            // attack again
            attacker.hp -= defenderDamage;
            if(attacker.hp < 0) {return;}
        }

        // if attack speed is four higher than defender, attack again
        if(numberOfHits == 2) {
            defender.hp -= damage;
            if(defender.hp < 0) {return;}
            if(attacker.heldWeapon.weaponType.equals("Gauntlet")) {
                defender.hp -= damage;
                if(defender.hp < 0) {return;}
            }
        }
        
    }
}
