import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class PlayerController extends MouseAdapter {

    // jframe the whole game runs off of
    public MainFrame frame;

    // current grid that the mouse should be using
    public TileGrid grid;

    // tileMode being on means the mouse can select tiles and ONLY tiles
    public boolean tileMode;
    public Unit unit;

    // speed units move in the movement animation
    public double moveSpeed;
    // how fast the animation delays between runs
    public double animRate = 10;

    public PlayerController(MainFrame fr, double speed) {
        frame = fr;
        moveSpeed = speed;
    }
    public void SetGrid(TileGrid g) {
        grid = g;
        tileMode = true;
        //System.out.println("Grid is set");
    }

    public void mouseClicked(MouseEvent mouseEvent) {
        if(tileMode == true) {
            Tile tile = grid.FrameCoordToTile(new Vector2(mouseEvent.getX(), mouseEvent.getY()));
            System.out.println(tile);
            // if the tile clicked on has a unit
            if(tile.unitOnTile != null) {
                if(unit == null) {
                    // set selected unit if there is no current selected unit
                    unit = tile.unitOnTile;
                    System.out.println(unit);
                    // make cancel button visible to clear selected unit
                } else if(unit != null) {
                    // if there is a selected unit, and another unit is clicked, move to and attack that unit
                    tileMode = false;
                    PreviewAttack(unit, tile.unitOnTile);
                    unit = null;
                    tile = null;
                }
            // else if the tile is empty/no unit  
            } else {
                // if a unit is not selected
                if(unit == null) {
                    // INCOMPLETE
                    // display standard menu
                    // menu options should at least be: end turn, map, exit
                } else if(unit != null) {
                    // if a unit is selected, check movement and if all good, move to that tile
                    System.out.println("Movestep 1");
                    Move(unit, tile);
                    unit = null;
                    tile = null;
                    /*if(grid.TileInDistance(unit.tile, unit.mv)) {
                        Move(unit, tile);
                        unit = null;
                        tile = null;
                    } else {
                        System.out.println("Out of range");
                        unit = null;
                        tile = null;
                    }*/
                    
                } 
            }
        }
    }

    public void MouseMoved(MouseEvent mouseEvent) {
        
    }

    public void Move(Unit unit, Tile targetTile) {
        System.out.println("Movestep 2");
        // call this to move a unit from one tile to another
        // everything is built in, calling MoveAnimation separately isn't needed
        Tile startTile = unit.tile;
        double changeInX = targetTile.framePosition.x - startTile.framePosition.x;
        double changeInY = targetTile.framePosition.y - startTile.framePosition.y;
        System.out.println(new Vector2(changeInX, changeInY));

        MoveAnimation(unit, new Vector2(changeInX, changeInY));
    }

    public void MoveAnimation(Unit unit, Vector2 distanceToMove) {
        System.out.println("Movestep 3");
        // called in Move()
        // animation to move unit from one tile to another
        java.util.Timer timer = new java.util.Timer();
        MoveAnimationTimerTask task = new MoveAnimationTimerTask(unit, new Vector2(0, moveSpeed), timer);
        
        task.distanceTotalToMove = new Vector2(0, distanceToMove.y);
        task.distanceTotalMoved = Vector2.zero;
        timer.schedule(task, 0, (long)animRate);
    }

    public class MoveAnimationTimerTask extends TimerTask{
        // created in MoveAnimation
        // incremental method that gets repeated to move the unit, TimerTask scheduled in MoveAnimation
        Unit unitToMove;
        Vector2 distanceToMove;
        public Vector2 distanceTotalToMove;
        public Vector2 distanceTotalMoved;
        public java.util.Timer timer;

        public MoveAnimationTimerTask(Unit u, Vector2 d, java.util.Timer t) {
            unitToMove = u;
            distanceToMove = d;
            timer = t;
            System.out.println(distanceToMove);
        }
        public void run() {
            //System.out.println("Task running, progress: " + distanceTotalMoved + " / " + distanceTotalToMove);
            //System.out.println(distanceTotalMoved.Equals(distanceTotalToMove));
            if(distanceTotalMoved.Equals(distanceTotalToMove)) {
                unitToMove.RefreshPosition();
                timer.cancel();
            }

            if(distanceToMove.sqrMagnitude > distanceTotalToMove.sqrMagnitude - distanceTotalMoved.sqrMagnitude) {
                // if the distance the anim would move is greater than what the total distance wants, just move to the total distance instead.
                unitToMove.framePosition = unitToMove.framePosition.Add(distanceTotalToMove.Add(distanceTotalMoved.ScaleFactor(-1)));
                distanceTotalMoved = distanceTotalToMove;
                unitToMove.RefreshPosition();
                return;
            }
            unitToMove.framePosition = unitToMove.framePosition.Add(distanceToMove);
            distanceTotalMoved = distanceTotalMoved.Add(distanceToMove);
            unitToMove.RefreshPosition();
        }

    }

    public void DisplayRangeOfUnit(Unit unit) {
        ArrayList<Tile> inRangeTiles = grid.GetTilesWithinDistance(unit.tile, unit.rng);

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
