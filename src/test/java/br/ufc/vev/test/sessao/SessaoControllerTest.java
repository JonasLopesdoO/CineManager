package br.ufc.vev.test.sessao;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.time.LocalTime;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.SessaoController;
import br.ufc.vev.service.FilmeService;
import br.ufc.vev.service.GeneroService;
import br.ufc.vev.service.SalaService;
import br.ufc.vev.service.SessaoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SessaoControllerTest {

    @Autowired
    SessaoController sessaoController;
    @Autowired
    SessaoService sessaoService;
    @Autowired
    SalaService salaService;

    @Autowired
    GeneroService generoService;
    @Autowired
    FilmeService filmeSevice;

    @Test
    public void indexSessaoControllerTest() {
        assertNotNull(sessaoController.index());
    }

    @Test
    public void formularioSessaoControllerTest() {
        assertNotNull(sessaoController.formularioSessao());
    }

    @Test
    public void salvarSessaoControllerTest() {
        String inicio = "10/11/2018";
        String fim = "20/11/2018";
        String horario = "20:30";

        assertNotNull(sessaoController.salvaSessao(horario, inicio, fim));
    }

    @Test
    public void atualizarSessaoControllerTest() {
        Sessao sessao;
        LocalDate inicio = LocalDate.of(2018, 11, 10);
        LocalDate fim = LocalDate.of(2018, 11, 20);
        LocalTime horario = LocalTime.of(20,30);

        sessao = new Sessao(horario, inicio, fim);
        Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);


        LocalDate fimData = LocalDate.of(2018, 11, 30);
        sessaoBuscada.setDataFim(fimData);
        assertNotNull(sessaoController.atualizaSessao(sessaoBuscada.getId()));
    }

    @Test
    public void buscaSessaoControllerTest() {
        Sessao sessao;
        LocalDate inicio = LocalDate.of(2018, 11, 10);
        LocalDate fim = LocalDate.of(2018, 11, 20);
        LocalTime horario = LocalTime.of(20,30);

        sessao = new Sessao(horario, inicio, fim);

        Sessao sessaoRetornada = sessaoService.salvarSessao(sessao);
        assertNotNull(sessaoController.buscaSessao(sessaoRetornada.getId()));
        assertNotNull(sessaoController.detalhesSessao(sessaoRetornada.getId()));
    }

    @Test
    public void getAllSessaoControllerTest() {
        sessaoController.getAllSessao();
    }

    @Test
    public void excluirSessaoControllerTest() {
        Sessao sessao;
        LocalDate inicio = LocalDate.of(2018, 11, 10);
        LocalDate fim = LocalDate.of(2018, 11, 20);
        LocalTime horario = LocalTime.of(20,30);

        sessao = new Sessao(horario, inicio, fim);

        Sessao sessaoRetornada = sessaoService.salvarSessao(sessao);
        assertNotNull(sessaoController.excluiSessao(sessaoRetornada.getId()));
    }

    @Test
    public void getSessaoPorCidadeControllerTest() {
        String cidade = "Quixadá";

        sessaoController.verTodasPorCidade(cidade);
    }

    @Test
    public void vinculaFilmeASessaoControllerTest() {
        Filme Filme = new Filme();
        Filme.setNome("DeadPool");
        Filme.setSinopse("Filme Top");;
        Filme.setDuracao(90);

        Sessao sessao;
        LocalDate inicio = LocalDate.of(2018, 11, 10);
        LocalDate fim = LocalDate.of(2018, 11, 20);
        LocalTime horario = LocalTime.of(20,30);

        sessao = new Sessao(horario, inicio, fim);
        Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);
        Filme filmeBuscado = filmeSevice.salvarFilme(Filme);

        assertNotNull(sessaoController.vincularFilmeASessao(sessaoBuscada.getId(), filmeBuscado.getId()));
        assertNotNull(sessaoController.desvinculaFilmeDaSessao(sessaoBuscada.getId(), filmeBuscado.getId()));
    }

    @Test
    public void vinculaSalaASessao() {
        String nome = "Sala A1";
        int capacidade = 150;

        Sala sala = new Sala();
        sala.setNome(nome);
        sala.setCapacidade(capacidade);

        Sala salaRecebida = salaService.salvarSala(sala);

        Sessao sessao;
        LocalDate inicio = LocalDate.of(2018, 11, 10);
        LocalDate fim = LocalDate.of(2018, 11, 20);
        LocalTime horario = LocalTime.of(20,30);

        sessao = new Sessao(horario, inicio, fim);
        Sessao sessaoBuscada = sessaoService.salvarSessao(sessao);

        assertNotNull(sessaoController.vincularSalaASessao(sessaoBuscada.getId(), salaRecebida.getId()));
        assertNotNull(sessaoController.desvinculaSalaDaSessao(sessaoBuscada.getId(), salaRecebida.getId()));
    }

}