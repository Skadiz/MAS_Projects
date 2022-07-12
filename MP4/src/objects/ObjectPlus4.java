package objects;

import java.util.LinkedList;
import java.util.List;


public abstract class ObjectPlus4 extends ObjectPlusPlus {


    private List<String> rolesXOR = new LinkedList<>();

    public ObjectPlus4() {
        super();
    }

    //Tworzy nowy link do danego obiekt z uwzględnieniem ograniczenia subset.
    public void addLink_subset(String roleName, String reverseRoleName, String superRoleName, ObjectPlusPlus targetObject) throws Exception {
        if(isLink(superRoleName, targetObject)) {
            //polaczenie super jest
            addLink(roleName, reverseRoleName, targetObject);
        }
        else {
            // nie ma
            throw new Exception("No link to the '" + targetObject + "' object in the '" + superRoleName + "' super role!");
        }
    }


    public void addXorRole(String xorRoleName) {
        rolesXOR.add(xorRoleName);
    }


    public void addLinkXor(String roleName, String reverseRoleName, ObjectPlusPlus targetObject) throws Exception {
        if(rolesXOR.contains(roleName)) {
            if(isXorLink()) {
                throw new Exception("There is a link for a XOR roles!");
            }
        }

        super.addLink(roleName, reverseRoleName, targetObject);
    }

    private boolean isXorLink() {
        for(String role : rolesXOR) {
            if(this.anyLink(role)) {
                return true;
            }
        }

        return false;
    }
}

