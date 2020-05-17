package skeleton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Skeleton
 * A skeleton singleton osztaly szolgal az osszes fuggvenyhivas kiiratasara.
 * Attributumok:
 * -single_instance: a Skeleton egyetlen peldanyat tarolja
 * -tabNum: a behuzasok szamat tarolja
 * -objectList: Az objektumok nevet tarolja az objektumok szerint rendezve
 * -active: azt tarolja, aktiv-e a kiiratas
 * Metodusok:
 * - Skeleton(): privat konstruktor
 * + setActive(boolean b): be lehet vele allitani, aktiv-e a kiiras
 * + instance(): visszaadja a skeleton egyetlen peldanyat
 * + called(Object o, String functionName): kiirja a hivott fuggvenyt, es hogy melyik objektumon hivjak, es megnoveli a tabnumot
 * + public static void returned(): lecsokkenti a tabnumot
 * + public static void created(Object o, String objectName, String objectType): kiirja a konstukciokat
 */

public class Skeleton {
    private static Skeleton single_instance = null;
    private static int tabNum;
    private static HashMap<Object, String> objectList = new HashMap<>();
    private static boolean active = true;

    private Skeleton() {
        tabNum = 0;
    }

    public static void setActive(boolean b){
        active = b;
    }

    public static Skeleton instance() {
        if (single_instance == null)
            single_instance = new Skeleton();
        return single_instance;
    }

    public static void called(Object o, String functionName) {
        if(!active) return;
        for (int i = 0; i < tabNum; i++)
            System.out.print('\t');
        System.out.print(objectList.get(o) + '.' + functionName);
        System.out.println();
        tabNum++;
    }

    public static void returned() {
        if(!active) return;
        tabNum--;
    }

    public static void created(Object o, String objectName, String objectType) {
        if(!active) return;
        for(int i = 0; i < tabNum; i++){
            System.out.print('\t');
        }
        System.out.print("uj " + objectType + " tipusu objektum letrehozasa " + objectName + " neven");
        System.out.println();
        tabNum++;
        objectList.put(o, objectName);
    }
}
