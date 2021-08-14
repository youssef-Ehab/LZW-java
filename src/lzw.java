import java.util.Scanner;
import java.util.Vector;

public class lzw {
    Vector compress(String input) {
        String previous;
        String current;
        String check;
        Vector<Integer> ans = new Vector<>();
        Vector<String> dictionary = new Vector<>();
        for (int i = 0; i < input.length(); i++) {
            check = input.substring(i, i + 1);
            if (dictionary.contains(check)) {
                continue;
            } else {
                dictionary.add(check);
            }
        }
        Vector<String> basic = new Vector<>();
        basic = dictionary;
        for (int i = 1; i < input.length(); i++) {

            previous = input.substring(i - 1, i);
            current = input.substring(i, i + 1);
            check = previous + current;
            if (dictionary.contains(check)) {
                for (int j = i; j < input.length(); j++) {
                    if (j + 2 >= input.length()) {

                        ans.add(dictionary.indexOf(check));
                        break;
                    }
                    previous += current;
                    current = input.substring(j + 1, j + 2);
                    check = previous + current;
                    if (dictionary.contains(check)) {
                        continue;
                    } else {
                        dictionary.add(check);
                        ans.add(dictionary.indexOf(previous));
                        i = j + 1;
                        break;
                    }
                }
            } else {
                dictionary.add(check);
                ans.add(dictionary.indexOf(previous));
            }
        }
        System.out.println(ans);
        System.out.println(dictionary);
        return basic;
    }


    void compress2(String input) {
        String previous;
        String current;
        String check;
        Vector<Integer> ans = new Vector<>();
        Vector<String> dic = new Vector<>();
        for (int i = 0; i < 128; i++) {
            dic.add(Character.toString((char) i));
        }

        for (int i = 1; i < input.length(); i++) {
            previous = input.substring(i - 1, i);
            current = input.substring(i, i + 1);
            check = previous + current;

            if (dic.contains(check)) {
                for (int j = i; j < input.length(); j++) {
                    if (j + 2 >= input.length()) {
                        ans.add(dic.indexOf(check));
                        break;
                    }
                    previous += current;
                    current = input.substring(j + 1, j + 2);
                    check = previous + current;
                    if (dic.contains(check)) {
                        continue;
                    } else {
                        dic.add(check);
                        ans.add(dic.indexOf(previous));
                        i = j + 1;
                        break;
                    }
                }
            } else {
                if (i == input.length() - 1) {
                    dic.add(check);
                    ans.add(dic.indexOf(previous));
                    ans.add(dic.indexOf(current));
                    break;
                }
                dic.add(check);
                ans.add(dic.indexOf(previous));
            }
        }
        System.out.println(ans);
        System.out.println(dic);
    }


    void decompress() {
        int a;
        String previous = new String();
        String current = new String();
        String check = new String();
        Scanner scanner = new Scanner(System.in);
        Vector<String> dic = new Vector<>();
        for (int i = 0; i < 128; i++) {
            dic.add(Character.toString((char) i));
        }
        Vector<Integer> compressed = new Vector<>();
        Vector<String> ans = new Vector<>();
        System.out.println("Enter the numbers and when you are done type a negative num");
        while (true) {
            a = scanner.nextInt();
            if (a < 0) {
                break;
            } else {
                compressed.add(a);
            }

        }
        for (int i = 0; i < compressed.size(); i++) {

            if (i == 0) {
                ans.add(dic.elementAt(compressed.elementAt(i)));
                continue;
            } else {
                if (i + 1 >= compressed.size()) {
                    current = dic.elementAt(compressed.elementAt(i));
                    ans.add(current);
                    break;
                }
                previous = ans.elementAt(i - 1);

                if (compressed.elementAt(i) >= dic.size()) {
                    check = previous + Character.toString(previous.charAt(0));
                    dic.add(check);
                    ans.add(check);
                    continue;
                } else {

                    current = dic.elementAt(compressed.elementAt(i));
                    check = previous + Character.toString(current.charAt(0));
                    dic.add(check);
                    ans.add(current);
                }
            }
        }
        System.out.println(ans);
        System.out.println(dic);
    }


    public static void main(String[] args) {
        lzw l = new lzw();
        l.compress2("abaabbababbbabbbbbb");
        l.decompress();
    }
}
