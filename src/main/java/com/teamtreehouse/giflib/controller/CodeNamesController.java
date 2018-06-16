package com.teamtreehouse.giflib.controller;

import com.teamtreehouse.giflib.data.WordRepository;
import com.teamtreehouse.giflib.model.Card;
import com.teamtreehouse.giflib.model.Game;
import com.teamtreehouse.giflib.model.SelectionResponse;
import com.teamtreehouse.giflib.model.TeamWords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "https://codenames-react.herokuapp.com")
@RestController
public class CodeNamesController {

    @Autowired
    WordRepository wordRepository;
    Map<String, Game> gameStore= new HashMap<>();

    @RequestMapping(value="/master/{gameId}", method= RequestMethod.GET)
    //@CrossOrigin(origins = "http://localhost:3000")
    public List<List<Card>> getMaster(@PathVariable("gameId") String gameId) throws GameNotFoundException, GameOverException{
        if(gameStore.containsKey(gameId)) {
            if(gameStore.get(gameId).isGameOver()){
                throw new GameOverException(gameId);
            }
            return gameStore.get(gameId).getGameCards();
        }
        throw new GameNotFoundException(gameId);
    }

    @RequestMapping(value="/game/{gameId}", method= RequestMethod.GET)
    //@CrossOrigin(origins = "http://localhost:3000")
    public Game getGame(@PathVariable("gameId") String gameId) throws GameNotFoundException, GameOverException{
        if(gameStore.containsKey(gameId)){
            if(gameStore.get(gameId).isGameOver()){
                throw new GameOverException(gameId);
            }
            return gameStore.get(gameId);
        }
        throw new GameNotFoundException(gameId);

    }

    @RequestMapping(value="/game/{gameId}", method= RequestMethod.HEAD, produces = MediaType.APPLICATION_JSON_VALUE)
    //@CrossOrigin(origins = "http://localhost:3000")
    public boolean existsGame(@PathVariable("gameId") String gameId) throws GameNotFoundException{
        if(gameStore.containsKey(gameId)){
            return true;
        }else{
            throw new GameNotFoundException(gameId);
        }
    }

    @RequestMapping(value="/game/{gameId}", method= RequestMethod.POST)
    //@CrossOrigin(origins = "http://localhost:3000")
    public Game createGame(@PathVariable("gameId") String gameId){
        gameStore.put(gameId, wordRepository.getRandomCards());
        return gameStore.get(gameId);
    }

    @RequestMapping(value="/game/{gameId}", method= RequestMethod.DELETE)
    //@CrossOrigin(origins = "http://localhost:3000")
    public void deleteGame(@PathVariable("gameId") String gameId){
        gameStore.remove(gameId);

    }


    @RequestMapping(value ="/game/checkCorrectWords/{gameId}", method= RequestMethod.POST)
    @CrossOrigin(origins = "http://localhost:3000")
    public SelectionResponse validateTeamWords(@RequestBody TeamWords teamWords, @PathVariable String gameId){
        Game g = gameStore.get(gameId);
        Map<String,Card> map = wordRepository.mapOfWords(g.getGameCards());
        List<Card> result = new ArrayList<>();
        int correctBlueAnswers = 0;
        int correctRedAnswers = 0;

        for(String word : teamWords.getSelectedWords()){
            Card c = map.get(word);
            c.setGuessed(true);
            result.add(c);
            if(map.get(word).getColor().equals("BLUE")){
                correctBlueAnswers++;
            }else if(map.get(word).getColor().equals("RED")){
                correctRedAnswers++;
            }else if(map.get(word).getColor().equals("BLACK")){
                g.setGameOver(true);
            }
        }

        g.setBlueScore(g.getBlueScore()+correctBlueAnswers);
        g.setRedScore(g.getRedScore()+correctRedAnswers);

        SelectionResponse selectionResponse = new SelectionResponse();
        selectionResponse.setCorrectRedAnswers(g.getRedScore());
        selectionResponse.setCorrectBlueAnswers(g.getBlueScore());
        List<List<Card>> selectedCards = new ArrayList<>();

        for(int i = 0 ; i < 5; i++){
            List<Card> row = new ArrayList<>();
            for(int j = 0 ; j < 5; j++){
                row.add(g.getGameCards().get(i).get(j));
            }
            selectedCards.add(row);
        }
        selectionResponse.setSelectedCards(selectedCards);

        return selectionResponse;
    }

    @RequestMapping(value ="/", method= RequestMethod.OPTIONS)
    //@CrossOrigin(origins = "http://localhost:3000")
    public String optionsAll(){

        return "";
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Game Not Found") //404
    public class GameNotFoundException extends Exception {

        private static final long serialVersionUID = -3332292346834265371L;

        public GameNotFoundException(String id){
            super("Game "+ id + " does not exist!");
        }
    }

    @ResponseStatus(value= HttpStatus.NOT_FOUND, reason="Game Not Found") //404
    public class GameOverException extends Exception {

        private static final long serialVersionUID = -3332292346834265371L;

        public GameOverException(String id){
            super("Game "+ id + " does not exist!");
        }
    }



}
