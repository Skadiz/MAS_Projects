package objects;

import java.io.PrintStream;
import java.io.Serializable;
import java.util.*;



public abstract class ObjectPlusPlus extends ObjectPlus implements Serializable{
    private Map<String, Map<Object, ObjectPlusPlus>> links = new Hashtable<>();

    private static Set<ObjectPlusPlus> allParts = new HashSet<>();

    public ObjectPlusPlus() {
        super();
    }

    //method (prywatny, użytkowy)
    private void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier, int counter) {
        Map<Object, ObjectPlusPlus> objectLinks;

            //ochrona połączenia zwrotnego
        if(counter < 1) {
            return;
        }

        // szukamy powiazania
        if(links.containsKey(roleName)) {
            objectLinks = links.get(roleName);
        }
        else {
            // jezeli nie ma - tworzymy
            objectLinks = new HashMap<>();
            links.put(roleName, objectLinks);
        }

        // Sprawdź, czy jest już połączenie
        //Jeśli tak, ignorujemy tworzenie
        if(!objectLinks.containsKey(qualifier)) {
            // Dodajemy link do obiektu docelowego
            objectLinks.put(qualifier, targetObject);

            // Dodajemy połączenie odwrotne
            targetObject.addLink(reverseRoleName, roleName, this, this, counter - 1);
        }
    }

    // Tworzy nowe polaczenie do podanego obiektu docelowego (opcjonalnie jako połączenie uproszczone).
    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject, Object qualifier) {
        addLink(roleName, reverseRoleName, targetObject, qualifier, 2);
    }

    // tworzy nowy polaczenie do danego obiektu docelowego (jako zwykla assocjacja, a nie kwalifikowana).
    public void addLink(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) {
        addLink(roleName, reverseRoleName, targetObject, targetObject);
    }

    //informacja do polacznia
    public void addPart(String roleName, String reverseRoleName, ObjectPlusPlus partObject) throws Exception {
        // Check if the part exist somewhere
        if(allParts.contains(partObject)) {
            throw new Exception("The part is already connected to a whole!");
        }

        addLink(roleName, reverseRoleName, partObject);

        // Store adding the object as a part
        allParts.add(partObject);
    }

    // tablica polaczen
    public ObjectPlusPlus[] getLinks(String roleName) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links for the role
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        return (ObjectPlusPlus[]) objectLinks.values().toArray(new ObjectPlusPlus[0]);
    }


    public void showLinks(String roleName, PrintStream stream) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            // No links
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);

        Collection col = objectLinks.values();

        stream.println(this.getClass().getSimpleName() + " links, role '" + roleName + "':");

        for(Object obj : col) {
            stream.println("   " + obj);
        }
    }


    //zwracamy object dla danego qualifiera
    public ObjectPlusPlus getLinkedObject(String roleName, Object qualifier) throws Exception {
        Map<Object, ObjectPlusPlus> objectLinks;

        if(!links.containsKey(roleName)) {
            throw new Exception("No links for the role: " + roleName);
        }

        objectLinks = links.get(roleName);
        if(!objectLinks.containsKey(qualifier)) {
            throw new Exception("No link for the qualifer: " + qualifier);
        }

        return objectLinks.get(qualifier);
    }

    //czy istneja polaczenia dla roli
    public boolean anyLink(String roleName) {
        if(!links.containsKey(roleName)) {
            return false;
        }

        Map<Object, ObjectPlusPlus> links = this.links.get(roleName);
        return links.size() > 0;
    }

    //czy istneja polaczenia dla objektu
    public boolean isLink(String roleName, ObjectPlusPlus targetObject) {
        Map<Object, ObjectPlusPlus> objectLink;

        if(!links.containsKey(roleName)) {
            // No links for the role
            return false;
        }

        objectLink = links.get(roleName);

        return objectLink.containsValue(targetObject);
    }
}

