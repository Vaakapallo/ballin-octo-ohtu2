
package statistics.matcher;

import java.lang.reflect.Method;
import statistics.Player;

public class HasAtLeast extends Matcher {

    private int value;
    private String fieldName;
    private Matcher matcher;

    public HasAtLeast(Matcher matcher, int value, String category) {
        this.matcher = matcher;
        this.value = value;
        fieldName = "get" + Character.toUpperCase(category.charAt(0)) + category.substring(1, category.length());
    }

    @Override
    public boolean matches(Player p) {
        try {
            Method method = p.getClass().getMethod(fieldName);
            int playersValue = (Integer) method.invoke(p);
            return playersValue >= value && matcher.matches(p);

        } catch (Exception ex) {
            System.out.println(ex);
            throw new IllegalStateException("Player does not have field " + fieldName.substring(3, fieldName.length()).toLowerCase());
        }

    }
}
