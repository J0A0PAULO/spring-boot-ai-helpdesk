package br.com.helpDesk.mensagens.service;

import br.com.helpDesk.chamados.entity.Chamado;
import br.com.helpDesk.chamados.repository.ChamadoRepository;
import br.com.helpDesk.config.GPTconfig;
import br.com.helpDesk.exceptions.NotFound;
import br.com.helpDesk.mensagens.dto.GptRequest;
import br.com.helpDesk.mensagens.dto.GptResponse;
import br.com.helpDesk.mensagens.dto.MensagensResponse;
import br.com.helpDesk.mensagens.entity.Mensagens;
import br.com.helpDesk.mensagens.mapper.MensagensMapper;
import br.com.helpDesk.mensagens.repository.MensagensRepository;
import br.com.helpDesk.usuarios.entity.Usuario;
import br.com.helpDesk.usuarios.usuarioRepository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MensagemService {

    private final GPTconfig gpTconfig;
    private final UsuarioRepository usuarioRepository;
    private final ChamadoRepository chamadoRepository;
    private final MensagensRepository mensagensRepository;
    private final MensagensMapper mensagensMapper;

    public MensagemService(GPTconfig gpTconfig, UsuarioRepository usuarioRepository, ChamadoRepository chamadoRepository, MensagensRepository mensagensRepository, MensagensMapper mensagensMapper) {
        this.gpTconfig = gpTconfig;
        this.usuarioRepository = usuarioRepository;
        this.chamadoRepository = chamadoRepository;
        this.mensagensRepository = mensagensRepository;
        this.mensagensMapper = mensagensMapper;
    }

    public List<MensagensResponse> listarMensagens(Long id) {

        Usuario usuario = usuarioRepository.findById(id).orElseThrow(() -> new NotFound("usuario não encontrado"));

        List<MensagensResponse> listaDeMensagensDoUsuarios = mensagensRepository.findByUsuario(usuario.getId()).stream().map(msm -> mensagensMapper.toDTO(msm)).toList();

        return listaDeMensagensDoUsuarios;

    }

    public MensagensResponse listarMensagenPorId(Long idUsuario , Long id) {

        Usuario usuario = usuarioRepository.findById(idUsuario).orElseThrow(() -> new NotFound("usuario não encontrado"));

        Mensagens mensagens = mensagensRepository.findById(id).orElseThrow(() -> new NotFound("mensagem por id não encontrado"));

        if (!usuario.getId().equals(mensagens.getUsuario().getId())) {
            throw new NotFound("mensagem nao existe");
        }

        MensagensResponse dto = mensagensMapper.toDTO(mensagens);

        return dto;
    }

    @Scheduled(fixedRate = 300000)
    @Transactional
    public void gerarResposta() throws RuntimeException {

        String iaPrompt = """
                Você é um assistente de suporte técnico Nível 1 de HelpDesk .
                Sua tarefa é ler o problema relatado pelo usuário e fornecer uma resposta objetiva,
                educada e passo a passo de como resolver ou qual procedimento seguir.
                Evite termos excessivamente complexos sem explicação.
                """;

        List<Chamado> aberto = chamadoRepository.findAllStatus("ABERTO");

        if (aberto.isEmpty()) {
            throw new NullPointerException();
        }

        for (Chamado chamado : aberto) {

            GptRequest gptRequest = new GptRequest(
                    "gpt-4o-mini",
                    List.of(
                            new GptRequest.Messages("developer", iaPrompt),
                            new GptRequest.Messages("user", "Descrição do chamado: " + chamado.getDescricao())
                    )
            );

            GptResponse response = gpTconfig.webClient()
                    .post()
                    .uri("/chat/completions")
                    .bodyValue(gptRequest)
                    .retrieve()
                    .bodyToMono(GptResponse.class)
                    .block();

            if (response != null && response.choices() != null && !response.choices().isEmpty()) {

                for (GptResponse.Choices choices : response.choices()) {
                    String conteudo = choices.message().content();
                    Mensagens mensagens = new Mensagens();
                    mensagens.setConteudo(conteudo);
                    mensagens.setChamado(chamado);
                    mensagens.setUsuario(chamado.getUsuario());

                    mensagensRepository.save(mensagens);
                }

                chamado.setStatus("FECHADO");
                chamadoRepository.save(chamado);
            }
        }
    }
}
