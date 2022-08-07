package ga.muzzle.utils;

import java.util.Random;

public class StringUtils {
    public static String getRandomString(int length) {
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(2);
            long result;
            switch (number) {
                case 0:
                    result = Math.round('A' + Math.random() * ('F' - 'A' + 1));
                    stringBuilder.append((char) result);
                    break;
                case 1:
                    stringBuilder.append(new Random().nextInt(10));
                    break;
                default:
                    break;
            }
        }
        return stringBuilder.toString();
    }
}
