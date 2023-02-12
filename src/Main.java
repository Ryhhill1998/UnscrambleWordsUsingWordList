import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        List<String> words = addWordListToArrayList("wordlist.txt");
        List<String> wordsToUnscramble = addWordListToArrayList("words.txt");

        unscrambleWords(wordsToUnscramble, words);
    }

    public static void unscrambleWords(List<String> wordsToUnscramble, List<String> wordList) {
        StringBuilder output = new StringBuilder();

        for (String word : wordsToUnscramble) {
            String foundWord = compareWordToWordList(word, wordList);

            if (!output.isEmpty()) {
                output.append(", ");
            }

            output.append(foundWord);
        }

        System.out.println(output);
    }

    public static List<String> addWordListToArrayList(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        Scanner scanFile = new Scanner(file);

        List<String> words = new ArrayList<>();

        while (scanFile.hasNextLine()) {
            words.add(scanFile.nextLine());
        }

        return words;
    }

    public static Map<String, Integer> getLetterMap(String[] letters) {
        Map<String, Integer> letterMap = new HashMap<>();

        for (String letter : letters) {
            int count = letterMap.getOrDefault(letter, 0) + 1;
            letterMap.put(letter, count);
        }

        return letterMap;
    }

    public static String compareWordToWordList(String word, List<String> wordList) {
        String[] letters = word.split("");
        Map<String, Integer> letterMap = getLetterMap(letters);

        for (String w : wordList) {

            if (w.equals(word)) return w;

            for (String letter : letters) {
                if (!w.contains(letter)) break;
            }

            Map<String, Integer> wordMap = getLetterMap(w.split(""));

            if (letterMapsAreEqual(letterMap, wordMap)) return w;
        }

        return null;
    }

    public static boolean letterMapsAreEqual(Map<String, Integer> map1, Map<String, Integer> map2) {
        if (map1.size() != map2.size()) return false;

        for (Map.Entry<String, Integer> entrySet : map1.entrySet()) {
            String key = entrySet.getKey();
            int value = entrySet.getValue();

            if (!map2.containsKey(key) || map2.get(key) != value) return false;
        }

        return true;
    }
}