import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class Main {
    static boolean check(String a, char b){
            String tmp ="" + b;
        return a.contains(tmp);
    }
    static String makeTmpString(int numberOfCharacters){
        return "_".repeat(Math.max(0, numberOfCharacters));
    }
    static StringBuffer getWord() throws IOException {
        URL url = new URL("https://random-word-api.herokuapp.com/word?lang=en");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        con.disconnect();

        return content;
    }
    public static void main(String[] args) {
        StringBuffer response;
        String word="";
        try {
            response = (getWord());
            word = response.toString();


        } catch (IOException e) {
            System.out.println("Greska !");
        }

        Scanner scanner = new Scanner(System.in);
        int left_tries = 6;
        String wordTmp;
        char letter;
        boolean hit = false;
        int wordLength = word.length();
        word = word.substring(2,wordLength-2);
        wordLength=wordLength-4;
        System.out.println(word);
        wordTmp=makeTmpString(wordLength);
        String allLetters = "";
        while(left_tries >0){
            System.out.println("Unesite slovo: ");
            letter = scanner.next().charAt(0);
            if(check(allLetters,letter)){
                System.out.println("Vec ste pokusali sa tim slovom!");
                continue;
            }
            else{
                allLetters+=letter;
            }
            for(int i = 0;i<wordLength;i++){
                if (word.charAt(i)==letter){
                    System.out.println("Pogodili ste slovo!");
                    wordTmp = wordTmp.substring(0,i) + word.charAt(i) + wordTmp.substring(i+1,wordLength);
                    hit = true;
                }

            }
            if(!hit) {
                left_tries--;
                System.out.println("Rec ne sadrzi slovo: " + letter);
            }
            hit = false;
            if(word.equals(wordTmp) ){
                System.out.println("Uspesno ste pogodili rec: " +word);
                break;
            }
            System.out.println(wordTmp);
            if(left_tries==0) System.out.println("6/6 Gresaka!");
            }
        }

    }

