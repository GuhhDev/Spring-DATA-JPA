package com.example.alura.services;

import com.example.alura.entities.Funcionario;
import com.example.alura.interfaces.FuncionarioProjecao;
import com.example.alura.repositories.FuncionarioRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class RelatoriosService {

    private Boolean system = true;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private final FuncionarioRepository funcionarioRepository;

    public RelatoriosService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {

        while (system) {
            System.out.println("Qual opção de cargo deseja executar?");
            System.out.println("0 - Sair");
            System.out.println("1 - Busca funcionario por nome: ");
            System.out.println("2 - Busca funcionario nome saalrio maior data: ");
            System.out.println("3 - Busca funcionario data contratação: ");
            System.out.println("4 - pesquisa funcionario por salário: ");


            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    buscaFuncionarioNome(scanner);
                    break;
                case 2:
                    buscaFuncionarioNomeSalarioMaiorData(scanner);
                    break;
                case 3:
                    buscaFuncionarioDataContratacao(scanner);
                    break;
                case 4:
                    funcionarioSalario();
                    break;
            }
        }
    }

    private void buscaFuncionarioNome(Scanner scanner){
        System.out.println("Qual nome deseja pesquisar? ");
        String name = scanner.next();
        List<Funcionario> list = funcionarioRepository.findByName(name);
        list.forEach(System.out::println);
    }

    private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
        System.out.println("Qual nome deseja pesquisar? ");
        String nome = scanner.next();

        System.out.println("Qual a data de contratação deseja pesquisar? ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        System.out.println("Qual salário deseja pesquisar? ");
        Double salario = scanner.nextDouble();

        List<Funcionario> list = funcionarioRepository
                .findNomeSalarioMaiorDataContratacao(nome, salario, localDate);
    }

    private void buscaFuncionarioDataContratacao(Scanner scanner){
        System.out.println("Qual a data de contratação deseja pesquisar? ");
        String data = scanner.next();
        LocalDate localDate = LocalDate.parse(data, formatter);

        List<Funcionario> list = funcionarioRepository.findDataContratacaoMaior(localDate);
    }

    private void funcionarioSalario() {
        List<FuncionarioProjecao> list = funcionarioRepository.findFuncionarioSalario();
        list.forEach(f -> System.out.println("Funcionario: id: " + f.getId()
                + " | nome: " + f.getNome() + " | salario: " + f.getSalario()));
    }
}