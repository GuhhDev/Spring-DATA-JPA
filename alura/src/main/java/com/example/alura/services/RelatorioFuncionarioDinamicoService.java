package com.example.alura.services;

import com.example.alura.entities.Funcionario;
import com.example.alura.repositories.FuncionarioRepository;
import com.example.alura.specifications.SpecificationFuncionario;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Service
public class RelatorioFuncionarioDinamicoService {

    private final FuncionarioRepository funcionarioRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public RelatorioFuncionarioDinamicoService(FuncionarioRepository funcionarioRepository) {
        this.funcionarioRepository = funcionarioRepository;
    }

    public void inicial(Scanner scanner) {
        System.out.println("Digite o nome: ");
        String nomeString = scanner.next();

        if (nomeString.equalsIgnoreCase("NULL")){
            nomeString = null;
        }

        System.out.println("Digite o cpf: ");
        String cpf = scanner.next();

        if (cpf.equalsIgnoreCase("NULL")){
            cpf = null;
        }

        System.out.println("Digite o salario: ");
        Double salario = scanner.nextDouble();

        if (salario == 0) {
            salario = null;
        }

        System.out.println("Digite a data de contratação: ");
        String data = scanner.next();

        LocalDate dataContratacao;
        if (data.equalsIgnoreCase("NULL")) {
            dataContratacao = null;
        } else {
            dataContratacao = LocalDate.parse(data ,formatter);
        }

        List<Funcionario> funcionarios = funcionarioRepository
                .findAll(Specification.where(SpecificationFuncionario.nome(nomeString))
                        .or(SpecificationFuncionario.cpf(cpf).or(SpecificationFuncionario.salario(salario)
                                .or(SpecificationFuncionario.dataContratacao(dataContratacao)))));

        funcionarios.forEach(System.out::println);
    }


}
