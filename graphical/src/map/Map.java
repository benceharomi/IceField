package map;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import field.Direction;
import field.Field;
import field.Hole;
import field.Stable;
import field.Unstable;
import item.*;
import main.Controller;

/**
 * A terkepet megvalosito singleton osztaly, amely tarolja azon mezoket,
 * amelybol a terkep felepul, illetve ez az osztaly kezeli a hovihart a
 * terkepen.
 */
public class Map implements Serializable {
    /**
     * az oszlopok szama
     */
    private int columns;
    /**
     * a sorok szama
     */
    private int rows;
    /**
     * a terkep mezoi
     */
    private ArrayList<Field> fields = new ArrayList<Field>();
    /**
     * A kezdomezo
     */
    private Stable startField;
    /**
     * randomszem generalasahoz
     */
    Random rand = new Random();

    public Map(int rows, int columns) {
        double holeProbability = 0.1;
        double unstableProbability = 0.3;
        double stableProbability = 1.0 - (holeProbability + unstableProbability);

        this.columns = columns;
        this.rows = rows;
        fields = new ArrayList<Field>(rows * columns);
        boolean startFieldFound = false;
        for (int i = 0; i < rows * columns; i++) {
            double randType = rand.nextDouble();
            int snow = 0;
            Field field;
            if (randType < holeProbability) {
                snow = rand.nextInt(4);
                field = new Hole(snow);
                fields.add(field);
            } else if (randType >= holeProbability && randType < holeProbability + unstableProbability) {
                snow = rand.nextInt(4);
                int capacity = rand.nextInt(5) + 1;
                field = new Unstable(snow, capacity);
                fields.add(field);
            } else if (randType >= 1.0 - stableProbability && randType < 1.0) {
                snow = rand.nextInt(4);
                Stable stable = new Stable(snow);
                field = stable;
                fields.add(field);
                if (!startFieldFound) {
                    startField = stable;
                    startFieldFound = true;
                }
            }
        }
        setNeighbours();
    }

    public Map(int rows, int columns, ArrayList<String> parameters) {
        this.columns = columns;
        this.rows = rows;
        fields = new ArrayList<Field>(rows * columns);
        boolean startFieldFound = false;

        for (int i = 0; i < parameters.size(); i += 2) {
            int snow;
            switch (parameters.get(i).substring(0, 1)) {
                case "h":
                    snow = Integer.parseInt(parameters.get(i + 1));
                    fields.add(new Hole(snow));
                    break;
                case "s":
                    snow = Integer.parseInt(parameters.get(i + 1));
                    Stable stable = new Stable(snow);
                    fields.add(stable);
                    if (!startFieldFound) {
                        startField = stable;
                        startFieldFound = true;
                    }
                    break;
                case "u":
                    int capacity = Integer.parseInt(parameters.get(i).substring(1));
                    snow = Integer.parseInt(parameters.get(i + 1));
                    fields.add(new Unstable(snow, capacity));
                    break;
                default:
                    break;
            }
        }
        setNeighbours();
    }

