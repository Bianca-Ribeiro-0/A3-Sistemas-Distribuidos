package model;

//Pegando as propriedades da Api de Cep
public class ViaCep {
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    public String getCep() {
        return cep;
    }

    public ViaCep setCep(String cep) {
        this.cep = cep;
        return this;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public ViaCep setLogradouro(String logradouro) {
        this.logradouro = logradouro;
        return this;
    }

    public String getComplemento() {
        return complemento;
    }

    public ViaCep setComplemento(String complemento) {
        this.complemento = complemento;
        return this;
    }

    public String getBairro() {
        return bairro;
    }

    public ViaCep setBairro(String bairro) {
        this.bairro = bairro;
        return this;
    }

    public String getLocalidade() {
        return localidade;
    }

    public ViaCep setLocalidade(String localidade) {
        this.localidade = localidade;
        return this;
    }

    public String getUf() {
        return uf;
    }

    public ViaCep setUf(String uf) {
        this.uf = uf;
        return this;
    }

    public String getIbge() {
        return ibge;
    }

    public ViaCep setIbge(String ibge) {
        this.ibge = ibge;
        return this;
    }

    public String getGia() {
        return gia;
    }

    public ViaCep setGia(String gia) {
        this.gia = gia;
        return this;
    }

    public String getDdd() {
        return ddd;
    }

    public ViaCep setDdd(String ddd) {
        this.ddd = ddd;
        return this;
    }

    public String getSiafi() {
        return siafi;
    }

    public ViaCep setSiafi(String siafi) {
        this.siafi = siafi;
        return this;
    }

    @Override
    public String toString() {
        return "{"
                + "\"cep\":\"" + (cep != null ? cep : "") + "\""
                + ", \"logradouro\":\"" + (logradouro != null ? logradouro : "") + "\""
                + ", \"complemento\":\"" + (complemento != null ? complemento : "") + "\""
                + ", \"bairro\":\"" + (bairro != null ? bairro : "") + "\""
                + ", \"localidade\":\"" + (localidade != null ? localidade : "") + "\""
                + ", \"uf\":\"" + (uf != null ? uf : "") + "\""
                + ", \"ibge\":\"" + (ibge != null ? ibge : "") + "\""
                + ", \"gia\":\"" + (gia != null ? gia : "") + "\""
                + ", \"ddd\":\"" + (ddd != null ? ddd : "") + "\""
                + ", \"siafi\":\"" + (siafi != null ? siafi : "") + "\""
                + "}";
    }
}

