import java.util.*;

public class SimpleWordDictionary {
    private Map<Character, List<String>> wordsMap;

    public SimpleWordDictionary() {
        wordsMap = new HashMap<>();
    }

    public void put(String key, String word) {
        if (word.isEmpty()) return;
        char firstChar = Character.toLowerCase(key.charAt(0));
        wordsMap.putIfAbsent(firstChar, new ArrayList<>());
        wordsMap.get(firstChar).add(word);
        Collections.sort(wordsMap.get(firstChar));
    }

    public void printAllWords() {
        for (char c = 'a'; c <= 'z'; c++) {
            if (wordsMap.containsKey(c)) {
                System.out.println(c + ": " + wordsMap.get(c));
            }
        }
    }

    public void printWordsForLetter(char letter) {
        char lowerCaseLetter = Character.toLowerCase(letter);
        if (wordsMap.containsKey(lowerCaseLetter)) {
            System.out.println(lowerCaseLetter + ": " + wordsMap.get(lowerCaseLetter));
        } else {
            System.out.println("No words found for letter " + lowerCaseLetter);
        }
    }

    public static void main(String[] args) {
        SimpleWordDictionary dictionary = new SimpleWordDictionary();
        dictionary.put("a", "apple");
        dictionary.put("a", "abricot");
        dictionary.put("b", "banana");
        dictionary.put("b", "ball");
        dictionary.put("c", "cat");
        dictionary.put("d", "dog");
        dictionary.put("e", "elephant");
        dictionary.put("f", "fish");
        dictionary.put("g", "giraffe");
        dictionary.put("h", "hat");
        dictionary.put("i", "ice cream");
        dictionary.put("j", "jacket");

        System.out.println("All words:");
        dictionary.printAllWords();

        System.out.println("\nWords for letter 'a':");
        dictionary.printWordsForLetter('a');

        System.out.println("\nWords for letter 'b':");
        dictionary.printWordsForLetter('b');

        System.out.println("\nWords for letter 'z':");
        dictionary.printWordsForLetter('z');
    }
}