    public Map(ArrayList<String> parameters) {
        int eskimos = Integer.parseInt(parameters.get(0));
        int explorers = Integer.parseInt(parameters.get(1));
        int playernum = eskimos + explorers;
        int size = 0;
        switch (playernum) {
            case (2):
                size = 4;
                break;
            case (3):
            case (4):
                size = 5;
                break;
            case (5):
            case (6):
                size = 5;
                break;
        }
        columns = size;
        rows = size;
        int partsLeft = 3;
        int holesLeft = size - 1;
        int unstablesLeft = holesLeft;
        for (int i = 0; i < rows * columns; i++) {
            if (i == 0) {
                startField = new Stable(0);
                fields.add(startField);
            } else {
                int ran = new Random().nextInt(10);
                if (ran > 7 && holesLeft > 0) {
                    fields.add(new Hole(new Random().nextInt(5)));
                    holesLeft--;
                } else if (ran >= 5 && unstablesLeft > 0) {
                    fields.add(new Unstable(new Random().nextInt(5), new Random().nextInt(playernum - 1) + 1));
                    unstablesLeft--;
                }

                else
                    fields.add(new Stable(new Random().nextInt(5)));
            }
        }
        setNeighbours();
        while (partsLeft > 0) {
            int ran = new Random().nextInt(size * size);
            if (fields.get(ran).getCanHoldItem() && fields.get(ran).getItem() == null) {
                fields.get(ran).setItem(new FlareGunPart(partsLeft));
                partsLeft--;
            }
        }
        int itemNum = playernum * 2 + 2;
        Item newItem;
        while (itemNum > 0) {
            int ran = new Random().nextInt(size * size);
            if (fields.get(ran).getCanHoldItem() && fields.get(ran).getItem() == null) {
                int type = new Random().nextInt(6);
                switch (type) {
                    case 0:
                        newItem = new BrittleShovel();
                        break;
                    case 1:
                        newItem = new Food();
                        break;
                    case 2:
                        newItem = new Rope();
                        break;
                    case 3:
                        newItem = new ScubaSuit();
                        break;
                    case 4:
                        newItem = new Shovel();
                        break;
                    case 5:
                        newItem = new Tent();
                        break;
                    default:
                        newItem = new Food();
                }
                fields.get(ran).setItem(newItem);
                itemNum--;
            }
        }
    }

    /**
     * Jegvihart indit a tablakon es lerombolja az eloregedett satrakat. Meghivja
     * minden field blizzardF(), illetve destroyShelter metodusat.
     */
    public void blizzardM() {
        double blizzardProbability = 0.5;
        double randBlizzard = rand.nextDouble();
        if (Controller.instance().isRandomBlizzard()) {
            if (randBlizzard < blizzardProbability) {
                for (Field field : fields) {
                    field.blizzardF();
                }
            }
        } else {
            ArrayList<String> params = Controller.instance().getBlizzardParameters();
            for (String param : params) {
                int fieldNum = Integer.parseInt(param) - 1;
                fields.get(fieldNum).blizzardF();
            }
            destroyShelterM();
        }
    }

    /**
     * meghivja az osszes mezo destroyShelter fuggvenyet
     */
    public void destroyShelterM() {
        for (Field field : fields) {
            field.destroyShelter();
        }
    }

    /**
     * Az oszlpok szamat visszaado metodus.
     *
     * @return int az oszlopok szamaval.
     */
    public int getColumns() {
        return columns;
    }

    /**
     * A Fieldeket tarolo ArrayListet visszaado metodus.
     *
     * @return ArrayList<Field> a tarolt fieldekkel
     */
    public ArrayList<Field> getFields() {
        return fields;
    }

    /**
     * A sorok szamat visszaado metodus.
     *
     * @return int a sorok szamaval.
     */
    public int getRows() {
        return rows;
    }

    /**
     * A kezdo fieldet visszaado metodus.
     *
     * @return Field startField
     */
    public Stable getStartField() {
        return startField;
    }

    /**
     * A fieldeken vegigiteralo es azok szomszedait beallito metodus
     */
    private void setNeighbours() {
        for (Field field : fields) {
            int indexOfField = fields.indexOf(field);
            ArrayList<Field> neighbours = new ArrayList<Field>();
            if (indexOfField - columns >= 0) {
                field.setNeighbour(Direction.NORTH, fields.get(indexOfField - columns));
                neighbours.add(fields.get(indexOfField - columns));
            }
            if (indexOfField % columns != columns - 1) {
                field.setNeighbour(Direction.EAST, fields.get(indexOfField + 1));
                neighbours.add(fields.get(indexOfField + 1));
            }
            if (indexOfField + columns < fields.size()) {
                field.setNeighbour(Direction.SOUTH, fields.get(indexOfField + columns));
                neighbours.add(fields.get(indexOfField + columns));
            }
            if (indexOfField % columns != 0) {
                field.setNeighbour(Direction.WEST, fields.get(indexOfField - 1));
                neighbours.add(fields.get(indexOfField - 1));
            }
            //Controller.instance().addNeighbours(field, neighbours);
        }
    }
}
