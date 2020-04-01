package bg.leetcode.exercises.itenev.string;

/**
 * Given a valid (IPv4) IP address, return a defanged version of that IP address.
 * A defanged IP address replaces every period "." with "[.]".
 * <p>
 * Input: address = "1.1.1.1"
 * Output: "1[.]1[.]1[.]1"
 * <p>
 * Input: address = "255.100.50.0"
 * Output: "255[.]100[.]50[.]0"
 */
public class DefangingIPAddress {

    public static String defangIPaddr(String address) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < address.length(); i++) {
            if (address.charAt(i) == '.')
                sb.append("[.]");
            else
                sb.append(address.charAt(i));
        }
        return sb.toString();
    }

    /****************/

    public String defangIPaddr2(String address) {
        int size = address.length();
        for(int i = 0; i < size; i++) {
            if(address.charAt(i) == '.') {
                address = address.substring(0,i) + "[.]" + address.substring(++i,size);
                size += 2;
            }
        }
        return address;
    }
}
