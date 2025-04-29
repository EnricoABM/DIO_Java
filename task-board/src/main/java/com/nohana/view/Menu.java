package com.nohana.view;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import com.nohana.config.ConnectionConfig;
import com.nohana.model.BoardColumnModel;
import com.nohana.model.BoardColumnTypeEnum;
import com.nohana.model.BoardModel;
import com.nohana.service.BoardService;

public class Menu {
    private Scanner scanner = new Scanner(System.in);

    public void execute() {
        int op = -1;
        while (true) {
            System.out.println("[1] - Criar um Board");
            System.out.println("[2] - Selecionar um Board existente");
            System.out.println("[3] - Excluir um Board");
            System.out.println("[4] - Sair");
            System.out.print("Escolha uma opção: ");
            op = scanner.nextInt();
            scanner.nextLine();

            switch (op) {
                case 1 -> createBoard();
                case 2 -> selectBoard();
                case 3 -> deleteBoard();
                case 4 -> System.exit(1);
                default -> System.out.println("Opção Inválida.");
            }
        }
    }

    private void createBoard() {
        BoardModel board = new BoardModel();
        System.out.print("Nome: ");
        board.setName(scanner.nextLine());

        System.out.print("Deseja o Board com 3 colunas? Se não, informe quantas, do contrário digite '0': ");
        int addColumns = scanner.nextInt() + 3;
        scanner.nextLine();

        List<BoardColumnModel> columns = new ArrayList<>();

        System.out.print("Informe o nome da coluna inicial do Board: ");
        BoardColumnModel column = createColumn(
                scanner.nextLine(),
                BoardColumnTypeEnum.INITIAL,
                0);
        columns.add(column);

        for (int i = 1; i <= addColumns; i++) {
            System.out.print("Informe o nome da " + 1 + "° coluna pendente do Board: ");
            column = createColumn(
                    scanner.nextLine(),
                    BoardColumnTypeEnum.PENDING,
                    i);
            columns.add(column);
        }

        System.out.print("Informe o nome da coluna final do Board: ");
        column = createColumn(
                scanner.nextLine(),
                BoardColumnTypeEnum.FINAL,
                addColumns + 1);
        columns.add(column);

        System.out.print("Informe o nome da coluna de cancelamento do Board: ");
        column = createColumn(
                scanner.nextLine(),
                BoardColumnTypeEnum.CANCEL,
                addColumns + 2);
        columns.add(column);

        board.setColumns(columns);

        try (var conn = ConnectionConfig.getConnection()) {
            BoardService service = new BoardService(conn);
            service.insert(board);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro interno");
        }
    }

    private void selectBoard() {
        System.out.print("Informe o ID que deseja selecionar: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        try (var conn = ConnectionConfig.getConnection()) {
            BoardService service = new BoardService(conn);
            Optional<BoardModel> opt = service.findById(id);
            opt.ifPresentOrElse(
                b -> new BoardMenu(b).execute(),
                () -> System.out.println("Board não encontrado")
            );
        } catch (SQLException e) {
            System.out.println("Erro interno");
        }
    }

    private void deleteBoard() {
        System.out.print("Informe o ID a ser deletado: ");
        long id = scanner.nextLong();
        scanner.nextLine();

        try (var conn = ConnectionConfig.getConnection()) {
            BoardService service = new BoardService(conn);
            if (service.delete(id)) {
                System.out.println("Deletado com sucesso");
            } else {
                System.out.println("Board não encontrado");
            }
        } catch (SQLException e) {
            System.out.println("Erro interno");
            ;
        }
    }

    private BoardColumnModel createColumn(final String name, final BoardColumnTypeEnum type, final int order) {
        BoardColumnModel column = new BoardColumnModel();
        column.setName(name);
        column.setKind(type);
        column.setOrder(order);
        return column;
    }
}
