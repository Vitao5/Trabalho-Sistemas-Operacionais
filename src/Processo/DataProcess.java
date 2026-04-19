package Processo;

public class DataProcess {
    private String nome;
    private Integer tempo;
    private String status;
    private Integer tempoEspera;
    private Integer tempoChegada;

    public DataProcess(String nome, Integer tempo, String status, Integer tempoEspera, Integer tempoChegada) {
        this.nome = nome;
        this.tempo = tempo;
        this.status = status;
        this.tempoEspera = tempoEspera;
        this.tempoChegada = tempoChegada;

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
}
