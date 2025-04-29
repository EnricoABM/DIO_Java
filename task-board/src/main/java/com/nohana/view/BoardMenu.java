package com.nohana.view;

import java.sql.SQLException;
import java.util.Scanner;

import com.nohana.config.ConnectionConfig;
import com.nohana.dto.BoardColumnInfoDTO;
import com.nohana.model.BoardColumnModel;
import com.nohana.model.BoardModel;
import com.nohana.model.CardModel;
import com.nohana.service.BoardColumnService;
import com.nohana.service.BoardService;
import com.nohana.service.CardService;

public class BoardMenu {

    private Scanner scanner = new Scanner(System.in);
    private BoardModel model;

    public BoardMenu(BoardModel model) {
        this.model = model;
    }

    public void execute() {
        try {
            int op = -1;
            while (op != 10) {
                System.out.println(model.getName() + " : Menu");
                System.out.println("[1] - Cadastrar card");
                System.out.println("[2] - Mover card");
                System.out.println("[3] - Bloquear card");
                System.out.println("[4] - Desbloquear card");
                System.out.println("[5] - Cancelar card");
                System.out.println("[6] - Ver board");
                System.out.println("[7] - Ver coluna com card");
                System.out.println("[8] - Ver card");
                System.out.println("[9] - Voltar");
                System.out.println("[10] - Sair");
                System.out.print("Selecione uma opção: ");
                op = scanner.nextInt();
                scanner.nextLine();
    
                switch (op) {
                    case 1 -> createCard();
                    case 2 -> moveCard();
                    case 3 -> blockCard();
                    case 4 -> unblockCard();
                    case 5 -> cancelCard();
                    case 6 -> showBoard();
                    case 7 -> showColumn();
                    case 8 -> showCard();
                    case 9 -> System.out.println("Voltando...");
                    case 10 -> System.exit(0);
                    default -> System.out.println("Opção Inválida.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    private void createCard() throws SQLException {
        var card = new CardModel();
        System.out.println("Informe o título do card");
        card.setTitle(scanner.next());
        System.out.println("Informe a descrição do card");
        card.setDescription(scanner.next());
        card.setColumn(model.getInitialColumn());
        try(var conn = ConnectionConfig.getConnection()){
            new CardService(conn).create(card);
        }
    }

    private void moveCard() throws SQLException {
        System.out.print("Informe o ID: ");
        var cardId = scanner.nextLong();
        scanner.nextLine();
        var boardColumnsInfo = model.getColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .toList();
        try(var conn = ConnectionConfig.getConnection()){
            new CardService(conn).moveToNextColumn(cardId, boardColumnsInfo);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }    
    }

    private void blockCard() throws SQLException {
        System.out.print("Informe o ID: ");
        var cardId = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Informe o Motivo: ");
        var reason = scanner.nextLine();

        var boardColumnsInfo = model.getColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .toList();
        try(var conn = ConnectionConfig.getConnection()){
            new CardService(conn).block(cardId, reason, boardColumnsInfo);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }    
    }

    private void unblockCard() throws SQLException {
        System.out.print("Informe o ID: ");
        var cardId = scanner.nextLong();
        scanner.nextLine();

        System.out.print("Informe o Motivo: ");
        var reason = scanner.nextLine();

        try(var conn = ConnectionConfig.getConnection()){
            new CardService(conn).unblock(cardId, reason);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }    
    }

    private void cancelCard() throws SQLException {
        System.out.print("Informe o ID: ");
        var cardId = scanner.nextLong();
        scanner.nextLine();

        var cancelColumn = model.getCancelColumn();
        var boardColumnsInfo = model.getColumns().stream()
                .map(bc -> new BoardColumnInfoDTO(bc.getId(), bc.getOrder(), bc.getKind()))
                .toList();
        try(var conn = ConnectionConfig.getConnection()){
            new CardService(conn).cancel(cardId, cancelColumn.getId(), boardColumnsInfo);
        } catch (RuntimeException ex){
            System.out.println(ex.getMessage());
        }    
    }

    private void showBoard() throws SQLException {
        try(var conn = ConnectionConfig.getConnection()){
            var optional = new BoardService(conn).showBoardDetails(model.getId());
            optional.ifPresent(b -> {
                System.out.printf("Board [%s,%s]\n", b.id(), b.name());
                b.columns().forEach(c ->
                        System.out.printf("Coluna [%s] tipo: [%s] tem %s cards\n", c.name(), c.kind(), c.amount())
                );
            });
        }
    }

    private void showColumn() throws SQLException {
        var columnsIds = model.getColumns().stream().map(BoardColumnModel::getId).toList();
        var selectedColumnId = -1L;
        while (!columnsIds.contains(selectedColumnId)){
            System.out.printf("Escolha uma coluna do board %s pelo id\n", model.getName());
            model.getColumns().forEach(c -> System.out.printf("%s - %s [%s]\n", c.getId(), c.getName(), c.getKind()));
            selectedColumnId = scanner.nextLong();
        }
        try(var conn  = ConnectionConfig.getConnection()){
            var column = new BoardColumnService(conn).findById(selectedColumnId);
            column.ifPresent(co -> {
                System.out.printf("Coluna %s tipo %s\n", co.getName(), co.getKind());
                co.getCards().forEach(ca -> System.out.printf("Card %s - %s\nDescrição: %s",
                        ca.getId(), ca.getTitle(), ca.getDescription()));
            });
        }    
    }

    private void showCard() throws SQLException {
        System.out.println("Informe o id do card que deseja visualizar");
        var selectedCardId = scanner.nextLong();
        try(var conn  = ConnectionConfig.getConnection()){
            new CardService(conn).findById(selectedCardId)
                    .ifPresentOrElse(
                            c -> {
                                System.out.printf("Card %s - %s.\n", c.id(), c.title());
                                System.out.printf("Descrição: %s\n", c.description());
                                System.out.println(c.blocked() ?
                                        "Está bloqueado. Motivo: " + c.blockReason() :
                                        "Não está bloqueado");
                                System.out.printf("Já foi bloqueado %s vezes\n", c.blocksAmount());
                                System.out.printf("Está no momento na coluna %s - %s\n", c.columnId(), c.columnName());
                            },
                            () -> System.out.printf("Não existe um card com o id %s\n", selectedCardId));
        }
    }    
}

