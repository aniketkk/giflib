package com.teamtreehouse.giflib.data;

import com.teamtreehouse.giflib.model.Card;
import com.teamtreehouse.giflib.model.Game;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class WordRepository {

    public Game getRandomCards(){
        Random r = new Random();
        List<String> words = fileReader("/static/wordlist.txt");
        int counter = words.size();

        int red = 0;
        int blue = 0;
        int black = 1;
        int villager = 7;

        if(r.nextInt(10) % 2 == 0){
            red = 9;
            blue = 8;

        }else{
            red = 8;
            blue = 9;
        }
        Game g = new Game();
        if(red > blue){
            g.setStarter("RED");

        }
        else{
            g.setStarter("BLUE");
        }
        List<String> gameWords = new ArrayList<>();
        while(gameWords.size() != 25){

            int randomNumber = r.nextInt(counter);//get index betn 0 and 4942
            gameWords.add(words.get(randomNumber));//add word at index randomNumber
            counter--;
            words.remove(randomNumber);
            System.out.print("Not even here");

        }

        List<Card> gameCards = new ArrayList<>();
        List<Integer> numbers = IntStream.range(0,25).boxed().collect(Collectors.toList());

        counter = 24;
        while(numbers.size() > 0){

            int randomNumber = r.nextInt(numbers.size());//get index betn 0 and 24

            if(red > 0){
                red--;
                Card c = new Card(numbers.get(randomNumber), gameWords.get(counter), "RED");
                gameCards.add(c);
            }else if(blue > 0){
                blue--;
                Card c = new Card(numbers.get(randomNumber), gameWords.get(counter), "BLUE");
                gameCards.add(c);
            }else if(villager > 0){
                villager--;
                Card c = new Card(numbers.get(randomNumber), gameWords.get(counter), "VILLAGER");
                gameCards.add(c);
            }else if(black > 0) {
                black--;
                Card c = new Card(numbers.get(randomNumber), gameWords.get(counter), "BLACK");
                gameCards.add(c);
            }
            numbers.remove(numbers.get(randomNumber));
            gameWords.remove(counter);
            counter--;

            Collections.sort(gameCards, Comparator.comparingInt(Card::getId));
        }


        List<List<Card>> listOfGameCards = new ArrayList<>();
        for(int i =0; i < 5; i++){
            List<Card> gc = new ArrayList<>();
            for(int j =0; j < 5; j++){
                gc.add(gameCards.get(i*5+j));
            }
            listOfGameCards.add(gc);
        }

        g.setGameCards(listOfGameCards);

        return g;
    }

    public List<List<Card>> getAllWords(){
        return getRandomCards().getGameCards();
    }

    public Map<String, Card> mapOfWords(List<List<Card>> cards){
        Map<String, Card> map =
                cards
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toMap(Card::getValue, Function.identity()));
         return map;
    }


    public List<String> fileReader(String FILENAME){
        List<String> words = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(FILENAME)))) {

            String sCurrentLine=null;

            while ((sCurrentLine = br.readLine()) != null) {
                words.add(sCurrentLine);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return words;
    }
}
