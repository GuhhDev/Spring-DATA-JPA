package com.example.alura;

import com.example.alura.services.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AluraApplication implements CommandLineRunner {

    private final CrudCargoService cargoService;
    private final CrudFuncionarioService crudFuncionarioService;
    private final CrudUnidadeTrabalhoService crudUnidadeTrabalhoService;

    private final RelatoriosService relatoriosService;

    private final RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService;

    private Boolean system = true;

    public AluraApplication(CrudCargoService cargoService, CrudFuncionarioService crudFuncionarioService,
                            CrudUnidadeTrabalhoService crudUnidadeTrabalhoService, RelatoriosService relatoriosService,
                            RelatorioFuncionarioDinamicoService relatorioFuncionarioDinamicoService) {
        this.cargoService = cargoService;
        this.crudFuncionarioService = crudFuncionarioService;
        this.crudUnidadeTrabalhoService = crudUnidadeTrabalhoService;
        this.relatoriosService = relatoriosService;
        this.relatorioFuncionarioDinamicoService = relatorioFuncionarioDinamicoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(AluraApplication.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Scanner scanner = new Scanner(System.in);

        while (system) {
            System.out.println("Qual ação você deseja executar? ");
            System.out.println("0 - Sair");
            System.out.println("1 - Cargo");
            System.out.println("2 - Funcionario");
            System.out.println("3 - Unidade Trabalho");
            System.out.println("4 - Relatórios");
            System.out.println("5 - Relatórios Dinâmicos");

            int action = scanner.nextInt();

            switch (action) {
                case 1:
                    cargoService.inicial(scanner);
                    break;
                case 2:
                    //crudUnidadeTrabalhoService.inicial(scanner);
                    break;
                case 3:
                    crudFuncionarioService.inicial(scanner);
                    break;
                case 4:
                    relatoriosService.inicial(scanner);
                    break;
                case 5:
                    relatorioFuncionarioDinamicoService.inicial(scanner);
                    break;
                default:
                    System.out.println("Finalizando");
                    system = false;
                    break;
            }
        }
    }
}
