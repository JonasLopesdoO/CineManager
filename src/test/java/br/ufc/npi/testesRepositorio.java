package br.ufc.npi;


import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import br.ufc.vev.bean.Filme;
import br.ufc.vev.bean.Sala;
import br.ufc.vev.bean.Sessao;
import br.ufc.vev.controller.FilmeController;
import br.ufc.vev.controller.SalaController;
import br.ufc.vev.repositorio.CinemaRepositorio;
import br.ufc.vev.repositorio.FilmeRepositorio;
import br.ufc.vev.repositorio.SalaRepositorio;
import br.ufc.vev.repositorio.SessaoRepositorio;
import br.ufc.vev.service.SessaoService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class  testesRepositorio{
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    @MockBean
    private SessaoRepositorio sessaoRepositorio;
    @MockBean
    private FilmeRepositorio filmeRepositorio;
    @MockBean
    private SalaRepositorio salaRepositorio;
    
    @Autowired
	SessaoService sessaoService;
    
    @MockBean
    private CinemaRepositorio cinemaRepositorio;
    
    @Autowired
    private MockMvc mockMvc;


    @Test
    public void adicionarSessao() {
    	MockitoAnnotations.initMocks(this);
		//configurando o stub usando mockito
		//stub da dependencia
    	SalaController salaController = Mockito.mock(SalaController.class);
    	FilmeController filmeController = Mockito.mock(FilmeController.class);
    	
    	Filme filme = new Filme();
    	Sala sala = new Sala();
    	
    	Mockito.when(filmeController.getFilmePorId(1)).thenReturn(filme);
    	Mockito.when(salaController.getSalaPorId(1)).thenReturn(sala);
    	
    	//exercitar a classe a ser testada
		LocalTime horario = LocalTime.parse("20:00");
		LocalDate dataInicio = LocalDate.parse("2018-05-12");
		LocalDate dataFim = LocalDate.parse("2018-06-12");
		
//		Sessao sessao = new Sessao(filme, sala, horario, dataInicio, dataFim);
		
		
		//verificacao
		Sessao sessaoEsperado = new Sessao(filme, sala, horario, dataInicio, dataFim);
		Sessao sessaoRecebido = sessaoService.getSessaoPorId(sessaoEsperado.getId());
		Assert.assertEquals(sessaoEsperado.getId(), sessaoRecebido.getId());
    }
//
//    @Test
//    public void listStudentsWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
//        List<Student> students = asList(new Student(1L, "Legolas", "legolas@lotr.com"), 
//        			new Student(2L, "Aragorn", "aragorn@lotr.com"));
//        BDDMockito.when(studentRepository.findAll()).thenReturn(students);
//        ResponseEntity<String> response = restTemplate.getForEntity("/v1/protected/students/", String.class);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
//    @Test 
//    public void getStudentsByIdWhenUsernameAndPasswordAreCorrectShouldReturnStatusCode200() {
//    	ResponseEntity<Student> response = restTemplate.getForEntity("/v1/protected/students/{id}", Student.class, 1L);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
//    }
//
//    @Test
//    public void getStudentsByIdWhenUsernameAndPasswordAreCorrectAndStudentDoesNotExistShouldReturnStatusCode404() {
//        ResponseEntity<Student> response = restTemplate.getForEntity("/v1/protected/students/{id}", Student.class, -1);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
//    }
//
//    @Test
//    public void deleteWhenUserHasRoleAdminAndStudentExistsShouldReturnStatusCode200() {
//    	BDDMockito.doNothing().when(studentRepository).deleteById(1L);
//        ResponseEntity<String> exchange = restTemplate.exchange("/v1/admin/students/{id}", DELETE, null, String.class, 1L);
//        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
//    }
//
//    @Test
//    @WithMockUser(username = "xx", password = "xx", roles = {"USER","ADMIN"})
//    public void deleteWhenUserHasRoleAdminAndStudentDoesNotExistShouldReturnStatusCode404() throws Exception {
//        BDDMockito.doNothing().when(studentRepository).deleteById(1L);
////        ResponseEntity<String> exchange = restTemplate.exchange("/v1/admin/students/{id}", DELETE, null, String.class, -1L);
////        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
//        mockMvc.perform(MockMvcRequestBuilders
//                .delete("/v1/admin/students/{id}", -1L))
//                .andExpect(MockMvcResultMatchers.status().isNotFound());
//    } 
//    @Test
//    @WithMockUser(username = "xx", password = "xx", roles = {"USER"})
//    public void deleteWhenUserDoesNotHaveRoleAdminShouldReturnStatusCode403() throws Exception {
//        BDDMockito.doNothing().when(studentRepository).deleteById(1L);
////        ResponseEntity<String> exchange = restTemplate.exchange("/v1/admin/students/{id}", DELETE, null, String.class, -1L);
////        Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
//        mockMvc.perform(MockMvcRequestBuilders.delete("/v1/admin/students/{id}", -1L))
//        				.andExpect(MockMvcResultMatchers.status().isForbidden());
//    }
//    	
//    @Test
//    public void createWhenNameIsNullShouldReturnStatusCode400BadRequest() throws Exception{
//        Student student = new Student(3L, null, "sam@lotr.com");
//        BDDMockito.when(studentRepository.save(student)).thenReturn(student);
//        ResponseEntity<String> response = restTemplate.postForEntity("/v1/admin/students/", student, String.class);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
//        Assertions.assertThat(response.getBody()).contains("fieldMessage","O campo nome do estudante é obrigatório");
//    }
//    @Test
//    public void createShouldPersistDataAndReturnStatusCode201() throws Exception{
//        Student student = new Student(3L, "Sam", "sam@lotr.com");
//        BDDMockito.when(studentRepository.save(student)).thenReturn(student);
//        ResponseEntity<Student> response = restTemplate.postForEntity("/v1/admin/students/", student, Student.class);
//        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
//        Assertions.assertThat(response.getBody().getId()).isNotNull();
//    }
}
