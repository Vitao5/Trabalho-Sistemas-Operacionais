package Processo;

public class DataProcess {
    private String nome;
    private Integer tempo;
    private String status;
    private Integer tempoEspera;
    private Integer tempoChegada;
    private Integer prioridade;

    public DataProcess(String nome, Integer tempo, String status, Integer tempoEspera, Integer tempoChegada, Integer prioridade) {
        this.nome = nome;
        this.tempo = tempo;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.tempoChegada = tempoChegada;
        this.prioridade = prioridade;
    }

    public Integer getTempoChegada() {
        return tempoChegada;
    }

    public void setTempoChegada(Integer tempoChegada) {
        this.tempoChegada = tempoChegada;
    }

    public Integer getTempoEspera() {
        return tempoEspera;
    }

    public void setTempoEspera(Integer tempoEspera) {
        this.tempoEspera = tempoEspera;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTempo() {
        return tempo;
    }

    public void setTempo(Integer tempo) {
        this.tempo = tempo;
    }

    public Integer getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(Integer prioridade) {
        this.prioridade = prioridade;
    }
}