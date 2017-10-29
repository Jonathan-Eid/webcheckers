package com.webcheckers.ui;

import spark.*;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by Ethan on 10/9/2017.
 */
public abstract class GaemRoute implements Route {
    private final TemplateEngine templateEngine;

    /**
     * The constructor for the {@code GET /game} route handler.
     *
     * @param templateEngine
     *    The {@link TemplateEngine} used for rendering page HTML.
     */
    public GaemRoute(TemplateEngine templateEngine) {
        Objects.requireNonNull(templateEngine, "templateEngine must not be null");
        this.templateEngine = templateEngine;
    }

    public static <T, E> Set<T> getKeysByValue(Map<T, E> map, E value) {
        return map.entrySet()
                .stream()
                .filter(entry -> Objects.equals(entry.getValue(), value))
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    /**
     * Create the board using boardview of rows, spaces, and pieces
     * according to the game view
     * @return the new board
     */

    /*
    private BoardView createGame(Piece.PieceColor pieceColor) {
        Piece.PieceColor pieceColor1;
        if (pieceColor == Piece.PieceColor.RED) {
            pieceColor1 = Piece.PieceColor.WHITE;
        } else {
            pieceColor1 = Piece.PieceColor.RED;
        }
        ArrayList<Row> rows = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            ArrayList<Space> spaces = new ArrayList<>();
            if (i == 0 || i == 2) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 != 0) {
                        spaces.add(new Space(j, new Piece(Piece.PieceType.SINGLE, pieceColor1), false));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            } else if (i == 1) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        spaces.add(new Space(j, new Piece(Piece.PieceType.SINGLE, pieceColor1), false));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            } else if (i == 5 || i == 7) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        spaces.add(new Space(j, new Piece(Piece.PieceType.SINGLE, pieceColor), false));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            } else if (i == 6) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 != 0) {
                        spaces.add(new Space(j, new Piece(Piece.PieceType.SINGLE, pieceColor), false));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            } else if (i == 3) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 == 0) {
                        spaces.add(new Space(j, null, true));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            } else if (i == 4) {
                for (int j = 0; j < 8; j++) {
                    if (j % 2 != 0) {
                        spaces.add(new Space(j, null, true));
                    } else {
                        spaces.add(new Space(j, null, false));
                    }
                }
            }
            Row row = new Row(i, spaces);
            rows.add(row);
        }
        return new BoardView(rows);
    }

    public String errorHandle(String error, Request request) {
        final Map<String, Object> vm = new HashMap<>();
        vm.put("PlayerName","ERROR");
        vm.put("inGame", false);
        if (request.session().attribute("signedin")) {
            vm.put("PlayerName", request.session().attribute("player"));
            vm.put("PlayerLobbyList", playerLobby.stringUsers(request.session().attribute("player")));
        }
        vm.put("message", true);
        vm.put("error", error);
        vm.put("newPlayer", request.session().attribute("signedin"));
        vm.put("title", "Welcome!");
        vm.put("PlayerLobbyCount", playerLobby.getListLength());
        return templateEngine.render(new ModelAndView(vm, "home.ftl"));
    }


    /**
     * Render Game page
     * @param request
     *   the HTTP request
     * @param response
     *   the HTTP response
     * @return Template engine to render Game
     */

    /*
    public String handle(Request request, Response response) {
        final Map<String, Object> vm = new HashMap<>();
        vm.put("viewMode", "PLAY");
        vm.put("title", "Play Checkers");
        if (request.session().attribute("signedin")) {
            if (request.queryParams("name") != null) {
                Player player1 = WebServer.playerLobby.getPlayer(request.session().attribute("player"));
                Player player2 = WebServer.playerLobby.getPlayer(request.queryParams("name"));
                if ((!(WebServer.playerLobby.checkIfInGame(player1))) && (!(WebServer.playerLobby.checkIfInGame(player2)))) {
                    WebServer.playerLobby.addInGame(player1, player2);
                    response.redirect("/game");
                } else if ((!(WebServer.playerLobby.checkIfInGame(player1))) && ((WebServer.playerLobby.checkIfInGame(player2)))) {
                    return errorHandle(player2.getName() + " is in a game!", request);
                } else if (((WebServer.playerLobby.checkIfInGame(player1))) && ((WebServer.playerLobby.checkIfInGame(player2)))) {
                    if (!(WebServer.playerLobby.getInGameList().get(player1).equals(player2))) {
                        return errorHandle(player2.getName() + " is in a game!", request);
                    }
                }
                vm.put("currentPlayer", player1);
                vm.put("redPlayer", player1);
                vm.put("whitePlayer", player2);
            } else {
                if (WebServer.playerLobby.checkIfInGame(WebServer.playerLobby.getPlayer(request.session().attribute("player")))) {
                    if (WebServer.playerLobby.getColor(WebServer.playerLobby.getPlayer(request.session().attribute("player"))).equals("white")) {
                        Player player2 = WebServer.playerLobby.getPlayer(request.session().attribute("player"));
                        HashMap<Player, Player> hashMap = WebServer.playerLobby.getInGameList();
                        Set set = getKeysByValue(hashMap, player2);
                        Player player1 = (Player) set.iterator().next();
                        vm.put("currentPlayer", player2);
                        vm.put("redPlayer", player1);
                        vm.put("whitePlayer", player2);
                    } else {
                        Player player1 = WebServer.playerLobby.getPlayer(request.session().attribute("player"));
                        Player player2 = WebServer.playerLobby.getInGameList().get(player1);
                        vm.put("currentPlayer", player1);
                        vm.put("redPlayer", player1);
                        vm.put("whitePlayer", player2);
                    }
                } else {
                    return errorHandle("You are not in a game!", request);
                }
            }
            vm.put("activeColor", Piece.PieceColor.RED);
            if (WebServer.playerLobby.getColor(WebServer.playerLobby.getPlayer(request.session().attribute("player"))).equals("red")) {
                vm.put("board", createGame(Piece.PieceColor.RED));
            } else {
                vm.put("board", createGame(Piece.PieceColor.WHITE));
            }
        } else {
            return errorHandle("You must be signed in to play!", request);
        }
        return templateEngine.render(new ModelAndView(vm, "game.ftl"));
    }
    */
}
